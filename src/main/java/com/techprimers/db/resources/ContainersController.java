package com.techprimers.db.resources;

import com.techprimers.db.model.Containers;
import com.techprimers.db.repository.ContainersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

//import sun.util.calendar.BaseCalendar.Date;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import java.util.List;


@Controller
@RequestMapping("/containers")
public class ContainersController {

    @Autowired
    ContainersRepository containersRepository;

    @GetMapping("/")
    public ModelAndView index(Model model) {
        List<Containers> containers = containersRepository.findAll();
        ModelAndView mav = new ModelAndView();
        mav.addObject("containers", containers);
        mav.setViewName("show");
        return mav;
    }

    @GetMapping("/show")
    public ModelAndView show(Model model){
        List<Containers> containers = containersRepository.findAll();
        //ModelAndView mav = new ModelAndView();
        model.addAttribute("containers", containers);
        return new ModelAndView("show", "users", model);
    }

    @PostMapping("/edit")
    public ModelAndView edit(HttpServletRequest req){
        List<Containers> containersList = containersRepository.findById(Integer.parseInt(req.getParameter("id")));
        
        for (Containers container : containersList){
            container.setId(Integer.parseInt(req.getParameter("id")));
            container.setCraneNumber(Integer.parseInt(req.getParameter("crane_number")));
            container.setShipNumber(Integer.parseInt(req.getParameter("ship_number")));
            container.setBadgeNumber(Integer.parseInt(req.getParameter("badge_number")));
            container.setTransmittedDatetime(new Date());

            container.setDriverShiftNumber(Integer.parseInt(req.getParameter("driver_shift_number")));
            containersRepository.save(container);
        }
        List<Containers> containers = containersRepository.findAll();
        ModelAndView mav = new ModelAndView();
        mav.addObject("containers", containers);
        mav.setViewName("show");
        return mav;
        
    }

    @PostMapping("/delete")
    public ModelAndView delete (HttpServletRequest req){
        List<Containers> containersList = containersRepository.findById(Integer.parseInt(req.getParameter("id")));
        for (Containers container : containersList) {
            containersRepository.delete(container);
        }
        List<Containers> containers = containersRepository.findAll();
        ModelAndView mav = new ModelAndView();
        mav.addObject("containers", containers);
        mav.setViewName("show");
        return mav;
    }
    /*
    @PostMapping("/find")
    public List<Users> find(HttpServletRequest req){
        List<users 
    }
    */
    @PostMapping("/create")
    public ModelAndView create(HttpServletRequest req){
        // badge_number, transmitted_datetime, driver_shift_number
        Integer crane_number = Integer.parseInt(req.getParameter("crane_number"));
        Integer ship_number = Integer.parseInt(req.getParameter("ship_number"));
        Integer badge_number = 17;
        Date transmitted_datetime = new Date();
        Integer driver_shift_number = Integer.parseInt(req.getParameter("driver_shift_number"));
        Containers new_container = new Containers(crane_number, ship_number, badge_number, transmitted_datetime, driver_shift_number);
        containersRepository.save(new_container);
        ModelAndView mav = new ModelAndView();
        List<Containers> containers = containersRepository.findAll();
        mav.addObject("containers", containers);
        mav.setViewName("show");
        return mav;
    }
    /*
    @GetMapping("/all")
    public List<Users> getAll() {
        return usersRepository.findAll();
    }

    @PostMapping("/load")
    public List<Users> persist(@RequestBody final Users users) {
        usersRepository.save(users);
        return usersRepository.findAll();
    }
    */

}