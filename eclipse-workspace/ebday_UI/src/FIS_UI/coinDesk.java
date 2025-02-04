
package FIS_UI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.hamcrest.Matcher;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
public class coinDesk 
{
	

	public static void main(String[] args) 
	{
		RestAssured.baseURI="https://api.coindesk.com/v1/bpi/currentprice.json";		
		//1.	Send the GET request		
		  Response res=given(). when().get(). then().log().all().statusCode(200).
		  extract().response();
		 
		//2. 2.	Verify the response contains
		  //a.	There are 3 BPIs i.	USD ii.	GBP iii.	EUR
		  // Verify 3 BPI  Count
		  JsonPath jsonObj=res.jsonPath(); 
		  String bpi=jsonObj.getString("bpi");
		  System.out.println(bpi);
		  int bpiSize = res.jsonPath().getMap("bpi").size();
		// Verify that the "bpi" object contains USD, GBP, and EUR keys
		  
	        assertThat("BPI should contain USD key", res.jsonPath().getMap("bpi"), hasKey("USD"));
	        assertThat("BPI should contain GBP key", res.jsonPath().getMap("bpi"), hasKey("GBP"));
	        assertThat("BPI should contain EUR key", res.jsonPath().getMap("bpi"), hasKey("EUR"));
	        
	     //2   b.	The GBP ‘description’ equals ‘British Pound Sterling’.
	     // Verify that the description of GBP is "British Pound Sterling"
	        String gbpDescription = res.jsonPath().getString("bpi.GBP.description");
	        assertThat("GBP description should be 'British Pound Sterling'", gbpDescription, equalTo("British Pound Sterling"));
        

		
	}

	private static void assertThat(String string, String gbpDescription, Matcher<String> equalTo) {
		// TODO Auto-generated method stub
		
	}

	private static void assertThat(String string, Map<Object, Object> map, Matcher<Map<? extends String, ?>> hasKey,
			Matcher<Map<?, ? extends String>> hasValue) {
		// TODO Auto-generated method stub
		
	}

	private static void assertThat(String string, Map<Object, Object> map, Matcher<Map<? extends String, ?>> hasKey) {
		// TODO Auto-generated method stub
		
	}

	}
