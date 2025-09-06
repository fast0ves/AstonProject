package application;

public class ApplicationMenu {

    static void mainMenu() {
        System.out.println("""
                \\n=== ГЛАВНОЕ МЕНЮ ===
                Выберите с какими данными будем работать:
                1.Книги
                2.Машины
                3.Корнеплоды
                0.Выход""");
    }

     static void dataWriterMenu() {
        System.out.println("""
                \\n=== ВЫБОР СПОСОБА ЗАПОЛНЕНИЯ ===
                Как будем заполнять данные?
                1.Рандомное заполнение данных
                2.Заполнение вручную вручную
                3.Заполнение данных из файла
                0.Назад""");
    }

    static void dataWorkMenu() {
        System.out.println("""
                \\n=== РАБОТА С ДАННЫМИ (%s) ===
                1.Отсортировать данные
                2.Поиск книги
                0.Назад""");
    }

    public static void showInvalidChoiceMessage() {
        System.out.println("Неверный выбор! Пожалуйста, введите число из предложенных вариантов.");
    }

    public static void showExitMessage() {
        System.out.println("Завершение работы приложения");
    }

    public static void showBackMessage() {
        System.out.println("Возврат в предыдущее меню...");
    }

    public static void showDataTypeHeader(String dataType) {
        System.out.println("\n=== РАБОТА С " + dataType.toUpperCase() + " ===");
    }

}
