using UnityEngine;
using System.Collections;

public class ColorChangeDevice : MonoBehaviour {

    // Creates a random color on an object
    public void Operate()
    {
        Color random = new Color(Random.Range(0f, 1f), Random.Range(0f, 1f), Random.Range(0f, 1f));
        GetComponent<Renderer>().material.color = random;
    }


}
