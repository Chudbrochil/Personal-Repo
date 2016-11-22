// Anthony Galczak - agalczak@cnm.edu
// CIS 2284 - Program 4 - Depreciation on a ASP.NET Web Form

using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace GalczakP4.Models
{
    public abstract class DepreciationBase
    {
        private int age;

        public int Age
        {
            get { return age; }
            set { age = value; }
        }

        private int lifetime;

        public int Lifetime
        {
            get { return lifetime; }
            set { lifetime = value; }
        }

        private decimal salvageValue;

        public decimal SalvageValue
        {
            get { return salvageValue; }
            set { salvageValue = value; }
        }

        private decimal startingValue;

        public decimal StartingValue
        {
            get { return startingValue; }
            set { startingValue = value; }
        }

        public decimal CurrentValue { get; set; }

        public abstract void Calc();

        // Default constructor
        public DepreciationBase()
        {
            // Loading some initialized values in case user sneaks by somehow without entering values
            //Age = 2;
            //Lifetime = 5;
            //SalvageValue = 1000;
            //StartingValue = 10000;
        }

        public override string ToString()
        {
            string output = "";
            output += "Starting value of " + StartingValue.ToString("C");
            output += " and salvage value of " + SalvageValue.ToString("C");
            output += " after " + Lifetime;
            output += " years, has a value of " + CurrentValue.ToString("C");
            output += " at year " + Age;
            return output;
        }




    }
}