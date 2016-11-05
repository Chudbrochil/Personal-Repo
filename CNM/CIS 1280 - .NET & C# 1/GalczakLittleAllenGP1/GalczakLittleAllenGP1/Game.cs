using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GalczakLittleAllenGP1
{
    // Anthony's territory
    public class Game
    {

        private List<GameLocation> map = new List<GameLocation>();

        public List<GameLocation> Map
        {
            get { return map; }
            set { map = value; }
        }

        private GameLocation playerLocation;

        public GameLocation PlayerLocation
        {
            get { return playerLocation; }
            set { playerLocation = value; }
        }

        public Game()
        {
            Map = new List<GameLocation>();

            
            // Gamelocation 0
            Map.Add(new GameLocation("You are heading towards a creepy forest"));

            // Gamelocation 1
            Map.Add(new GameLocation("You are now in a spooky forest."));

            // Gamelocation 2
            Map.Add(new GameLocation("You are next to a lovely spring"));

            // Gamelocation 3
            Map.Add(new GameLocation("The old man is a great warrior. He kills you with a giant axe."));

            // Gamelocation 4
            Map.Add(new GameLocation("You approach a beautiful woman holding a frosty beverage."));

            // Gamelocation 5
            Map.Add(new GameLocation("The water is pure poison. You die a slow death."));

            // Gamelocation 6
            Map.Add(new GameLocation("You are delirious from thirst. You die a slow death."));

            // Gamelocation 7
            Map.Add(new GameLocation("You won the game!"));

            // Adding all of the travel options to each gamelocation
            Map[0].TravelOptions.Add(new TravelOption(Map[1], "A creepy forest is in front of you"));
            Map[1].TravelOptions.Add(new TravelOption(Map[2], "A serene spring is to your west."));
            Map[1].TravelOptions.Add(new TravelOption(Map[3], "A sickly old man lies in the distance to your east."));
            Map[1].TravelOptions.Add(new TravelOption(Map[4], "To your north, you see a beautiful woman holding a frosty beverage."));
            Map[2].TravelOptions.Add(new TravelOption(Map[5], "Drink the water.")); // water kills them
            Map[2].TravelOptions.Add(new TravelOption(Map[6], "Don't drink the water.")); // no water kills them
            Map[4].TravelOptions.Add(new TravelOption(Map[7], "Retreat into paradise with your beautiful lass. Congratulations!"));

            // Initializing player location
            PlayerLocation = Map[0];
        }
        
        
    }
}
