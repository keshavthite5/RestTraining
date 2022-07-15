package Assignment1;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
public class NewTest {
    @Test
    public void requestPost_checkStatusCode() {
        Response postResponse;
        postResponse = given().contentType(ContentType.JSON).
                when().
                get("http://localhost:3000/posts").then().extract().response();
        System.out.println("***********************");
        System.out.println("Status received => " + postResponse.statusCode());
        System.out.println("Response=>" + postResponse.prettyPrint());
        System.out.println("***********************");
    }

    @Test
    public void requestComments() {
        Response commentResponse = given().
                contentType(ContentType.JSON).
                when().
                get("http://localhost:3000/comments").
                then().extract().response();
        System.out.println("***********************");
        System.out.println("Comment Response=>" + commentResponse.prettyPrint());
        System.out.println("***********************");
    }

    @Test
    public void requestProfiles() {
        Response profileResponse = given().
                contentType(ContentType.JSON).
                when().
                get("http://localhost:3000/profile").
                then().extract().response();
        System.out.println("***********************");
        System.out.println("Profile Response=>" + profileResponse.prettyPrint());
        System.out.println("***********************");
    }

    @Test
    public void requestFriendsList() {
        Response friendsResponse = (Response) given().
                contentType(ContentType.JSON).log().all().
                when().
                get("http://localhost:3000/friends").
                then()
                .extract().response();
        System.out.println("***********************");
        System.out.println("Friend Response=>" + friendsResponse.prettyPrint());
        System.out.println("***********************");
    }
}

