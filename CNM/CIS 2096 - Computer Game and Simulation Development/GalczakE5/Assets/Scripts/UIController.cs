using UnityEngine;
using UnityEngine.UI;
using System.Collections;

public class UIController : MonoBehaviour {

    [SerializeField]
    private Text scoreLabel;


    [SerializeField]
    private SettingsPopup settingsPopup;



	// Use this for initialization
	void Start () {
        settingsPopup.Close();
	}

    // Update is called once per frame
    void Update()
    {
        scoreLabel.text = Time.realtimeSinceStartup.ToString();
    }

    public void OnOpenSettings()
    {
        Debug.Log("Open settings.");
        settingsPopup.Open();
    }


}
