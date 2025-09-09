package application;

import algorithm.BinarySearch;
import data.DataProvider;
import data.FileDataProviderStrategy;
import data.ManualDataProviderStrategy;
import data.RandomDataProviderStrategy;
import entity.Book;
import entity.Car;
import entity.RootVegetable;
import interfaces.DataProviderStrategy;
import thread.SortingService;
import validator.Validator;
import thread.FileWriterService;

import java.io.File;
import java.util.*;
import java.util.function.Supplier;

import static application.ApplicationMenu.*;

/**
 * Главный класс приложения, реализующий консольный интерфейс пользователя.
 * Координирует работу всех компонентов системы: ввод данных, сортировку, поиск.
 */
public class Main {

    private static final String PATH_OF_BOOK = "src" + File.separator + "main" + File.separator + "resources" +
            File.separator + "books";
    private static final String PATH_OF_CAR = "src" + File.separator + "main" + File.separator + "resources" +
            File.separator + "cars";
    private static final String PATH_OF_VEGETABLES = "src" + File.separator + "main" + File.separator
            + "resources" + File.separator + "vegetables";

    private static final BinarySearch BINARY_SEARCH = new BinarySearch();
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Обрабатывает работу с данными: сортировку и поиск.
     *
     * @param <T> тип обрабатываемых данных
     * @param data список данных для обработки
     * @param dataType тип данных (для отображения)
     * @param itemCreator поставщик объектов для поиска
     * @param comparatorSupplier поставщик компаратора для сортировки и поиска
     */
    private static <T> void processDataWork(List<T> data, String dataType, Supplier<T> itemCreator, Supplier<Comparator<T>> comparatorSupplier) {
        boolean backToMainMenu = false;

        while (!backToMainMenu) {
            dataWorkMenu();

            if (SCANNER.hasNextInt()) {
                int userPick = SCANNER.nextInt();
                switch (userPick) {
                    case 1 -> sortData(data, dataType, comparatorSupplier.get());
                    case 2 -> searchData(data, itemCreator, comparatorSupplier.get());
                    case 0 -> {
                        showBackMessage();
                        backToMainMenu = true;
                    }
                    default -> showInvalidChoiceMessage();
                }
            } else {
                showInvalidChoiceMessage();
                SCANNER.next();
            }
        }
    }

    /**
     * Выполняет сортировку данных в многопоточном режиме и сохраняет результаты в файл.
     * Результаты сортировки автоматически записываются в файл sorted_[dataType].txt
     * в режиме добавления данных.
     *
     * @param <T> тип сортируемых данных
     * @param data список данных для сортировки
     * @param dataType тип данных (для отображения и именования файла)
     * @param comparator компаратор для определения порядка сортировки
     */
    private static <T> void sortData(List<T> data, String dataType, Comparator<T> comparator) {
        System.out.println("Отсортированный список: ");
        SortingService<T> sortingService = new SortingService<>();
        sortingService.sortInTwoThreads(new ArrayList<>(data), comparator, dataType);
        sortingService.shutdown();
        System.out.println(data);

        String filename = "sorted_" + dataType + ".txt";
        FileWriterService.writeToFile(data, filename, true);
        ApplicationMenu.showFileWriteSuccess(filename);
    }

    /**
     * Выполняет поиск элемента в отсортированном списке и сохраняет результаты в файл.
     * Результаты поиска автоматически записываются в файл search_results.txt
     * в режиме добавления данных.
     *
     * @param <T> тип данных для поиска
     * @param data отсортированный список данных
     * @param builderSupplier поставщик объекта для поиска
     * @param comparator компаратор для сравнения элементов
     */
    private static <T> void searchData(List<T> data, Supplier<T> builderSupplier, Comparator<T> comparator) {
        T searchItem = createSearchItem(builderSupplier);
        if (searchItem != null) {
            int result = BINARY_SEARCH.search(data, searchItem, comparator);
            if (result != -1) {
                String message = "Элемент не найден на позиции: " + result;
                System.out.println(message);

                String filename = "search_results.txt";
                FileWriterService.writeStringToFile(message, filename, true);
                FileWriterService.writeToFile(searchItem, filename, true);
            } else  {
                String message = "Элемент не найден: " + searchItem;
                System.out.println(message);

                FileWriterService.writeStringToFile(message, "search_results.txt", true);
                ApplicationMenu.showFileWriteSuccess("search_results.txt");
            }
        }
    }

    /**
     * Создает объект книги на основе пользовательского ввода.
     *
     * @return объект книги или null при ошибке ввода
     */
    private static Book createBookFromInput() {
        System.out.println("Введите книгу для поиска (Автор, название, количество страниц):");
        SCANNER.nextLine();
        String input = SCANNER.nextLine();
        String[] parameters = input.split(", ");

        if (parameters.length == 3 && Validator.checkNumber(parameters[2])) {

            return new Book.BookBuilder()
                    .setAuthor(parameters[0])
                    .setTitle(parameters[1])
                    .setQuantityPage(Integer.parseInt(parameters[2]))
                    .build();
        } else {
            System.out.println("Неверный формат или числовое значение не может быть словом");

            return null;
        }
    }


