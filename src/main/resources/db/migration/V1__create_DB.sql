

CREATE TABLE ingredient (
	id 			BIGINT not null AUTO_INCREMENT PRIMARY KEY, 
	name 		VARCHAR(50), 
	price 		double,
	category 	ENUM('BASE', 'FILLING', 'TOPPING', 'FRUIT') not null,
	healthy		BOOLEAN		not null,
	constraint  UK_ingredient_name unique (name)
);


CREATE TABLE order_table (
	id					BIGINT not null AUTO_INCREMENT PRIMARY KEY,
    time				DATETIME not null,
    description			VARCHAR(500)
);


CREATE TABLE pancake (
	id 					BIGINT 	not null 	AUTO_INCREMENT PRIMARY KEY,
	basic_ingredient 	BIGINT	not null,
	FOREIGN KEY 		(basic_ingredient) references ingredient (id)
						on delete cascade
);


CREATE TABLE ingredients_list (
	pancake_id			BIGINT 	not null,
	ingredient_id		BIGINT 	not null,
	PRIMARY KEY 		(pancake_id, ingredient_id),
	FOREIGN KEY 		(pancake_id)  		references pancake (id) on delete cascade,
	FOREIGN KEY 		(ingredient_id)		references ingredient (id) on delete cascade
);



CREATE TABLE pancakes_list (
	order_id			BIGINT not null,
    pancake_id			BIGINT 	not null PRIMARY KEY,
	FOREIGN KEY 		(pancake_id)  references pancake (id) 
						on delete cascade,
	FOREIGN KEY 		(order_id) references order_table (id)
						on delete cascade
);


