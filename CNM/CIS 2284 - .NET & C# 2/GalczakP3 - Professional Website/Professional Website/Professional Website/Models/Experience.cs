using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Professional_Website.Models
{
    public class Experience
    {
        private String description;

        public String Description
        {
            get { return description; }
            set { description = value; }
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

        private int experienceID;

        public int ExperienceID
        {
            get { return experienceID; }
            set { experienceID = value; }
        }

        private String title;

        public String Title
        {
            get { return title; }
            set { title = value; }
        }


    }
}