using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Professional_Website.Models
{
    public class ResumeViewModel
    {
        public ApplicationDbContext db;

        private List<Education> educationItems;

        public List<Education> EducationItems
        {
            get { return educationItems; }
            set { educationItems = value; }
        }

        private List<Experience> experienceItems;

        public List<Experience> ExperienceItems
        {
            get { return experienceItems; }
            set { experienceItems = value; }
        }

        private String preamble;

        public String Preamble
        {
            get { return preamble; }
            set { preamble = value; }
        }

        private List<Skill> skillItems;

        public List<Skill> SkillItems
        {
            get { return skillItems; }
            set { skillItems = value; }
        }

        public ResumeViewModel()
        {
            Preamble = "Hire Me!";
            db = new ApplicationDbContext();
            SkillItems = db.Skills.ToList();
            EducationItems = db.Educations.ToList();
            ExperienceItems = db.Experiences.ToList();

        }



    }
}