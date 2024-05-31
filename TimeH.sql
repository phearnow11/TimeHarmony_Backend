create database TimeHarmony
drop database TimeHarmony


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
	[username] varchar(100) not null,
	[member_image] varchar(50) null,
	[first_name] varchar(50) null, 
	[last_name] varchar(50) null, 
	[is_active] tinyint null, 
	[address] varchar (50) null,
	[email] varchar (50) null,
	[phone] varchar(20) null,
	[last_login_date] datetime,
	[last_logout_date] datetime,
	[email_verification] varchar(6),
	primary key ([member_id]),
	foreign key (username) references Users(username)
)


create table Addresses (
	[address_id] char(6) not null, 
	[member_id] binary(16) not null, 
	[phone] varchar(20) null, 
	[address_detail] varchar(50) null, 
	[address_type] tinyint, 
	[is_default] tinyint 
	primary key ([address_id]), 
	foreign key ([member_id]) references Members([member_id])
)

select * from Addresses

create table Watch(
	[watch_id] char(6) not null, 
	[watch_image] varchar(max) null, 
	[watch_description] varchar(max) null, 
	[watch_name] varchar(100) null, 
	[watch_create_date] datetime, 
	[state] tinyint null, 
	[price] float null, 
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
	foreign key (member_id) references Members([member_id]),
	foreign key (watch_id) references Watch([watch_id]),
	foreign key (report_type) references ReportType([report_type])
)

create table Customer_Support_Agents(
	[member_id] binary(16), 
	[report_id] char(6)
	foreign key (member_id) references Members([member_id]),
	foreign key (report_id) references Report([report_id])
)

create table Admins (
	[member_id] binary(16) null,
	[key_pass] varchar(15) null, 
	foreign key (member_id) references Members([member_id])
)

create table Sellers (
	[member_id] binary(16) null,
	[watch_id] char(6) null,
	foreign key (member_id) references Members([member_id])
)

create table Appraisers (
	[member_id] binary(16) null, 
	[watch_id] char(6) null, 
	[year_experience] tinyint,
	foreign key (member_id) references Members([member_id])
)

create table Access_History(
	[member_id] binary(16) not null,
	[url] varchar(50) not null,
	[access_time] datetime not null
	foreign key (member_id) references Members([member_id])
)



insert Watch (watch_id, watch_image, watch_description, watch_name, watch_create_date,[state],price, brand, series, model, gender, style_type, 
sub_class, made_label, calender, feature, movement, functions, engine, water_resistant, band_color, band_type, clasp, bracelet, dial_type, 
dial_color, crystal, second_makers, bezel, bezel_material, case_back, case_dimension, case_shape) values (N'W001',N'images', 'The beautiful Longines L38404966 watch features a 
stainless steel 44mm case, with a uni-directional rotating bezel, and a blue dial covered by a scratch resistant sapphire crystal. The stylish wristwatch is equipped with an 
exclusive 22mm stainless steel which combines comfort and sturdiness. This horological trendy device has date, hour, minute, second, e.o.l. indicator functions.', N'Longines L38404966 HydroConquest Men Quartz Watch',
CURRENT_TIMESTAMP, 1, 791.99,N'Longines', N'HydroConquest', N'L38404966', N'Men', N'Dive Watch', N'Watches', N'Swiss Made', N'Date display at the 3 o''clock position','Stainless Steel', 'Quartz', 'Date, Hour, Minute, Second, E.O.L. Indicator'
,'Longines Calibre L157', '300 meters / 1000 feet', 'Silver Tone', 'Bracelet', 'Fold Over with Safety Release', 'Stainless Steel', 'Analog', 'Blue', 'Scratch Resistant Sapphire','Arabic Numerals mark the 6 and 12 o''clock positions. Minute Markers around the outer rim'
,'Uni-directional Rotating Coin Edge', 'Stainless Steel', 'Solid', '44 mm', 'Round')

