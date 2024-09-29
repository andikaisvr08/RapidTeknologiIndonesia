package QaAutomation;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC05_APITest {

	@Test
	public static void main(String[] args) {
		// 1. Kirim request ke endpoint dengan id=1
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/posts/1");

        // 2. Verifikasi bahwa status code adalah 200
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Status code should be 200");

        // 3. Verifikasi bahwa data yang dikembalikan sesuai
        String title = response.jsonPath().getString("title");
        Assert.assertNotNull(title, "Title should not be null");
        System.out.println("Title: " + title);

        // Optional: Print full response for debugging
        response.prettyPrint();
    }

    @Test
    public void testGetPostWithInvalidId() {
        // 4. Kirim request dengan ID yang tidak valid (id=99999)
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/posts/99999");

        // Verifikasi bahwa status code adalah 404
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 404, "Status code should be 404 for invalid ID");

        // Optional: Print full response for debugging
        response.prettyPrint();
    }
}