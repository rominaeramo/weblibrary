<%@ include file="_header.jsp" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!-- START CONTENT -->
<!--form-->
<table width="760" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="20" align="left" valign="top">
      <html:img page="/images/spacer.gif" alt="" border="0" width="20" height="5" />
    </td>
    <td width="131" align="left" valign="top">
		  <%-- include left navigation --%>
			<%@ include file="_leftnav.jsp" %>
    </td>
    <td width="25" align="left" valign="top"><html:img page="/images/spacer.gif" alt="" border="0" width="25" height="5" /></td>
    <td width="" align="left" valign="top">
		  <!-- START LISTING -->
			<table width="100%" border="0" cellspacing="1" cellpadding="4">
				<tr align="left" valign="top">
					<td><h1>Main Page</h1></td>
      				</tr>
				<tr align="left" valign="top">
					<td><html:link page="/Browse.do?subAction=view">Browse Titles</html:link></td>
				</tr>
				<tr align="left" valign="top">
					<!-- <td><html:link page="/ManageBorrowers.do?subAction=view">Browse Borrowers</html:link></td> -->
                                        <td>Browse Borrowers</td>
				</tr>
			</table>
			<!-- END LISTING -->
			<%-- include footer --%>
			<%@include file="_footer.jsp" %>
    </td>
  </tr>
</table>
<!--/form-->
<!-- END CONTENT -->
</body>
</html:html>