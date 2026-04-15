package com.kurilo.array.service;

import com.kurilo.array.entity.IntArray;
import com.kurilo.array.exception.ArrayDataException;

import java.util.OptionalDouble;
import java.util.OptionalInt;

public interface ArrayStatisticsService {
    OptionalInt findMin(IntArray array) throws ArrayDataException;
    OptionalInt findMax(IntArray array) throws ArrayDataException;
    OptionalInt sum(IntArray array) throws ArrayDataException;
    OptionalDouble average(IntArray array) throws ArrayDataException;
}
