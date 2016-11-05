using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace WebFormDemo
{
    public partial class ShowMeetingRooms : System.Web.UI.Page
    {
        public string SelectedMeetingRoom
        {
            get { return ddlRooms.SelectedItem.Text; }
        }
        protected void Page_Load(object sender, EventArgs e)
        {
            
        }

        protected void ddlRooms_SelectedIndexChanged(object sender, EventArgs e)
        {
            lblResults.Text = ddlRooms.SelectedItem.Text;
        }

        protected void btnClick_Click(object sender, EventArgs e)
        {

        }
    }
}