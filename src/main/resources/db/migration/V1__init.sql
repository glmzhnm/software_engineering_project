CREATE TABLE nations (
                         id BIGSERIAL PRIMARY KEY,
                         name VARCHAR(255),
                         description VARCHAR(255)
);
CREATE TABLE countries (
                           id BIGSERIAL PRIMARY KEY,
                           name VARCHAR(255),
                           continent VARCHAR(255),
                           population INT
);
CREATE TABLE cities (
                        id BIGSERIAL PRIMARY KEY,
                        name VARCHAR(255),
                        population INT,
                        country_id BIGINT,
                        CONSTRAINT fk_cities_country FOREIGN KEY (country_id) REFERENCES countries (id)
);
CREATE TABLE city_nations (
                              city_id BIGINT NOT NULL,
                              nation_id BIGINT NOT NULL,
                              PRIMARY KEY (city_id, nation_id),
                              CONSTRAINT fk_city_nations_city FOREIGN KEY (city_id) REFERENCES cities (id),
                              CONSTRAINT fk_city_nations_nation FOREIGN KEY (nation_id) REFERENCES nations (id)
);
CREATE TABLE presidents (
                            id BIGSERIAL PRIMARY KEY,
                            name VARCHAR(255),
                            political_party VARCHAR(255),
                            term_start_year INT,
                            country_id BIGINT UNIQUE, -- One-to-One relationship
                            CONSTRAINT fk_presidents_country FOREIGN KEY (country_id) REFERENCES countries(id)
);
CREATE TABLE attractions (
                             id BIGSERIAL PRIMARY KEY,
                             name VARCHAR(255),
                             type VARCHAR(255),
                             ticket_price DOUBLE PRECISION,
                             city_id BIGINT,
                             CONSTRAINT fk_attractions_city FOREIGN KEY (city_id) REFERENCES cities(id)
);
