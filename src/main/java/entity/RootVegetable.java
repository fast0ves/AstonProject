package entity;

import java.util.Comparator;
import java.util.Objects;

/**
 * Класс, представляющий корнеплод.
 * Содержит информацию о типе, весе и цвете корнеплода.
 * Реализует паттерн Builder для создания объектов.
 */
public class RootVegetable {

    /** Тип корнеплода */
    private String type;

    /** Вес корнеплода (в кг) */
    private double weight;

    /** Цвет корнеплода */
    private String color;

    /** Компаратор для сортировки по типу */
    private static final Comparator<RootVegetable> BY_TYPE = Comparator.nullsFirst(
            Comparator.comparing(RootVegetable::getType));

    /** Компаратор для сортировки по весу */
    private static final Comparator<RootVegetable> BY_WEIGHT = Comparator.nullsFirst(
            Comparator.comparing(RootVegetable::getWeight));

    /** Компаратор для сортировки по цвету */
    private static final Comparator<RootVegetable> BY_COLOR = Comparator.nullsFirst(
            Comparator.comparing(RootVegetable::getColor));

    /** Компаратор для комплексной сортировки */
    private static final Comparator<RootVegetable> BY_ALL_FIELDS = BY_TYPE.thenComparing(BY_COLOR)
            .thenComparing(BY_WEIGHT);

    /**
     * Конструктор класса.
     * Использует паттерн Builder для инициализации объекта.
     *
     * @param rootVegetableBuilder объект построителя корнеплода
     */
    public RootVegetable(RootVegetableBuilder rootVegetableBuilder) {
        this.type = rootVegetableBuilder.type;
        this.weight = rootVegetableBuilder.weight;
        this.color = rootVegetableBuilder.color;
    }

    /**
     * Возвращает компаратор для сортировки корнеплодов.
     *
     * @return компаратор по всем полям
     */
    public static Comparator<RootVegetable> getComparator() {

        return BY_ALL_FIELDS;
    }

    public String getType() {
        return type;
    }

    public double getWeight() {
        return weight;
    }

    public String getColor() {
        return color;
    }


    @Override
    public String toString() {

        return "RootVegetable{" +
                "type='" + type + '\'' +
                ", weight=" + weight +
                ", color='" + color + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RootVegetable rootVegetable = (RootVegetable) o;

        return (Double.valueOf(weight).equals(rootVegetable.getWeight())
                && Objects.equals(type, rootVegetable.getType()) && Objects.equals(color, rootVegetable.getColor()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, weight, color);
    }

    /**
     * Вложенный класс-строитель для создания объектов RootVegetable.
     */
    public static class RootVegetableBuilder {
        private String type = "Корнеплод";
        private double weight = 1;
        private String color = "Бесцветный";

        public RootVegetableBuilder() {
        }

        /**
         * Устанавливает тип корнеплода.
         *
         * @param type тип корнеплода
         * @return текущий построитель
         */
        public RootVegetableBuilder setType(String type) {
            if (type != null && !type.trim().isEmpty()) {
                this.type = type;
            }

            return this;
        }

        /**
         * Устанавливает вес корнеплода.
         *
         * @param weight вес в кг
         * @return текущий построитель
         */
        public RootVegetableBuilder setWeight(double weight) {
            if (weight > 0) {
                this.weight = weight;
            }

            return this;
        }

        /**
         * Устанавливает цвет корнеплода.
         *
         * @param color цвет
         * @return текущий построитель
         */
        public RootVegetableBuilder setColor(String color) {
            if (color != null && !color.trim().isEmpty()) {
                this.color = color;
            }

            return this;
        }

        /**
         * Создает и возвращает готовый объект корнеплода.
         * Выполняет валидацию всех полей перед созданием объекта.
         *
         * @return готовый объект RootVegetable
         * @throws IllegalArgumentException если данные не прошли валидацию
         */
        public RootVegetable build() {
            if (type == null || type.trim().isEmpty()) {
                throw new IllegalArgumentException("Type cannot be null or empty");
            }
            if (weight <= 0) {
                throw new IllegalArgumentException("Weight must be positive");
            }
            if (color == null || color.trim().isEmpty()) {
                throw new IllegalArgumentException("Color cannot be null or empty");
            }

            return new RootVegetable(this);
        }
    }
}
