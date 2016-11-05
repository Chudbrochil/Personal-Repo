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

    string lastItemAdded;

    // TODO-Both dropItem and useItem are a bit clunky and use string comparison for the inventory, with time this could be improved
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
        // Initializing the current inventory and enabling the cursor
        setInventory();
        isOpen = true;
        Cursor.lockState = CursorLockMode.None; 
        Cursor.visible = true;
        gameObject.SetActive(true);
    }

    public void Close()
    {
        // Disabling the cursor and closing the GUI
        isOpen = false;
        Cursor.visible = false;
        Cursor.lockState = CursorLockMode.Locked;
        gameObject.SetActive(false);
    }

    public void Update()
    {
        // Displaying the time elapsed in the gametime within the GUI
        timeInto.text = Time.realtimeSinceStartup.ToString();
    }

    // When quitting game the game will save your location in the game plane
    public void QuitGame()
    {
        // Saving players position to be loaded when game starts
        Vector3 playersPosition = new Vector3();
        playersPosition = player.transform.position;

        // Two separate sets of player preference coords as the levels coordinates do not coincide
        if(Application.loadedLevelName == "Tutorial")
        {
            PlayerPrefs.SetFloat("PlayerX-Tut", playersPosition.x);
            PlayerPrefs.SetFloat("PlayerY-Tut", playersPosition.y);
            PlayerPrefs.SetFloat("PlayerZ-Tut", playersPosition.z);
        }
        else if(Application.loadedLevelName == "Outside")
        {
            PlayerPrefs.SetFloat("PlayerX-Out", playersPosition.x);
            PlayerPrefs.SetFloat("PlayerY-Out", playersPosition.y);
            PlayerPrefs.SetFloat("PlayerZ-Out", playersPosition.z);
        }

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
            // Switching to third person mode, this will disable/enable the appropriate scripts
            oc.enabled = true;
            shiftCamera.transform.position = player.transform.position + thirdPerson;
            isThirdPerson = true;
            player.GetComponent<RelativeMovement>().enabled = true;
            player.GetComponent<FPSController>().enabled = false;

            // TODO - get crosshair to deactivate on third person mode
            //crosshair.SetActive(false);


        }
        else
        {
            // Switching to first person mode, this will disable/enable the appropriate scripts
            oc.enabled = false;
            shiftCamera.transform.position = player.transform.position;
            isThirdPerson = false;
            player.GetComponent<RelativeMovement>().enabled = false;
            player.GetComponent<FPSController>().enabled = true;


            //crosshair.SetActive(true);

        }
    }


}
