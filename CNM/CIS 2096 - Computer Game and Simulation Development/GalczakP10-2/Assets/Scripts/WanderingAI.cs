using UnityEngine;
using System.Collections;

public class WanderingAI : MonoBehaviour {

    [SerializeField]
    public float speed = 3.0f;
    [SerializeField]
    public float obstacleRange = 5.0f;
    private bool _alive;

    private bool _waiting;


	// Use this for initialization
	void Start () {

        _alive = true;
        _waiting = false;
	}
	
	// Update is called once per frame
	void Update () {
        if (_alive && !_waiting)
        {
            transform.Translate(0, 0, speed * Time.deltaTime);
            Ray ray = new Ray(transform.position, transform.forward);
            RaycastHit hit;

            if (Physics.SphereCast(ray, 0.75f, out hit))
            {
                //GameObject hitObject = hit.transform.gameObject;

                // If the raycast that is being projected from the AI-controller is within hitting an obstacle, it will randomly turn
                if (hit.distance < obstacleRange)
                {
                    // TODO - It'd be cool to make it wait .1s before rotating
                    //yield return new WaitForSeconds(0.1f);
                    float angle = Random.Range(-40, 40);
                    transform.Rotate(0, angle, 0);
                }
            }


        }
	}

    // Setting whether or not the object is alive
    public void SetAlive(bool alive)
    {
        _alive = alive;
    }

    // Setting whether or not the object is waiting
    public void SetWaiting(bool waiting)
    {
        _waiting = waiting;
    }



}
