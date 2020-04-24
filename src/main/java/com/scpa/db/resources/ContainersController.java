package com.scpa.db.resources;

import com.scpa.db.model.Containers;
import com.scpa.db.repository.ContainersRepository;
import com.scpa.db.model.Employees;
import com.scpa.db.repository.EmployeesRepository;
import com.scpa.db.model.Vessel;
import com.scpa.db.repository.VesselRepository;
import com.scpa.db.model.Productivity;
import com.scpa.db.repository.ProductivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

//import sun.util.calendar.BaseCalendar.Date;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;


import java.util.List;

@Controller
@RequestMapping("/containers")
public class ContainersController {

    /* The below 4 autowired statements allow us to 
    interact with each repository class. */

    @Autowired
    ContainersRepository containersRepository;

    @Autowired
    EmployeesRepository employeesRepository;

    @Autowired
    VesselRepository vesselRepository;

    @Autowired
    ProductivityRepository productivityRepository;

    @GetMapping("/")
    public ModelAndView index(Model model) {
        List<Containers> containers = containersRepository.findAll();
        ModelAndView mav = new ModelAndView();
        mav.addObject("containers", containers);
        mav.setViewName("index");
        return mav;
    }

    /* This method is used to simply return the "show" view */
    @GetMapping("/show")
    public ModelAndView show(Model model) throws ParseException {
       
        //model.addAttribute("containers", containers);
        ModelAndView mav = new ModelAndView();
        //mav.addObject("containers", containers);
        mav.setViewName("show");
        return mav;
       // return new ModelAndView("show", "users", model);
    }

    /* This method is executed when the user clicks the "Add Record"
    button on the productivity form. It simply creates a new productivity
    object, gets each relevant form field value from the HttpServletRequest,
    and assigns the productivity object's values with the form values. The object
    is then saved into the productivity database. */
    @ResponseBody
    @PostMapping("/submit_productivity")    
    public String submit_productivity(HttpServletRequest req) throws ParseException {

        // This is the object that will ultimately be saved to the
        // database
        Productivity prod_object = new Productivity();

        // getting start date from servlet request
        String start_date_string = req.getParameter("start_date");

        // we must then convert the string to a date object and set the 
        // productivity object's start date value accordingly
        Date start_date = new SimpleDateFormat("yyyy-MM-dd").parse(start_date_string);
        prod_object.setMinOfTransmitted_Datetime(start_date);

        // we do the same as above for the end date
        String end_date_string = req.getParameter("end_date");
        Date end_date = new SimpleDateFormat("yyyy-MM-dd").parse(end_date_string);
        prod_object.setMaxOfTransmittedDatetime(end_date);

        String badge_number = req.getParameter("badge_number");
        prod_object.setBadgeNumber(badge_number);

        String driver_shift_number = req.getParameter("driver_shift_number");
        prod_object.setDriverShiftNumber(driver_shift_number);

        String ship_number = req.getParameter("ship_number");
        prod_object.setShipNumber(ship_number);

        // unsure what to do with hatchcover moves...
        prod_object.setHatchcoverMoves(0);

        String crane_number = req.getParameter("crane_number");
        prod_object.setCraneNumber(crane_number);

        // total driver shifts and moves are integers in the database, so we must
        // parse them into an integer from a string
        String total_driver_shifts_string = req.getParameter("total_driver_shifts");
        Integer total_driver_shifts = Integer.parseInt(total_driver_shifts_string);
        prod_object.setTotalDriverShifts(total_driver_shifts);


        String moves_string = req.getParameter("moves");
        Integer moves = Integer.parseInt(moves_string);
        prod_object.setContMoves(moves);

        Integer calendar_year = end_date.getYear() + 1900;
        prod_object.setCalendarYear(calendar_year);

        Integer calendar_month = end_date.getMonth() + 1;
        prod_object.setCyMonthSort(calendar_month);

        // there is probably a better way to calculate
        // fiscal month here, but this seems to work
        // with every case
        Integer fiscal_month;
        if (calendar_month == 6) {
            fiscal_month = 12;
        }
        else if (calendar_month >= 7) {
            fiscal_month = ((calendar_month - 6) % 12);
        }
        else {
            fiscal_month = (((calendar_month - 6) % 12) + 12);
        }

        prod_object.setFyMonthSort(fiscal_month);

        // again, there's probably a better way to
        // calculate fiscal year, but this seems to
        // work
        Integer fiscal_year;
        if (calendar_month >= 7) {
            fiscal_year = calendar_year + 1;
        }
        else {
            fiscal_year = calendar_year;
        }
        prod_object.setFiscalYear(fiscal_year);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(end_date);
        Integer day_of_week = calendar.get(Calendar.DAY_OF_WEEK);
        System.out.println("day of week: " + day_of_week);
        prod_object.setDayOfWeek(day_of_week);
        productivityRepository.save(prod_object);
        return "test";
    }

