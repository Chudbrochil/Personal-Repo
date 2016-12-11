<%@ Page Title="" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="OrderList.aspx.cs" Inherits="GalczakP5_2.OrderList" %>
<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <h2>Snowboard Order List</h2>

    <asp:SqlDataSource 
        ID="SqlDataSource1" 
        runat="server" 
        ConnectionString="<%$ ConnectionStrings:ConnectionString %>" 
        SelectCommand="SELECT * FROM [Snowboards]"
        UpdateCommand="UPDATE SNOWBOARDS SET BindingOption=@BindingOption,ImageFileName=@ImageFileName,IncludeBindings=@IncludeBindings,IsRegularFoot=@IsRegularFoot,Length=@Length,Width=@Width WHERE SnowboardID=@SnowboardID"
        DeleteCommand="DELETE FROM SNOWBOARDS WHERE SnowboardID=@SnowboardID"    >
        
    </asp:SqlDataSource>

    <asp:GridView ID="GridView1" runat="server" DataSourceID="SqlDataSource1">
        <Columns>
            <asp:CommandField HeaderText="Edit" ShowEditButton="True" ShowHeader="True" />
            <asp:CommandField HeaderText="Delete" ShowDeleteButton="True" ShowHeader="True"  />     
        </Columns>
    </asp:GridView>
    <asp:Button ID="Button1" runat="server" Text="Create New Snowboard Order" PostBackUrl="~/OrderForm.aspx" />
</asp:Content>
