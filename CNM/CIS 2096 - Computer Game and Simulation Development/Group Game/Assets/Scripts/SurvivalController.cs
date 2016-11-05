using UnityEngine;
using System.Collections;
using System.Collections.Generic;

public class SurvivalController : MonoBehaviour
{
    private SurvivalUIController ui;

    /// <summary>
    /// Level layout prefabs to load on the scene.
    /// </summary>
    public List<GameObject> layouts;

    private Transform[] enemySpawns,    //Enemy spawn points
        playerSpawns;                   //Player spawn points

    /// <summary>
    /// The pause state of the Survival scene.
    /// </summary>
    public static bool Paused { get; private set; }

    public GameObject player,
        enemy;

    private bool endless;

    private int layoutLoaded,   //The current layout loaded
        spawnedEnemies,         //Number of enemies spawned
        killedEnemies = 0,      //Number of enemies killed
        remainingEnemies,       //Number of enemies remaining
        maxEnemies,             //Maximum enemies to spawn
        initialSpawn,           //Number of enemies to spawn initially
        spawnMultiplier,        //Number of enemies to spawn for each enemy death
        waves,                  //Maximum number of enemies
        wavesCompleted = 0;     //Number of enemies completed

    /// <summary>
    /// Difficulty settings for survival.
    /// </summary>
    /// <remarks>Controls amount to respawn enemies.</remarks>
    public enum Difficulty
    {
        Easy = 2,
        Medium = 3,
        Hard = 4
    }

    private Difficulty difficulty;

    private float health,   //Player's current health
        maxHealth = 100;    //Player's maximum health amount

    void Awake()
    {
        Messenger.AddListener(GameEvent.ENEMY_KILLED, EnemyKilled);
        Messenger<float>.AddListener(GameEvent.HEALTH_CHANGED, HealthChanged);
    }

    void Destroy()
    {
        Messenger.RemoveListener(GameEvent.ENEMY_KILLED, EnemyKilled);
        Messenger<float>.RemoveListener(GameEvent.HEALTH_CHANGED, HealthChanged);
    }

	// Use this for initialization
	void Start ()
    {
        //Lock cursor
        Cursor.lockState = CursorLockMode.Locked;
        Cursor.visible = false;

        ui = GetComponent<SurvivalUIController>();

        //Pick a random layout
        layoutLoaded = Random.Range(0, layouts.Count);

        //Load the spawns points from the loaded layout
        enemySpawns = layouts[layoutLoaded].transform.FindChild("EnemySpawns").GetComponentsInChildren<Transform>();
        playerSpawns = layouts[layoutLoaded].transform.FindChild("PlayerSpawns").GetComponentsInChildren<Transform>();

        //Spawn the layout and player
        Instantiate(layouts[layoutLoaded] as GameObject);
        Instantiate(player as GameObject, GetSpawn(playerSpawns), Quaternion.Euler(0, Random.rotation.eulerAngles.y, 0));

        //Get if the player has selected endless mode or not
        int endlessInt = PlayerPrefs.GetInt("endless", 1);
        switch (endlessInt)
        {
            case 0:
                endless = false;

                //Set waves variables for survival mode
                waves = PlayerPrefs.GetInt("waves", 1);
                ui.OnMaxWavesChanged(waves);
                break;
            case 1:
                endless = true;
                break;
        }
        
        ui.OnWavesVisible(!endless); //Show or hide wave progress

        //Get and set selected difficulty
        int difficultyInt = PlayerPrefs.GetInt("difficulty", 0);
        switch(difficultyInt)
        {
            case 0:
                difficulty = Difficulty.Easy;
                break;
            case 1:
                difficulty = Difficulty.Medium;
                break;
            case 2:
                difficulty = Difficulty.Hard;
                break;
        }

        //Set initial enemy variables to the difficulty selected
        initialSpawn = (int)difficulty;
        spawnedEnemies = initialSpawn;
        spawnMultiplier = initialSpawn;
        maxEnemies = initialSpawn;
        remainingEnemies = initialSpawn;

        ui.OnSpawnedChanged(initialSpawn);  //Update the amount of enemies spawned to initial spawn amount

        //Set health and update GUI
        health = maxHealth;
        ui.SetMaxHealth(maxHealth);
        ui.SetHealth(health);

        //Spawn enemies
        for(int i = 0; i < initialSpawn; i++)
        {
            Instantiate(enemy as GameObject, GetSpawn(enemySpawns), Quaternion.Euler(0, Random.rotation.eulerAngles.y, 0));
        }
    }
	
	// Update is called once per frame
	void Update ()
    {
        //Pause game
        if (Input.GetKeyDown(KeyCode.Escape))
        {
            switch (Paused)
            {
                case false:
                    Time.timeScale = 0;
                    MouseView.PressedPause(Paused);
                    Paused = true;
                    break;
                case true:
                    Time.timeScale = 1;
                    MouseView.PressedPause(Paused);
                    Paused = false;
                    break;
            }

            ui.OnPaused(Paused);
        }
    }

    //Pick random spawn point
    private Vector3 GetSpawn(Transform[] spawns)
    {
        return spawns[Random.Range(1, spawns.Length)].position;
    }

    /// <summary>
    /// Update game information upon enemy death.
    /// </summary>
    public void EnemyKilled()
    {
        int numSpawn;   //How many more enemies to spawn
        bool remaining; //How many enemies are remaining

        if (endless)    //Determine if player is in endless mode
        {
            numSpawn = (int)difficulty; //Spawn more enemies equal to the difficulty value
            remaining = false;          //Set remaining to false because the game is in endless mode
        }
        else //Player is in survival mode
        {
            remainingEnemies--;                 //Remove one enemy from the remaining enemies
            remaining = remainingEnemies != 0;  //Determine if there are enemies remaining
            if (!remaining) //All enemies have been killed
            {
                wavesCompleted++;   //Increase the number of waves completed

                if (wavesCompleted == PlayerPrefs.GetInt("waves")) //Determine if all waves have been completed
                {
                    ui.OnFinish(true);  //Show end screen
                    return;             //Skip enemy spawn code
                }
                else
                {
                    numSpawn = (wavesCompleted + 1) * (int)difficulty;  //Spawn next waves of enemies based on difficulty
                    ui.OnWavesChanged(wavesCompleted);                  //Update GUI to show number of completed waves
                }
            }
            else
                numSpawn = 0;   //Don't spawn any more enemies if there are still some alive
        }
        
        //Update the number of spawned enemies
        spawnedEnemies += numSpawn - 1;
        ui.OnSpawnedChanged(spawnedEnemies);

        //Update number of killed enemies
        killedEnemies++;
        ui.OnKilledChanged(killedEnemies);

        if (!remaining) //If no enemies remain, respawn more enemies
        {
            for (int i = 0; i < numSpawn; i++)
            {
                Instantiate(enemy as GameObject, GetSpawn(enemySpawns), Quaternion.Euler(0, Random.rotation.eulerAngles.y, 0));
            }
        }
    }

    /// <summary>
    /// Change the player's health amount
    /// </summary>
    /// <param name="change">The amount to increase the health.</param>
    public void HealthChanged(float change)
    {
        health += change;       //Update health amount
        ui.SetHealth(health);   //Update GUI

        if (health <= 0)    //The player has died
        {
            ui.OnFinish(false); //Show death screen

            //Show cursor and pause game
            Paused = true;
            Cursor.lockState = CursorLockMode.None;
            Cursor.visible = true;
            Time.timeScale = 0;
        }
    }
}
