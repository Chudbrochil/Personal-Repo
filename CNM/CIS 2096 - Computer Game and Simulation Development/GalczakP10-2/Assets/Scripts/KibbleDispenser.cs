using UnityEngine;
using System.Collections;

public class KibbleDispenser : MonoBehaviour {

    [SerializeField]
    ParticleSystem kibbleDispenser;

    [SerializeField]
    private AudioClip dogBark;

    // Turn on the kibble dispenser and play a barking sound
    public void Activate()
    {
        kibbleDispenser.playOnAwake = true;
        kibbleDispenser.Play();
        AudioSource.PlayClipAtPoint(dogBark, transform.position);
    }

    // Turn off the kibble dispenser
    public void Deactivate()
    {
        kibbleDispenser.playOnAwake = false;
        kibbleDispenser.Stop();
    }



}
