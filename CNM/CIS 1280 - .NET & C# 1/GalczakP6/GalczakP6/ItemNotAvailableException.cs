using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GalczakP6
{
    class ItemNotAvailableException : Exception
    {
        public ItemNotAvailableException()
            : base("This item is not available!")
        {
        }

        // Using custom message within ApplicationException using :base, this calls Exception(string param)
        public ItemNotAvailableException(string message)
            : base(message)
        {
        }
    }
}
