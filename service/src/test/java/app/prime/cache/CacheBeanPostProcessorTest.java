package app.prime.cache;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Check cache bean.
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CacheBeanPostProcessorTest.TestConfig.class)
public class CacheBeanPostProcessorTest {
    @Configuration
    @ComponentScan(lazyInit = true, value = {"app.prime.cache"})
    public static class TestConfig {
    }

    @Autowired
    CachedService cachedService;
    @Autowired
    NonCachedService nonCachedService;

    @Test
    public void postProcessBeforeInitialization() {
        Assert.assertNotEquals("Proxy class", CachedService.class, cachedService.getClass());
        assertEquals("Non Proxy class", NonCachedService.class, nonCachedService.getClass());
        long cachedValue = cachedService.countCall();
        assertEquals("Value 2 from cache", cachedValue, cachedService.countCall());
        assertEquals("Value 3 from cache", cachedValue, cachedService.countCall());
        long nonCachedValue = nonCachedService.countCall();
        assertEquals("Value 2 from non cache", nonCachedValue + 1, nonCachedService.countCall());
        assertEquals("Value 3 from non cache", nonCachedValue + 2, nonCachedService.countCall());
    }

}
