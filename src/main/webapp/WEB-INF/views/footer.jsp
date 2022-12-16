<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
			  </div>
            
				<div id="sidebar">
					<table border=1>
                    <tr>
                    <td width="252" align="left">
                    <font color=white>
                    You are authorized as ${sessionScope.user.name}<br />
                    There are ${sessionScope.userCart.size()} items in your cart.
                    </font>
                    </td>
                    </tr>
                    </table>
                    <h2>Side menu</h2>
					<ul>
						<li><a href="${pageContext.request.contextPath}/products?category=1">Samsung</a></li>
						<li><a href="${pageContext.request.contextPath}/products?category=2">Apple</a></li>
						<li><a href="${pageContext.request.contextPath}/products?category=3">HUAWEI</a></li>
						<li><a href="${pageContext.request.contextPath}/registration">Registration</a></li>
						<li><a href="${pageContext.request.contextPath}/login">Login</a></li>
						<li><a href="${pageContext.request.contextPath}/cart">Cart</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="footer">
	<p>Copyright (c) by Pablo</p>
</div>
<!-- end #footer -->
</body>
</html>
