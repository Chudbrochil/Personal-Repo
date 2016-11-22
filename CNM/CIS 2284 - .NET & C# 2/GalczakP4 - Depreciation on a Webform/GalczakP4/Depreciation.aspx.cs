// Anthony Galczak - agalczak@cnm.edu
// CIS 2284 - Program 4 - Depreciation on a ASP.NET Web Form

using GalczakP4.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace GalczakP4
{
    public partial class Depreciation : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void btnSubmit_Click(object sender, EventArgs e)
        {
            DepreciationBase deprec;

            // Selecting the type of child class to instantiate based on
            // which radio button is selected
            if (rblDepreciation.SelectedItem.Text == "Double Declining")
            {
                deprec = new DepreciationDoubleDeclining();
            }
            else
            {
                deprec = new DepreciationStraightLine();
            }

            // Setting values from the TextBoxes
            deprec.StartingValue = Decimal.Parse(tbStarting.Text);
            deprec.SalvageValue = Decimal.Parse(tbSalvage.Text);
            deprec.Lifetime = int.Parse(tbLifetime.Text);
            deprec.Age = int.Parse(tbAge.Text);

            // Validating that the user didn't leave any fields blank
            if (deprec.StartingValue == 0 || deprec.SalvageValue == 0 ||
                deprec.Lifetime == 0 || deprec.Age == 0)
            {
                tbResult.Text = "You must fill in all the fields. Try again.";
            }
            else
            {
                // Calculate the depreciation
                deprec.Calc();

                // Output the depreciation string to the user
                tbResult.Text = deprec.ToString();
            }
        }
    }
}