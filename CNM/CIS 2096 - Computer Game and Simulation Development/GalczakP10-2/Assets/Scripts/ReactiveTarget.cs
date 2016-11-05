using UnityEngine;
using System.Collections;

public class ReactiveTarget : MonoBehaviour {

    public void ReactToHit()
    {
        WanderingAI behavior = GetComponent<WanderingAI>();

        // I want the object to wait on react to hit

        if (behavior != null)
        {
            // Telling the wanderingAI class that the object is waiting...
            behavior.SetWaiting(true);
        }

        StartCoroutine(Wait());




        
        // If I want to kill the object on react to hit...
        
        // Telling WanderingAI that this object has died so it can stop it's behavior
        //if (behavior != null)
        //{
        //    behavior.SetAlive(false);
        //}
        //StartCoroutine(Die());
    }


    // Having the character wait
    private IEnumerator Wait()
    {
        this.transform.Rotate(0, 75, -15);
        yield return new WaitForSeconds(3.0f);

        this.transform.Rotate(0, 0, 15);

        // Telling the object to stop waiting
        WanderingAI behavior = GetComponent<WanderingAI>();
        behavior.SetWaiting(false);


    }


    // If I somehow kill the character
    private IEnumerator Die()
    {
        this.transform.Rotate(-75, 0, 0);
        yield return new WaitForSeconds(1.5f);
        Destroy(this.gameObject);
    }


}
