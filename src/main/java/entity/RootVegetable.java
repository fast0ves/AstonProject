/*
created by Ivanov Nikita
*/

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

    public RootVegetable(RootVegetableBuilder rootVegetableBuilder) {
        this.type = rootVegetableBuilder.type;
        this.weight = rootVegetableBuilder.weight;
        this.color = rootVegetableBuilder.color;
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
            return new RootVegetable(this);
        }
    }
}
