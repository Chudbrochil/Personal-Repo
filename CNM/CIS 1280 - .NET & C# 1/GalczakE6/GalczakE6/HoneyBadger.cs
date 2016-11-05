using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GalczakE6
{
    class HoneyBadger : ZooAnimal
    {
        public override string getName()
        {
            return "I am the ferious honey badger!\n";
        }
        public override string getHabitat()
        {
            return "The honey badger lives in the forest; hiding in wait for it's prey.\n";
        }
        public override string getFood()
        {
            return "The honey badger eats whatever it wants. It's rumored that a heroic honey badger once ate an entire bear.\n";
        }
    }
}
