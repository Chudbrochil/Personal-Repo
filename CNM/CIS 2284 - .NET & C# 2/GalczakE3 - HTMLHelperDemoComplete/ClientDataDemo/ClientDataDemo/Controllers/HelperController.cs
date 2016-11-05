using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using ClientDataDemo.Models;
namespace ClientDataDemo.Controllers
{
    public class HelperController : Controller
    {
        // GET: SimpleHelper
        public ActionResult Index()
        {
            return View();
        }

        //POST: SubmitData
        [HttpPost]
        public ActionResult SubmitData(Menu m)
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

        public ActionResult SimpleHelper()
        {
            return View();
        }

        public ActionResult HelperWithMenu()
        {
            var menu = new Menu 
            {
                Id = 1, 
                Text = "Schweinsbraten mit Knödel und Sauerkraut", 
                Price = 6.9, 
                Date = new DateTime(2012, 10, 5), 
                Category = "Main"
            };
            return View(menu); 
        }

        public ActionResult HelperWithAttributes()
        {
            return View();
        }

        public ActionResult StronglyTypedMenu() 
        {
            var menu = new Menu 
            {
                Id = 1, 
                Text = "Schweinsbraten mit Knödel und Sauerkraut", 
                Price = 6.9, 
                Date = new DateTime(2013, 10, 5), 
                Category = "Main" 
            }; 
            return View(menu); 
        }

        public ActionResult EditorExtMenu()
        {
            var menu = new Menu
            {
                Id = 1,
                Text = "Schweinsbraten mit Knödel und Sauerkraut",
                Price = 6.9,
                Date = new DateTime(2013, 10, 5),
                Category = "Main"
            };
            return View(menu);
        }

    }
}