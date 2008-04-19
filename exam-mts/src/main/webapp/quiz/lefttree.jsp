<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>无标题文档</title>
		<script src="<%=request.getContextPath() %>/js/dtree.js"></script>
	</head>

	<body topmargin="0" leftmargin="0">
		<table width="100%" height="540" border="0" cellpadding="0"
			cellspacing="0" bgcolor="#CAE4FF" id="table2">
			<tr>
				<td valign="top" bgcolor="#7FBEE6">
					<div align="left">
						
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td valign="top">
									<div align="left">
										
										<p class="hcy2">
											<%=(String)request.getAttribute("treeStr") %>
										</p>
									</div>
								</td>
							</tr>
						</table>
					</div>
					<p>
					</p>
				</td>
			</tr>
		</table>
	</body>
</html>
