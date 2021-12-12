import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.*;
import org.apache.commons.lang3.StringUtils;

public class StoreJsonParser {
    public static String[] names;
    public static double[] durations;
    public static double[] latitudes ;
    public static double[] longtitudes;
    public static int[] prices;
    public static String[] businessTypes;
    public static int storeCount=0;
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public StoreJsonParser() throws IOException, InterruptedException {
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
        //System.out.println(json);
        int lenght=StringUtils.countMatches(json, "store");
        storeCount=lenght;
        names=new String[lenght];
        durations=new double[lenght];
        latitudes=new double[lenght];
        longtitudes=new double[lenght];
        prices=new int[lenght];
        businessTypes=new String[lenght];

        for(int j=0;j<lenght;j++){
            JSONObject obj = new JSONObject(json);
            if(j==0){
                json=json.substring(obj.toString().length()+1);
            }if(j!=lenght-1&&j!=0){
                json=json.substring(obj.toString().length()+1);
            }
            //System.out.println(json);
            //System.out.println(obj.toString());
            JSONArray arr = obj.getJSONArray("store");

            for (int i = 0; i < arr.length(); i++) {
                names[j] = arr.getJSONObject(i).getString("name");
                durations[j] = arr.getJSONObject(i).getDouble("duration");
                latitudes[j] = arr.getJSONObject(i).getDouble("latitude");
                longtitudes[j] = arr.getJSONObject(i).getDouble("longtitude");
                prices[j] = arr.getJSONObject(i).getInt("price");
                businessTypes [j]= arr.getJSONObject(i).getString("businessType");
                /*System.out.print("name :"+name);
                System.out.print(" duration: "+duration);
                System.out.print(" latitude: "+latitude);
                System.out.print(" longtitude: "+longtitude);
                System.out.print(" price: "+price);
                System.out.println(" businessType: "+businessType);*/
            }
        }


    }

    public static String[] getNames() {
        return names;
    }

    public static double[] getDurations() {
        return durations;
    }

    public static double[] getLatitudes() {
        return latitudes;
    }

    public static double[] getLongtitudes() {
        return longtitudes;
    }

    public static int[] getPrices() {
        return prices;
    }

    public static String[] getBusinessTypes() {
        return businessTypes;
    }

    public int getStoreCount() {
        return storeCount;
    }
}
