package Assignment4;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
public class Que1 {
	 RequestSpecification reqSpec;
	    ResponseSpecification responseSpecification;
	    SoftAssert softAssert = new SoftAssert();
	    @BeforeClass
	    void beforeClass(){
	        reqSpec= new RequestSpecBuilder().setBaseUri("http://localhost:3000").build();
	        responseSpecification =  new ResponseSpecBuilder().expectStatusCode(200)
	                .expectContentType(ContentType.JSON).build();
	    }


	    @Test(dataProvider = "postDetails")
	    void day4Assignment1(int id, String name){
	         String  author = given().
	                pathParam("id",id).
	                contentType(ContentType.JSON).spec(reqSpec).when()
	                .get("/posts/{id}").then().spec(responseSpecification).extract().path("author");
	         softAssert.assertEquals(author,name,"Assertion getting failed");
	         softAssert.assertAll();
	    }


	    @Test(dataProvider = "commentDetails")
	    void day4Assignment2(int id, String body,int postId){
	        Response response = given().
	                pathParam("id",id).
	                contentType(ContentType.JSON).spec(reqSpec).when()
	                .get("/comments/{id}").then().extract().path("body");
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
