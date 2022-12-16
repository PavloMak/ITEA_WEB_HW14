package com.komsoft.shopmvc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.komsoft.shopmvc.model.Product;
import com.komsoft.shopmvc.repository.DBManager;

@Controller
@RequestMapping("/cart")
public class CartController {

	DBManager menager = new DBManager();

	@RequestMapping(method = RequestMethod.GET)
	protected String doGet() {
		String url = "cartpage";
		return url;
	}

	@RequestMapping(method = RequestMethod.POST)
	protected String doPost(HttpServletRequest request, ModelMap modelMap, @RequestParam String id, @RequestParam String count) {
		int countNum = count == null ? 0 : Integer.parseInt(count);
		HttpSession session = session();
		if (session.getAttribute("userCart") == null) {
			session.setAttribute("userCart", new HashMap<Product, Integer>());
		}
		Map<Product, Integer> cart = (Map<Product, Integer>) session.getAttribute("userCart");
		Product product = menager.getOneProduct(id);
		int quantity = cart.get(product) == null ? 0 : cart.get(product);
		quantity += countNum;
		cart.put(product, quantity);
		session.setAttribute("userCart", cart);
		String referer = request.getHeader("Referer");
	    return "redirect:"+ referer;
	}

	public static HttpSession session() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attr.getRequest().getSession(true); // true == allow create
	}

}
