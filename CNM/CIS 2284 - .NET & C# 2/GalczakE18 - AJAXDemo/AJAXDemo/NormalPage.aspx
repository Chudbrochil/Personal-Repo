<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="NormalPage.aspx.cs" Inherits="AJAXDemo.NormalPage" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
        <asp:ScriptManager ID="ScriptManager1" runat="server"></asp:ScriptManager>
        <p>
        <asp:Label ID="LabelPostBack" runat="server" Text="Label"></asp:Label>
        </p>
        <p>
        <asp:Label ID="LabelButton1" runat="server" Text="Label"></asp:Label>
        </p>
        <p>
        <asp:Button ID="Button1" runat="server" Text="Button" OnClick="Button1_Click" />
        </p>
        <asp:UpdatePanel ID="UpdatePanel1" runat="server">
            <ContentTemplate>
            <p>
                <asp:Label ID="LabelButton2" runat="server" Text="Label"></asp:Label>
            </p>
            <p>
                <asp:Button ID="Button2" runat="server" Text="Button" OnClick="Button2_Click" />
            </p>
            </ContentTemplate>
        </asp:UpdatePanel>
    </div>
    </form>
</body>
</html>
