package dev.louisa;

import ch.qos.logback.classic.spi.ILoggingEvent;

import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public interface LoggerSpyMatcher extends Predicate<List<ILoggingEvent>> {

    interface MatchPredicate extends Predicate<ILoggingEvent> {

        static MatchPredicate equalTo(String value) {
            return event -> event.getFormattedMessage().equals(value);
        }

        static MatchPredicate startsWith(String value) {
            return event -> event.getFormattedMessage().startsWith(value);
        }

        static MatchPredicate matches(Pattern pattern) {
            return event -> pattern.matcher(event.getFormattedMessage()).matches();
        }
    }
}
