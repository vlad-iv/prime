package app.prime.log;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.WriterAppender;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Check logging annotation.
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = LoggingAspectTest.TestConfig.class)
public class LoggingAspectTest {
    @Configuration
    @EnableAspectJAutoProxy
    @ComponentScan(lazyInit = true, value = {"app.prime.log"})
    public static class TestConfig {
    }

    @Autowired
    LoggingService loggingService;

    @Test
    public void logAroundLoggedMethod() {
        Assert.assertNotEquals("Proxy class", LoggingService.class, loggingService.getClass());
        final LoggerContext context = LoggerContext.getContext(false);
        final org.apache.logging.log4j.core.config.Configuration config = context.getConfiguration();
        final PatternLayout layout = PatternLayout.createDefaultLayout(config);
        final String loggerName = "test-log";
        final StringWriter sw = new StringWriter();
        final PrintWriter w = new PrintWriter(sw);
        final Appender appender = WriterAppender.createAppender(layout, null, w, loggerName, false, true);
        appender.start();
        config.addAppender(appender);
        updateLoggers(appender, config);

        loggingService.getLog();
        loggingService.getNonLog();

        removeLoggers(loggerName, config);
        appender.stop();
        context.updateLoggers();

        String logResult = sw.toString();
        assertTrue("Contain Log method", logResult.contains("Call: LoggingService.getLog"));
        assertFalse("Not contain NonLog method", logResult.contains("Call: LoggingService.getNonLog"));
    }

    private void updateLoggers(final Appender appender, final org.apache.logging.log4j.core.config.Configuration config) {
        final Level level = Level.INFO;
        final Filter filter = null;
        for (final LoggerConfig loggerConfig : config.getLoggers().values()) {
            loggerConfig.addAppender(appender, level, filter);
        }
        config.getRootLogger().addAppender(appender, level, filter);
    }

    private void removeLoggers(final String writerName, final org.apache.logging.log4j.core.config.Configuration config) {
        for (final LoggerConfig loggerConfig : config.getLoggers().values()) {
            loggerConfig.removeAppender(writerName);
        }
        config.getRootLogger().removeAppender(writerName);
    }

}
