// Anthony Galczak - agalczak@cnm.edu
// Program 5 - Snowboard Database

using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.SqlClient;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace GalczakP5_2
{
    public partial class OrderForm : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        // If the user selects include bindings...
        protected void cbBindings_CheckedChanged(object sender, EventArgs e)
        {
            if(cbBindings.Checked)
            {
                rblBindings.Visible = true;
            }
            else
            {
                rblBindings.Visible = false;
            }
        }

        protected void btnUpload_Click(object sender, EventArgs e)
        {
            if(fupImage.HasFile)
            {
                string fileName = Path.GetFileName(fupImage.PostedFile.FileName);
                fupImage.SaveAs(Server.MapPath("Images/" + fileName));
                this.imgSnowboard.ImageUrl = "Images/" + fileName;
            }
        }

        // Creating a new snowboard order
        protected void btnSubmit_Click(object sender, EventArgs e)
        {
            // Saving the datbase values from the order form
            bool isRegFooting = cbRegFoot.Checked;
            bool isBinding = cbBindings.Checked;
            Double length = Double.Parse(tbLength.Text);
            Double width = Double.Parse(tbWidth.Text);
            string bindingOption = rblBindings.SelectedValue;
            string imgFileName = imgSnowboard.ImageUrl;


            //Inserts the FirstName variable into the db-table
            SqlConnection conn = new SqlConnection(ConfigurationManager.ConnectionStrings["ConnectionString"].ToString());
            SqlCommand cmd = new SqlCommand("INSERT INTO SNOWBOARDS(BindingOption,ImageFileName,IncludeBindings,IsRegularFoot,Length,Width) VALUES(@BindingOption,@ImageFileName,@IncludeBindings,@IsRegularFoot,@Length,@Width)");

            //Uses the FirstName variable and creates a new sql variable for use in the sql commandtext
            cmd.Parameters.AddWithValue("@BindingOption", bindingOption);
            cmd.Parameters.AddWithValue("@ImageFileName", imgFileName);
            cmd.Parameters.AddWithValue("@IncludeBindings", isBinding);
            cmd.Parameters.AddWithValue("@IsRegularFoot", isRegFooting);
            cmd.Parameters.AddWithValue("@Length", length);
            cmd.Parameters.AddWithValue("@Width", width);

            cmd.Connection = conn;

            conn.Open();

            cmd.ExecuteNonQuery();

            conn.Close();


            Response.Redirect("OrderList.aspx");
        }
    }
}