using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class GUIController : MonoBehaviour {

    [SerializeField]
    private PopupController popup;

	// Start with the GUI closed
	void Start () {
        popup.Close();
	}
	
    // Open the popup upon button press (ESC)
    public void OnSettingsButtonClick()
    {
        popup.Open();
    }

}
