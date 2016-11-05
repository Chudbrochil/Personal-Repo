using UnityEngine;
using System.Collections;
using System.Collections.Generic;

public static class PlayerInventory {

    private static GameObject fleas;

    private static List<GameObject> inventory = new List<GameObject>();

    public static void Pickup(GameObject g)
    {
        inventory.Add(g);
        g.SetActive(false);
        Debug.Log(getInventoryNames());
    }


    public static List<GameObject> getInventory()
    {
        return inventory;
    }

    static PlayerInventory()
    {
        inventory.Add(fleas);
    }

    public static string getInventoryNames()
    {
        // TODO - get this inventory to show up by name and then display it in the GUI or popup Menu
        string inventoryString = "";

        for(int i = 0; i < inventory.Count; ++i)
        {
            inventoryString += (inventory[i].gameObject.name + "\n");
        }

        return inventoryString;
    }






}
