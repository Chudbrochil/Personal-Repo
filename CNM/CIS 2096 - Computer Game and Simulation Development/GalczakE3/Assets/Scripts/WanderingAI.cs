using UnityEngine;
using System.Collections;

public class WanderingAI : MonoBehaviour {

    public float speed = 3.0f;
    public float obstacleRange = 5.0f;
    private bool _alive;

    [SerializeField] private GameObject fireballPrefab;
    private GameObject _fireball;
	
    void Start()
    {
        _alive = true;
    }

	// Update is called once per frame
	void Update () {


        // If the object is living... then do some actions
        if (_alive)
        {
            transform.Translate(0, 0, speed * Time.deltaTime);
            Ray ray = new Ray(transform.position, transform.forward);
            RaycastHit hit;


            if(Physics.SphereCast(ray, 0.75f, out hit))
            {
                GameObject hitObject = hit.transform.gameObject;
                if (hitObject.GetComponent<PlayerCharacter>())
                {
                    if (_fireball == null)
                    {
                        _fireball = Instantiate(fireballPrefab) as GameObject;
                        _fireball.transform.position = transform.TransformPoint(Vector3.forward * 1.5f);
                        _fireball.transform.rotation = transform.rotation;
                    }
                }
                else if (hit.distance < obstacleRange)
                {
                    float angle = Random.Range(-110, 110);
                    transform.Rotate(0, angle, 0);
                }
            }


            if (Physics.SphereCast(ray, 0.75f, out hit))
            {
                if (hit.distance < obstacleRange)
                {
                    // Picking a random degree to mvoe to in case there is an obstacle in the way
                    float angle = Random.Range(-110, 110);

                    // Rotates along the horizontal plane
                    transform.Rotate(0, angle, 0);
                }
            }

        }
	}


    public void SetAlive(bool alive)
    {
        _alive = alive;
    }
}
