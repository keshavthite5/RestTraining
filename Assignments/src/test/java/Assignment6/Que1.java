package Assignment6;

import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.specification.RequestSpecification;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
public class Que1 {
	RequestSpecification reqSpec;

    @BeforeClass
    void beforeClass(){
        reqSpec = new RequestSpecBuilder().setBaseUri("http://localhost:3000").
                build();
    }
    @Test
    void jsonSchemaValidation() throws IOException, ParseException {
        String response = given().contentType(ContentType.JSON).spec(reqSpec)
                .get("/friends/23").andReturn().asString();
        System.out.println(response);
        assertThat(response, JsonSchemaValidator.matchesJsonSchemaInClasspath("friendsJsonSchema.json"));
    }
}
