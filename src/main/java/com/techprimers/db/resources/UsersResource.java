package com.techprimers.db.resources;

import com.techprimers.db.model.Users;
import com.techprimers.db.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;


import java.util.List;


@RestController
@RequestMapping("/users")
public class UsersResource {

    @Autowired
    UsersRepository usersRepository;



    @GetMapping("/")
    public String index(Model model) {
        //Map<String, String> map = new HashMap<>();
        List<Users> users = usersRepository.findAll();
        model.addAttribute("usersList", users);
        return "index.html";
    }

    @GetMapping("/show")
    public ModelAndView show(Model model){
        List<Users> users = usersRepository.findAll();
        model.addAttribute("users", users);
        return new ModelAndView("show", "users", model);
        //return new ModelAndView("show", "userList", model);
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
    public List<Users> delete (HttpServletRequest req){
        List<Users> userList = usersRepository.findByName(req.getParameter("name"));
        for (Users user : userList) {
            usersRepository.delete(user);
        }


        return usersRepository.findAll();
    }
    /*
    @PostMapping("/find")
    public List<Users> find(HttpServletRequest req){
        List<users 
    }
    */
    @PostMapping("/create")
    public List<Users> create(HttpServletRequest req){
        String name = req.getParameter("name");
        String teamName = req.getParameter("teamname");
        String salaryStr = req.getParameter("salary");
        int salary = Integer.parseInt(salaryStr);
        Users newUser = new Users(name, teamName, salary);
        usersRepository.save(newUser);
        return usersRepository.findAll();
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
