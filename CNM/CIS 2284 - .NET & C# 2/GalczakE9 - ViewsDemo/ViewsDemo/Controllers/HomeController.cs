using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using ViewsDemo.Models;

namespace ViewsDemo.Controllers
{
    public class HomeController : Controller
    {

        // GET: Home
        public ActionResult Index()
        {
            ViewBag.MyReallyCoolKey = "My really cool value!";
            //ViewData["FirstName"] = "Anthony";
            //ViewData["LastName"] = "Galczak";
            ViewBag.FirstName = "Anthony";
            ViewBag.LastName = "Galczak";
            return View();
        }


        public ActionResult PassingAModel()
        {
            var Contacts = new List<Contact>
            {
                new Contact {ContactID = 1, Name="Bjarney Stroustrup", Email="bjarney@aserver.net", Phone="555-1212"},
                new Contact {ContactID = 2, Name="Ada Lovelace", Email="ada@aserver.net", Phone="555-1213"},
                new Contact {ContactID = 3, Name="Alan Turing", Email="alan@aserver.net", Phone="555-1214"}
            };
            return View(Contacts);
        }

        public ActionResult LayoutPageDemo()
        {
            return View();
        }


    }
}