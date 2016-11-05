using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GalczakLittleAllenGP2
{
    class Map
    {
        private List<MapLocation> gameMap;

        public List<MapLocation> GameMap
        {
            get { return gameMap; }
            set { gameMap = value; }
        }
        
        public List<MapLocation> Locations { get; set; }

        public Map()
        {
            GameMap = new List<MapLocation>();

            // MapLocation 0
            GameMap.Add(new MapLocation("You stand at the far end of main street, the whole town seems deserted."));
            
            // MapLocation 1
            GameMap.Add(new MapLocation("The saloon is eerily quiet. A soft sizzling sound draws your attention to the barkeep who is slouched face-down over the counter, thick plumes of smoke rise lazily from his back."));

            // MapLocation 2
            GameMap.Add(new MapLocation("The bedroom upstairs reeks of liquor and sweat. A four post bed takes up most of the room. Shards of glass from a broken lamp litter the floor."));

            // MapLocation 3
            GameMap.Add(new MapLocation("The train station has seen better days, the ticket booth is nothing more than a charred husk. Most of the seats are either overturned or broken into several pieces, half burned papers flutter around the room. The train itself looks to have melted onto the tracks, clearly the result of some sort of blast of energy that nearly took out the whole middle car."));

            // MapLocation 4
            GameMap.Add(new MapLocation("A few tumbleweeds roll past the usually bustling street. The few horses that are tied up are either half disintegrated or just plain old fashioned shot dead."));

            // MapLocation 5
            GameMap.Add(new MapLocation("The door leading to the jail cell has been blown apart, as has the sheriff's desk next to it. The jail cell itself has become a hardened mound of smoldering iron."));

            // MapLocation 6
            GameMap.Add(new MapLocation("It looks like someone cleared out the general store from top to bottom, all that's left on the shelves are a few odd items."));

            // MapLocation 7
            GameMap.Add(new MapLocation("You've reached the far edge of town, a sign pointing north reads \"turn back, not safe! creatures have taken over!\""));

            //MapLocation 8
            GameMap.Add(new MapLocation("It looks like someone tried to make a stand at the wagon. Large holes in the canvas reveal a few charred remains that suggest it didn't go so well... Aside from a half burned wagon wheel, the wagon itself managed to stay suprisingly intact."));

            //MapLocation 9
            GameMap.Add(new MapLocation("The door creaks open, revealing a strong scent of manure. Strangely, all of the cows are gone."));

            // Adding all of the travel options to each MapLocation

            //map 0 travel options
            GameMap[0].TravelOptions.Add(new TravelOption(GameMap[1], "A saloon stands to the west"));
            GameMap[0].TravelOptions.Add(new TravelOption(GameMap[3], "A train station stands to the east"));
            GameMap[0].TravelOptions.Add(new TravelOption(GameMap[4], "Main street continues to the north"));
            //map 1 travel options
            GameMap[1].TravelOptions.Add(new TravelOption(GameMap[2], "Stairs lead to a room above the bar"));
            GameMap[1].TravelOptions.Add(new TravelOption(GameMap[0], "Batwing doors swing open to reveal main street"));
            //map 2 travel options
            GameMap[2].TravelOptions.Add(new TravelOption(GameMap[1], "The bedroom door leads back downstairs to the saloon"));
            //map 3 travel options
            GameMap[3].TravelOptions.Add(new TravelOption(GameMap[0], "The half melted entryway leads back to main street"));
            //map 4 travel options
            GameMap[4].TravelOptions.Add(new TravelOption(GameMap[0], "To the south lies the entrance to town"));
            GameMap[4].TravelOptions.Add(new TravelOption(GameMap[5], "The Sheriff's office is to the west"));
            GameMap[4].TravelOptions.Add(new TravelOption(GameMap[6], "A general store is to the east"));
            GameMap[4].TravelOptions.Add(new TravelOption(GameMap[7], "Main street continues to the north"));
            //map 5 travel options
            GameMap[5].TravelOptions.Add(new TravelOption(GameMap[4], "What's left of the front entrance leads east back to main street"));
            //map 6 travel options
            GameMap[6].TravelOptions.Add(new TravelOption(GameMap[4], "The only door leads west back to main street"));
            //map 7 travel options
            GameMap[7].TravelOptions.Add(new TravelOption(GameMap[4], "The road south leads back to the center of town"));
            GameMap[7].TravelOptions.Add(new TravelOption(GameMap[8], "To the west is a covered wagon and a makeshift campsight"));
            GameMap[7].TravelOptions.Add(new TravelOption(GameMap[9], "A Barn is to the east"));
            //map 8 travel options
            GameMap[8].TravelOptions.Add(new TravelOption(GameMap[7], "The end of town lies to the east"));
            //map 9 travel options
            GameMap[9].TravelOptions.Add(new TravelOption(GameMap[7], "The door to the barn leads back to the edge of town"));

            //GameMap[0].TravelOptions.Add(new TravelOption(GameMap[1], "A creepy forest is in front of you"));
            //GameMap[1].TravelOptions.Add(new TravelOption(GameMap[2], "A serene spring is to your west."));
            //GameMap[1].TravelOptions.Add(new TravelOption(GameMap[3], "A sickly old man lies in the distance to your east."));
            //GameMap[1].TravelOptions.Add(new TravelOption(GameMap[4], "To your north, you see a beautiful woman holding a frosty beverage."));
            //GameMap[2].TravelOptions.Add(new TravelOption(GameMap[5], "Drink the water.")); // water kills them
            //GameMap[2].TravelOptions.Add(new TravelOption(GameMap[6], "Don't drink the water.")); // no water kills them
            //GameMap[4].TravelOptions.Add(new TravelOption(GameMap[7], "Retreat into paradise with your beautiful lass. Congratulations!"));

            // Initializing player location
            //PlayerLocation = GameMap[0];
        }
    }
}