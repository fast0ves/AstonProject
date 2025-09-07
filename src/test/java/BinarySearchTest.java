import algorithm.BinarySearch;
import algorithm.MergeSort;
import entity.Book;
import entity.RootVegetable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTest {
    private MergeSort mergeSort = new MergeSort();
    private BinarySearch binarySearch = new BinarySearch();
    private List data = new ArrayList();

    @Test
    public void emptyList() {
        List emptyList = new ArrayList();
        Book book = new Book.BookBuilder()
                .setQuantityPage(671)
                .setTitle("Преступление и наказание")
                .setAuthor("Фёдор Достоевский")
                .build();

        int result = binarySearch.search(emptyList, book, Book.getComparator());

        Assertions.assertEquals(-1, result);
    }

    @Test
    public void searchInMiddle() {
        RootVegetable rootVegetable = new RootVegetable.RootVegetableBuilder()
                .setType("Морковь")
                .setColor("оранжевый")
                .setWeight(150)
                .build();
        RootVegetable rootVegetable1 = new RootVegetable.RootVegetableBuilder()
                .setType("Свекла")
                .setColor("бордовый")
                .setWeight(300)
                .build();
        RootVegetable rootVegetable2 = new RootVegetable.RootVegetableBuilder()
                .setType("Редис")
                .setColor("красный")
                .setWeight(20)
                .build();
        RootVegetable rootVegetable3 = new RootVegetable.RootVegetableBuilder()
                .setType("Репа")
                .setColor("белый")
                .setWeight(200)
                .build();
        RootVegetable rootVegetable4 = new RootVegetable.RootVegetableBuilder()
                .setType("Хрен")
                .setColor("белый")
                .setWeight(200)
                .build();
        data.add(rootVegetable);
        data.add(rootVegetable1);
        data.add(rootVegetable2);
        data.add(rootVegetable3);
        data.add(rootVegetable4);

        mergeSort.sort(data, RootVegetable.getComparator());
        int result = binarySearch.search(data, rootVegetable3, RootVegetable.getComparator());

        Assertions.assertEquals(2, result);

    }

    @Test
    public void searchFirst() {
        RootVegetable rootVegetable = new RootVegetable.RootVegetableBuilder()
                .setType("Морковь")
                .setColor("оранжевый")
                .setWeight(150)
                .build();
        RootVegetable rootVegetable1 = new RootVegetable.RootVegetableBuilder()
                .setType("Свекла")
                .setColor("бордовый")
                .setWeight(300)
                .build();
        RootVegetable rootVegetable2 = new RootVegetable.RootVegetableBuilder()
                .setType("Редис")
                .setColor("красный")
                .setWeight(20)
                .build();
        RootVegetable rootVegetable3 = new RootVegetable.RootVegetableBuilder()
                .setType("Репа")
                .setColor("белый")
                .setWeight(200)
                .build();
        RootVegetable rootVegetable4 = new RootVegetable.RootVegetableBuilder()
                .setType("Хрен")
                .setColor("белый")
                .setWeight(200)
                .build();
        data.add(rootVegetable);
        data.add(rootVegetable1);
        data.add(rootVegetable2);
        data.add(rootVegetable3);
        data.add(rootVegetable4);

        mergeSort.sort(data, RootVegetable.getComparator());
        int result = binarySearch.search(data, rootVegetable, RootVegetable.getComparator());

        Assertions.assertEquals(0, result);

    }

    @Test
    public void searchLast() {
        RootVegetable rootVegetable = new RootVegetable.RootVegetableBuilder()
                .setType("Морковь")
                .setColor("оранжевый")
                .setWeight(150)
                .build();
        RootVegetable rootVegetable1 = new RootVegetable.RootVegetableBuilder()
                .setType("Свекла")
                .setColor("бордовый")
                .setWeight(300)
                .build();
        RootVegetable rootVegetable2 = new RootVegetable.RootVegetableBuilder()
                .setType("Редис")
                .setColor("красный")
                .setWeight(20)
                .build();
        RootVegetable rootVegetable3 = new RootVegetable.RootVegetableBuilder()
                .setType("Репа")
                .setColor("белый")
                .setWeight(200)
                .build();
        RootVegetable rootVegetable4 = new RootVegetable.RootVegetableBuilder()
                .setType("Хрен")
                .setColor("белый")
                .setWeight(200)
                .build();
        data.add(rootVegetable);
        data.add(rootVegetable1);
        data.add(rootVegetable2);
        data.add(rootVegetable3);
        data.add(rootVegetable4);

        mergeSort.sort(data, RootVegetable.getComparator());
        int result = binarySearch.search(data, rootVegetable4, RootVegetable.getComparator());

        Assertions.assertEquals(4, result);

    }

    @Test
    public void searchNotExistElement() {
        RootVegetable rootVegetable = new RootVegetable.RootVegetableBuilder()
                .setType("Морковь")
                .setColor("оранжевый")
                .setWeight(150)
                .build();
        RootVegetable rootVegetable1 = new RootVegetable.RootVegetableBuilder()
                .setType("Свекла")
                .setColor("бордовый")
                .setWeight(300)
                .build();
        RootVegetable rootVegetable2 = new RootVegetable.RootVegetableBuilder()
                .setType("Редис")
                .setColor("красный")
                .setWeight(20)
                .build();
        RootVegetable rootVegetable3 = new RootVegetable.RootVegetableBuilder()
                .setType("Репа")
                .setColor("белый")
                .setWeight(200)
                .build();
        RootVegetable rootVegetable4 = new RootVegetable.RootVegetableBuilder()
                .setType("Хрен")
                .setColor("белый")
                .setWeight(200)
                .build();
        data.add(rootVegetable);
        data.add(rootVegetable1);
        data.add(rootVegetable2);
        data.add(rootVegetable4);

        mergeSort.sort(data, RootVegetable.getComparator());
        int result = binarySearch.search(data, rootVegetable3, RootVegetable.getComparator());

        Assertions.assertEquals(-1, result);

    }

    @Test
    public void nullList() {
        data = null;
        RootVegetable rootVegetable1 = new RootVegetable.RootVegetableBuilder()
                .setType("Свекла")
                .setColor("бордовый")
                .setWeight(300)
                .build();
        Assertions.assertThrows(IllegalArgumentException.class, () ->
        {
            binarySearch.search(data, rootVegetable1, RootVegetable.getComparator());
        });
    }

    @Test
    public void nullElement() {
        RootVegetable rootVegetable1 = new RootVegetable.RootVegetableBuilder()
                .setType("Свекла")
                .setColor("бордовый")
                .setWeight(300)
                .build();
        RootVegetable rootVegetable2 = new RootVegetable.RootVegetableBuilder()
                .setType("Редис")
                .setColor("красный")
                .setWeight(20)
                .build();
        RootVegetable rootVegetable = null;
        data.add(rootVegetable);
        data.add(rootVegetable1);
        data.add(rootVegetable2);
        Assertions.assertThrows(IllegalArgumentException.class, () ->
        {
            binarySearch.search(data, rootVegetable, RootVegetable.getComparator());
        });
    }

    @Test
    public void nullComparator() {
        RootVegetable rootVegetable1 = new RootVegetable.RootVegetableBuilder()
                .setType("Свекла")
                .setColor("бордовый")
                .setWeight(300)
                .build();
        RootVegetable rootVegetable2 = new RootVegetable.RootVegetableBuilder()
                .setType("Редис")
                .setColor("красный")
                .setWeight(20)
                .build();
        data.add(rootVegetable1);
        data.add(rootVegetable2);
        Assertions.assertThrows(IllegalArgumentException.class, () ->
        {
            binarySearch.search(data, rootVegetable1, null);
        });
    }
}
