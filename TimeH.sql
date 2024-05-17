create database TimeHarmony

create table Roles (
	[role_id] tinyint not null, 
	[role_name] varchar(50) null,
	[security_level] tinyint null,
	primary key ([role_id])
)

insert Roles (role_id, role_name, security_level) values (1, N'Buyer', 0)
insert Roles (role_id, role_name, security_level) values (2, N'Seller', 1)
insert Roles (role_id, role_name, security_level) values (3, N'Appraiser', 1)
insert Roles (role_id, role_name, security_level) values (4, N'Admin', 5)

create table Members (
	[member_id] char (6) not null, 
	[member_image] varchar(50) null,
	[username] varchar (50) null,
	[password] varchar(50) null,
	[first_name] varchar(50) null, 
	[last_name] varchar(50) null, 
	[is_active] tinyint null, 
	[address] varchar (50) null,
	[email] varchar (50) null,
	[phone] varchar(20) null,
	[last_login_date] datetime,
	[last_logout_date] datetime,
	[email_verification] varchar(6),
	[role_id] tinyint null,
	primary key ([member_id]),
	foreign key ([role_id]) references Roles([role_id])
)
insert Members (member_id, username, [password], last_name, is_active, email, role_id) values
(N'000000', N'admin', N'1', N'Phien', 0, N'thaiphiennn@gmail.com', 4)


create table Addresses (
	[address_id] char(6) not null, 
	[member_id] char(6) not null, 
	[phone] varchar(20) null, 
	[address_detail] varchar(50) null, 
	[address_type] tinyint, 
	[is_default] tinyint 
	primary key ([address_id]), 
	foreign key ([member_id]) references Members([member_id])
)

create table Watch(
	[watch_id] char(6) not null, 
	[watch_image] varchar(50) null, 
	[watch_description] varchar(max) null, 
	[watch_name] varchar(50) null, 
	[watch_create_date] timestamp null, 
	[state] tinyint null, 
	[price] float null, 
	[brand] varchar(20) null, 
	[series] varchar(20) null, 
	[model] varchar(20) null, 
	[gender] varchar(10) null, 
	[style_type] varchar(50) null, 
	[sub_class] varchar(50) null, 
	[made_label] varchar(50) null, 
	[calender] varchar(50) null, 
	[feature] varchar(50) null, 
	[movement] varchar(20) null, 
	[functions] varchar(50) null, 
	[engine] varchar(20) null, 
	[water_resistant] varchar(50) null, 
	[band_color] varchar(10) null, 
	[band_type] varchar(50) null, 
	[clasp] varchar(20) null,
	[bracelet] varchar(20) null, 
	[dial_type] varchar(20) null, 
	[dial_color] varchar(10) null, 
	[crystal] varchar(20) null, 
	[second_makers] varchar(20) null, 
	[bezel] varchar(20) null, 
	[bezel_material] varchar(20) null, 
	[case_back] varchar(20) null, 
	[case_dimension] varchar(20) null, 
	[case_shape] varchar(20) null,
	primary key ([watch_id])
)

create table ReportType(
	[report_type] tinyint,
	[report_title] varchar(20),
	primary key ([report_type])
)

insert ReportType(report_type, report_title) values(1, N'Product Report')
insert ReportType(report_type, report_title) values(2, N'Member Report')
insert ReportType(report_type, report_title) values(3, N'Website Feedback')
insert ReportType(report_type, report_title) values(4, N'Appraisal Report')
insert ReportType(report_type, report_title) values(5, N'Bug Report')
insert ReportType(report_type, report_title) values(6, N'Transaction Report')
insert ReportType(report_type, report_title) values(7, N'Issue Report')
insert ReportType(report_type, report_title) values(8, N'Issue Reply')

create table Report(
	[report_id] char(6) not null, 
	[member_id] char(6) null,
	[watch_id] char(6) null, 
	[report_content] varchar(max) null, 
	[report_date] datetime null, 
	[report_type] tinyint,
	primary key ([report_id]),
	foreign key (member_id) references Members([member_id]),
	foreign key (watch_id) references Watch([watch_id]),
	foreign key (report_type) references ReportType([report_type])
)

create table CustomerSupportAgents(
	[member_id] char(6), 
	[report_id] char(6)
	foreign key (member_id) references Members([member_id]),
	foreign key (report_id) references Report([report_id])
)

create table Admins (
	[member_id] char(6) null,
	[key_pass] varchar(15) null, 
	foreign key (member_id) references Members([member_id])
)

create table Sellers (
	[member_id] char(6) null,
	[watch_id] char(6) null,
	foreign key (member_id) references Members([member_id])
)

create table Appraisers (
	[member_id] char(6) null, 
	[watch_id] char(6) null, 
	foreign key (member_id) references Members([member_id])
)

insert Admins (member_id, key_pass) values (N'000000', N'Few231Poes@a')

drop table Members
drop table Admins
drop table Roles

select * from Members join Roles on Members.role_id = Roles.role_id
select * from Admins join Members on Members.member_id = Admins.member_id
select * from Members

delete Members where Members.member_id = N'000000'