using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GalczakLittleAllenGP2
{
    public interface IWeapon : IPortable
    {
        int Attack(double range, double wind, double light);
    }
}