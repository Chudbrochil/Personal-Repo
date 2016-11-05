using UnityEngine;
using System.Collections;

public class FPSController : MonoBehaviour {

    public float speed = 6.0f,
        jumpSpeed = 150.0f,
        terminalVelocity = -20.0f;

    public Camera camera;

    public int size = 24,
        fontSize = 20;

    private Rigidbody _rigidbody;

    private bool survival = false;

    void OnGUI()
    {
        if ((!survival && !SceneController.Paused) || (survival && !SurvivalController.Paused))
        {
            float posX = camera.pixelWidth / 2 - size / 2,
                posY = camera.pixelHeight / 2 - size / 2;

            GUIStyle style = new GUIStyle();
            style.fontSize = fontSize;
            style.alignment = TextAnchor.MiddleCenter;
            GUI.Label(new Rect(posX, posY, size, size), "+", style);
        }
    }

    // Use this for initialization
    void Start () {
        _rigidbody = GetComponent<Rigidbody>();

        if (FindObjectOfType<SceneController>() != null)
            survival = false;
        else if (FindObjectOfType<SurvivalController>() != null)
            survival = true;
    }

    // Update is called once per frame
    void Update() {
        float deltaX = Input.GetAxis("Horizontal") * speed * Time.deltaTime,
            deltaZ = Input.GetAxis("Vertical") * speed * Time.deltaTime;
        
        if (Input.GetAxis("Vertical") != 0 && !SceneController.Paused)
        {
            if (Input.GetAxis("Run") != 0)
            {
                transform.Translate(Vector3.forward * deltaZ * 1.8f);
            }
            else
            {
                transform.Translate(Vector3.forward * deltaZ);
            }
        }

        if (Input.GetAxis("Horizontal") != 0 && !SceneController.Paused)
        {
            transform.Translate(Vector3.right * deltaX);
        }

        Vector3 velocity = transform.GetComponent<Rigidbody>().velocity;
        if (velocity.y < terminalVelocity)
        {
            transform.GetComponent<Rigidbody>().velocity = new Vector3(velocity.x, terminalVelocity, velocity.z);
        }
        else if(velocity.magnitude > speed)
        {
            velocity = Vector3.ClampMagnitude(velocity, speed);
        }
	}

    void FixedUpdate()
    {
        if (Input.GetAxis("Jump") > 0 && Physics.Raycast(transform.position, Vector3.down, 1.1f))
        {
            _rigidbody.AddForce(Vector3.up * jumpSpeed * Time.deltaTime, ForceMode.Impulse);
        }
    }
}
