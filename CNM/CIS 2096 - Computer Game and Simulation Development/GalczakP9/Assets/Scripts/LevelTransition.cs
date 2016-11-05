using UnityEngine;
using System.Collections;

public class LevelTransition : MonoBehaviour {

    public void Activate()
    {
        Application.LoadLevel("Outside");
    }

    public void Deactivate()
    {
        Debug.Log("Deactivated");
    }
}