    /* This method is used to return the productivity template. In order to
    populate the employee name dropdown menu, we are querying the employees
    table and putting each employee whose department number is 12 in a list
    and returning that list along with the template. */
    @GetMapping("/productivity")
    public ModelAndView productivity(Model model) {
        List<Employees> employees = employeesRepository.findEmployees();
        List<String> vessel_codes = vesselRepository.findVesselCodes();
        ModelAndView mav = new ModelAndView();
        mav.addObject("employees", employees);
        mav.addObject("vessel_codes", vessel_codes);
        mav.setViewName("productivity");
        return mav;
    }

    /* This method is used by the productivity form to retrieve the badge number
    of the employee chosen by the dropdown menu. It queries the employees table
    and returns the badge number associated with the name chosen from the
    dropdown menu */
    @PostMapping("/badge")
    @ResponseBody
    public String badge(HttpServletRequest req) throws ParseException {
        //System.out.println("---------- IN GETBADGENUMBER ----------");
        String badge_number = employeesRepository.findBadgeNumber(req.getParameter("name"));
        //System.out.println("badge_number: " + badge_number);
        return badge_number;
    }

    /* This method is used to autopopulate form fields in the
    productivity form. Whenever the user enters a vessel code, 
    that vessel code is passed to this method. The vessel database
    is then queried to get all relevant information about the vessel
    with that vessel code. The information is then put into a list and
    referenced back at the view. */
    @PostMapping("/vessel")
    @ResponseBody
    public List<String> vessel(HttpServletRequest req) throws ParseException {
        Vessel vessel = vesselRepository.findByVesselCode(req.getParameter("vessel_code"));
        System.out.println(vessel);
        List<String> return_list = Arrays.asList(vessel.getVesselName(), vessel.getLineName(), vessel.getLineCode());
        return return_list;
    }
    
    /* This method is used by the show template whenever the user 
    executes a lookup. The user must put in a vessel number, badge number,
    crane number, beginning date, and ending date. These values are then used
    to execute a query of the database. Since we're changing the table in the
    view without refreshing the page, this method returns a string of HTML that 
    replaces the current table html on the page. */
    @PostMapping("/retrieve")
    @ResponseBody
    public String retrieve(HttpServletRequest req) throws ParseException {

        String begin_date = req.getParameter("begin_date");
        Date from_date = new SimpleDateFormat("yyyy-MM-dd").parse(begin_date);
        String end_date = req.getParameter("end_date");
        Date to_date = new SimpleDateFormat("yyyy-MM-dd").parse(end_date);
        String crane_number = req.getParameter("crane_number");
        String old_ship_number = req.getParameter("current_ship_number");
        String old_badge_number = req.getParameter("current_badge_number");
        List<Containers> containersList = containersRepository.findByTransmittedDatetimeBetween(from_date, to_date, old_badge_number, old_ship_number, crane_number);
        String return_string = "<thead><tr><th>Crane Number</th><th>Ship Number</th><th>Badge Number</th><th>Transmitted Datetime</th><th>Driver Shift Number</th></tr></thead><tbody><div id='retrieved'>";
        
        // each time through this loop, we are putting each container's attributes into the 
        // HTML string that will constitute the table
        for (Containers container : containersList) {
            return_string = return_string + "<tr><td>" + container.getCraneNumber() + "</td>";
            return_string = return_string + "<td>" + container.getShipNumber() + "</td>";
            return_string = return_string + "<td>" + container.getBadgeNumber() + "</td>";
            return_string = return_string + "<td>" + container.getTransmittedDatetime() + "</td>";
            return_string = return_string + "<td>" + container.getDriverShiftNumber() + "</td></tr>";
        }
        return_string = return_string + "</div></tbody>";
        return return_string;
    }


