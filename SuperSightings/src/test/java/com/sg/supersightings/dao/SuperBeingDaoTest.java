/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.dao;

import com.sg.supersightings.model.Location;
import com.sg.supersightings.model.Organization;
import com.sg.supersightings.model.Power;
import com.sg.supersightings.model.Sighting;
import com.sg.supersightings.model.SuperBeing;
import java.time.LocalDate;
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
        
        List<Organization> orgs = dao.getAllOrganizations();
        for (Organization currentOrg : orgs) {
            dao.deleteOrganization(currentOrg.getOrganizationId());
        }
        
        // delete super_being_sighting table entries
//        dao.deleteSuperBeingSightingTableEntries();
        
        // delete sighting table entries
        List<Sighting> sightings = dao.getAllSightings();
        for (Sighting currentSighting : sightings) {
            dao.deleteSighting(currentSighting.getSightingId());
        }
        
        List<Location> locations = dao.getAllLocations();
        for (Location currentLocation : locations) {
            dao.deleteLocationById(currentLocation.getLocationId());
        }
        
//        List<Integer> superIds = dao.getAllSuperIdsAndPowers();
//        for (Integer currentId : superIds) {
//            dao.deleteSuperBeingPowersBySuperId(currentId);
//        }
        
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
        assertEquals(fromDao, sb);
    }

    /**
     * Test of deleteSuperBeing method, of class SuperBeingDao.
     */
    @Test
    public void testDeleteSuperBeing() {
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
        assertEquals(fromDao, sb);
        
        dao.deleteSuperBeing(sb.getSuperId());
        fromDao = dao.getSuperBeingById(sb.getSuperId());
        assertNull(fromDao);
        
    }

    /**
     * Test of updateSuperBeing method, of class SuperBeingDao.
     */
    @Test
    public void testUpdateSuperBeing() {
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
        SuperBeing fromDao = dao.getSuperBeingById((sb.getSuperId()));
        assertEquals("B-Man", fromDao.getName());
        
        sb.setName("Max Power");
        sb.setDescription("Super duper dude");
        sb.setIdentity("Dolph Lundgren");
        power.setDescription("Can smell crime");
        powers.clear();
        powers.add(power);
        sb.setPowers(powers);
        dao.updateSuperBeing(sb);
        fromDao = dao.getSuperBeingById(sb.getSuperId());
        assertEquals("Max Power", fromDao.getName());
        assertEquals("Super duper dude", fromDao.getDescription());
        assertEquals("Dolph Lundgren", fromDao.getIdentity());
        assertEquals("Can smell crime", fromDao.getPowers().get(0).getDescription());
    }

    /**
     * Test of getAllSuperBeings method, of class SuperBeingDao.
     */
    @Test
    public void testGetAllSuperBeings() {
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
        assertEquals(1, dao.getAllSuperBeings().size());
        
        Power power2 = new Power();
        power2.setDescription("Super Cool");
        List<Power> powers2 = new ArrayList<>();
        powers2.add(power2);
        
        SuperBeing sb2 = new SuperBeing();
        sb2.setName("Iceman");
        sb2.setDescription("Retired crime fighter");
        sb2.setIdentity("Chuck Lidell");
        sb2.setPowers(powers2);
        
        dao.addSuperBeing(sb2);
        assertEquals(2, dao.getAllSuperBeings().size());
        
        List<SuperBeing> supers = dao.getAllSuperBeings();
        assertEquals(sb, supers.get(0));
        assertEquals(sb2, supers.get(1));
    }
    
    /**
     * Test getAllOrganizationMembers method
     */
    @Test
    public void testGetAllOrganizationMembers() {
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
        
        Power power2 = new Power();
        power2.setDescription("Super Cool");
        List<Power> powers2 = new ArrayList<>();
        powers2.add(power2);
        
        SuperBeing sb2 = new SuperBeing();
        sb2.setName("Iceman");
        sb2.setDescription("Retired crime fighter");
        sb2.setIdentity("Chuck Lidell");
        sb2.setPowers(powers2);
        
        dao.addSuperBeing(sb2);
        
        List<SuperBeing> members = new ArrayList<>();
        members.add(sb);
        members.add(sb2);
        
        Location loc = new Location();
        loc.setName("Legion of Doom");
        loc.setDescription("Bad guy party palace");
        loc.setAddressLine1("123 1st Street");
        loc.setAddressLine2("Apt 2");
        loc.setCity("Apple Valley");
        loc.setRegion("MN");
        loc.setPostalCode("55111");
        loc.setCountry("USA");
        loc.setLatitude(100.5);
        loc.setLongitude(75.75);
        dao.addLocation(loc);
        
        Organization org = new Organization();
        org.setDescription("Justice League");
        org.setLocation(loc);
        org.setPhone("111-111-1111");
        org.setEmail("sup@jl.com");
        org.setMembers(members);
        
        dao.addOrganization(org);
        
        List<SuperBeing> orgMembers = 
                dao.getAllOrganizationMembers(org.getOrganizationId());
        
        assertEquals(sb, orgMembers.get(0));
        assertEquals(sb2, orgMembers.get(1));
    }
    
    /**
     * Test of addPower, getPowerById, getPowerByDescription methods
     * of class SuperBeingDao.
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
     * Test of deletePower method, of class SuperBeingDao.
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
     * Test of updatePower method, of class SuperBeingDao.
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
     * Test of getAllPowers method, of class SuperBeingDao.
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
    
    /**
     * Test addLocation, getLocationById methods
     */
    @Test
    public void testAddGetLocation() {
        Location loc = new Location();
        loc.setName("Legion of Doom");
        loc.setDescription("Bad guy party palace");
        loc.setAddressLine1("123 1st Street");
        loc.setAddressLine2("Apt 2");
        loc.setCity("Apple Valley");
        loc.setRegion("MN");
        loc.setPostalCode("55111");
        loc.setCountry("USA");
        loc.setLatitude(100.5);
        loc.setLongitude(75.75);
        
        Location fromDao = dao.getLocationById(loc.getLocationId());
        assertNull(fromDao);
        
        dao.addLocation(loc);
        
        fromDao = dao.getLocationById(loc.getLocationId());
        assertEquals(fromDao, loc);
    }
    
    /**
     * Test deleteLocation method
     */
    @Test
    public void testDeleteLocation() {
        Location loc = new Location();
        loc.setName("Legion of Doom");
        loc.setDescription("Bad guy party palace");
        loc.setAddressLine1("123 1st Street");
        loc.setAddressLine2("Apt 2");
        loc.setCity("Apple Valley");
        loc.setRegion("MN");
        loc.setPostalCode("55111");
        loc.setCountry("USA");
        loc.setLatitude(100.5);
        loc.setLongitude(75.75);
        
        dao.addLocation(loc);
        Location fromDao = dao.getLocationById(loc.getLocationId());
        assertEquals(fromDao, loc);
        
        dao.deleteLocationById(loc.getLocationId());
        fromDao = dao.getLocationById(loc.getLocationId());
        assertNull(fromDao);
    }
    
    /**
     * Test updateLocation method
     */
    @Test
    public void testUpdateLocation() {
        Location loc = new Location();
        loc.setName("Legion of Doom");
        loc.setDescription("Bad guy party palace");
        loc.setAddressLine1("123 1st Street");
        loc.setAddressLine2("Apt 2");
        loc.setCity("Apple Valley");
        loc.setRegion("MN");
        loc.setPostalCode("55111");
        loc.setCountry("USA");
        loc.setLatitude(100.5);
        loc.setLongitude(75.75);
        
        dao.addLocation(loc);
        Location fromDao = dao.getLocationById(loc.getLocationId());
        assertEquals(fromDao, loc);
        
        Location loc2 = new Location();
        loc2.setName("Hall of Justice");
        loc2.setDescription("Good guy party palace");
        loc2.setAddressLine1("321 1st Ave");
        loc2.setAddressLine2("Penthouse");
        loc2.setCity("Metropolis");
        loc2.setRegion("FakeState");
        loc2.setPostalCode("12345");
        loc2.setCountry("USA");
        loc2.setLatitude(90.6);
        loc2.setLongitude(50.50);
        loc2.setLocationId(loc.getLocationId());
        
        dao.updateLocation(loc2);
        fromDao = dao.getLocationById(loc.getLocationId());
        assertEquals(fromDao, loc2);
    }    
    
    /**
     * Test getAllLocations method
     */
    @Test
    public void testGetAllLocations() {
        Location loc = new Location();
        loc.setName("Legion of Doom");
        loc.setDescription("Bad guy party palace");
        loc.setAddressLine1("123 1st Street");
        loc.setAddressLine2("Apt 2");
        loc.setCity("Apple Valley");
        loc.setRegion("MN");
        loc.setPostalCode("55111");
        loc.setCountry("USA");
        loc.setLatitude(100.5);
        loc.setLongitude(75.75);
        dao.addLocation(loc);
        assertEquals(1, dao.getAllLocations().size());
        
        Location loc2 = new Location();
        loc2.setName("Hall of Justice");
        loc2.setDescription("Good guy party palace");
        loc2.setAddressLine1("321 1st Ave");
        loc2.setAddressLine2("Penthouse");
        loc2.setCity("Metropolis");
        loc2.setRegion("FakeState");
        loc2.setPostalCode("12345");
        loc2.setCountry("USA");
        loc2.setLatitude(90.6);
        loc2.setLongitude(50.50);
        dao.addLocation(loc2);
        assertEquals(2, dao.getAllLocations().size());
    }
    
    /**
     * Test getAllLocationsBySuperId method
     */
    @Test
    public void testGetAllLocationsBySuperId() {
        Location loc1 = new Location();
        loc1.setName("Legion of Doom");
        loc1.setDescription("Bad guy party palace");
        loc1.setAddressLine1("123 1st Street");
        loc1.setAddressLine2("Apt 2");
        loc1.setCity("Apple Valley");
        loc1.setRegion("MN");
        loc1.setPostalCode("55111");
        loc1.setCountry("USA");
        loc1.setLatitude(100.5);
        loc1.setLongitude(75.75);
        dao.addLocation(loc1);
        
        Location loc2 = new Location();
        loc2.setName("Hall of Justice");
        loc2.setDescription("Good guy party palace");
        loc2.setAddressLine1("321 1st Ave");
        loc2.setAddressLine2("Penthouse");
        loc2.setCity("Metropolis");
        loc2.setRegion("FakeState");
        loc2.setPostalCode("12345");
        loc2.setCountry("USA");
        loc2.setLatitude(90.6);
        loc2.setLongitude(50.50);
        dao.addLocation(loc2);
        
        SuperBeing sb = new SuperBeing();
        sb.setName("B-Man");
        sb.setDescription("Can beat up Batman and Superman at same time");
        sb.setIdentity("Blake Edwards");
        dao.addSuperBeing(sb);
        List<SuperBeing> superBeings = new ArrayList<>();
        superBeings.add(sb);
        
        Sighting s1 = new Sighting();
        s1.setLocation(loc1);
        s1.setDate(LocalDate.now());
        s1.setSuperBeings(superBeings);
        dao.addSighting(s1);
        
        Sighting s2 = new Sighting();
        s2.setLocation(loc2);
        s2.setDate(LocalDate.now());
        s2.setSuperBeings(superBeings);
        dao.addSighting(s2);
        
        assertEquals(2, dao.getAllLocationsBySuperId(sb.getSuperId()).size());
    }
    
    /** 
     * Test addSighting, getSightingById, deleteSighting methods
     */
    @Test
    public void testAddGetDeleteSighting() {
        List<Power> powers = new ArrayList<>();
        Power p1 = new Power();
        p1.setDescription("Flight");
        powers.add(p1);
        
        List<SuperBeing> beings = new ArrayList<>();
        SuperBeing sb = new SuperBeing();
        sb.setName("Superman");
        sb.setDescription("Super dude");
        sb.setIdentity("Clark Kent");
        sb.setPowers(powers);
        beings.add(sb);
        dao.addSuperBeing(sb);
        
        Location loc = new Location();
        loc.setName("Legion of Doom");
        loc.setDescription("Bad guy party palace");
        loc.setAddressLine1("123 1st Street");
        loc.setAddressLine2("Apt 2");
        loc.setCity("Apple Valley");
        loc.setRegion("MN");
        loc.setPostalCode("55111");
        loc.setCountry("USA");
        loc.setLatitude(100.5);
        loc.setLongitude(75.75);
        dao.addLocation(loc);
        
        Sighting s = new Sighting();
        s.setLocation(loc);
        s.setDate(LocalDate.now());
        s.setSuperBeings(beings);
        
        dao.addSighting(s);
        Sighting fromDao = dao.getSightingById(s.getSightingId());
        assertEquals(fromDao, s);
        
        dao.deleteSighting(fromDao.getSightingId());
        fromDao = dao.getSightingById(fromDao.getSightingId());
        assertNull(fromDao);
    }
    
    @Test
    public void testUpdateSighting() {
        
        List<Power> powers = new ArrayList<>();
        Power p1 = new Power();
        p1.setDescription("Flight");
        powers.add(p1);
        
        List<SuperBeing> beings = new ArrayList<>();
        SuperBeing sb = new SuperBeing();
        sb.setName("Superman");
        sb.setDescription("Super dude");
        sb.setIdentity("Clark Kent");
        sb.setPowers(powers);
        beings.add(sb);
        dao.addSuperBeing(sb);
        
        Location loc = new Location();
        loc.setName("Legion of Doom");
        loc.setDescription("Bad guy party palace");
        loc.setAddressLine1("123 1st Street");
        loc.setAddressLine2("Apt 2");
        loc.setCity("Apple Valley");
        loc.setRegion("MN");
        loc.setPostalCode("55111");
        loc.setCountry("USA");
        loc.setLatitude(100.5);
        loc.setLongitude(75.75);
        dao.addLocation(loc);
        
        Sighting s = new Sighting();
        s.setLocation(loc);
        s.setDate(LocalDate.now());
        s.setSuperBeings(beings);
        
        dao.addSighting(s);
        
        List<Power> powers2 = new ArrayList<>();
        Power p2 = new Power();
        p2.setDescription("Super Speed");
        powers2.add(p2);
        
        List<SuperBeing> beings2 = new ArrayList<>();
        SuperBeing sb2 = new SuperBeing();
        sb2.setName("Flash");
        sb2.setDescription("Fastest man alive");
        sb2.setIdentity("Barry Allen");
        sb2.setPowers(powers2);
        beings2.add(sb2);
        dao.addSuperBeing(sb2);
        
        Location loc2 = new Location();
        loc2.setName("Downtown");
        loc2.setDescription("Downtown MPLS");
        loc2.setAddressLine1("456 4th Street");
        loc2.setAddressLine2(null);
        loc2.setCity("Minneapolis");
        loc2.setRegion("MN");
        loc2.setPostalCode("55222");
        loc2.setCountry("USA");
        loc2.setLatitude(60.66);
        loc2.setLongitude(80.88);
        dao.addLocation(loc2);
        
        Sighting s2 = new Sighting();
        s2.setLocation(loc2);
        s2.setDate(LocalDate.now());
        s2.setSuperBeings(beings2);
        s2.setSightingId(s.getSightingId());
        
        dao.updateSighting(s2);
        
        Sighting fromDao = dao.getSightingById(s.getSightingId());
        assertEquals(fromDao, s2);
    }
    
    /**
     * Test getAllSightings method
     */
    @Test
    public void testGetAllSightings() {
        
        assertEquals(0, dao.getAllSightings().size());
        
        List<Power> powers = new ArrayList<>();
        Power p1 = new Power();
        p1.setDescription("Flight");
        powers.add(p1);
        
        List<SuperBeing> beings = new ArrayList<>();
        SuperBeing sb = new SuperBeing();
        sb.setName("Superman");
        sb.setDescription("Super dude");
        sb.setIdentity("Clark Kent");
        sb.setPowers(powers);
        beings.add(sb);
        dao.addSuperBeing(sb);
        
        Location loc = new Location();
        loc.setName("Legion of Doom");
        loc.setDescription("Bad guy party palace");
        loc.setAddressLine1("123 1st Street");
        loc.setAddressLine2("Apt 2");
        loc.setCity("Apple Valley");
        loc.setRegion("MN");
        loc.setPostalCode("55111");
        loc.setCountry("USA");
        loc.setLatitude(100.5);
        loc.setLongitude(75.75);
        dao.addLocation(loc);
        
        Sighting s = new Sighting();
        s.setLocation(loc);
        s.setDate(LocalDate.now());
        s.setSuperBeings(beings);
        
        dao.addSighting(s);
        
        assertEquals(1, dao.getAllSightings().size());
        
        List<Power> powers2 = new ArrayList<>();
        Power p2 = new Power();
        p2.setDescription("Super Speed");
        powers2.add(p2);
        
        List<SuperBeing> beings2 = new ArrayList<>();
        SuperBeing sb2 = new SuperBeing();
        sb2.setName("Flash");
        sb2.setDescription("Super fast dude");
        sb2.setIdentity("Barry Allen");
        sb2.setPowers(powers2);
        beings2.add(sb2);
        dao.addSuperBeing(sb2);
        
        Location loc2 = new Location();
        loc2.setName("Legion of Doom");
        loc2.setDescription("Bad guy party palace");
        loc2.setAddressLine1("123 1st Street");
        loc2.setAddressLine2("Apt 2");
        loc2.setCity("Apple Valley");
        loc2.setRegion("MN");
        loc2.setPostalCode("55111");
        loc2.setCountry("USA");
        loc2.setLatitude(100.5);
        loc2.setLongitude(75.75);
        dao.addLocation(loc2);
        
        Sighting s2 = new Sighting();
        s2.setLocation(loc2);
        s2.setDate(LocalDate.now());
        s2.setSuperBeings(beings2);
        
        dao.addSighting(s2);
        
        assertEquals(2, dao.getAllSightings().size());
        
        List<Sighting> sightings = dao.getAllSightings();
        assertEquals(s, sightings.get(0));
        assertEquals(s2, sightings.get(1));
        
    }
    
    /**
     * Test getAllSightingsByDate method
     */
    @Test
    public void testGetAllSightingsByDate() {
        List<Power> powers = new ArrayList<>();
        Power p1 = new Power();
        p1.setDescription("Flight");
        powers.add(p1);
        
        List<SuperBeing> beings = new ArrayList<>();
        SuperBeing sb = new SuperBeing();
        sb.setName("Superman");
        sb.setDescription("Super dude");
        sb.setIdentity("Clark Kent");
        sb.setPowers(powers);
        beings.add(sb);
        dao.addSuperBeing(sb);
        
        Location loc = new Location();
        loc.setName("Legion of Doom");
        loc.setDescription("Bad guy party palace");
        loc.setAddressLine1("123 1st Street");
        loc.setAddressLine2("Apt 2");
        loc.setCity("Apple Valley");
        loc.setRegion("MN");
        loc.setPostalCode("55111");
        loc.setCountry("USA");
        loc.setLatitude(100.5);
        loc.setLongitude(75.75);
        dao.addLocation(loc);
        
        Sighting s = new Sighting();
        s.setLocation(loc);
        s.setDate(LocalDate.of(2000, 1, 1));
        s.setSuperBeings(beings);
        
        dao.addSighting(s);
        
        List<Power> powers2 = new ArrayList<>();
        Power p2 = new Power();
        p2.setDescription("Super Speed");
        powers2.add(p2);
        
        List<SuperBeing> beings2 = new ArrayList<>();
        SuperBeing sb2 = new SuperBeing();
        sb2.setName("Flash");
        sb2.setDescription("Super fast dude");
        sb2.setIdentity("Barry Allen");
        sb2.setPowers(powers2);
        beings2.add(sb2);
        dao.addSuperBeing(sb2);
        
        Location loc2 = new Location();
        loc2.setName("Legion of Doom");
        loc2.setDescription("Bad guy party palace");
        loc2.setAddressLine1("123 1st Street");
        loc2.setAddressLine2("Apt 2");
        loc2.setCity("Apple Valley");
        loc2.setRegion("MN");
        loc2.setPostalCode("55111");
        loc2.setCountry("USA");
        loc2.setLatitude(100.5);
        loc2.setLongitude(75.75);
        dao.addLocation(loc2);
        
        Sighting s2 = new Sighting();
        s2.setLocation(loc2);
        s2.setDate(LocalDate.of(2000, 1, 1));
        s2.setSuperBeings(beings2);
        
        dao.addSighting(s2);
        
        List<Power> powers3 = new ArrayList<>();
        Power p3 = new Power();
        p3.setDescription("Gadgets");
        powers2.add(p3);
        
        List<SuperBeing> beings3 = new ArrayList<>();
        SuperBeing sb3 = new SuperBeing();
        sb3.setName("Batman");
        sb3.setDescription("The Dark Knight");
        sb3.setIdentity("Bruce Wayne");
        sb3.setPowers(powers3);
        beings3.add(sb3);
        dao.addSuperBeing(sb3);
        
        Location loc3 = new Location();
        loc3.setName("Batcave");
        loc3.setDescription("Secret layer of Batman");
        loc3.setAddressLine1("Wayne Manor");
        loc3.setAddressLine2("Cave 1");
        loc3.setCity("Gotham");
        loc3.setRegion("MM");
        loc3.setPostalCode("99999");
        loc3.setCountry("USA");
        loc3.setLatitude(20.20);
        loc3.setLongitude(30.30);
        dao.addLocation(loc3);
        
        Sighting s3 = new Sighting();
        s3.setLocation(loc3);
        s3.setDate(LocalDate.now());
        s3.setSuperBeings(beings3);
        
        dao.addSighting(s3);
        
        assertEquals(2, dao.getAllSightingsByDate(LocalDate.of(2000, 1, 1)).size());
        assertEquals(1, dao.getAllSightingsByDate(LocalDate.now()).size());
        assertEquals(0, dao.getAllSightingsByDate(LocalDate.of(2018, 1, 1)).size());
    }
    
    @Test
    public void testAddGetDeleteOrganization() {
        List<Power> powers = new ArrayList<>();
        Power p1 = new Power();
        p1.setDescription("Flight");
        powers.add(p1);
        
        List<SuperBeing> beings = new ArrayList<>();
        SuperBeing sb = new SuperBeing();
        sb.setName("Superman");
        sb.setDescription("Super dude");
        sb.setIdentity("Clark Kent");
        sb.setPowers(powers);
        beings.add(sb);
        dao.addSuperBeing(sb);
        
        Location loc = new Location();
        loc.setName("Legion of Doom");
        loc.setDescription("Bad guy party palace");
        loc.setAddressLine1("123 1st Street");
        loc.setAddressLine2("Apt 2");
        loc.setCity("Apple Valley");
        loc.setRegion("MN");
        loc.setPostalCode("55111");
        loc.setCountry("USA");
        loc.setLatitude(100.5);
        loc.setLongitude(75.75);
        dao.addLocation(loc);
        
        Organization org = new Organization();
        org.setDescription("Justice League");
        org.setLocation(loc);
        org.setPhone("111-111-1111");
        org.setEmail("sup@jl.com");
        org.setMembers(beings);
        
        Organization fromDao = dao.getOrganizationById(org.getOrganizationId());
        assertNull(fromDao);
        
        dao.addOrganization(org);
        fromDao = dao.getOrganizationById(org.getOrganizationId());
        assertEquals(fromDao, org);
        
        dao.deleteOrganization(org.getOrganizationId());
        fromDao = dao.getOrganizationById(org.getOrganizationId());
        assertNull(fromDao);
    }
    
    @Test
    public void testUpdateOrganization() {
        List<Power> powers = new ArrayList<>();
        Power p1 = new Power();
        p1.setDescription("Flight");
        powers.add(p1);
        
        List<SuperBeing> beings = new ArrayList<>();
        SuperBeing sb = new SuperBeing();
        sb.setName("Superman");
        sb.setDescription("Super dude");
        sb.setIdentity("Clark Kent");
        sb.setPowers(powers);
        beings.add(sb);
        dao.addSuperBeing(sb);
        
        Location loc = new Location();
        loc.setName("Legion of Doom");
        loc.setDescription("Bad guy party palace");
        loc.setAddressLine1("123 1st Street");
        loc.setAddressLine2("Apt 2");
        loc.setCity("Apple Valley");
        loc.setRegion("MN");
        loc.setPostalCode("55111");
        loc.setCountry("USA");
        loc.setLatitude(100.5);
        loc.setLongitude(75.75);
        dao.addLocation(loc);
        
        Organization org = new Organization();
        org.setDescription("Justice League");
        org.setLocation(loc);
        org.setPhone("111-111-1111");
        org.setEmail("sup@jl.com");
        org.setMembers(beings);
        
        dao.addOrganization(org);
        
        List<Power> powers2 = new ArrayList<>();
        Power p2 = new Power();
        p2.setDescription("Super Speed");
        powers.add(p2);
        
        List<SuperBeing> beings2 = new ArrayList<>();
        SuperBeing sb2 = new SuperBeing();
        sb2.setName("Flash");
        sb2.setDescription("Super fast");
        sb2.setIdentity("Barry Allen");
        sb2.setPowers(powers2);
        beings2.add(sb2);
        dao.addSuperBeing(sb2);
        
        Location loc2 = new Location();
        loc2.setName("Hall of Justice");
        loc2.setDescription("Good Guy Spa");
        loc2.setAddressLine1("456 4th Ave");
        loc2.setAddressLine2(null);
        loc2.setCity("Metropolis");
        loc2.setRegion("Fake Region");
        loc2.setPostalCode("99999");
        loc2.setCountry("USA");
        loc2.setLatitude(60.60);
        loc2.setLongitude(90.11);
        dao.addLocation(loc2);
        
        Organization org2 = new Organization();
        org2.setDescription("Community Watch");
        org2.setLocation(loc2);
        org2.setPhone("222-222-2222");
        org2.setEmail("flash@cw.com");
        org2.setMembers(beings2);
        org2.setOrganizationId(org.getOrganizationId());
        
        dao.updateOrganization(org2);
        
        Organization fromDao = dao.getOrganizationById(org.getOrganizationId());
        assertEquals(fromDao, org2);
    }
    
    /**
     * Test getAllOrganizations method
     */
    @Test
    public void testGetAllOrganizations() {
        
        assertEquals(0, dao.getAllOrganizations().size());
        
        List<Power> powers = new ArrayList<>();
        Power p1 = new Power();
        p1.setDescription("Flight");
        powers.add(p1);
        
        List<SuperBeing> beings = new ArrayList<>();
        SuperBeing sb = new SuperBeing();
        sb.setName("Superman");
        sb.setDescription("Super dude");
        sb.setIdentity("Clark Kent");
        sb.setPowers(powers);
        beings.add(sb);
        dao.addSuperBeing(sb);
        
        Location loc = new Location();
        loc.setName("Legion of Doom");
        loc.setDescription("Bad guy party palace");
        loc.setAddressLine1("123 1st Street");
        loc.setAddressLine2("Apt 2");
        loc.setCity("Apple Valley");
        loc.setRegion("MN");
        loc.setPostalCode("55111");
        loc.setCountry("USA");
        loc.setLatitude(100.5);
        loc.setLongitude(75.75);
        dao.addLocation(loc);
        
        Organization org = new Organization();
        org.setDescription("Justice League");
        org.setLocation(loc);
        org.setPhone("111-111-1111");
        org.setEmail("sup@jl.com");
        org.setMembers(beings);
        
        dao.addOrganization(org);
        
        assertEquals(1, dao.getAllOrganizations().size());
        
        List<Power> powers2 = new ArrayList<>();
        Power p2 = new Power();
        p2.setDescription("Super Speed");
        powers2.add(p2);
        
        List<SuperBeing> beings2 = new ArrayList<>();
        SuperBeing sb2 = new SuperBeing();
        sb2.setName("Batman");
        sb2.setDescription("The Dark Knight");
        sb2.setIdentity("Bruce Wayne");
        sb2.setPowers(powers2);
        beings2.add(sb2);
        dao.addSuperBeing(sb2);
        
        Location loc2 = new Location();
        loc2.setName("Hall of Justice");
        loc2.setDescription("Good Guy Spa");
        loc2.setAddressLine1("456 4th Ave");
        loc2.setAddressLine2(null);
        loc2.setCity("Metropolis");
        loc2.setRegion("Fake Region");
        loc2.setPostalCode("99999");
        loc2.setCountry("USA");
        loc2.setLatitude(60.60);
        loc2.setLongitude(90.11);
        dao.addLocation(loc2);
        
        Organization org2 = new Organization();
        org2.setDescription("Community Watch");
        org2.setLocation(loc2);
        org2.setPhone("222-222-2222");
        org2.setEmail("flash@cw.com");
        org2.setMembers(beings2);
        
        dao.addOrganization(org2);
        
        assertEquals(2, dao.getAllOrganizations().size());
        
        List<Organization> orgs = dao.getAllOrganizations();
        assertEquals(org, orgs.get(0));
        assertEquals(org2, orgs.get(1));
    }
    
    /**
     * Test getAllOrganizationsBySuperId method
     */
    @Test
    public void testGetAllOrganizationsBySuperId() {
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
        
        Power power2 = new Power();
        power2.setDescription("Super Cool");
        List<Power> powers2 = new ArrayList<>();
        powers2.add(power2);
        
        SuperBeing sb2 = new SuperBeing();
        sb2.setName("Iceman");
        sb2.setDescription("Retired crime fighter");
        sb2.setIdentity("Chuck Lidell");
        sb2.setPowers(powers2);
        
        dao.addSuperBeing(sb2);
        
        List<SuperBeing> members = new ArrayList<>();
        members.add(sb);
        members.add(sb2);
        
        Location loc = new Location();
        loc.setName("Legion of Doom");
        loc.setDescription("Bad guy party palace");
        loc.setAddressLine1("123 1st Street");
        loc.setAddressLine2("Apt 2");
        loc.setCity("Apple Valley");
        loc.setRegion("MN");
        loc.setPostalCode("55111");
        loc.setCountry("USA");
        loc.setLatitude(100.5);
        loc.setLongitude(75.75);
        dao.addLocation(loc);
        
        Location loc2 = new Location();
        loc2.setName("Hall of Justice");
        loc2.setDescription("Good Guy Spa");
        loc2.setAddressLine1("456 4th Ave");
        loc2.setAddressLine2(null);
        loc2.setCity("Metropolis");
        loc2.setRegion("Fake Region");
        loc2.setPostalCode("99999");
        loc2.setCountry("USA");
        loc2.setLatitude(60.60);
        loc2.setLongitude(90.11);
        dao.addLocation(loc2);
        
        Organization org = new Organization();
        org.setDescription("Justice League");
        org.setLocation(loc);
        org.setPhone("111-111-1111");
        org.setEmail("sup@jl.com");
        org.setMembers(members);
        dao.addOrganization(org);
        
        Organization org2 = new Organization();
        org2.setDescription("Temple of Doom");
        org2.setLocation(loc2);
        org2.setPhone("222-222-2222");
        org2.setEmail("me@doom.com");
        org2.setMembers(members);
        dao.addOrganization(org2);
        
        List<Organization> orgList = 
                dao.getAllOrganizationsBySuperId(sb.getSuperId());
        
        assertEquals(org, orgList.get(0));
        assertEquals(org2, orgList.get(1));
    }
}
