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
            = "select * from power";

    private static final String UPDATE_POWER
            = "update power set description = ? where power_id = ?";
//    
    private static final String DELETE_ALL_POWERS_BY_SUPER_ID
            = "delete from super_being_power where super_id = ?";

    private static final String DELETE_SUPER_BEING_POWER_BY_POWER_ID
            = "delete from super_being_power where power_id = ?";

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

    @Override
    @Transactional
    public void addSuperBeing(SuperBeing being) {
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
    }

    @Override
    @Transactional
    public SuperBeing getSuperBeingById(int superId) {
        try {
            SuperBeing sb = jt.queryForObject(SELECT_SUPER_BY_ID,
                    new SuperMapper(),
                    superId);

            // add powers
            associatePowersWithSuper(sb);
            return sb;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private void insertPowers(SuperBeing sb) {
        List<Power> powers = sb.getPowers();

        if (powers != null) {
            for (Power currentPower : powers) {
                addPower(currentPower);
            }
        }
    }

    private void insertSuperBeingPowers(SuperBeing sb) {
        final int superId = sb.getSuperId();
        final List<Power> powers = sb.getPowers();

        if (powers != null) {
            for (Power currentPower : powers) {
                jt.update(INSERT_SUPER_BEING_POWER,
                        superId,
                        currentPower.getPowerId());
            }
        }
    }

    private List<Power> findPowersForSuperBeing(SuperBeing sb) {
        return jt.query(SELECT_ALL_POWERS_BY_SUPER_ID,
                new PowerMapper(),
                sb.getSuperId());
    }

    private void associatePowersWithSuper(SuperBeing sb) {
        sb.setPowers(findPowersForSuperBeing(sb));
    }

    @Override
    @Transactional
    public SuperBeing deleteSuperBeing(int superId) {
        try {
            SuperBeing sb = getSuperBeingById(superId);

            // delete super_being_power entries
            deleteSuperBeingPowersBySuperId(superId);

            // delete super_being entry
            jt.update(DELETE_SUPER_BEING, superId);

            return sb;
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public SuperBeing updateSuperBeing(SuperBeing being) {
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
    }

    @Override
    @Transactional
    public List<SuperBeing> getAllSuperBeings() {
        try {
            List<SuperBeing> beings
                    = jt.query(SELECT_ALL_SUPER_BEINGS, new SuperMapper());

            for (SuperBeing currentBeing : beings) {
                this.associatePowersWithSuper(currentBeing);
            }

            return beings;
        } catch (DataAccessException e) {
            return null;
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public List<SuperBeing> getAllOrganizationMembers(int orgId) {
        try {
            List<SuperBeing> beings = new ArrayList<>();

            List<Integer> superIds = jt.query(
                    SELECT_ALL_SUPER_IDS_BY_ORG_ID,
                    new SuperIdMapper(),
                    orgId);

            for (Integer currentId : superIds) {
                SuperBeing sb = getSuperBeingById(currentId);
                beings.add(sb);
            }

            return beings;
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public Power addPower(Power power) {
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
    }

    @Override
    @Transactional
    public void deletePower(int powerId) {
        try {
            // delete super_being_power table entries
            jt.update(DELETE_SUPER_BEING_POWER_BY_POWER_ID, powerId);
            // delete power table entry
            jt.update(DELETE_POWER, powerId);
        } catch (DataAccessException e) {
            // do nothing
        }
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

    private void deleteSuperBeingPowersBySuperId(int superId) {
        try {
            jt.update(DELETE_ALL_POWERS_BY_SUPER_ID, superId);
        } catch (DataAccessException e) {
            // do nothing
        }
    }

    @Override
    @Transactional
    public void addLocation(Location loc) {
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
    }

    @Override
    public Location getLocationById(int locationId) {
        try {
            return jt.queryForObject(SELECT_LOCATION_BY_ID,
                    new LocationMapper(),
                    locationId);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Location> getAllLocations() {
        return jt.query(SELECT_ALL_LOCATIONS, new LocationMapper());
    }

    @Override
    public List<Location> getAllLocationsBySuperId(int superId) {
        return jt.query(SELECT_ALL_LOCATIONS_BY_SUPER_ID,
                new LocationMapper(),
                superId);
    }

    @Override
    public void updateLocation(Location loc) {
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
    }

    @Override
    public void deleteLocationById(int locationId) {
        try {
            jt.update(DELETE_LOCATION, locationId);
        } catch (DataAccessException e) {
            // do nothing
        }
    }

    @Override
    @Transactional
    public void addSighting(Sighting sighting) {
        jt.update(INSERT_SIGHTING,
                sighting.getLocation().getLocationId(),
                sighting.getDate().toString());

        sighting.setSightingId(
                jt.queryForObject("select LAST_INSERT_ID()", Integer.class));

        // insert super_being_sighting bridge table entries
        insertSuperBeingSighting(
                sighting.getSightingId(), sighting.getSuperBeings());
    }

    private void insertSuperBeingSighting(
            int sightingId, List<SuperBeing> beings) {

        for (SuperBeing currentBeing : beings) {
            jt.update(INSERT_SUPER_BEING_SIGHTING,
                    currentBeing.getSuperId(),
                    sightingId);
        }
    }

    @Override
    @Transactional
    public void deleteSighting(int sightingId) {
        try {
            // delete super_being_sighting enries
            jt.update(DELETE_ALL_SUPER_SIGHTINGS_BY_SIGHTING_ID, sightingId);
            // delete sighting table entry
            jt.update(DELETE_SIGHTING, sightingId);
        } catch (DataAccessException e) {
            // do nothing
        }

    }

    @Override
    @Transactional
    public void updateSighting(Sighting s) {
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
    }

    @Override
    @Transactional
    public Sighting getSightingById(int sightingId) {
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
            return null;
        }
    }

    @Override
    @Transactional
    public List<Sighting> getAllSightings() {
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
            return null;
        }
    }

    @Override
    @Transactional
    public List<Sighting> getAllSightingsByDate(LocalDate date) {
        try {
            List<Sighting> sightings = jt.query(
                    SELECT_ALL_SIGHTINGS_BY_DATE,
                    new SightingMapper(),
                    date.toString());

            for (Sighting currentSighting : sightings) {
                associateLocationWithSighting(currentSighting);
                associateSuperBeingsWithSighting(currentSighting);
            }

            return sightings;
        } catch (DataAccessException e) {
            return null;
        }
    }

    private void associateLocationWithSighting(Sighting s) {
        // Get location object for sighting
        Location loc = jt.queryForObject(SELECT_LOCATION_BY_SIGHTING_ID,
                new LocationMapper(),
                s.getSightingId());

        s.setLocation(loc);
    }

    private void associateSuperBeingsWithSighting(Sighting s) {
        List<SuperBeing> beings
                = jt.query(SELECT_ALL_SUPER_BEINGS_BY_SIGHTING_ID,
                        new SuperMapper(),
                        s.getSightingId());

        // Associate powers with supers
        for (SuperBeing currentBeing : beings) {
            this.associatePowersWithSuper(currentBeing);
        }

        s.setSuperBeings(beings);
    }

    @Override
    @Transactional
    public void addOrganization(Organization org) {
        jt.update(INSERT_ORGANIZATION,
                org.getName(),
                org.getLocation().getLocationId(),
                org.getPhone(),
                org.getEmail());

        int orgId = jt.queryForObject("select LAST_INSERT_ID()", Integer.class);

        org.setOrganizationId(orgId);

        // create organization_member entries
        insertOrganizationMembers(org);
    }

    private void insertOrganizationMembers(Organization o) {
        List<SuperBeing> members = o.getMembers();

        if (members != null) {
            for (SuperBeing currentMember : members) {
                jt.update(INSERT_ORG_MEMBER,
                        o.getOrganizationId(),
                        currentMember.getSuperId());
            }
        }
    }

    @Override
    @Transactional
    public void deleteOrganization(int orgId) {
        // delete organization_members entries
        jt.update(DELETE_ORG_MEMBERS_BY_ORG_ID, orgId);

        // delete organization table entry
        jt.update(DELETE_ORGANIZATION, orgId);
    }

    @Override
    @Transactional
    public void updateOrganization(Organization org) {
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
    }

    @Override
    @Transactional
    public Organization getOrganizationById(int orgId) {
        try {
            Organization org = jt.queryForObject(
                    SELECT_ORGANIZATION_BY_ID,
                    new OrganizationMapper(),
                    orgId);

            associateLocationWithOrg(org);

            associateMembersWithOrg(org);

            return org;

        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public List<Organization> getAllOrganizations() {
        try {
            List<Organization> orgs
                    = jt.query(SELECT_ALL_ORGANIZATIONS, new OrganizationMapper());

            for (Organization currentOrg : orgs) {
                associateLocationWithOrg(currentOrg);
                associateMembersWithOrg(currentOrg);
            }

            return orgs;
        } catch (DataAccessException e) {
            return null;
        }

    }

    @Override
    @Transactional
    public List<Organization> getAllOrganizationsBySuperId(int superId) {
        try {
            List<Organization> orgs = new ArrayList<>();

            List<Integer> orgIds = jt.query(
                    SELECT_ALL_ORG_IDS_FOR_SUPER_ID,
                    new OrgIdMapper(),
                    superId);

            for (Integer currentId : orgIds) {
                Organization org = this.getOrganizationById(currentId);
                orgs.add(org);
            }

            return orgs;
        } catch (DataAccessException e) {
            return null;
        }
    }

    private void associateLocationWithOrg(Organization o) {
        int locationId = jt.queryForObject(
                SELECT_LOCATION_ID_BY_ORG_ID,
                Integer.class,
                o.getOrganizationId());

        Location loc = getLocationById(locationId);

        o.setLocation(loc);
    }

    private void associateMembersWithOrg(Organization o) {
        List<SuperBeing> members = new ArrayList<>();

        List<Integer> superIds = jt.query(
                SELECT_ALL_SUPER_IDS_BY_ORG_ID,
                new SuperIdMapper(),
                o.getOrganizationId());

        for (Integer currentId : superIds) {
            SuperBeing sb = this.getSuperBeingById(currentId);
            members.add(sb);
        }

        o.setMembers(members);
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
