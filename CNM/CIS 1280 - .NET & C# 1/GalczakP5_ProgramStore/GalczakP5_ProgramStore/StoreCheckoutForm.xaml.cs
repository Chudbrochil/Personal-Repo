using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Forms;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace GalczakP5_ProgramStore
{
    /// <summary>
    /// Interaction logic for StoreCheckoutForm.xaml
    /// </summary>
    public partial class StoreCheckoutForm : Window
    {
        // Creating a new store object and setting the items into the storecheckout form
        StoreCheckout store = new StoreCheckout();

        

        public StoreCheckoutForm()
        {
            InitializeComponent();

            // Initializing form with furniture names and prices
            string[] furniture = new string[10];
            for (int i = 0; i < store.GetFurnitureNames.Length; ++i )
            {
                furniture[i] = store.GetFurnitureNames[i] + " - " + store.GetFurniturePrices[i];
            }
            lbFurniture.ItemsSource = furniture;

            // Initializing form with office supplies names and prices
            string[] supplies = new string[10];
            for (int i = 0; i < store.GetSuppliesNames.Length; ++i )
            {
                supplies[i] = store.GetSuppliesNames[i] + " - " + store.GetSuppliesPrices[i];
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

    }
}
