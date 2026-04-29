package com.kurilo.array.reader.impl;

import com.kurilo.array.exception.ArrayDataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DataReaderImplTest {
    private DataReaderImpl reader;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        reader = new DataReaderImpl();
    }

    static Stream<Arguments> invalidPathProvider() {
        return Stream.of(
                Arguments.of((String) null),
                Arguments.of("  "),
                Arguments.of("non_existent_file.txt")
        );
    }

    @ParameterizedTest
    @MethodSource("invalidPathProvider")
    @DisplayName("Исключения при невалидных путях к файлу")
    void readAllLinesFromFileExceptionTest(String path) {
        assertThrows(ArrayDataException.class, () -> reader.readAllLinesFromFile(path));
    }

    @Test
    @DisplayName("Успешное чтение строк из файла")
    void readAllLinesFromFileSuccessTest() throws IOException, ArrayDataException {
        Path file = tempDir.resolve("test.txt");
        List<String> expected = List.of("1, 2, 3", "4 5 6");
        Files.write(file, expected);

        List<String> actual = reader.readAllLinesFromFile(file.toString());

        assertAll("Проверка прочитанных данных",
                () -> assertEquals(expected.size(), actual.size(), "Количество строк не совпадает"),
                () -> assertEquals(expected, actual, "Содержимое строк не совпадает")
        );
    }
}