package com.kurilo.array.warehouse;

import com.kurilo.array.entity.ArrayStatistics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayWarehouseTest {
    private ArrayWarehouse warehouse;

    @BeforeEach
    void setUp() {
        warehouse = ArrayWarehouse.getInstance();
    }

    @Test
    @DisplayName("Сохранение и удаление статистики")
    void putAndRemoveTest() {
        long id = 1L;
        ArrayStatistics stats = new ArrayStatistics(1, 10, 50, 5.0);

        assertAll("Работа с Warehouse",
                () -> assertDoesNotThrow(() -> warehouse.put(id, stats)),
                () -> assertDoesNotThrow(() -> warehouse.remove(id))
        );
    }
}