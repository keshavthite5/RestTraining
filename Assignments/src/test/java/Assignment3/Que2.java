package Assignment3;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class Que2 {
	 SoftAssert softAssert = new SoftAssert();
	    @Test
	     void postCallSimple(){
	         JSONObject obj=new JSONObject();
	         obj.put("firstname", "Keshav");
	         obj.put("lastname", "Thite");
	         obj.put("id", 77);
	         obj.put( "age", 25);
	         Response response = given().
	                 contentType(ContentType.JSON).log().all().when().body(obj.toString()).post
	                 ("http://localhost:3000/friends").then().extract().response();
	         System.out.println(response.getStatusCode());
	         System.out.println(response.prettyPrint());
	}
	    @Test
	    void postCallMedium(){
	        Random random = new Random();
	        // Generates random integers 0 to 4999
	        int id = random.nextInt(5000);
	        JSONObject obj=new JSONObject();
	        obj.put("firstname", "ABC");
	        obj.put("lastname", "XYZ");
	        JSONObject addressObj = new JSONObject();
	        addressObj.put("streetName", "Blue ridge");
	        addressObj.put("houseNo", "7");
	        obj.put("address",addressObj);
	        obj.put("id", id);
	        obj.put( "age", 25);
	        Response response = given().
	                contentType(ContentType.JSON).log().all().when().body(obj.toString()).post
	                        ("http://localhost:3000/friends").then().extract().response();
	        System.out.println(response.getStatusCode());
	        softAssert.assertEquals(response.getStatusCode(),201);
	        Response response2 = given().pathParam("id",id).
	                contentType(ContentType.JSON).log().all().when().get
	                        ("http://localhost:3000/friends/{id}").then().extract().response();
	        Assert.assertEquals(response2.jsonPath().get("firstname"),((String) obj.get("firstname")));
	        softAssert.assertAll();
	    }

	    @Test
	    public void friendJsonFile_Test() {
	        File file = new File("./src/test/resources/friends.json");

	        given().
	                contentType(ContentType.JSON).
	                body(file).
	                when().
	                post("http://localhost:3000/friends").
	                then().
	                assertThat().
	                statusCode(201);
	    }
}
