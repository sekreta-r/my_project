
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
INSERT INTO t_parcel (weight, dimensions, destination_address) VALUES (40.45, '10x10x10', 'Address_1');
INSERT INTO t_parcel (weight, dimensions, destination_address) VALUES (4.36, '10x10x10', 'Address_2');
INSERT INTO t_parcel (weight, dimensions, destination_address) VALUES (27.01, '10x10x10', 'Address_3');
INSERT INTO t_parcel (weight, dimensions, destination_address) VALUES (40.30, '10x10x10', 'Address_4');
INSERT INTO t_parcel (weight, dimensions, destination_address) VALUES (34.19, '10x10x10', 'Address_5');
INSERT INTO t_parcel (weight, dimensions, destination_address) VALUES (34.78, '10x10x10', 'Address_6');
INSERT INTO t_parcel (weight, dimensions, destination_address) VALUES (49.54, '10x10x10', 'Address_7');
INSERT INTO t_parcel (weight, dimensions, destination_address) VALUES (22.79, '10x10x10', 'Address_8');
INSERT INTO t_parcel (weight, dimensions, destination_address) VALUES (34.64, '10x10x10', 'Address_9');
INSERT INTO t_parcel (weight, dimensions, destination_address) VALUES (8.69, '10x10x10', 'Address_10');
INSERT INTO t_delivery (parcel_id, courier_id, delivery_date, status) VALUES (5, 2, '2025-03-11', 'DELIVERED');
INSERT INTO t_delivery (parcel_id, courier_id, delivery_date, status) VALUES (7, 1, '2025-03-08', 'PENDING');
INSERT INTO t_delivery (parcel_id, courier_id, delivery_date, status) VALUES (3, 2, '2025-03-02', 'DELIVERED');
INSERT INTO t_delivery (parcel_id, courier_id, delivery_date, status) VALUES (10, 4, '2025-03-12', 'DELIVERED');
INSERT INTO t_delivery (parcel_id, courier_id, delivery_date, status) VALUES (10, 4, '2025-02-27', 'DELIVERED');
INSERT INTO t_delivery (parcel_id, courier_id, delivery_date, status) VALUES (1, 4, '2025-03-07', 'CANCELLED');
INSERT INTO t_delivery (parcel_id, courier_id, delivery_date, status) VALUES (1, 2, '2025-02-24', 'DELIVERED');
INSERT INTO t_delivery (parcel_id, courier_id, delivery_date, status) VALUES (7, 5, '2025-02-26', 'CANCELLED');
COMMIT;
