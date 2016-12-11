using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(SportsStatistics3.Startup))]
namespace SportsStatistics3
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}
