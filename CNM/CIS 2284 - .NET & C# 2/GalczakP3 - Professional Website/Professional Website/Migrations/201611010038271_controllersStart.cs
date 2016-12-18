namespace Professional_Website.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class controllersStart : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.ContactInfoes",
                c => new
                    {
                        ContactInfoID = c.Int(nullable: false, identity: true),
                        Comments = c.String(),
                        Email = c.String(),
                        FirstName = c.String(),
                        LastName = c.String(),
                        IsPhoneApp = c.Boolean(nullable: false),
                        IsWebApp = c.Boolean(nullable: false),
                        IsWindowsApp = c.Boolean(nullable: false),
                        TypeOfRequest = c.String(),
                    })
                .PrimaryKey(t => t.ContactInfoID);
            
            CreateTable(
                "dbo.Educations",
                c => new
                    {
                        EducationID = c.Int(nullable: false, identity: true),
                        Degree = c.String(),
                        School = c.String(),
                        Start = c.DateTime(nullable: false),
                        End = c.DateTime(nullable: false),
                    })
                .PrimaryKey(t => t.EducationID);
            
            CreateTable(
                "dbo.Experiences",
                c => new
                    {
                        ExperienceID = c.Int(nullable: false, identity: true),
                        Description = c.String(),
                        Start = c.DateTime(nullable: false),
                        End = c.DateTime(nullable: false),
                        Title = c.String(),
                    })
                .PrimaryKey(t => t.ExperienceID);
            
            CreateTable(
                "dbo.Skills",
                c => new
                    {
                        SkillID = c.Int(nullable: false, identity: true),
                        Description = c.String(),
                        Title = c.String(),
                    })
                .PrimaryKey(t => t.SkillID);
            
        }
        
        public override void Down()
        {
            DropTable("dbo.Skills");
            DropTable("dbo.Experiences");
            DropTable("dbo.Educations");
            DropTable("dbo.ContactInfoes");
        }
    }
}
