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
        Debug.Log(inventory.ToString());
    }


    public static List<GameObject> getInventory()
    {
        return inventory;
    }

    static PlayerInventory()
    {
        inventory.Add(fleas);
    }






}
