package dev.louisa;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import static ch.qos.logback.classic.Level.INFO;

@Slf4j
public class LoggerSpy {
    private final ListAppender<ILoggingEvent> listAppender;

    public LoggerSpy(Class<?> loggedClass) {
        Logger logger = (Logger) LoggerFactory.getLogger(loggedClass);
        logger.setLevel(INFO);
        listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);
    }

    public boolean containsMessage(LoggerSpyMatcher matcher) {
        return matcher.test(listAppender.list);
    }
}
