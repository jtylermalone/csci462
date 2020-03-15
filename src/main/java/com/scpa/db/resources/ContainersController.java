package com.scpa.db.resources;

import com.scpa.db.model.Containers;
import com.scpa.db.repository.ContainersRepository;
import com.scpa.db.model.Employees;
import com.scpa.db.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

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

    @Autowired
    ContainersRepository containersRepository;

    @Autowired
    EmployeesRepository employeesRepository;

    @GetMapping("/")
    public ModelAndView index(Model model) {
        List<Containers> containers = containersRepository.findAll();
        ModelAndView mav = new ModelAndView();
        mav.addObject("containers", containers);
        mav.setViewName("index");
        return mav;
    }

    @GetMapping("/show")
    public ModelAndView show(Model model) throws ParseException {
       
        //model.addAttribute("containers", containers);
        ModelAndView mav = new ModelAndView();
        //mav.addObject("containers", containers);
        mav.setViewName("show");
        return mav;
       // return new ModelAndView("show", "users", model);
    }

    @GetMapping("/productivity")
    public ModelAndView productivity(Model model) {
        List<Employees> employees = employeesRepository.findEmployees();
        ModelAndView mav = new ModelAndView();
        mav.addObject("employees", employees);
        mav.setViewName("productivity");
        return mav;
    }
    
    @PostMapping("/retrieve")
    @ResponseBody
    public String retrieve(HttpServletRequest req) throws ParseException {
        System.out.println("---------- IN RETRIEVE ----------");
        String begin_date = req.getParameter("begin_date");
        System.out.println("!!!!!!!! begin_date: " + begin_date);
        Date from_date = new SimpleDateFormat("yyyy-MM-dd").parse(begin_date);
        String end_date = req.getParameter("end_date");
        Date to_date = new SimpleDateFormat("yyyy-MM-dd").parse(end_date);
        System.out.println("from_date: " + from_date);
        System.out.println("to_date: " + to_date);
        Integer crane_number = Integer.parseInt(req.getParameter("crane_number"));
        System.out.println("crane_number: " + crane_number);
        Integer old_ship_number = Integer.parseInt(req.getParameter("current_ship_number"));
        System.out.println("old_ship_number: " + old_ship_number);
        Integer old_badge_number = Integer.parseInt(req.getParameter("current_badge_number"));
        System.out.println("current_badge_number: " + old_badge_number);
        List<Containers> containersList = containersRepository.findByTransmittedDatetimeBetween(from_date, to_date, old_badge_number, old_ship_number, crane_number);
        if (containersList.isEmpty())
            System.out.println("************************list is empty!");
        else 
            System.out.println("NOT EMPTY!");
        String return_string = "<thead><tr><th>Crane Number</th><th>Ship Number</th><th>Badge Number</th><th>Transmitted Datetime</th><th>Driver Shift Number</th></tr></thead><tbody><div id='retrieved'>";
        for (Containers container : containersList) {
            return_string = return_string + "<tr><td>" + container.getCraneNumber() + "</td>";
            return_string = return_string + "<td>" + container.getShipNumber() + "</td>";
            return_string = return_string + "<td>" + container.getBadgeNumber() + "</td>";
            return_string = return_string + "<td>" + container.getTransmittedDatetime() + "</td>";
            return_string = return_string + "<td>" + container.getDriverShiftNumber() + "</td></tr>";
        }
        return_string = return_string + "</div></tbody>";
        System.out.println("return_string: " + return_string);
        return return_string;
    }



    @PostMapping("/edit")
    @ResponseBody
    public List<Object> edit(HttpServletRequest req) throws ParseException {
        /*
        String begin_date = req.getParameter("dateBeginning");
        Date from_date = new SimpleDateFormat("yyyy/MM/dd").parse(begin_date);
        String end_date = req.getParameter("dateEnd");
        Date to_date = new SimpleDateFormat("yyyy/MM/dd").parse(end_date);
        Integer crane_number = Integer.parseInt(req.getParameter("craneNumber"));
        Integer old_ship_number = Integer.parseInt(req.getParameter("cVesselNumber"));
        Integer new_ship_number = Integer.parseInt(req.getParameter("nVesselNumber"));
        Integer old_badge_number = Integer.parseInt(req.getParameter("cBadgeNumber"));
        Integer new_badge_number = Integer.parseInt(req.getParameter("nBadgeNumber"));
        List<Containers> containersList = containersRepository.findByTransmittedDatetimeBetween(from_date, to_date, old_badge_number, old_ship_number, crane_number);
        */
        String begin_date = req.getParameter("begin_date");
        Date from_date = new SimpleDateFormat("yyyy-MM-dd").parse(begin_date);
        String end_date = req.getParameter("end_date");
        Date to_date = new SimpleDateFormat("yyyy-MM-dd").parse(end_date);
        System.out.println("from_date: " + from_date);
        System.out.println("to_date: " + to_date);
        Integer crane_number = Integer.parseInt(req.getParameter("crane_number"));
        System.out.println("crane_number: " + crane_number);
        Integer old_ship_number = Integer.parseInt(req.getParameter("current_ship_number"));
        System.out.println("old_ship_number: " + old_ship_number);
        Integer new_ship_number = Integer.parseInt(req.getParameter("new_ship_number"));
        System.out.println("new_ship_number: " + new_ship_number);
        Integer old_badge_number = Integer.parseInt(req.getParameter("current_badge_number"));
        System.out.println("current_badge_number: " + old_badge_number);
        Integer new_badge_number = Integer.parseInt(req.getParameter("new_badge_number"));
        System.out.println("new_badge_number: " + new_badge_number);
        List<Containers> containersList = containersRepository.findByTransmittedDatetimeBetween(from_date, to_date, old_badge_number, old_ship_number, crane_number);
        Integer number_of_updated_entries = 0;
        for (Containers container : containersList){
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
        System.out.println("return_string: " + return_string);
        List<Object> return_list = new ArrayList<Object>();
        return_list.add(return_string);
        return_list.add(number_of_updated_entries);
        System.out.println("---------- RETURNING ----------");
        return return_list;
    }

    @PostMapping("/create")
    public ModelAndView create(HttpServletRequest req){
        // badge_number, transmitted_datetime, driver_shift_number
        Integer crane_number = Integer.parseInt(req.getParameter("crane_number"));
        Integer ship_number = Integer.parseInt(req.getParameter("ship_number"));
        Integer badge_number = Integer.parseInt(req.getParameter("badge_number"));
        Integer driver_shift_number = Integer.parseInt(req.getParameter("driver_shift_number"));
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