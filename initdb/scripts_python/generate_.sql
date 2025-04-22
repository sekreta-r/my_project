
        BEGIN TRANSACTION;

        CREATE TABLE t_courier (
            id BIGSERIAL PRIMARY KEY,
            full_name VARCHAR(255) NOT NULL,
            transport VARCHAR(255),
            work_zone VARCHAR(255)
        );

        CREATE TABLE t_parcel (
            id BIGSERIAL PRIMARY KEY,
            weight DOUBLE PRECISION NOT NULL,
            dimensions VARCHAR(255),
            destination_address VARCHAR(255)
        );

        CREATE TABLE t_delivery (
            id BIGSERIAL PRIMARY KEY,
            parcel_id BIGINT NOT NULL REFERENCES t_parcel(id) ON DELETE CASCADE,
            courier_id BIGINT NOT NULL REFERENCES t_courier(id) ON DELETE CASCADE,
            delivery_date DATE NOT NULL,
            status VARCHAR(50) NOT NULL CHECK (status IN ('PENDING', 'IN_TRANSIT', 'DELIVERED', 'CANCELLED'))
        );

        COMMIT;
        
BEGIN TRANSACTION;
INSERT INTO t_courier (full_name, transport, work_zone) VALUES ('Иван Иванов', 'Car', 'Moscow');
INSERT INTO t_courier (full_name, transport, work_zone) VALUES ('Петр Петров', 'Bike', 'Saint Petersburg');
INSERT INTO t_courier (full_name, transport, work_zone) VALUES ('Сергей Сергеев', 'Scooter', 'Kazan');
INSERT INTO t_courier (full_name, transport, work_zone) VALUES ('Андрей Андреев', 'Truck', 'Novosibirsk');
INSERT INTO t_courier (full_name, transport, work_zone) VALUES ('Алексей Алексеев', 'Foot', 'Yekaterinburg');
INSERT INTO t_parcel (weight, dimensions, destination_address) VALUES (5.24, '10x10x10', 'Address_1');
INSERT INTO t_parcel (weight, dimensions, destination_address) VALUES (39.96, '10x10x10', 'Address_2');
INSERT INTO t_parcel (weight, dimensions, destination_address) VALUES (39.64, '10x10x10', 'Address_3');
INSERT INTO t_parcel (weight, dimensions, destination_address) VALUES (9.44, '10x10x10', 'Address_4');
INSERT INTO t_parcel (weight, dimensions, destination_address) VALUES (45.60, '10x10x10', 'Address_5');
INSERT INTO t_parcel (weight, dimensions, destination_address) VALUES (19.88, '10x10x10', 'Address_6');
INSERT INTO t_parcel (weight, dimensions, destination_address) VALUES (14.94, '10x10x10', 'Address_7');
INSERT INTO t_parcel (weight, dimensions, destination_address) VALUES (21.97, '10x10x10', 'Address_8');
INSERT INTO t_parcel (weight, dimensions, destination_address) VALUES (28.07, '10x10x10', 'Address_9');
INSERT INTO t_parcel (weight, dimensions, destination_address) VALUES (23.71, '10x10x10', 'Address_10');
INSERT INTO t_delivery (parcel_id, courier_id, delivery_date, status) VALUES (5, 4, '2025-03-13', 'DELIVERED');
INSERT INTO t_delivery (parcel_id, courier_id, delivery_date, status) VALUES (3, 3, '2025-03-23', 'IN_TRANSIT');
INSERT INTO t_delivery (parcel_id, courier_id, delivery_date, status) VALUES (9, 4, '2025-03-24', 'DELIVERED');
INSERT INTO t_delivery (parcel_id, courier_id, delivery_date, status) VALUES (4, 3, '2025-03-24', 'CANCELLED');
INSERT INTO t_delivery (parcel_id, courier_id, delivery_date, status) VALUES (10, 2, '2025-04-03', 'PENDING');
INSERT INTO t_delivery (parcel_id, courier_id, delivery_date, status) VALUES (7, 2, '2025-03-23', 'CANCELLED');
INSERT INTO t_delivery (parcel_id, courier_id, delivery_date, status) VALUES (6, 5, '2025-03-17', 'CANCELLED');
INSERT INTO t_delivery (parcel_id, courier_id, delivery_date, status) VALUES (2, 3, '2025-04-07', 'IN_TRANSIT');
COMMIT;
