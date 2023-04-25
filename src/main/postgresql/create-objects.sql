create table contacts (
  id smallint NOT NULL,
  name character varchar(100),
  birthday character varchar(5), -- format: dd/mm
  CONSTRAINT contacts_pkey PRIMARY KEY (id)
);

create table date_reminders (
    id serial,
    day_in_month smallint,
    date date,
    message varchar(100) not null,
    constraint date_reminders_pk primary key (id),
    constraint date_reminders_type_chk check (
        (day_in_month is null and date is not null)
        or (day_in_month is not null and date is null)
    )
);

create table telegram_updates (
    update_id integer not null,
    reception_timestamp timestamp not null,
    update_payload jsonb not null,
    constraint telegram_updates_pk primary key (update_id)
);