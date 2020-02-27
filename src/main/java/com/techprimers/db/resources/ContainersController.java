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
    ContainersRepository ContainersRepository;

    @GetMapping("/")
    public ModelAndView index(Model model) {
        List<Containers> containers = ContainersRepository.findAll();
        ModelAndView mav = new ModelAndView();
        mav.addObject("containers", containers);
        mav.setViewName("show");
        return mav;
    }

    @GetMapping("/show")
    public ModelAndView show(Model model){
        List<Containers> containers = ContainersRepository.findAll();
        //ModelAndView mav = new ModelAndView();
        model.addAttribute("containers", containers);
        return new ModelAndView("show", "users", model);
    }

    @PostMapping("/edit")
    public ModelAndView edit(HttpServletRequest req){
        List<Containers> containersList = ContainersRepository.findByName(req.getParameter("record_number"));
        
        for (Containers container : containersList){
            container.setRecordNumber(Integer.parseInt(req.getParameter("record_number")));
            container.setCraneNumber(Integer.parseInt(req.getParameter("crane_number")));
            container.setShipNumber(Integer.parseInt(req.getParameter("ship_number")));
            container.setBadgeNumber(Integer.parseInt(req.getParameter("badge_number")));
            //container.setTransmittedDatetime(new SimpleDateFormat("yyyy/MM/dd HH-mm-ss").parse(req.getParameter("transmitted_datetime")));

            container.setDriverShiftNumber(Double.parseDouble(req.getParameter("driver_shift_number")));
            ContainersRepository.save(container);
        }
        List<Containers> containers = ContainersRepository.findAll();
        ModelAndView mav = new ModelAndView();
        mav.addObject("containers", containers);
        mav.setViewName("show");
        return mav;
        
    }

    @PostMapping("/delete")
    public ModelAndView delete (HttpServletRequest req){
        List<Containers> containersList = ContainersRepository.findByName(req.getParameter("record_number"));
        for (Containers container : containersList) {
            ContainersRepository.delete(container);
        }
        List<Containers> containers = ContainersRepository.findAll();
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
        Integer record_number = Integer.parseInt(req.getParameter("record_number"));
        Integer crane_number = Integer.parseInt(req.getParameter("crane_number"));
        Integer ship_number = Integer.parseInt(req.getParameter("ship_number"));
        Integer badge_number = Integer.parseInt(req.getParameter("badge_humber"));
        Date transmitted_datetime = new Date();
        Double driver_shift_number = Double.parseDouble(req.getParameter("driver_shift_number"));
        Containers new_container = new Containers(record_number, crane_number, ship_number, badge_number, transmitted_datetime, driver_shift_number);
        ContainersRepository.save(new_container);
        ModelAndView mav = new ModelAndView();
        List<Containers> containers = ContainersRepository.findAll();
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