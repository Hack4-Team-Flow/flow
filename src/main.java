import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.*;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
public class main {
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();
    public static void main(String[] Args) throws IOException, InterruptedException {
        Store[] stores=new Store[6];
        Store store1=new Store("White bakery",37.02028754,30.59819901,30,"bake",5.00);
        stores[0]=store1;
        Store store2=new Store("McDonalds",36.88682729,30.7025303,30,"burger",15.00);
        stores[1]=store2;
        Store store3=new Store("Beach Bar",36.61379416,30.5610624,30,"drink",45.00);
        stores[2]=store3;
        Store store4=new Store("Coffee Shop",36.99647015,30.85289727,30,"cofee",10.00);
        stores[3]=store4;
        Store store5=new Store("Burger King",36.88672206,30.81871253,30,"burger",17.00);
        stores[4]=store5;
        Store store6=new Store("maria's coffee",36.85133768,30.85056657,30,"cofee",10.00);
        stores[5]=store6;
        double[]location=new double[2];
        location[0]=36.9159143629179;
        location[1]=30.804464229398153;
        StoreCalculator storeCalculator=new StoreCalculator(stores,location);
        System.out.println(storeCalculator.getTravelTimeToOrder());


    }
    public void communicate() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:5000/takeorder"))
                //.header("content-type", "application/json")
                //.header("x-rapidapi-host", "distance-calculator.p.rapidapi.com")
                //.header("x-rapidapi-key", "26956441a4mshc9c2756fdf42cd7p1c723ejsn350adb6e0848")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());


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
    }
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
}
