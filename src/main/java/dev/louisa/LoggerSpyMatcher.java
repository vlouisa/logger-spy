package dev.louisa;

import ch.qos.logback.classic.spi.ILoggingEvent;

import java.util.List;

public interface LoggerSpyMatcher {
    boolean matches(List<ILoggingEvent> events);
}
