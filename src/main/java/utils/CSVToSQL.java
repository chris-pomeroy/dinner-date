package utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVToSQL {

    private static final String RECIPE_SQL = """
            INSERT INTO
              "public"."recipes" ("id", "title", "instructions", "image_name")
            VALUES
              ('%d', '%s', '%s', '%s');
            """;

    private static final String INGREDIENT_SQL = """
            INSERT INTO
              "public"."ingredients" ("recipe_id", "ingredient")
            VALUES
            """;

    public static void main(String[] args) throws IOException {
        try (Reader in = new FileReader("src/main/resources/recipes.csv")) {
            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .setHeader()
                    .setSkipHeaderRecord(true)
                    .get();

            List<CSVRecord> records = csvFormat.parse(in).stream().toList();

            for (int c=0; c < records.size(); c++) {
                CSVRecord record = records.get(c);
                String formatted = RECIPE_SQL.formatted(c+1, record.get("Title"), record.get("Instructions").replaceAll("'", "''"), record.get("Image_Name"));
                System.out.println(formatted);

                String ingredientsFormatted = record.get("Cleaned_Ingredients")
                        .replaceAll("\"", "\"\"")
                        .replaceAll("^\\['|']$", "\"")
                        .replaceAll("', '", "\",\"");

                Stream<String> ingredients = CSVFormat.DEFAULT.parse(new StringReader(ingredientsFormatted))
                        .stream()
                        .toList()
                        .getFirst()
                        .stream();

                int finalC = c;
                String ingredientsValues = ingredients
                        .map(ingredient -> "('%d', '%s')".formatted(finalC + 1, ingredient))
                        .collect(Collectors.joining("," + System.lineSeparator()));

                System.out.println(INGREDIENT_SQL + ingredientsValues + ";\n");
            }
        }
    }
}
