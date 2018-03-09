package com.gc.controller;

import java.util.ArrayList;

import org.hibernate.Criteria;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gc.dto.UserDto;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String index(Model model) {
		boolean loggedIn = true;
		model.addAttribute("fail", loggedIn);

		return "index";
	}

	@RequestMapping("/login")
	public String login(Model model, @RequestParam("username") String userName,
			@RequestParam("password") String password) {
		boolean loggedIn = true;
		Configuration cfg = new Configuration().configure("hibernate.cfg.xml");

		SessionFactory sessionFact = cfg.buildSessionFactory();

		Session selectUser = sessionFact.openSession();

		selectUser.beginTransaction();

		// Criteria is used to create the query
		Criteria c = selectUser.createCriteria(UserDto.class);

		// this is matching against the unique username in the table User from the DB
		c.add(Restrictions.eq("userName", userName));

		ArrayList<UserDto> list = (ArrayList<UserDto>) c.list(); // can also use the unique result
		if (list.size() > 0 && list.get(0).getPassword().equals(password)) {

			model.addAttribute("user", list);
			return "loginsuccess";
		}
		loggedIn = false;
		model.addAttribute("fail", loggedIn);
		model.addAttribute("failmsg", "Your information did not match, please try again!");
		selectUser.close();
		return "index";
	}

}
