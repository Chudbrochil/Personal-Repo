using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GalczakE7
{
    abstract class HousingBase
    {
        private string address;

        public string Address
        {
            get { return address; }
            set { address = value; }
        }

        private string builder;

        public string Builder
        {
            get { return builder; }
            set { builder = value; }
        }

        private string cleaningCrewNameNum;

        public string CleaningCrewNameNum
        {
            get { return cleaningCrewNameNum; }
            set { cleaningCrewNameNum = value; }
        }

        private string fireHistory;

        public string FireHistory
        {
            get { return fireHistory; }
            set { fireHistory = value; }
        }

        private string structureType;

        public string StructureType
        {
            get { return structureType; }
            set { structureType = value; }
        }

        private int yearBuilt;

        public int YearBuilt
        {
            get { return yearBuilt; }
            set { yearBuilt = value; }
        }

        // Methods

        // Regular constructor
        public HousingBase()
        {
        }

        // Overloaded constructor
        public HousingBase(string address, string builder, string cleaningCrewNameNum, string fireHistory, string structureType, int yearBuilt)
        {
            Address = address;
            Builder = builder;
            CleaningCrewNameNum = cleaningCrewNameNum;
            FireHistory = fireHistory;
            StructureType = structureType;
            YearBuilt = yearBuilt;
        }
        
        // Making this method virtual so that it can be overwritten in the child classes
        public virtual string GetHousingInfo()
        {
            // Outputting static declartions
            return "Address: " + Address + "\nBuilder: " + Builder + "\nCleaning Crew: " + CleaningCrewNameNum + 
                "\nFire History: " + FireHistory + "\nStructure Type: " + StructureType + "\nYear Built: " + YearBuilt;
        }

        // Abstract method can't have a body
        public abstract decimal TotalRent();
        
    }
}
