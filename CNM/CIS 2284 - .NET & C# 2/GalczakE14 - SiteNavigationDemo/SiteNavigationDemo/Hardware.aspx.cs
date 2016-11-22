using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace SiteNavigationDemo
{
    public partial class Hardware : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            TreeView1.ShowCheckBoxes = TreeNodeTypes.Leaf;
        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            if (TreeView1.CheckedNodes.Count > 0)
            {
                Label1.Text = "We are sending you information on:<p>";
                foreach (TreeNode node in TreeView1.CheckedNodes)
                {
                    Label1.Text += node.Text + " " + node.Parent.Text + "<br>";
                }
            }
            else
            {
                Label1.Text = "You didn't select anything. Sorry.";
            }

        }
    }
}