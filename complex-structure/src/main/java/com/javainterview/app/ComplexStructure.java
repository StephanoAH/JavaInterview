package com.javainterview.app;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class ComplexStructure {
    public static List<String> findDuplicateProducts(List<String> productIds) {
        if (productIds == null || productIds.isEmpty()) {
            return Collections.emptyList();
        }

        Set<String> duplicateProductIds = new HashSet<>();
        return productIds.stream().filter(Objects::nonNull).filter(p -> !duplicateProductIds.add(p)).distinct().collect(Collectors.toList());
    }
}
