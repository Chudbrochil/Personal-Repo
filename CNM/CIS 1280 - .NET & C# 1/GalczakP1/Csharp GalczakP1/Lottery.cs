using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Csharp_GalczakP1
{
    class Lottery
    {
        static void Main(string[] args)
        {
            // Program header telling user my name, program title and program objective
            Console.WriteLine("Anthony Galczak\nProgram 1\nThis program will play a lottery game.\n\n");

            // Declaring variables
            string strLott1, strLott2, strLott3;
            int lott1, lott2, lott3, rand1, rand2, rand3;
            int[] lott = new int[3];
            int[] rand = new int[3];
            
            // Starting a do while loop in case user wants to play again
            string lpProg = "y";
            do
            {
            // Asking user for their lottery numbers
                Console.WriteLine("What is your first lottery number?");
                strLott1 = Console.ReadLine();
                Console.WriteLine("What is your second lottery number?");
                strLott2 = Console.ReadLine();
                Console.WriteLine("What is your third lottery number?");
                strLott3 = Console.ReadLine();

            // Casting strings to ints
                lott1 = Convert.ToInt32(strLott1);
                lott2 = Convert.ToInt32(strLott2);
                lott3 = Convert.ToInt32(strLott3);

            // Creating random numbers, 1, 5 makes random numbers between 1 and 4
                Random r = new Random();
                rand1 = r.Next(1, 5);
                rand2 = r.Next(1, 5);
                rand3 = r.Next(1, 5);

            // Letting user know what the random numbers are
                Console.WriteLine("The lottery numbers are:\n");
                Console.WriteLine("{0} {1} {2}", rand1, rand2, rand3);

            // Putting the user's lottery numbers and the random numbers into an array 
                lott[0] = lott1;
                lott[1] = lott2;
                lott[2] = lott3;
                rand[0] = rand1;
                rand[1] = rand2;
                rand[2] = rand3;

                // Declaring score variable
                int score = 0;

                // Figuring out how many matches there are. This applies to 1 and 2 matches
                for(int i =0 ; i < 3; ++i)
                {
                    for(int j = 0; j < 3; ++j)
                    {
                        // Calculates if lott[0] == rand[0] and then if lott[1] == rand[0] and so on until lott[2] == rand[2]
                        if(lott[j] == rand[i])
                        {
                            score += 1;
                        // Nulling out the values for the ones that match so they cannot match with new analysis
                            lott[j] = 0;
                            rand[i] = 0;
                        }
                    }
                }

                // Sorting arrays to find out if 3 overall matching numbers
                Array.Sort(lott);
                Array.Sort(rand);
                
                // Declaring winnings amount through if statements
                if (score == 0)
                {
                    Console.WriteLine("Sorry, you aren't a winner!\n");
                }
                // One match
                else if (score == 1)
                {
                    Console.WriteLine("One matching number!\nYou have won 10$!\n");
                }
                // All 3 digits matching in order, this catches it as variables so the sort is irrelevant
                else if (lott1 == rand1 && lott2 == rand2 && lott3 == rand3)
                {
                    Console.WriteLine("Three in-order matching numbers\nYou have won 10000$!\nJACKPOT!!!\nYou didn't cheat did you?\n");
                }
                // Sorted arrays matching, 3 matching numbers. This works because it's comparing elements of the array after the sort
                else if (lott[0] == rand[0] && lott[1] == rand[1] && lott[2] == rand[2])
                {
                    Console.WriteLine("Three matching numbers!\nYou have won 1000$!\n");
                }
                // This serves as a catchall in case score is greater than 1, but works perfectly because anything that has
                // 3 matches is captured before this point
                else if (score > 1)
                {
                    Console.WriteLine("Two matching numbers!\nYou have won 100$\n");
                }

            // Do while loop to find out if user wants to play again
                Console.WriteLine("Would you like to buy another lottery ticket?");
                lpProg = Console.ReadLine();

            } while (lpProg == "y");

            // Displaying good bye message
            Console.WriteLine("Thanks for using my lottery program! Good Bye!");
        }
    }
}