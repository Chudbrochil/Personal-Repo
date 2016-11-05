using UnityEngine;
using System.Collections;

public class MouseView : MonoBehaviour {

    public enum RotationAxes
    {
        MouseXAndY = 0,
        MouseX = 1,
        MouseY = 2
    }

    public RotationAxes axes = RotationAxes.MouseXAndY;

    public float sensitivityHor = 7.0f,
        sensitivityVert = 7.0f,
        minVert = -45.0f,
        maxVert = 45.0f;

    private float _roationX = 0.0f;

    private bool survival = false;

    // Use this for initialization
    void Start () {
        Rigidbody body = GetComponent<Rigidbody>();
        if(body != null)
        {
            body.freezeRotation = true;
        }

        if (FindObjectOfType<SceneController>() != null)
            survival = false;
        else if (FindObjectOfType<SurvivalController>() != null)
            survival = true;
    }
	
	// Update is called once per frame
	void Update () {
        if ((!survival && !SceneController.Paused) || (survival && !SurvivalController.Paused))
        {
            if (axes == RotationAxes.MouseX)
            {
                //Horizontal rotation
                transform.Rotate(0, Input.GetAxis("Mouse X") * sensitivityHor, 0);
            }
            else if (axes == RotationAxes.MouseY)
            {
                //Vertical rotation
                _roationX -= Input.GetAxis("Mouse Y") * sensitivityVert;
                _roationX = Mathf.Clamp(_roationX, minVert, maxVert);

                float rotationY = transform.localEulerAngles.y;
                transform.localEulerAngles = new Vector3(_roationX, rotationY, 0);
            }
            else
            {
                //Horizontal and vertical rotation
                _roationX -= Input.GetAxis("Mouse Y") * sensitivityVert;
                _roationX = Mathf.Clamp(_roationX, minVert, maxVert);

                float delta = Input.GetAxis("Mouse X") * sensitivityHor,
                    rotationY = transform.localEulerAngles.y + delta;
                transform.localEulerAngles = new Vector3(_roationX, rotationY, 0);
            }
        }
    }

    internal static void PressedPause(bool paused)
    {
        switch (paused)
        {
            case false:
                Cursor.lockState = CursorLockMode.None;
                break;
            case true:
                Cursor.lockState = CursorLockMode.Locked;
                break;
        }
        Cursor.visible = !paused;
    }

    private void OnResume()
    {
        Cursor.lockState = CursorLockMode.Locked;
    }
}
