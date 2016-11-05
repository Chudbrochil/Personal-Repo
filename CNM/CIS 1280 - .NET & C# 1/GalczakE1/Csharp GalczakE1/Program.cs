using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GalczakE1
{
    class Program
    {
        static void Main(string[] args)
        {
            // Program Header telling user my name, program title and program objective
            Console.WriteLine("Anthony Galczak\n Exercise 1 Burma Shave\n This program will printout a Burma-Shave rhyme based on what option the user selects.\n\n");

            // Starting a do while loop in case user wants to pick another rhyme
            string lpProg = "y";
            do
            {
                // Having user select which Burma-Shave rhyme they want to see
                Console.WriteLine("Please select a Burma-Shave rhyme:\n 1. 1942 Rhyme\n 2. 1948 Rhyme\n 3. 1932 Rhyme\n\n");
                int choice = Convert.ToInt16(Console.ReadLine());

                // If statement for selecting the user's rhyme
                if (choice == 1)
                {
                    Console.WriteLine("Iceman's grandson\nNow full grown\nHas cooling system\nAll his own\nHe uses \nBurma-Shave\n");
                }
                if (choice == 2)
                {
                    Console.WriteLine("Other things have\nGone sky high\nHalf a dollar\nStill will buy\nHalf pound jar\nBurma-Shave\n");
                }
                if (choice == 3)
                {
                    Console.WriteLine("From New York town\nTo Pumpkin Holler\nIt's half a pound\nFor\nHalf a dollar\nBurma-Shave\n");
                }

                // Do While Loop to find out if user wants to go again
                Console.WriteLine("Go again? (y/n)");
                lpProg = Console.ReadLine();

            } while (lpProg == "y");

            // Displaying good bye message
            Console.WriteLine("Thanks for using my Burma-Shave Rhyme program! Good Bye!");
        }
    }
}