package Assignment5;

import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import Model.Friend;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
public class Que1 {
	RequestSpecification reqSpec;
    ResponseSpecification resSpec;
    @BeforeClass
    public void beforeClass() {
        reqSpec = new RequestSpecBuilder().setBaseUri("http://localhost:3000").build();
        resSpec = new ResponseSpecBuilder().expectStatusCode(500).build();
    }

    @Test
    void getFriendsUsingPOJO(){
        Friend[] friends = given().
                contentType(ContentType.JSON).spec(reqSpec).when()
                .get("/friends").as(Friend[].class);

        for(Friend friend:friends){
            System.out.println(friend.getAge());
            System.out.println(friend.getFirstname());
            System.out.println(friend.getLastname());
            System.out.println(friend.getAddress());
            System.out.println(friend.getId());
        }
    }

    @Test
    void checkStatusOfExistingUser(){
        Friend[] friends = given().
                contentType(ContentType.JSON).spec(reqSpec).when()
                .get("/friends").as(Friend[].class);
        String id= friends[0].getId().toString();
        JSONObject addressObj = new JSONObject();
        addressObj.put("streetName", "Hinjewadi");
        addressObj.put("houseNo", "77");
        Friend friend = Friend.builder().firstname("Arijit").lastname("Singh").id(id)
                .age(25).address(addressObj).build();
        given().contentType(ContentType.JSON)
                .spec(reqSpec).body(friend).when().post("/friends").then().spec(resSpec);

    }
}
