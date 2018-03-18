/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.dao;

import com.sg.supersightings.model.Power;
import com.sg.supersightings.model.SuperBeing;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author blake
 */
public class SuperBeingDaoDbImpl implements SuperBeingDao {
    
    private JdbcTemplate jt;
    
    // Super-being queries
    private static final String INSERT_SUPER
            = "insert into super_being (name, description, identity) "
            + "values (?, ?, ?)";
    
    private static final String DELETE_SUPER
            = "delete from super_being where super_id = ?";
    
    private static final String UPDATE_SUPER
            = "update super_being set name = ?, description = ?, "
            + "identity = ? where super_id = ?";
    
    private static final String SELECT_SUPER
            = "select * from super_being where super_id = ?";
    
    private static final String SELECT_ALL_SUPERS
            = "select * from super_being";
    
//    private static final String INSERT_SUPER_BEING_POWER 
//            = "insert into super_being_power (super_id, power_id) "
//            + "values (?, ?)";
    
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
    
    // Super Being Powers queries
    private static final String INSERT_SUPER_BEING_POWER
            = "insert into super_being_power (super_id, power_id) "
            + "values (?, ?)";
    
    private static final String SELECT_ALL_POWERS_BY_SUPER_ID
            = "select * from super_being_power where super_id = ?";
    
//    private static final String UPDATE_SUPER_BEING_POWER 
//            = "update super_being_power set "
    
    private static final String DELETE_ALL_POWERS_BY_SUPER_ID 
            = "delete from super_being_power where super_id = ?";
    
    private static final String SELECT_ALL_SUPER_IDS_WITH_POWERS 
            = "select distinct(super_id) from super_being_power";
    
    
    public void setJdbcTemplate(JdbcTemplate jt) {
        this.jt = jt;
    }

    @Override
    @Transactional
    public SuperBeing addSuperBeing(SuperBeing being) {
        jt.update(INSERT_SUPER,
                being.getName(),
                being.getDescription(),
                being.getIdentity());
        
        int super_id = 
                jt.queryForObject("select LAST_INSERT_ID()", 
                        Integer.class);
        
        being.setSuperId(super_id);
        
        insertPowers(being);
        
//        insertSuperBeingsPowers(being); 

        this.addSuperBeingPowers(being);
        
        return being;
    }

    @Override
    @Transactional
    public SuperBeing deleteSuperBeing(int superId) {
        SuperBeing sb = getSuperBeingById(superId);
        
        jt.update(DELETE_SUPER, superId);
        
        return sb;
    }

    @Override
    public SuperBeing updateSuperBeing(SuperBeing being) {
        jt.update(UPDATE_SUPER,
                being.getName(),
                being.getDescription(),
                being.getIdentity(),
                being.getSuperId());
        
        return being;
    }

    @Override
    public SuperBeing getSuperBeingById(int id) {
        try {
            return jt.queryForObject(SELECT_SUPER, new SuperMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<SuperBeing> getAllSuperBeings() {
        return jt.query(SELECT_ALL_SUPERS, new SuperMapper());
    }
    
    @Override
    @Transactional
    public Power addPower(Power power) {
        jt.update(INSERT_POWER, power.getDescription());

        int powerId
                = jt.queryForObject("select LAST_INSERT_ID()", Integer.class);

        power.setPowerId(powerId);

        return power;
    }

    @Override
    @Transactional
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

    @Override
    public void addSuperBeingPowers(SuperBeing superBeing) {
        List<Power> powers = superBeing.getPowers();
        int superId = superBeing.getSuperId();
        
        for (Power currentPower : powers) {
            jt.update(INSERT_SUPER_BEING_POWER, 
                    superId,
                    currentPower.getPowerId());
        }
    }

    @Override
    public List<Power> getAllPowersBySuperId(int superId) {
        try {
            return jt.query(SELECT_ALL_POWERS_BY_SUPER_ID, 
                new PowerMapper(),
                superId);
        } catch (DataAccessException e) {
            return null;
        }
    }

//    @Override
//    public void updateSuperBeingPowers(SuperBeing superBeing) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public void deleteSuperBeingPowersBySuperId(int superId) {
        jt.update(DELETE_ALL_POWERS_BY_SUPER_ID, superId);
    }

    @Override
    public List<Integer> getAllSuperIdsWithPowers() {
        try {
            return jt.queryForList(SELECT_ALL_SUPER_IDS_WITH_POWERS, 
                    Integer.class);
        } catch (DataAccessException e) {
            return null;
        }
    }
    
    private void insertPowers(SuperBeing sb) {
        List<Power> powers = sb.getPowers();
        
        for (Power currentPower : powers) {
            //check if power exists
            //if doesn't exists then insert
            //else skip
//            try {
//                Power p = jt.queryForObject(SELECT_POWER_BY_DESCRIPTION, 
//                        new PowerMapper(),
//                        currentPower.getDescription());
//                
//                currentPower.setPowerId(p.getPowerId());
//            } catch (DataAccessException e) {
//                
//            }
            
            Power p = this.getPowerByDescription(currentPower.getDescription());
            
            if (p != null) {
                currentPower.setPowerId(p.getPowerId());
            } else {
                this.addPower(currentPower);
            }
        }
    }
    
//    private void insertSuperBeingsPowers(SuperBeing sb) {
//        int superId = sb.getSuperId();
//        List<Power> powers = sb.getPowers();
//        
//        for (Power currentPower : powers) {
//            this.addSuperBeingPowers(sb);
//        }
//    }
    
    
    private static final class SuperMapper implements RowMapper<SuperBeing> {

        @Override
        public SuperBeing mapRow(ResultSet rs, int i) throws SQLException {
            SuperBeing sb = new SuperBeing();
            sb.setName(rs.getString("name"));
            sb.setDescription("description");
            sb.setIdentity("identity");
            sb.setSuperId(rs.getInt("super_id"));
            return sb;
        }
        
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
