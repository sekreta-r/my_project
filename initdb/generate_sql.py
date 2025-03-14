import uuid
import random
from datetime import datetime, timedelta

def generate_sql(filename, num_parcels=10, num_deliveries=8):
    delivery_statuses = ["PENDING", "IN_TRANSIT", "DELIVERED", "CANCELLED"]
    couriers = [
        ("Иван Иванов", "Car", "Moscow"),
        ("Петр Петров", "Bike", "Saint Petersburg"),
        ("Сергей Сергеев", "Scooter", "Kazan"),
        ("Андрей Андреев", "Truck", "Novosibirsk"),
        ("Алексей Алексеев", "Foot", "Yekaterinburg")
    ]

    with open(filename, 'w', encoding='utf-8') as f:

        f.write("""
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
        """)


        f.write("\nBEGIN TRANSACTION;\n")


        for full_name, transport, work_zone in couriers:
            f.write(
                f"INSERT INTO t_courier (full_name, transport, work_zone) VALUES ('{full_name}', '{transport}', '{work_zone}');\n"
            )


        for i in range(1, num_parcels + 1):
            f.write(
                f"INSERT INTO t_parcel (weight, dimensions, destination_address) VALUES ({random.uniform(1, 50):.2f}, '10x10x10', 'Address_{i}');\n"
            )


        for i in range(1, num_deliveries + 1):
            parcel_id = random.randint(1, num_parcels)
            courier_id = random.randint(1, len(couriers))
            delivery_date = (datetime.now() - timedelta(days=random.randint(1, 30))).date()
            status = random.choice(delivery_statuses)
            f.write(
                f"INSERT INTO t_delivery (parcel_id, courier_id, delivery_date, status) VALUES ({parcel_id}, {courier_id}, '{delivery_date}', '{status}');\n"
            )

        f.write("COMMIT;\n")


generate_sql("./scripts_python/generate_.sql")
