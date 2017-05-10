package com.beongame.top.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.mail.NoSuchProviderException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.beongame.top.dao.AcademyDAO;
import com.beongame.top.dao.BookingDAO;
import com.beongame.top.dao.ServiceproviderDAO;
import com.beongame.top.dao.SportsDAO;
import com.beongame.top.dao.SportsacademyDAO;
import com.beongame.top.exception.UserException;
import com.beongame.top.pojo.Academy;
import com.beongame.top.pojo.Booking;
import com.beongame.top.pojo.Email;
import com.beongame.top.pojo.Enduser;
import com.beongame.top.pojo.Person;
import com.beongame.top.pojo.Serviceprovider;
import com.beongame.top.pojo.SportsAcademy;
import com.beongame.top.random.EmailDetails;

@Controller
@RequestMapping("/enduser/*")
public class Endusercontroller {

	@Autowired
	@Qualifier("academyDAO")
	AcademyDAO academyDAO;

	@Autowired
	@Qualifier("spacDAO")
	SportsacademyDAO spacDAO;

	@Autowired
	@Qualifier("bookingDAO")
	BookingDAO bookingDAO;

	@Autowired
	@Qualifier("svcprDAO")
	ServiceproviderDAO svcprDAO;

	// Add Sports

	@Autowired
	@Qualifier("sportsDAO")
	SportsDAO sportsDAO;

	@RequestMapping(value = "enduser/academySearch", method = RequestMethod.GET)
	protected ModelAndView searchAcademy1(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();

		if (session.getAttribute("ltype") == null) {
			return new ModelAndView("redirect-Page");
		} else {

			String ltype = (String) session.getAttribute("ltype");
			String enduser = "User";

			if (ltype.equals(enduser)) {
				Person user = (Person) session.getAttribute("Enduser");
				if (user != null) {
					try {
						List<SportsAcademy> sportslist = spacDAO.list();
						List<Academy> a = new ArrayList<Academy>();
						for (SportsAcademy sa : sportslist) {
							if (!(a.contains(sa.getAcademy()))) {
								String str = (String) session.getAttribute("searchSport");
								String st1 = sa.getGame();
								if (st1.equals(str)) {
									a.add(sa.getAcademy());
								}
							}
						}
						if (a.isEmpty()) {
							session.setAttribute("errorMessage",
									"No Academy existis for the given sport. We are working hard to include More academy of your interest");
							return new ModelAndView("sorry-Page");
						} else {
							return new ModelAndView("academySearch", "alist", a);
						}
					} catch (UserException e) {
						System.out.println("Exception: " + e.getMessage());
						session.setAttribute("errorMessage", "error while Adding Sports");
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

	@RequestMapping(value = "enduser/academySearch", method = RequestMethod.POST)
	protected ModelAndView searchAcademy(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		try {

			List<SportsAcademy> sportslist = spacDAO.list();
			List<Academy> a = new ArrayList<Academy>();
			for (SportsAcademy sa : sportslist) {
				if (!(a.contains(sa.getAcademy()))) {
					String str = request.getParameter("sportname");
					session.setAttribute("searchSport", str);
					String st1 = sa.getGame();
					if (st1.equals(str)) {
						a.add(sa.getAcademy());
					}
				}
			}
			if (a.isEmpty()) {
				session.setAttribute("errorMessage",
						"No Academy existis for the given sport. We are working hard to include More academy of your interest");
				return new ModelAndView("sorry-Page");
			} else {
				return new ModelAndView("academySearch", "alist", a);
			}

		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			session.setAttribute("errorMessage", "error while Adding Sports");
			return new ModelAndView("error");
		}

	}

	@RequestMapping(value = "enduser/selectSport", method = RequestMethod.GET)
	protected ModelAndView UpdateSports(HttpServletRequest request) throws NoSuchProviderException {
		HttpSession session = (HttpSession) request.getSession();
		if (session.getAttribute("ltype") == null) {
			return new ModelAndView("redirect-Page");
		} else {
			String str1 = request.getParameter("spID");
			String ltype = (String) session.getAttribute("ltype");
			String enduser = "User";
			if (ltype.equals(enduser)) {
				Person user = (Person) session.getAttribute("Enduser");
				if (user != null) {
					try {
						List<SportsAcademy> sportslist = spacDAO.list();
						SportsAcademy tmp = new SportsAcademy();
						Academy acad = new Academy();
						Booking bo = new Booking();
						boolean flag = false;
						for (SportsAcademy sa : sportslist) {
							String str2 = String.valueOf(sa.getSpID());
							if (str2.equals(str1)) {
								sa.setAvailability("N");
								spacDAO.save(sa);
								flag = true;
								tmp = sa;
								acad = sa.getAcademy();
							}
						}
						if (flag == true) {
							Enduser eu = (Enduser) session.getAttribute("Enduser");
							Serviceprovider spID = svcprDAO.getObj(acad);
							bo = bookingDAO.addbooking(eu, tmp, spID);
							String message = "Congratz! Your booking has been confirmed with "+acad.getAcademyName()+ ". Thank you for using BeOnGame.";
							// Send Mail

							Email emails = new Email();
							emails.setFromEmail("vinodhsrii@gmail.com");
							emails.setToEmail(user.getEmailId());
							emails.setSubject("Booking Confirmation");
							emails.setMessage(message);
							EmailDetails email = new EmailDetails();
							email.sendMail(emails);							
						}

						if (bo == null) {
							System.out.println("Issue while booking .Try again Later");
							session.setAttribute("errorMessage", "Issue while booking .Try again Later");
							return new ModelAndView("sorry-Page");
						}
						
						
						
						session.setAttribute("SuccessMessage", "You successfully made a booking. Just Be -on- Game.");
						return new ModelAndView("success");

					} catch (UserException e) {
						System.out.println("Exception: " + e.getMessage());
						session.setAttribute("errorMessage", "Error while Booking your Slot.Please Try again later");
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

	@RequestMapping(value = "enduser/sportsSearch", method = RequestMethod.GET)
	protected ModelAndView searchSports(HttpServletRequest request) {
		HttpSession session = (HttpSession) request.getSession();
		if (session.getAttribute("ltype") == null) {
			return new ModelAndView("redirect-Page");
		} else {
			String str1 = request.getParameter("acadID");
			String ltype = (String) session.getAttribute("ltype");
			String enduser = "User";
			if (ltype.equals(enduser)) {
				Person user = (Person) session.getAttribute("Enduser");
				if (user != null) {
					try {
						List<SportsAcademy> sportslist = spacDAO.list();
						List<SportsAcademy> sportslistnew = new ArrayList<SportsAcademy>();
						for (SportsAcademy sa : sportslist) {
							String str2 = String.valueOf(sa.getAcademy().getAcademyID());
							if (str2.equals(str1)) {
								sportslistnew.add(sa);
							}
						}
						return new ModelAndView("slotsView-user", "splist", sportslistnew);
					} catch (UserException e) {
						System.out.println("Exception: " + e.getMessage());
						session.setAttribute("errorMessage", "error while Adding Sports");
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

}
