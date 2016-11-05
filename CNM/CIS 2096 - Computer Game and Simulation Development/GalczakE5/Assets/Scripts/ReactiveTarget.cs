using UnityEngine;
using System.Collections;
using System;

public class ReactiveTarget : MonoBehaviour {

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
	
	}

    internal void ReactToHit()
    {
        WanderingAI behavior = GetComponent<WanderingAI>();
        behavior.Alive = false;
        StartCoroutine(Die());
    }

    private IEnumerator Die()
    {
        transform.Rotate(-75, 0, 0);

        yield return new WaitForSeconds(1.5f);

        Destroy(this.gameObject);
    }
}
