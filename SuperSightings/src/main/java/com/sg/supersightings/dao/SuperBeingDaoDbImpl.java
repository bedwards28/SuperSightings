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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author blake
 */
@Component
public class SuperBeingDaoDbImpl implements SuperBeingDao {

    private JdbcTemplate jt;

    public void setJdbcTemplate(JdbcTemplate jt) {
        this.jt = jt;
    }

    // Super-being queries
    private static final String INSERT_SUPER_BEING
            = "insert into super_being (name, description, identity) "
            + "values (?, ?, ?)";

    private static final String SELECT_SUPER_BY_ID
            = "select * from super_being where super_id = ?";

    private static final String SELECT_ALL_POWERS_BY_SUPER_ID
            = "select p.power_id, p.description "
            + "from power p "
            + "join super_being_power sbp on p.power_id = sbp.power_id "
            + "where sbp.super_id = ?";

    private static final String INSERT_SUPER_BEING_POWER
            = "insert into super_being_power (super_id, power_id) "
            + "values (?, ?)";

    private static final String DELETE_SUPER_BEING
            = "delete from super_being where super_id = ?";

    private static final String UPDATE_SUPER_BEING
            = "update super_being set name = ?, description = ?, "
            + "identity = ? "
            + "where super_id = ?";

    private static final String SELECT_ALL_SUPER_BEINGS
            = "select * from super_being";

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
            = "select * from power order by description ASC";

    private static final String UPDATE_POWER
            = "update power set description = ? where power_id = ?";

    private static final String DELETE_SUPER_BEING_POWERS_BY_SUPER_ID
            = "delete from super_being_power where super_id = ?";

    private static final String DELETE_SUPER_BEING_POWER_BY_POWER_ID
            = "delete from super_being_power where power_id = ?";
    
    private static final String DELETE_SUPER_BEING_SIGHTING_BY_SUPER_ID 
            = "delete from super_being_sighting where super_id = ?";
    
    private static final String DELETE_ORGANIZATION_MEMBERS_BY_SUPER_ID 
            = "delete from organization_members where super_id = ?";

    // Location queries
    private static final String INSERT_LOCATION
            = "insert into location (name, description, address_line_1, "
            + "address_line_2, city, region, postal_code, country, "
            + "latitude, longitude) "
            + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String DELETE_LOCATION
            = "delete from location where location_id = ?";

    private static final String UPDATE_LOCATION
            = "update location set name = ?, description = ?, "
            + "address_line_1 = ?, address_line_2 = ?, city = ?, "
            + "region = ?, postal_code = ?, country = ?, "
            + "latitude = ?, longitude = ? "
            + "where location_id = ?";

    private static final String SELECT_LOCATION_BY_ID
            = "select * from location where location_id = ?";

    private static final String SELECT_ALL_LOCATIONS
            = "select * from location";

    private static final String SELECT_ALL_LOCATIONS_BY_SUPER_ID
            = "select * "
            + "from location l "
            + "join sighting s "
            + "on l.location_id = s.location_id "
            + "join super_being_sighting sbs "
            + "on s.sighting_id = sbs.sighting_id "
            + "where sbs.super_id = ?";

    // sighting queries
    private static final String INSERT_SIGHTING
            = "insert into sighting (location_id, date) "
            + "values (?, ?)";

    private static final String DELETE_SIGHTING
            = "delete from sighting where sighting_id = ?";

    private static final String SELECT_SIGHTING_BY_ID
            = "select * from sighting where sighting_id = ?";

    private static final String SELECT_LOCATION_BY_SIGHTING_ID
            = "select * "
            + "from location l "
            + "join sighting s on l.location_id = s.location_id "
            + "where s.sighting_id = ?";

    private static final String SELECT_ALL_SUPER_BEINGS_BY_SIGHTING_ID
            = "select * "
            + "from super_being sb "
            + "join super_being_sighting sbs on sb.super_id = sbs.super_id "
            + "where sbs.sighting_id = ?";

    private static final String SELECT_ALL_SIGHTINGS
            = "select * from sighting";

    private static final String SELECT_ALL_SIGHTINGS_BY_DATE
            = "select * from sighting where date = ?";

    private static final String INSERT_SUPER_BEING_SIGHTING
            = "insert into super_being_sighting (super_id, sighting_id) "
            + "values (?, ?)";