insert Watch (watch_id, watch_image, watch_description, watch_name, watch_create_date,[state],price, brand, series, model, gender, style_type, 
sub_class, made_label, calender, feature, movement, functions, engine, water_resistant, band_color, band_type, clasp, bracelet, dial_type, 
dial_color, crystal, second_makers, bezel, bezel_material, case_back, case_dimension, case_shape) values (N'W002',N'images', 'The beautiful Hamilton H64455523 watch features a stainless steel 40mm case, with a fixed bezel, and a silvery beige dial covered by a scratch resistant sapphire crystal. The stylish wristwatch is equipped with an exclusive 20mm 
leather which combines comfort and sturdiness. This horological trendy device has date, day, GMT, second time zone, hour, minute, second functions.At the heart of this timepiece is Hamilton Calibre H-40. The Automatic movement operates at 21600 A/h and keeps the hands revolving smoothly and accurately for precise, reliable timekeeping. It contains 25 Jewels,
and picks up all the advantages of this high-performance ‘engine’, including an 80-hour power reserve.', N'Hamilton H64455523 Khaki Men''s Automatic Watch',CURRENT_TIMESTAMP, 1, 695.00,N'Hamilton', N'Khaki', N'H64455523', N'Men', N'Dress Watch', N'Watches', N'Swiss Made'
, 'Day of the week and date display at the 12 o''clock position','Stainless Steel', 'Automatic', ' Date, Day, Hour, Minute, Second, 24 Hours'
,'Hamilton Calibre H-40', ' 50 meters / 165 feet', 'Brown', 'Strap', 'Tang', 'Leather', 'Analog', 'Beige', 'Scratch Resistant Sapphire','Minute Markers around the outer rim, 24 Hour (GMT) scale around an inner ring'
,'Fixed', 'Stainless Steel', 'Fixed', '40 mm', 'Round')

insert Watch (watch_id, watch_image, watch_description, watch_name, watch_create_date,[state],price, brand, series, model, gender, style_type, 
sub_class, made_label, calender, feature, movement, functions, engine, water_resistant, band_color, band_type, clasp, bracelet, dial_type, 
dial_color, crystal, second_makers, bezel, bezel_material, case_back, case_dimension, case_shape) values (N'W003',N'images', 'Silver-tone Ion Plated Round Case Watch on Silver-tone Nougat Textured Bracelet Silver-tone 
alloy case and bracelet. Fixed silver-tone alloy bezel. White dial with silver-tone hands and diamond hour markers. Dial Type: Analog. Quartz movement. Pull / push crown. Solid case back. Round case shape, case size: 37 mm,
case thickness: 11 mm. Band width: 18 mm, band length: 6.75 inches. Buckle clasp. Water resistant at 30 meters / 100 feet. Functions: hour, minute, second. Womens Dress Series. Dress watch style. Akribos XXIV Womens Dress Quartz Diamond White Dial Ladies Watch P50147.'
, N'Akribos XXIV P50147 Womens Dress Ladies Quartz Watch',CURRENT_TIMESTAMP, 1, 345.00,N'Akribos XXIV', N'Womens Dress', N'H64455523', N'Men', N'Dress Watch', N'Watches', N'Swiss Made'
, 'Day of the week and date display at the 12 o''clock position','Stainless Steel', 'Automatic', ' Date, Day, Hour, Minute, Second, 24 Hours'
,'Hamilton Calibre H-40', ' 50 meters / 165 feet', 'Brown', 'Strap', 'Tang', 'Leather', 'Analog', 'Beige', 'Scratch Resistant Sapphire','Minute Markers around the outer rim, 24 Hour (GMT) scale around an inner ring'
,'Fixed', 'Stainless Steel', 'Fixed', '40 mm', 'Round')

select * from Users
select * from Members
select * from Authorities
select * from Appraisers
select * from Admins
select * from Customer_Support_Agents
select * from Watch
select username, [authority] from Users join Roles on Users.role_id = Roles.role_id 
select username, [password],[enabled] from Users where Users.username = N'admin'

delete Watch
delete Users where Users.member_id = N'000002'
delete Admins
delete Users where Users.username = N'phien'
drop table Users

alter table Watch drop column watch_create_date
alter table Watch add watch_create_date datetime
