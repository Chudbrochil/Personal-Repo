using UnityEngine;
using System.Collections;
using System.Collections.Generic;

public class InventoryManager : MonoBehaviour, IGameManager {

    public ManagerStatus status { get; private set; }

    private Dictionary<string, int> _items;

    public void Startup()
    {
        Debug.Log("Inventory manager starting...");

        _items = new Dictionary<string, int>();

        status = ManagerStatus.Started;

        // Adding fleas so that the inventory isn't null upon level load other than main level
        _items.Add("Fleas", 1);
    }

    //public void deleteItem(string n)
    //{
    //    foreach(KeyValuePair<string, int> item in _items)
    //    {
    //        if (n == )
    //    }
    //}



    private void DisplayItems()
    {
        string itemDisplay = "Items: ";
        foreach (KeyValuePair<string, int> item in _items)
        {
            itemDisplay += item.Key + "(" + item.Value + ") ";
        }

        Debug.Log(itemDisplay);


    }

    // Adding an item to the dictionary
    public void AddItem(string name)
    {
        if (_items.ContainsKey(name))
        {
            _items[name] += 1;
        }
        else
        {
            _items[name] = 1;
        }

        DisplayItems();
    }


    public List<string> GetItemList()
    {
        List<string> list = new List<string>(_items.Keys);
        return list;
    }

    // Returns the count of an item via it's string (key)
    public int GetItemCount(string name)
    {
        if (_items.ContainsKey(name))
        {
            return _items[name];
        }
        return 0;
    }

    // Method to check if the player has 5 bones to advance to the next level
    public bool enoughBones()
    {
        bool fiveBones = false;

        if(_items["Bone"] > 4)
        {
            fiveBones = true;
        }

        return fiveBones;
    }

}
