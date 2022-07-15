package Assignment2;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasKey;
public class NewTest {
	@Test
    public void Assignment1(){
        //Print the request
        Response response = given().contentType(ContentType.JSON).log().all().when()
                .get("https://restful-booker.herokuapp.com/booking").then().extract().response();
        //Verify the status code of the request.
        Assert.assertEquals(response.getStatusCode(),200);
//        Print the response body
        System.out.println("Response body : "+response.getBody().prettyPrint());
        List<Integer> ids = response.jsonPath().getList("bookingid");
//        Print the different item values
        System.out.println("Size is : "+ids.size());
        for(Integer id:ids) {
            System.out.println(id);
        }
    }
	@Test
    void Assignment2(){
        Response response = given().pathParam("id","87").contentType(ContentType.JSON).log().all().when()
                .get("https://restful-booker.herokuapp.com/booking/{id}").then().extract().response();
        System.out.println(response.getStatusCode());
        System.out.println(response.prettyPrint());
        JsonPath jsonObject = response.jsonPath();
        //print first name
        System.out.println(jsonObject.get("firstname").toString());
        //print last name
        System.out.println(jsonObject.get("lastname").toString());
        //print deposit paid
        System.out.println(jsonObject.get("depositpaid").toString());
        //print check in date
        System.out.println(jsonObject.get("bookingdates.checkin").toString());
        Map<String, String> map = jsonObject.get();
        System.out.println(map);
        assertThat(map, hasKey("firstname"));
    }
}
