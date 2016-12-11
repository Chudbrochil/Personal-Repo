<%@ Page Title="" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="OrderForm.aspx.cs" Inherits="GalczakP5_2.OrderForm" %>
<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <h2>Snowboard Order Form</h2>
    <asp:CheckBox ID="cbRegFoot" runat="server" Text="Regular Footed?" /><br />
    <asp:CheckBox ID="cbBindings" runat="server" Text="Include Bindings?" OnCheckedChanged="cbBindings_CheckedChanged" AutoPostBack="true" /> <br />
    <asp:RadioButtonList ID="rblBindings" runat="server" Visible="false">
        <asp:ListItem>Strap-In</asp:ListItem>
        <asp:ListItem>Rear-Entry</asp:ListItem>
    </asp:RadioButtonList>
    <asp:Label ID="lblLength" runat="server" Text="Length"></asp:Label><asp:TextBox ID="tbLength" runat="server"></asp:TextBox><br />
    <asp:RegularExpressionValidator ID="lengthValidator" ControlToValidate="tbLength" runat="server" ErrorMessage="Only Numbers allowed" ValidationExpression="\d+" Display="Dynamic"></asp:RegularExpressionValidator>
    <asp:Label ID="lblWidth" runat="server" Text="Width"></asp:Label><asp:TextBox ID="tbWidth" runat="server"></asp:TextBox><br />
    <asp:RegularExpressionValidator ID="widthValidator" ControlToValidate="tbWidth" runat="server" ErrorMessage="Only Numbers allowed" ValidationExpression="\d+" Display="Dynamic"></asp:RegularExpressionValidator>
    <asp:FileUpload ID="fupImage" runat="server" Text="Upload Image" /> <br />
    <asp:Button ID="btnUpload" runat="server" Text="Upload Image" OnClick="btnUpload_Click" /> <br />
    <asp:Image ID="imgSnowboard" runat="server" /><br />
    <asp:Button ID="btnSubmit" runat="server" Text="Submit Order" OnClick="btnSubmit_Click"/><br />
    <asp:Label ID="Label2" runat="server" Text="Please make selections then click submit."></asp:Label>


</asp:Content>
