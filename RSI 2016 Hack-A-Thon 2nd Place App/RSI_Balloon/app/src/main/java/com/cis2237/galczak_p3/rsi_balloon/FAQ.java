package com.cis2237.galczak_p3.rsi_balloon;

import java.util.ArrayList;

/**
 * Created by huynh on 9/30/2016.
 */
public class FAQ
{
    String question;
    String answer;

    public FAQ(){

    }

    public FAQ(String quest ,String answer)
    {
        this.question = quest;
        this.answer = answer;
    }
    public void setQuestion(String quest){
        this.question = quest;
    }
    public String getQuestion()
    {
        return question;
    }
    public void setAnswer()
    {
        this.answer = answer;
    }
    public String getAnswer()
    {
        return answer;
    }
    public ArrayList<FAQ> getList()
    {
        ArrayList<FAQ> FAQlist = new ArrayList<FAQ>();
        FAQlist.add(new FAQ("How long does it take to make a special shapes balloon?",
                "According to one manufacturer (Lindstrand Balloons USA) it takes 14-20 weeks after the design has been approved."));
        FAQlist.add(new FAQ(" What do you have to do to be a pilot?","On January 15, 1991, the Virgin Pacific Flyer balloon completed the " +
                "longest flight in a hot air balloon when Per Lindstrand and Richard Branson flew 7,671.91 km (4,767.10 mi) from Japan to Northern Canada."));
        FAQlist.add(new FAQ("What’s the highest flight?","On November 26, 2005 Vijaypat Singhania set the world altitude" +
                " record for highest hot air balloon flight, reaching 21,027 m (68,986 ft).\n"));
        FAQlist.add(new FAQ("What's the largest balloon every created?","With a volume of 74 thousand cubic meters (2.6 million cubic feet), th" +
                "e envelope of the Virgin Pacific Flyer balloon was the largest ever built for a hot air craft."
        ));
        FAQlist.add(new FAQ(" Why is ABQ such a good place for ballooning?",
                "Wind currents and air temperature are key to the successful flying of hot air balloons. Albuquerque has the perfect combination of these conditions."));
        FAQlist.add(new FAQ("Explain the ABQ box?", "The Albuquerque “box” is a set of very predictable wind patterns that can be used to navigate balloons. At low elevations the winds tend to be southerly, but " +
                "at higher elevations they tend to be northerly. Balloonists do not have a way to steer their hot air " +
                "balloons but they can.use these “box” winds to move away from the field and back again."));
        return FAQlist;

    }

}
