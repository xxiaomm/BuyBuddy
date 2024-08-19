use BuyBuddy;

select * from Users;
select * from Roles;
select * from User_Roles;

DROP TABLE IF EXISTS User_Roles;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Roles;

create table Users(
  id BIGINT AUTO_INCREMENT Primary key,
  username VARCHAR(255),
  password VARCHAR(255),
  email VARCHAR(255),
  shipping_address VARCHAR(255),
  billing_address VARCHAR(255),
  payment_method VARCHAR(255)
);
create table Roles(
  id BIGINT AUTO_INCREMENT Primary key,
  name VARCHAR(255)
);
create table User_Roles(
   user_id BIGINT Primary key,
   role_id BIGINT,
   foreign key(user_id) references Users(id),
   foreign key(role_id) references Roles(id)
);

insert into Roles(id, name)
values (1, "ROLE_ADMIN"),
       (2, "ROLE_USER");

-- ALTER TABLE table_name
--     ADD COLUMN column_name data_type [constraints];

-- insert into house(address, max_occupant, landlord_id)
-- values
--     ("666 Tom Ave", 4, 1),
--     ("667 Tom Ave", 4, 1),
--     ("668 Tom Ave", 4, 1),
--     ("669 Jerry Ave", 4, 2),
--     ("670 Jerry Ave", 4, 2),
--     ("671 Jerry Ave", 4, 2);