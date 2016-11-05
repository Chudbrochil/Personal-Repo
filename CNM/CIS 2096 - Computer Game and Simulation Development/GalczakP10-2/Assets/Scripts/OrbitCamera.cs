using UnityEngine;
using System.Collections;

public class OrbitCamera : MonoBehaviour {

    [SerializeField]
    private Transform target;

    [SerializeField]
    private Vector3 targetOffset;

    public float rotSpeed = 1.5f;

    private float _rotY;
    private Vector3 _offset;


	// Initializing rotation and offset via the start method for the camera orbit class
	void Start () {
        _rotY = transform.eulerAngles.y;
        _offset = target.position - transform.position;
	}
	
	// Using late update to orbit the camera properly upon player camera/player movement
	void LateUpdate () {
        float horInput = Input.GetAxis("Horizontal");
        if(horInput != 0)
        {
            _rotY += horInput * rotSpeed;
        }
        else
        {
            _rotY += Input.GetAxis("Mouse X") * rotSpeed * 3;
        }

        Quaternion rotation = Quaternion.Euler(0, _rotY, 0);
        transform.position = target.position - (rotation * _offset);
        transform.LookAt(target);

	}
}
