drop database if exists dynamicRealm ;
create database if not exists dynamicRealm ;

use  dynamicRealm ;

create table grup(
	id int primary key auto_increment,
	groupName varchar(20) 
);

create table module(
	id int primary key auto_increment,
	title varchar(20)
);

create table permissions(
	id int primary key auto_increment,
	create_perm BOOLEAN,
	read_perm BOOLEAN,
	update_perm BOOLEAN,
	delete_perm BOOLEAN,
	module_id int,
	FOREIGN KEY (module_id) REFERENCES module(id)  ON DELETE CASCADE
);

create table group_perm(
	group_id int,
	perm_id int,
 	FOREIGN KEY (group_id) REFERENCES grup(id) ON DELETE CASCADE,
	FOREIGN KEY (perm_id) REFERENCES permissions(id) ON DELETE CASCADE
);

create table user_grup(
	user_id int,
	grup_id int,
	FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
	FOREIGN KEY (grup_id) REFERENCES grup(id) ON DELETE CASCADE
);

create table user(
	id int primary key auto_increment,
	name varchar(20),
	email varchar(20)
);
