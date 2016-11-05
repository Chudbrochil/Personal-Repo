using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GalczakE8
{
    class AddMultipleCommand : IInventoryCommand
    {
        private int number;
        
        private InventoryItem item;

        public InventoryItem Item
        {
            get { return item; }
            set { item = value; }
        }

        // Private list that holds the added inventory items
        private List<InventoryItem> targetList;

        public List<InventoryItem> TargetList
        {
            get { return targetList; }
            set { targetList = value; }
        }

        // Constructor
        public AddMultipleCommand(List<InventoryItem> tarList, InventoryItem itm)
        {
            targetList = tarList;
            item = itm;
        }

        // Adding a random amount of inventoryitems to the targetlist
        public void Do()
        {
            Random rnd = new Random();
            number = rnd.Next(1, 6);

            for(int i = 0; i < number; ++i)
            {
                targetList.Add(item);
            }
        }
        
        // Undoing adding the inventoryitems to the inventory list
        public void Undo()
        {
            for(int i = 0; i < number; ++i)
            {
                targetList.Remove(item);
            }
        }

    }
}
