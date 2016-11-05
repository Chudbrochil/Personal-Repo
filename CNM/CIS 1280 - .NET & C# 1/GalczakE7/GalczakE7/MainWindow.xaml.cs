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

namespace GalczakE7
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        private void radMulti_Checked(object sender, RoutedEventArgs e)
        {
            // Instantiating Multiunit object
            MultiUnit multi = new MultiUnit();

            multi.Address = "500 Main St:";
            multi.NumUnits = 5;
            multi.RentAmount = 1200;

            // Displaying Housing Info to the results box
            txbResults.Text = multi.GetHousingInfo();

        }

        private void radSingle_Checked(object sender, RoutedEventArgs e)
        {
            // Instantiating Single Family Object
            SingleFamily single = new SingleFamily();

            single.Address = "1409 Homestead Ave. Apt 2B";
            single.Builder = "Bob";
            single.RentAmount = 800;

            // Displaying Housing Info to the Results box
            txbResults.Text = single.GetHousingInfo();
        }
    }
}
