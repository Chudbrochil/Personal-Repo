using GalczakP6;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GalczakP6
{
    public class ComputerOrder
    {
        // Field Declarations, initializing indices to 1 to avoid out of bounds error
        // Prices gathered on tigerdirect.com 11-10-15
        private int moboOptionIndex = 0;
        private static string[] moboOptions = { "Gigabyte B85M-HD3 + i3", "Asus Z170M-Plus + i5-6600k", "Gigabyte Z97 + i7-4790k" };
        private static double[] moboPrice = { 189.99, 359.99, 484.99 };
        private int memoryOptionIndex = 0;
        private static string[] memoryOptions = { "Silicon Power 8GB", "Crucial Ballistic Sport XT 16GB", "Kingston Beast 32GB" };
        private static double[] memoryPrice = { 54.99, 89.99, 160.49 };
        private int driveOptionIndex = 0;
        private static string[] driveOptions = { "Western Digital Blue 1TB", "Seagate 2TB", "Samsung 1TB SSD" };
        private static double[] drivePrice = { 54.99, 111.99, 369.99 };
        private List<int> periphList;
        private static string[] periphOptions = {"Samsung SE590C 32\" Curved Monitor", "Logitech G700s Wireless Gaming Mouse", "Logitech G710+ Mechanical Gaming Keyboard",
               "Bose SoundLink Wireless Headphone II", "LG Super Multi Blue 16X Blu-Ray Burner", "Key Tronic Budget Laser Mouse", "Key Tronic Budget Keyboard", "HP LV2311 23\" LED Monitor"};
        private static double[] periphPrice = { 449.99, 89.99, 139.99, 279.95, 64.99, 10.99, 10.99, 99.99 };
        public double totalPrice;

        // Variable for calculating actual cost of peripherals
        private double totalPeriphCost = 0;

        // Constructors
        public ComputerOrder()
            : this(1, 1, 1, new List<int>())
        { }

        // Overloaded constructor
        public ComputerOrder(int moboOptionIndex, int memoryOptionIndex, int driveOptionIndex)
            : this(1, 1, 1, new List<int>())
        { }

        // Even more overloaded constructor with initialization for variables from other 2 linked constructors
        public ComputerOrder(int moboOptionIndex, int memoryOptionIndex, int driveOptionIndex, List<int> periphList)
        {
            this.moboOptionIndex = moboOptionIndex;
            this.memoryOptionIndex = memoryOptionIndex;
            this.driveOptionIndex = driveOptionIndex;
            this.periphList = periphList;
        }

        // Method for calculating the price of the items
        private void Calculate()
        {
            // Adding prices of peripherals to totalPeriphCost for future use in total price
            foreach (int item in periphList)
            {
                totalPeriphCost += periphPrice[item];
            }

            // Calculating the price of mobo, memory and drive
            totalPrice = moboPrice[moboOptionIndex] + memoryPrice[memoryOptionIndex]
                + drivePrice[driveOptionIndex] + totalPeriphCost;
        }

        // Properties
        public string[] DriveInfo
        {
            get
            {
                // Making a new array of drives and prices
                string[] drives = new string[driveOptions.Length];

                // Filling my drives string array with the drive name + the price for the form output
                for (int i = 0; i < driveOptions.Length; i++ )
                {
                    drives[i] = driveOptions[i] + " - " + drivePrice[i];
                }
                
                // Returning my array
                return drives;
            }
        }

        public string[] MoboInfo
        {
            get
            {
                // Making a new array of motherboards and prices
                string[] mobos = new string[moboOptions.Length];

                // Filling my mobo string array with the mobo name + the price for the form output
                for (int i = 0; i < moboOptions.Length; i++ )
                {
                    mobos[i] = moboOptions[i] + " - " + moboPrice[i];
                }

                // Returning a List of mobo's
                return mobos;
            }
        }

        public string[] MemoryInfo
        {
            get
            {
                // Making a new array of memory and prices
                string[] memory = new string[memoryOptions.Length];

                // Filling my memory string array with the memory name + the price for the form output
                for (int i = 0; i < memoryOptions.Length; i++)
                {
                    memory[i] = memoryOptions[i] + " - " + memoryPrice[i];
                }

                // Returning a List of Memory
                return memory;
            }
        }

        public string[] PeripheralsInfo
        {
            get
            {
                // Making a new array of peripherals and prices of peripherals
                string[] periphs = new string[periphOptions.Length];

                // Filling my periph string array with the periph name + the price for the form output
                for (int i = 0; i < periphOptions.Length; i++)
                {
                    periphs[i] = periphOptions[i] + " - " + periphPrice[i];
                }

                // Returning a List of periphs
                return periphs;
            }
        }

        public int MoboOptionIndex
        {
            get { return moboOptionIndex; }
            set
            {
                if (value > -1 && value <= (moboOptions.Length - 1))
                {
                    moboOptionIndex = value;
                    Calculate();
                }
                else
                {
                    throw new ItemNotAvailableException();
                }
            }
        }

        public int MemoryOptionIndex
        {
            get { return memoryOptionIndex; }
            set
            {
                if (value > -1 && value <= (memoryOptions.Length - 1))
                {
                    memoryOptionIndex = value;
                    Calculate();
                }
                else
                {
                    throw new ItemNotAvailableException();
                }
            }
        }

        public int DriveOptionIndex
        {
            get { return driveOptionIndex; }
            set
            {
                if (value > -1 && value <= (driveOptions.Length-1))
                {
                    driveOptionIndex = value;
                    Calculate();
                }
                else
                {
                    throw new ItemNotAvailableException();
                }

            }
        }

        public List<int> PeriphList
        {
            get { return periphList; }
            set
            {
                

                // Counting through the list items in periphList
                for (int i = 0; i < periphList.Count; i++)
                {
                    // If the first(2nd, 3rd, etc.) item in periphlist is less than or equal to 0 or more than the length of periph options then throw an exception
                    if (value[i] < 0 || value[i] > (periphOptions.Length - 1))
                    {
                        throw new ItemNotAvailableException();
                    }
                }
                periphList = value;

                Calculate();

            }
        }

        public string OrderSummary
        {
            get
            {
                // Building a string to output all the user's data
                string order;

                order = "Your motherboard: " + moboOptions[moboOptionIndex] + "\nCost is: $" +
                    moboPrice[moboOptionIndex] + "\nYour memory: " + memoryOptions[memoryOptionIndex] +
                    "\nCost is: $" + memoryPrice[memoryOptionIndex] + "\nYour drive: " + driveOptions[driveOptionIndex] +
                    "\nCost is: $" + drivePrice[driveOptionIndex] + "\n" + "Your peripherals:\n";

                // Adding peripherals names into output
                foreach (int item in periphList)
                {
                    order += periphOptions[item] + "\n";
                }

                // Adding total price into output
                order += "Cost of Peripherals: $" + totalPeriphCost + "\nTotal Computer price is: $" + totalPrice + "\n";

                // Outputting said string by value
                return order;
            }
        }

    }
}
