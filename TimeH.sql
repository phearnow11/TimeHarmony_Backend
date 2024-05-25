create database TimeHarmony
drop database TimeHarmony

create table Roles (
	[role_id] tinyint not null, 
	[authority] varchar(50) null,
	primary key ([role_id])
)

insert Roles (role_id, [authority]) values (1, N'ROLE_USER')
insert Roles (role_id, [authority]) values (2, N'ROLE_ADMIN')

create table Users (
	[member_id] binary(16) not null, 
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
	[enabled] bit null,
	primary key ([member_id]),
	foreign key ([role_id]) references Roles([role_id])
)

create table Addresses (
	[address_id] char(6) not null, 
	[member_id] binary(16) not null, 
	[phone] varchar(20) null, 
	[address_detail] varchar(50) null, 
	[address_type] tinyint, 
	[is_default] tinyint 
	primary key ([address_id]), 
	foreign key ([member_id]) references Users([member_id])
)

select * from Addresses

create table Watch(
	[watch_id] char(6) not null, 
	[watch_image] varchar(50) null, 
	[watch_description] varchar(max) null, 
	[watch_name] varchar(50) null, 
	[watch_create_date] datetime, 
	[state] tinyint null, 
	[price] float null, 
	[brand] varchar(50) null, 
	[series] varchar(50) null, 
	[model] varchar(50) null, 
	[gender] varchar(20) null, 
	[style_type] varchar(50) null, 
	[sub_class] varchar(50) null, 
	[made_label] varchar(50) null, 
	[calender] varchar(50) null, 
	[feature] varchar(50) null, 
	[movement] varchar(50) null, 
	[functions] varchar(50) null, 
	[engine] varchar(50) null, 
	[water_resistant] varchar(50) null, 
	[band_color] varchar(50) null, 
	[band_type] varchar(50) null, 
	[clasp] varchar(50) null,
	[bracelet] varchar(50) null, 
	[dial_type] varchar(50) null, 
	[dial_color] varchar(50) null, 
	[crystal] varchar(50) null, 
	[second_makers] varchar(100) null, 
	[bezel] varchar(50) null, 
	[bezel_material] varchar(50) null, 
	[case_back] varchar(50) null, 
	[case_dimension] varchar(50) null, 
	[case_shape] varchar(50) null,
	primary key ([watch_id])
)

drop table Watch

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
	[member_id] binary(16) null,
	[watch_id] char(6) null, 
	[report_content] varchar(max) null, 
	[report_date] datetime null, 
	[report_type] tinyint,
	primary key ([report_id]),
	foreign key (member_id) references Users([member_id]),
	foreign key (watch_id) references Watch([watch_id]),
	foreign key (report_type) references ReportType([report_type])
)

create table Customer_Support_Agents(
	[member_id] binary(16), 
	[report_id] char(6)
	foreign key (member_id) references Users([member_id]),
	foreign key (report_id) references Report([report_id])
)

create table Admins (
	[member_id] binary(16) null,
	[key_pass] varchar(15) null, 
	foreign key (member_id) references Users([member_id])
)

create table Sellers (
	[member_id] binary(16) null,
	[watch_id] char(6) null,
	foreign key (member_id) references Users([member_id])
)

create table Appraisers (
	[member_id] binary(16) null, 
	[watch_id] char(6) null, 
	[year_experience] tinyint,
	foreign key (member_id) references Users([member_id])
)


alter table Watch drop column watch_create_date
alter table Watch add watch_create_date datetime

insert Watch (watch_id, watch_image, watch_description, watch_name, watch_create_date,[state],price, brand, series, model, gender, style_type, 
sub_class, made_label, calender, feature, movement, functions, engine, water_resistant, band_color, band_type, clasp, bracelet, dial_type, 
dial_color, crystal, second_makers, bezel, bezel_material, case_back, case_dimension, case_shape) values (N'W001',N'images', 'The beautiful Longines L38404966 watch features a 
stainless steel 44mm case, with a uni-directional rotating bezel, and a blue dial covered by a scratch resistant sapphire crystal. The stylish wristwatch is equipped with an 
exclusive 22mm stainless steel which combines comfort and sturdiness. This horological trendy device has date, hour, minute, second, e.o.l. indicator functions.', N'Longines L38404966 HydroConquest Men Quartz Watch',
CURRENT_TIMESTAMP, 1, 791.99,N'Longines', N'HydroConquest', N'L38404966', N'Men', N'Dive Watch', N'Watches', N'Swiss Made', N'Date display at the 3 o''clock position','Stainless Steel', 'Quartz', 'Date, Hour, Minute, Second, E.O.L. Indicator'
,'Longines Calibre L157', '300 meters / 1000 feet', 'Silver Tone', 'Bracelet', 'Fold Over with Safety Release', 'Stainless Steel', 'Analog', 'Blue', 'Scratch Resistant Sapphire','Arabic Numerals mark the 6 and 12 o''clock positions. Minute Markers around the outer rim'
,'Uni-directional Rotating Coin Edge', 'Stainless Steel', 'Solid', '44 mm', 'Round')

select * from Users
select * from Appraisers
select * from Admins
select * from Customer_Support_Agents
select * from Watch
select username, [authority] from Users join Roles on Users.role_id = Roles.role_id 
select username, [password],[enabled] from Users where Users.username = N'admin'

delete Watch
delete Users where Users.member_id = N'000002'
delete Admins