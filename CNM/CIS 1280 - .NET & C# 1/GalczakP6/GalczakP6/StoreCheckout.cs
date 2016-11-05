using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.Data.Common;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GalczakP6
{
    class StoreCheckout
    {
        private double totalCost;

        private List<int> furnIndices = new List<int>();

        private List<string> furnitureDesc;

        public List<string> FurnitureDesc
        {
            get { return furnitureDesc; }
            set { furnitureDesc = value; }
        }

        private List<string> suppliesDesc;

        public List<string> SuppliesDesc
        {
            get { return suppliesDesc; }
            set { suppliesDesc = value; }
        }

        private List<decimal> furniturePces;

        public List<decimal> FurniturePces
        {
            get { return furniturePces; }
            set { furniturePces = value; }
        }

        private List<decimal> suppliesPces;

        public List<decimal> SuppliesPces
        {
            get { return suppliesPces; }
            set { suppliesPces = value; }
        }
        
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
                output += furnitureDesc[item] + " - " + furniturePces[item].ToString("C") + "\n";
            }

            // Adding text for each office supply item bought
            output += "List of Office Supplies:\n";
            foreach(int item in suppIndices)
            {
                output += suppliesDesc[item] + " - " + suppliesPces[item].ToString("C") + "\n";
            }

            // Adding total cost to the end of the output
            output += "Your total order cost is: " + totalCost.ToString("C");

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
                totalCost += (double)furniturePces[item];
            }

            // Adding supplies prices into total cost
            foreach(int item in suppIndices)
            {
                totalCost += (double)suppliesPces[item];
            }

        }

        private void GetData(string sqlStr, out List<string> descriptions, out List<decimal> prices)
        {
            // Initializing lists
            descriptions = new List<string>();
            prices = new List<decimal>();

            string connStr = ConfigurationManager.ConnectionStrings["StoreDB"].ConnectionString;
            using (SqlConnection conn = new SqlConnection(connStr))
            {
                SqlCommand selectCmd = new SqlCommand(sqlStr, conn);
                conn.Open();
                SqlDataReader dr = selectCmd.ExecuteReader();
                while (dr.Read())
                {
                    descriptions.Add(dr.GetString(1));
                    prices.Add(dr.GetDecimal(2));
                }
            }
        }


        public StoreCheckout()
        {
            Refresh();
        }

        public void Refresh()
        {
            // Initializing the lists from the StoreDB
            GetData("SELECT * FROM Furniture", out furnitureDesc, out furniturePces);
            GetData("SELECT * FROM Supplies", out suppliesDesc, out suppliesPces);
        }
        
        
    }
}
