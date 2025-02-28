package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ImageDownloader {

    private static final String DESTINATION_FOLDER = "src/main/resources/static/recipe-images/";

    public static void main(String[] args) throws IOException, InterruptedException {
        try (Reader in = new FileReader("src/main/resources/recipes.json")) {
            List<GoodFoodRecipe> recipes = new ObjectMapper().readValue(in, new TypeReference<>() {});
            for (GoodFoodRecipe recipe : recipes) {
                download(recipe.imageUrl());
            }
        }
    }

    private static void download(String url) throws IOException, InterruptedException {
        String filename = url.substring(url.lastIndexOf("/") + 1);
        Path destination = Paths.get(DESTINATION_FOLDER + filename);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        client.send(request, HttpResponse.BodyHandlers.ofFile(destination));
        client.close();
    }
}
