import http from 'k6/http';
import { check } from 'k6';
import { randomItem } from 'https://jslib.k6.io/k6-utils/1.4.0/index.js';

export const options = {
    scenarios: {
        writers: {
            executor: 'constant-vus',
            vus: __ENV.VUS_WRITE ? parseInt(__ENV.VUS_WRITE) : 1,
            duration: '1m',
            exec: 'writeScenario',
        },
        readers: {
            executor: 'constant-vus',
            vus: __ENV.VUS_READ ? parseInt(__ENV.VUS_READ) : 10,
            duration: '1m',
            exec: 'readScenario',
        },
    },
};

export function setup() {
    const res = http.get('http://localhost:8080/deliveries');
    let deliveryIds = [];

    try {
        deliveryIds = res.json().map(d => d.id);
    } catch (err) {
        console.error('Ошибка разбора /deliveries:', err);
    }

    const couriers = http.get('http://localhost:8080/couriers').json();
    const parcels = http.get('http://localhost:8080/parcels').json();

    return {
        deliveryIds,
        courierIds: couriers.map(c => c.id),
        parcelIds: parcels.map(p => p.id),
    };
}

function generateDeliveryPayload(courierIds, parcelIds) {
    return JSON.stringify({
        courierId: randomItem(courierIds),
        parcelId: randomItem(parcelIds),
        deliveryDate: new Date(Date.now() - Math.random() * 2.628e+9).toISOString().split("T")[0],
        status: randomItem(["PENDING", "IN_TRANSIT", "DELIVERED", "CANCELLED"]),
    });
}

export function writeScenario(data) {
    const payload = generateDeliveryPayload(data.courierIds, data.parcelIds);
    const headers = { 'Content-Type': 'application/json' };
    const res = http.post('http://localhost:8080/deliveries', payload, { headers });
    check(res, {
        'created delivery': r => r.status === 200 || r.status === 201,
    });
}

export function readScenario(data) {
    const id = randomItem(data.deliveryIds);
    if (id) {
        const res = http.get(`http://localhost:8080/deliveries/${id}`);
        check(res, {
            'got delivery': r => r.status === 200,
        });
    }
}