using UnityEngine;
using System.Collections;

public class Shoot : MonoBehaviour {
    /// <summary>
    /// Bullet prefab to shoot from gun
    /// </summary>
    public GameObject bulletPrefab;
    
    /// <summary>
    /// The prefab in the position to spawn the bullet from
    /// </summary>
    /// <remarks>Works best as an empty connected to the gun</remarks>
    public Transform bulletSpawn;

    /// <summary>
    /// The speed to shoot the bullet
    /// </summary>
    public static float bulletSpeed = 10.0f;
    
    /// <summary>
    /// Time it takes before the player can shoot again
    /// </summary>
    public static float reloadSpeed = 0.05f;

    private bool reloading = false, //If the gun is reloading
        survival = false,           //If the player is in survival mode
        enemy = false;              //If the bullet was shot by an enemy

    public float minWallDist = 1f;  //Distance from a wall to make sure the gun stays mostly straight

    void Start()
    {
        if(transform.parent.tag == "Enemy") //The bullet came from an enemy
            enemy = true;

        if (FindObjectOfType<SceneController>() != null)    //Not in survival mode
            survival = false;
        else if (FindObjectOfType<SurvivalController>() != null)    //In survival mode
            survival = true;
    }

    // Update is called once per frame
    void Update()
    {
        Ray ray = new Ray(transform.parent.position, transform.parent.forward);
        RaycastHit hit;
        if(Physics.Raycast(ray, out hit, Mathf.Infinity) && Vector3.Distance(hit.point, transform.position) >= minWallDist)
        {
            transform.LookAt(hit.point);    //Point the gun to where the crosshair is aiming
        }

        if (!enemy)
        {
            if (Input.GetButton("Fire1") && ((!survival && !SceneController.Paused) || (survival && !SurvivalController.Paused)) && !reloading)
            {
                Fire(); //Fire the gun if the player has pressed the fire button
            }
        }
    }


    /// <summary>
    /// Spawn a bullet from a certain position with a certain speed
    /// </summary>
    public void Fire()
    {
        GameObject bullet = (GameObject)Instantiate(bulletPrefab, bulletSpawn.position, Quaternion.identity);
        bullet.transform.forward = transform.forward;                                       //Point the bullet in the same direction as the gun
        bullet.GetComponent<Rigidbody>().velocity = bullet.transform.forward * bulletSpeed; //Add force to the bullet to move it foward

        if(enemy)
        {
            bullet.GetComponent<BulletController>().FromEnemy = true;   //Tell the bullet controller that the bullet came from an enemy
        }
        else
        {
            //Stop the player from shooting again
            reloading = true;
            StartCoroutine(ShootTimer(reloadSpeed));

            bullet.GetComponent<BulletController>().FromEnemy = false;  //Tell the bullet controller that the bullet came from the player
        }
    }

    //Allow the player to shoot after specified amount of time
    private IEnumerator ShootTimer(float seconds)
    {
        yield return new WaitForSeconds(seconds);

        reloading = false;
    }
}

