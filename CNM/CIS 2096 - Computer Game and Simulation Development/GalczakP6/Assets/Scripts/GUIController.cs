using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class GUIController : MonoBehaviour {

    [SerializeField]
    private PopupController popup;




	// Use this for initialization
	void Start () {
        popup.Close();
	}
	
	// Update is called once per frame
	void Update () {
	    
	}

    public void OnSettingsButtonClick()
    {
        popup.Open();
    }


}
