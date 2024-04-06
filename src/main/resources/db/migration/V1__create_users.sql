create table if not exists users (
	id bigserial not null,
	name varchar(50) not null,
	email varchar(50) not null unique,
	primary key (id)
);