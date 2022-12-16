<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ include file="header.jsp"%>

<c:forEach items="${products}" var="product">
	<table border='1'>
		<tr>
			<td width='200'><a
				href='${pageContext.request.contextPath}/product?prid=${product.id}'>${product.name}</a></td>
			<td width='200'></td>
		</tr>
		<tr>
			<td><img
				src="${pageContext.request.contextPath}/static/images/products/${product.id}.jpg"
				width="auto" height="100px" /></td>
			<td>${product.description}</td>
		</tr>
		<tr>
			<td>${product.price}</td>
			<td>
				<form action="${pageContext.request.contextPath}/cart" method="post">
					<input type="hidden" name="id" value="${product.id}" /> 
					<input type="text" name="count" value="1" /> 
					<input type="submit" value='Buy' />
				</form>
			</td>
		</tr>
	</table>
	<br />
	<br />
</c:forEach>

<%@ include file="footer.jsp"%>


