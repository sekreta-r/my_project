import argparse
import random
from faker import Faker
import requests
from datetime import datetime, timedelta

fake = Faker()
BASE_URL = "http://localhost:8080"

def clear_endpoint(endpoint):
    print(f"[INFO] Clearing {endpoint}")
    res = requests.post(f"{BASE_URL}/{endpoint}/clear")
    if not res.ok:
        print(f"[ERROR] Failed to clear {endpoint}: {res.status_code} {res.text}")

def generate_courier():
    return {
        "fullName": fake.name(),
        "transport": random.choice(["bike", "car", "scooter", "foot"]),
        "workZone": fake.city()
    }

def generate_parcel():
    return {
        "weight": round(random.uniform(0.5, 50.0), 2),
        "dimensions": f"{random.randint(10,100)}x{random.randint(10,100)}x{random.randint(10,100)}",
        "destinationAddress": fake.address()
    }

def generate_delivery(courier_id, parcel_id):
    return {
        "courierId": courier_id,
        "parcelId": parcel_id,
        "deliveryDate": (datetime.now() - timedelta(days=random.randint(0, 30))).strftime("%Y-%m-%d"),
        "status": random.choice(["PENDING", "IN_TRANSIT", "DELIVERED", "CANCELLED"])
    }

def populate(endpoint, count):
    if endpoint == "couriers":
        clear_endpoint("couriers")
        for _ in range(count):
            data = generate_courier()
            res = requests.post(f"{BASE_URL}/couriers", json=data)
            if not res.ok:
                print(f"[ERROR] Failed to create courier: {res.status_code} {res.text}")

    elif endpoint == "parcels":
        clear_endpoint("parcels")
        for _ in range(count):
            data = generate_parcel()
            res = requests.post(f"{BASE_URL}/parcels", json=data)
            if not res.ok:
                print(f"[ERROR] Failed to create parcel: {res.status_code} {res.text}")

    elif endpoint == "deliveries":
        clear_endpoint("deliveries")
        clear_endpoint("couriers")
        clear_endpoint("parcels")

        courier_ids = []
        parcel_ids = []

        for _ in range(count):
            c = requests.post(f"{BASE_URL}/couriers", json=generate_courier())
            if c.ok:
                courier_ids.append(c.json()["id"])

            p = requests.post(f"{BASE_URL}/parcels", json=generate_parcel())
            if p.ok:
                parcel_ids.append(p.json()["id"])

        for _ in range(count):
            data = generate_delivery(random.choice(courier_ids), random.choice(parcel_ids))
            res = requests.post(f"{BASE_URL}/deliveries", json=data)
            if not res.ok:
                print(f"[ERROR] Failed to create delivery: {res.status_code} {res.text}")

    else:
        print(f"[ERROR] Unknown endpoint: {endpoint}")

def main():
    parser = argparse.ArgumentParser(description="Data generator for couriers/parcels/deliveries")
    parser.add_argument("--count", type=int, default=500, help="Number of objects to create")
    parser.add_argument("--endpoint", type=str, required=True,
                        choices=["couriers", "parcels", "deliveries"],
                        help="API endpoint to populate")
    args = parser.parse_args()

    populate(args.endpoint, args.count)

if __name__ == "__main__":
    main()
