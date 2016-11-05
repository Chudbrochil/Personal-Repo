using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GalczakE8
{
    public class InventoryItem
    {

        private string name;

        public string Name
        {
            get { return name; }
            set { name = value; }
        }

        // Constructor
        public InventoryItem(string nm)
        {
            name = nm;
        }

        public override string ToString()
        {
            return name;
        }
        
    }
}
