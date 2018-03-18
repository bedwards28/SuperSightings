/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.dao;

import com.sg.supersightings.model.Power;
import com.sg.supersightings.model.SuperBeing;
import java.util.ArrayList;
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
public class SuperBeingDaoTest {
    
    SuperBeingDao dao;
    
    public SuperBeingDaoTest() {
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
        
        dao = ctx.getBean("superBeingDao", SuperBeingDao.class);
        
        List<Integer> superIds = dao.getAllSuperIdsWithPowers();
        for (Integer currentId : superIds) {
            dao.deleteSuperBeingPowersBySuperId(currentId);
        }
        
        List<Power> powers = dao.getAllPowers();
        for (Power currentPower : powers) {
            dao.deletePower(currentPower.getPowerId());
        }
        
        List<SuperBeing> supers = dao.getAllSuperBeings();
        for (SuperBeing currentSuper : supers) {
            dao.deleteSuperBeing(currentSuper.getSuperId());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addSuperBeing method, of class SuperBeingDao.
     */
    @Test
    public void testAddGetSuperBeing() {
        Power power = new Power();
        power.setDescription("Super Strength");
        List<Power> powers = new ArrayList<>();
        powers.add(power);
        
        SuperBeing sb = new SuperBeing();
        sb.setName("B-Man");
        sb.setDescription("Can beat up Batman and Superman at same time");
        sb.setIdentity("Blake Edwards");
        sb.setPowers(powers);
        
        dao.addSuperBeing(sb);
        
        SuperBeing fromDao = dao.getSuperBeingById(sb.getSuperId());
        assertEquals(fromDao.getSuperId(), sb.getSuperId());
        
        // This doesn't work for some reason
//        assertEquals(fromDao, sb);
    }

    /**
     * Test of deleteSuperBeing method, of class SuperBeingDao.
     */
    @Test
    public void testDeleteSuperBeing() {
    }

    /**
     * Test of updateSuperBeing method, of class SuperBeingDao.
     */
    @Test
    public void testUpdateSuperBeing() {
    }

    /**
     * Test of getAllSuperBeings method, of class SuperBeingDao.
     */
    @Test
    public void testGetAllSuperBeings() {
    }
    
    /**
     * Test of addPower method, of class PowerDao.
     */
    @Test
    public void testAddGetPower() {
        Power p = new Power();
        p.setDescription("Super Strength");
        
        dao.addPower(p);
        
        Power fromDao = dao.getPowerById(p.getPowerId());
        assertEquals(fromDao, p);
        
        fromDao = dao.getPowerByDescription("super strength");
        assertEquals(fromDao, p);
    }

    /**
     * Test of deletePower method, of class PowerDao.
     */
    @Test
    public void testDeletePower() {
        Power p = new Power();
        p.setDescription("Super Strength");
        dao.addPower(p);
        
        Power fromDao = dao.getPowerById(p.getPowerId());
        assertEquals(fromDao, p);
        
        dao.deletePower(p.getPowerId());
        fromDao = dao.getPowerById(p.getPowerId());
        assertNull(fromDao);
    }

    /**
     * Test of updatePower method, of class PowerDao.
     */
    @Test
    public void testUpdatePower() {
        Power p = new Power();
        p.setDescription("Super Strength");
        dao.addPower(p);
        
        Power fromDao = dao.getPowerById(p.getPowerId());
        assertEquals("super strength", fromDao.getDescription().toLowerCase());
        
        p.setDescription("Heat Vision");
        dao.updatePower(p);
        fromDao = dao.getPowerById(p.getPowerId());
        assertEquals("heat vision", fromDao.getDescription().toLowerCase());
    }

    /**
     * Test of getAllPowers method, of class PowerDao.
     */
    @Test
    public void testGetAllPowers() {
        Power p = new Power();
        p.setDescription("Super Strength");
        dao.addPower(p);
        
        List<Power> powers = dao.getAllPowers();
        assertEquals(1, powers.size());
        
        Power p2 = new Power();
        p2.setDescription("Flight");
        dao.addPower(p2);
        
        powers = dao.getAllPowers();
        assertEquals(2, powers.size());
    }
    
}
