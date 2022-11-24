package com.roi.rentalcar.database.entities;

import com.roi.rentalcar.static_data.CarStatus;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;
    private String brand;
    private String bodyType;
    private Integer year;
    private String color;
    private Double mileage;
    private Double amount;
    @Enumerated(value = EnumType.STRING)
    private CarStatus carStatus;
    @OneToOne
    private UnavailableStatus status;
    @ManyToOne
    @JoinColumn(name = "reservation")
    private Reservation reservation;
    @ManyToOne
    @JoinColumn(name = "branch")
    private Branch branch;

    public Car(Long carId, String brand, String bodyType, Integer year, String color, Double mileage, Double amount,
               CarStatus carStatus, UnavailableStatus status, Reservation reservation, Branch branch) {
        this.carId = carId;
        this.brand = brand;
        this.bodyType = bodyType;
        this.year = year;
        this.color = color;
        this.mileage = mileage;
        this.amount = amount;
        this.carStatus = carStatus;
        this.status = status;
        this.reservation = reservation;
        this.branch = branch;
    }

    public Car(){}

    public static CarBuilder builder() {
        return new CarBuilder();
    }
    public static class CarBuilder {
        private Long carId;
        private String brand;
        private String bodyType;
        private Integer year;
        private String color;
        private Double mileage;
        private Double amount;
        private CarStatus carStatus;
        private UnavailableStatus status;
        private Reservation reservation;
        private Branch branch;

        CarBuilder() {
        }

        public CarBuilder carId(final Long carId) {
            this.carId = carId;
            return this;
        }

        public CarBuilder brand(final String brand) {
            this.brand = brand;
            return this;
        }

        public CarBuilder bodyType(final String bodyType) {
            this.bodyType = bodyType;
            return this;
        }

        public CarBuilder year(final Integer year) {
            this.year = year;
            return this;
        }

        public CarBuilder color(final String color) {
            this.color = color;
            return this;
        }

        public CarBuilder mileage(final Double mileage) {
            this.mileage = mileage;
            return this;
        }

        public CarBuilder amount(final Double amount) {
            this.amount = amount;
            return this;
        }

        public CarBuilder carStatus(final CarStatus carStatus) {
            this.carStatus = carStatus;
            return this;
        }

        public CarBuilder status(final UnavailableStatus status) {
            this.status = status;
            return this;
        }

        public CarBuilder reservation(final Reservation reservation) {
            this.reservation = reservation;
            return this;
        }

        public CarBuilder branch(final Branch branch) {
            this.branch = branch;
            return this;
        }

        public Car build() {
            return new Car(this.carId, this.brand, this.bodyType, this.year, this.color, this.mileage, this.amount, this.carStatus, this.status, this.reservation, this.branch);
        }

        public String toString() {
            return "Car.CarBuilder(carId=" + this.carId + ", brand=" + this.brand + ", bodyType=" + this.bodyType + ", year=" + this.year + ", color=" + this.color + ", mileage=" + this.mileage + ", amount=" + this.amount + ", carStatus=" + this.carStatus + ", status=" + this.status + ", reservation=" + this.reservation + ", branch=" + this.branch + ")";
        }
    }
}
