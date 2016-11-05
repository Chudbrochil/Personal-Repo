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

namespace GalczakLittleAllenGP2
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        Alien alien;
        List<Alien> aliens;
        Map map;
        Player player;
        Random rand;

        bool didTheyWait = false;

        public MainWindow()
        {
            InitializeComponent();

            //Setting player location
            map = new Map();
            player = new Player(map.GameMap[0]);
            // Setting players inventory max to 8 and giving them a default weapon of a spear
            player.MaxInventory = 8;
            player.Weapon = new Spear();
            //Adding some pocket lint to start with for the player's inventory - TODO: This doesn't work...
            //PocketLint lint = new PocketLint();
            //player.AddInventoryItem(lint);

            aliens = new List<Alien>();
            rand = new Random();
            //Add some aliens and randomize them in the map
            aliens.Add(alien = new Alien(map.GameMap[rand.Next(1,9)]));
            UpdateDisplay();
            Update();
        }


        public void DropItem()
        {

        }

        public void TakeItem()
        {

        }

        public void Update()
        {
            player.Update();
            //Add update for alien
            foreach(var i in aliens)
            {
                //Go through aliens and update them
                i.Update();
            }
            string statusText = "";

            // If they selected to wait, first say they waited a turn
            if (didTheyWait == true)
            {
                statusText += "You waited a turn.\n";
                didTheyWait = false;
            }

            // Giving stats on inventory size and current weapon to player
            statusText += "Your Max Inventory Size Is: " + player.MaxInventory.ToString() + "\nYour Current Equipped Weapon Is: " + player.Weapon.ToString();

            txbStatus.Text = statusText;
        }

        public void UpdateDisplay()
        {
            //Update the control for all the functions

            txbHealth.Text = player.Health.ToString();
            lbTravelOption.ItemsSource = player.Location.TravelOptions;
            //Dont know how to add inventory
            lbInventory.ItemsSource = player.Inventory;
            lbInventory.Items.Refresh();
            lbSearchorTake.Items.Refresh();
            lbTravelOption.Items.Refresh();

            txbLocation.Text = player.Location.Description;
        }

        private void lbInventory_MouseDoubleClick(object sender, MouseButtonEventArgs e)
        {

        }

        private void lbTravelOption_MouseDoubleClick(object sender, MouseButtonEventArgs e)
        {
            player.Location = ((TravelOption)lbTravelOption.SelectedItem).Location;
            UpdateDisplay();
            Update();
        }

        private void lbSearchorTake_MouseDoubleClick(object sender, MouseButtonEventArgs e)
        {

        }

        private void btnSearch_Click(object sender, RoutedEventArgs e)
        {


        }

        private void btnTake_Click(object sender, RoutedEventArgs e)
        {

        }

        private void btnDrop_Click(object sender, RoutedEventArgs e)
        {
            //Drop item from inventory and add it to location items
            lbSearchorTake.Items.Add(lbInventory.SelectedItem);
            lbInventory.Items.RemoveAt(lbInventory.SelectedIndex);
        }

        private void btnWait_Click(object sender, RoutedEventArgs e)
        {
            didTheyWait = true;
            Update();
        }
    }
}