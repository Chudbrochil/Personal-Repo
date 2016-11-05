using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace CodeFirstDemo
{
    public class Employee
    {
        public int EmployeeID { get; set; }

        [Required]
        [StringLength(50)]
        public string Name { get; set; }

        public int? DepartmentID { get; set; }
        [ForeignKey("DepartmentID")]
        public virtual Department Department { get; set; }
        
        public int? SupervisorID { get; set; }
        public virtual Employee Supervisor { get; set; }

        [DataType(DataType.Date)]
        public DateTime? HireDate { get; set; }

    }

}