    private static final String DELETE_ALL_SUPER_SIGHTINGS_BY_SIGHTING_ID
            = "delete from super_being_sighting where sighting_id = ?";

    private static final String UPDATE_SIGHTING
            = "update sighting "
            + "set date = ?, location_id = ? "
            + "where sighting_id = ?";

    private static final String INSERT_ORGANIZATION
            = "insert into organization (name, location_id, phone, email) "
            + "values (?, ?, ?, ?)";

    private static final String DELETE_ORGANIZATION
            = "delete from organization where organization_id = ?";

    private static final String UPDATE_ORGANIZATION
            = "update organization set name = ?, location_id = ?, "
            + "phone = ?, email = ? "
            + "where organization_id = ?";

    private static final String SELECT_ORGANIZATION_BY_ID
            = "select * from organization where organization_id = ?";

    private static final String SELECT_LOCATION_ID_BY_ORG_ID
            = "select location_id "
            + "from organization "
            + "where organization_id = ?";

    private static final String SELECT_ALL_ORGANIZATIONS
            = "select * from organization";

    private static final String INSERT_ORG_MEMBER
            = "insert into organization_members (organization_id, super_id) "
            + "values (?, ?)";

    private static final String DELETE_ORG_MEMBERS_BY_ORG_ID
            = "delete from organization_members "
            + "where organization_id = ?";

    private static final String SELECT_ALL_SUPER_IDS_BY_ORG_ID
            = "select super_id from organization_members "
            + "where organization_id = ?";

    private static final String SELECT_ALL_ORG_IDS_FOR_SUPER_ID
            = "select organization_id "
            + "from organization_members "
            + "where super_id = ?";

    private static final String SELECT_MOST_RECENT_SIGHTINGS
            = "select * from sighting order by date desc limit 10";

    @Override
    @Transactional
    public SuperBeing addSuperBeing(SuperBeing being) throws SuperBeingPersistenceException {
        try {
            jt.update(INSERT_SUPER_BEING,
                being.getName(),
                being.getDescription(),
                being.getIdentity());

            int super_id
                    = jt.queryForObject("select LAST_INSERT_ID()",
                            Integer.class);

            being.setSuperId(super_id);

            insertPowers(being);

            insertSuperBeingPowers(being);

            return being;
        } catch (DataAccessException e) {
            throw new SuperBeingPersistenceException(e.getMessage());
        }
        
    }

    @Override
    @Transactional
    public SuperBeing getSuperBeingById(int superId) throws SuperBeingPersistenceException {
        try {
            SuperBeing sb = jt.queryForObject(SELECT_SUPER_BY_ID,
                    new SuperMapper(),
                    superId);

            // add powers
            associatePowersWithSuper(sb);
            return sb;
        } catch (EmptyResultDataAccessException e) {
            throw new SuperBeingPersistenceException(e.getMessage());
        }
    }

    private void insertPowers(SuperBeing sb) throws SuperBeingPersistenceException {
        try {
            List<Power> powers = sb.getPowers();

            if (powers != null) {
                for (Power currentPower : powers) {
                    addPower(currentPower);
                }
            }
        } catch (EmptyResultDataAccessException e) {
            throw new SuperBeingPersistenceException(e.getMessage());
        }
        
    }

    private void insertSuperBeingPowers(SuperBeing sb) throws SuperBeingPersistenceException {
        try {
            final int superId = sb.getSuperId();
            final List<Power> powers = sb.getPowers();

            if (powers != null) {
                for (Power currentPower : powers) {
                    jt.update(INSERT_SUPER_BEING_POWER,
                            superId,
                            currentPower.getPowerId());
                }
            }
        } catch (EmptyResultDataAccessException e) {
            throw new SuperBeingPersistenceException(e.getMessage());
        }
        
    }

    private List<Power> findPowersForSuperBeing(SuperBeing sb) throws SuperBeingPersistenceException {
        try {
            return jt.query(SELECT_ALL_POWERS_BY_SUPER_ID,
                new PowerMapper(),
                sb.getSuperId());
        } catch (EmptyResultDataAccessException e) {
            throw new SuperBeingPersistenceException(e.getMessage());
        }
        
    }

