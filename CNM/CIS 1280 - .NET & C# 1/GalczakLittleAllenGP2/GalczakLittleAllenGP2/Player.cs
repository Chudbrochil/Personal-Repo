using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GalczakLittleAllenGP2
{
    public class Player
    {
        int inventorySize;
        public int Health { get; set; }

        public List<IPortable> Inventory { get; set; }

        public MapLocation Location { get; set; }

        public int MaxHealth { get; set; }

        public int MaxInventory { get; set; }

        public IWeapon Weapon { get; set; }

        public bool AddInventoryItem(IPortable itm)
        {
            //Calc();
            // Checking if there is space in the inventory
            if(inventorySize >= MaxInventory)
            {
                return false;
            }
            else
            {
                Inventory.Add(itm);
                return true;
            }

        }

        public void Calc()
        {
            // Getting the size of the inventory
            inventorySize = Inventory.Count;
        }

        public Player(MapLocation location)
        {
            // Initializing class variables/properties
            Health = 200;
            Weapon = new Spear();
            MaxInventory = 10;
            Location = location;
        }

        public void RemoveInventoryItem(IPortable item)
        {
            Inventory.Remove(item);
            Calc();
        }

        public void Update()
        {
            // Generating a number between 5 and 20 and adding this amount of health to the player
            Random rand = new Random();
            int healthGained = rand.Next(5, 20);
            Health += healthGained;
        }

    }
}