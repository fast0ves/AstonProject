package application;

import entity.Book;
import entity.Car;
import entity.RootVegetable;

public class Main {
    public static void main(String[] args) {
        Car car = new Car.CarBuilder().build();
        System.out.println(car);
        Car car1 = new Car.CarBuilder()
                .setModel("Infinity Qx35")
                .setPower(155)
                .setYearOfProduction(2011)
                .build();
        System.out.println(car1 + "\n");

        Book book = new Book.BookBuilder().build();
        System.out.println(book);
        Book book1 = new Book.BookBuilder()
                .setAuthor("Пушкин")
                .setTitle("Руслан и Людмила")
                .setQuantityPage(250)
                .build();
        System.out.println(book1 + "\n");

        RootVegetable rootVegetable = new RootVegetable.RootVegetableBuilder().build();
        System.out.println(rootVegetable);
        RootVegetable rootVegetable1 = new RootVegetable.RootVegetableBuilder()
                .setColor("Оранжевый")
                .setType("Зонтичные")
                .setWeight(105.5)
                .build();
        System.out.println(rootVegetable1);



    }
}
