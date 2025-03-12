
package ru.hpclab.hl.module1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.hpclab.hl.module1.controller.ParcelController;
import ru.hpclab.hl.module1.controller.CourierController;
import ru.hpclab.hl.module1.controller.DeliveryController;
import ru.hpclab.hl.module1.model.Courier;
import ru.hpclab.hl.module1.model.Delivery;
import ru.hpclab.hl.module1.model.Parcel;

import java.time.LocalDate;

@EnableScheduling
@EnableAsync
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(ParcelController parcelController,
                                  CourierController courierController,
                                  DeliveryController deliveryController) {
        return args -> {
            Parcel parcel = new Parcel(1L, 5.5, "30x20x10", "123 Main St");
            Courier courier = new Courier(1L, "John Doe", "Bike", "Central Zone");
            Delivery delivery = new Delivery(1L, parcel, courier, LocalDate.now(), "Delivered");

            parcelController.addParcel(parcel);
            courierController.addCourier(courier);
            deliveryController.addDelivery(delivery);

            System.out.println("Delivery successfully recorded!");
        };
    }
}