    private void associatePowersWithSuper(SuperBeing sb) throws SuperBeingPersistenceException {
        try {
            sb.setPowers(findPowersForSuperBeing(sb));
        } catch (EmptyResultDataAccessException e) {
            throw new SuperBeingPersistenceException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public int deleteSuperBeing(int superId) throws SuperBeingPersistenceException {
        try {
            // delete super_being_sighting entries
            deleteSuperBeingSightingBySuperId(superId);
            
            // delete organizatino_members entries
            deleteOrganizationMembersBySuperId(superId);
            
            // delete super_being_power entries
            deleteSuperBeingPowersBySuperId(superId);

            // delete super_being entry
            return jt.update(DELETE_SUPER_BEING, superId);

        } catch (DataAccessException e) {
            throw new SuperBeingPersistenceException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public SuperBeing updateSuperBeing(SuperBeing being) throws SuperBeingPersistenceException {
        try {
            jt.update(UPDATE_SUPER_BEING,
                being.getName(),
                being.getDescription(),
                being.getIdentity(),
                being.getSuperId());

            insertPowers(being);

            // delete super_being_power entries
            deleteSuperBeingPowersBySuperId(being.getSuperId());

            // recreate super_being_power entries
            insertSuperBeingPowers(being);

            return being;
        } catch (DataAccessException e) {
            throw new SuperBeingPersistenceException(e.getMessage());
        }
        
    }
    
    private int deleteSuperBeingPowersBySuperId(int superId) 
            throws SuperBeingPersistenceException {
        try {
            return jt.update(DELETE_SUPER_BEING_POWERS_BY_SUPER_ID, superId);
        } catch (DataAccessException e) {
            throw new SuperBeingPersistenceException(e.getMessage());
        }
    }

    private int deleteSuperBeingSightingBySuperId(int superId) 
            throws SuperBeingPersistenceException {
        try {
            return jt.update(DELETE_SUPER_BEING_SIGHTING_BY_SUPER_ID, superId);
        } catch (DataAccessException e) {
            throw new SuperBeingPersistenceException(e.getMessage());
        }
    }
    
    private int deleteOrganizationMembersBySuperId(int superId) 
            throws SuperBeingPersistenceException {
        try {
            return jt.update(DELETE_ORGANIZATION_MEMBERS_BY_SUPER_ID, superId);
        } catch (DataAccessException e) {
            throw new SuperBeingPersistenceException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<SuperBeing> getAllSuperBeings() throws SuperBeingPersistenceException {
        try {
            List<SuperBeing> beings
                    = jt.query(SELECT_ALL_SUPER_BEINGS, new SuperMapper());

            if (beings.size() > 0) {
                for (SuperBeing currentBeing : beings) {
                    this.associatePowersWithSuper(currentBeing);
                }
            }

            return beings;
        } catch (DataAccessException e) {
            throw new SuperBeingPersistenceException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<SuperBeing> getAllOrganizationMembers(int orgId) throws SuperBeingPersistenceException {
        try {
            List<SuperBeing> beings = new ArrayList<>();

            List<Integer> superIds = jt.query(
                    SELECT_ALL_SUPER_IDS_BY_ORG_ID,
                    new SuperIdMapper(),
                    orgId);

            if (superIds.size() > 0) {
                for (Integer currentId : superIds) {
                    SuperBeing sb = getSuperBeingById(currentId);
                    beings.add(sb);
                }
            }

            return beings;
        } catch (DataAccessException e) {
            throw new SuperBeingPersistenceException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Power addPower(Power power) throws SuperBeingPersistenceException {
        try {
            Power fromDb = new Power();

            try {
                fromDb = jt.queryForObject(SELECT_POWER_BY_DESCRIPTION,
                        new PowerMapper(),
                        power.getDescription());
            } catch (DataAccessException e) {
                fromDb = null;
            }

            if (fromDb == null) {
                jt.update(INSERT_POWER, power.getDescription());

                int powerId
                        = jt.queryForObject("select LAST_INSERT_ID()", Integer.class);

                power.setPowerId(powerId);
            } else {
                power.setPowerId(fromDb.getPowerId());
            }
            return power;
        } catch (DataAccessException e) {
            throw new SuperBeingPersistenceException(e.getMessage());
        }
        
    }

    @Override
    @Transactional
    public int deletePower(int powerId) throws SuperBeingPersistenceException {
        try {
            // delete super_being_power table entries
            jt.update(DELETE_SUPER_BEING_POWER_BY_POWER_ID, powerId);
            // delete power table entry
            return jt.update(DELETE_POWER, powerId);
        } catch (DataAccessException e) {
            throw new SuperBeingPersistenceException(e.getMessage());
        }
    }

    @Override
    public Power updatePower(Power power) throws SuperBeingPersistenceException {
        try {
            jt.update(UPDATE_POWER, power.getDescription(), power.getPowerId());
            return power;
        } catch (DataAccessException e) {
            throw new SuperBeingPersistenceException(e.getMessage());
        }
    }

    @Override
    public Power getPowerById(int powerId) throws SuperBeingPersistenceException {
        try {
            return jt.queryForObject(SELECT_POWER_BY_ID,
                    new PowerMapper(),
                    powerId);
        } catch (EmptyResultDataAccessException e) {
            throw new SuperBeingPersistenceException(e.getMessage());
        }

    }

    @Override
    public Power getPowerByDescription(String description) throws SuperBeingPersistenceException {
        try {
            return jt.queryForObject(SELECT_POWER_BY_DESCRIPTION,
                    new PowerMapper(),
                    description);
        } catch (EmptyResultDataAccessException e) {
            throw new SuperBeingPersistenceException(e.getMessage());
        }
    }

    @Override
    public List<Power> getAllPowers() throws SuperBeingPersistenceException {
        try {
            return jt.query(SELECT_ALL_POWERS, new PowerMapper());
        } catch (DataAccessException e) {
            throw new SuperBeingPersistenceException(e.getMessage());
        }
    }



    @Override
    @Transactional
    public Location addLocation(Location loc) throws SuperBeingPersistenceException {
        try {
            jt.update(INSERT_LOCATION,
                loc.getName(),
                loc.getDescription(),
                loc.getAddressLine1(),
                loc.getAddressLine2(),
                loc.getCity(),
                loc.getRegion(),
                loc.getPostalCode(),
                loc.getCountry(),
                loc.getLatitude(),
                loc.getLongitude());

            int locationId
                    = jt.queryForObject("select LAST_INSERT_ID()", Integer.class);

            loc.setLocationId(locationId);

            return loc;
        } catch (DataAccessException e) {
            throw new SuperBeingPersistenceException(e.getMessage());
        }
        
    }

    @Override
    public Location getLocationById(int locationId) throws SuperBeingPersistenceException {
        try {
            return jt.queryForObject(SELECT_LOCATION_BY_ID,
                    new LocationMapper(),
                    locationId);
        } catch (DataAccessException e) {
            throw new SuperBeingPersistenceException(e.getMessage());
        }
    }

    @Override
    public List<Location> getAllLocations() throws SuperBeingPersistenceException {
        try {
            return jt.query(SELECT_ALL_LOCATIONS, new LocationMapper());
        } catch (DataAccessException e) {
            throw new SuperBeingPersistenceException(e.getMessage());
        }
    }

    @Override
    public List<Location> getAllLocationsBySuperId(int superId) throws SuperBeingPersistenceException {
        try {
            return jt.query(SELECT_ALL_LOCATIONS_BY_SUPER_ID,
                new LocationMapper(),
                superId);
        } catch (DataAccessException e) {
            throw new SuperBeingPersistenceException(e.getMessage());
        }
    }

    @Override
    public Location updateLocation(Location loc) throws SuperBeingPersistenceException {
        try {
            jt.update(UPDATE_LOCATION,
                loc.getName(),
                loc.getDescription(),
                loc.getAddressLine1(),
                loc.getAddressLine2(),
                loc.getCity(),
                loc.getRegion(),
                loc.getPostalCode(),
                loc.getCountry(),
                loc.getLatitude(),
                loc.getLongitude(),
                loc.getLocationId());
            
            return this.getLocationById(loc.getLocationId());
        } catch (DataAccessException e) {
            throw new SuperBeingPersistenceException(e.getMessage());
        }
    }

    @Override
    public int deleteLocationById(int locationId) throws SuperBeingPersistenceException {
        try {
            return jt.update(DELETE_LOCATION, locationId);
        } catch (DataAccessException e) {
            throw new SuperBeingPersistenceException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Sighting addSighting(Sighting sighting) throws SuperBeingPersistenceException {
        try {
            jt.update(INSERT_SIGHTING,
                sighting.getLocation().getLocationId(),
                sighting.getDate().toString());

            sighting.setSightingId(
                    jt.queryForObject("select LAST_INSERT_ID()", Integer.class));

            // insert super_being_sighting bridge table entries
            insertSuperBeingSighting(
                    sighting.getSightingId(), sighting.getSuperBeings());
            
            return sighting;
        } catch (DataAccessException e) {
            throw new SuperBeingPersistenceException(e.getMessage());
        }
        
    }

    private void insertSuperBeingSighting(
            int sightingId, List<SuperBeing> beings) throws SuperBeingPersistenceException {
        try {
            for (SuperBeing currentBeing : beings) {
                jt.update(INSERT_SUPER_BEING_SIGHTING,
                        currentBeing.getSuperId(),
                        sightingId);
            }
        } catch (DataAccessException e) {
            throw new SuperBeingPersistenceException(e.getMessage());
        }
        
    }

    @Override
    @Transactional
    public int deleteSighting(int sightingId) throws SuperBeingPersistenceException {
        try {
            // delete super_being_sighting enries
            jt.update(DELETE_ALL_SUPER_SIGHTINGS_BY_SIGHTING_ID, sightingId);
            // delete sighting table entry
            return jt.update(DELETE_SIGHTING, sightingId);
        } catch (DataAccessException e) {
            throw new SuperBeingPersistenceException(e.getMessage());
        }

    }

    @Override
    @Transactional
    public Sighting updateSighting(Sighting s) throws SuperBeingPersistenceException {
        try {
            String str = s.getDate().toString();
            jt.update(UPDATE_SIGHTING,
                    s.getDate().toString(),
                    s.getLocation().getLocationId(),
                    s.getSightingId());

            // delete super_being_sighting entries
            jt.update(DELETE_ALL_SUPER_SIGHTINGS_BY_SIGHTING_ID, s.getSightingId());

            // recreate super_being_sighting entries
            insertSuperBeingSighting(
                    s.getSightingId(), s.getSuperBeings());
            
            return s;
        } catch (DataAccessException e) {
            throw new SuperBeingPersistenceException(e.getMessage());
        }
        
    }

    @Override
    @Transactional
    public Sighting getSightingById(int sightingId) throws SuperBeingPersistenceException {
        try {
            Sighting s = jt.queryForObject(SELECT_SIGHTING_BY_ID,
                    new SightingMapper(),
                    sightingId);

            // associate location with sighting
            associateLocationWithSighting(s);

            // associate super beings with sighting
            associateSuperBeingsWithSighting(s);

            return s;
        } catch (DataAccessException e) {
            throw new SuperBeingPersistenceException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<Sighting> getAllSightings() throws SuperBeingPersistenceException {
        try {
            List<Sighting> sightings
                    = jt.query(SELECT_ALL_SIGHTINGS, new SightingMapper());

            if (sightings.size() > 0) {
                for (Sighting currentSighting : sightings) {
                    associateLocationWithSighting(currentSighting);
                    associateSuperBeingsWithSighting(currentSighting);
                }
            }

            return sightings;
        } catch (DataAccessException e) {
            throw new SuperBeingPersistenceException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<Sighting> getAllSightingsByDate(LocalDate date) throws SuperBeingPersistenceException {
        try {
            List<Sighting> sightings = jt.query(
                    SELECT_ALL_SIGHTINGS_BY_DATE,
                    new SightingMapper(),
                    date.toString());

            if (sightings.size() > 0) {
                for (Sighting currentSighting : sightings) {
                    associateLocationWithSighting(currentSighting);
                    associateSuperBeingsWithSighting(currentSighting);
                }
            }

            return sightings;
        } catch (DataAccessException e) {
            throw new SuperBeingPersistenceException(e.getMessage());
        }
    }

    private void associateLocationWithSighting(Sighting s) throws SuperBeingPersistenceException {
        try {
            // Get location object for sighting
            Location loc = jt.queryForObject(SELECT_LOCATION_BY_SIGHTING_ID,
                    new LocationMapper(),
                    s.getSightingId());

            s.setLocation(loc);
        } catch (DataAccessException e) {
            throw new SuperBeingPersistenceException(e.getMessage());
        }
        
    }

    private void associateSuperBeingsWithSighting(Sighting s) throws SuperBeingPersistenceException {
        try {
            List<SuperBeing> beings
                = jt.query(SELECT_ALL_SUPER_BEINGS_BY_SIGHTING_ID,
                        new SuperMapper(),
                        s.getSightingId());
            
            if (beings.size() > 0) {
                // Associate powers with supers
                for (SuperBeing currentBeing : beings) {
                    this.associatePowersWithSuper(currentBeing);
                }
            }

            s.setSuperBeings(beings);
        } catch (DataAccessException e) {
            throw new SuperBeingPersistenceException(e.getMessage());
        }
        
    }

    @Override
    @Transactional
    public Organization addOrganization(Organization org) throws SuperBeingPersistenceException {
        try {
            jt.update(INSERT_ORGANIZATION,
                org.getName(),
                org.getLocation().getLocationId(),
                org.getPhone(),
                org.getEmail());

            int orgId = jt.queryForObject("select LAST_INSERT_ID()", Integer.class);

            org.setOrganizationId(orgId);

            // create organization_member entries
            insertOrganizationMembers(org);
            
            return org;
        } catch (DataAccessException e) {
            throw new SuperBeingPersistenceException(e.getMessage());
        }
        
    }

    private void insertOrganizationMembers(Organization o) throws SuperBeingPersistenceException {
        try {
            List<SuperBeing> members = o.getMembers();

            if (members != null) {
                for (SuperBeing currentMember : members) {
                    jt.update(INSERT_ORG_MEMBER,
                            o.getOrganizationId(),
                            currentMember.getSuperId());
                }
            }
        } catch (DataAccessException e) {
            throw new SuperBeingPersistenceException(e.getMessage());
        }
        
    }

    @Override
    @Transactional
    public int deleteOrganization(int orgId) throws SuperBeingPersistenceException {
        try {
            // delete organization_members entries
            jt.update(DELETE_ORG_MEMBERS_BY_ORG_ID, orgId);

            // delete organization table entry
            return jt.update(DELETE_ORGANIZATION, orgId);
        } catch (DataAccessException e) {
            throw new SuperBeingPersistenceException(e.getMessage());
        }
        
    }

    @Override
    @Transactional
    public Organization updateOrganization(Organization org) throws SuperBeingPersistenceException {
        try {
            jt.update(UPDATE_ORGANIZATION,
                org.getName(),
                org.getLocation().getLocationId(),
                org.getPhone(),
                org.getEmail(),
                org.getOrganizationId());

            // delete organization_memmbers entries
            jt.update(DELETE_ORG_MEMBERS_BY_ORG_ID, org.getOrganizationId());

            // recreate organization_members entries
            insertOrganizationMembers(org);
            
            return org;
        } catch (DataAccessException e) {
            throw new SuperBeingPersistenceException(e.getMessage());
        }
        
    }

    @Override
    @Transactional
    public Organization getOrganizationById(int orgId) throws SuperBeingPersistenceException {
        try {
            Organization org = jt.queryForObject(
                    SELECT_ORGANIZATION_BY_ID,
                    new OrganizationMapper(),
                    orgId);

            associateLocationWithOrg(org);

            associateMembersWithOrg(org);

            return org;

        } catch (DataAccessException e) {
            throw new SuperBeingPersistenceException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<Organization> getAllOrganizations() throws SuperBeingPersistenceException {
        try {
            List<Organization> orgs
                    = jt.query(SELECT_ALL_ORGANIZATIONS, new OrganizationMapper());

            if (orgs.size() > 0) {
                for (Organization currentOrg : orgs) {
                    associateLocationWithOrg(currentOrg);
                    associateMembersWithOrg(currentOrg);
                }
            }

            return orgs;
        } catch (DataAccessException e) {
            throw new SuperBeingPersistenceException(e.getMessage());
        }

    }

    @Override
    @Transactional
    public List<Organization> getAllOrganizationsBySuperId(int superId) throws SuperBeingPersistenceException {
        try {
            List<Organization> orgs = new ArrayList<>();

            List<Integer> orgIds = jt.query(
                    SELECT_ALL_ORG_IDS_FOR_SUPER_ID,
                    new OrgIdMapper(),
                    superId);

            if (orgIds.size() > 0) {
                for (Integer currentId : orgIds) {
                    Organization org = this.getOrganizationById(currentId);
                    orgs.add(org);
                }
            }

            return orgs;
        } catch (DataAccessException e) {
            throw new SuperBeingPersistenceException(e.getMessage());
        }
    }

    private void associateLocationWithOrg(Organization o) throws SuperBeingPersistenceException {
        try {
            int locationId = jt.queryForObject(
                SELECT_LOCATION_ID_BY_ORG_ID,
                Integer.class,
                o.getOrganizationId());

            Location loc = getLocationById(locationId);

            o.setLocation(loc);
        } catch (DataAccessException e) {
            throw new SuperBeingPersistenceException(e.getMessage());
        }
        
    }

    private void associateMembersWithOrg(Organization o) throws SuperBeingPersistenceException {
        try {
            List<SuperBeing> members = new ArrayList<>();

            List<Integer> superIds = jt.query(
                    SELECT_ALL_SUPER_IDS_BY_ORG_ID,
                    new SuperIdMapper(),
                    o.getOrganizationId());

            if (superIds.size() > 0) {
                for (Integer currentId : superIds) {
                    SuperBeing sb = this.getSuperBeingById(currentId);
                    members.add(sb);
                }
            }

            o.setMembers(members);
        } catch (DataAccessException e) {
            throw new SuperBeingPersistenceException(e.getMessage());
        }
        
    }

    @Override
    public List<Sighting> getMostRecentSightings() throws SuperBeingPersistenceException {
        try {
            List<Sighting> sightingList = jt.query(SELECT_MOST_RECENT_SIGHTINGS,
                new SightingMapper());

            if (sightingList.size() > 0) {
                for (Sighting currentSighting : sightingList) {
                    // associate location with sighting
                    associateLocationWithSighting(currentSighting);

                    // associate super beings with sighting
                    associateSuperBeingsWithSighting(currentSighting);
                }
            }

            return sightingList;
        } catch (DataAccessException e) {
            throw new SuperBeingPersistenceException(e.getMessage());
        }
        
    }

    private static final class SuperMapper implements RowMapper<SuperBeing> {

        @Override
        public SuperBeing mapRow(ResultSet rs, int i) throws SQLException {
            SuperBeing sb = new SuperBeing();
            sb.setName(rs.getString("name"));
            sb.setDescription(rs.getString("description"));
            sb.setIdentity(rs.getString("identity"));
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

    private static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location l = new Location();
            l.setLocationId(rs.getInt("location_id"));
            l.setName(rs.getString("name"));
            l.setDescription(rs.getString("description"));
            l.setAddressLine1(rs.getString("address_line_1"));
            l.setAddressLine2(rs.getString("address_line_2"));
            l.setCity(rs.getString("city"));
            l.setRegion(rs.getString("region"));
            l.setPostalCode(rs.getString("postal_code"));
            l.setCountry(rs.getString("country"));
            l.setLatitude(rs.getDouble("latitude"));
            l.setLongitude(rs.getDouble("longitude"));
            return l;
        }

    }

    private static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            Organization org = new Organization();
            org.setOrganizationId(rs.getInt("organization_id"));
            org.setName(rs.getString("name"));
            org.setPhone(rs.getString("phone"));
            org.setEmail(rs.getString("email"));
            return org;
        }

    }

    private static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Sighting s = new Sighting();
            s.setSightingId(rs.getInt("sighting_id"));
            s.setDate(rs.getTimestamp("date").toLocalDateTime().toLocalDate());
            return s;
        }

    }

    private static final class SuperIdMapper implements RowMapper<Integer> {

        @Override
        public Integer mapRow(ResultSet rs, int i) throws SQLException {
            Integer integer;
            integer = rs.getInt("super_id");
            return integer;
        }

    }

    private static final class OrgIdMapper implements RowMapper<Integer> {

        @Override
        public Integer mapRow(ResultSet rs, int i) throws SQLException {
            Integer integer;
            integer = rs.getInt("organization_id");
            return integer;
        }

    }

}
