using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(Professional_Website.Startup))]
namespace Professional_Website
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}
