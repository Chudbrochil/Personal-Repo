using UnityEngine;
using System.Collections;

public class DoorAction : MonoBehaviour, IActivatable
{
    public Transform aDoor;

    public void Activate()
    {
        aDoor = this.gameObject.transform;

        // TODO - get the door to move better
        aDoor.Rotate(0f, 40f, 0f);
    }

}