<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp"%>

<center>
	<form action='${pageContext.request.contextPath}/login' method='post'>

		<table border='0'>
			<tr>

				<td width='100'>Login</td>
				<td><input type='text' name='login' /></td>
			</tr>

			<tr>
				<td>Password</td>
				<td><input type='password' name='password' /></td>
			</tr>

			<tr>

				<td align='center' colspan='2'><input type='submit'
					value='Send' /></td>
			</tr>
		</table>
	</form>
	${message}<br>
</center>


<%@ include file="footer.jsp"%>