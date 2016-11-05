using UnityEngine;
using System.Collections;

public class ReactiveTarget : MonoBehaviour {


    public void ReactToHit()
    {
        WanderingAI behavior = GetComponent<WanderingAI>();

        // Telling WanderingAI that this object has died so it cna stopped it's behavior
        if (behavior != null)
        {
            behavior.SetAlive(false);
        }

        StartCoroutine(Die());
    }

    private IEnumerator Die()
    {
        this.transform.Rotate(-75, 0, 0);
        yield return new WaitForSeconds(1.5f);
        Destroy(this.gameObject);
    }

}
