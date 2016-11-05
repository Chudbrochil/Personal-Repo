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

namespace GalczakE6
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

        private void Button_Click(object sender, RoutedEventArgs e)
        {


            int zooIndex = 0;

            if (radHoneyBadger.IsChecked == true)
            {
                zooIndex = 0;
            }
            else if (radPorpoise.IsChecked == true)
            {
                zooIndex = 1;
            }
            else
            {
                zooIndex = 2;
            }

            ZooAnimal[] animal = new ZooAnimal[3];


            animal[0] = new HoneyBadger();
            animal[1] = new Porpoise();
            animal[2] = new Dodo();

            txbResults.Text = animal[zooIndex].getName() + animal[zooIndex].getHabitat() + animal[zooIndex].getFood();

        }
    }
}
