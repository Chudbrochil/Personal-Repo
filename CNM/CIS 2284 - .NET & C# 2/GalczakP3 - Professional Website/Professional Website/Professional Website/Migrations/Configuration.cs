namespace Professional_Website.Migrations
{
    using Microsoft.AspNet.Identity;
    using Microsoft.AspNet.Identity.EntityFramework;
    using Models;
    using System;
    using System.Data.Entity;
    using System.Data.Entity.Migrations;
    using System.Linq;

    internal sealed class Configuration : DbMigrationsConfiguration<Professional_Website.Models.ApplicationDbContext>
    {
        public Configuration()
        {
            AutomaticMigrationsEnabled = false;
            ContextKey = "Professional_Website.Models.ApplicationDbContext";
        }

        protected override void Seed(Models.ApplicationDbContext context)

        {

            RoleManager<IdentityRole> roleManager = new RoleManager<IdentityRole>(new RoleStore<IdentityRole>(context));

            UserManager<ApplicationUser> userManager = new UserManager<ApplicationUser>(new UserStore<ApplicationUser>(context));



            if (!roleManager.RoleExists("Administrator"))

            {

                roleManager.Create(new IdentityRole("Administrator"));

            }


            // Adding a user right off the bat
            var user = new ApplicationUser { UserName = "bob", Email = "bob@gmail.com", EmailConfirmed = true };

            // If it found a user by name anthonygalczak@gmail.com then make them an admin
            if (userManager.FindByName("anthonygalczak@gmail.com") == null)

            {

                var result = userManager.Create(user, "Password1!");



                if (result.Succeeded)

                {

                    userManager.AddToRole(user.Id, "Administrator");

                }

            }

        }
    }
}
