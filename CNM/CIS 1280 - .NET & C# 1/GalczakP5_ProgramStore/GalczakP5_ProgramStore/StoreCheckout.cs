using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GalczakP5_ProgramStore
{
    class StoreCheckout
    {
        private static string[] furnitureNames = {"Oak Table w/ 8 Chairs", "Fancy Black Desk Lamp", "FutureTech Office Chair", 
                                                 "Karoshi Office Couch", "Card Table", "Folding Chair"};
        private static double[] furniturePrices = { 1299.99, 199.99, 699.99, 999.99, 29.99, 19.99 };

        private static string[] suppliesNames = { "Box of Staples", "Crate of Nerf Darts", "Case of Fancy Pen #92", "Demotivational Posters", "Swingline Stapler" };
        
        private static double[] suppliesPrices = { 9.99, 209.95, 429.99, 49.99, 19.99 };

        private double totalCost;

        private List<int> furnIndices = new List<int>();

        public List<int> FurnIndices
        {
            get { return furnIndices; }
            set { furnIndices = value; }
        }

        private List<int> suppIndices = new List<int>();

        public List<int> SuppIndices
        {
            get { return suppIndices; }
            set { suppIndices = value; }
        }
        
        

        // Get methods for my private arrays holding data for office supplies and furniture
        public string[] GetFurnitureNames 
        {
            get { return furnitureNames; } 
        }

        public double[] GetFurniturePrices
        {
            get { return furniturePrices; }
        }

        public string[] GetSuppliesNames
        {
            get { return suppliesNames; }
        }

        public double[] GetSuppliesPrices
        {
            get { return suppliesPrices; }
        }

        private ComputerOrder compOrder;

        public ComputerOrder CompOrder
        {
            get { return compOrder; }
            set { compOrder = value; }
        }

        public override string ToString()
        {
            Calculate();
            string output = "";

            // If they ordered a computer, add the computer order order summary to their information
            if (compOrder != null)
            {
                output += "You ordered a computer.\n";
                output += compOrder.OrderSummary;
            }

            // Adding text for each furniture item bought
            output += "List of Furniture:\n";
            foreach(int item in furnIndices)
            {
                output += furnitureNames[item] + " - $" + furniturePrices[item] + "\n";
            }

            // Adding text for each office supply item bought
            output += "List of Office Supplies:\n";
            foreach(int item in suppIndices)
            {
                output += suppliesNames[item] + " - $" + suppliesPrices[item] + "\n";
            }

            // Adding total cost to the end of the output
            output += "Your total order cost is: $" + totalCost;

            // Returning the big string
            return output;
        }

        private void Calculate()
        {
            this.totalCost = 0.0;
            // Adding the computer cost to total cost if they ordered a computer
            if (compOrder != null)
            {
                totalCost += compOrder.totalPrice;
            }

            // Adding furniture prices into total cost
            foreach(int item in furnIndices)
            {
                totalCost += furniturePrices[item];
            }

            // Adding supplies prices into total cost
            foreach(int item in suppIndices)
            {
                totalCost += suppliesPrices[item];
            }

        }
        
        
    }
}
