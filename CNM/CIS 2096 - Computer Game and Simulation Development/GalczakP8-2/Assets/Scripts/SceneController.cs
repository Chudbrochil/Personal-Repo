using UnityEngine;
using System.Collections;

public class SceneController : MonoBehaviour {

    [SerializeField]
    private GameObject enemyPrefab;
    private GameObject _enemy;

    [SerializeField]
    private PopupController popup;

    //public static bool Paused { get; set; }

    // Update is called once per frame
    void Update()
    {
        if (_enemy == null)
        {
            _enemy = Instantiate(enemyPrefab) as GameObject;
            _enemy.transform.position = new Vector3(0, 1, 0);
            float angle = Random.Range(0, 360);
            _enemy.transform.Rotate(0, angle, 0);
        }



        if (Input.GetKeyDown(KeyCode.Escape))
        {
            if (popup.isOpen == false)
            {
                popup.Open();
            }
            else
            {
                popup.Close();
            }

        }




    }



}
