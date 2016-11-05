using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class SurvivalUIController : MonoBehaviour {

    [SerializeField]
    private Slider healthBar;

    [SerializeField]
    private Text health,
        spawned,
        killed,
        waves,
        endMessage;

    [SerializeField]
    private Image enemyBackground,
        endBackground;

    [SerializeField]
    private GameObject deathOptions;

    private string maxWaves = "1";

    /// <summary>
    /// Set the maximum health available to player.
    /// </summary>
    /// <param name="max">Maximum health value.</param>
    public void SetMaxHealth(float max)
    {
        healthBar.maxValue = max;
    }

    /// <summary>
    /// Set the value of the player's health.
    /// </summary>
    /// <param name="value">The updated health value.</param>
	public void SetHealth(float value)
    {
        healthBar.value = value;
        health.text = "Health: " + value;
    }

    /// <summary>
    /// Update GUI to inform user of spawned enemies amount.
    /// </summary>
    /// <param name="value">Number of enemies.</param>
    public void OnSpawnedChanged(int value)
    {
        if (PlayerPrefs.GetInt("endless", 0) == 1)
            spawned.text = "Enemies Spawned: ";
        else
            spawned.text = "Enemies Remaining: ";

        spawned.text += value.ToString();

        EditImageSize();
    }

    /// <summary>
    /// Update GUI to inform user of the amount of enemies they've killed.
    /// </summary>
    /// <param name="value">Number of enemies killed.</param>
    public void OnKilledChanged(int value)
    {
        killed.text = "Enemies Killed: " + value.ToString();
    }

    /// <summary>
    /// Update the visibility of the "Waves Completed" GUI element.
    /// </summary>
    /// <param name="survival">If the player is in survival mode or not.</param>
    public void OnWavesVisible(bool survival)
    {
        waves.gameObject.SetActive(survival);
    }

    /// <summary>
    /// Update GUI to inform user of how many waves they've completed.
    /// </summary>
    /// <param name="completed">Number of completed waves.</param>
    public void OnWavesChanged(int completed)
    {
        waves.text = "Waves Completed: " + completed + "/" + maxWaves;
    }

    /// <summary>
    /// Update the maximum number of waves and update GUI to represent this.
    /// </summary>
    /// <param name="max">Maximum waves to complete.</param>
    public void OnMaxWavesChanged(int max)
    {
        maxWaves = max.ToString();
        OnWavesChanged(0);
    }

    private void EditImageSize()
    {
        float size,
            posY;

        RectTransform image = enemyBackground.rectTransform;

        if (PlayerPrefs.GetInt("endless", 1) == 0)
        {
            size = 100;
            posY = 150.25f;
        }
        else
        {
            size = 60;
            posY = 170.13f;
        }

        enemyBackground.rectTransform.sizeDelta = new Vector2(image.rect.width, size);
        enemyBackground.rectTransform.anchoredPosition = new Vector2(image.anchoredPosition.x, posY);
    }

    /// <summary>
    /// Show user end screen.
    /// </summary>
    /// <param name="won">If the user has won or not.</param>
    public void OnFinish(bool won)
    {
        deathOptions.SetActive(true);

        if (won)
        {
            endMessage.text = "Surivived all " + maxWaves + " waves!";
            endBackground.color = Color.green;
        }
        else
        {
            endMessage.text = "You died!";
            endBackground.color = Color.red;
        }
    }

    /// <summary>
    /// Show or hide the pause screen
    /// </summary>
    /// <param name="paused">If the game is paused or not</param>
    public void OnPaused(bool paused)
    {
        deathOptions.SetActive(paused);

        if(paused)
        {
            endMessage.text = "Paused";
            endBackground.color = Color.blue;
        }
    }

    /// <summary>
    /// Reload the scene and all its components.
    /// </summary>
    public void Restart()
    {
        GetComponent<LoadLevel>().Load("Survival");
    }

    /// <summary>
    /// Return to the main menu screen.
    /// </summary>
    public void Return()
    {
        GetComponent<LoadLevel>().Load("MainMenu");
    }
}
