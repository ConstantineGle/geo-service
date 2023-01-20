import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

import java.util.stream.Stream;

public class GeoServiceImplTest {

    @ParameterizedTest
    @MethodSource("Parameters")
    public void byIp(String ip, Country expected) {

        GeoServiceImpl geoService = new GeoServiceImpl();

        Location resultLocation = geoService.byIp(ip);

        Assertions.assertEquals(expected, resultLocation != null ? resultLocation.getCountry() : null);

    }

    private static Stream<Arguments> Parameters() {
        return Stream.of(
                Arguments.of("172.1.2.3", Country.RUSSIA),
                Arguments.of("96.1.2.3", Country.USA),
                Arguments.of("69.1.2.3", null)
        );
    }

}
