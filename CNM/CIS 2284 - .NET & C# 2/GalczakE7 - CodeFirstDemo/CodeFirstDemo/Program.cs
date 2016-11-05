using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CodeFirstDemo
{
    class Program
    {
        static void Main(string[] args)
        {
            using (var db = new DemoDBContext())
            {
                //Create a new employee
                Console.Write("Please enter name of new employee: ");
                var name = Console.ReadLine();

                var employee = new Employee { Name = name };
                db.Employees.Add(employee);
                db.SaveChanges();
                DisplayEmployees(db);

                //Ask user for name of an employee to modify
                Console.Write("Enter ID of employee to change name: ");
                int empID = int.Parse(Console.ReadLine());
                Console.Write("Enter new name: ");
                string newName = Console.ReadLine();


                //Update employee:
                Employee empToChange = (from e in db.Employees
                                        where e.EmployeeID == empID
                                        select e).First();
                empToChange.Name = newName;
                DisplayEmployees(db);


                //Ask user for id of an employee to delete
                Console.Write("Enter ID of employee to remove: ");
                int delID = int.Parse(Console.ReadLine());

                //Delete employee
                Employee empToRemove = (from e in db.Employees
                                        where e.EmployeeID == delID
                                        select e).First();

                if (empToRemove != null)
                {
                    db.Employees.Remove(empToRemove);
                    db.SaveChanges();
                }


                //Display employees
                DisplayEmployees(db);


            }
        }

        private static void DisplayEmployees(DemoDBContext db)
        {
            //Display all employees
            var query = from e in db.Employees
                        orderby e.EmployeeID
                        select e;

            Console.WriteLine("All employees in the database: ");
            foreach (var item in query)
            {
                Console.WriteLine(item.EmployeeID + " " + item.Name);
            }
        }

    }
}
