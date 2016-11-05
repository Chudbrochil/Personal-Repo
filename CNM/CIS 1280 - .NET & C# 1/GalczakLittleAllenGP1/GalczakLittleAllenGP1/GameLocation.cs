//Alexander Little - alittle5@cnm.edu
//CIS 1280 - Robert Garner
//Program 5 - Cowboys & Aliens
//GameLocation.cs
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GalczakLittleAllenGP1
{
    // Alex's territory
    public class GameLocation
    {
        // describes the location setting, displayed in the description textbox
        private string locationDesc;

        public string LocationDesc
        {
            get { return locationDesc; }
            set { locationDesc = value; }
        }
        // TravelOptions property is a list of TravelOption objects
        public List<TravelOption> travelOptions;

        public List<TravelOption> TravelOptions
        {
            get { return travelOptions; }
            set { travelOptions = value; }
        }

        public GameLocation(string locationString)
        {
            locationDesc = locationString;

            travelOptions = new List<TravelOption>();
        }

        public override string ToString()
        {
            return locationDesc;
        }


    }
}


        //// private 
        ////describes the location setting, displayed in the description textbox
        //private string LocationDesc; //TODO: check if you need static, abstract, etc. -AL
        ////list of travelOption objects
       // private List<TravelOption> travelList = new List<TravelOption>(); //TODO: check after travel option exists -AL
        ////returns the LocationDesc property
       // public string ToString(); //TODO: double check this for accurate invoking -AL
        ////takes a string that will be used to initialize description
        //GameLocation(string locationString)
       // {
            
        //}








 //// private 
 //       //describes the location setting, displayed in the description textbox
 //       ///////private string LocationDesc; //TODO: check if you need static, abstract, etc. -AL
 //       private string LocationDesc;

 //       public string GameLocation
 //       {
 //           get { return LocationDesc; }
 //           set { LocationDesc = value; }
 //       }

 //       //private int myVar;

 //       //public int MyProperty
 //       //{
 //       //    get { return myVar; }
 //       //    set { myVar = value; }
 //       //}


 //       //list of travelOption objects
 //       private List<TravelOption> travelList = new List<TravelOption>(); //TODO: check after travel option exists -AL
 //       //returns the LocationDesc property
 //       public string ToString() //TODO: double check this for accurate invoking -AL
 //       {
 //           string locationString = LocationDesc;
 //           return locationString;
 //       }
 //       //takes a string that will be used to initialize description
 //       //GameLocation(string locationString)
 //       //{

 //       //}