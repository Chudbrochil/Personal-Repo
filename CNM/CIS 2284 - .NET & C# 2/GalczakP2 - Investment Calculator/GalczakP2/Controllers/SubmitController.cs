//TODO: -3 no comment header. RJG
using GalczakP2.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace GalczakP2.Controllers
{
    public class SubmitController : Controller
    {
        // GET: Submit
        public ActionResult Index()
        {
            return View();
        }

        public ActionResult SubmitInvestment()
        {
            return View();
        }

        // Submitting the values of home/index to an ic object and then re-displaying in the html
        [HttpPost]
        public ActionResult SubmitInvestment(InvestmentCalc ic)
        {
            if (ModelState.IsValid)
            {
                //TODO: -5 do not use ViewBag for this. That is what the model if for. Pass the model to the view instead. RJG
                // Individually assigning each variable in the viewbag as a string to output in the HTML
                ViewBag.Principal = string.Format("Initial principal on Investment is {0:C}", ic.Principal);
                ViewBag.Years = string.Format("Years of Investment is {0}", ic.Years);
                ViewBag.Interest = string.Format("Interest on Investment is {0:P}", ic.Interest);
                ViewBag.CmpPerYr = string.Format("Compounds per Year is {0}", ic.CmpPerYr);
                ViewBag.FutureValue = string.Format("Future value of Investment is {0:C}",ic.FutureValue);
            }
            else
            {
                ViewBag.Info = "Invalid input given, try again";
            }
            //TODO: return View(ic) instead here. RJG
            return View("Index");
        }


    }
}