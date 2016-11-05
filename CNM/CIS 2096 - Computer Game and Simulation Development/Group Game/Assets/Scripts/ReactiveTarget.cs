using UnityEngine;
using System.Collections;

public class ReactiveTarget : MonoBehaviour {

    private bool hitOnce = false;

    [SerializeField]
    private AudioClip impactSound;

    public void ReactToHit()
    {
        WanderingAI behavior = GetComponent<WanderingAI>();
        if (behavior != null)
        {
            behavior.Alive = false;
        }

        if (!hitOnce)
        {
            AudioSource.PlayClipAtPoint(impactSound, transform.position);
            Messenger.Broadcast(GameEvent.ENEMY_KILLED);
            transform.GetComponent<Rigidbody>().useGravity = true;
            transform.GetComponent<NavMeshAgent>().enabled = false;
            Destroy(gameObject, 10.0f);
        }

        hitOnce = true;
    }
}
