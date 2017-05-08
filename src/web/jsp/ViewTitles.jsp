<%@ include file="_header.jsp" %>
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
                  <%@ include file="_leftnav.jsp" %>
    </td>
    <td width="25" align="left" valign="top"><html:img page="/images/spacer.gif" alt="" border="0" width="25" height="5" /></td>
    <td width="" align="left" valign="top">
		  <!-- START LISTING -->
			<table width="100%" border="0" cellspacing="1" cellpadding="4">
				<tr align="left" valign="top">
					<td><h1>View Titles</h1></td>
        			</tr>
			</table>
			<br />
			<table width="100%" border="0" cellspacing="1" cellpadding="1">
				<tr align="center" valign="middle">
					<th>ID</th>
					<th>Name</th>
     				</tr>
				<logic:iterate name="titles" id="title" scope="request">
				<tr align="left" valign="top">
					<td class="listCell"><html:link page="/Browse.do?subAction=load" paramId="titleId" paramName="title" paramProperty="titleId" ><bean:write name="title" property="titleId"/></html:link></td>
					<td class="listCell"><bean:write name="title" property="name"/></td>
				</tr>
				</logic:iterate>
                                <logic:empty name="titles" scope="request">
                                <tr align="left" valign="top">
                                     <td class="listCell" colspan="2">No titles in the system.</td>
                                </tr>
                                </logic:empty>
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