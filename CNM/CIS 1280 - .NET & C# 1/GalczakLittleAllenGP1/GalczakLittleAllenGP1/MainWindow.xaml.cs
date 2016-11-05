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

namespace GalczakLittleAllenGP1
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {

        // Making a new game object
        Game game = new Game();

        public MainWindow()
        {
            InitializeComponent();
            UpdateDisplay();
        }

        void UpdateDisplay()
        {
            // Setting the listbox' source to playerlocation's travel options
            lbDirection.ItemsSource = game.PlayerLocation.TravelOptions;
            lbDirection.Items.Refresh();

            // Setting textbox with players location
            txtLocation.Text = game.PlayerLocation.LocationDesc;
        }

        private void lbDirection_MouseDoubleClick(object sender, MouseButtonEventArgs e)
        {

            game.PlayerLocation = ((TravelOption)lbDirection.SelectedItem).Location;
            UpdateDisplay();
        }

    }
}
