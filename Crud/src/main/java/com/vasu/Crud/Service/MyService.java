/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vasu.Crud.Service;

import com.vasu.Crud.Model.User;
import com.vasu.Crud.Model.UserWrapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;

/**
 *
 * @author Vasu Rajput
 */
@Service
public class MyService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger("MyService.class");

    //1st method to get All Users
    public String findAll() {
        try {
            jdbcTemplate.query("select * from user", new RowCallbackHandler() {
                @Override
                public void processRow(ResultSet rs) throws SQLException {
                    User user = new User();
                    user.setEmailId(rs.getString("EmailId"));
                    user.setId(rs.getLong("Id"));
                    user.setPassword(rs.getString("Password"));
                    user.setRole(rs.getInt("Role"));
                    logger.info(" " + user);
                }
            });

        } catch (Exception e) {
            return "error";
        }
        return "success";
    }

    //2nd Method to get All User
    public List<User> findAll2() {
        try {
            List<User> query = jdbcTemplate.query("select * from user", new UserWrapper());
            logger.info("totalUser= " + query.size());
            return query;
        } catch (Exception e) {
            logger.error("Error", e);
            return null;
        }
    }

    //insert into DB
    public int insertUser(User user) {
        int update = jdbcTemplate.update("insert into user values(?,?,?,?)", user.getId(), user.getEmailId(), user.getPassword(),
                user.getPassword());
        logger.info("update= " + update);
        return update;
    }

    //create table
    public String createTable() {
        try {
            jdbcTemplate.execute("create table vasu (id int, name varchar(20), primary key(id))");
            return "succesflly created table";
        } catch (Exception e) {
            e.printStackTrace();
            return "error in table creation";
        }

    }

    //Delete From DB
    public int delete(int id) {
        try {
            int deleteStatus = jdbcTemplate.update("delete from user where id = ?", id);
            return deleteStatus;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //update DB
    public int update(User user) {
        try {
            logger.info("user= " + user);
            int updateStatus = jdbcTemplate.update("update user set emailId = ? where id = ?",
                    user.getEmailId(), user.getId());
            logger.info("updateStatus= " + updateStatus);
            return updateStatus;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
