import java.time.Clock;
import java.time.Instant;

public class DateTest {
    public static void main(String[] args) {
        final Clock clock = Clock.systemUTC();
        System.out.println(clock.millis());
        System.out.println(Instant.now().getEpochSecond());
    }
}
