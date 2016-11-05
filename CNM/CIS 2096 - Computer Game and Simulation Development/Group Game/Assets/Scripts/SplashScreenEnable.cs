using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class SplashScreenEnable : MonoBehaviour {

    [SerializeField]
    private GameObject splashImage;

    [SerializeField]
    private GameObject titleScreen;


    public IEnumerator displaySplash()
    {
        yield return new WaitForSeconds(1f);

        titleScreen.SetActive(true);
        splashImage.SetActive(false);
        
    }
    
    void Start()
    {
        StartCoroutine(displaySplash());
    }



}