    /* This method is executed whenever the user clicks on the "Update Entries"
    button on the show form. It executes the same query as in the "retrieve" method
    above, and puts each matching entry into a list. Each object in the list then has
    its values updated with the values from the HttpServletRequest, and then each
    object is saved back into the database. Then, a new HTML string is constructed
    that will replace the current table HTML on the page. We are also keeping track of
    the number of updated entries, so each time an entry is updated we increment a counter. The
    counter and the HTML string are put into a list and returned to the view. */
    @PostMapping("/edit")
    @ResponseBody
    public List<Object> edit(HttpServletRequest req) throws ParseException {

        String begin_date = req.getParameter("begin_date");
        Date from_date = new SimpleDateFormat("yyyy-MM-dd").parse(begin_date);
        String end_date = req.getParameter("end_date");
        Date to_date = new SimpleDateFormat("yyyy-MM-dd").parse(end_date);
        String crane_number = req.getParameter("crane_number");
        String old_ship_number = req.getParameter("current_ship_number");
        String new_ship_number = req.getParameter("new_ship_number");
        String old_badge_number = req.getParameter("current_badge_number");
        String new_badge_number = req.getParameter("new_badge_number");
        List<Containers> containersList = containersRepository.findByTransmittedDatetimeBetween(from_date, to_date, old_badge_number, old_ship_number, crane_number);
        Integer number_of_updated_entries = 0;
        for (Containers container : containersList) {
            container.setShipNumber(new_ship_number);
            container.setBadgeNumber(new_badge_number);
            containersRepository.save(container);
            number_of_updated_entries = number_of_updated_entries + 1;
        }
        String return_string = "<thead><tr><th>Crane Number</th><th>Ship Number</th><th>Badge Number</th><th>Transmitted Datetime</th><th>Driver Shift Number</th></tr></thead><tbody><div id='retrieved'>";
        for (Containers container : containersList) {
            return_string = return_string + "<tr><td>" + container.getCraneNumber() + "</td>";
            return_string = return_string + "<td>" + container.getShipNumber() + "</td>";
            return_string = return_string + "<td>" + container.getBadgeNumber() + "</td>";
            return_string = return_string + "<td>" + container.getTransmittedDatetime() + "</td>";
            return_string = return_string + "<td>" + container.getDriverShiftNumber() + "</td></tr>";
        }
        return_string = return_string + "</div></tbody>";
        List<Object> return_list = new ArrayList<Object>();
        return_list.add(return_string);
        return_list.add(number_of_updated_entries);
        return return_list;
    }

    @PostMapping("/create")
    public ModelAndView create(HttpServletRequest req){
        String crane_number = req.getParameter("crane_number");
        String ship_number = req.getParameter("ship_number");
        String badge_number = req.getParameter("badge_number");
        String driver_shift_number = req.getParameter("driver_shift_number");
        Date transmitted_datetime = new Date();
        Containers new_container = new Containers(crane_number, ship_number, badge_number, transmitted_datetime, driver_shift_number);
        containersRepository.save(new_container);
        ModelAndView mav = new ModelAndView();
        List<Containers> containers = containersRepository.findAll();
        mav.addObject("containers", containers);
        mav.setViewName("show");
        return mav;
    }
}