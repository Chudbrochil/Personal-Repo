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

    }

    void Awake()
    {
        Messenger<float>.AddListener(GameEvent.JSPEED_CHANGED, OnSpeedChanged);
    }

    void OnDestroy()
    {
        Messenger<float>.RemoveListener(GameEvent.JSPEED_CHANGED, OnSpeedChanged);
    }

    // Jump speed slider in GUI
    private void OnSpeedChanged(float value)
    {
        jumpSpeed = 3.0f * value;
    }


}



