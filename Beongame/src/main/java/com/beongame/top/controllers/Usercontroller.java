package com.beongame.top.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.beongame.top.dao.AcademyDAO;
import com.beongame.top.dao.BookingDAO;
import com.beongame.top.dao.EnduserDAO;
import com.beongame.top.dao.ServiceproviderDAO;
import com.beongame.top.dao.SportsDAO;
import com.beongame.top.dao.SportsacademyDAO;
import com.beongame.top.dao.UserDAO;
import com.beongame.top.exception.UserException;
import com.beongame.top.pojo.Academy;
import com.beongame.top.pojo.Booking;
import com.beongame.top.pojo.Enduser;
import com.beongame.top.pojo.Person;
import com.beongame.top.pojo.Serviceprovider;
import com.beongame.top.pojo.Sports;
import com.beongame.top.pojo.SportsAcademy;

@Controller
@RequestMapping("/user/*")
public class Usercontroller {
	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;

	@Autowired
	@Qualifier("svcprDAO")
	ServiceproviderDAO svcprDAO;

	@Autowired
	@Qualifier("euserDao")
	EnduserDAO euserDao;

	@Autowired
	@Qualifier("sportsDAO")
	SportsDAO sportsDAO;

	@Autowired
	@Qualifier("academyDAO")
	AcademyDAO academyDAO;

	@Autowired
	@Qualifier("bookingDAO")
	BookingDAO bookingDAO;

	@Autowired
	@Qualifier("spacDAO")
	SportsacademyDAO spacDAO;
	// Signin - Get

	@RequestMapping(value = "/user/login", method = RequestMethod.GET)
	protected ModelAndView signInUse(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();

		if (session.getAttribute("ltype") == null) {
			return new ModelAndView("redirect-Page");
		} else {

			String ltype = (String) session.getAttribute("ltype");
			String ladmin = "Admin";
			String enduser = "User";
			String servicep = "ServiceProvider";

			if (ltype.equals(ladmin)) {
//				Person user = userDao.get(request.getParameter("email"), request.getParameter("password"),
//						request.getParameter("loginType"));
				Person user = (Person) session.getAttribute("cuser");
				if (user != null) {
					return new ModelAndView("admin", "user", user);
				} else {
					session.setAttribute("errorMessage", "UserName/Password does not exist. Login Again");
					return new ModelAndView("userNotFound");
				}

			} else if (ltype.equals(enduser)) {
				List<Sports> spList = sportsDAO.list();
				return new ModelAndView("userPage", "spList", spList);
			} else if (ltype.equals(servicep)) {
				Person user = (Person) session.getAttribute("spuser");
				if (user == null) {
					session.setAttribute("errorMessage", "UserName/Password does not exist. Login Again");
					return new ModelAndView("userNotFound");
				} else {
					return new ModelAndView("serviceproviderPage", "user", user);
				}
			} else {
				session.setAttribute("errorMessage", "UserName/Password does not exist. Login Again");
				return new ModelAndView("userNotFound");
			}
		}
	}

