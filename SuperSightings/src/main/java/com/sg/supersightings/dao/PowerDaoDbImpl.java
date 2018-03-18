/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.dao;

import com.sg.supersightings.model.Power;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author blake
 */
public class PowerDaoDbImpl implements PowerDao {

    JdbcTemplate jt;

    // Power queries
    private static final String INSERT_POWER
            = "insert into power (description) values (?)";

    private static final String DELETE_POWER
            = "delete from power where power_id = ?";

    private static final String SELECT_POWER_BY_DESCRIPTION
            = "select * from power where description = ?";

    private static final String SELECT_POWER_BY_ID
            = "select * from power where power_id = ?";

    private static final String SELECT_ALL_POWERS
            = "select * from power";
    
    private static final String UPDATE_POWER 
            = "update power set description = ? where power_id = ?";

    public void setJdbcTemplate(JdbcTemplate jt) {
        this.jt = jt;
    }

    @Override
    public Power addPower(Power power) {
        jt.update(INSERT_POWER, power.getDescription());

        int powerId
                = jt.queryForObject("select LAST_INSERT_ID()", Integer.class);

        power.setPowerId(powerId);

        return power;
    }

    @Override
    public Power deletePower(int powerId) {
        Power p = getPowerById(powerId);

        jt.update(DELETE_POWER, p.getPowerId());

        return p;
    }

    @Override
    public Power updatePower(Power power) {
        jt.update(UPDATE_POWER, power.getDescription(), power.getPowerId());
        return power;
    }

    @Override
    public Power getPowerById(int powerId) {
        try {
            return jt.queryForObject(SELECT_POWER_BY_ID, 
                    new PowerMapper(), 
                    powerId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    @Override
    public Power getPowerByDescription(String description) {
        try {
            return jt.queryForObject(SELECT_POWER_BY_DESCRIPTION, 
                    new PowerMapper(), 
                    description);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Power> getAllPowers() {
        return jt.query(SELECT_ALL_POWERS, new PowerMapper());
    }

    private static final class PowerMapper implements RowMapper<Power> {

        @Override
        public Power mapRow(ResultSet rs, int i) throws SQLException {
            Power p = new Power();
            p.setPowerId(rs.getInt("power_id"));
            p.setDescription(rs.getString("description"));
            return p;
        }

    }

}
