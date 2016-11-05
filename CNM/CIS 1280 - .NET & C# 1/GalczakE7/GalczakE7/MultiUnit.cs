using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GalczakE7
{
    class MultiUnit : HousingBase
    {
        private string complexName;

        public string ComplexName
        {
            get { return complexName; }
            set { complexName = value; }
        }



        private int numUnits;

        public int NumUnits
        {
            get { return numUnits; }
            set { numUnits = value; }
        }

        private decimal rentAmount;

        public decimal RentAmount
        {
            get { return rentAmount; }
            set { rentAmount = value; }
        }

        // Methods
        // Adding more information onto the inherited gethousinginfo from housingbase
        public override string GetHousingInfo()
        {   
            return base.GetHousingInfo() + "\nComplex Name: " + ComplexName + "\nNumber of Units: " + NumUnits + "\nTotal Rent Amount: $" + TotalRent();
        }

        // Calculating total rent based on a multi-unit
        public override decimal TotalRent()
        {
            RentAmount = RentAmount * NumUnits;
            return RentAmount;
        }

    }
}
