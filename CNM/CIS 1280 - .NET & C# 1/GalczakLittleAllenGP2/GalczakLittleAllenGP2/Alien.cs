using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GalczakLittleAllenGP2
{
    public class Alien : GameObject
    {
        double moveProbability;
        Random rand;

        public int Health { get; set; }

        public MapLocation Location { get; set; }

        public IWeapon Weapon { get; set; }

        public Alien(MapLocation location)
        {
            rand = new Random();

            Location = location;
            //description is needed

            //give alien a weapon and health move prob
            Weapon = new AlienGauntlet();
            Health = 100;
            moveProbability = rand.NextDouble();
        }

        public void Update()
        {
            rand = new Random();
           
            if(moveProbability < .2)
            {
                //Removes the alien and move it to another part of the map
                 Location.Items.Remove(this);
                 Location = (Location.TravelOptions[rand.Next(Location.TravelOptions.Count)].Location);
                 Location.Items.Add(this);
            }
        }
    }
}