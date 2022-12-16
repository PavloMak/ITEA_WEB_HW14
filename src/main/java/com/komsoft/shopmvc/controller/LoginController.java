package com.komsoft.shopmvc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.komsoft.shopmvc.repository.DBManager;
import com.komsoft.shopmvc.util.Encoder;

@Controller
@RequestMapping("/login")
public class LoginController {

	DBManager menager = new DBManager();

	public static final String USER_ACCESS_GRANTED = "user";

	@RequestMapping(method = RequestMethod.GET)
	protected String doGet(ModelMap modelMap, @RequestParam(required = false) String key) {

		HttpSession session = session();

		if (key != null) {
			session.invalidate();
		}

		String url = "loginform";

		if (session.getAttribute(USER_ACCESS_GRANTED) != null) {
			modelMap.addAttribute("user", menager.getUser());
			url = "loginsuccesspage";
		}
		return url;

	}

	@RequestMapping(method = RequestMethod.POST)
	protected String doPost(ModelMap modelMap, @RequestParam String login, @RequestParam String password) {

		String url = "loginform";
		if (menager.getFullNameByLoginAndPassword(login, Encoder.md5EncriptionWithSult(password))) {

			HttpSession session = session();
			session.setAttribute(USER_ACCESS_GRANTED, menager.getUser());
			url = "loginsuccesspage";

		} else {
			modelMap.addAttribute("message", "Access denied");
		}
		return url;

	}

	public static HttpSession session() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attr.getRequest().getSession(true); // true == allow create
	}

}
