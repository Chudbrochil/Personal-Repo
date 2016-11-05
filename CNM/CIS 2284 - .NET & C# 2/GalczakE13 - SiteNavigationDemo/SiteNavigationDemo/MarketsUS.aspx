<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="MarketsUS.aspx.cs" Inherits="SiteNavigationDemo.MarketsUS" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
        <asp:SiteMapPath ID="SiteMapPath1" runat="server"  ParentLevelsDisplayed="2">
            <PathSeparatorTemplate> | </PathSeparatorTemplate>
            <PathSeparatorStyle Font-Bold="true" Font-Names="Verdana" ForeColor="#663333" BackColor="#cccc66"></PathSeparatorStyle> 
        </asp:SiteMapPath>
    </div>
    <div>
        <asp:TreeView ID="TreeView1" runat="server" DataSourceID="SiteMapDataSource1"></asp:TreeView>
    </div>
        <asp:SiteMapDataSource ID="SiteMapDataSource1" runat="server" />
    </form>
</body>
</html>
