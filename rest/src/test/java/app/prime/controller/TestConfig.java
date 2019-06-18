package app.prime.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Test configuration with lazy bean init.
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@Configuration
@ComponentScan(lazyInit = true, value = {"app.prime.controller", "app.prime.service", "app.prime.cache", "app.prime.log"})
public class TestConfig {
}
