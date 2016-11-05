using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GalczakLittleAllenGP2
{
    public class MapLocation
    {
        public string Description { get; set; }

        public List<GameObject> Items { get; set; }

        public List<TravelOption> TravelOptions { get; set; }
    
        public MapLocation(string descriptor)
        {
            // Perhaps needs to be some "items" stuff in here...

            Description = descriptor;

            TravelOptions = new List<TravelOption>();
        }

        public override string ToString()
        {
            return Description;
        }
    }
}