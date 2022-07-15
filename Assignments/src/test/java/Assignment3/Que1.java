package Assignment3;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
public class Que1 {
	 @Test(dataProvider = "postDetails")
	    void day3Assignment1(int id, String name){
	        Response response = given().
	            pathParam("id",id).
	        contentType(ContentType.JSON).log().all().when()
	                .get("http://localhost:3000/posts/{id}").then().extract().response();
	        Assert.assertEquals(response.getStatusCode(),200);
	        JsonPath object =response.jsonPath();
	        assertThat(object.get("author"),equalToIgnoringCase(name));
	        System.out.println(response.prettyPrint());
	    }


	    @Test(dataProvider = "commentDetails")
	    void day3Assignment2(int id, String body,int postId){
	        Response response = given().
	                pathParam("id",id).
	                contentType(ContentType.JSON).log().all().when()
	                .get("http://localhost:3000/comments/{id}").then().extract().response();
	        Assert.assertEquals(response.getStatusCode(),200);
	        JsonPath object =response.jsonPath();
	        assertThat(object.get("body"),equalToIgnoringCase(body));
	        System.out.println(response.prettyPrint());
	    }
	    @DataProvider
	    public static Object[][] postDetails(){
	        return new Object[][]{
	                {1,"Sachin"},
	                {2,"Sachin F"},
	                {3,"Mahesh"},
	                {4,"Anirudha"}
	        };
	    }
	    @DataProvider
	    public static Object[][] commentDetails(){
	        return new Object[][]{
	                {1,"some comment globant 1", 1},
	                {2,"some comment globant 2", 2},
	                {3,"some comment globant 3", 3},
	                {4,"some comment globant 4", 4},
	        };
	    }
}
