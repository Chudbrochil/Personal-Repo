// Anthony Galczak - agalczak@cnm.edu
// CIS 2284 - Program 4 - Depreciation on a ASP.NET Web Form

using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Microsoft.AspNet.SignalR;

namespace GalczakP4
{
    public class ChatHub : Hub
    {
        public void Send(string name, string message)
        {
            // Call the broadcastMessage method to update clients.
            Clients.All.broadcastMessage(name, message);
        }
    }
}