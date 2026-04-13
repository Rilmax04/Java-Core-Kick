package by.kurilo.array.reader.impl;

import by.kurilo.array.exception.ArrayDataException;
import by.kurilo.array.reader.ReadFile;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TextFileReaderTest {

    private final ReadFile reader = new TextFileReader();

    @TempDir
    Path tempDir;

    @Test
    void readAllLinesShouldReturnListOfLines() throws Exception {
        Path file = tempDir.resolve("test.txt");
        Files.write(file, List.of("first line", "second line", "third line"));
        List<String> lines = reader.readAllLines(file.toString());
        assertEquals(3, lines.size());
        assertEquals("first line", lines.get(0));
        assertEquals("second line", lines.get(1));
        assertEquals("third line", lines.get(2));
    }

    @Test
    void readAllLinesShouldThrowArrayDataExceptionWhenFileNotFound() {
        Path nonExistent = tempDir.resolve("missing.txt");
        assertThrows(ArrayDataException.class, () -> reader.readAllLines(nonExistent.toString()));
    }

    @Test
    void readAllLinesShouldHandleEmptyFile() throws Exception {
        Path emptyFile = tempDir.resolve("empty.txt");
        Files.createFile(emptyFile);
        List<String> lines = reader.readAllLines(emptyFile.toString());
        assertTrue(lines.isEmpty());
    }

    @Test
    void readAllLinesShouldHandleTrailingNewlines() throws Exception {
        Path file = tempDir.resolve("trailing.txt");
        Files.write(file, List.of("line1", "line2", ""));
        List<String> lines = reader.readAllLines(file.toString());
        assertEquals(3, lines.size());
    }
}