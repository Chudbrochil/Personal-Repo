using UnityEngine;
using System.Collections;

public class KibbleDispenser : MonoBehaviour {

    [SerializeField]
    ParticleSystem kibbleDispenser;


    public void Activate()
    {
        kibbleDispenser.playOnAwake = true;
        kibbleDispenser.Play();
    }

    public void Deactivate()
    {
        kibbleDispenser.playOnAwake = false;
        kibbleDispenser.Stop();
    }



}
