<%@ Page Title="" Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" CodeBehind="Depreciation.aspx.cs" Inherits="GalczakP4.Depreciation" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
        <asp:Label ID="lblHeader" runat="server" Text="Please enter your values in numeric format." Font-Size="Larger"></asp:Label> <br />

        <asp:Label ID="lblStarting" runat="server" Text="Starting Value" AssociatedControlID="tbStarting"></asp:Label> <br />
        <asp:TextBox ID="tbStarting" runat="server"></asp:TextBox> 
        <asp:RegularExpressionValidator ID="startingValueValidator" ControlToValidate="tbStarting" runat="server" ErrorMessage="Only Numbers allowed" ValidationExpression="\d+" Display="Dynamic"></asp:RegularExpressionValidator>
        <asp:RequiredFieldValidator ID="startingValReqValidator" runat="server" ErrorMessage="This field is required." ControlToValidate="tbStarting" Display="Dynamic"></asp:RequiredFieldValidator>

        <br />

        <asp:Label ID="lblSalvage" runat="server" Text="Salvage Value" AssociatedControlID="tbSalvage"></asp:Label> <br />
        <asp:TextBox ID="tbSalvage" runat="server"></asp:TextBox> 
        <asp:RegularExpressionValidator ID="salvageValueValidator" ControlToValidate="tbSalvage" runat="server" ErrorMessage="Only Numbers allowed" ValidationExpression="\d+" Display="Dynamic"></asp:RegularExpressionValidator>
        <asp:RequiredFieldValidator ID="salvageValReqValidator" runat="server" ErrorMessage="This field is required." ControlToValidate="tbSalvage" Display="Dynamic"></asp:RequiredFieldValidator>


        <br />

        <asp:Label ID="lblLifetime" runat="server" Text="Estimated Lifetime of Asset" AssociatedControlID="tbLifetime"></asp:Label> <br />
        <asp:TextBox ID="tbLifetime" runat="server"></asp:TextBox> 
        <asp:RegularExpressionValidator ID="lifetimeValidator" ControlToValidate="tbLifetime" runat="server" ErrorMessage="Only Numbers allowed" ValidationExpression="\d+" Display="Dynamic"></asp:RegularExpressionValidator>
        <asp:RequiredFieldValidator ID="liftimeReqValidator" runat="server" ErrorMessage="This field is required." ControlToValidate="tbLifetime" Display="Dynamic"></asp:RequiredFieldValidator>


        <br />

        <asp:Label ID="lblAge" runat="server" Text="Age of Asset" AssociatedControlID="tbAge"></asp:Label> <br />
        <asp:TextBox ID="tbAge" runat="server"></asp:TextBox> 
        <asp:RegularExpressionValidator ID="ageValidator" ControlToValidate="tbAge" runat="server" ErrorMessage="Only Numbers allowed" ValidationExpression="\d+" Display="Dynamic"></asp:RegularExpressionValidator>
        <asp:RequiredFieldValidator ID="ageReqValidator" runat="server" ErrorMessage="This field is required." ControlToValidate="tbAge" Display="Dynamic"></asp:RequiredFieldValidator>

        
        <br /> <br />
        
        
        <asp:RadioButtonList ID="rblDepreciation" runat="server">
            <asp:ListItem Selected="True">Straight Line</asp:ListItem>
            <asp:ListItem>Double Declining</asp:ListItem>
        </asp:RadioButtonList>


        <asp:Button ID="btnSubmit" runat="server" Text="Calculate Result" OnClick="btnSubmit_Click" />

        <br /> <br />

        <asp:TextBox ID="tbResult" runat="server" Height="50" Width="400" Wrap="true" TextMode="MultiLine" ></asp:TextBox>

</asp:Content>
