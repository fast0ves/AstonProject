/*
created by Ivanov Nikita
*/
package entity;

import java.util.Comparator;
import java.util.Objects;

public class Car {
    private double power;
    private int yearOfProduction;
    private String model;

    private static final Comparator<Car> BY_POWER = Comparator.nullsFirst(Comparator.comparing(Car::getPower));
    private static final Comparator<Car> BY_YEAR = Comparator.nullsFirst(Comparator.comparing(Car::getYearOfProduction));
    private static final Comparator<Car> BY_MODEL = Comparator.nullsFirst(Comparator.comparing(Car::getModel));
    private static final Comparator<Car> BY_ALL_FIELDS = BY_MODEL.thenComparing(BY_YEAR).thenComparing(BY_POWER);

    public Car(CarBuilder carBuilder) {
        this.power = carBuilder.power;
        this.yearOfProduction = carBuilder.yearOfProduction;
        this.model = carBuilder.model;
    }

    public static Comparator<Car> getComparator(){
        return BY_ALL_FIELDS;
    }

    public double getPower() {
        return power;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public String getModel() {
        return model;
    }

    @Override
    public String toString() {
        return "Car{" +
                "power=" + power +
                ", yearOfProduction=" + yearOfProduction +
                ", model='" + model + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return (Objects.equals(model, car.getModel()) && yearOfProduction == car.getYearOfProduction() &&
                Double.valueOf(power).equals(car.getPower()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(power, yearOfProduction, model);
    }

    public static class CarBuilder {
        private double power = 81.6;
        private int yearOfProduction = 2013;
        private String model = "Lada Samara-2";

        public CarBuilder() {
        }

        public CarBuilder setPower(double power) {
            if (power > 0) {
                this.power = power;
            }
            return this;
        }

        public CarBuilder setYearOfProduction(int yearOfProduction) {
            if (yearOfProduction > 0) {
                this.yearOfProduction = yearOfProduction;
            }
            return this;
        }

        public CarBuilder setModel(String model) {
            if (model != null && !model.trim().isEmpty()) {
                this.model = model;
            }
            return this;
        }

        public Car build() {
            return new Car(this);
        }

    }
}
