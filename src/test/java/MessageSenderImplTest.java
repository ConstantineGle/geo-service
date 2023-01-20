import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

public class MessageSenderImplTest {
    @Test
    public void sendRu() {
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(Mockito.startsWith("172")))
                .thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));
        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");
        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        String expected = "Добро пожаловать";
        Map<String, String> user = new HashMap<>();
        user.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.1.2.3");
        String actual = messageSender.send(user);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void sendEng() {
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(Mockito.startsWith("96")))
                .thenReturn(new Location("New York", Country.USA, "10th Avenue", 32));
        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");
        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        String expected = "Welcome";
        Map<String, String> user = new HashMap<>();
        user.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.1.2.3");
        String actual = messageSender.send(user);
        Assertions.assertEquals(expected, actual);
    }


}
