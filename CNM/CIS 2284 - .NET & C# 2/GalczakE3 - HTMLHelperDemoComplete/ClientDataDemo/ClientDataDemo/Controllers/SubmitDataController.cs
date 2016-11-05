using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using ClientDataDemo.Models;
using System.Data.Entity;

namespace ClientDataDemo.Controllers
{
    public class SubmitDataController : Controller
    {
        private MenuDB db = new MenuDB();

        public ActionResult Index()
        {
            return View(db.Menus.ToList());
        }

        
        // GET: SubmitData
        public ActionResult CreateMenu()
        {
            return View();
        }

        //POST: SubmitData
        [HttpPost]
        public ActionResult CreateMenu(Menu m)
        {
            MenuDB db = new MenuDB();
            m.Date = DateTime.Now;
            db.Menus.Add(m);
            db.SaveChanges();
            if (ModelState.IsValid)
            {
                ViewBag.Info = string.Format(
                    "Menu created: {0}, Price: {1}, Category {2}", m.Text, m.Price, m.Category);
            }
            else
            {
                ViewBag.Info = "not valid";
            }
            return View("Index", db.Menus.ToList());
        }

        public ActionResult Edit(int? id)
        {
            Menu menu = db.Menus.Find(id);
            if (menu == null)
            {
                return HttpNotFound();
            }
            return View(menu);
        }
        // POST: 
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "Id,Text,Price,Date,Category")] Menu menu)
        {
            if (ModelState.IsValid)
            {
                db.Entry(menu).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(menu);
        }

        public ActionResult Delete(int? id)
        {
            MenuDB db = new MenuDB();
            Menu menu = db.Menus.Find(id);
            db.Menus.Remove(menu);
            db.SaveChanges();
            ViewBag.Info = string.Format(
            "Menu item deleted: {0}", menu.Id);
            return View("Index", db.Menus.ToList());
        }




    }
}