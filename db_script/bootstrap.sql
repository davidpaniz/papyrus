--User
insert into `User` (email, password, active, role) values( 'a@a.com', '123', true, 'ADMIN');
insert into `User` (email, password, active, role) values( 's@a.com', '123', true, 'STAFF');
insert into `User` (email, password, active, role) values( 'm@a.com', '123', true, 'MANAGER');
insert into `User` (email, password, active, role) values( 'c@a.com', '123', true, 'CLIENT');

--Urgency
INSERT INTO Urgency(description) values('LOW')
INSERT INTO Urgency(description) values('MEDIUM')
INSERT INTO Urgency(description) values('HIGH')

--Impact
INSERT INTO Impact(description) values('LOW')
INSERT INTO Impact(description) values('MEDIUM')
INSERT INTO Impact(description) values('HIGH')

--Priority
--INSERT INTO Impact(description) values('MEDIUM')
