using UnityEngine;
using System.Collections;

public class RayShooter : MonoBehaviour {

    private Camera _camera;

    void Start()
    {
        // Locking the camera/cursor
        _camera = GetComponent<Camera>();
        Cursor.lockState = CursorLockMode.Locked;
        Cursor.visible = false;
    }

    // Update is called once per frame
    void Update()
    {

        Vector3 point = new Vector3(_camera.pixelWidth / 2, _camera.pixelHeight / 2, 0);
        Ray ray = _camera.ScreenPointToRay(point);

        RaycastHit hitObject;


        // Anything that activates off from e action
        if (Input.GetKeyDown("e"))
        {

            if (Physics.Raycast(ray, out hitObject))
            {
                GameObject hitGameObject = hitObject.transform.gameObject;
                IActivatable eAction = hitGameObject.GetComponent<IActivatable>();

                if (eAction != null)
                {
                    eAction.Activate();
                }
            }

        }


        // Stopping an enemy/NPC - Causing them to wait
        if (Input.GetMouseButtonDown(0))
        {

            if (Physics.Raycast(ray, out hitObject))
            {
                GameObject hitGameObject = hitObject.transform.gameObject;
                ReactiveTarget targetScript = hitGameObject.GetComponent<ReactiveTarget>();

                // If the target in front of you is not null, then do action...
                if (targetScript != null)
                {

                    // Custom scripted behavior that is created in ReactiveTarget class
                    targetScript.ReactToHit();
                }


            }
        }


    }


    // Creating a sphere... - not currently used
    private IEnumerator SphereIndicator(Vector3 point)
    {
        GameObject sphere = GameObject.CreatePrimitive(PrimitiveType.Sphere);
        sphere.transform.position = point;

        yield return new WaitForSeconds(3);

        Destroy(sphere);
    }


}
