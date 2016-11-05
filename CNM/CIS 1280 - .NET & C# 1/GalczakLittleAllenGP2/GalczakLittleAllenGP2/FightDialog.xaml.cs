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
using System.Windows.Shapes;

namespace GalczakLittleAllenGP2
{
    /// <summary>
    /// Interaction logic for FightDialog.xaml
    /// </summary>
    public partial class FightDialog : Window
    {

        private double light;
        private double wind;
        private double range;

        Random rand;
        public FightDialog()
        {
            InitializeComponent();
            rand = new Random();

            //Random numbers for light,wind,range
            light = rand.NextDouble();
            wind = rand.NextDouble();
            range = rand.Next(1001);

            //Put light,wind,range into string and display 
            txbLight.Text = light.ToString();
            txbWind.Text = wind.ToString();
            txbRange.Text = range.ToString();
        }

        public Alien Alien { get; set; }

        public string Outcome { get; set; }

        public Player Player { get; set; }

        // Event Handlers
        // btnAttack_Click
        // Button_Click

        public FightDialog(Alien alien, Player player)
        {
            Alien = alien;
            Player = player;
        }

        public void Update()
        {
            //Update slider with the health of the player and Alien
            sldAlien.Value = Alien.Health;
            sldPlayer.Value = Player.Health;
        }

        private void btnAttack_Click(object sender, RoutedEventArgs e)
        {
            //Passing in the range,wind,light to the weapon and subtracting from the health
 
            Alien.Health -= Player.Weapon.Attack(range, wind, light);
            Player.Health -= Alien.Weapon.Attack(range, wind, light);

            Update();

            if(Player.Health <= 0 || Alien.Health <= 0)
            {
                //Close the dialog if player health of Alien health is 0
                this.Close();
            }
        }

        private void btnRunAway_Click(object sender, RoutedEventArgs e)
        {
            //Close the dialog 
            this.Close();
        }
    }
}