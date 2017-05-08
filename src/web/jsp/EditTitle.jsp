<jsp:include page="_header.jsp" flush="true" />
<%@ page contentType="text/html;charset=iso-8859-1" language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!-- START CONTENT -->
<html:form action="/saveTitle">
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
					<td><h1>Edit Title</h1></td>
            			</tr>
			</table>
			<br />
			<table width="" border="0" cellspacing="1" cellpadding="2">
				<tr align="center" valign="middle">
					<td><b>Current Title:</b></td>
					<td><bean:write></td>
    				</tr>
				<tr align="left" valign="middle">
					<td><b>New Title:</b></td>
					<td><html:text property="title" size="50" /></td>
				</tr>
				<tr align="left" valign="middle">
					<td colspan="2" height="10"><html:img page="/images/spacer.gif" alt="" border="0" width="10" height="10" /></td>
				</tr>
				<tr align="right">
					<td colspan="2"><input type="submit" name="Submit22" value="Submit" /></td>
				</tr>
			</table>
			<!-- END LISTING -->
			<%-- include footer --%>
			<jsp:include page="_footer.jsp" flush="true" />
    </td>
  </tr>
</table>
</html:form>
<!-- END CONTENT -->
</body>
</html:html>