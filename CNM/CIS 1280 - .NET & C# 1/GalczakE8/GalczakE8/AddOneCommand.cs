using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GalczakE8
{
    class AddOneCommand : IInventoryCommand
    {
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
        public AddOneCommand(List<InventoryItem> tarList, InventoryItem itm)
        {
            item = itm;
            targetList = tarList;
        }

        public void Do()
        {
            targetList.Add(item);
        }

        public void Undo()
        {
            targetList.Remove(item);
        }

    }
}
