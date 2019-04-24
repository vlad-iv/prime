package app.prime;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Select cached version.
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface WithCache {
}
