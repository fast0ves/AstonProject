import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import thread.FileWriterService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileWriterServiceTest {

    @TempDir
    Path tempDir;

    @Test
    void testWriteListToFile_AppendMode() throws IOException {

        Path testFile = tempDir.resolve("test_append.txt");
        List<String> data1 = Arrays.asList("Line 1", "Line 2", "Line 3");
        List<String> data2 = Arrays.asList("Line 4", "Line 5");

        FileWriterService.writeToFile(data1, testFile.toString(), false);
        FileWriterService.writeToFile(data2, testFile.toString(), true);

        List<String> lines = Files.readAllLines(testFile);
        assertEquals(5, lines.size());
        assertEquals("Line 1", lines.get(0));
        assertEquals("Line 5", lines.get(4));
    }

    @Test
    void testWriteListToFile_OverwriteMode() throws IOException {
        Path testFile = tempDir.resolve("test_overwrite.txt");
        List<String> data1 = Arrays.asList("First", "Second");
        List<String> data2 = Arrays.asList("Third", "Fourth");

        FileWriterService.writeToFile(data1, testFile.toString(), false);
        FileWriterService.writeToFile(data2, testFile.toString(), false);

        List<String> lines = Files.readAllLines(testFile);
        assertEquals(2, lines.size());
        assertEquals("Third", lines.get(0));
        assertEquals("Fourth", lines.get(1));
    }

    @Test
    void testWriteSingleItemToFile() throws IOException {

        Path testFile = tempDir.resolve("test_single.txt");
        String testItem = "Test Item Content";

        FileWriterService.writeToFile(testItem, testFile.toString(), false);

        List<String> lines = Files.readAllLines(testFile);
        assertEquals(1, lines.size());
        assertEquals("Test Item Content", lines.get(0));
    }


    @Test
    void testWriteStringToFile() throws IOException {
        Path testFile = tempDir.resolve("test_string.txt");
        String content = "Hello, World!";

        FileWriterService.writeStringToFile(content, testFile.toString(), false);

        List<String> lines = Files.readAllLines(testFile);
        assertEquals(1, lines.size());
        assertEquals("Hello, World!", lines.get(0));
    }


    @Test
    void testWriteToFileWithEmptyList() throws IOException {
        Path testFile = tempDir.resolve("test_empty.txt");
        List<String> emptyList = List.of();

        FileWriterService.writeToFile(emptyList, testFile.toString(), false);

        assertTrue(Files.exists(testFile));
        List<String> lines = Files.readAllLines(testFile);
        assertTrue(lines.isEmpty());
    }

    @Test
    void testWriteToFileWithNullData() {
        Path testFile = tempDir.resolve("test_null.txt");

        FileWriterService.writeToFile((List<String>) null, testFile.toString(), false);
    }

    @Test
    void testWriteToFileWithNullFilename() {
        List<String> data = Arrays.asList("test", "data");

        FileWriterService.writeToFile(data, null, false);

        assertDoesNotThrow(() -> FileWriterService.writeToFile(data, null, false));
    }

    @Test
    void testWriteToFileWithEmptyFilename() {
        List<String> data = Arrays.asList("test", "data");

        FileWriterService.writeToFile(data, "", false);

        assertDoesNotThrow(() -> FileWriterService.writeToFile(data, "", false));
    }

    @Test
    void testWriteToFileInNonExistentDirectory() {
        String invalidPath = tempDir.resolve("nonexistent_dir/test.txt").toString();
        List<String> data = Arrays.asList("test", "data");

        FileWriterService.writeToFile(data, invalidPath, false);

        assertDoesNotThrow(() -> FileWriterService.writeToFile(data, invalidPath, false));
    }
}




