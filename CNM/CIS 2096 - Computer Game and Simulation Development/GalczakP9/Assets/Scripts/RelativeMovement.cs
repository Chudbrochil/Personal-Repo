using UnityEngine;
using System.Collections;

public class RelativeMovement : MonoBehaviour {

    [SerializeField]
    private Transform target;

    public float pushForce = 2.0f;

    public float rotSpeed = 5.0f;
    public float moveSpeed = 6.0f;
    private CharacterController _charController;



    // Jump and gravity variables
    public float jumpSpeed = 15.0f;
    public float gravity = -9.8f;
    public float terminalVelocity = -10.0f;
    public float minFall = -1.5f;
    public float _vertSpeed;
    private ControllerColliderHit _contact;






    void Start()
    {
        _vertSpeed = minFall;
        _charController = GetComponent<CharacterController>();
    }


	// Update is called once per frame
	void Update () {
        Vector3 movement = Vector3.zero;

        float horInput = Input.GetAxis("Horizontal");
        float vertInput = Input.GetAxis("Vertical");

        // Jump scripts
        bool hitGround = false;
        RaycastHit hit;

        if (_vertSpeed < 0 && Physics.Raycast(transform.position, Vector3.down, out hit))
        {
            float check = (_charController.height + _charController.radius) / 1.9f;
            hitGround = hit.distance <= check;
        }

        if (horInput != 0 || vertInput != 0)
        {
            movement.x = horInput * moveSpeed;
            movement.z = vertInput * moveSpeed;

            movement = Vector3.ClampMagnitude(movement, moveSpeed);
            Quaternion tmp = target.rotation;
            target.eulerAngles = new Vector3(0, target.eulerAngles.y, 0);
            movement = target.TransformDirection(movement);
            target.rotation = tmp;
            Quaternion direction = Quaternion.LookRotation(movement);
            transform.rotation = Quaternion.Lerp(transform.rotation, direction, rotSpeed * Time.deltaTime);
        }

        // _animator.SetFloat("Speed", movement.sqrMagnitude);

        if (hitGround)
        {
            if (Input.GetButtonDown("Jump"))
            {
                _vertSpeed = jumpSpeed;
            }
            else
            {
                _vertSpeed = minFall;
                //_animator.SetBool("Jumping", false);
            }
        }
        else
        {
            _vertSpeed += gravity * 5 * Time.deltaTime;
            if (_vertSpeed < terminalVelocity)
            {
                _vertSpeed = terminalVelocity;
            }
            if (_charController.isGrounded)
            {
                if (Vector3.Dot(movement, _contact.normal) < 0)
                {
                    movement = _contact.normal * moveSpeed;
                }
                else
                {
                    movement += _contact.normal * moveSpeed;
                }

            }

            //if (_contact != null)
            //{
            //    _animator.SetBool("Jumping", true);
            //}

        }




        movement.y = _vertSpeed;
        movement *= Time.deltaTime;
        _charController.Move(movement);
	}

    void OnControllerColliderHit(ControllerColliderHit hit)
    {
        _contact = hit;
        Rigidbody body = hit.collider.attachedRigidbody;
        if (body != null && !body.isKinematic)
        {
            body.velocity = hit.moveDirection * pushForce;
        }
    }

    void Awake()
    {
        Messenger<float>.AddListener(GameEvent.JJUMP_CHANGED, OnJumpChanged);
        Messenger<float>.AddListener(GameEvent.JSPEED_CHANGED, OnSpeedChanged);
    }

    void OnDestroy()
    {
        Messenger<float>.RemoveListener(GameEvent.JJUMP_CHANGED, OnJumpChanged);
        Messenger<float>.RemoveListener(GameEvent.JSPEED_CHANGED, OnSpeedChanged);
    }

    // Jump speed slider in GUI
    private void OnJumpChanged(float value)
    {
        jumpSpeed = 7.0f * value;
    }

    // Speed modifier via energy powerup
    private void OnSpeedChanged(float value)
    {
        moveSpeed = value;
    }



}
