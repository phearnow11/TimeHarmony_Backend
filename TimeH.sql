create database TimeHarmony

create table Roles (
	[role_id] int not null, 
	[role_name] varchar(50) null,
	[security_level] int null,
	primary key ([role_id])
)

insert Roles (role_id, role_name, security_level) values (1, N'Buyer', 0)
insert Roles (role_id, role_name, security_level) values (2, N'Seller', 1)
insert Roles (role_id, role_name, security_level) values (3, N'Appraiser', 1)
insert Roles (role_id, role_name, security_level) values (4, N'Admin', 5)

create table Members (
	[member_id] char (6) not null, 
	[member_name] varchar (50) null, 
	[address] varchar (50) null,
	[email] varchar (50) null,
	[phone] varchar(20) null,
	[last_login_date] date,
	[role_id] int null,
	primary key ([member_id]),
	foreign key ([role_id]) references Roles([role_id])
)

create table Admins (
	[member_id] char(6) null,
)

insert Members (member_id, member_name, [address], email, phone, last_login_date, role_id) values (N'000000', N'Phien', null, N'thaiphiennn@gmail.com', null, null, 4)
insert Admins (member_id) values (N'000000')

drop table Members

select * from Members join Roles on Members.role_id = Roles.role_id
