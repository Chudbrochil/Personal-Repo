using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GalczakE6
{
    class Porpoise : ZooAnimal
    {
        public override string getName()
        {
            return "I am the glorious mini-whale, the porpoise!\n";
        }
        public override string getHabitat()
        {
            return "I live in the ocean.\n";
        }
        public override string getFood()
        {
            return "I like eating small fatty fish such as herring and mackerel.\n";
        }
    }
}
