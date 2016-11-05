using UnityEngine;
using System.Collections;

public class FPSController : MonoBehaviour {

    public float speed = 15.0f;
    public float gravity = -9.8f;
    public float jumpSpeed = 10.0f;
    private CharacterController _charController;

    private Vector3 moveDirection = Vector3.zero;


	// Use this for initialization
	void Start () {
        _charController = GetComponent<CharacterController>();
        // Loading player's saved position via the QuitGame method from SettingsPopup
        if (Application.loadedLevelName == "Outside")
        {
            // Dynamically gets position in level from player preferences for both levels
            //transform.position = new Vector3(PlayerPrefs.GetFloat("PlayerX-Out"), PlayerPrefs.GetFloat("PlayerY-Out"), PlayerPrefs.GetFloat("PlayerZ-Out"));
            // Bug, needs to be declared statically
            transform.position = new Vector3(-1182, -678, 241);
        }
        else if (Application.loadedLevelName == "Tutorial")
        {
            transform.position = new Vector3(PlayerPrefs.GetFloat("PlayerX-Tut"), PlayerPrefs.GetFloat("PlayerY-Tut"), PlayerPrefs.GetFloat("PlayerZ-Tut"));
        }

    }



    void Update()
    {

        // If the player isn't jumping then allow movement
        if (_charController.isGrounded)
        {
            moveDirection = new Vector3(Input.GetAxis("Horizontal") * speed, 0, Input.GetAxis("Vertical") * speed);
            moveDirection = transform.TransformDirection(moveDirection);
            moveDirection *= speed;

            // Clamping movement so diagonal movement isn't faster than horizontal via the plane
            moveDirection = Vector3.ClampMagnitude(moveDirection, speed);

            // First person jumping
            if (Input.GetKeyDown(KeyCode.Space))
            {
                moveDirection.y = jumpSpeed;
            }
        }

        // Start movement via the character controller
        moveDirection.y += gravity * Time.deltaTime;
        _charController.Move(moveDirection * Time.deltaTime);

    }

}



