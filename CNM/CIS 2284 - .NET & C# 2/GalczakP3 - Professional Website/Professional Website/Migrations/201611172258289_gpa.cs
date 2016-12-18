namespace Professional_Website.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class gpa : DbMigration
    {
        public override void Up()
        {
            AddColumn("dbo.Educations", "ProgramGpa", c => c.Single(nullable: false));
            AddColumn("dbo.Educations", "CumulGpa", c => c.Single(nullable: false));
            DropColumn("dbo.Educations", "Gpa");
        }
        
        public override void Down()
        {
            AddColumn("dbo.Educations", "Gpa", c => c.Single(nullable: false));
            DropColumn("dbo.Educations", "CumulGpa");
            DropColumn("dbo.Educations", "ProgramGpa");
        }
    }
}
