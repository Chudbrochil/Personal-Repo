using UnityEngine;
using System.Collections;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class LoadLevel : MonoBehaviour
{

    /// <summary>
    /// The gameobject to use as the loading screen
    /// </summary>
    /// <remarks>Requires a Text and Slider GUI element</remarks>
    public GameObject loadingScreen;

    /// <summary>
    /// Slider to show the progress loading the level
    /// </summary>
    /// <remarks>Works best with an inactive handle</remarks>
    public Slider progressBar;

    /// <summary>
    /// Text to show the exact value loaded
    /// </summary>
    public Text progressLabel;

    private AsyncOperation async;   //Load level in the background

    private bool loading = false;   //If the level is loading

    /// <summary>
    /// Load a level.
    /// </summary>
    /// <param name="name">The name of the scene to load</param>
    public void Load(string name)
    {
        loading = true;
        loadingScreen.SetActive(true);
        async = SceneManager.LoadSceneAsync(name);
    }

    void Update()
    {
        if(loading)
        {
            progressBar.value = async.progress;                             //Set progress value to equal the loading progress
            progressLabel.text = "Progress: " + async.progress * 100 + "%"; //Set the text to show the progress
        }
    }
}
