using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace ValidatedControlsDemo
{
    public partial class SurveyPage1 : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            RangeValidator2.MinimumValue = DateTime.Now.ToShortDateString();
            RangeValidator2.MaximumValue = DateTime.Now.AddDays(14).ToShortDateString();
        }

        protected void Calendar1_SelectionChanged(object sender, EventArgs e)
        {
            TextBox4.Text = Calendar1.SelectedDate.ToShortDateString();
        }
    }
}