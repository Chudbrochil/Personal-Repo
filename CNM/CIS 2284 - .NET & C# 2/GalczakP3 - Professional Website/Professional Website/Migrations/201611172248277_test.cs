namespace Professional_Website.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class test : DbMigration
    {
        public override void Up()
        {
            AddColumn("dbo.Educations", "Gpa", c => c.Single(nullable: false));
        }
        
        public override void Down()
        {
            DropColumn("dbo.Educations", "Gpa");
        }
    }
}
