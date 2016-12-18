// Anthony Galczak - agalczak@cnm.edu
// Program 3 - Professional Website

using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using Professional_Website.Models;
using System.Net.Mail;
using System.Text;

namespace Professional_Website.Controllers
{
    public class ContactInfoesController : Controller
    {
        public ActionResult Index()
        {
            return View();
        }

        public ActionResult Contact()
        {
            return View();
        }

        [HttpPost]
        public ActionResult Contact(ContactInfo c)
        {

            if (ModelState.IsValid)
            {
                try
                {
                    MailMessage msg = new MailMessage();
                    SmtpClient client = new SmtpClient();
                    // We use gmail as our smtp client
                    client.Host = "smtp.gmail.com";
                    client.Port = 587;
                    client.EnableSsl = true;
                    client.DeliveryMethod = SmtpDeliveryMethod.Network;
                    client.Credentials = new System.Net.NetworkCredential("wgalczak@gmail.com", "PASSWORD HERE");
                    MailAddress from = new MailAddress(c.Email.ToString());
                    StringBuilder sb = new StringBuilder();
                    sb.Append("First name: " + c.FirstName);
                    sb.Append(Environment.NewLine);
                    sb.Append("Last name: " + c.LastName);
                    sb.Append(Environment.NewLine);
                    sb.Append("Email: " + c.Email);
                    sb.Append(Environment.NewLine);
                    sb.Append("Comments: " + c.Comments);
                    msg.From = from;
                    msg.To.Add("wgalczak@gmail.com");
                    msg.Subject = "Contact Us";
                    msg.IsBodyHtml = false;
                    msg.Body = sb.ToString();
                    client.Send(msg);
                    msg.Dispose();
                    return View("Success");
                }
                catch (Exception exc)
                {
                    return View("Error");
                }
            }
            return View();
        }



    }
}
