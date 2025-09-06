package application;

import algorithm.BinarySearch;
import algorithm.MergeSort;
import data.DataProvider;
import data.FileDataProviderStrategy;
import data.ManualDataProviderStrategy;
import data.RandomDataProviderStrategy;
import entity.Book;
import entity.Car;
import entity.RootVegetable;
import thread.SortingService;
import validator.Validator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static application.ApplicationMenu.*;

public class Main {

    private static final String pathOfBook = "src" + File.separator + "main" + File.separator + "resources" +
            File.separator + "books";
    private static final String pathOfCar = "src" + File.separator + "main" + File.separator + "resources" +
            File.separator + "cars";
    private static final String pathOfVegetables = "src" + File.separator + "main" + File.separator
            + "resources" + File.separator + "vegetables";


    public static void main(String[] args) {

        boolean runFlag = true;
        boolean backToMainMenu = false;
        List data;
        int length;
        BinarySearch binarySearch = new BinarySearch();
        while (runFlag) {
            mainMenu();
            String dataType;
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                int userPick = scanner.nextInt();
                switch (userPick) {
                    case 1:
                        dataType = "books";
                        dataWriterMenu();
                        if (scanner.hasNextInt()) {
                            userPick = scanner.nextInt();
                            switch (userPick) {
                                case 1:
                                    System.out.println("Введите размер списка");
                                    if (scanner.hasNextInt()) {
                                        length = scanner.nextInt();
                                        DataProvider randDataProvider = new DataProvider(
                                                new RandomDataProviderStrategy(pathOfBook));
                                        data = randDataProvider.provideData(length, dataType);
                                        if (!data.isEmpty()) {
                                            System.out.println("Данные заполнены");
                                            System.out.println(data);
                                            backToMainMenu = false;
                                            while (!backToMainMenu) {
                                                dataWorkMenu();
                                                if (scanner.hasNextInt()) {
                                                    userPick = scanner.nextInt();
                                                    switch (userPick) {
                                                        case 1:
                                                            System.out.println("Отсортированный список:");
                                                            SortingService<Book> bookSortingService = new SortingService<>();
                                                            bookSortingService.sortInTwoThreads(new ArrayList<>(data), Book.getComparator(), "книг");
                                                            bookSortingService.shutdown();
                                                            System.out.println(data);
                                                            break;
                                                        case 2:
                                                            System.out.println("Введите книгу, которую хотите найти " +
                                                                    "(Автор, название, количество страниц)");
                                                            scanner.nextLine();
                                                            String input = scanner.nextLine();
                                                            String[] parameters = input.split(", ");
                                                            if (parameters.length == 3 &&
                                                                    Validator.checkNumber(parameters[2])) {
                                                                Book searchBook = new Book.BookBuilder()
                                                                        .setAuthor(parameters[0])
                                                                        .setTitle(parameters[1])
                                                                        .setQuantityPage(Integer.parseInt(parameters[2]))
                                                                        .build();
                                                                System.out.println(binarySearch.search(data, searchBook,
                                                                        Book.getComparator()));
                                                            } else
                                                                System.out.println("Числовое значение не может быть словом");
                                                            break;
                                                        case 0:
                                                            backToMainMenu = true;
                                                            break;
                                                    }
                                                }
                                            }
                                        }
                                    } else System.out.println("Введите число");
                                    break;

                                case 2:
                                    System.out.println("Введите размер списка");
                                    if (scanner.hasNextInt()) {
                                        length = scanner.nextInt();
                                        scanner.nextLine();
                                        System.out.println("При запонение данных придерживайтесь данного формата: " +
                                                "(Автор, название, количество страниц)");
                                        DataProvider dataProvider = new DataProvider(
                                                new ManualDataProviderStrategy(scanner));
                                        data = dataProvider.provideData(length, dataType);
                                        System.out.println("Данные заполнены");
                                        System.out.println(data);
                                        backToMainMenu = false;
                                        while (!backToMainMenu) {
                                            dataWorkMenu();
                                            if (scanner.hasNextInt()) {
                                                userPick = scanner.nextInt();
                                                switch (userPick) {
                                                    case 1:
                                                        System.out.println("Отсортированный список:");
                                                        SortingService<Book> bookSortingService = new SortingService<>();
                                                        bookSortingService.sortInTwoThreads(new ArrayList<>(data), Book.getComparator(), "книг");
                                                        bookSortingService.shutdown();
                                                        System.out.println(data);
                                                        break;

                                                    case 2:
                                                        System.out.println("Введите книгу, которую хотите найти " +
                                                                "(Автор, название, количество страниц)");
                                                        scanner.nextLine();
                                                        String input = scanner.nextLine();
                                                        String[] parameters = input.split(", ");
                                                        if (parameters.length == 3 &&
                                                                Validator.checkNumber(parameters[2])) {
                                                            Book searchBook = new Book.BookBuilder()
                                                                    .setAuthor(parameters[0])
                                                                    .setTitle(parameters[1])
                                                                    .setQuantityPage(Integer.parseInt(parameters[2]))
                                                                    .build();
                                                            System.out.println(binarySearch.search(data, searchBook,
                                                                    Book.getComparator()));
                                                        } else
                                                            System.out.println("Числовое значение не может быть словом");
                                                        break;
                                                    case 0:
                                                        backToMainMenu = true;
                                                        break;
                                                }
                                            }
                                        }
                                    } else System.out.println("Введите число");
                                    break;
                                case 3:
                                    System.out.println("Введите размер списка");
                                    if (scanner.hasNextInt()) {
                                        length = scanner.nextInt();
                                        System.out.println("Введите путь до файла");
                                        scanner.nextLine();
                                        String filePath = scanner.nextLine();
                                        DataProvider fileDataProvider = new DataProvider(
                                                new FileDataProviderStrategy(filePath));
                                        data = fileDataProvider.provideData(length, dataType);
                                        if (!data.isEmpty()) {
                                            System.out.println("Данные заполнены");
                                            System.out.println(data);
                                            backToMainMenu = false;
                                            while (!backToMainMenu) {
                                                dataWorkMenu();
                                                if (scanner.hasNextInt()) {
                                                    userPick = scanner.nextInt();
                                                    switch (userPick) {

                                                        case 1:
                                                            System.out.println("Отсортированный список:");
                                                            SortingService<Book> bookSortingService = new SortingService<>();
                                                            bookSortingService.sortInTwoThreads(new ArrayList<>(data), Book.getComparator(), "книг");
                                                            bookSortingService.shutdown();
                                                            System.out.println(data);
                                                            break;

                                                        case 2:
                                                            System.out.println("Введите книгу, которую хотите найти " +
                                                                    "(Автор, название, количество страниц)");
                                                            scanner.nextLine();
                                                            String input = scanner.nextLine();
                                                            String[] parameters = input.split(", ");
                                                            if (parameters.length == 3 &&
                                                                    Validator.checkNumber(parameters[2])) {
                                                                Book searchBook = new Book.BookBuilder()
                                                                        .setAuthor(parameters[0])
                                                                        .setTitle(parameters[1])
                                                                        .setQuantityPage(Integer.parseInt(parameters[2]))
                                                                        .build();
                                                                System.out.println(binarySearch.search(data, searchBook,
                                                                        Book.getComparator()));
                                                            } else
                                                                System.out.println("Числовое значение не может быть словом");
                                                            break;

                                                        case 0:
                                                            backToMainMenu = true;
                                                            break;
                                                    }
                                                }
                                            }
                                        }
                                    } else System.out.println("Введите число");
                                    break;

                                case 0:
                                    break;
                            }
                        } else System.out.println("Введите число 0-3");
                        break;

                    case 2:
                        dataType = "cars";
                        dataWriterMenu();
                        if (scanner.hasNextInt()) {
                            userPick = scanner.nextInt();
                            switch (userPick) {

                                case 1:
                                    System.out.println("Введите размер списка");
                                    if (scanner.hasNextInt()) {
                                        length = scanner.nextInt();
                                        DataProvider randDataProvider = new DataProvider(
                                                new RandomDataProviderStrategy(pathOfCar));
                                        data = randDataProvider.provideData(length, dataType);
                                        if (!data.isEmpty()) {
                                            System.out.println("Данные заполнены");
                                            System.out.println(data);
                                            backToMainMenu = false;
                                            while (!backToMainMenu) {
                                                dataWorkMenu();
                                                if (scanner.hasNextInt()) {
                                                    userPick = scanner.nextInt();
                                                    switch (userPick) {

                                                        case 1:
                                                            System.out.println("Отсортированный список:");
                                                            SortingService<Car> carSortingService = new SortingService<>();
                                                            carSortingService.sortInTwoThreads(new ArrayList<>(data), Car.getComparator(), "автомобилей");
                                                            carSortingService.shutdown();
                                                            System.out.println(data);
                                                            break;

                                                        case 2:
                                                            System.out.println("Введите машину, которую хотите найти " +
                                                                    "(Мощность(число), год выпуска, модель)");
                                                            scanner.nextLine();
                                                            String input = scanner.nextLine();
                                                            String[] parameters = input.split(", ");
                                                            if (parameters.length == 3 &&
                                                                    Validator.checkNumber(parameters[1]) &&
                                                                    Validator.checkDouble(parameters[0])) {
                                                                Car searchCar = new Car.CarBuilder()
                                                                        .setPower(Double.parseDouble(parameters[0]))
                                                                        .setYearOfProduction(Integer.parseInt(parameters[1]))
                                                                        .setModel(parameters[2])
                                                                        .build();
                                                                System.out.println(binarySearch.search(data, searchCar,
                                                                        Car.getComparator()));
                                                            } else
                                                                System.out.println("Числовое значение не может быть словом");
                                                            break;

                                                        case 0:
                                                            backToMainMenu = true;
                                                            break;
                                                    }
                                                }
                                            }
                                        }
                                    } else System.out.println("Введите число");
                                    break;

                                case 2:
                                    System.out.println("Введите размер списка");
                                    if (scanner.hasNextInt()) {
                                        length = scanner.nextInt();
                                        scanner.nextLine();
                                        System.out.println("При заполнении данных придерживайтесь данного формата: " +
                                                "(Мощность(число), год выпуска, модель)");
                                        DataProvider dataProvider = new DataProvider(
                                                new ManualDataProviderStrategy(scanner));
                                        data = dataProvider.provideData(length, dataType);
                                        System.out.println("Данные заполнены");
                                        System.out.println(data);
                                        backToMainMenu = false;
                                        while (!backToMainMenu) {
                                            dataWorkMenu();
                                            if (scanner.hasNextInt()) {
                                                userPick = scanner.nextInt();
                                                switch (userPick) {

                                                    case 1:
                                                        System.out.println("Отсортированный список:");
                                                        SortingService<Car> carSortingService = new SortingService<>();
                                                        carSortingService.sortInTwoThreads(new ArrayList<>(data), Car.getComparator(), "автомобилей");
                                                        carSortingService.shutdown();
                                                        System.out.println(data);
                                                        break;

                                                    case 2:
                                                        System.out.println("Введите машину, которую хотите найти " +
                                                                "(Мощность(число), год выпуска, модель)");
                                                        scanner.nextLine();
                                                        String input = scanner.nextLine();
                                                        String[] parameters = input.split(", ");
                                                        if (parameters.length == 3 &&
                                                                Validator.checkNumber(parameters[1]) &&
                                                                Validator.checkDouble(parameters[0])) {
                                                            Car searchCar = new Car.CarBuilder()
                                                                    .setPower(Double.parseDouble(parameters[0]))
                                                                    .setYearOfProduction(Integer.parseInt(parameters[1]))
                                                                    .setModel(parameters[2])
                                                                    .build();
                                                            System.out.println(binarySearch.search(data, searchCar,
                                                                    Car.getComparator()));
                                                        } else
                                                            System.out.println("Числовое значение не может быть словом");
                                                        break;
                                                    case 0:
                                                        backToMainMenu = true;
                                                        break;
                                                }
                                            }
                                        }
                                    } else System.out.println("Введите число");
                                    break;

                                case 3:
                                    System.out.println("Введите размер списка");
                                    if (scanner.hasNextInt()) {
                                        length = scanner.nextInt();
                                        System.out.println("Введите путь до файла");
                                        scanner.nextLine();
                                        String filePath = scanner.nextLine();
                                        DataProvider fileDataProvider = new DataProvider(
                                                new FileDataProviderStrategy(filePath));
                                        data = fileDataProvider.provideData(length, dataType);
                                        if (!data.isEmpty()) {
                                            System.out.println("Данные заполнены");
                                            System.out.println(data);
                                            backToMainMenu = false;
                                            while (!backToMainMenu) {
                                                dataWorkMenu();
                                                if (scanner.hasNextInt()) {
                                                    userPick = scanner.nextInt();
                                                    switch (userPick) {

                                                        case 1:
                                                            System.out.println("Отсортированный список:");
                                                            SortingService<Car> carSortingService = new SortingService<>();
                                                            carSortingService.sortInTwoThreads(new ArrayList<>(data), Car.getComparator(), "автомобилей");
                                                            carSortingService.shutdown();
                                                            System.out.println(data);
                                                            break;

                                                        case 2:
                                                            System.out.println("Введите машину, которую хотите найти " +
                                                                    "(Мощность(число), год выпуска, модель)");
                                                            scanner.nextLine();
                                                            String input = scanner.nextLine();
                                                            String[] parameters = input.split(", ");
                                                            if (parameters.length == 3 &&
                                                                    Validator.checkNumber(parameters[1]) &&
                                                                    Validator.checkDouble(parameters[0])) {
                                                                Car searchCar = new Car.CarBuilder()
                                                                        .setPower(Double.parseDouble(parameters[0]))
                                                                        .setYearOfProduction(Integer.parseInt(parameters[1]))
                                                                        .setModel(parameters[2])
                                                                        .build();
                                                                System.out.println(binarySearch.search(data, searchCar,
                                                                        Car.getComparator()));
                                                            } else
                                                                System.out.println("Числовое значение не может быть словом");
                                                            break;

                                                        case 0:
                                                            backToMainMenu = true;
                                                            break;
                                                    }
                                                }
                                            }
                                        }
                                    } else System.out.println("Введите число");
                                    break;

                                case 0:
                                    break;
                            }
                        } else System.out.println("Введите число 0-3");
                        break;

                    case 3:
                        dataType = "vegetables";
                        dataWriterMenu();
                        if (scanner.hasNextInt()) {
                            userPick = scanner.nextInt();
                            switch (userPick) {

                                case 1:
                                    System.out.println("Введите размер списка");
                                    if (scanner.hasNextInt()) {
                                        length = scanner.nextInt();
                                        DataProvider randDataProvider = new DataProvider(
                                                new RandomDataProviderStrategy(pathOfVegetables));
                                        data = randDataProvider.provideData(length, dataType);
                                        if (!data.isEmpty()) {
                                            System.out.println("Данные заполнены");
                                            System.out.println(data);
                                            backToMainMenu = false;
                                            while (!backToMainMenu) {
                                                dataWorkMenu();
                                                if (scanner.hasNextInt()) {
                                                    userPick = scanner.nextInt();
                                                    switch (userPick) {

                                                        case 1:
                                                            System.out.println("Отсортированный список:");
                                                            SortingService<RootVegetable> vegSortingService = new SortingService<>();
                                                            vegSortingService.sortInTwoThreads(new ArrayList<>(data), RootVegetable.getComparator(), "корнеплодов");
                                                            vegSortingService.shutdown();
                                                            System.out.println(data);
                                                            break;

                                                        case 2:
                                                            System.out.println("Введите корнеплод, который хотите найти " +
                                                                    "(Тип, вес(число), цвет)");
                                                            scanner.nextLine();
                                                            String input = scanner.nextLine();
                                                            String[] parameters = input.split(", ");
                                                            if (parameters.length == 3 &&
                                                                    Validator.checkDouble(parameters[1])) {
                                                                RootVegetable searchRootVegetable = new RootVegetable
                                                                        .RootVegetableBuilder()
                                                                        .setType(parameters[0])
                                                                        .setWeight(Double.parseDouble(parameters[1]))
                                                                        .setColor(parameters[2])
                                                                        .build();
                                                                System.out.println(binarySearch.search(data,
                                                                        searchRootVegetable,
                                                                        RootVegetable.getComparator()));
                                                            } else
                                                                System.out.println("Числовое значение не может быть словом");
                                                            break;

                                                        case 0:
                                                            backToMainMenu = true;
                                                            break;
                                                    }
                                                }
                                            }
                                        }
                                    } else System.out.println("Введите число");
                                    break;

                                case 2:
                                    System.out.println("Введите размер списка");
                                    if (scanner.hasNextInt()) {
                                        length = scanner.nextInt();
                                        scanner.nextLine();
                                        System.out.println("При заполнении данных придерживайтесь данного формата: " +
                                                "(Тип, вес(число), цвет)");
                                        DataProvider dataProvider = new DataProvider(
                                                new ManualDataProviderStrategy(scanner));
                                        data = dataProvider.provideData(length, dataType);
                                        System.out.println("Данные заполнены");
                                        System.out.println(data);
                                        backToMainMenu = false;
                                        while (!backToMainMenu) {
                                            dataWorkMenu();
                                            if (scanner.hasNextInt()) {
                                                userPick = scanner.nextInt();
                                                switch (userPick) {

                                                    case 1:
                                                        System.out.println("Отсортированный список:");
                                                        SortingService<RootVegetable> vegSortingService = new SortingService<>();
                                                        vegSortingService.sortInTwoThreads(new ArrayList<>(data), RootVegetable.getComparator(), "корнеплодов");
                                                        vegSortingService.shutdown();
                                                        System.out.println(data);
                                                        break;

                                                    case 2:
                                                        System.out.println("Введите корнеплод, который хотите найти " +
                                                                "(Тип, вес(число), цвет)");
                                                        scanner.nextLine();
                                                        String input = scanner.nextLine();
                                                        String[] parameters = input.split(", ");
                                                        if (parameters.length == 3 && Validator.checkDouble(parameters[1])) {
                                                            RootVegetable searchRootVegetable = new RootVegetable
                                                                    .RootVegetableBuilder()
                                                                    .setType(parameters[0])
                                                                    .setWeight(Double.parseDouble(parameters[1]))
                                                                    .setColor(parameters[2])
                                                                    .build();
                                                            System.out.println(binarySearch.search(data, searchRootVegetable,
                                                                    RootVegetable.getComparator()));
                                                        } else
                                                            System.out.println("Числовое значение не может быть словом");
                                                        break;

                                                    case 0:
                                                        backToMainMenu = true;
                                                        break;
                                                }
                                            }
                                        }
                                    } else System.out.println("Введите число");
                                    break;

                                case 3:
                                    System.out.println("Введите размер списка");
                                    if (scanner.hasNextInt()) {
                                        length = scanner.nextInt();
                                        System.out.println("Введите путь до файла");
                                        scanner.nextLine();
                                        String filePath = scanner.nextLine();
                                        DataProvider fileDataProvider = new DataProvider(
                                                new FileDataProviderStrategy(filePath));
                                        data = fileDataProvider.provideData(length, dataType);
                                        if (!data.isEmpty()) {
                                            System.out.println("Данные заполнены");
                                            System.out.println(data);
                                            backToMainMenu = false;
                                            while (!backToMainMenu) {
                                                dataWorkMenu();
                                                if (scanner.hasNextInt()) {
                                                    userPick = scanner.nextInt();
                                                    switch (userPick) {

                                                        case 1:
                                                            System.out.println("Отсортированный список:");
                                                            SortingService<RootVegetable> vegSortingService = new SortingService<>();
                                                            vegSortingService.sortInTwoThreads(new ArrayList<>(data), RootVegetable.getComparator(), "корнеплодов");
                                                            vegSortingService.shutdown();
                                                            System.out.println(data);
                                                            break;

                                                        case 2:
                                                            System.out.println("Введите корнеплод, который хотите найти " +
                                                                    "(Тип, вес(число), цвет)");
                                                            scanner.nextLine();
                                                            String input = scanner.nextLine();
                                                            String[] parameters = input.split(", ");
                                                            if (parameters.length == 3 &&
                                                                    Validator.checkDouble(parameters[1])) {
                                                                RootVegetable searchRootVegetable = new RootVegetable
                                                                        .RootVegetableBuilder()
                                                                        .setType(parameters[0])
                                                                        .setWeight(Double.parseDouble(parameters[1]))
                                                                        .setColor(parameters[2])
                                                                        .build();
                                                                System.out.println(binarySearch.search(data,
                                                                        searchRootVegetable,
                                                                        RootVegetable.getComparator()));
                                                            } else
                                                                System.out.println("Числовое значение не может быть словом");
                                                            break;

                                                        case 0:
                                                            backToMainMenu = true;
                                                            break;
                                                    }
                                                }
                                            }
                                        }
                                    } else System.out.println("Введите число");
                                    break;

                                case 0:
                                    break;
                            }
                        } else System.out.println("Введите число 0-3");
                        break;

                    case 0:
                        runFlag = false;
                        break;
                }
            } else System.out.println("Введите число 0-3");
        }
    }
}