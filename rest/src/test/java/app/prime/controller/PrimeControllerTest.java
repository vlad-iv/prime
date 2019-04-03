package app.prime.controller;

import app.prime.model.PrimeList;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-prime-context.xml")
public class PrimeControllerTest {
    @Autowired
    PrimeController primeController;

    @Before
    public void startUp() throws Exception {
        RestAssuredMockMvc.standaloneSetup(primeController);
    }

    @Test
    public void testIsPrime() {
        given()
            .when()
            .get("/prime/check/-1")
            .then()
            .statusCode(200)
            .body("result", equalTo(false));

        given()
            .when()
            .get("/prime/check/0")
            .then()
            .statusCode(200)
            .body("result", equalTo(false));

        given()
            .when()
            .get("/prime/check/4")
            .then()
            .statusCode(200)
            .body("result", equalTo(false));

        given()
            .when()
            .get("/prime/check/15")
            .then()
            .statusCode(200)
            .body("result", equalTo(false));

        given()
            .when()
            .get("/prime/check/1")
            .then()
            .statusCode(200)
            .body("result", equalTo(true));

        given()
            .when()
            .get("/prime/check/13")
            .then()
            .statusCode(200)
            .body("result", equalTo(true));

    }

    @Test
    public void testFindPrimes() {
        assertThat(given().when().get("/prime/find/-1")
                .as(PrimeList.class).getPrimes(),
            is(Collections.<Long>emptyList()));
        assertThat(given().when().get("/prime/find/0")
            .as(PrimeList.class).getPrimes(), is(Collections.<Long>emptyList()));
        assertThat(given().when().get("/prime/find/1")
            .as(PrimeList.class).getPrimes(), is(Collections.<Long>emptyList()));

        assertThat(given().when().get("/prime/find/2")
            .as(PrimeList.class).getPrimes(), is(singletonList(2L)));
        assertThat(given().when().get("/prime/find/3")
            .as(PrimeList.class).getPrimes(), is(singletonList(3L)));
        assertThat(given().when().get("/prime/find/4")
            .as(PrimeList.class).getPrimes(), is(asList(2L, 2L)));
        assertThat(given().when().get("/prime/find/5")
            .as(PrimeList.class).getPrimes(), is(singletonList(5L)));
        assertThat(given().when().get("/prime/find/6")
            .as(PrimeList.class).getPrimes(), is(asList(2L, 3L)));
        assertThat(given().when().get("/prime/find/7")
            .as(PrimeList.class).getPrimes(), is(singletonList(7L)));
        assertThat(given().when().get("/prime/find/8")
            .as(PrimeList.class).getPrimes(), is(asList(2L, 2L, 2L)));
        assertThat(given().when().get("/prime/find/9")
            .as(PrimeList.class).getPrimes(), is(asList(3L, 3L)));
        assertThat(given().when().get("/prime/find/10")
            .as(PrimeList.class).getPrimes(), is(asList(2L, 5L)));
    }
}
