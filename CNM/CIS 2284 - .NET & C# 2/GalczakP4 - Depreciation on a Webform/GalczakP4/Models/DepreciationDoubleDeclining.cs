// Anthony Galczak - agalczak@cnm.edu
// CIS 2284 - Program 4 - Depreciation on a ASP.NET Web Form

using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace GalczakP4.Models
{
    public class DepreciationDoubleDeclining : DepreciationBase
    {
        public override void Calc()
        {
            // Saving the initial value as we're going to use this as a recursive value for
            // calculating following depreciation per year
            decimal originalStartingValue = base.StartingValue;

            // Catching the special case if user inputs a year equal or greater to it's lifetime
            // then it becomes equivalent to it's salvage value
            if(base.Age >= base.Lifetime)
            {
                base.CurrentValue = base.SalvageValue;
            }
            else
            {
                // Running the algorithm for as many years old the item is
                for (int i = 0; i < base.Age; ++i)
                {
                    // Formula is 2x straight-line alg against just the startingvalue calculated each year
                    decimal multiplier = (decimal)1.0 / (decimal)base.Lifetime;
                    decimal depreciation = base.StartingValue * (multiplier * 2);
                    base.StartingValue = base.StartingValue - depreciation;
                }
                base.CurrentValue = base.StartingValue;
                base.StartingValue = originalStartingValue;
            }

        }

        public override string ToString()
        {
            string output = base.ToString();
            output += " using double declining depreciation.";
            return output;
        }
    }
}