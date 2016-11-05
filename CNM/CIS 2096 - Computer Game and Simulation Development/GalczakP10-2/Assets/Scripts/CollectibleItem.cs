using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class CollectibleItem : MonoBehaviour {

    [SerializeField]
    private string itemName;

    [SerializeField]
    private AudioClip itemPick;

    [SerializeField]
    private Text notification;

    // When colliding with an item add it to inventory and play a sound effect
    void OnTriggerEnter(Collider other)
    {
        Managers.Inventory.AddItem(name);
        AudioSource.PlayClipAtPoint(itemPick, transform.position);

        Debug.Log("Collision debug1");
        if(notification != null)
        {
            StartCoroutine(ShowMessage("Pickup more bones to enter the Outside level!", 2f));
            
        }

        // Disabling the object so that it stops appearing without being destroyed
        //this.gameObject.SetActive(false);
    }

    // Send a message to the player via a warning notification on a delay
    IEnumerator ShowMessage(string msg, float delay)
    {
        notification.text = msg;
        notification.enabled = true;


        yield return new WaitForSeconds(delay);

        //this.gameObject.SetActive(true);
        Debug.Log("Delay successful");
        notification.enabled = false;

        // Destroying the object after the coroutine
        Destroy(this.gameObject);


    }



}