	// signin - Post
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	protected ModelAndView signInUser(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		try {

			Booking bk = bookingDAO.get();
			if (bk == null) {
				System.out.println("Booking");
			}
			SportsAcademy spac = spacDAO.get();
			if (spac == null) {
				System.out.println("Booking");
			}

			String loginType = request.getParameter("loginType");
			if (loginType.equalsIgnoreCase("Admin")) {
				Person user = userDao.get(request.getParameter("email"), request.getParameter("password"),
						request.getParameter("loginType"));

				if (user == null) {
					System.out.println("UserName/Password does not exist");
					session.setAttribute("errorMessage", "UserName/Password does not exist");
					return new ModelAndView("userNotFound");
				}
				session.setAttribute("cuser", user);
				session.setAttribute("ltype", request.getParameter("loginType"));
				return new ModelAndView("admin", "user", user);

			} else if (loginType.equalsIgnoreCase("User")) {

				List<Sports> spList = sportsDAO.list();
				Enduser user = euserDao.get(request.getParameter("email"), request.getParameter("password"),
						loginType);

				if (user == null) {
					System.out.println("UserName/Password does not exist");
					session.setAttribute("errorMessage", "UserName/Password does not exist");
					return new ModelAndView("userNotFound");
				}
				session.setAttribute("ltype", request.getParameter("loginType"));
				session.setAttribute("Enduser", user);
				return new ModelAndView("userPage", "spList", spList);
			} else {

				Serviceprovider user = svcprDAO.get(request.getParameter("email"), request.getParameter("password"),
						request.getParameter("loginType"));

				if (user == null) {
					System.out.println("UserName/Password does not exist");
					session.setAttribute("errorMessage", "UserName/Password does not exist");
					return new ModelAndView("userNotFound");
				}
				Academy acad = academyDAO.getAcademy(user.getAcadId());
				if (acad == null) {
					session.setAttribute("errorMessage", "Issue in Sigin");
					return new ModelAndView("userNotFound");
				}

				List<Sports> spList = sportsDAO.list();
				session.setAttribute("spList", spList);
				session.setAttribute("Academyobj", acad);
				session.setAttribute("spuser", user);
				session.setAttribute("ltype", request.getParameter("loginType"));
				return new ModelAndView("serviceproviderPage", "user", user);
			}

		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			session.setAttribute("errorMessage", "error while login");
			return new ModelAndView("userNotFound");

		}
	}

	// User SignUp - Get
	@RequestMapping(value = "/user/signup", method = RequestMethod.GET)
	protected String signUpUse(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		try {
			Enduser user = euserDao.signup(request.getParameter("firstName"), request.getParameter("lastName"),
					request.getParameter("age"), request.getParameter("emailId"), request.getParameter("contactNo"),
					request.getParameter("userName"), request.getParameter("password"),
					request.getParameter("loginType"));
			if (user == null) {
				System.out.println("Issue While Creating User at this point .Try again Later");
				session.setAttribute("errorMessage", "Issue While Creating User at this point .Try again Later");
				return "error";
			}
			session.setAttribute("cuser", user);
			return "home";

		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			session.setAttribute("errorMessage", "error while SignUp");
			return "error";
		}

	}
	
	// Academy check
	@RequestMapping(value = "/user/signup", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	protected @ResponseBody String usercheck(HttpServletRequest request) throws Exception {
		Person user =  userDao.getnew(request.getParameter("aid1"));
		if (user != null) {
			return "Email Already Registered";
		} else {
			return "";
		}
	}
	
	
	

	// User signup - Post
	@RequestMapping(value = "/user/signup", method = RequestMethod.POST)
	protected ModelAndView signUpUser(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		try {
			Person puser =  userDao.getnew(request.getParameter("emailId"));
			if (puser==null){
			Enduser user = euserDao.signup(request.getParameter("firstName"), request.getParameter("lastName"),
					request.getParameter("age"), request.getParameter("emailId"), request.getParameter("contactNo"),
					request.getParameter("userName"), request.getParameter("password"),
					request.getParameter("loginType"));
			if (user == null) {
				System.out.println("Issue While Creating User at this point .Try again Later");
				session.setAttribute("errorMessage", "Issue While Creating User at this point .Try again Later");
				return  new ModelAndView("error");
			}
			List<Sports> spList = sportsDAO.list();
			session.setAttribute("ltype", request.getParameter("loginType"));
			session.setAttribute("Enduser", user);
			return new ModelAndView("userPage", "spList", spList);			
			}
			else {
				session.setAttribute("errorMessage", "Email Already Registered");
				return  new ModelAndView("error");
				
			}

		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			session.setAttribute("errorMessage", "error while SignUp");
			return  new ModelAndView("error");
		}

	}

}
