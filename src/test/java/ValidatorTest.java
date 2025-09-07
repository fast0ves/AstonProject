import entity.Book;
import entity.Car;
import entity.RootVegetable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import validator.Validator;

import javax.swing.border.TitledBorder;

public class ValidatorTest {

    @Test
    public void bookValidating(){
        Book bookAuthorNull = new Book.BookBuilder()
                .setAuthor(null)
                .setTitle("Title")
                .setQuantityPage(434)
                .build();
        Book bookTitleNull = new Book.BookBuilder()
                .setAuthor("Author")
                .setTitle(null)
                .setQuantityPage(432)
                .build();
        Book bookIncorrectPage = new Book.BookBuilder()
                .setAuthor("Author")
                .setTitle("Title")
                .setQuantityPage(1000000000)
                .build();

        Assertions.assertEquals(true, Validator.bookValid(bookAuthorNull));
        Assertions.assertEquals(false, Validator.bookValid(bookIncorrectPage));
        Assertions.assertEquals(true, Validator.bookValid(bookTitleNull));

    }

    @Test
    public void carValidating(){
        Car carNullModel = new Car.CarBuilder()
                .setModel(null)
                .setYearOfProduction(2002)
                .setPower(150)
                .build();
        Car carIncorrectYear = new Car.CarBuilder()
                .setModel("Model")
                .setYearOfProduction(999)
                .setPower(303)
                .build();
        Car carIncorrectPower = new Car.CarBuilder()
                .setModel("Model")
                .setYearOfProduction(1999)
                .setPower(-4343)
                .build();

        Assertions.assertEquals(true, Validator.carValid(carNullModel));
        Assertions.assertEquals(true, Validator.carValid(carIncorrectPower));
        Assertions.assertEquals(false, Validator.carValid(carIncorrectYear));
    }

    @Test
    public void rootVegetableValidating(){
        RootVegetable rootVegetableColorNull = new RootVegetable.RootVegetableBuilder()
                .setColor(null)
                .setType("Морковь")
                .setWeight(23)
                .build();
        RootVegetable rootVegetableTypeNull = new RootVegetable.RootVegetableBuilder()
                .setType(null)
                .setColor("белый")
                .setWeight(44)
                .build();
        RootVegetable rootVegetableIncorrectWeight = new RootVegetable.RootVegetableBuilder()
                .setWeight(10001)
                .setColor("желтый")
                .setType("Картофель")
                .build();

        Assertions.assertEquals(true, Validator.vegetableValid(rootVegetableTypeNull));
        Assertions.assertEquals(true, Validator.vegetableValid(rootVegetableColorNull));
        Assertions.assertEquals(false,Validator.vegetableValid(rootVegetableIncorrectWeight));
    }

    @Test
    public void numberValidation(){
        String str = "defdefefe";
        String numberStr = "1324";

        Assertions.assertEquals(false, Validator.checkNumber(str));
        Assertions.assertEquals(true, Validator.checkNumber(numberStr));
    }

    @Test
    public void doubleTypeValidation(){
        String str = "vcxbcvbv";
        String numberStr = "12.4";

        Assertions.assertEquals(false, Validator.checkDouble(str));
        Assertions.assertEquals(true, Validator.checkDouble(numberStr));
    }
}
