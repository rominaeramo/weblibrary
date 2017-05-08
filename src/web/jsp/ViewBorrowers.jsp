<%@ include file="_header.jsp" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ page import="com.wiley.umltoolkit.casestudy.common.Constants" %>
<%@ page import="com.wiley.umltoolkit.casestudy.vo.UserVo" %>

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
					<td><h1>View Borrowers</h1></td>
                                </tr>
			</table>
			<br />
			<table width="100%" border="0" cellspacing="1" cellpadding="1">
				<tr align="center" valign="middle">
					<th>Name</th>
					<th>Role</th>
					<th>Address</th>
					<th>City</th>
					<th>State</th>
					<th>ZIP</th>
					<th>Phone</th>
                                </tr>



                                <logic:iterate name="borrowers" id="borrower" scope="request">
				<tr align="left" valign="top">
					<td class="listCell"><bean:write name="borrower" property="lastName"/>, <bean:write name="borrower" property="firstName"/></td>
					<td class="listCell"><bean:write name="borrower" property="role"/></td>
					<td class="listCell"><bean:write name="borrower" property="address"/></td>
					<td class="listCell"><bean:write name="borrower" property="city"/></td>
					<td class="listCell"><bean:write name="borrower" property="state"/></td>
					<td class="listCell"><bean:write name="borrower" property="zip"/></td>
					<td class="listCell"><bean:write name="borrower" property="phone"/></td>
				</tr>
				</logic:iterate>
                                <logic:empty name="borrowers" scope="request">
                                <tr align="left" valign="top">
                                     <td class="listCell" colspan="7">No borrowers in the system.</td>
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