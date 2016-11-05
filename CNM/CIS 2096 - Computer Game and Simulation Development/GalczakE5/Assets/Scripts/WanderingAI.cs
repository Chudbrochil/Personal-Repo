﻿using UnityEngine;
using System.Collections;

public class WanderingAI : MonoBehaviour {

    [SerializeField]
    private GameObject fireballPrefab;
    private GameObject _fireball;

    public float speed = 3.0f;
    public float obstacleRange = 5.0f;

	// Use this for initialization
	void Start () {
        Alive = true;
	}

    // Update is called once per frame
    void Update()
    {
        if (Alive)
        {
            transform.Translate(0, 0, speed * Time.deltaTime);
            Ray ray = new Ray(transform.position, transform.forward);
            RaycastHit hit;
            if (Physics.SphereCast(ray, 0.75f, out hit))
            {
                GameObject hitObject = hit.transform.gameObject;
                if (hitObject.GetComponent<PlayerController>())
                {
                    _fireball = Instantiate(fireballPrefab) as GameObject;
                    _fireball.transform.position =
                        transform.TransformPoint(Vector3.forward * 1.5f);
                    _fireball.transform.rotation = transform.rotation;
                }
                else if (hit.distance < obstacleRange)
                {
                    float angle = Random.Range(-110, 110);
                    transform.Rotate(0, angle, 0);
                }
            }

        }
    }

    public bool Alive { get; set; }
}
