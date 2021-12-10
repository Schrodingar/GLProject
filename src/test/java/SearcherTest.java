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
    public void testCountObjectsWithSpecificFieldOnly(String expected, String param1, String param2) {
        assertEquals(expected, searcher.countObjectsWithSpecificFieldOnly(param1, param2));
    }

    public static Stream<Arguments> countObjectsWithSpecificFieldOnlyDataProvider() {
        return Stream.of(
                arguments("found 3 objects with field \"address1\"", "src/test/java/JSON", "address1"),
                arguments("found 0 objects with field \"idCode\"", "src/test/java/JSON", "idCode"),
                arguments("found 1 objects with field \"refCode\"", "src/test/java/JSON", "refCode")
        );
    }

    @ParameterizedTest
    @MethodSource("countObjectsWithSpecificFieldAndSpecificValueDataProvider")
    public void testCountObjectsWithSpecificFieldAndSpecificValue(String expected, String param1, String param2, String param3) {
        assertEquals(expected, searcher.countObjectsWithSpecificFieldAndSpecificValue(param1, param2, param3));
    }

    public static Stream<Arguments> countObjectsWithSpecificFieldAndSpecificValueDataProvider() {
        return Stream.of(
                arguments("found 3 objects where field \"weight\" equals \"229\"", "src/test/java/JSON", "weight", "229"),
                arguments("found 2 objects where field \"desc\" equals \"Driver Count Required\"", "src/test/java/JSON", "desc", "Driver Count Required"),
                arguments("found 5 objects where field \"contactFunctionCode\" equals \"CN\"", "src/test/java/JSON", "contactFunctionCode", "CN")
        );
    }

}