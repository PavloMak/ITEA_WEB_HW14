<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ include file="header.jsp"%>

<c:forEach items="${sessionScope.userCart}" var="product">
	<table border='1'>
		<tr>
			<td width='200'><a
				href='${pageContext.request.contextPath}/product?prid=${product.key.id}'>${product.key.name}</a></td>
			<td width='200'></td>
		</tr>
		<tr>
			<td><img
				src="${pageContext.request.contextPath}/static/images/products/${product.key.id}.jpg"
				width="auto" height="100px" /></td>
			<td>${product.key.description}</td>
		</tr>
		<tr>
			<td>${product.key.price}</td>
			<td>${product.value}</td>
		</tr>
	</table>
	<br />
	<br />
</c:forEach>

<%@ include file="footer.jsp"%>


