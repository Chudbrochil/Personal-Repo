using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GalczakLittleAllenGP2
{
    class NotGoodHidingPlace : IHidingPlace
    {
        private GameObject hiddenObject;

        public GameObject HiddenObject
        {
            get { return hiddenObject; }
            set { hiddenObject = value; }
        }
        
        public NotGoodHidingPlace(string description)
        {

        }

        public GameObject Search()
        {
            hiddenObject = null;
            return hiddenObject;
        }
    }
}