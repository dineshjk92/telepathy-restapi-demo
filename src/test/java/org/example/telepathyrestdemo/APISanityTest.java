package org.example.telepathyrestdemo;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import java.util.Arrays;
import java.util.List;

public class APISanityTest extends AbstractTestNGSpringContextTests {


    @DataProvider(name = "plan-data")
    public Object[][] dpMethod(){
        return new Object[][] {
                {"Plan1", 100, "voice,email"},
                {"Plan2", 150, "email,database,admin"},
                {"Plan3", 125, "voice,admin"},
                {"Plan4", 135, "database,admin"}
        };
    }

    @BeforeTest
    public void setup() {
        RestAssured.baseURI ="http://localhost:8081";
    }

    @Test (dataProvider = "plan-data", priority = 0, description = "tests whether plans can be added via post call")
    public void testAddPlan(String planName, int price, String features) throws JSONException {
        RequestSpecification request = RestAssured.given().contentType("application/json\r\n");
        JSONObject requestParams = new JSONObject();
        requestParams.put("planName", planName);
        requestParams.put("planPrice", String.valueOf(price));
        requestParams.put("planFeatures", Arrays.asList(features.split(",")));
        request.body(requestParams.toString());
        Response response = request.post("/cloudservice");
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.statusCode(), 200, "Post call failed");
        Assert.assertEquals(response.getBody().asString(), "Cloud plan has been successfully created", "Incorrect return message");
    }

    @Test (dataProvider = "plan-data", priority = 1, description = "tests whether plans can be accessed via get call")
    public void getPlan(String planName, int price, String features) {
        RequestSpecification request = RestAssured.given().contentType("application/json\r\n");
        Response response = request.get("/cloudservice/"+planName);
        Assert.assertEquals(response.statusCode(), 200, "Post call failed");
        Assert.assertEquals(response.jsonPath().get("planName"), planName, "Incorrect plan name");
        Assert.assertEquals((int) response.jsonPath().get("planPrice"), price, "Incorrect plan price");
        Assert.assertEquals(response.jsonPath().get("planFeatures"), Arrays.asList(features.split(",")), "Incorrect plan price");
    }

    @Test (priority = 2, description = "tests whether cheap plan with the best price is found from the existing plans")
    public void testCheapPlan() {
        RequestSpecification request = RestAssured.given().contentType("application/json\r\n");
        JSONArray reqFeatures = new JSONArray();
        reqFeatures.put("email");
        reqFeatures.put("voice");
        reqFeatures.put("admin");
        request.body(reqFeatures.toString());
        Response response = request.post("/cloudservice/findbestplan");
        Assert.assertEquals(response.statusCode(), 200, "Post call failed");
        List<String> responseList = response.jsonPath().get();
        Assert.assertTrue(responseList.get(0).contains("Plan1,Plan3"), "Incorrect plan for cheap cost");
        Assert.assertTrue(responseList.get(1).contains("225"), "Incorrect price for cheap cost");
    }

    @Test (priority = 3, description = "tests whether 0 is returned as price when no cheap plan found from the existing plans")
    public void testNoSuchCheapPlan() {
        RequestSpecification request = RestAssured.given().contentType("application/json\r\n");
        JSONArray reqFeatures = new JSONArray();
        reqFeatures.put("email");
        reqFeatures.put("voice");
        reqFeatures.put("sms");
        request.body(reqFeatures.toString());
        Response response = request.post("/cloudservice/findbestplan");
        Assert.assertEquals(response.statusCode(), 200, "Post call failed");
        List<String> responseList = response.jsonPath().get();
        Assert.assertTrue(responseList.get(0).equalsIgnoreCase(""), "Incorrect plan for cheap cost");
        Assert.assertTrue(responseList.get(1).contains("0"), "Incorrect price for cheap cost");
    }

}
