package entity;

import java.util.Comparator;
import java.util.Objects;

public class RootVegetable {
    private String type;
    private double weight;
    private String color;

    private static final Comparator<RootVegetable> BY_TYPE = Comparator.nullsFirst(
            Comparator.comparing(RootVegetable::getType));
    private static final Comparator<RootVegetable> BY_WEIGHT = Comparator.nullsFirst(
            Comparator.comparing(RootVegetable::getWeight));
    private static final Comparator<RootVegetable> BY_COLOR = Comparator.nullsFirst(
            Comparator.comparing(RootVegetable::getColor));
    private static final Comparator<RootVegetable> BY_ALL_FIELDS = BY_TYPE.thenComparing(BY_COLOR)
            .thenComparing(BY_WEIGHT);

    public RootVegetable(RootVegetableBuilder rootVegetableBuilder) {
        this.type = rootVegetableBuilder.type;
        this.weight = rootVegetableBuilder.weight;
        this.color = rootVegetableBuilder.color;
    }

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

    public static class RootVegetableBuilder {
        private String type = "Корнеплод";
        private double weight = 1;
        private String color = "Бесцветный";

        public RootVegetableBuilder() {
        }

        public RootVegetableBuilder setType(String type) {
            if (type != null && !type.trim().isEmpty()) {
                this.type = type;
            }
            return this;
        }

        public RootVegetableBuilder setWeight(double weight) {
            if (weight > 0) {
                this.weight = weight;
            }
            return this;
        }

        public RootVegetableBuilder setColor(String color) {
            if (color != null && !color.trim().isEmpty()) {
                this.color = color;
            }
            return this;
        }

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
