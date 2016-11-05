using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Csharp_GalczakE3
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
            Console.WriteLine("Anthony Galczak\nExercise 3\nThis program will help you spec out a computer using properties and overloaded constructors as well as adding the functionality of exception handling.\n\n");

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

                // Variable for exception handling trying again
                bool tryAgain = false;

                // Do loop to ask for input again if the user input isn't an acceptable value
                do
                {
                    try
                    {
                        // Outputting drive menu and getting drive index
                        Console.WriteLine(driveOutput);
                        driveTemp = Console.ReadLine();
                        // Converting string from readline into a usable index int
                        driveChoice = Convert.ToInt16(driveTemp);
                        comp.DriveOptionIndex = driveChoice;

                        // If the user gets an error and comes back to selecting another option successfully, the do while loop must be stopped by making tryAgain false
                        tryAgain = false;
                    }
                    catch (ItemNotAvailableException e)
                    {
                        Console.WriteLine("Item not available!\n{0}\nPlease try again!", e.Message);
                        tryAgain = true;
                    }
                    catch (FormatException e)
                    {
                        Console.WriteLine("Incorrect format!\n{0}\nPlease try again!", e.Message);
                        tryAgain = true;
                    }
                    catch (OverflowException e)
                    {
                        Console.WriteLine("Overflow!\n{0}\nPlease try again!", e.Message);
                        tryAgain = true;
                    }
                    catch (Exception e)
                    {
                        Console.WriteLine("General Error!\n{0}\nPlease try again!", e.Message);
                        tryAgain = true;
                    }
                } while (tryAgain == true);

                // Do loop to ask for input again if the user input isn't an acceptable value
                do
                {
                    try
                    {
                        // Outputting mobo menu and getting mobo index
                        Console.WriteLine(moboOutput);
                        moboTemp = Console.ReadLine();
                        // Converting string from readline into a usable index int
                        moboChoice = Convert.ToInt16(moboTemp);
                        comp.MboardOptionIndex = moboChoice;
                        tryAgain = false;
                    }
                    catch (ItemNotAvailableException e)
                    {
                        Console.WriteLine("Item not available!\n{0}\nPlease try again!", e.Message);
                        tryAgain = true;
                    }
                    catch (FormatException e)
                    {
                        Console.WriteLine("Incorrect format!\n{0}\nPlease try again!", e.Message);
                        tryAgain = true;
                    }
                    catch (OverflowException e)
                    {
                        Console.WriteLine("Overflow!\n{0}\nPlease try again!", e.Message);
                        tryAgain = true;
                    }
                    catch (Exception e)
                    {
                        Console.WriteLine("General Error!\n{0}\nPlease try again!", e.Message);
                        tryAgain = true;
                    }
                } while (tryAgain == true);

                // Do loop to ask for input again if the user input isn't an acceptable value
                do
                {
                    try
                    {
                        // Outputting memory menu and getting memory index
                        Console.WriteLine(memoryOutput);
                        memoryTemp = Console.ReadLine();
                        // Converting string from readline into a usable index int
                        memoryChoice = Convert.ToInt16(memoryTemp);
                        comp.MemoryOptionIndex = memoryChoice;
                        tryAgain = false;
                    }
                    catch (ItemNotAvailableException e)
                    {
                        Console.WriteLine("Item not available!\n{0}\nPlease try again!", e.Message);
                        tryAgain = true;
                    }
                    catch (FormatException e)
                    {
                        Console.WriteLine("Incorrect format!\n{0}\nPlease try again!", e.Message);
                        tryAgain = true;
                    }
                    catch (OverflowException e)
                    {
                        Console.WriteLine("Overflow!\n{0}\nPlease try again!", e.Message);
                        tryAgain = true;
                    }
                    catch (Exception e)
                    {
                        Console.WriteLine("General Error!\n{0}\nPlease try again!", e.Message);
                        tryAgain = true;
                    }
                } while (tryAgain == true); 



                // Capturing peripheral information from user
                int periphInt = 0;
                string periphStr;
                List<int> tempPeriphList = new List<int>();

                // Do loop if user wants to stop selecting peripherals by typing "-1"
                do
                {
                    // Do loop to ask for input again if the user input isn't an acceptable value
                    do
                    {
                        try
                        {
                            // Outputting peripherals menu
                            Console.WriteLine(periphOutput + "\nPlease enter your number for a peripheral, choose however many you want.\n"
                            + "When you are done enter -1");
                            // Capturing user's input to populate it into the periphlist
                            periphStr = Console.ReadLine();
                            periphInt = Convert.ToInt16(periphStr);

                            // Throwing a new custom exception if user enters incorrect data
                            if (periphInt > 8 || periphInt < 1 && periphInt != -1)
                            {
                                throw new ItemNotAvailableException("This is not an available item");
                            }

                            // Adding the int into the periphlist, if statement so that -1 isn't added to the stack
                            // Also capturing numbers that are not within the 1-8 list
                            if (periphInt != -1 && periphInt < 9 && periphInt > 0)
                            {
                                tempPeriphList.Add(periphInt);
                            }

                            tryAgain = false;
                        }
                        catch (FormatException e)
                        {
                            Console.WriteLine("Incorrect format!\n{0}\nPlease try again!", e.Message);
                            tryAgain = true;
                        }
                        catch (OverflowException e)
                        {
                            Console.WriteLine("Overflow!\n{0}\nPlease try again!", e.Message);
                            tryAgain = true;
                        }
                        catch (Exception e)
                        {
                            Console.WriteLine("General Error!\n{0}\nPlease try again!", e.Message);
                            tryAgain = true;
                        }

                    } while (tryAgain == true); 

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
