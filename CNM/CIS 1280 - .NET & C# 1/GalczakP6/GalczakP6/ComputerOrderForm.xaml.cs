using GalczakP6;
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
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace GalczakP6
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class ComputerOrderForm : Window
    {
        private ComputerOrder comp;

        public ComputerOrder Comp
        {
            get { return comp; }
            set { comp = value; }
        }


        public ComputerOrderForm(ComputerOrder computerOrder)
        {
            InitializeComponent();

            comp = computerOrder;

            // Setting local arrays to keep the names of the fields from the ComputerOrder class
            string[] driveItems = comp.DriveInfo;
            string[] moboItems = comp.MoboInfo;
            string[] memItems = comp.MemoryInfo;
            string[] periphItems = comp.PeripheralsInfo;

            // Setting drive radio buttons
            radDrive1.Content = driveItems[0];
            radDrive2.Content = driveItems[1];
            radDrive3.Content = driveItems[2];

            // Setting mobo collection list for the combobox
            cbxMobo.ItemsSource = moboItems;

            // Setting memory radio buttons
            radMem1.Content = memItems[0];
            radMem2.Content = memItems[1];
            radMem3.Content = memItems[2];

            // Setting up peripheral checkboxes
            cbPeriph1.Content = periphItems[0];
            cbPeriph2.Content = periphItems[1];
            cbPeriph3.Content = periphItems[2];
            cbPeriph4.Content = periphItems[3];
            cbPeriph5.Content = periphItems[4];
            cbPeriph6.Content = periphItems[5];
            cbPeriph7.Content = periphItems[6];
            cbPeriph8.Content = periphItems[7];


        }

        // Default constructor - Links to overloaded constructor
        public ComputerOrderForm():this(new ComputerOrder())
        {
            

            
        }

        private void BtnSubmit_Click(object sender, RoutedEventArgs e)
        {
            this.DialogResult = true;
            // Groupbox selection for motherboards
            if (cbxMobo.SelectedIndex == 0) { comp.MoboOptionIndex = 0; }
            if (cbxMobo.SelectedIndex == 1) { comp.MoboOptionIndex = 1; }
            if (cbxMobo.SelectedIndex == 2) { comp.MoboOptionIndex = 2; }

            // Drive index
            if (radDrive1.IsChecked == true) { comp.DriveOptionIndex = 0; }
            if (radDrive2.IsChecked == true) { comp.DriveOptionIndex = 1; }
            if (radDrive3.IsChecked == true) { comp.DriveOptionIndex = 2; }

            // Memory Index
            if (radMem1.IsChecked == true) { comp.MemoryOptionIndex = 0; }
            if (radMem2.IsChecked == true) { comp.MemoryOptionIndex = 1; }
            if (radMem3.IsChecked == true) { comp.MemoryOptionIndex = 2; }

            // Creating new list for peripherals
            List<int> tempPeriphList = new List<int>();

            if (cbPeriph1.IsChecked == true) { tempPeriphList.Add(0); }
            if (cbPeriph2.IsChecked == true) { tempPeriphList.Add(1); }
            if (cbPeriph3.IsChecked == true) { tempPeriphList.Add(2); }
            if (cbPeriph4.IsChecked == true) { tempPeriphList.Add(3); }
            if (cbPeriph5.IsChecked == true) { tempPeriphList.Add(4); }
            if (cbPeriph6.IsChecked == true) { tempPeriphList.Add(5); }
            if (cbPeriph7.IsChecked == true) { tempPeriphList.Add(6); }
            if (cbPeriph8.IsChecked == true) { tempPeriphList.Add(7); }

            // Setting the temporary list object into the class' peripheral list object
            comp.PeriphList = tempPeriphList;

            // Displaying to user
            txbResults.Text = comp.OrderSummary;
        }

        private void btnClear_Click(object sender, RoutedEventArgs e)
        {
            this.DialogResult = true;
            // Clearing all the fields
            radDrive1.IsChecked = false;
            radDrive2.IsChecked = false;
            radDrive3.IsChecked = false;
            radMem1.IsChecked = false;
            radMem2.IsChecked = false;
            radMem3.IsChecked = false;
            cbxMobo.SelectedIndex = -1;
            cbPeriph1.IsChecked = false;
            cbPeriph2.IsChecked = false;
            cbPeriph3.IsChecked = false;
            cbPeriph4.IsChecked = false;
            cbPeriph5.IsChecked = false;
            cbPeriph6.IsChecked = false;
            cbPeriph7.IsChecked = false;
            cbPeriph8.IsChecked = false;
            txbResults.Clear();
        }
    }
}
