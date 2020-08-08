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

create table daily_reminders (
    id serial,
    date date not null,
    message varchar(100) not null,
    constraint daily_reminders_pk primary key (id)
);

create table telegram_updates (
    update_id integer not null,
    reception_timestamp timestamp not null,
    update_payload jsonb not null,
    constraint telegram_updates_pk primary key (update_id)
);