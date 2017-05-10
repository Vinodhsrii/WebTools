package com.beongame.top.controllers;

import java.util.ArrayList;
import java.util.List;

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
import com.beongame.top.dao.EnduserDAO;
import com.beongame.top.dao.ServiceproviderDAO;
import com.beongame.top.dao.SportsDAO;
import com.beongame.top.dao.SportsacademyDAO;
import com.beongame.top.dao.UserDAO;
import com.beongame.top.exception.UserException;
import com.beongame.top.pojo.Academy;
import com.beongame.top.pojo.Person;
import com.beongame.top.pojo.Serviceprovider;
import com.beongame.top.pojo.Sports;
import com.beongame.top.pojo.SportsAcademy;

@Controller
@RequestMapping("/serviceprovider/*")
public class Service {

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

	// Create slots - GET

	@RequestMapping(value = "serviceprovider/sportsCreate", method = RequestMethod.GET)
	protected ModelAndView signUpUse(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		if (session.getAttribute("ltype") == null) {
			return new ModelAndView("redirect-Page");
		} else {
			String ltype = (String) session.getAttribute("ltype");
			String serviceProvider = "ServiceProvider";
			if (ltype.equals(serviceProvider)) {
				Person user = (Person) session.getAttribute("spuser");
				if (user != null) {
					session.setAttribute("SuccessMessage", "Successfully Created Slots for your academy");
					return new ModelAndView("success");
				} else {
					return new ModelAndView("redirect-Page");
				}
			} else {
				return new ModelAndView("redirect-Page");
			}
		}
	}
	
	

	@RequestMapping(value = "serviceprovider/update", method = RequestMethod.GET)
	protected ModelAndView update(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		if (session.getAttribute("ltype") == null) {
			return new ModelAndView("redirect-Page");
		} else {
			String ltype = (String) session.getAttribute("ltype");
			String serviceProvider = "ServiceProvider";
			if (ltype.equals(serviceProvider)) {
				String str1 = request.getParameter("spID");
				try {
					List<SportsAcademy> sportslist = spacDAO.list();
					for (SportsAcademy sa : sportslist) {
						String str2 = String.valueOf(sa.getSpID());
						if (str2.equals(str1)) {
							spacDAO.delete(sa);
						}
					}
					
					session.setAttribute("SuccessMessage", "You successfully deleted a slot");
					return new ModelAndView("success");

				} catch (UserException e) {
					System.out.println("Exception: " + e.getMessage());
					session.setAttribute("errorMessage", "Error while deleting your Slot.Please Try again later");
					return new ModelAndView("error");
				}
				
			} else {
				return new ModelAndView("redirect-Page");
			}
		}
	}
	

	@RequestMapping(value = "serviceprovider/addorDelete", method = RequestMethod.GET)
	protected ModelAndView addorDelget(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		if (session.getAttribute("ltype") == null) {
			return new ModelAndView("redirect-Page");
		} else {
			try {
				String viewValue = String.valueOf(session.getAttribute("optradio"));
				if (viewValue.equalsIgnoreCase("sp")) {
					Person person = (Person) session.getAttribute("spuser");
					Serviceprovider user = svcprDAO.getObjsp(person.getPersonID());
					Academy acad = academyDAO.getAcademy(user.getAcadId());
					if (acad == null) {
						session.setAttribute("errorMessage", "Issue with fetching results");
						return new ModelAndView("userNotFound");
					}
					List<Sports> spList = sportsDAO.list();
					session.setAttribute("spList", spList);
					session.setAttribute("Academyobj", acad);
					return new ModelAndView("serviceProviderCreate", "user", user);

				} else {

					Person person = (Person) session.getAttribute("spuser");
					Serviceprovider user = svcprDAO.getObjsp(person.getPersonID());
					Academy acad = academyDAO.getAcademy(user.getAcadId());
					String str1 = String.valueOf(acad.getAcademyID());
					try {
						List<SportsAcademy> sportslist = spacDAO.list();
						List<SportsAcademy> sportslistnew = new ArrayList<SportsAcademy>();
						for (SportsAcademy sa : sportslist) {
							String str2 = String.valueOf(sa.getAcademy().getAcademyID());
							if (str2.equals(str1)) {
								sportslistnew.add(sa);
							}
						}
						return new ModelAndView("slotsView-sp", "splist", sportslistnew);
					} catch (UserException e) {
						System.out.println("Exception: " + e.getMessage());
						session.setAttribute("errorMessage", "error while Adding Sports");
						return new ModelAndView("error");
					}
				}
			} catch (UserException e) {
				System.out.println("Exception: " + e.getMessage());
				session.setAttribute("errorMessage", "error while Pulling data from DB");
				return new ModelAndView("error");
			}			
		}
		
	}
	
	
	@RequestMapping(value = "serviceprovider/addorDelete", method = RequestMethod.POST)
	protected ModelAndView addorDel(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		try {
			String viewValue = request.getParameter("optradio");
			session.setAttribute("optradio", viewValue);
			if (viewValue.equalsIgnoreCase("sp")) {

				Person person = (Person) session.getAttribute("spuser");
				Serviceprovider user = svcprDAO.getObjsp(person.getPersonID());
				Academy acad = academyDAO.getAcademy(user.getAcadId());
				if (acad == null) {
					session.setAttribute("errorMessage", "Issue with fetching results");
					return new ModelAndView("userNotFound");
				}
				List<Sports> spList = sportsDAO.list();
				session.setAttribute("spList", spList);
				session.setAttribute("Academyobj", acad);
				return new ModelAndView("serviceProviderCreate", "user", user);

			} else {

				Person person = (Person) session.getAttribute("spuser");
				Serviceprovider user = svcprDAO.getObjsp(person.getPersonID());
				Academy acad = academyDAO.getAcademy(user.getAcadId());
				String str1 = String.valueOf(acad.getAcademyID());
				try {
					List<SportsAcademy> sportslist = spacDAO.list();
					List<SportsAcademy> sportslistnew = new ArrayList<SportsAcademy>();
					for (SportsAcademy sa : sportslist) {
						String str2 = String.valueOf(sa.getAcademy().getAcademyID());
						if (str2.equals(str1)) {
							sportslistnew.add(sa);
						}
					}
					return new ModelAndView("slotsView-sp", "splist", sportslistnew);
				} catch (UserException e) {
					System.out.println("Exception: " + e.getMessage());
					session.setAttribute("errorMessage", "error while Adding Sports");
					return new ModelAndView("error");
				}
			}
		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			session.setAttribute("errorMessage", "error while Pulling data from DB");
			return new ModelAndView("error");
		}
	}

	// Create slots - Post
	@RequestMapping(value = "serviceprovider/sportsCreate", method = RequestMethod.POST)
	protected String signUpUser(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		int noSlots = Integer.parseInt(request.getParameter("spslots"));
		while (noSlots != 0) {
			try {
				SportsAcademy spaceacad = spacDAO.addslots(request.getParameter("spprice"), String.valueOf(noSlots),
						request.getParameter("spname"), (Academy) session.getAttribute("Academyobj"));
				if (spaceacad == null) {
					System.out.println("Issue While Creating User at this point .Try again Later");
					session.setAttribute("errorMessage", "Issue While Creating User at this point .Try again Later");
					return "error";
				}

			} catch (UserException e) {
				System.out.println("Exception: " + e.getMessage());
				session.setAttribute("errorMessage", "error while SignUp");
				return "error";
			}
			noSlots--;
		}
		session.setAttribute("SuccessMessage", "Successfully Created Slots for your academy");
		return "success";
	}

}
