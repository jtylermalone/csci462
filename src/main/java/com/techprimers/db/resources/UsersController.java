package com.techprimers.db.resources;

import com.techprimers.db.model.Users;
import com.techprimers.db.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;

import java.util.List;


@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UsersRepository usersRepository;

    @GetMapping("/")
    public ModelAndView index(Model model) {
        List<Users> users = usersRepository.findAll();
        ModelAndView mav = new ModelAndView();
        model.addAttribute("usersList", users);
        mav.addObject("users", users);
        mav.setViewName("show");
        return mav;
    }

    @GetMapping("/show")
    public ModelAndView show(Model model){
        List<Users> users = usersRepository.findAll();
        //ModelAndView mav = new ModelAndView();
        model.addAttribute("users", users);
        return new ModelAndView("show", "users", model);
    }

    @PostMapping("/edit")
    public List<Users> edit(HttpServletRequest req){
        List<Users> userList = usersRepository.findByName(req.getParameter("name"));
        
        for (Users user : userList){
            user.setName(req.getParameter("newname"));
            user.setTeamName(req.getParameter("newteamname"));
            String salaryStr = req.getParameter("newsalary");
            int newSalary = Integer.parseInt(salaryStr);
            user.setSalary(newSalary);
            usersRepository.save(user);
        }
        return usersRepository.findAll();
        
    }

    @PostMapping("/delete")
    public ModelAndView delete (HttpServletRequest req){
        List<Users> userList = usersRepository.findByName(req.getParameter("name"));
        for (Users user : userList) {
            usersRepository.delete(user);
        }
        List<Users> users = usersRepository.findAll();
        ModelAndView mav = new ModelAndView();
        mav.addObject("users", users);
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
        String name = req.getParameter("name");
        String teamName = req.getParameter("teamname");
        String salaryStr = req.getParameter("salary");
        int salary = Integer.parseInt(salaryStr);
        Users newUser = new Users(name, teamName, salary);
        usersRepository.save(newUser);
        ModelAndView mav = new ModelAndView();
        List<Users> users = usersRepository.findAll();
        mav.addObject("users", users);
        mav.setViewName("show");
        return mav;
    }

    @GetMapping("/all")
    public List<Users> getAll() {
        return usersRepository.findAll();
    }

    @PostMapping("/load")
    public List<Users> persist(@RequestBody final Users users) {
        usersRepository.save(users);
        return usersRepository.findAll();
    }

}