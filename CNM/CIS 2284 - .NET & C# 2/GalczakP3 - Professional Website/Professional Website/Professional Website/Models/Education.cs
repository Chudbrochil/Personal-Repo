using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Professional_Website.Models
{
    public class Education
    {
        private String degree;

        public String Degree
        {
            get { return degree; }
            set { degree = value; }
        }

        private int educationID;

        public int EducationID
        {
            get { return educationID; }
            set { educationID = value; }
        }

        private String school;

        public String School
        {
            get { return school; }
            set { school = value; }
        }

        private DateTime start;

        public DateTime Start
        {
            get { return start; }
            set { start = value; }
        }

        private DateTime end;

        public DateTime End
        {
            get { return end; }
            set { end = value; }
        }





    }
}