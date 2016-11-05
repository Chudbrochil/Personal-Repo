using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Csharp_GalczakE2
{
    class TestComputerOrder
    {
        static void Main(string[] args)
        {
            // Variable Declaration
            int driveChoice = 0, moboChoice = 0, memoryChoice = 0; // Indices for user input
            string driveOutput, moboOutput, memoryOutput, periphOutput, driveTemp, moboTemp, 
                memoryTemp, summary;
            
            // Program header telling user my name, program title and program objective
            Console.WriteLine("Anthony Galczak\nExercise 2\nThis program will help you spec out a computer using properties and overloaded constructors.\n\n");
            
            // Starting a do-while loop
            string lpProg = "y";
            do
            {
                // Instantiating ComputerOrder Class
                ComputerOrder comp = new ComputerOrder();

                // Assigning menu gets
                driveOutput = comp.DriveMenu;
                moboOutput = comp.MboardMenu;
                memoryOutput = comp.MemoryMenu;
                periphOutput = comp.PeripheralsMenu;

                // Outputting drive menu and getting drive index
                Console.WriteLine(driveOutput);
                driveTemp = Console.ReadLine();
                // Converting string from readline into a usable index int
                driveChoice = Convert.ToInt16(driveTemp);
                comp.DriveOptionIndex = driveChoice;

                // Outputting mobo menu and getting mobo index
                Console.WriteLine(moboOutput);
                moboTemp = Console.ReadLine();
                // Converting string from readline into a usable index int
                moboChoice = Convert.ToInt16(moboTemp);
                comp.MboardOptionIndex = moboChoice;

                // Outputting memory menu and getting memory index
                Console.WriteLine(memoryOutput);
                memoryTemp = Console.ReadLine();
                // Converting string from readline into a usable index int
                memoryChoice = Convert.ToInt16(memoryTemp);
                comp.MemoryOptionIndex = memoryChoice;

                // Capturing peripheral information from user
                int periphInt = 0;
                string periphStr;
                List<int> tempPeriphList = new List<int>();
                do
                {
                    // Outputting peripherals menu
                    Console.WriteLine(periphOutput + "\nPlease enter your number for a peripheral, choose however many you want.\n"
                    + "When you are done enter -1");
                    // Capturing user's input to populate it into the periphlist
                    periphStr = Console.ReadLine();
                    periphInt = Convert.ToInt16(periphStr);
                    
                    // Adding the int into the periphlist, if statement so that -1 isn't added to the stack
                    if (periphInt != -1)
                    {
                        tempPeriphList.Add(periphInt);
                    }

                // Sentinel is set to -1 for user to stop the peripheral loop                    
                } while (periphInt != -1);

                // Setting the peripheral list as the list I just got from the do while loop
                comp.PeriphList = tempPeriphList;

                // Printing an order summary to the user
                summary = comp.OrderSummary;
                Console.WriteLine(summary);

                // Asking user if they would like to build another PC
                Console.WriteLine("Would you like to build another PC?\n");
                lpProg = Console.ReadLine();

            } while (lpProg == "y");

            Console.WriteLine("Thanks for using my computer building program! Good Bye!");
        }
    }
}