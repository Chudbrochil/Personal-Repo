using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GalczakLittleAllenGP2
{
    public class GameObject
    {
        private string description;

        public string Description
        {
            get { return description; }
            set { description = value; }
        }

        public GameObject()
        {
            description = "default";
        }

        public GameObject(string description)
        {
            this.description = description;
        }

        public override string ToString()
        {
            return description;
        }
    }
}