<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="SurveyPage1.aspx.cs" Inherits="ValidatedControlsDemo.SurveyPage1" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
    <p>
        <asp:TextBox ID="TextBox1" runat="server" Text="Wrox"></asp:TextBox>

        --><asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" ErrorMessage="RequiredFieldValidator" ControlToValidate="TextBox1" Text="Required!" InitialValue="Wrox" Display="Dynamic"></asp:RequiredFieldValidator><--
    </p>
    <p>
        <asp:DropDownList ID="DropDownList1" runat="server">
            <asp:ListItem Selected="True">Select a profession</asp:ListItem>
            <asp:ListItem>Programmer</asp:ListItem>
            <asp:ListItem>Lawyer</asp:ListItem>
            <asp:ListItem>Doctor</asp:ListItem>
            <asp:ListItem>Artist</asp:ListItem>
        </asp:DropDownList>
        <asp:RequiredFieldValidator ID="RequiredFieldValidator2" runat="server" ErrorMessage="RequiredFieldValidator" Text="Please make a selection" InitialValue="Select a profession" ControlToValidate="DropDownList1"></asp:RequiredFieldValidator>

    </p>
    <p>
        Enter Password:
        <asp:TextBox ID="txbPWD1" TextMode="Password" runat="server"></asp:TextBox>
    </p>
    <p>
        Re-enter Password:
        <asp:TextBox ID="txbPWD2" TextMode="Password" runat="server"></asp:TextBox>
        <asp:CompareValidator ID="CompareValidator1" runat="server" ErrorMessage="CompareValidator" Text="Passwords must match!" ControlToValidate="txbPWD2" ControlToCompare="txbPWD1"></asp:CompareValidator>
    </p>
    <p>
        Enter Age:
        <asp:TextBox ID="TextBox2" runat="server" MaxLength="3"></asp:TextBox>
        <asp:CompareValidator ID="CompareValidator2" runat="server" ErrorMessage="You must enter a number greater than 18" ControlToValidate="TextBox2" Type="Integer" Operator="GreaterThanEqual" ValueToCompare="18"></asp:CompareValidator>
    </p>
    <p>
        Enter a Percent between 1 and 100:
        <asp:TextBox ID="TextBox3" runat="server"></asp:TextBox>
        <asp:RangeValidator ID="RangeValidator1" runat="server" ErrorMessage="Value must be between 1 and 100" ControlToValidate="TextBox3" Type="Integer" MaximumValue="100" MinimumValue="1"></asp:RangeValidator>
    </p>
    <p>
        <asp:Calendar ID="Calendar1" runat="server" OnSelectionChanged="Calendar1_SelectionChanged"></asp:Calendar>
    </p>
    <p>
        <asp:TextBox ID="TextBox4" runat="server"></asp:TextBox>
        <asp:RangeValidator ID="RangeValidator2" runat="server" ErrorMessage="You must only select a date within the next two weeks" Type="Date" ControlToValidate="TextBox4"></asp:RangeValidator>
    </p>
    <p>
        <asp:Button ID="Button1" runat="server" Text="Button" PostBackUrl="~/SurveyPage2.aspx" />
    </p>
    </div>
    </form>
</body>
</html>
