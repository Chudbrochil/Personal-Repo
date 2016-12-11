namespace SportsStatistics3.Migrations
{
    using Microsoft.AspNet.Identity;
    using Microsoft.AspNet.Identity.EntityFramework;
    using Models;
    using PayPal.Api;
    using System;
    using System.Collections.Generic;
    using System.Data.Entity;
    using System.Data.Entity.Migrations;
    using System.Linq;

    internal sealed class Configuration : DbMigrationsConfiguration<SportsStatistics3.Models.ApplicationDbContext>
    {
        //these variables will store the clientID and clientSecret
        //by reading them from the web.config
        public readonly static string ClientId;
        public readonly static string ClientSecret;

        static Configuration()
        {
            var config = GetConfig();
            ClientId = config["clientId"];
            ClientSecret = config["clientSecret"];
        }

        // getting properties from the web.config
        public static Dictionary<string, string> GetConfig()
        {
            return ConfigManager.Instance.GetProperties();
        }

        private static string GetAccessToken()
        {
            // getting accesstocken from paypal                
            string accessToken = new OAuthTokenCredential
        (ClientId, ClientSecret, GetConfig()).GetAccessToken();

            return accessToken;
        }

        public static APIContext GetAPIContext()
        {
            // return apicontext object by invoking it with the accesstoken
            APIContext apiContext = new APIContext(GetAccessToken());
            apiContext.Config = GetConfig();
            return apiContext;
        }

        protected override void Seed(SportsStatistics3.Models.ApplicationDbContext context)
        {
            RoleManager<IdentityRole> roleManager = new RoleManager<IdentityRole>(new RoleStore<IdentityRole>(context));
            UserManager<ApplicationUser> userManager = new UserManager<ApplicationUser>(new UserStore<ApplicationUser>(context));

            if (!roleManager.RoleExists("Administrator"))
            {
                roleManager.Create(new IdentityRole("Administrator"));
            }

            var user = new ApplicationUser { UserName = "test@cnm.edu", Email = "test@cnm.edu", EmailConfirmed = true };


            if (userManager.FindByName("test@cnm.edu") == null)
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
