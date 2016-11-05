using UnityEngine;
using System.Collections;

public class RayShooter : MonoBehaviour {

    private Camera _camera;

    public int size = 24,
        fontSize = 20;

    private Rigidbody _rigidbody;

    // Use this for initialization
    void Start () {
        _camera = GetComponent<Camera>();

        Cursor.lockState = CursorLockMode.Locked;
        Cursor.visible = false;
	}

    void OnGUI()
    {
        float posX = _camera.pixelWidth / 2 - size / 2,
            posY = _camera.pixelHeight / 2 - size / 2;

        GUIStyle style = new GUIStyle();
        style.fontSize = fontSize;
        style.alignment = TextAnchor.MiddleCenter;
        GUI.Label(new Rect(posX, posY, size, size), "+", style);
    }
	
	// Update is called once per frame
	/*void Update () {
        if (Input.GetMouseButtonDown(0) && !SceneController.Paused)
        {

        }
	}*/
}
