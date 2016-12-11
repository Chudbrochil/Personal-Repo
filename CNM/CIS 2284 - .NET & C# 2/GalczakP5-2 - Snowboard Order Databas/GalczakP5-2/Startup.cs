using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(GalczakP5_2.Startup))]
namespace GalczakP5_2
{
    public partial class Startup {
        public void Configuration(IAppBuilder app) {
            ConfigureAuth(app);
        }
    }
}
