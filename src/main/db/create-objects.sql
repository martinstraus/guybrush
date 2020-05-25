create table contacts (
  id smallint NOT NULL,
  name character varying(100),
  birthday character varying(5),
  CONSTRAINT contacts_pkey PRIMARY KEY (id)
);