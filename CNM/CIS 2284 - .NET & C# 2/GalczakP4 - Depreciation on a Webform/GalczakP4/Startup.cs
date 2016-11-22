// Anthony Galczak - agalczak@cnm.edu
// CIS 2284 - Program 4 - Depreciation on a ASP.NET Web Form

using System;
using System.Threading.Tasks;
using Microsoft.Owin;
using Owin;

[assembly: OwinStartup(typeof(GalczakP4.Startup))]

namespace GalczakP4
{
    public class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            app.MapSignalR();
        }
    }
}
