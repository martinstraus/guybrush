create table contacts (
  id smallint NOT NULL,
  name character varchar(100),
  birthday character varchar(5),
  CONSTRAINT contacts_pkey PRIMARY KEY (id)
);

create table monthly_reminders (
    id serial,
    day_in_month smallint not null,
    message varchar(100) not null,
    constraint monthly_reminders_pk primary key (id)
);