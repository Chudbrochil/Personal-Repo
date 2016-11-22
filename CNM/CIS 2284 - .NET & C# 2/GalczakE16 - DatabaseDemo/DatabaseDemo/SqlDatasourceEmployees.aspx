<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="SqlDatasourceEmployees.aspx.cs" Inherits="DatabaseDemo.SqlDatasourceEmployees" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
        <asp:SqlDataSource ID="SqlDataSource1" runat="server" ConnectionString="<%$ ConnectionStrings:DemoDBConnection %>" SelectCommand="Select * from Employee"></asp:SqlDataSource>
        <asp:GridView 
    ID="GridViewEmployees" 
    AutoGenerateColumns ="False" 
    runat="server" 
    DataKeyNames="pkEmployeeID" 
    DataSourceID="SqlDataSource1" OnSelectedIndexChanged="GridViewEmployees_SelectedIndexChanged">
    <Columns>
        <asp:BoundField DataField ="pkEmployeeID" HeaderText ="pkEmployeeID" SortExpression ="pkEmployeeID" InsertVisible="False" ReadOnly="True"/>
        <asp:BoundField DataField="fkSupervisorID" HeaderText="fkSupervisorID" SortExpression="fkSupervisorID" />
        <asp:BoundField DataField="Name" HeaderText="Name" SortExpression="Name" />
    </Columns>            
</asp:GridView>

    </div>
    </form>
</body>
</html>
