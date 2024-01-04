DROP DATABASE IF EXISTS AnimalClinic;

CREATE DATABASE IF NOT EXISTS AnimalClinic;


use AnimalClinic;

create table user(
	user_id varchar(25) primary key,
	password varchar(25) NOT NULL,
	name varchar(25) NOT NULL,
	address varchar(25) NOT NULL,
	e_mail varchar(50) NOT NULL,
    image_data LONGBLOB

);

create table customer(
        cus_id varchar(25) primary key,
      	name varchar(25) NOT NULL,
      	tel varchar(25)  NULL,
      	e_mail varchar(100) NOT NULL,
      	address varchar(20) NOT NULL



);
create table pet(
	pet_id varchar(25) primary key,
	name varchar(25) NOT NULL,
	age varchar(10) NOT NULL,
	pet_type varchar(20) NOT NULL,
	cus_id varchar(25) NOT NULL,

	foreign key(cus_id) references customer(cus_id) on update cascade on delete cascade

);



create table doctor(
    doctor_id varchar(25) primary key,
	doctor_name varchar(25) NOT NULL,
	tel varchar(15) NULL,
	address varchar(25) NOT NULL,
	e_mail varchar(25)NOT NULL,
	come_in_time Time NOT NULL,
	com_out_time Time NOT NULL

 );

create table appointements(
    app_id varchar(25) primary key,
    doctor_id varchar(25) NOT NULL,
    pet_id varchar(25),
    date Date NOT NULL ,
	time Time NOT NULL,
	Number int(25) NOT NULL,
	foreign key(pet_id) references pet(pet_id) on delete cascade on update cascade,
	foreign key(doctor_id) references doctor(doctor_id) on update cascade on delete cascade
);

create table payment (
	p_id varchar(25) PRIMARY KEY,
	app_id varchar(25) NOT NULL,
	amount DOUBLE (20,2) NOT NULL,
	foreign key(app_id) references appointements(app_id) on delete cascade on update cascade
);

create table vaccinations(
    vaccination_id varchar(25) primary key,
	vaccine_name varchar(25) NOT NULL,
	next_dueDate Date NOT NULL

);

create table treatement(
	treatement_id varchar(25) primary key,
	vaccination_id varchar(25) NULL,
	type varchar(50) NOT NULL,
	cost DOUBLE (20,2),
	date Date NOT NULL,
	foreign key(vaccination_id) references vaccinations(vaccination_id) on delete cascade on update cascade
);

create table  treatementDetails(
    app_id varchar(25),
    treatement_id varchar(25),
    cost DOUBLE (20,2),
    type varchar(50),
    total DOUBLE (20,2),
    foreign key(app_id) references appointements(app_id) on delete cascade on update cascade,
    foreign key(treatement_id) references treatement(treatement_id) on delete cascade on update cascade

);

create table stock(
	stock_id varchar(25) primary key,
	category varchar(25) NOT NULL,
	date Date NOT NULL
);
create table  medicineDetails(
	treatement_id varchar(25),
	stock_id varchar(25),
	PRIMARY KEY (stock_id, treatement_id),
	foreign key(stock_id) references stock(stock_id) on delete cascade on update cascade,
	foreign key(treatement_id) references treatement(treatement_id) on delete cascade on update cascade

);

create table supliyer(
    supliyer_id varchar(25) primary key,
    stock_id varchar(25) NOT NULL,
	supliyer_name varchar(25) NOT NULL,
	 tel varchar(15) NULL,
	e_mail varchar(25) NOT NULL,
	address varchar(25) NOT NULL,
	foreign key(stock_id) references stock(stock_id) on update cascade on delete cascade
);

create table employee(
    emp_id varchar(25) primary key,
	emp_name varchar(25) NOT NULL,
	tel varchar(15) NULL,
	e_mail varchar(25) NOT NULL,
	address varchar(25) NOT NULL
	);

create table attendance (
	a_id varchar(25) PRIMARY KEY,
	emp_id varchar(10) NOT NULL,
	date Date NOT NULL,
	time Time NOT NULL,
	foreign key(emp_id) references employee(emp_id) on delete cascade on update cascade
	);

create table salary (
	salary_id varchar(25) PRIMARY KEY,
	emp_id varchar(25) NOT NULL,
	date Date NOT NULL,
    salary_month varchar(10) NOT NULL,
    amount DOUBLE (20,2) NOT NULL,
	foreign key(emp_id) references employee(emp_id) on delete cascade on update cascade
	);
