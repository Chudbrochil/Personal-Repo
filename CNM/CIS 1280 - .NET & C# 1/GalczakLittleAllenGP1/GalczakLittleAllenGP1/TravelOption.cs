//Vaughn Allen 
//vallen10@cnm.edu
//Program 5
//TravelOptions.cs

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GalczakLittleAllenGP1
{
    // Vaughn's territory
    public class TravelOption
    {
        //Properties
        public string Description { get; set; }

        public GameLocation Location
        {
            get { return location; }
            set { location = value; }
        }
        private GameLocation location;

        //Constructor
        public TravelOption(GameLocation loc, string description)
        {
           
            Description = description;
            location = loc;
        }

        public override string ToString()
        {
            return Description;
        }
    }
}
