using UnityEngine;
using System.Collections;

public class Spawner : MonoBehaviour {
    [SerializeField] private GameObject prefab;
    private GameObject _gameObject;

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
	    if(_gameObject == null)
        {
            _gameObject = Instantiate(prefab) as GameObject;
            _gameObject.transform.position = new Vector3(0, 1, 0);
            float angle = Random.Range(0, 360);
            _gameObject.transform.Rotate(0, angle, 0);
        }
	}
}
