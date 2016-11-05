using UnityEngine;
using System.Collections;

public class ItemPickup : MonoBehaviour,IActivatable {

    public Transform anItem;

    public GameObject anObject;

    public void Activate()
    {
        anItem = this.gameObject.transform;
        anObject = this.gameObject;
        
        // Do something before it's picked up possibly?
        //anItem.Rotate(0f, 40f, 0f);
        //Debug.Log("Rotated");

        PlayerInventory.Pickup(anObject);


    }
}
