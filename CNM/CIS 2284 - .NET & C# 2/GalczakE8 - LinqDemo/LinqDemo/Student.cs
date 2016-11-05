using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LinqDemo
{
    [Serializable]
    class Student
    {
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public string StudentNumber { get; set; }
        public string Major { get; set; }
        public int Average { get; private set; }
        private List<int> scores;

        public List<int> Scores
        {
            get { return scores; }
            set { scores = value; Calculate(); }
        }

        private void Calculate()
        {
            int total = 0;
            foreach(int score in scores)
            {
                total += score;
            }
            Average = total / scores.Count;
        }
        

        public override string ToString()
        {
            return
                FirstName
                + " " + LastName
                + " Student Num: " + StudentNumber
                + " Major: " + Major
                + " Average: " + Average;
        }
    }
}
