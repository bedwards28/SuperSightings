DROP DATABASE IF EXISTS super_sighting_test;

CREATE DATABASE super_sighting_test;

USE super_sighting_test;

CREATE TABLE super_being
(
	super_id int primary key auto_increment,
    `name` varchar(45) not null,
    description varchar(500) not null,
    identity varchar(45)
);

CREATE TABLE power
(
	power_id int primary key auto_increment,
    description varchar(45) not null
);

CREATE TABLE super_being_power
(
	super_id int not null,
    power_id int not null,
    primary key (super_id, power_id),
    foreign key (super_id) references super_being(super_id),
    foreign key (power_id) references power(power_id)
);

CREATE TABLE location
(
	location_id int primary key auto_increment,
    `name` varchar(45) not null,
    description varchar(100) not null,
    address_line_1 varchar(45) not null,
    address_line_2 varchar(45),
    city varchar(45) not null,
    region varchar(25) not null,
    postal_code varchar(20) not null,
    country varchar(45) not null,
    latitude decimal(9, 6) not null,
    longitude decimal(9, 6) not null
);

CREATE TABLE organization
(
	organization_id int primary key auto_increment,
    name varchar(45) not null,
    location_id int not null,
    phone varchar(20) not null,
    email varchar(100) not null,
    foreign key (location_id) references location(location_id)
);

CREATE TABLE organization_members
(
	organization_id int not null,
    super_id int not null,
    primary key (organization_id, super_id),
    foreign key (organization_id) references organization(organization_id),
    foreign key (super_id) references super_being(super_id)
);

CREATE TABLE sighting
(
	sighting_id int primary key auto_increment,
    location_id int not null,
    `date` date not null,
    foreign key (location_id) references location(location_id)
);

CREATE TABLE super_being_sighting
(
	super_id int not null,
    sighting_id int not null,
    primary key (super_id, sighting_id),
    foreign key (super_id) references super_being(super_id),
    foreign key (sighting_id) references sighting(sighting_id)
);

-- INSERT INTO super_being (`name`, description, identity)
-- values ('Superman', 'Goody goody', 'Clark Kent'),
--        ('Batman', 'The Dark Knight', 'Bruce Wayne');
--        
-- INSERT INTO power (description)
-- values ('Flight'), ('Heat Vision'), ('Freeze Breath'), ('Utility Belt'), ('Smoke Bombs');
-- 
-- INSERT INTO super_being_power (super_id, power_id)
-- values (1, 1), (1,2), (1,3),
-- 	   (2,4), (2,5);
--        
-- INSERT INTO location (`name`, description, address_line_1, address_line_2, city, region, postal_code, country, latitude, longitude)
-- values ('Fortress of Solitude', 'Party palace for Superman and friends', '1 North Pole Lane', 'Apt 1', 'Northpolis', 'North Pole', '11111', 'Antarctica', '200', '300'),
--        ('Hall of Justice', 'Justice League HQ', '123 1st Street', null, 'Metropolis', 'MM', '55555', 'USA', '500', '600');
-- 
-- INSERT INTO super_organization (location_id, description, phone, email)
-- values (1, 'Super Dudes', '111-111-1111', 'superman@goodguys.com'),
-- 	   (2, 'Justice League Headquarters', '222-222-2222', 'batman@justiceleague.com');
--        
-- INSERT INTO organization_members (organization_id, super_id)
-- values (1, 1), (1, 2),
--        (2, 1), (2, 2);
--        
-- INSERT INTO sighting (location_id, `date`)
-- values (1, '2000/07/04');
-- 
-- INSERT INTO super_being_sighting (super_id, sighting_id)
-- values (1, 1), (2, 1);