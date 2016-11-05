using UnityEngine;
using System.Collections;

public class BulletController : MonoBehaviour {

    /// <summary>
    /// The distance from the player that will delete the bullet
    /// </summary>
    public float despawnRange = 50.0f;

    /// <summary>
    /// The maximum number of collisions before the bullet despawns
    /// </summary>
    public int maxCollisions = 3;

    private Transform player;

    private int collisions = 0;

    /// <summary>
    /// If the bullet came from an enemy
    /// </summary>
    public bool FromEnemy { get; set; }

    void Start()
    {
        player = GameObject.FindGameObjectWithTag("Player").transform;
    }

	// Update is called once per frame
	void Update ()
    {
        //Destroy the bullet if it has reached the maximum distance from the player
        if(Vector3.Distance(transform.position, player.position) > despawnRange)
        {
            Delete();
        }
        else if(collisions >= maxCollisions)    //Stop the bullet if it has collided the maximum number of times
        {
            Rigidbody bullet = transform.GetComponent<Rigidbody>();
            bullet.useGravity = true;
            if (bullet.velocity.y == 0)
            {
                StartCoroutine(DelayedDelete());
                bullet.velocity = Vector3.zero;
            }
        }
	}

    void OnCollisionEnter(Collision collision)
    {
        if(collision.gameObject.tag == "Enemy" && !FromEnemy)   //Determine if the player has hit an enemy
        {
            Delete();
            collision.gameObject.GetComponent<ReactiveTarget>().ReactToHit();   //Have the enemy react to being hit
        }
        else if(collision.gameObject.tag == "Player" && FromEnemy)  //Determine if an enemy has hit the player
        {
            Delete();
            Messenger<float>.Broadcast(GameEvent.HEALTH_CHANGED, -5f);  //Reduce the player's health by 5 points
        }
        else //If the bullet did not hit a player or enemy
        {
            collisions++;   //Increase the number of collisions
        }
    }

    //Destroy the bullet object
    private void Delete()
    {
        Destroy(gameObject);
    }

    //Destroy a bullet after two seconds.
    private IEnumerator DelayedDelete()
    {
        yield return new WaitForSeconds(2.0f);

        Delete();
    }
}
