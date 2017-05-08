<%@ include file="_header.jsp" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ page import="com.wiley.umltoolkit.casestudy.common.Constants" %>
<%@ page import="com.wiley.umltoolkit.casestudy.vo.UserVo" %>

<!-- START CONTENT -->
<!--form-->
<table width="740" border="0" cellspacing="0" cellpadding="0">
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
					<td colspan="2"><h1><bean:write name="title" property="name"/></h1><h2>Title Detail</h2></td>
                                </tr>
				<%-- display error row only if errors exist --%>
				<tr align="left" valign="top">
					<td colspan="2"><span class="red"><html:errors/></span></td>
				</tr>
			</table>
			<br />
			<table width="" border="0" cellspacing="1" cellpadding="2">
				<tr align="left" valign="top">
					<td><b>Title Id:</b></td>
					<td><bean:write name="title" property="titleId"/></td>
                                        <td rowspan="6" align="right" valign="top">
						<div class="bookPic"><html:img page="/images/umlToolkitSm.jpg" alt="Cover image of UML 2 Toolkit" width="135" height="169" border="0" /></div>
					</td>
				</tr>
				<tr align="left" valign="top">
					<td><b>Name:</b></td>
					<td><bean:write name="title" property="name"/></td>
				</tr>
				<tr align="left" valign="top">
					<td><b>Author:</b></td>
					<td><bean:write name="title" property="author"/></td>
				</tr>
				<tr align="left" valign="top">
					<td><b>ISBN:</b></td>
					<td><bean:write name="title" property="isbn"/></td>
				</tr>
				<tr align="left" valign="top">
					<td><b>Publication Type:</b></td>
					<td><bean:write name="title" property="titleKind.description"/></td>
				</tr>
				<tr align="left" valign="top">
					<td><b>Description:</b></td>
					<td><bean:write name="title" property="description"/></td>
				</tr>
			</table>
			<br />
			<br />
			<table width="100%" border="0" cellspacing="1" cellpadding="4">
				<tr align="left" valign="top">
					<td><h2>Items in System</h2><html:link page="/Browse.do?subAction=load" paramId="titleId" paramName="title" paramProperty="titleId">Refresh</html:link></td>
                                </tr>
			</table>
			<br />
			<table width="100%" border="0" cellspacing="1" cellpadding="1">
				<tr align="center" valign="middle">
					<th>Item ID</th>
					<th>Title ID</th>
					<th>Added Date</th>
					<th>Checked Out Flag</th>
					<th>Borrower ID</th>
					<th>Due Back Date</th>
					<th>&nbsp;</th>
        			</tr>
				<logic:iterate name="items" id="item" scope="request">
				<tr align="left" valign="top">
					<td class="listCell"><bean:write name="item" property="itemId"/></td>
					<td class="listCell"><bean:write name="item" property="titleId"/></td>
					<td class="listCell"><bean:write name="item" property="addedDate"/></td>
					<td class="listCell"><bean:write name="item" property="checkedOut"/></td>
					<td class="listCell"><bean:write name="item" property="borrower.id"/></td>
					<td class="listCell"><bean:write name="item" property="dueBack"/></td>
					    <logic:equal name="item" property="isCheckedOut" value="false">
					    <td class="listCell"><html:link page="/Checkout.do?subAction=store" name="item" property="itemIdAndTitleId">Checkout</html:link></td>
 					    </logic:equal>
					    <logic:equal name="item" property="isCheckedOut" value="true">
					       <!-- Show the Return Item link, since this user checked out the ITEM -->
					       <logic:equal name="item" property="borrower.id" value="<%=((UserVo)session.getAttribute(Constants.USER_INFO)).getId()%>">
 					       <td class="listCell"><html:link page="/ReturnItem.do?subAction=store" name="item" property="itemIdAndTitleId">Return Item</html:link></td>
 					       </logic:equal>
  					      <logic:notEqual name="item" property="borrower.id" value="<%=((UserVo)session.getAttribute(Constants.USER_INFO)).getId()%>">
  					          <logic:equal name="item" property="isReserved" value="false">
  					              <logic:notEqual name="item" property="borrower.id" value="<%=((UserVo)session.getAttribute(Constants.USER_INFO)).getId()%>">
  					              <td class="listCell"><html:link page="/ReserveItem.do?subAction=reserve" name="item" property="itemIdAndTitleId">Reserve Item</html:link></td>
  					              </logic:notEqual>
  					          </logic:equal>
   					         <logic:equal name="item" property="isReserved" value="true">
   					             <logic:equal name="item" property="reservation.userId" value="<%=((UserVo)session.getAttribute(Constants.USER_INFO)).getId()%>">
   					             <td class="listCell"><html:link page="/UnreserveItem.do?subAction=unreserve" name="item" property="itemIdAndTitleId">Unreserve Item</html:link></td>
    					            </logic:equal>
   					         </logic:equal>
  					      </logic:notEqual>
 					    </logic:equal>
				</tr>
				</logic:iterate>
                                <logic:empty name="items" scope="request">
                                <tr align="left" valign="top">
                                     <td class="listCell" colspan="8">No items in the system.</td>
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