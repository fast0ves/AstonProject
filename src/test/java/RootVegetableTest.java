import entity.RootVegetable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RootVegetableTest {

    @Test
    public void createDefaultRootVegetable() {
        RootVegetable rootVegetable = new RootVegetable.RootVegetableBuilder().build();
        Assertions.assertEquals("Корнеплод", rootVegetable.getType());
        Assertions.assertEquals(1, rootVegetable.getWeight());
        Assertions.assertEquals("Бесцветный", rootVegetable.getColor());
    }

    @Test
    public void createRootVegetableWithCustomType() {
        RootVegetable rootVegetable = new RootVegetable.RootVegetableBuilder().setType("Капустный").build();
        Assertions.assertEquals("Капустный", rootVegetable.getType());
        Assertions.assertEquals(1, rootVegetable.getWeight());
        Assertions.assertEquals("Бесцветный", rootVegetable.getColor());
    }

    @Test
    public void createRootVegetableWithCustomWeight() {
        RootVegetable rootVegetable = new RootVegetable.RootVegetableBuilder().setWeight(22).build();
        Assertions.assertEquals("Корнеплод", rootVegetable.getType());
        Assertions.assertEquals(22, rootVegetable.getWeight());
        Assertions.assertEquals("Бесцветный", rootVegetable.getColor());
    }

    @Test
    public void createRootVegetableWithCustomColor() {
        RootVegetable rootVegetable = new RootVegetable.RootVegetableBuilder().setColor("Оранжевый").build();
        Assertions.assertEquals("Корнеплод", rootVegetable.getType());
        Assertions.assertEquals(1, rootVegetable.getWeight());
        Assertions.assertEquals("Оранжевый", rootVegetable.getColor());
    }

    @Test
    public void createRootVegetableWithTwoCustomParameters() {
        RootVegetable rootVegetable = new RootVegetable.RootVegetableBuilder()
                .setType("Зонтичный")
                .setColor("Зеленый")
                .build();
        Assertions.assertEquals("Зонтичный", rootVegetable.getType());
        Assertions.assertEquals(1, rootVegetable.getWeight());
        Assertions.assertEquals("Зеленый", rootVegetable.getColor());
    }

    @Test
    public void createRootVegetableWithAllCustomParameters() {
        RootVegetable rootVegetable = new RootVegetable.RootVegetableBuilder()
                .setType("Маревые")
                .setColor("Красный")
                .setWeight(300)
                .build();
        Assertions.assertEquals("Маревые", rootVegetable.getType());
        Assertions.assertEquals(300, rootVegetable.getWeight());
        Assertions.assertEquals("Красный", rootVegetable.getColor());
    }

    @Test
    public void createRootVegetableIncorrectParameters() {
        RootVegetable rootVegetable = new RootVegetable.RootVegetableBuilder()
                .setType("")
                .setColor("")
                .setWeight(-4232)
                .build();
        Assertions.assertEquals("Корнеплод", rootVegetable.getType());
        Assertions.assertEquals(1, rootVegetable.getWeight());
        Assertions.assertEquals("Бесцветный", rootVegetable.getColor());
    }

}
