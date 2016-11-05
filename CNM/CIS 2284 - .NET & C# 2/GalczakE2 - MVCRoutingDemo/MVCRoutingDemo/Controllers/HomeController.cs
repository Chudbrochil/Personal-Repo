// Anthony Galczak - agalczak@cnm.edu
// GalczakE2

using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace MVCRoutingDemo.Controllers
{
    public class HomeController : Controller
    {
        // GET: Home
        public String Index(String param)
        {
            return "Hello from Home Controller Index method! Parameter: " + param;
        }

        public String About()
        {
            return "About from Home Controller Index method!";
        }

        public String Greeting(String name, String last)
        {
            return HttpUtility.HtmlEncode("Hello, " + name + " " + last);
        }

        public string Greeting2(string id)
        {
            return HttpUtility.HtmlEncode("Hello, " + id);
        }

        public int Add(int x, int y)
        {
            return x + y;
        }

        public ActionResult ContentDemo()
        {
            return Content("Hello World", "text/plain");
        }

        public ActionResult JavaScriptDemo()
        {
            return JavaScript("<script>function foo{ alert('foo'); }</script>");
        }

        public ActionResult JsonDemo()
        {
            var m = new Menu
            {
                Id = 3,
                Text = "Grilled sausage with sauerkraut und potatoes",
                Price = 12.90,
                Category = "Main"
            };

            return Json(m, JsonRequestBehavior.AllowGet);
        }

        public ActionResult RedirectDemo()
        {
            return Redirect("http://www.cnm.edu");
        }

        public ActionResult FileDemo()
        {
            return File("~\\Images\\wood.jpg", "image/jpg");
        }





    }



}