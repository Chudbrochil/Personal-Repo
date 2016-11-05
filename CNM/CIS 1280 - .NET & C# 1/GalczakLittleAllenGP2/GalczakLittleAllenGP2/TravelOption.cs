using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GalczakLittleAllenGP2
{
    public class TravelOption
    {
        public string Description { get; set; }
        public MapLocation Location { get; set; }

        public override string ToString()
        {
            return Description;
        }

        public TravelOption(MapLocation location, string description)
        {
            Description = description;
            Location = location;
        }
    }
}