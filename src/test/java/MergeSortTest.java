import algorithm.MergeSort;
import entity.Car;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MergeSortTest {

    MergeSort mergeSort = new MergeSort();
    private List data = new ArrayList();

    @Test
    public void singleElement() {
        Car car = new Car.CarBuilder()
                .setModel("Toyota Camry")
                .setPower(150)
                .setYearOfProduction(2022)
                .build();
        data.add(car);

        List sortedList = new ArrayList();
        sortedList.add(car);
        mergeSort.sort(data, Car.getComparator());

        Assertions.assertEquals(sortedList, data);

    }

    @Test
    public void sortTest() {
        List sortedList = new ArrayList<>();

        Car car1 = new Car.CarBuilder()
                .setModel("Toyota Camry")
                .setPower(150)
                .setYearOfProduction(2022)
                .build();
        data.add(car1);
        sortedList.add(car1);

        Car car2 = new Car.CarBuilder()
                .setModel("BMW 330i")
                .setPower(249)
                .setYearOfProduction(2023)
                .build();
        data.add(car2);
        sortedList.add(car2);

        Car car3 = new Car.CarBuilder()
                .setModel("Mercedes-AMG C63")
                .setPower(450)
                .setYearOfProduction(2021)
                .build();
        data.add(car3);
        sortedList.add(car3);

        Car car4 = new Car.CarBuilder()
                .setModel("Volkswagen Polo")
                .setPower(98)
                .setYearOfProduction(2020)
                .build();
        data.add(car4);
        sortedList.add(car4);

        Car car5 = new Car.CarBuilder()
                .setModel("Ferrari 812 Superfast")
                .setPower(670)
                .setYearOfProduction(2023)
                .build();
        data.add(car5);
        sortedList.add(car5);

        Car car6 = new Car.CarBuilder()
                .setModel("Skoda Kodiaq")
                .setPower(202)
                .setYearOfProduction(2022)
                .build();
        data.add(car6);
        sortedList.add(car6);

        Car car7 = new Car.CarBuilder()
                .setModel("Audi Q5")
                .setPower(300)
                .setYearOfProduction(2019)
                .build();
        data.add(car7);
        sortedList.add(car7);

        Car car8 = new Car.CarBuilder()
                .setModel("Hyundai Tucson")
                .setPower(155)
                .setYearOfProduction(2021)
                .build();
        data.add(car8);
        sortedList.add(car8);

        Car car9 = new Car.CarBuilder()
                .setModel("Porsche 911 Turbo S")
                .setPower(600)
                .setYearOfProduction(2024)
                .build();
        data.add(car9);
        sortedList.add(car9);

        Car car10 = new Car.CarBuilder()
                .setModel("Kia Rio")
                .setPower(116)
                .setYearOfProduction(2018)
                .build();
        data.add(car10);
        sortedList.add(car10);

        Car car11 = new Car.CarBuilder()
                .setModel("Ford Mustang GT")
                .setPower(381)
                .setYearOfProduction(2020)
                .build();
        data.add(car11);
        sortedList.add(car11);

        Car car12 = new Car.CarBuilder()
                .setModel("Dacia Logan")
                .setPower(72)
                .setYearOfProduction(2015)
                .build();
        data.add(car12);
        sortedList.add(car12);

        Car car13 = new Car.CarBuilder()
                .setModel("Tesla Model S Plaid")
                .setPower(1000)
                .setYearOfProduction(2023)
                .build();
        data.add(car13);
        sortedList.add(car13);

        Car car14 = new Car.CarBuilder()
                .setModel("Renault Duster")
                .setPower(122)
                .setYearOfProduction(2019)
                .build();
        data.add(car14);
        sortedList.add(car14);

        Car car15 = new Car.CarBuilder()
                .setModel("Lada Vesta")
                .setPower(150)
                .setYearOfProduction(2017)
                .build();
        data.add(car15);
        sortedList.add(car15);

        sortedList.sort(Car.getComparator());

        mergeSort.sort(data, Car.getComparator());

        Assertions.assertEquals(sortedList, data);
    }

    @Test
    public void nullList() {
        data = null;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            mergeSort.sort(data, Car.getComparator());
        });
    }

    @Test
    public void nullComparator() {
        Car car1 = new Car.CarBuilder()
                .setModel("Toyota Camry")
                .setPower(150)
                .setYearOfProduction(2022)
                .build();
        data.add(car1);
        Car car2 = new Car.CarBuilder()
                .setModel("BMW 330i")
                .setPower(249)
                .setYearOfProduction(2023)
                .build();
        data.add(car2);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            mergeSort.sort(data, null);
        });
    }

    @Test
    public void sortSortedList() {
        List sortedList = new ArrayList<>();

        Car car1 = new Car.CarBuilder()
                .setModel("Toyota Camry")
                .setPower(150)
                .setYearOfProduction(2022)
                .build();
        data.add(car1);
        sortedList.add(car1);

        Car car2 = new Car.CarBuilder()
                .setModel("BMW 330i")
                .setPower(249)
                .setYearOfProduction(2023)
                .build();
        data.add(car2);
        sortedList.add(car2);

        Car car3 = new Car.CarBuilder()
                .setModel("Mercedes-AMG C63")
                .setPower(450)
                .setYearOfProduction(2021)
                .build();
        data.add(car3);
        sortedList.add(car3);

        Car car4 = new Car.CarBuilder()
                .setModel("Volkswagen Polo")
                .setPower(98)
                .setYearOfProduction(2020)
                .build();
        data.add(car4);
        sortedList.add(car4);

        Car car5 = new Car.CarBuilder()
                .setModel("Ferrari 812 Superfast")
                .setPower(670)
                .setYearOfProduction(2023)
                .build();
        data.add(car5);
        sortedList.add(car5);

        Car car6 = new Car.CarBuilder()
                .setModel("Skoda Kodiaq")
                .setPower(202)
                .setYearOfProduction(2022)
                .build();
        data.add(car6);
        sortedList.add(car6);

        Car car7 = new Car.CarBuilder()
                .setModel("Audi Q5")
                .setPower(300)
                .setYearOfProduction(2019)
                .build();
        data.add(car7);
        sortedList.add(car7);

        Car car8 = new Car.CarBuilder()
                .setModel("Hyundai Tucson")
                .setPower(155)
                .setYearOfProduction(2021)
                .build();
        data.add(car8);
        sortedList.add(car8);

        Car car9 = new Car.CarBuilder()
                .setModel("Porsche 911 Turbo S")
                .setPower(600)
                .setYearOfProduction(2024)
                .build();
        data.add(car9);
        sortedList.add(car9);

        Car car10 = new Car.CarBuilder()
                .setModel("Kia Rio")
                .setPower(116)
                .setYearOfProduction(2018)
                .build();
        data.add(car10);
        sortedList.add(car10);

        Car car11 = new Car.CarBuilder()
                .setModel("Ford Mustang GT")
                .setPower(381)
                .setYearOfProduction(2020)
                .build();
        data.add(car11);
        sortedList.add(car11);

        Car car12 = new Car.CarBuilder()
                .setModel("Dacia Logan")
                .setPower(72)
                .setYearOfProduction(2015)
                .build();
        data.add(car12);
        sortedList.add(car12);

        Car car13 = new Car.CarBuilder()
                .setModel("Tesla Model S Plaid")
                .setPower(1000)
                .setYearOfProduction(2023)
                .build();
        data.add(car13);
        sortedList.add(car13);

        Car car14 = new Car.CarBuilder()
                .setModel("Renault Duster")
                .setPower(122)
                .setYearOfProduction(2019)
                .build();
        data.add(car14);
        sortedList.add(car14);

        Car car15 = new Car.CarBuilder()
                .setModel("Lada Vesta")
                .setPower(150)
                .setYearOfProduction(2017)
                .build();
        data.add(car15);
        sortedList.add(car15);

        sortedList.sort(Car.getComparator());
        sortedList.sort(Car.getComparator());

        mergeSort.sort(data, Car.getComparator());
        mergeSort.sort(data, Car.getComparator());

        Assertions.assertEquals(sortedList, data);
    }

    @Test
    public void sortEmptyList(){
        data = new ArrayList<>();
        List<Car> emptyList = new ArrayList<>();

        mergeSort.sort(emptyList, Car.getComparator());
        data.sort(Car.getComparator());

        Assertions.assertEquals(emptyList, data);
    }

    @Test
    public void sortImmutableList(){
        Car car1 = new Car.CarBuilder()
                .setModel("Toyota Camry")
                .setPower(150)
                .setYearOfProduction(2022)
                .build();
        Car car2 = new Car.CarBuilder()
                .setModel("BMW 330i")
                .setPower(249)
                .setYearOfProduction(2023)
                .build();
        Car car3 = new Car.CarBuilder()
                .setModel("Mercedes-AMG C63")
                .setPower(450)
                .setYearOfProduction(2021)
                .build();

        data = List.of(car1, car3, car2);
        List<Car> unsortedList = List.of(car1, car3, car2);
        List<Car> sortedList = unsortedList;


        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            data.sort(Car.getComparator());
            mergeSort.sort(sortedList, Car.getComparator());
        });

    }
}
