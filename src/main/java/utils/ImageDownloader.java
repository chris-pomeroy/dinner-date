package utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageDownloader {

    public static void download(String url, String destinationFolder) throws IOException, InterruptedException {
        String filename = url.substring(url.lastIndexOf("/") + 1);
        Path destination = Paths.get(destinationFolder + filename);

        if (Files.exists(destination)) {
            return;
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        client.send(request, HttpResponse.BodyHandlers.ofFile(destination));
        client.close();
    }
}
