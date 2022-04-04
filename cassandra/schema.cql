CREATE KEYSPACE IF NOT EXISTS product WITH durable_writes = true AND REPLICATION = {'class': 'SimpleStrategy', 'replication_factor': '1'};
CREATE TYPE IF NOT EXISTS product.price(value double,currency_code text);
CREATE TABLE IF NOT EXISTS product.pricing(product_id text, current_price price,last_updated_date timestamp,PRIMARY KEY (product_id));

INSERT INTO product.pricing(product_id,current_price,last_updated_date) VALUES ('13860428',{value:13.49,currency_code:'USD'}, toTimestamp(now()));
INSERT INTO product.pricing(product_id,current_price,last_updated_date) VALUES ('54456119',{value:20.19,currency_code:'USD'}, toTimestamp(now()));
INSERT INTO product.pricing(product_id,current_price,last_updated_date) VALUES ('13264003',{value:100.09,currency_code:'USD'}, toTimestamp(now()));
INSERT INTO product.pricing(product_id,current_price,last_updated_date) VALUES ('12954218',{value:30.49,currency_code:'USD'}, toTimestamp(now()));