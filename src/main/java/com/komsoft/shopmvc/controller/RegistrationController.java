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
@RequestMapping("/registration")
public class RegistrationController {

	@RequestMapping(method = RequestMethod.GET)
	protected String doGet(ModelMap modelMap, @RequestParam(required = false) String login, @RequestParam(required = false) String fullName,
			@RequestParam(required = false) String region, @RequestParam(required = false) String gender, @RequestParam(required = false) String comment) {

		HttpSession session = session();

		String url = "registrationform";

		if (session.getAttribute(LoginController.USER_ACCESS_GRANTED) != null) {
			url = "registrationsuccesspage";
		} else {

			if (login != null) {
				modelMap.addAttribute("rLogin", login);
			} else {
				modelMap.addAttribute("rLogin", " ");
			}

			if (fullName != null) {
				modelMap.addAttribute("rFullName", fullName);
			} else {
				modelMap.addAttribute("rFullName", " ");
			}

			if (region != null) {
				if (region.equals("Lviv")) {
					modelMap.addAttribute("rRegionLviv", "selected");
					modelMap.addAttribute("rRegionKyiv", " ");
					modelMap.addAttribute("rRegionKharkiv", " ");
				} else if (region.equals("Kyiv")) {
					modelMap.addAttribute("rRegionLviv", " ");
					modelMap.addAttribute("rRegionKyiv", "selected");
					modelMap.addAttribute("rRegionKharkiv", " ");
				} else {
					modelMap.addAttribute("rRegionLviv", " ");
					modelMap.addAttribute("rRegionKyiv", " ");
					modelMap.addAttribute("rRegionKharkiv", "selected");
				}
			} else {
				modelMap.addAttribute("rRegionLviv", " ");
				modelMap.addAttribute("rRegionKyiv", " ");
				modelMap.addAttribute("rRegionKharkiv", "selected");
			}

			if (gender != null) {
				if (gender.equals("F")) {
					modelMap.addAttribute("rGenderF", "checked");
					modelMap.addAttribute("rGenderM", " ");
				} else {
					modelMap.addAttribute("rGenderF", " ");
					modelMap.addAttribute("rGenderM", "checked");
				}
			} else {
				modelMap.addAttribute("rGenderF", "checked");
				modelMap.addAttribute("rGenderM", " ");
			}

			if (comment != null) {
				modelMap.addAttribute("rcomment", comment);
			} else {
				modelMap.addAttribute("rcomment", " ");
			}

		}
		modelMap.addAttribute("errMessage", " ");
		return url;
	}

	@RequestMapping(method = RequestMethod.POST)
	protected String doPost(ModelMap modelMap, @RequestParam String login, @RequestParam String password,
			@RequestParam String rePassword, @RequestParam String fullName, @RequestParam String region,
			@RequestParam String gender, @RequestParam String comment, @RequestParam String agreement) {

		String url = "registrationform";

		boolean isError = false;

		String message = "<ul>";
		if (login == null || login.isEmpty()) {
			isError = true;
			message += "<li>login is empty</li>";
		} else if (!login.matches(".*@.*\\.com")) {
			isError = true;
			message += "<li>incorect login</li>";
		}

		if (password == null || password.isEmpty()) {
			isError = true;
			message += "<li>password is empty</li>";
		} else {
			if (password.length() < 8) {
				isError = true;
				message += "<li>password is too short</li>";
			}
			if (!password.matches(".*[0-9].*")) {
				isError = true;
				message += "<li>password has to contain at least one digit</li>";
			}
			if (!password.matches(".*[A-Z].*")) {
				isError = true;
				message += "<li>password has to contain at least capital letter</li>";
			}
		}

		if (rePassword == null || rePassword.isEmpty()) {
			isError = true;
			message += "<li>rePassword is empty</li>";
		} else if (password != null && !password.isEmpty()) {
			if (!rePassword.equals(password)) {
				isError = true;
				message += "<li>rePassword and password are different</li>";
			}
		}

		if (fullName == null || fullName.isEmpty()) {
			isError = true;
			message += "<li>fullName is empty</li>";
		}
		if (gender == null || gender.isEmpty()) {
			isError = true;
			message += "<li>gender is not chosen</li>";
		}
		if (agreement == null || agreement.isEmpty()) {
			isError = true;
			message += "<li>agreement is not checked</li>";
		}

		message += "</ul>";

		if (!isError) {
			url = "registrationform";
			message = "registration was complited";
			DBManager menager = new DBManager();
			menager.addUserToBD(login, Encoder.md5EncriptionWithSult(password), fullName, region, gender, comment);
		} else {

			if (login != null) {
				modelMap.addAttribute("rLogin", login);
			} else {
				modelMap.addAttribute("rLogin", " ");
			}

			if (fullName != null) {
				modelMap.addAttribute("rFullName", fullName);
			} else {
				modelMap.addAttribute("rFullName", " ");
			}

			if (region != null) {
				if (region.equals("Lviv")) {
					modelMap.addAttribute("rRegionLviv", "selected");
					modelMap.addAttribute("rRegionKyiv", " ");
					modelMap.addAttribute("rRegionKharkiv", " ");
				} else if (region.equals("Kyiv")) {
					modelMap.addAttribute("rRegionLviv", " ");
					modelMap.addAttribute("rRegionKyiv", "selected");
					modelMap.addAttribute("rRegionKharkiv", " ");
				} else {
					modelMap.addAttribute("rRegionLviv", " ");
					modelMap.addAttribute("rRegionKyiv", " ");
					modelMap.addAttribute("rRegionKharkiv", "selected");
				}
			} else {
				modelMap.addAttribute("rRegionLviv", " ");
				modelMap.addAttribute("rRegionKyiv", " ");
				modelMap.addAttribute("rRegionKharkiv", "selected");
			}

			if (gender != null) {
				if (gender.equals("F")) {
					modelMap.addAttribute("rGenderF", "checked");
					modelMap.addAttribute("rGenderM", " ");
				} else {
					modelMap.addAttribute("rGenderF", " ");
					modelMap.addAttribute("rGenderM", "checked");
				}
			} else {
				modelMap.addAttribute("rGenderF", "checked");
				modelMap.addAttribute("rGenderM", " ");
			}

			if (comment != null) {
				modelMap.addAttribute("rcomment", comment);
			} else {
				modelMap.addAttribute("rcomment", " ");
			}
		}

		modelMap.addAttribute("errMessage", message);

		return url;

	}

	public static HttpSession session() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attr.getRequest().getSession(true); // true == allow create
	}

}
