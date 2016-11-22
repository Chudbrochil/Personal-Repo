using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace AJAXDemo
{
    public partial class NormalPage : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            LabelPostBack.Text = "Post back at " + DateTime.Now.ToString();
        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            LabelButton1.Text = "Button1 click at " + DateTime.Now.ToString();
        }

        protected void Button2_Click(object sender, EventArgs e)
        {
            LabelButton2.Text = "Button2 click at " + DateTime.Now.ToString();
        }
    }
}