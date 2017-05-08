<%@ include file="_headerLogin.jsp" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="org.apache.struts.action.Action" %>
<%@ page import="org.apache.struts.action.ActionErrors" %>
<%@ page import="org.apache.struts.action.ActionMessages" %>
<%@ page import="java.util.Enumeration" %>
<!-- START CONTENT -->
<html:form action="/Login.do?subAction=login" focus="username">
<table width="760" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="20" align="left" valign="top">
      <html:img page="/images/spacer.gif" alt="" border="0" width="20" height="5" />
    </td>
    <td width="131" align="left" valign="top">
		  <%-- NO left navigation --%>
    </td>
    <td width="25" align="left" valign="top"><html:img page="/images/spacer.gif" alt="" border="0" width="25" height="5" /></td>
    <td width="" align="left" valign="top">
		  <!-- START LOGIN -->
			<table width="100%" border="0" cellspacing="1" cellpadding="4">
				<tr align="left" valign="top">
					<td><span class="red"><html:errors/></span></td>
                                </tr>
			</table>
			<br />
			<div class="login">
			<table width="100%" border="0" cellspacing="1" cellpadding="4">
				<tr align="left" valign="top">
					<td><h1>Login</h1></td>
                                 </tr>
			</table>
			<br />
			<table width="" border="0" cellspacing="1" cellpadding="1">
				<tr align="left" valign="middle">
					<td><bean:message key="prompt.username"/></td>
					<td><html:text name="loginForm" property="username" size="16" maxlength="16" value="smatyas"/></td>
                                </tr>
				<tr align="left" valign="middle">
					<td><bean:message key="prompt.password"/></td>
					<td><html:password name="loginForm" property="password" size="16" maxlength="16" redisplay="false" value="password"/></td>
				</tr>
				<tr align="left" valign="middle">
					<td colspan="2" height="10"><html:img page="/images/spacer.gif" alt="" border="0" width="10" height="10" /></td>
				</tr>
				<tr align="right">
					<td colspan="2"><html:submit property="submit" value="Submit"/>&nbsp;&nbsp;&nbsp;<html:reset/></td>
				</tr>
			</table>
			</div>
			<!-- END LOGIN -->
			<%-- NO footer --%>
    </td>
  </tr>
</table>
</html:form>
<!-- END CONTENT -->
</body>
</html:html>