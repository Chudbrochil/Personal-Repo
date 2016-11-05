using UnityEngine;
using System.Collections;
using UnityEngine.UI;
using System.Collections.Generic;

public class PopupController : MonoBehaviour
{

    [SerializeField]
    private GameObject shiftCamera;

    [SerializeField]
    private GameObject player;

    [SerializeField]
    private GameObject crosshair;

    [SerializeField]
    private Text inventory1;
    [SerializeField]
    private Text inventory2;
    [SerializeField]
    private Text inventory3;
    [SerializeField]
    private Text inventory4;
    [SerializeField]
    private Text inventory5;

    [SerializeField]
    private GameObject Energy;

    [SerializeField]
    private GameObject Health;

    [SerializeField]
    private GameObject Bone;


    //public Dictionary<string, GameObject> invObjects;
    //public string test;

    string lastItemAdded;


    public void dropItem()
    {       

        if (lastItemAdded == Energy.name)
        {
            Instantiate(Energy, player.transform.position+(transform.forward * 2), Quaternion.Euler(0, 0, 0));
        }
        else if (lastItemAdded == Health.name)
        {
            Instantiate(Health, player.transform.position + (transform.forward * 2), Quaternion.Euler(0, 0, 0));
        }
        else
        {
            Instantiate(Bone, player.transform.position + (transform.forward * 2), Quaternion.Euler(0, 0, 0));
        }


    }

    public void useItem()
    {
        if(lastItemAdded == Energy.name)
        {
            Debug.Log("Energy used!");
            OnSpeedValue(20);
        }
        else if(lastItemAdded == Health.name){
            Debug.Log("Health used!");
        }
        else
        {
            Debug.Log("Bone used!");
        }
    }


    public void setInventory()
    {
        List<string> itemList = new List<string>();

        // Getting the current inventory
        if(Managers.Inventory.GetItemList() != null)
        {
            itemList = Managers.Inventory.GetItemList();
        }


        Text[] inventoryFields = { inventory1, inventory2, inventory3, inventory4, inventory5 };

        if (itemList.Count > 0)
        {
            for (int i = 0; i < itemList.Count; ++i)
            {
                inventoryFields[i].text = itemList[i] + "(" + Managers.Inventory.GetItemCount(itemList[i].ToString()) + ")";
            }
        }

        int howMany = itemList.Count;

        lastItemAdded = itemList[howMany - 1];

        Debug.Log(lastItemAdded);

    }


    private bool isThirdPerson = false;

    // Camera position to set when moving into third-person mode
    Vector3 thirdPerson = new Vector3(0f, 2.5f, -5f);

    [SerializeField]
    private Text timeInto;

    [SerializeField]
    private Text jumpText;

    public bool isOpen = false;

    public void Open()
    {
        setInventory();
        isOpen = true;
        Cursor.visible = true;
        gameObject.SetActive(true);
    }

    public void Close()
    {
        isOpen = false;
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

    public void OnJumpValue(float jump)
    {
        Messenger<float>.Broadcast(GameEvent.JJUMP_CHANGED, jump);
        jumpText.text = "Jump Height: " + jump.ToString();
    }

    public void OnSpeedValue(float speed)
    {
        Messenger<float>.Broadcast(GameEvent.JSPEED_CHANGED, speed);
    }

    public void ShiftPerspective()
    {
        OrbitCamera oc = shiftCamera.GetComponent<OrbitCamera>();

        if (!isThirdPerson)
        {
            //Debug.Log("Shifted to third person");
            oc.enabled = true;
            shiftCamera.transform.position = player.transform.position + thirdPerson;
            //shiftCamera.transform.rotation = Quaternion.Euler(0, 0, 0)*Quaternion.Euler(0,0,0);
            isThirdPerson = true;
            player.GetComponent<RelativeMovement>().enabled = true;
            player.GetComponent<FPSController>().enabled = false;

            // TODO - get crosshair to deactivate on third person mode
            //crosshair.SetActive(false);


        }
        else
        {
            //Debug.Log("Shifted to first person");
            oc.enabled = false;
            shiftCamera.transform.position = player.transform.position;
            isThirdPerson = false;
            player.GetComponent<RelativeMovement>().enabled = false;
            player.GetComponent<FPSController>().enabled = true;


            //crosshair.SetActive(true);

        }
    }

}
