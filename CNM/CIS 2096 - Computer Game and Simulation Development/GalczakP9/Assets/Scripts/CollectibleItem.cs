using UnityEngine;
using System.Collections;

public class CollectibleItem : MonoBehaviour {

    [SerializeField]
    private string itemName;

    [SerializeField]
    private AudioClip itemPick;

    void OnTriggerEnter(Collider other)
    {
        Managers.Inventory.AddItem(name);
        AudioSource.PlayClipAtPoint(itemPick, transform.position);
        //Debug.Log("Item Collected: " + itemName);
        Destroy(this.gameObject);
    }



}
