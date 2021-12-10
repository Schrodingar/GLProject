import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class SearcherTest {
    Searcher searcher = new Searcher();

    @ParameterizedTest
    @MethodSource("countObjectsWithSpecificFieldOnlyDataProvider")
    public void method1(String expected, String param1, String param2) {
        assertEquals(expected, searcher.countObjectsWithSpecificFieldOnly(param1, param2));
    }

    public static Stream<Arguments> countObjectsWithSpecificFieldOnlyDataProvider() {
        return Stream.of(
                arguments("found 3 objects with field \"address1\"", "tests\\searcherTests\\JSON", "address1")
        );
    }
}