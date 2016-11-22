using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace DatabaseDemo
{
    public partial class DataAdapterDemo : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!Page.IsPostBack)
            {
                SqlConnection Conn = new SqlConnection("Data Source = (LocalDB)\\MSSQLLocalDB; AttachDbFilename = \"C:\\Users\\anthony\\Documents\\~Docs\\ws\\VS2015\\Projects\\CIS 2284\\DatabaseDemo\\DatabaseDemo\\App_Data\\DemoDB.mdf\"; Integrated Security = True");
                SqlCommand cmd = new SqlCommand("SELECT * FROM Department", Conn);
                SqlDataAdapter da = new SqlDataAdapter(cmd);
                DataSet ds = new DataSet();
                da.Fill(ds);
                DropDownListDepartments.DataSource = ds;
                DropDownListDepartments.DataBind();
            }

        }

        protected void DropDownListDepartments_SelectedIndexChanged(object sender, EventArgs e)
        {
            LabelDDLResults.Text = "<br>You selected department " + DropDownListDepartments.SelectedValue.ToString() +
            " which is the " + DropDownListDepartments.SelectedItem.Text +
            " department!";
            foreach (ListItem item in DropDownListDepartments.Items)
            {
                LabelDDLResults.Text += "<br/> Item " + item.Value + " is " + item.Text;
            }
            LabelDDLResults.Text +=
        "<br/><br/> Selected item index is " + DropDownListDepartments.SelectedIndex + " which is the " + DropDownListDepartments.Items[DropDownListDepartments.SelectedIndex].Text +
        " department";
        }

    }
}