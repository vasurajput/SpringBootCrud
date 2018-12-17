/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vasu.Crud.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Vasu Rajput
 */
public class UserWrapper implements RowMapper<User> {

    private static final Logger logger = LoggerFactory.getLogger("UserWrapper.class");

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            logger.info("rowNumber = " + rowNum);
            User user = new User();
            user.setEmailId(rs.getString("EmailId"));
            user.setId(rs.getLong("Id"));
            user.setPassword(rs.getString("Password"));
            user.setRole(rs.getInt("Role"));
            return user;
        } catch (Exception e) {
            logger.error("Error", e);
        }
        return null;
    }

}
