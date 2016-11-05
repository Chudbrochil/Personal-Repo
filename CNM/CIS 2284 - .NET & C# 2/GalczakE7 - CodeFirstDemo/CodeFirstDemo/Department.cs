using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace CodeFirstDemo
{
public class Department
{
    public int DepartmentID { get; set; }

    [Required]
    [StringLength(50)]
    public string DepartmentName { get; set; }

    public int? DepartmentHeadID { get; set; }
    [ForeignKey("DepartmentHeadID")]
    public virtual Employee DepartmentHead { get; set; }

    public ICollection<Employee> Employees { get; set; }
}

}