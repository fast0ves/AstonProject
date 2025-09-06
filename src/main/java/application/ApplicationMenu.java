package application;

public class ApplicationMenu {

    static void mainMenu() {
        System.out.println("""
                Выберите с какими данными будем работать:
                1.Книги
                2.Машины
                3.Корнеплоды
                0.Выход""");
    }

     static void dataWriterMenu() {
        System.out.println("""
                Как будем заполнять данные?
                1.Рандомное заполнение данных
                2.Заполнение вручную вручную
                3.Заполнение данных из файла
                0.Назад""");
    }

    static void dataWorkMenu() {
        System.out.println("""
                1.Отсортировать данные
                2.Поиск книги
                0.Назад""");
    }

}
