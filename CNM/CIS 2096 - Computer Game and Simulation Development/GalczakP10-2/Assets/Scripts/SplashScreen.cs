using UnityEngine;
using System.Collections;

public class SplashScreen : MonoBehaviour {

	// Use this for initialization
	public void StartGame()
    {
        Application.LoadLevel("Tutorial");
    }

    public void QuitGame()
    {
        Application.Quit();
    }
}
