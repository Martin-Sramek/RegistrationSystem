DROP TABLE IF EXISTS users;

CREATE TABLE users (
	id BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(255) NOT NULL,
	surname VARCHAR(255),
	person_id VARCHAR(255) NOT NULL,
	uuid VARCHAR(255) NOT NULL,
	UNIQUE (person_id),
	UNIQUE (uuid),
	PRIMARY KEY (id)
);
