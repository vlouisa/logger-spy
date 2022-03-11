package dev.louisa;

import ch.qos.logback.classic.spi.ILoggingEvent;

import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public interface LoggerSpyMatcher extends Predicate<List<ILoggingEvent>> {

    static LoggerSpyMatcher equalTo(String value) {
        return events -> events.stream()
                .anyMatch(event -> event.getFormattedMessage().equals(value));
    }

    static LoggerSpyMatcher startsWith(String value) {
        return events -> events.stream()
                .anyMatch(event -> event.getFormattedMessage().startsWith(value));
    }

    static LoggerSpyMatcher matches(Pattern pattern) {
        return events -> events.stream()
                .anyMatch(event -> pattern.matcher(event.getFormattedMessage()).matches());
    }
}
