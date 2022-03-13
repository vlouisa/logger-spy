package dev.louisa;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import java.util.List;

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
        return matcher.matches(listAppender.list);       
    }

    public static class ExactMatcher implements LoggerSpyMatcher {
        private final String value;

        private ExactMatcher(String value) {
            this.value = value;
        }
        
        public static ExactMatcher equalTo(String value) {
            return new ExactMatcher(value);
        } 

        @Override
        public boolean matches(List<ILoggingEvent> events) {
            return events.stream()
                    .anyMatch(event -> event.getFormattedMessage().equals(value));
        }
    }

    public static class StartsWithMatcher implements LoggerSpyMatcher {
        private final String value;

        private StartsWithMatcher(String value) {
            this.value = value;
        }
        
        public static StartsWithMatcher startsWith(String value) {
            return new StartsWithMatcher(value);
        } 

        @Override
        public boolean matches(List<ILoggingEvent> events) {
            return events.stream()
                    .anyMatch(event -> event.getFormattedMessage().startsWith(value));
        }
    }
}
