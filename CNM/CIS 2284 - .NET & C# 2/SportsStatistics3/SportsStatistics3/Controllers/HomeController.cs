using SportsStatistics3.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace SportsStatistics3.Controllers
{
    public class HomeController : Controller
    {
        ApplicationDbContext db = new ApplicationDbContext();

        public ActionResult Index()
        {
            return View();
        }

        public ActionResult About()
        {
            ViewBag.Message = "Your application description page.";

            return View();
        }

        public ActionResult League()
        {
            League league = new League();

            league.InitialFee = db.Leagues.ToList()[0].InitialFee;
            league.CommissionerName = db.Leagues.ToList()[0].CommissionerName;
            league.Rules = db.Leagues.ToList()[0].Rules;

            return View(league);
        }

        public ActionResult Team()
        {
            Team team = new Models.Team();
            League league = new League();

            foreach(var m in db.Users.ToList())
            {
                foreach(var t in league.Teams)
                {
                    if(m.Email == t.Email)
                    {
                        return View(t);
                    }
                }
            }

            // TODO: This should go to ~/Views/Teams/Create.cshtml
            return View(team);
        }


    }
}