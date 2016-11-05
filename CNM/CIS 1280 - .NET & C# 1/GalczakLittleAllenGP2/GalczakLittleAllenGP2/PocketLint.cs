using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GalczakLittleAllenGP2
{
    class PocketLint : IPortable
    {
        int size = 1;
        public int Size
        {
            get { return size; }
            set { size = value; }
        }

        
    }
}
