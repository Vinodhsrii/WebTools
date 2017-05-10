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
import com.beongame.top.dao.ServiceproviderDAO;
import com.beongame.top.dao.SportsDAO;
import com.beongame.top.exception.UserException;
import com.beongame.top.pojo.Academy;
import com.beongame.top.pojo.Email;
import com.beongame.top.pojo.Person;
import com.beongame.top.pojo.Serviceprovider;
import com.beongame.top.pojo.Sports;
import com.beongame.top.random.EmailDetails;

@Controller
@RequestMapping("/admin/*")
public class Admincontroller {

	@Autowired
	@Qualifier("svcprDAO")
	ServiceproviderDAO svcprDAO;

	@Autowired
	@Qualifier("sportsDAO")
	SportsDAO sportsDAO;

	@Autowired
	@Qualifier("academyDAO")
	AcademyDAO academyDAO;

	// // Add Sports - Get
	@RequestMapping(value = "admin/sportsCreates", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	protected @ResponseBody String createSportaj(HttpServletRequest request) throws Exception {
		Sports check = sportsDAO.checkSports(request.getParameter("aid"));
		if (check != null) {
			return "Sports already exists";
		} else {
			return "";
		}
	}

	// Add Sports - Get
	@RequestMapping(value = "admin/sportsCreate", method = RequestMethod.GET)
	protected String createSport(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();

		if (session.getAttribute("ltype") == null) {
			return "redirect-Page";
		} else {
			String ltype = (String) session.getAttribute("ltype");
			String ladmin = "Admin";
			if (ltype.equals(ladmin)) {
				Person user = (Person) session.getAttribute("cuser");
				if (user != null) {
					session.setAttribute("SuccessMessage", "Sports added successfully");
					return "success";
				} else {
					return "redirect-Page";
				}
			} else {
				return "redirect-Page";
			}
		}

	}

	// Add Sports - Post

	@RequestMapping(value = "admin/sportsCreate", method = RequestMethod.POST)
	protected String createSports(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		try {
			Sports check = sportsDAO.checkSports(request.getParameter("spName"));
			if (check == null) {
				Sports game = sportsDAO.addSports(request.getParameter("spName"));
				if (game == null) {
					session.setAttribute("errorMessage", "Issue while adding Sports at this point .Try again Later");
					return "error";
				}

				session.setAttribute("sports", request.getParameter("spName"));
				session.setAttribute("SuccessMessage", "Sports added successfully");
				return "success";
			} else {
				session.setAttribute("errorMessage", "Sorry ! This sport already exists in the DB.");
				return "error";
			}

		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			session.setAttribute("errorMessage", "error while Adding Sports");
			return "error";
		}

	}

	// Email check
	@RequestMapping(value = "admin/serviceprovidersignups", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	protected @ResponseBody String emailcheck(HttpServletRequest request) throws Exception {

		Person p = svcprDAO.checkEmail(request.getParameter("aid1"));
		;
		if (p != null) {
			return "EmailID Not Available";
		} else {
			return "";
		}
	}

	// Academy check
	@RequestMapping(value = "admin/serviceprovidersignupss", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	protected @ResponseBody String academycheck(HttpServletRequest request) throws Exception {

		Academy check = academyDAO.checkAcademy1(request.getParameter("aid2"));
		if (check != null) {
			return "Academy Already Exists";
		} else {
			return "";
		}
	}

	// Add ServiceProvider - Get

	@RequestMapping(value = "admin/serviceprovidersignup", method = RequestMethod.GET)
	protected ModelAndView createServiceProv(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		if (session.getAttribute("ltype") == null) {
			return new ModelAndView("redirect-Page");
		} else {

			String ltype = (String) session.getAttribute("ltype");
			String ladmin = "Admin";
			if (ltype.equals(ladmin)) {
				Person user = (Person) session.getAttribute("cuser");
				if (user != null) {
					session.setAttribute("SuccessMessage", "Sports added successfully");
					return new ModelAndView("success");
				} else {
					return new ModelAndView("redirect-Page");
				}
			} else {
				return new ModelAndView("redirect-Page");
			}
		}

	}

	// Add ServiceProvider - Post
	@RequestMapping(value = "admin/serviceprovidersignup", method = RequestMethod.POST)
	protected String createServiceProvider(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		try {
			Academy check = academyDAO.checkAcademy(request.getParameter("acadName"), request.getParameter("acadLoc"));
			if (check == null) {
				Person p = svcprDAO.scheck(request.getParameter("emailId"));
				if (p == null) {
					Academy acad = academyDAO.addAcademy(request.getParameter("acadName"),
							request.getParameter("acadLoc"));
					if (acad == null) {
						System.out.println("Issue while creating Service Provider at this point .Try again Later");
						session.setAttribute("errorMessage",
								"Issue while creating Service Provider at this point .Try again Later");
						return "error";
					} else {

						Serviceprovider sp = svcprDAO.signup(request.getParameter("firstName"),
								request.getParameter("lastName"), request.getParameter("age"),
								request.getParameter("emailId"), request.getParameter("contactNo"),
								 request.getParameter("password"),
								request.getParameter("loginType"), acad);

						if (sp == null) {
							System.out.println("Issue While Creating Service Provider at this point .Try again Later");
							session.setAttribute("errorMessage",
									"Issue While Creating User at this point .Try again Later");
							return "error";
						}

						String message = "BeOngame welcomes you. Thanks for registering with Beongame.Please find your credentials. "
								+ " EmailID: " + request.getParameter("emailId") + " and " + " Password :"
								+ request.getParameter("password");
						// Send Mail

						Email emails = new Email();
						emails.setFromEmail("vinodhsrii@gmail.com");
						emails.setToEmail(request.getParameter("emailId"));
						emails.setSubject("SignIn Credentials");
						emails.setMessage(message);
						EmailDetails email = new EmailDetails();
						email.sendMail(emails);

						session.setAttribute("spuser", sp);
						session.setAttribute("SuccessMessage", "Service Provider added successfully");
						return "success";

					}
				} else {
					session.setAttribute("errorMessage",
							"Sorry ! Service Provider / User Details already exists in DB");
					return "error";
				}

			} else {
				session.setAttribute("errorMessage", "Sorry ! Academy already exists");
				return "error";

			}
		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			session.setAttribute("errorMessage", "error while Adding Service Provider");
			return "error";
		}

	}
	// View Sports / Academy - Get

	@RequestMapping(value = "admin/view", method = RequestMethod.GET)
	protected ModelAndView views(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		if (session.getAttribute("ltype") == null) {
			return new ModelAndView("redirect-Page");
		} else {
			String ltype = (String) session.getAttribute("ltype");
			String ladmin = "Admin";
			if (ltype.equals(ladmin)) {
				Person user = (Person) session.getAttribute("cuser");
				if (user != null) {
					try {
						String viewValue = (String) session.getAttribute("optradio");
						if (viewValue.equalsIgnoreCase("sp")) {
							List<Sports> sports = sportsDAO.list();
							if (sports == null) {
								System.out.println("Issue while retriving Sports at this point .Try again Later");
								session.setAttribute("errorMessage",
										"Issue while adding Sports at this point .Try again Later");
								return new ModelAndView("error");
							}

							return new ModelAndView("search-result", "spList", sports);
						} else {
							List<Academy> academy = academyDAO.list();
							if (academy == null) {
								System.out.println("Issue while retriving Academy at this point .Try again Later");
								session.setAttribute("errorMessage",
										"Issue while adding Academy at this point .Try again Later");
								return new ModelAndView("error");
							}
							return new ModelAndView("academyList", "acList", academy);
						}
					} catch (UserException e) {
						System.out.println("Exception: " + e.getMessage());
						session.setAttribute("errorMessage", "error while Pulling data from DB");
						return new ModelAndView("error");
					}
				} else {
					return new ModelAndView("redirect-Page");
				}
			} else {
				return new ModelAndView("redirect-Page");
			}
		}

	}

	// View Sports / Academy - Post
	@RequestMapping(value = "admin/view", method = RequestMethod.POST)
	protected ModelAndView view(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		try {
			String viewValue = request.getParameter("optradio");
			if (viewValue.equalsIgnoreCase("sp")) {
				List<Sports> sports = sportsDAO.list();
				if (sports == null) {
					System.out.println("Issue while retriving Sports at this point .Try again Later");
					session.setAttribute("errorMessage", "Issue while adding Sports at this point .Try again Later");
					return new ModelAndView("error");
				}
				// session.setAttribute("sportsset", sports);
				session.setAttribute("optradio", viewValue);
				session.setAttribute("url", "https://drive.google.com/drive/my-drive");
				return new ModelAndView("search-result", "spList", sports);
			} else {
				List<Academy> academy = academyDAO.list();
				if (academy == null) {
					System.out.println("Issue while retriving Academy at this point .Try again Later");
					session.setAttribute("errorMessage", "Issue while adding Academy at this point .Try again Later");
					return new ModelAndView("error");
				}
				// session.setAttribute("academyset", academy);
				session.setAttribute("optradio", viewValue);
				return new ModelAndView("academyList", "acList", academy);
			}
		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			session.setAttribute("errorMessage", "error while Pulling data from DB");
			return new ModelAndView("error");
		}

	}

}
