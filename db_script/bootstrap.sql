--User
insert into `User` (id, email, password, active, role) values(1, 'a@a.com', '123', true, 'ADMIN');
insert into `User` (id, email, password, active, role) values(2, 's@a.com', '123', true, 'STAFF');
insert into `User` (id, email, password, active, role) values(3, 'm@a.com', '123', true, 'MANAGER');
insert into `User` (id, email, password, active, role) values(4, 'c@a.com', '123', true, 'CLIENT');

--Urgency
INSERT INTO Urgency(id, description) values(1, 'LOW');
INSERT INTO Urgency(id, description) values(2, 'MEDIUM');
INSERT INTO Urgency(id, description) values(3, 'HIGH');

--Impact
INSERT INTO Impact(id, description) values(1, 'LOW');
INSERT INTO Impact(id, description) values(2, 'MEDIUM');
INSERT INTO Impact(id, description) values(3, 'HIGH');

--Priority
--priori 5
INSERT INTO Priority(id, description, duration, response, urgency_id, impact_id) values(1, 'Low x Low', 172800, 86400, 1, 1);
--priori 4
INSERT INTO Priority(id, description, duration, response, urgency_id, impact_id) values(2, 'Low x Medium', 129600, 10800, 1, 2);
INSERT INTO Priority(id, description, duration, response, urgency_id, impact_id) values(3, 'Medium x Low', 129600, 10800, 2, 1);
--priori 3
INSERT INTO Priority(id, description, duration, response, urgency_id, impact_id) values(4, 'Low x High', 86400, 7200, 1, 3);
INSERT INTO Priority(id, description, duration, response, urgency_id, impact_id) values(5, 'High x Low', 86400, 7200, 3, 1);
INSERT INTO Priority(id, description, duration, response, urgency_id, impact_id) values(6, 'Medium x Medium', 86400, 7200, 2, 2);
--priori 2
INSERT INTO Priority(id, description, duration, response, urgency_id, impact_id) values(7, 'Medium x High', 7200, 1200, 2, 3);
INSERT INTO Priority(id, description, duration, response, urgency_id, impact_id) values(8, 'High x Medium', 7200, 1200, 3, 2);
--priori 1
INSERT INTO Priority(id, description, duration, response, urgency_id, impact_id) values(9, 'High x High', 3600, 600, 3, 3);

--Category
INSERT INTO Category(id, name) values(1, 'Hardware');
INSERT INTO Category(id, name) values(2, 'Software');
INSERT INTO Category(id, name, parent_id) values(3, 'Hardware1', 1);
INSERT INTO Category(id, name, parent_id) values(4, 'Hardware2', 1);
INSERT INTO Category(id, name, parent_id) values(5, 'Software1', 2);
INSERT INTO Category(id, name, parent_id) values(6, 'Software2', 2);
