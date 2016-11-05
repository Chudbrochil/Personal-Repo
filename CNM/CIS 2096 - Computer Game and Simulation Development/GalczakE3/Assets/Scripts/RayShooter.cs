using UnityEngine;
using System.Collections;

public class RayShooter : MonoBehaviour {

    private Camera _camera;

    public int size = 60;

    // Use this for initialization
    void Start () {

        _camera = GetComponent<Camera>();
        // Locking cursor in middle of the screen
        Cursor.lockState = CursorLockMode.Locked;
        Cursor.visible = false;
	}

    void OnGUI()
    {
        float posX = (_camera.pixelWidth / 2) - (size / 4);
        float posY = (_camera.pixelHeight / 2) - (size / 2);

        // Instead of a plus you can reference an image in this place for a crosshair
        GUI.Label(new Rect(posX, posY, size, size), "+");
    }
	
	// Update is called once per frame
	void Update () {
	    if(Input.GetMouseButtonDown(0))
        {
            Vector3 point = new Vector3(_camera.pixelWidth / 2, _camera.pixelHeight / 2, 0);
            Ray ray = _camera.ScreenPointToRay(point);

            RaycastHit hitObject;

            if (Physics.Raycast(ray, out hitObject))
            {
                GameObject hitGameObject = hitObject.transform.gameObject;
                ReactiveTarget targetScript = hitGameObject.GetComponent<ReactiveTarget>();

                // If the target in front of you is not null, then do action...
                if(targetScript != null)
                {
                    Debug.Log("Hit target");

                    // Custom scripted behavior that is created in ReactiveTarget class
                    targetScript.ReactToHit();
                }
                // If the target in front of you is null, then just create a sphere...
                else
                {
                    Vector3 pos = hitObject.point;
                    StartCoroutine(SphereIndicator(pos));
                }
                


            }

            
        }
	}

    private static IEnumerator SphereIndicator(Vector3 pos)
    {
        // Create a sphere where you are clicking
        GameObject sphere = GameObject.CreatePrimitive(PrimitiveType.Sphere);
        sphere.transform.position = pos;

        // Destroying the sphere 1 second after it is created
        yield return new WaitForSeconds(1);
        Destroy(sphere);
    }
}
