using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Csharp_GalczakE3
{
    class ComputerOrder
    {
        // Field Declarations, initializing indices to 1 to avoid out of bounds error
        private int mboardOptionIndex = 1;
        private static string[] mboardOptions = { "I3 + Mobo", "I5 + Mobo", "I7 + Mobo" };
        private static double[] mboardPrice = { 300, 500, 700 };
        private int memoryOptionIndex = 1;
        private static string[] memoryOptions = { "8GB - slow", "8GB - fast", "16GB - fast" };
        private static double[] memoryPrice = { 100, 150, 250 };
        private int driveOptionIndex = 1;
        private static string[] driveOptions = { "1TB", "2TB", "1TB + 512GB SSD" };
        private static double[] drivePrice = { 100, 150, 500 };
        private List<int> periphList;
        private static string[] periphOptions = {"Dell 27 Monitor", "Logitech Gaming Mouse", "Logitech Gaming Keyboard",
               "Bose Headphones", "Blu-Ray Burner", "Bob's Mouse", "Bob's Keyboard", "Bob's Monitor"};
        private static double[] periphPrice = { 500, 80, 150, 300, 50, 20, 20, 75 };
        private double totalPrice;

        // Variable for calculating actual cost of peripherals
        private double totalPeriphCost = 0;

        // Constructors
        public ComputerOrder()
            : this(1, 1, 1, new List<int>())
        { }

        // Overloaded constructor
        public ComputerOrder(int mboardOptionIndex, int memoryOptionIndex, int driveOptionIndex)
            : this(1, 1, 1, new List<int>())
        { }

        // Even more overloaded constructor with initialization for variables from other 2 linked constructors
        public ComputerOrder(int mboardOptionIndex, int memoryOptionIndex, int driveOptionIndex, List<int> periphList)
        {
            this.mboardOptionIndex = mboardOptionIndex;
            this.memoryOptionIndex = memoryOptionIndex;
            this.DriveOptionIndex = driveOptionIndex;
            this.periphList = periphList;
        }

        // Method for calculating the price of the items
        private void Calculate()
        {
            // Calculating the price of mobo, memory and drive
            totalPrice = mboardPrice[mboardOptionIndex - 1] + memoryPrice[memoryOptionIndex - 1]
                + drivePrice[driveOptionIndex - 1] + totalPeriphCost;
        }

        // Method overriding ToString to output mobo, memory and drive
        public override string ToString()
        {
            return "Mobo: " + mboardOptions[mboardOptionIndex - 1] + "Memory: " + memoryOptions[memoryOptionIndex - 1] +
                 "Drive: " + driveOptions[driveOptionIndex - 1] + "\n";
        }

        // Properties
        public string DriveMenu
        {
            get
            {
                // Beginning of menu outputted to user
                string menu = "Drive Menu:\nEnter a number\n";
                // Outputting each element of drive options and then drive price corresponding
                for (int i = 0; i < driveOptions.Length; i++)
                {
                    menu += (i + 1).ToString() + ". " + driveOptions[i] + " " + drivePrice[i] + "\n";
                }
                return menu;
            }
        }

        public int DriveOptionIndex
        {
            get { return driveOptionIndex; }
            set {
                    // This variable needs to be set in the value first before the exception handling can begin
                    driveOptionIndex = value;
                    if(driveOptionIndex > 0 && driveOptionIndex <= driveOptions.Length)
                    {
                        Calculate();
                    }
                    else
                    {
                        throw new ItemNotAvailableException();
                    }
                }
        }

        public string MboardMenu
        {
            get
            {
                string menu = "Motherboard Menu:\nEnter a number\n";
                for (int i = 0; i < mboardOptions.Length; i++)
                {
                    // Outputting each element of mobo options and then mobo price corresponding
                    menu += (i + 1).ToString() + ". " + mboardOptions[i] + " " + mboardPrice[i] + "\n";
                }
                return menu;
            }
        }

        public int MboardOptionIndex
        {
            get { return mboardOptionIndex; }
            set
            {
                mboardOptionIndex = value;
                if (mboardOptionIndex > 0 && mboardOptionIndex <= mboardOptions.Length)
                {
                    Calculate();
                }
                else
                {
                    throw new ItemNotAvailableException();
                }
            }
        }

        public string MemoryMenu
        {
            get
            {
                string menu = "Memory Menu:\nEnter a number\n";
                for (int i = 0; i < memoryOptions.Length; i++)
                {
                    // Outputting each element of memory options and then memory price corresponding
                    menu += (i + 1).ToString() + ". " + memoryOptions[i] + " " + memoryPrice[i] + "\n";
                }
                return menu;
            }
        }

        public int MemoryOptionIndex
        {
            get { return memoryOptionIndex; }
            set
            {
                memoryOptionIndex = value;
                if (memoryOptionIndex > 0 && memoryOptionIndex <= memoryOptions.Length)
                {

                    Calculate();
                }
                else
                {
                    throw new ItemNotAvailableException();
                }
            }
        }

        public string OrderSummary
        {
            get
            {
                // Building a string to output all the user's data
                string order;

                order = "\nYour motherboard: " + mboardOptions[mboardOptionIndex - 1] + "\nCost is: " +
                    mboardPrice[mboardOptionIndex - 1] + "\nYour drive: " + driveOptions[driveOptionIndex - 1] +
                    "\nCost is: " + drivePrice[driveOptionIndex - 1] + "\nYour memory: " + memoryOptions[memoryOptionIndex - 1] +
                    "\nCost is: " + memoryPrice[memoryOptionIndex - 1] + "\n" + "Your peripherals:\n";

                // Adding peripherals names into output
                foreach (int item in periphList)
                {
                    order += periphOptions[item - 1] + "\n";
                }

                // Adding total price into output
                order += "Cost of Peripherals: " + totalPeriphCost + "\nTotal price is: " + totalPrice;

                // Outputting said string by value
                return order;
            }
        }

        public string PeripheralsMenu
        {
            get
            {
                string menu = "Peripherals Menu:\nEnter a number\n";
                for (int i = 0; i < periphOptions.Length; i++)
                {
                    // Outputting each element of periph options and then periph price corresponding
                    menu += (i + 1).ToString() + ". " + periphOptions[i] + " " + periphPrice[i] + "\n";
                }
                return menu;
            }
        }

        public List<int> PeriphList
        {
            get { return periphList; }
            set
            {
                periphList = value;

                // Counting through the list items in periphList
                for (int i = 0; i < periphList.Count; i++)
                {
                    // If the first(2nd, 3rd, etc.) item in periphlist is less than or equal to 0 or more than the length of periph options then throw an exception
                    if (periphList[i] <= 0 || periphList[i] > periphOptions.Length)
                    {
                        throw new ItemNotAvailableException();
                    }
                }

                // Adding prices of peripherals to totalPeriphCost for future use in total price
                foreach (int item in periphList)
                {
                    totalPeriphCost += periphPrice[item - 1];
                }

                Calculate();

            }
        }

    }
}
