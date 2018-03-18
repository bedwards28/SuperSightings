/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.dao;

import com.sg.supersightings.model.Power;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author blake
 */
public class PowerDaoTest {
    
    PowerDao powerDao;
    
    public PowerDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("test-applicationContext.xml");
        
        powerDao = ctx.getBean("powerDao", PowerDao.class);
        
        List<Power> powers = powerDao.getAllPowers();
        for (Power currentPower : powers) {
            powerDao.deletePower(currentPower.getPowerId());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addPower method, of class PowerDao.
     */
    @Test
    public void testAddGetPower() {
        Power p = new Power();
        p.setDescription("Super Strength");
        
        powerDao.addPower(p);
        
        Power fromDao = powerDao.getPowerById(p.getPowerId());
        assertEquals(fromDao, p);
        
        fromDao = powerDao.getPowerByDescription("super strength");
        assertEquals(fromDao, p);
    }

    /**
     * Test of deletePower method, of class PowerDao.
     */
    @Test
    public void testDeletePower() {
        Power p = new Power();
        p.setDescription("Super Strength");
        powerDao.addPower(p);
        
        Power fromDao = powerDao.getPowerById(p.getPowerId());
        assertEquals(fromDao, p);
        
        powerDao.deletePower(p.getPowerId());
        fromDao = powerDao.getPowerById(p.getPowerId());
        assertNull(fromDao);
    }

    /**
     * Test of updatePower method, of class PowerDao.
     */
    @Test
    public void testUpdatePower() {
        Power p = new Power();
        p.setDescription("Super Strength");
        powerDao.addPower(p);
        
        Power fromDao = powerDao.getPowerById(p.getPowerId());
        assertEquals("super strength", fromDao.getDescription().toLowerCase());
        
        p.setDescription("Heat Vision");
        powerDao.updatePower(p);
        fromDao = powerDao.getPowerById(p.getPowerId());
        assertEquals("heat vision", fromDao.getDescription().toLowerCase());
    }

    /**
     * Test of getAllPowers method, of class PowerDao.
     */
    @Test
    public void testGetAllPowers() {
        Power p = new Power();
        p.setDescription("Super Strength");
        powerDao.addPower(p);
        
        List<Power> powers = powerDao.getAllPowers();
        assertEquals(1, powers.size());
        
        Power p2 = new Power();
        p2.setDescription("Flight");
        powerDao.addPower(p2);
        
        powers = powerDao.getAllPowers();
        assertEquals(2, powers.size());
    }
    
}
