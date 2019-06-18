package app.prime.cache;

import lombok.extern.log4j.Log4j2;
import org.jooq.lambda.Unchecked;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Add cacheable proxy to bean annotated with cache.
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@Log4j2
@Component
public class CacheBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if (bean.getClass().isAnnotationPresent(WithCache.class)) {
            log.debug("Wrapping with cache " + bean);
            return proxy(bean);
        }
        return bean;
    }

    private static Object proxy(Object bean) {
        Callback callback = new MethodInterceptor() {
            private final Map<Object, Object> cache = new ConcurrentHashMap<>();

            private List<Object> getCachekey(Method method, Object[] args) {
                List<Object> list = new ArrayList<>();
                list.add(method.getName());
                list.addAll(Arrays.asList(args));
                return list;
            }

            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) {
                List<Object> key = getCachekey(method, args);
                log.debug("Intercepted: " + method.getName());
                return cache.computeIfAbsent(key, Unchecked.function(ignored -> {
                    log.info("Calculating {}.{}({})", bean.getClass().getSimpleName(), method.getName(), Arrays.toString(args));
                    long start = System.nanoTime();
                    Object invoke = method.invoke(bean, args);
                    long finish = System.nanoTime();
                    log.info("Calculating {}.{}({}) {} ms", bean.getClass().getSimpleName(), method.getName(), Arrays.toString(args), (finish - start) / 1_000L);
                    return invoke;
                }));
            }
        };
        return Enhancer.create(bean.getClass(), callback);
    }

}
