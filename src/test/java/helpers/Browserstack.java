package helpers;

import static io.restassured.RestAssured.given;

public class Browserstack {

    // curl -u "vitkuz_MjJ24Z:owV6DA2C55TBiK4TvTyz" -X GET "https://api.browserstack.com/app-automate/sessions/0545ff34d503e81d53f35f57abf348eebacc6f8a.json"

    public static String videoUrl(String sessionId) {
        String url = String.format("https://api.browserstack.com/app-automate/sessions/%s.json", sessionId);

        return given()
                .auth().basic("vitkuz_MjJ24Z", "owV6DA2C55TBiK4TvTyz")
                .get(url)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().path("automation_session.video_url");
    }
}


