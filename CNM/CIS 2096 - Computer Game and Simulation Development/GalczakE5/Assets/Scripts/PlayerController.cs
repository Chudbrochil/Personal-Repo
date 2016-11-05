using UnityEngine;
using System.Collections;

public class PlayerController : MonoBehaviour {

    private float _health;
	// Use this for initialization
	void Start () {
        _health = 5;
	}
	
	// Update is called once per frame
	void Update () {
	
	}

    public void Hurt(float damage)
    {
        _health -= damage;
        Debug.Log("Health: " + _health);
    }
}
