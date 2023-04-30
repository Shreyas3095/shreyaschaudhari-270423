CREATE TABLE customer (
	id BIGINT not null,
	first_name varchar(100) not null,
	last_name varchar(100) not null,
	PRIMARY KEY (id)
);
CREATE SEQUENCE customer_seq START WITH 5 INCREMENT BY 1;


CREATE TABLE mobilenumber (
	id BIGINT not null AUTO_INCREMENT PRIMARY KEY,
	mobile_number varchar(100) not null UNIQUE,
	customer_id BIGINT not null,
	FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE CASCADE
);

CREATE SEQUENCE mobilenumber_seq START WITH 14 INCREMENT BY 1;