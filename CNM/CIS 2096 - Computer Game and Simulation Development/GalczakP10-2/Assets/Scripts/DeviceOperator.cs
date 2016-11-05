using UnityEngine;
using System.Collections;

public class DeviceOperator : MonoBehaviour {

    [SerializeField]
    public float radius = 1.5f;

    void Update()
    {
        // Operating the "Operate" method upon using Fire3(middle mouse)
        if (Input.GetButtonDown("Fire3"))
        {
            Collider[] hitColliders = Physics.OverlapSphere(transform.position, radius);
            foreach (Collider hitCollider in hitColliders)
            {
                Vector3 direction = hitCollider.transform.position - transform.position;
                if (Vector3.Dot(transform.forward, direction) > .5f)
                {
                    hitCollider.SendMessage("Operate", SendMessageOptions.DontRequireReceiver);
                }

            }
        }
    }


}
