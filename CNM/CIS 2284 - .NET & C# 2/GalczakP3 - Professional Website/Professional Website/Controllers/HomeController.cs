// Anthony Galczak - agalczak@cnm.edu
// Program 3 - Professional Website

using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Professional_Website.Controllers
{
    public class HomeController : Controller
    {
        public ActionResult Index()
        {
            return View();
        }

        public ActionResult About()
        {
            return View();
        }

        //TODO: -10 need to implement contact page ActionResult methods
        public ActionResult Contact()
        {
            ViewBag.Message = "Contact";

            return View();
        }
    }
}