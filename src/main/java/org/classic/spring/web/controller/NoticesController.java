package org.classic.spring.web.controller;

import java.math.BigInteger;
import java.util.List;

import javax.validation.Valid;

import org.classic.spring.web.dao.Notice;
import org.classic.spring.web.services.NoticesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NoticesController {

	private NoticesService noticesService;

	@Autowired
	public NoticesController(NoticesService noticesService) {
		this.noticesService = noticesService;
	}

	@RequestMapping("/notices")
	public String showNotices(Model model) {
//		noticesService.throwTestException();
		List<Notice> notices = noticesService.getCurrent();
		model.addAttribute("notices", notices);
		return "notices";
	}

	@RequestMapping("/notice/create")
	public String createNotice(Model model) {
		/*
		 * List<Notice> notices = noticesService.getCurrent();
		 * model.addAttribute("notices", notices);
		 */
		model.addAttribute(new Notice());

		return "create-notice";
	}

	@RequestMapping("/test")
	public String showTest(Model model, @RequestParam("id") BigInteger id) {
		System.out.println("ID is : " + id);
		return "home";
	}

	@RequestMapping(value = "/docreate", method = RequestMethod.POST)
	public String doCreate(Model model, @Valid Notice notice, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {

			return "create-notice";
		}

		noticesService.create(notice);

		return "notice-created";
	}
}
