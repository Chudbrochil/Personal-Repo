<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="ShowMeetingRooms.aspx.cs" Inherits="WebFormDemo.ShowMeetingRooms" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
        <asp:Button ID="btnClick" runat="server" Text="Button" PostBackUrl="~/MeetingRoomInformation.aspx"/>
        <br />
        <br />
        <asp:DropDownList ID="ddlRooms" runat="server" OnSelectedIndexChanged="ddlRooms_SelectedIndexChanged"
            AutoPostBack="true">
            <asp:ListItem>Jane</asp:ListItem>
            <asp:ListItem>Bob</asp:ListItem>
            <asp:ListItem>Billy</asp:ListItem>
        </asp:DropDownList>
        <br />
        <br />
        <p>
        <asp:Label ID="lblResults" runat="server" Text="Label"></asp:Label>
        </p>
    </div>
    </form>
</body>
</html>
