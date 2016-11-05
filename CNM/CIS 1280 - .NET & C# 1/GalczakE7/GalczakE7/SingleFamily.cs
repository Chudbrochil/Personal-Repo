using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GalczakE7
{
    class SingleFamily : HousingBase
    {
        private bool hasGarage;

        public bool HasGarage
        {
            get { return hasGarage; }
            set { hasGarage = value; }
        }

        private bool hasPorch;

        public bool HasPorch
        {
            get { return hasPorch; }
            set { hasPorch = value; }
        }

        private decimal rentAmount;

        public decimal RentAmount
        {
            get { return rentAmount; }
            set { rentAmount = value; }
        }

        private int size;

        public int Size
        {
            get { return size; }
            set { size = value; }
        }

        private string style;

        public string Style
        {
            get { return style; }
            set { style = value; }
        }

        private string subDivName;

        public string SubDivName
        {
            get { return subDivName; }
            set { subDivName = value; }
        }
        
        // Methods

        // Adding more information onto the inherited gethousinginfo from housingbase
        public override string GetHousingInfo()
        {
            // Static declaration of data
            return base.GetHousingInfo() + "\nHas Garage: " + HasGarage + "\nHas Porch: " + HasPorch + "\nTotal Rent Amount: $" + TotalRent() +
            "\nSize: " + Size + "sqft.\nStyle: " + Style + "\nSub Division Name: " + SubDivName;
        }

        // Calculating total rent based on single family house
        public override decimal TotalRent()
        {
            return RentAmount;
        }

    }
}
