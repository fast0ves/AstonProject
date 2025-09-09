package thread;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Сервис для записи данных в файлы.
 * Поддерживает режим добавления данных к существующему содержимому файла.
 */
public class FileWriterService {

    /**
     * Записывает список объектов в файл.
     * Каждый объект записывается в отдельной строке через toString().
     *
     * @param <T> тип объектов в списке
     * @param data список данных для записи
     * @param filename имя файла для записи
     * @param append true - добавлять к существующему файлу, false - перезаписывать
     */
    public static <T> void writeToFile(List<T> data, String filename, boolean append) {
        if (data == null || filename == null || filename.trim().isEmpty()) {
            System.out.println("Некорректные параметры для записи в файл");
            return;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, append))) {
            for (T item : data) {
                writer.println(item.toString());
            }
            System.out.println("Данные успешно записаны в файл: " + filename);
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл '" + filename + "': " + e.getMessage());
        }
    }

    /**
     * Записывает один объект в файл.
     *
     * @param <T> тип объекта
     * @param item объект для записи
     * @param filename имя файла для записи
     * @param append true - добавлять к существующему файлу, false - перезаписывать
     */
    public static <T> void writeToFile(T item, String filename, boolean append) {
        if (item == null || filename == null || filename.trim().isEmpty()) {
            System.out.println("Некорректные параметры для записи в файл");
            return;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, append))) {
            writer.println(item.toString());
            System.out.println("Данные успешно записаны в файл: " + filename);
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл '" + filename + "': " + e.getMessage());
        }
    }

    /**
     * Записывает строку в файл.
     *
     * @param content строка для записи
     * @param filename имя файла для записи
     * @param append true - добавлять к существующему файлу, false - перезаписывать
     */
    public static void writeStringToFile(String content, String filename, boolean append) {
        if (content == null || filename == null || filename.trim().isEmpty()) {
            System.out.println("Некорректные параметры для записи в файл");
            return;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, append))) {
            writer.println(content);
            System.out.println("Данные успешно записаны в файл: " + filename);
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл '" + filename + "': " + e.getMessage());
        }
    }
}