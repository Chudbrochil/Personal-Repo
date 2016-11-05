using ClientDataDemo.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace ClientDataDemo.Controllers
{
    public class SubmitDataController : Controller
    {
        // GET: SubmitData
        public ActionResult Index()
        {
            return View();
        }

        public ActionResult CreateMenu()
        {
            return View();
        }


        //POST: SubmitData
        [HttpPost]
        public ActionResult CreateMenu(Menu m)
        {
            if (ModelState.IsValid)
            {
                ViewBag.Info = string.Format(
                    "Menu created: {0}, Price: {1}, Category {2}", m.Text, m.Price, m.Category);
            }
            else
            {
                ViewBag.Info = "not valid";
            }
            return View("Index");
        }



    }
}