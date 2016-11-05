using UnityEngine;
using System.Collections.Generic;
using UnityEngine.UI;

public class MainMenuController : MonoBehaviour {

    [SerializeField]
    private GameObject titleScreen,
        playOptions,
        survivalOptions;

    [SerializeField]
    private Button startButton;

    /// <summary>
    /// List of possible scenes to load
    /// </summary>
    public List<string> nextScenes;

    private bool endless = true;

    void Start()
    {
        PlayerPrefs.SetInt("endless", 1);
        PlayerPrefs.SetInt("difficulty", 0);
    }

    /// <summary>
    /// Event handler for pressing the play button
    /// </summary>
	public void OnPlayGame()
    {
        titleScreen.SetActive(false);   //Hide the title screen
        playOptions.SetActive(true);    //Show options for playing the game
    }

    /// <summary>
    /// Event handler for pressing the quit button
    /// </summary>
    public void OnQuitGame()
    {
        #if UNITY_EDITOR                                    //Determine if the game is being played in the editor
        UnityEditor.EditorApplication.isPlaying = false;    //Stop the editor
        Debug.Log("Ended editor player");
        #else
        Application.Quit(); //Stop the .exe file
        #endif
    }

    /// <summary>
    /// Event handler for starting the game
    /// </summary>
    public void OnStartGame()
    {
        GetComponent<LoadLevel>().Load(nextScenes.Find(x => x == "Survival"));  //Load the survival scene
        playOptions.SetActive(false);                                           //Hide the play options screen
    }

    /// <summary>
    /// Event handler for toggling endless mode
    /// </summary>
    /// <param name="value">If endless mode is slected or not</param>
    public void OnEndlessToggle(bool value)
    {
        endless = value;
        int endlessValue;

        if (endless)
            endlessValue = 1;
        else
            endlessValue = 0;

        survivalOptions.SetActive(!endless);    //Show or hide options for survival mode
        startButton.interactable = endless;     //Enable or disable start button

        PlayerPrefs.SetInt("endless", endlessValue);
    }

    /// <summary>
    /// Event handler for changing the difficulty
    /// </summary>
    /// <param name="difficulty"></param>
    public void OnDifficultyChange(int difficulty)
    {
        PlayerPrefs.SetInt("difficulty", difficulty);
    }

    /// <summary>
    /// Event handler for setting the amount of waves the player would like to complete
    /// </summary>
    /// <param name="waves">The amount the player has input</param>
    public void OnWaveFinished(string waves)
    {
        PlayerPrefs.SetInt("waves", int.Parse(waves));
    }

    /// <summary>
    /// Determine if the player has input any text
    /// </summary>
    /// <param name="text">The input from the input field</param>
    public void OnWaveChanged(string text)
    {
        if(string.IsNullOrEmpty(text))
            startButton.interactable = false;
        else
            startButton.interactable = true;
    }

    /// <summary>
    /// Event handler for pressing the back button.
    /// </summary>
    public void OnBackPressed()
    {
        titleScreen.SetActive(true);
        playOptions.SetActive(false);
    }
}
