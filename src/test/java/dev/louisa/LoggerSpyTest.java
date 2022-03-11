package dev.louisa;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static dev.louisa.LoggerSpyMatcher.equalTo;
import static dev.louisa.LoggerSpyMatcher.startsWith;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class LoggerSpyTest {

    private LoggerSpy loggerSpy;

    @BeforeEach
    void setUp() {
        loggerSpy = new LoggerSpy(LoggerSpyTest.class);
    }

    @Test
    void should_inform_that_a_line_has_been_logged() {
        log.info("Start task");  
        assertThat(loggerSpy.containsMessage(equalTo("Start task"))).isTrue();    
    }

    @Test
    void should_inform_that_a_line_has_not_been_logged() {
        log.info("Start task");  
        assertThat(loggerSpy.containsMessage(equalTo("Stop task"))).isFalse();    
    }

    @Test
    void should_inform_that_a_line_that_starts_with_a_given_string_has_been_logged() {
        log.info("Start task");  
        assertThat(loggerSpy.containsMessage(startsWith("Start"))).isTrue();    
    }

    @Test
    void should_inform_that_a_line_that_starts_with_a_given_string_has_not_been_logged() {
        log.info("Start task");  
        assertThat(loggerSpy.containsMessage(startsWith("Stop"))).isFalse();    
    }

    @Test
    void should_inform_that_a_line_that_complies_to_a_regex_has_been_logged() {
        log.info("Start task #13");  
        
        Pattern pattern = Pattern.compile("Start task #[0-9]{2}"); 
        assertThat(loggerSpy.containsMessage(LoggerSpyMatcher.matches(pattern))).isTrue();    
    }

    @Test
    void should_inform_that_no_line_has_been_looged_that_complies_to_a_given_regex() {
        log.info("Start task Nr. 13");  
        
        Pattern pattern = Pattern.compile("Start task #[0-9]{2}"); 
        assertThat(loggerSpy.containsMessage(LoggerSpyMatcher.matches(pattern))).isFalse();    
    }
}
