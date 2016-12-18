// Anthony Galczak - agalczak@cnm.edu
// Program 3 - Professional Website

using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Professional_Website.Models
{
    public class Skill
    {
        private String description;

        public String Description
        {
            get { return description; }
            set { description = value; }
        }

        private int skillID;

        public int SkillID
        {
            get { return skillID; }
            set { skillID = value; }
        }

        private String title;

        public String Title
        {
            get { return title; }
            set { title = value; }
        }



    }
}