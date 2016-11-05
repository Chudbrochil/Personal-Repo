using UnityEngine;
using System.Collections;

public class KibbleDispenser : MonoBehaviour {

    [SerializeField]
    ParticleSystem kibbleDispenser;

    [SerializeField]
    private AudioClip dogBark;


    public void Activate()
    {
        kibbleDispenser.playOnAwake = true;
        kibbleDispenser.Play();
        AudioSource.PlayClipAtPoint(dogBark, transform.position);
    }

    public void Deactivate()
    {
        kibbleDispenser.playOnAwake = false;
        kibbleDispenser.Stop();
    }



}
