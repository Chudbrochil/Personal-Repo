using UnityEngine;
using System.Collections;

public class ImmediateGUI : MonoBehaviour {

    void OnGUI()
    {
        if (GUI.Button(new Rect(10, 10, 40, 20), "Test"))
        {
            Debug.Log("Test Button");
        }
    }


}
