// Anthony Galczak - agalczak@cnm.edu
// Program 3 - Professional Website

using Professional_Website.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Professional_Website.Controllers
{
    public class ResumeController : Controller
    {    
        // GET: Resume
        public ActionResult Index()
        {
            ResumeViewModel resume = new ResumeViewModel();
            return View(resume);
        }
    }
}