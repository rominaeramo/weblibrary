<jsp:include page="_header.jsp" flush="true" />
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>

<!-- START CONTENT -->
<!--form-->
<table width="760" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="20" align="left" valign="top">
      <html:img page="/images/spacer.gif" alt="" border="0" width="20" height="5" />
    </td>
    <td width="131" align="left" valign="top">
		  <%-- include left navigation --%>
			<jsp:include page="_leftnav.jsp" flush="true" />
    </td>
    <td width="25" align="left" valign="top"><html:img page="/images/spacer.gif" alt="" border="0" width="25" height="5" /></td>
    <td width="" align="left" valign="top">
		  <!-- START LISTING -->
			<table width="100%" border="0" cellspacing="1" cellpadding="4">
				<tr align="left" valign="top">
					<td><h1>Add/Edit Borrower</h1></td>
    				</tr>
			</table>
			<br />
			<table width="" border="0" cellspacing="1" cellpadding="2">
				<tr align="left">
					<td><b>First Name:</b></td>
					<td><html:text property="firstName" size="50" /></td>
    				</tr>
				<tr align="left">
					<td><b>Last Name:</b></td>
					<td><html:text property="lastName" size="50" /></td>
    				</tr>
				<tr align="left">
					<td><b>Role:</b></td>
					<td><html:text property="role" size="50" /></td>
    				</tr>
				<tr align="left">
					<td><b>Address:</b></td>
					<td><html:text property="address" size="50" /></td>
    				</tr>
				<tr align="left">
					<td><b>City:</b></td>
					<td><html:text property="city" size="50" /></td>
    				</tr>
				<tr align="left">
					<td><b>State:</b></td>
					<td><html:text property="state" size="50" /></td>
    				</tr>
				<tr align="left">
					<td><b>ZIP:</b></td>
					<td><html:text property="zip" size="50" /></td>
    				</tr>
				<tr align="left">
					<td><b>Phone:</b></td>
					<td><html:text property="phone" size="50" /></td>
    				</tr>
			</table>
			<!-- END LISTING -->
			<%-- include footer --%>
			<jsp:include page="_footer.jsp" flush="true" />
    </td>
  </tr>
</table>
<!--/form-->
<!-- END CONTENT -->
</body>
</html:html>