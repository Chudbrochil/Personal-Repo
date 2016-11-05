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

    public int GetItemCount(string name)
    {
        if (_items.ContainsKey(name))
        {
            return _items[name];
        }
        return 0;
    }

}
