insert into users(id, join_date, name, password, ssn) values (70001, now(), 'User1', 'pass1', '900101');
insert into users(id, join_date, name, password, ssn) values (70002, now(), 'User2', 'pass2', '900301');
insert into users(id, join_date, name, password, ssn) values (70003, now(), 'User3', 'pass3', '900701');

insert into post(description, user_id) values ('First Data', 70001);
insert into post(description, user_id) values ('Second Data', 70001);
insert into post(description, user_id) values ('Third Data', 70001);