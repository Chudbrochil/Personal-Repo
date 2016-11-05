using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class PopupController : MonoBehaviour
{


    [SerializeField]
    private Text timeInto;

    public void Open()
    {
        Cursor.visible = true;
        gameObject.SetActive(true);
    }

    public void Close()
    {
        Cursor.visible = false;
        Cursor.lockState = CursorLockMode.Locked;
        gameObject.SetActive(false);
    }

    public void Update()
    {
        timeInto.text = Time.realtimeSinceStartup.ToString();
    }

    // add onspeedvalue or something else to modify players actions
    public void QuitGame()
    {
        Application.Quit();
    }

    public void OnSpeedValue(float speed)
    {
        Messenger<float>.Broadcast(GameEvent.JSPEED_CHANGED, speed);
    }

}
