namespace CodeFirstDemo.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class InitialCreate : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.Departments",
                c => new
                    {
                        DepartmentID = c.Int(nullable: false, identity: true),
                        DepartmentName = c.String(nullable: false, maxLength: 50),
                        DepartmentHeadID = c.Int(),
                    })
                .PrimaryKey(t => t.DepartmentID)
                .ForeignKey("dbo.Employees", t => t.DepartmentHeadID)
                .Index(t => t.DepartmentHeadID);
            
            CreateTable(
                "dbo.Employees",
                c => new
                    {
                        EmployeeID = c.Int(nullable: false, identity: true),
                        Name = c.String(nullable: false, maxLength: 50),
                        DepartmentID = c.Int(),
                        SupervisorID = c.Int(),
                        Supervisor_EmployeeID = c.Int(),
                        Department_DepartmentID = c.Int(),
                    })
                .PrimaryKey(t => t.EmployeeID)
                .ForeignKey("dbo.Departments", t => t.DepartmentID)
                .ForeignKey("dbo.Employees", t => t.Supervisor_EmployeeID)
                .ForeignKey("dbo.Departments", t => t.Department_DepartmentID)
                .Index(t => t.DepartmentID)
                .Index(t => t.Supervisor_EmployeeID)
                .Index(t => t.Department_DepartmentID);
            
        }
        
        public override void Down()
        {
            DropForeignKey("dbo.Employees", "Department_DepartmentID", "dbo.Departments");
            DropForeignKey("dbo.Departments", "DepartmentHeadID", "dbo.Employees");
            DropForeignKey("dbo.Employees", "Supervisor_EmployeeID", "dbo.Employees");
            DropForeignKey("dbo.Employees", "DepartmentID", "dbo.Departments");
            DropIndex("dbo.Employees", new[] { "Department_DepartmentID" });
            DropIndex("dbo.Employees", new[] { "Supervisor_EmployeeID" });
            DropIndex("dbo.Employees", new[] { "DepartmentID" });
            DropIndex("dbo.Departments", new[] { "DepartmentHeadID" });
            DropTable("dbo.Employees");
            DropTable("dbo.Departments");
        }
    }
}
