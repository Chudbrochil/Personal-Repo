using UnityEngine;
using System.Collections;

public class SceneController : MonoBehaviour {

    [SerializeField]
    private GameObject _enemyPrefab;

    private GameObject _enemy;

    public GameObject spawns;

    private Transform[] spawnPoints;

    public static bool Paused { get; set; }

    // Use this for initialization
    void Start ()
    {
        spawnPoints = new Transform[spawns.transform.childCount];

        for (int i = 0; i < spawns.transform.childCount; i++)
        {
            spawnPoints[i] = spawns.transform.GetChild(i);
        }

        Cursor.lockState = CursorLockMode.Locked;
        Cursor.visible = false;
    }
	
	// Update is called once per frame
	void Update () {
	    if (_enemy == null)
        {
            _enemy = Instantiate(_enemyPrefab) as GameObject;
            _enemy.transform.position = SpawnLocation();
            
            _enemy.transform.Rotate(0, Random.rotation.eulerAngles.y, 0);
        }

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
        }
    }

    private Vector3 SpawnLocation()
    {
        int spawn = Random.Range(0, spawnPoints.Length);

        return spawnPoints[spawn].position;
    }
}