    /**
     * Создает объект машины на основе пользовательского ввода.
     *
     * @return объект машины или null при ошибке ввода
     */
    private static Car createCarFromInput() {
        System.out.println("Введите машину для поиска (Мощность, год выпуска, модель):");
        SCANNER.nextLine();
        String input = SCANNER.nextLine();
        String[] parameters = input.split(", ");

        if (parameters.length == 3 && Validator.checkNumber(parameters[1]) &&
                Validator.checkDouble(parameters[0])) {

            return new Car.CarBuilder()
                    .setPower(Double.parseDouble(parameters[0]))
                    .setYearOfProduction(Integer.parseInt(parameters[1]))
                    .setModel(parameters[2])
                    .build();
        } else {
            System.out.println("Неверный формат или числовое значение не может быть словом");

            return null;
        }
    }

    /**
     * Создает объект корнеплода на основе пользовательского ввода.
     *
     * @return объект корнеплода или null при ошибке ввода
     */
    private static RootVegetable createVegetableFromInput() {
        System.out.println("Введите корнеплод для поиска (Тип, вес, цвет):");
        SCANNER.nextLine();
        String input = SCANNER.nextLine();
        String[] parameters = input.split(", ");

        if (parameters.length == 3 && Validator.checkDouble(parameters[1])) {

            return new RootVegetable.RootVegetableBuilder()
                    .setType(parameters[0])
                    .setWeight(Double.parseDouble(parameters[1]))
                    .setColor(parameters[2])
                    .build();
        } else {
            System.out.println("Неверный формат или числовое значение не может быть словом");

            return null;
        }
    }

    /**
     * Главный метод приложения, точка входа.
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        boolean runFlag = true;

        while (runFlag) {
            mainMenu();

            if (SCANNER.hasNextInt()) {
                int userPick = SCANNER.nextInt();

                switch (userPick) {
                    case 1 -> processDataType("books", PATH_OF_BOOK,
                            Main::createBookFromInput, Book::getComparator);
                    case 2 -> processDataType("cars", PATH_OF_CAR,
                            Main::createCarFromInput, Car::getComparator);
                    case 3 -> processDataType("vegetables", PATH_OF_VEGETABLES,
                            Main::createVegetableFromInput, RootVegetable::getComparator);
                    case 0 -> {
                        showExitMessage();
                        runFlag = false;
                    }
                    default -> showInvalidChoiceMessage();
                }
            } else {
                showExitMessage();
                SCANNER.next();
            }
        }
        SCANNER.close();
    }

    /**
     * Обрабатывает работу с конкретным типом данных.
     *
     * @param <T> тип обрабатываемых данных
     * @param dataType тип данных
     * @param filePath путь к файлу с данными
     * @param itemCreator поставщик объектов для поиска
     * @param comparatorSupplier поставщик компаратора
     */
    private static <T> void processDataType(String dataType, String filePath, Supplier<T> itemCreator, Supplier<Comparator<T>> comparatorSupplier) {
        dataWriterMenu();

        if (SCANNER.hasNextInt()) {
            int userPick = SCANNER.nextInt();
            List<T> data = null;

            switch (userPick) {
                case 1 -> data = provideData(dataType, filePath, new RandomDataProviderStrategy(filePath));
                case 2 -> data = provideData(dataType, filePath, new ManualDataProviderStrategy(SCANNER));
                case 3 -> data = provideDataFromFile(dataType);
                case 0 -> {
                    showBackMessage();
                    return;
                }
                default -> showInvalidChoiceMessage();
            }

            if (data != null && !data.isEmpty()) {
                showDataTypeHeader(dataType);
                System.out.println("Данные получены: " + data);
                processDataWork(data, dataType, itemCreator, comparatorSupplier);
            } else {
                System.out.println("Не удалось получить данные или список пуст");
            }
        } else {
            showInvalidChoiceMessage();
            SCANNER.next();
        }
    }

    /**
     * Предоставляет данные через указанную стратегию.
     *
     * @param <T> тип предоставляемых данных
     * @param dataType тип данных
     * @param strategy стратегия предоставления данных
     * @return список данных
     */
    private static <T> List<T> provideData(String dataType, String filePath, DataProviderStrategy strategy) {
        System.out.println("Введите размер списка:");
        if (SCANNER.hasNextInt()) {
            int length = SCANNER.nextInt();
            DataProvider dataProvider = new DataProvider(strategy);
            return dataProvider.provideData(length, dataType);
        } else {
            showInvalidChoiceMessage();
            SCANNER.next();

            return new ArrayList<>();
        }
    }

    /**
     * Предоставляет данные из файла.
     *
     * @param <T> тип предоставляемых данных
     * @param dataType тип данных
     * @return список данных из файла
     */
    private static <T> List<T> provideDataFromFile(String dataType) {
        System.out.println("Введите размер списка:");
        if (SCANNER.hasNextInt()) {
            int length = SCANNER.nextInt();
            System.out.println("Введите путь до файла:");
            SCANNER.nextLine();
            String filePath = SCANNER.nextLine();
            DataProvider fileDataProvider = new DataProvider(new FileDataProviderStrategy(filePath));

            return fileDataProvider.provideData(length, dataType);
        } else {
            showInvalidChoiceMessage();
            SCANNER.next();

            return new ArrayList<>();
        }
    }

    /**
     * Создает объект для поиска через поставщика.
     *
     * @param <T> тип создаваемого объекта
     * @param itemCreator поставщик объектов
     * @return созданный объект
     */
    private static <T> T createSearchItem(Supplier<T> itemCreator) {

        return itemCreator.get();
    }
}