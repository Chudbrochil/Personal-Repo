using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class PopupController : MonoBehaviour
{

    [SerializeField]
    private Camera shiftCamera;

    [SerializeField]
    private GameObject player;

    private bool isThirdPerson = false;

    Vector3 thirdPerson = new Vector3(0f, 2.5f, -5f);
    //Vector3 firstPerson = new Vector3(0f, 0f, 0f);

    [SerializeField]
    private Text timeInto;

    [SerializeField]
    private Text jumpText;

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
        jumpText.text = "Jump Height: " + speed.ToString();
    }

    public void ShiftPerspective()
    {
        OrbitCamera oc = shiftCamera.GetComponent<OrbitCamera>();

        if (!isThirdPerson)
        {
            Debug.Log("Shifted to third person");
            oc.enabled = true;
            shiftCamera.transform.position = player.transform.position + thirdPerson;
            isThirdPerson = true;
        }
        else
        {
            Debug.Log("Shifted to first person");
            oc.enabled = false;
            shiftCamera.transform.position = player.transform.position;
            isThirdPerson = false;
        }
    }

}
