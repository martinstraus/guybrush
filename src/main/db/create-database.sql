create database guybrush;
create user guybrush with password 'guybrush';
grant all privileges on database guybrush to guybrush;
alter default privileges
    for user guybrush
    in schema public
    grant select, insert, update, delete on tables to guybrush;