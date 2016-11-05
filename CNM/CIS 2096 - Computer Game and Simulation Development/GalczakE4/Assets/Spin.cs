using UnityEngine;
using System.Collections;

public class Spin : MonoBehaviour {

    [SerializeField]
    public float speed;

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
        // x, y, z
        //transform.Translate(0, speed*Time.deltaTime, 0);

        transform.Rotate(0, speed * Time.deltaTime, 0);
	}
}
