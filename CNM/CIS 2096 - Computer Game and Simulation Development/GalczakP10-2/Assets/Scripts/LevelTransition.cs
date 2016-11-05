using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class LevelTransition : MonoBehaviour {

    [SerializeField]
    private Text notification;

    public void Activate()
    {
        // If activate is sent via a trigger then attempt to load the next level, this will check for enoughBones (5 bones)
        if(Application.loadedLevelName == "Tutorial")
        {
            if(Managers.Inventory.enoughBones() == true)
            {
                Application.LoadLevel("Outside");
            }
            else
            {
                StartCoroutine(ShowMessage("You must collect 5 bones first!", 3f));
            }
            
        }
        
        // If player has already made it to the outside then this will transition them back to the tutorial level
        else if(Application.loadedLevelName == "Outside")
        {
            Application.LoadLevel("Tutorial");
        }
        
    }

    // This is unused
    public void Deactivate()
    {
        
    }

    // IEnumerator for sending a message to the player
    IEnumerator ShowMessage(string msg, float delay)
    {
        notification.text = msg;
        notification.enabled = true;
        yield return new WaitForSeconds(delay);
        notification.enabled = false;
    }
}
