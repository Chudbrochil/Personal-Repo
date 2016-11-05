using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LinqDemo
{
    class Program
    {
        static void Main(string[] args)
        {
            Cohort cohort = GetStudents();

            List<Student> honorsStudents =
                cohort.Students.Where(s => s.Average > 95).ToList<Student>();

            foreach (var s in honorsStudents)
            {
                Console.WriteLine(s.ToString());
            }
        }




        static Cohort GetStudents()
        {
            Cohort cohort = new Cohort();
            cohort.Students.Add(new Student { FirstName = "Ada", LastName = "Lovelace", Major = "Mathmatics", Scores = new List<int>{ 100, 100, 100 }, StudentNumber = "1111" });
            cohort.Students.Add(new Student { FirstName = "Bjarney", LastName = "Stroustrup", Major = "Computer Science", Scores = new List<int> { 100, 90, 95 }, StudentNumber = "2222" });
            cohort.Students.Add(new Student { FirstName = "Mark", LastName = "Zukerberg", Major = "Psychology", Scores = new List<int> { 100, 98, 96 }, StudentNumber = "3333" });
            cohort.Students.Add(new Student { FirstName = "Steve", LastName = "Jobs", Major = "Caligraphy", Scores = new List<int> { 80, 100, 90 }, StudentNumber = "4444" });
            cohort.Students.Add(new Student { FirstName = "Bill", LastName = "Gates", Major = "Computer Science", Scores = new List<int> { 90, 95, 100 }, StudentNumber = "5555" });

            return cohort;
        }
    }
}
