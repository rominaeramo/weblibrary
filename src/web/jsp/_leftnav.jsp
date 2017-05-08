			<!-- START LEFT NAV -->
			<table width="131" border="0" cellspacing="0" cellpadding="0">
				<tr align="left" valign="top">
					<td>
					  <div class="navBox">
                                          <html:form action="/Search.do?subAction=search">
						<table border="0" cellspacing="0" cellpadding="3">
						  <tr align="left" valign="top">
							  <td>&nbsp;<b>Search:</b>&nbsp;</td>
							</tr>
							<tr align="left" valign="top" bgcolor="#FF9900">
								<td>&nbsp;<html:text name="searchForm" property="searchText" styleId="searchText" size="17" />&nbsp;</td>
							</tr>
							<tr align="right" valign="top" bgcolor="#FF9900">
								<td>&nbsp;<html:image page="/images/btnGo.gif" />&nbsp;</td>
							</tr>
						</table>
                                          </html:form>
						</div>
					</td>
				</tr>
				<tr align="left" valign="top">
					<td>
					  <div class="navBox">
                                                <html:form action="/Browse.do?subAction=view">
						<table border="0" cellspacing="0" cellpadding="3">
						  <tr align="left" valign="top">
							  <td>&nbsp;<b>Browse:</b>&nbsp;</td>
							</tr>
							<tr align="right" valign="top" bgcolor="#FF9900">
								<td>&nbsp;<html:image page="/images/btnBrowseLibrary.gif" styleId="browseButton"/>&nbsp;</td>
							</tr>
						</table>
                                                </html:form>
						</div>
					</td>
				</tr>
			</table>
			<!-- END LEFT NAV -->