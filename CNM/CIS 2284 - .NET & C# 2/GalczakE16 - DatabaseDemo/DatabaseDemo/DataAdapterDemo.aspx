<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="DataAdapterDemo.aspx.cs" Inherits="DatabaseDemo.DataAdapterDemo" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
        <asp:DropDownList ID="DropDownListDepartments" runat="server" DataTextField="DepartmentName" DataValueField="pkDepartmentID" AutoPostBack="True" OnSelectedIndexChanged="DropDownListDepartments_SelectedIndexChanged" ></asp:DropDownList> 
        <asp:Label ID="LabelDDLResults" runat="server"></asp:Label>
    
    </div>
    </form>
</body>
</html>
