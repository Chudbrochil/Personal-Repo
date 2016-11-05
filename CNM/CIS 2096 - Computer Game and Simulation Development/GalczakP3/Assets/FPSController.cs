using UnityEngine;
using System.Collections;

public class FPSController : MonoBehaviour {

    public float speed = 15.0f;
    public float gravity = -9.8f;
    public float jumpSpeed = 7.0f;
    private CharacterController _charController;

    private Vector3 moveDirection = Vector3.zero;


	// Use this for initialization
	void Start () {
        _charController = GetComponent<CharacterController>();
	}
	
	// Update is called once per frame
	//void Update () {
 //       float deltaX = Input.GetAxis("Horizontal") * speed;
 //       float deltaZ = Input.GetAxis("Vertical") * speed;
 //       transform.Translate(deltaX * Time.deltaTime, 0, deltaZ * Time.deltaTime);

 //       Vector3 movement = new Vector3(deltaX, 0, deltaZ);
 //       movement = Vector3.ClampMagnitude(movement, speed);


 //       movement.y = gravity;


 //       movement *= Time.deltaTime;
 //       movement = transform.TransformDirection(movement);

 //       _charController.Move(movement);

 //       // Jumping code... - Sourced from http://answers.unity3d.com/questions/324386/c-let-a-character-jump.html
 //       if (Input.GetKeyDown(KeyCode.Space) && _charController.isGrounded)
 //       {
 //           movement.y = jumpForce;
 //       }


 //   }
    void Update()
    {
        if (_charController.isGrounded)
        {
            moveDirection = new Vector3(Input.GetAxis("Horizontal") * speed, 0, Input.GetAxis("Vertical") * speed);
            moveDirection = transform.TransformDirection(moveDirection);
            moveDirection *= speed;
            // Fix jump speed issue, player is moving faster while jumping. clamping isn't in effect...
            moveDirection = Vector3.ClampMagnitude(moveDirection, speed);
            if (Input.GetKeyDown(KeyCode.Space))
            {
                moveDirection.y = jumpSpeed;
            }
        }



        moveDirection.y += gravity * Time.deltaTime;
        _charController.Move(moveDirection * Time.deltaTime);

        Debug.Log(moveDirection.y);
    }


}



