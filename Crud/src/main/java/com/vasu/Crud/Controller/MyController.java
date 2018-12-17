/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vasu.Crud.Controller;

import com.vasu.Crud.Model.User;
import com.vasu.Crud.Service.MyService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Vasu Rajput
 */
@RestController
public class MyController {

    @Autowired
    private MyService service;

    private static final Logger logger = LoggerFactory.getLogger("MyController.class");

    @GetMapping("/")
    public String test() {
        return "Nice Try";
    }

    @GetMapping("/findAll")
    public String findAllUser() {
        try {
            String findAll = service.findAll();
            logger.info("findAllStatus= " + findAll);
            return "success";
        } catch (Exception e) {
            logger.error("Error", e);
            return "error";
        }
    }

    @GetMapping("/findAll2")
    public List<User> findAllUser2() {
        try {
            List<User> findAll2 = service.findAll2();
            for (User user : findAll2) {
                logger.info("name= " + user.getEmailId() + " ,password= " + user.getPassword() + " ,role= "
                        + user.getRole());
            }
            return findAll2;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //Insert into database
    @PostMapping("/insert")
    public int insert(@RequestBody User user) {
        try {
            int insertUser = service.insertUser(user);
            return insertUser;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //create table
    @GetMapping("/createTable")
    public String createTable() {
        try {
            String createTableStatus = service.createTable();
            return createTableStatus;
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    //delete byId
    @DeleteMapping("/deleteById/{id}")
    public String deleteById(@PathVariable int id) {
        try {
            logger.info("id= " + id);
            int deleteStatus = service.delete(id);
            if (deleteStatus == 1) {
                return "success";
            } else {
                return "error in deletion";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error in deletion";
        }
    }

    //update Db
    @PutMapping("/update")
    public String updateUser(@RequestBody User user) {
        try {
            logger.info("user= " + user);
            int updateStatus = service.update(user);
            if (updateStatus == 1) {
                return "succesfully updated";
            } else {
                return "updation error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "updation error";
        }

    }
}
