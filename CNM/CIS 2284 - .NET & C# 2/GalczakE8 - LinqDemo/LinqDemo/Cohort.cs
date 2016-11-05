using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LinqDemo
{
    [Serializable]
    class Cohort
    {
        public Cohort()
        {
            Students = new List<Student>();
        }
        public List<Student> Students { get; set; }
    }
}
