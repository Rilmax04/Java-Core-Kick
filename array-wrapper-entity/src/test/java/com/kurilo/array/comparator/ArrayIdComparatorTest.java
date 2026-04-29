package com.kurilo.array.comparator;

import com.kurilo.array.entity.IntArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayIdComparatorTest {
    private ArrayIdComparator comparator;

    @BeforeEach
    void setUp() {
        comparator = new ArrayIdComparator();
    }

    @Test
    @DisplayName("Сравнение массивов по ID")
    void compareIdsTest() {
        IntArray firstArray = new IntArray(new int[]{1, 2});
        IntArray secondArray = new IntArray(new int[]{3, 4});

        assertAll("Проверка ID компаратора",
                () -> assertTrue(comparator.compare(firstArray, secondArray) < 0, "Первый ID должен быть меньше"),
                () -> assertTrue(comparator.compare(secondArray, firstArray) > 0, "Второй ID должен быть больше"),
                () -> assertEquals(0, comparator.compare(firstArray, firstArray), "ID должны быть равны")
        );
    }
}