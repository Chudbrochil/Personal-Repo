using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GalczakE6
{
    class Dodo : ZooAnimal  
    {
        public override string getName()
        {
            return "I am the derpy Dodo!\n";
        }
        public override string getHabitat()
        {
            return "I live on the island of Mauritus. Actually just kidding, I'm extinct.\n";
        }
        public override string getFood()
        {
            return "I like eating fruit and various vegetables.\n";
        }
    }
}
