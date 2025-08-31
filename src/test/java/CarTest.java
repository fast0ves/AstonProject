/*
created by Ivanov Nikita
*/
import entity.Car;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CarTest {

    @Test
    public void createDefaultCar() {
        Car car = new Car.CarBuilder().build();
        Assertions.assertEquals("Lada Samara-2", car.getModel());
        Assertions.assertEquals(81.6, car.getPower());
        Assertions.assertEquals(2013, car.getYearOfProduction());
    }

    @Test
    public void createCarWithCustomPower() {
        Car car = new Car.CarBuilder().setPower(111).build();
        Assertions.assertEquals("Lada Samara-2", car.getModel());
        Assertions.assertEquals(111, car.getPower());
        Assertions.assertEquals(2013, car.getYearOfProduction());

    }

    @Test
    public void createCarWithCustomModel() {
        Car car = new Car.CarBuilder().setModel("WW Golf").build();
        Assertions.assertEquals("WW Golf", car.getModel());
        Assertions.assertEquals(81.6, car.getPower());
        Assertions.assertEquals(2013, car.getYearOfProduction());
    }

    @Test
    public void createCarWithCustomYearOfProduction() {
        Car car = new Car.CarBuilder().setYearOfProduction(2000).build();
        Assertions.assertEquals("Lada Samara-2", car.getModel());
        Assertions.assertEquals(81.6, car.getPower());
        Assertions.assertEquals(2000, car.getYearOfProduction());
    }

    @Test
    public void createCarWithTwoCustomParameters() {
        Car car = new Car.CarBuilder()
                .setYearOfProduction(1989)
                .setModel("BMW e-34")
                .build();
        Assertions.assertEquals("BMW e-34", car.getModel());
        Assertions.assertEquals(81.6, car.getPower());
        Assertions.assertEquals(1989, car.getYearOfProduction());
    }

    @Test
    public void createCarWithAllCustomParameters() {
        Car car = new Car.CarBuilder()
                .setModel("KIA")
                .setPower(155)
                .setYearOfProduction(2017)
                .build();
        Assertions.assertEquals("KIA", car.getModel());
        Assertions.assertEquals(155, car.getPower());
        Assertions.assertEquals(2017, car.getYearOfProduction());
    }

    @Test
    public void createCarWithIncorrectParameters() {
        Car car = new Car.CarBuilder()
                .setPower(-1)
                .setYearOfProduction(-132)
                .setModel("")
                .build();
        Assertions.assertEquals("Lada Samara-2", car.getModel());
        Assertions.assertEquals(81.6, car.getPower());
        Assertions.assertEquals(2013, car.getYearOfProduction());
    }
}
