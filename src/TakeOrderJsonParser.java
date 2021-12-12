import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Map;

public class TakeOrderJsonParser {
    public static String foodType;
    public static String foodKind;
    public static String drink;

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public TakeOrderJsonParser() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:5000/stores"))
                //.header("content-type", "application/json")
                //.header("x-rapidapi-host", "distance-calculator.p.rapidapi.com")
                //.header("x-rapidapi-key", "26956441a4mshc9c2756fdf42cd7p1c723ejsn350adb6e0848")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        //System.out.println(response.body());
        jsonConverter(response.body().substring(1,response.body().length()-2));
    }

    /*public static void communicate() throws IOException, InterruptedException {



        Map<Object, Object> data = new HashMap<>();
        data.put("username", "abc");
        data.put("password", "123");
        data.put("custom", "secret");
        data.put("ts", System.currentTimeMillis());

        HttpRequest request1 = HttpRequest.newBuilder()
                .POST(ofFormData(data))
                .uri(URI.create("http://127.0.0.1:5000/takeorder"))
                //.setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                //.header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        HttpResponse<String> response1 = httpClient.send(request1, HttpResponse.BodyHandlers.ofString());

        // print status code
        System.out.println(response1.statusCode());

        // print response body
        System.out.println(response1.body());
    }*/
    public static HttpRequest.BodyPublisher ofFormData(Map<Object, Object> data) {
        var builder = new StringBuilder();
        for (Map.Entry<Object, Object> entry : data.entrySet()) {
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
        }
        return HttpRequest.BodyPublishers.ofString(builder.toString());
    }
    public static void jsonConverter(String json){
        JSONObject obj = new JSONObject(json);
        JSONArray arr = obj.getJSONArray("takeorder");
        foodKind= arr.getJSONObject(0).getString("foodKind");
        foodType= arr.getJSONObject(0).getString("foodType");
        drink= arr.getJSONObject(0).getString("drink");
    }

    public static String getFoodType() {
        return foodType;
    }

    public static String getFoodKind() {
        return foodKind;
    }

    public static String getDrink() {
        return drink;
    }
}
