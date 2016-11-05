namespace ClientDataDemo.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class MenusTable : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.Menus",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        Text = c.String(nullable: false, maxLength: 50),
                        Price = c.Double(nullable: false),
                        Date = c.DateTime(nullable: false),
                        Category = c.String(maxLength: 10),
                    })
                .PrimaryKey(t => t.Id);
            
        }
        
        public override void Down()
        {
            DropTable("dbo.Menus");
        }
    }
}
