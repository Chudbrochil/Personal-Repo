using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GalczakLittleAllenGP2
{
    class GoodHidingPlace : IHidingPlace
    {
        private GameObject hiddenObject;

        public GameObject HiddenObject
        {
            get { return hiddenObject; }
            set { hiddenObject = value; }
        }
       
        public double Probability { get; set; }

        public GoodHidingPlace(string description)
        {            
            //Random number for a chance to get an object
            Random probabilityRand = new Random();
            Probability = probabilityRand.NextDouble();
        }

        public GameObject Search()
        {
            //Random number to compare to probability
            Random rand = new Random();
            double random = rand.NextDouble();
            
            //Return the hidden object
            if (random < Probability)
            {
                return hiddenObject;
            }
            else
            {
                hiddenObject = null;
                return hiddenObject; 
            }
        }
    }
}