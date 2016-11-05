using UnityEngine;
using System.Collections;

public class DoorOpenDevice : MonoBehaviour {

    [SerializeField]
    private Vector3 dPos;

    private bool _open;

    // Door open script called upon Operate send
    public void Operate()
    {
        if (_open)
        {
            Vector3 pos = transform.position - dPos;
            transform.position = pos;
        }
        else
        {
            Vector3 pos = transform.position + dPos;
            transform.position = pos;
        }

        _open = !_open;

    }

}
