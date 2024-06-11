create database TimeHarmony
drop database TimeHarmony

use TimeHarmony

create table Users (
	username varchar(100) primary key,
	[password] varchar(100), 
	[enabled] tinyint,
)

create table Authorities (
	username varchar(100),
	authority varchar(100),
	foreign key (username) references Users(username)
)

create table Members (
	[member_id] binary(16) not null,
	[google_id] varchar(100) null,
	[username] varchar(100) null,
	[member_image] varchar(50) null,
	[first_name] varchar(50) null, 
	[last_name] varchar(50) null, 
	[is_active] tinyint null, 
	[email] varchar (50) null,
	[phone] varchar(20) null,
	[last_login_date] datetime,
	[last_logout_date] datetime,
	primary key ([member_id]),
	foreign key (username) references Users(username)
)


create table Addresses (
	[address_id] char(12) not null,
	[member_id] binary(16) not null, 
	[name] varchar(50) null,
	[phone] varchar(20) null, 
	[address_detail] varchar(50) null, 
	[is_default] tinyint,
	primary key (address_id),
	foreign key ([member_id]) references Members([member_id])
)

create table Watch(
	[watch_id] char(12) not null, 
	[member_id] binary(16) not null,
	[watch_description] varchar(max) null, 
	[watch_name] varchar(100) null, 
	[watch_create_date] datetime,
	[watch_approval_date] datetime,
	[state] tinyint null, 
	[price] bigint null, 
	[brand] varchar(100) null, 
	[series] varchar(100) null, 
	[model] varchar(100) null, 
	[gender] varchar(100) null, 
	[style_type] varchar(100) null, 
	[sub_class] varchar(100) null, 
	[made_label] varchar(100) null, 
	[calender] varchar(100) null, 
	[feature] varchar(100) null, 
	[movement] varchar(100) null, 
	[functions] varchar(100) null, 
	[engine] varchar(100) null, 
	[water_resistant] varchar(100) null, 
	[band_color] varchar(100) null, 
	[band_type] varchar(100) null, 
	[clasp] varchar(100) null,
	[bracelet] varchar(100) null, 
	[dial_type] varchar(100) null, 
	[dial_color] varchar(100) null, 
	[crystal] varchar(100) null, 
	[second_makers] varchar(100) null, 
	[bezel] varchar(100) null, 
	[bezel_material] varchar(100) null, 
	[case_back] varchar(100) null, 
	[case_dimension] varchar(100) null, 
	[case_shape] varchar(100) null,
	primary key ([watch_id])
)

create table Watch_images(
	image_url varchar(max) null, 
	watch_id char(12) not null,
	foreign key (watch_id) references Watch(watch_id) 
)

create table Staff(
	[member_id] binary(16), 
	primary key (member_id),
	foreign key (member_id) references Members([member_id])
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
	created_by binary(16) null,
	replied_by binary(16) null,
	[watch_id] char(12) null, 
	[report_content] varchar(max) null, 
	[report_date] datetime null, 
	[report_type] tinyint,
	primary key ([report_id]),
	foreign key (created_by) references Members([member_id]),
	foreign key (watch_id) references Watch([watch_id]),
	foreign key (replied_by) references Staff([member_id]),
	foreign key (report_type) references ReportType([report_type])
)

create table Admins (
	[member_id] binary(16) not null,
	[key_pass] varchar(15) null, 
	primary key (member_id),
	foreign key (member_id) references Members([member_id])
)

create table Sellers (
	[member_id] binary(16) not null,
	primary key (member_id),
	foreign key (member_id) references Members([member_id])
)

create table Access_History(
	[member_id] binary(16) not null,
	[url] varchar(50) not null,
	[access_time] datetime not null
	foreign key (member_id) references Members([member_id])
)

create table Vouchers(
	[voucher_id] char(12) not null,		
	[name] varchar(200) null,
	[description] varchar(max) null, 
	[value] int,
	[value_percentage] float, 
	[available] tinyint,
	primary key (voucher_id)
)

create table Orders(
	[order_id] char(12) not null,
	[member_id] binary(16) not null,
	[create_time] datetime null,
	[address] varchar(50) null,
	[receive_name] varchar(50) null,
	[phone] varchar(50) null,
	[notice] varchar(500) null,
	[total_price] bigint,
	primary key (order_id),
)

create table Voucher_Applied (
	voucher_id char(12) not null,
	order_id char(12) not null,
	foreign key (voucher_id) references Vouchers(voucher_id),
	foreign key (order_id) references Orders(order_id)
)

create table Cart(
	[cart_id] char(10) not null, 
	[watch_id] char(12) not null, 
	[member_id] binary(16) not null,
	[order_id] char(12) null,
	[add_date] datetime null, 
	primary key ([cart_id]), 
	foreign key (member_id) references Members(member_id),
	foreign key (watch_id) references Watch(watch_id)
)


select * from Users
select * from Members
select * from Authorities
select * from Admins
select * from Watch
select * from Sellers
select * from Addresses
select * from Watch_images
select * from Cart
select username, [authority] from Users join Roles on Users.role_id = Roles.role_id 
select username, [password],[enabled] from Users where Users.username = N'admin'

update Users set [enabled] = 0 where username = N'phienn1'

delete Watch
delete Users where Users.member_id = N'000002'
delete Admins
delete Users where Users.username = N'phien'
drop table Users

alter table Watch drop column watch_create_date
alter table Watch add watch_create_date datetime
