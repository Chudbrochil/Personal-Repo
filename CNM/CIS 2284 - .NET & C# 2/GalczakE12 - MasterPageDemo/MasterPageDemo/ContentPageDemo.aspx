<%@ Page Title="" Language="C#" AutoEventWireup="true" CodeBehind="ContentPageDemo.aspx.cs" Inherits="MasterPageDemo.ContentPageDemo" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    Please enter your name
    <asp:Button ID="ButtonName" runat="server" Text="Submit" OnClick="ButtonName_Click" />
    <asp:TextBox ID="TextBoxName" runat="server"></asp:TextBox>
    <asp:Label ID="LabelWelcome" runat="server" Text="Label"></asp:Label>
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="ContentPlaceHolder2" runat="server">
    <asp:Image ID="Image1" runat="server" />
    <img src="Images/Harold.png" />
</asp:Content>
