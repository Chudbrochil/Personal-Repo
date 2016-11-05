using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Configuration;
using System.Data.SqlClient;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Forms;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace GalczakP6
{

    public class MyItem
    {
        public int KeyID { get; set; }
        public string Desc { get; set; }
        public decimal Price { get; set; }

        public override string ToString()
        {
            return Desc + " - " + Price.ToString("C");
        }
    }



    /// <summary>
    /// Interaction logic for StoreCheckoutForm.xaml
    /// </summary>
    public partial class StoreCheckoutForm : Window
    {
        // Creating a new store object and setting the items into the storecheckout form
        StoreCheckout store = new StoreCheckout();

        MyItem keyStore = new MyItem();

        public StoreCheckoutForm()
        {
            InitializeComponent();

            FormRefresh();

        }

        private void FormRefresh()
        {
            // Accessing the database to read the data to the form
            List<String> supplies = new List<String>();

            // Assigning the supplies list to populate the form
            for (int i = 0; i < store.SuppliesDesc.Count; ++i)
            {
                supplies.Add(store.SuppliesDesc[i] + " - " + store.SuppliesPces[i].ToString("C"));
            }

            // Making a new list of myitems so I can assign key id's to my items
            List<MyItem> someFurniture = new List<MyItem>();

            // Clearing the listbox of furniture in case you are adding/deleting/refreshing list
            lbFurniture.Items.Clear();

            // Assigning the furniture list to populate the form
            for (int i = 0; i < store.FurnitureDesc.Count; ++i)
            {
                MyItem aFurniture = new MyItem();
                aFurniture.Desc = store.FurnitureDesc[i];
                aFurniture.Price = store.FurniturePces[i];
                aFurniture.KeyID = i;

                // Adding the object that I created and assigned things to to the list of objects
                someFurniture.Add(aFurniture);

                // Adding the to string method from myitem class
                lbFurniture.Items.Add(aFurniture);

            }

            // Setting the checkboxes
            cbItem1.Content = supplies[0];
            cbItem2.Content = supplies[1];
            cbItem3.Content = supplies[2];
            cbItem4.Content = supplies[3];
            cbItem5.Content = supplies[4];
        }

        private void btnComputer_Click(object sender, RoutedEventArgs e)
        {
            // Creating a new computer order object and assigning the object into the computer order form constructor
            ComputerOrder compOrder = new ComputerOrder();

           
            ComputerOrderForm frmComputerOrder = new ComputerOrderForm(compOrder);
            
            // Showing the dialogbox for computer order
            frmComputerOrder.ShowDialog();
            if(frmComputerOrder.DialogResult == true)
            {
                store.CompOrder = compOrder;
            }

            txbComputerResults.Text = store.CompOrder.OrderSummary;

            
        }

        // Completing the entire order
        private void btnSubmit_Click(object sender, RoutedEventArgs e)
        {
            // In case user is trying to click the button multiple times this will clear the list of indices
            store.FurnIndices.Clear();
            store.SuppIndices.Clear();

            foreach(object item in lbFurniture.SelectedItems)
            {
                // Using the listbox of furniture to index the items to add to furnindices
                store.FurnIndices.Add(lbFurniture.Items.IndexOf(item));
            }

            // Adding price of each supplies item that is checked
            if (cbItem1.IsChecked == true) { store.SuppIndices.Add(0); }
            if (cbItem2.IsChecked == true) { store.SuppIndices.Add(1); }
            if (cbItem3.IsChecked == true) { store.SuppIndices.Add(2); }
            if (cbItem4.IsChecked == true) { store.SuppIndices.Add(3); }
            if (cbItem5.IsChecked == true) { store.SuppIndices.Add(4); }

            // Setting the store checkout's to string into the results textbox. Now that the indices are set, the math can be calculated
            txbResults.Text = store.ToString();

        }

        private void btnDelFurn_Click(object sender, RoutedEventArgs e)
        {
            if (lbFurniture.SelectedItem != null)
            {
                string delStr = "DELETE FROM Furniture WHERE Description = @Desc";

                string connStr = ConfigurationManager.ConnectionStrings["StoreDB"].ConnectionString;
                using (SqlConnection conn = new SqlConnection(connStr))
                {
                    SqlCommand delCmd = new SqlCommand(delStr, conn);

                    MyItem item = (MyItem)lbFurniture.SelectedItem;

                    delCmd.Parameters.AddWithValue("Desc", item.Desc);
                    conn.Open();
                    delCmd.ExecuteNonQuery();
                }

                // Refreshing the lists inside my class
                store.Refresh();

                //Re-populating the form
                FormRefresh();
            }

        }

        private void btnAddFurn_Click(object sender, RoutedEventArgs e)
        {
            // Checking to make sure that the fields for furniture name and furniture price have values to update/add
            if (txbFurnName != null && txbFurnPrice != null)
            {
                //Add furniture description and price to database
                if (lbFurniture.SelectedItem == null)
                {
                    string insStr = "INSERT INTO Furniture(Description, Price) VALUES(@Name, @Price)";

                    string connStr = ConfigurationManager.ConnectionStrings["StoreDB"].ConnectionString;
                    using (SqlConnection conn = new SqlConnection(connStr))
                    {
                        SqlCommand insCmd = new SqlCommand(insStr, conn);

                        // Setting the values that are designated within the SQL command above
                        insCmd.Parameters.AddWithValue("Name", txbFurnName.Text);
                        insCmd.Parameters.AddWithValue("Price", txbFurnPrice.Text);

                        // Opening a new database connection and executing the cmd
                        conn.Open();
                        insCmd.ExecuteNonQuery();
                    }

                }
                else
                {
                    // SQL string to be inserted into my storeDB
                    string updStr = "UPDATE Furniture SET Description = @Desc, Price = @Price WHERE Description = @OldDesc";

                    string connStr = ConfigurationManager.ConnectionStrings["StoreDB"].ConnectionString;
                    using (SqlConnection conn = new SqlConnection(connStr))
                    {
                        SqlCommand updCmd = new SqlCommand(updStr, conn);

                        // Creating a new string that is the description of the object the user has selected
                        string aDesc = ((MyItem)lbFurniture.SelectedItem).Desc;

                        // Setting the values that are designated within the SQL command above
                        updCmd.Parameters.AddWithValue("OldDesc", aDesc);
                        updCmd.Parameters.AddWithValue("Desc", txbFurnName.Text);
                        updCmd.Parameters.AddWithValue("Price", txbFurnPrice.Text);

                        conn.Open();
                        updCmd.ExecuteNonQuery();
                    }


                }

                // Refreshing the lists inside my class
                store.Refresh();

                //Re-populating the form
                FormRefresh();

                // Clearing the textboxes that the user inputted data into. These have been added into the database already
                txbFurnName.Clear();
                txbFurnPrice.Clear();

            }



        }



    }
}
