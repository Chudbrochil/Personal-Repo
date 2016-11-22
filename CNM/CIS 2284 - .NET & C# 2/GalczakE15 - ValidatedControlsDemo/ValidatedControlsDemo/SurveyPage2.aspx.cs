using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace ValidatedControlsDemo
{
    public partial class SurveyPage2 : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (PreviousPage != null && PreviousPage.IsCrossPagePostBack && PreviousPage.IsValid)
            {
                TextBox ppTextBox1 = (TextBox)PreviousPage.FindControl("TextBox1");
                Label1.Text = "Hello " + ppTextBox1.Text + "</br>";

            }
        }
    }
}