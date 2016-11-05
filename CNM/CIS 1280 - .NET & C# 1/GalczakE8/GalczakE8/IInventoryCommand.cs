using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GalczakE8
{
    // interface for addonecommand and addmultiplecommand to inherit from
    interface IInventoryCommand
    {
        void Do();
        void Undo();
    }
}
