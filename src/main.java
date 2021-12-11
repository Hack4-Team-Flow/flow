import java.io.IOException;
import java.net.URI;
import java.net.http.*;

public class main {
    public static HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://127.0.0.1:5000/takeorder"))
            //.header("content-type", "application/json")
            //.header("x-rapidapi-host", "distance-calculator.p.rapidapi.com")
            //.header("x-rapidapi-key", "26956441a4mshc9c2756fdf42cd7p1c723ejsn350adb6e0848")
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();
    public static HttpResponse<String> response;

    static {
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] Args) throws IOException, InterruptedException {
        System.out.println(response.body());
    }
}
