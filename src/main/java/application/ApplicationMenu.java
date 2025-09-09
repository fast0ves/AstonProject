package application;

/**
 * Утилитный класс для отображения меню приложения.
 * Предоставляет статические методы для отображения различных меню и сообщений.
 */
public class ApplicationMenu {

    /**
     * Отображает главное меню приложения.
     */
    static void mainMenu() {
        System.out.println("""
                === ГЛАВНОЕ МЕНЮ ===
                Выберите с какими данными будем работать:
                1.Книги
                2.Машины
                3.Корнеплоды
                0.Выход""");
    }

    /**
     * Отображает меню выбора способа заполнения данных.
     */
     static void dataWriterMenu() {
        System.out.println("""
                === ВЫБОР СПОСОБА ЗАПОЛНЕНИЯ ===
                Как будем заполнять данные?
                1.Рандомное заполнение данных
                2.Заполнение вручную вручную
                3.Заполнение данных из файла
                0.Назад""");
    }

    /**
     * Отображает меню работы с данными для конкретного типа.
     */
    static void dataWorkMenu() {
        System.out.println("""
                === РАБОТА С ДАННЫМИ (%s) ===
                1.Отсортировать данные
                2.Поиск книги
                0.Назад""");
    }

    /**
     * Отображает сообщение о неверном выборе.
     */
    public static void showInvalidChoiceMessage() {
        System.out.println("Неверный выбор! Пожалуйста, введите число из предложенных вариантов.");
    }

    /**
     * Отображает сообщение о выходе из приложения.
     */
    public static void showExitMessage() {
        System.out.println("Завершение работы приложения");
    }

    /**
     * Отображает сообщение о возврате в предыдущее меню.
     */
    public static void showBackMessage() {
        System.out.println("Возврат в предыдущее меню...");
    }

    /**
     * Отображает заголовок для работы с конкретным типом данных.
     *
     * @param dataType тип данных для отображения в заголовке
     */
    public static void showDataTypeHeader(String dataType) {
        System.out.println("=== РАБОТА С " + dataType.toUpperCase() + " ===");
    }

    /**
     * Отображает меню опций записи в файл.
     */
    public static void showFileWriteMenu() {
        System.out.println("""
            === ОПЦИИ ЗАПИСИ В ФАЙЛ ===
            1. Записать результаты в новый файл
            2. Добавить результаты к существующему файлу
            0. Не записывать в файл""");
    }

    /**
     * Отображает сообщение об успешной записи в файл.
     *
     * @param filename имя файла
     */
    public static void showFileWriteSuccess(String filename) {
        System.out.println("Результаты успешно сохранены в файл: " + filename);
    }

    /**
     * Отображает сообщение об ошибке записи в файл.
     *
     * @param filename имя файла
     * @param errorMessage сообщение об ошибке
     */
    public static void showFileWriteError(String filename, String errorMessage) {
        System.out.println("Ошибка записи в файл '" + filename + "': " + errorMessage);
    }

}
