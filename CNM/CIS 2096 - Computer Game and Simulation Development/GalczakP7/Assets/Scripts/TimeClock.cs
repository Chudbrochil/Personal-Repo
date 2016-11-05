﻿using UnityEngine;
using System.Collections;
using System;

public class TimeClock : MonoBehaviour {

    private const float
        hoursToDegrees = 360f / 12f, minutesToDegrees = 360f / 60f, secondsToDegrees = 360f / 60f;


    public Transform secondsHand, minutesHand, hoursHand;

    void Update()
    {
        secondsHand = this.gameObject.transform.GetChild(0);
        minutesHand = this.gameObject.transform.GetChild(1);
        hoursHand = this.gameObject.transform.GetChild(2);


        DateTime time = DateTime.Now;
        secondsHand.localRotation = Quaternion.Euler(0f, (time.Second * secondsToDegrees) + 90, 270f );
        minutesHand.localRotation = Quaternion.Euler(0f, (time.Minute * minutesToDegrees) + 90, 270f);
        hoursHand.localRotation = Quaternion.Euler(0f, (time.Hour * hoursToDegrees) + 90, 270f);

    }
}
