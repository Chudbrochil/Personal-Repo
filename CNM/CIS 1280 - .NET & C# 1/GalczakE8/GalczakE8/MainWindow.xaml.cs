using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace GalczakE8
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        // Instantiating new lists
        List<InventoryItem> inventoryItems = new List<InventoryItem>();

        List<IInventoryCommand> commands = new List<IInventoryCommand>();

        public MainWindow()
        {
            InitializeComponent();
        }

        private void btnAddOne_Click(object sender, RoutedEventArgs e)
        {
            // Making an inventory item with the text of the item box sent as the parameter
            InventoryItem userItem = new InventoryItem(txbItem.Text);

            // Making a new add one command object in order to add one item to the inventory list
            AddOneCommand add = new AddOneCommand(inventoryItems, userItem);

            // Adding the user item to inventoryitems via the do method
            add.Do();

            // Adding the entire add object to the commands base object of iinventorycommand
            commands.Add(add);

            // Outputting into textbox results for each inventory item in this target list
            FormOut();

        }

        private void btnAddRand_Click(object sender, RoutedEventArgs e)
        {
            // Making an inventory item with the text of the item box sent as the parameter
            InventoryItem userItem = new InventoryItem(txbItem.Text);

            // Making a new add multiple command objet in order to add multiple items to the inventory list
            AddMultipleCommand multi = new AddMultipleCommand(inventoryItems, userItem);

            // Adding a multiple of 1-6 inventoryitem objects to inventoryitems list
            multi.Do();

            // Adding the multiple objects to the commands base object of iinventorycommand
            commands.Add(multi);

            // Outputting into textbox results for each inventory item in this target list
            FormOut();

        }

        private void btnUndo_Click(object sender, RoutedEventArgs e)
        {
                // commands is a list of IInventoryCommand objects, these can include addonecommand objects or addmultiplecommand objects
                // This line takes the last element of the list and runs Undo on it regardless of which kind of object it is.
                commands[commands.Count - 1].Undo();

                // Exception handling for in case user hits undo with nothing in the list. Stupid user....
                if (inventoryItems.Count > 0)
                {
                    // Outputting into textbox results for each inventory item in this target list
                    FormOut();

                    // Removing that last element so that you can do multiple undos
                    commands.RemoveAt(commands.Count - 1);
                    }
                else
                {
                    txbResults.Text = "You removed everything!";
                }
        }

        // Function to push output to the textbox txbResults, made this a function as it is used 3 separate times, DRY
        private void FormOut()
        {
            string output = "";
            foreach (InventoryItem itm in inventoryItems)
            {
                output += itm.Name + "\n";
                txbResults.Text = output;
            }
        }
    }
}
