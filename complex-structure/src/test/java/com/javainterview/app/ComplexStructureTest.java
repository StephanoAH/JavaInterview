package com.javainterview.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ComplexStructureTest {

    @ParameterizedTest 
    @MethodSource("provideStreamArgumentsForTesting")
    public void ShouldReturnDuplicateElements(List<String> elements, List<String> expectedDuplicateElements) {
        List<String> duplicateElements = ComplexStructure.findDuplicateProducts(elements);
        assertEquals(expectedDuplicateElements, duplicateElements);
    }

    static Stream<Arguments> provideStreamArgumentsForTesting() {
        return Stream.of(
            Arguments.of(null, Arrays.asList()),
            Arguments.of(Arrays.asList(), Arrays.asList()),
            Arguments.of(Arrays.asList("A", "B", "C"), Arrays.asList()),
            Arguments.of(Arrays.asList("A", "A"), Arrays.asList("A")),
            Arguments.of(Arrays.asList("A", "B", "A", "B"), Arrays.asList("A", "B")),
            Arguments.of(Arrays.asList("A", "A", "A"), Arrays.asList("A")),
            Arguments.of(Arrays.asList("A", null, "", "A"), Arrays.asList("A")),
            Arguments.of(Arrays.asList("a", "B", "a"), Arrays.asList("a"))
        );
    }
}
