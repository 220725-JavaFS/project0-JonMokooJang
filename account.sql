drop table if exists account;

--This indicates account information

create table account (
	account_Number SERIAL primary key, 
	account_User varchar(30) not null,
	account_Pass varchar(20) not null,
	account_First_Name varchar(20),
	account_Last_Name varchar(30),
	account_Job varchar(20),
	account_balance int not null
);
	
insert into account (account_user , account_pass , account_first_name, account_last_name, account_job, account_balance) values
	('test', 'test', 'Joseph', 'Giovanna', 'Researcher', 5000), -- SAMPLE #1
	('testtwo', 'testtwo', 'Jonathan', 'Joestar', 'Historian', 10000); -- SAMPLE #2
	

SELECT * FROM account where account_user='test'



