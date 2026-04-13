package by.kurilo.array.service;

import by.kurilo.array.entity.IntArray;

import java.util.Optional;

public interface ArrayStatisticsService {
    Optional<Integer> findMin(IntArray array);
    Optional<Integer> findMax(IntArray array);
    Optional<Integer> sum(IntArray array);
    Optional<Double> average(IntArray array);
}
