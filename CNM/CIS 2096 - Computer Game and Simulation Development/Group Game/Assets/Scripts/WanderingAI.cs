using UnityEngine;
using System.Collections;

[RequireComponent(typeof(NavMeshAgent))]
public class WanderingAI : MonoBehaviour {

    public float obstacleRange = 2.0f,  //Distance in front of the enemy to move away from
        playerRange = 50.0f,            //Distance from the player to engage the player
        wanderRadius = 5.0f,            //Distance from the enemy the enemy will walk to
        wanderTimer = 5.0f;             //Time before moving to a new position

    /// <summary>
    /// If the enemy is alive or not
    /// </summary>
    public bool Alive { get; set; }
    
    private Transform player;

    private Collider[] collisions;

    private bool shot = false,
        following = false;

    private float timer;

    private NavMeshAgent navAgent;

    private Ray toPlayer;
    private RaycastHit hitPlayer;

    void Start()
    {
        Alive = true;
        player = GameObject.FindGameObjectWithTag("Player").transform;
        navAgent = GetComponent<NavMeshAgent>();
        timer = wanderTimer;
    }

	// Update is called once per frame
	void Update () {
        if (Alive)
        {
            collisions = Physics.OverlapSphere(transform.position, playerRange);    //Look for the player around the enemy in a given area

            if (collisions.Length > 0)  //Determine if any colliders are in range of the enemy
            {
                foreach (Collider collision in collisions)
                {
                    toPlayer = new Ray(transform.position, collision.transform.position);

                    //Check to see if the player is close to the enemy and is in direct sight
                    if (collision.transform == player && Vector3.Distance (transform.position, player.position) <= playerRange
                        && Physics.Raycast(toPlayer, out hitPlayer, playerRange))
                    {
                        //Follow the player
                        navAgent.SetDestination(player.position);
                        following = true;
                    }
                    else
                    {
                        //Player not in sight and wandering
                        following = false;
                        timer += Time.deltaTime;
                        if(timer >= wanderTimer)
                        {
                            navAgent.SetDestination(RandomNavSphere(transform.position, wanderRadius, -1));
                            timer = 0;
                        }
                    }
                }
            }
            
            Ray ray = new Ray(transform.position, transform.forward);
            RaycastHit hit;
            if (Physics.Raycast(ray, out hit))
            {
                if (hit.distance <= obstacleRange && hit.transform.tag == "Terrain" && !following)  //Move away from hitting wall
                {
                    float angle = Random.Range(-110, 110);
                    transform.Rotate(0, angle, 0);
                }
                else if (hit.distance <= playerRange && hit.transform.tag == "Player")  //Shoot at player
                {
                    if (!shot)  //Limit amount of time between shots from enemy
                    {
                        transform.GetComponentInChildren<Shoot>().Fire();
                        shot = true;
                        StartCoroutine(ShootDelay());
                    }
                }
            }
        }
	}

    //Allow enemy to shoot after an amount of time
    private IEnumerator ShootDelay()
    {
        // Hard
        if(PlayerPrefs.GetInt("difficulty") == 2){
            yield return new WaitForSeconds(Random.Range(0.05f, 0.1f));
        }
        else if(PlayerPrefs.GetInt("difficulty") == 1)
        {
            yield return new WaitForSeconds(Random.Range(0.2f, 0.5f));
        }
        else
        {
            yield return new WaitForSeconds(Random.Range(1f, 2f));
        }
        

        shot = false;
    }

    //Pick random spot around the enemy and move to that point
    private static Vector3 RandomNavSphere(Vector3 origin, float distance, int layermask)
    {
        Vector3 randDirection = Random.insideUnitSphere * distance;
        randDirection += origin;

        NavMeshHit navHit;

        NavMesh.SamplePosition(randDirection, out navHit, distance, layermask);

        return navHit.position;
    }
}
