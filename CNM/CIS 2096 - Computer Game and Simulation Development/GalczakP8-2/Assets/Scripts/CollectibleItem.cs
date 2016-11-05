using UnityEngine;
using System.Collections;

public class CollectibleItem : MonoBehaviour {

    [SerializeField]
    private string itemName;

    void OnTriggerEnter(Collider other)
    {
        Managers.Inventory.AddItem(name);
        //Debug.Log("Item Collected: " + itemName);
        Destroy(this.gameObject);
    }



}
