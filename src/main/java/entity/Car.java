package entity;

import java.util.Comparator;
import java.util.Objects;

/**
 * Класс, представляющий автомобиль.
 * Содержит информацию о мощности, годе выпуска и модели.
 * Реализует паттерн Builder для создания объектов.
 */
public class Car {

    /** Мощность автомобиля (л.с.) */
    private double power;

    /** Год выпуска автомобиля */
    private int yearOfProduction;

    /** Модель автомобиля */
    private String model;

    /** Компаратор для сортировки по мощности */
    private static final Comparator<Car> BY_POWER = Comparator.nullsFirst(Comparator.comparing(Car::getPower));

    /** Компаратор для сортировки по году выпуска */
    private static final Comparator<Car> BY_YEAR = Comparator.nullsFirst(Comparator.comparing(Car::getYearOfProduction));

    /** Компаратор для сортировки по модели */
    private static final Comparator<Car> BY_MODEL = Comparator.nullsFirst(Comparator.comparing(Car::getModel));

    /** Компаратор для комплексной сортировки */
    private static final Comparator<Car> BY_ALL_FIELDS = BY_MODEL.thenComparing(BY_YEAR).thenComparing(BY_POWER);

    /**
     * Конструктор класса.
     * Использует паттерн Builder для инициализации объекта.
     *
     * @param carBuilder объект построителя автомобиля
     */
    public Car(CarBuilder carBuilder) {
        this.power = carBuilder.power;
        this.yearOfProduction = carBuilder.yearOfProduction;
        this.model = carBuilder.model;
    }

    /**
     * Возвращает компаратор для сортировки автомобилей.
     *
     * @return компаратор по всем полям
     */
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

        return (Objects.equals(model, car.getModel())
                && yearOfProduction == car.getYearOfProduction()
                && Double.valueOf(power).equals(car.getPower()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(power, yearOfProduction, model);
    }

    /**
     * Вложенный класс-строитель для создания объектов Car.
     */
    public static class CarBuilder {
        private double power = 81.6;
        private int yearOfProduction = 2013;
        private String model = "Lada Samara-2";

        public CarBuilder() {
        }

        /**
         * Устанавливает мощность автомобиля.
         *
         * @param power значение мощности
         * @return текущий построитель
         */
        public CarBuilder setPower(double power) {
            if (power > 0) {
                this.power = power;
            }

            return this;
        }

        /**
         * Устанавливает год выпуска.
         *
         * @param yearOfProduction год выпуска
         * @return текущий построитель
         */
        public CarBuilder setYearOfProduction(int yearOfProduction) {
            if (yearOfProduction > 0) {
                this.yearOfProduction = yearOfProduction;
            }

            return this;
        }

        /**
         * Устанавливает модель автомобиля.
         *
         * @param model название модели
         * @return текущий построитель
         */
        public CarBuilder setModel(String model) {
            if (model != null && !model.trim().isEmpty()) {
                this.model = model;
            }

            return this;
        }

        /**
         * Создает и возвращает готовый объект автомобиля.
         * Выполняет валидацию всех полей перед созданием объекта.
         *
         * @return готовый объект Car
         * @throws IllegalArgumentException если данные не прошли валидацию
         */
        public Car build() {
            if (model == null || model.trim().isEmpty()) {
                throw new IllegalArgumentException("Model cannot be null or empty");
            }
            if (power <= 0) {
                throw new IllegalArgumentException("Power must be positive");
            }
            if (yearOfProduction <= 0) {
                throw new IllegalArgumentException("Year of production must be positive");
            }

            return new Car(this);
        }

    }
}
