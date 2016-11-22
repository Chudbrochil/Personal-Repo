// Anthony Galczak - agalczak@cnm.edu
// CIS 2284 - Program 4 - Depreciation on a ASP.NET Web Form

using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace GalczakP4.Models
{
    public class DepreciationStraightLine : DepreciationBase
    {
        public override void Calc()
        {
            // Straight-Line Depreciation Formula is:
            // (initial cost - salvage value) * (age / lifetime)
            decimal multiplier = (decimal)base.Age / (decimal)base.Lifetime;
            decimal depreciation = (base.StartingValue - base.SalvageValue) * multiplier;
            base.CurrentValue = base.StartingValue - depreciation;
        }

        public override string ToString()
        {
            string output = base.ToString();
            output += " using straight line depreciation.";
            return output;
        }
    }
}