using UnityEngine;
using System.Collections;

public class MouseView : MonoBehaviour {



    public enum RotationAxes
    {
        MouseXAndY = 0,
        MouseX = 1,
        MouseY = 2,
    }
    public RotationAxes axes = RotationAxes.MouseXAndY;
    public float sensitivityHor = 9.0f;
    public float sensitivityVer = 9.0f;
    public float minVert = -45.0f;
    public float maxVert = 45.0f;

    private float _rotationx = 0;

	// Use this for initialization
	void Start () {
        Rigidbody body = GetComponent<Rigidbody>();
        if (body != null)
        {
            body.freezeRotation = true;
        }


	}
	
	// Update is called once per frame
	void Update () {
        if(axes == RotationAxes.MouseX)
        {
            // Horizontal rotation
            transform.Rotate(0, Input.GetAxis("Mouse X") * sensitivityVer, 0);
        }
        else if(axes == RotationAxes.MouseY)
        {
            // Vertical rotation
            _rotationx -= Input.GetAxis("Mouse Y") * sensitivityVer;
            _rotationx = Mathf.Clamp(_rotationx, minVert, maxVert);

            float rotationY = transform.localEulerAngles.y;
            transform.localEulerAngles = new Vector3(_rotationx, rotationY, 0);
        }
        else
        {
            // Both horizontal and vertical

            _rotationx -= Input.GetAxis("Mouse Y") * sensitivityVer;
            _rotationx = Mathf.Clamp(_rotationx, minVert, maxVert);

            float delta = Input.GetAxis("Mouse X") * sensitivityHor;
            float rotationY = transform.localEulerAngles.y + delta;
            transform.localEulerAngles = new Vector3(_rotationx, rotationY, 0);

            //transform.Rotate(sensitivityHor, sensitivityVer, 0);
        }
	}

    //internal static void PressedPause(bool paused)
    //{
    //    switch(paused)
    //    {
    //        case false:
    //            Cursor.lockState = CursorLockMode.None;
    //            Cursor.visible = !paused;
    //            break;
    //        case true:
    //            Cursor.lockState = CursorLockMode.Locked;
    //            Cursor.visible = !paused;
    //            break;
    //    }
    //}

    //private void OnResume()
    //{
    //    Cursor.lockState = CursorLockMode.Locked;
    //}



}
