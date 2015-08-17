package zTest;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

public class POCMapping {

    // Example taken from: http://www.ngdata.com/parsing-a-large-json-file-efficiently-and-easily/
    // read mapping
    // <dependency>
    // <groupId>org.json</groupId>
    // <artifactId>json</artifactId>
    // <version>20140107</version>
    // </dependency>

    // Example taken from: https://code.google.com/p/json-path/
    // <dependency>
    // <groupId>com.jayway.jsonpath</groupId>
    // <artifactId>json-path</artifactId>
    // <version>0.9.1</version>
    // </dependency>
    //
    // <dependency>
    // <groupId>com.jayway.jsonpath</groupId>
    // <artifactId>json-path-assert</artifactId>
    // <version>0.9.1</version>
    // <scope>test</scope>
    // </dependency>

    private Map<String, String> mapping;

    public static void main(String... args) throws JsonParseException, JsonMappingException, IOException {
        POCMapping pocMapping = new POCMapping();
        pocMapping.loadMapping();
        try {
            pocMapping.readFile();
        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        final File folder = new File("/tmp");
        pocMapping.listFilesForFolder(folder);
    }

    private void listFilesForFolder(final File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (!fileEntry.isDirectory()) {
                // String ext = fileEntry.getName().substring(fileEntry.getName().indexOf(File.separator));
                String ext = FilenameUtils.getExtension(fileEntry.getName());
                // String ext = FileUtils.extension(fileEntry.getName());
                // int ext = fileEntry.getName().indexOf(File.separator);
                System.out.println(fileEntry.getName() + " with ext: " + ext);
            }
        }
    }

    private void loadMapping() throws JsonParseException, JsonMappingException, IOException {
        File source = new File("/poi_mapping_poc.json");
        // mapping = new JSONObject();
        // mapping.put("id", "-UniekeCode");
        // mapping.put("naam", "-Naam");
        // mapping.put("woonplaats", "Adreslijst.Adres.Woonplaats");
        // poi_mapping_poc.json
        mapping = new ObjectMapper().readValue(source, new TypeReference<Map<String, String>>() {
        });
    }

    private void readFile() throws JsonParseException, JsonProcessingException, IOException {
        JsonFactory f = new MappingJsonFactory();
        // must be utf 8!
        JsonParser jp = f.createParser(new File("/fietsroutes.json"));

        JsonToken token = jp.nextToken();
        if (token != JsonToken.START_OBJECT) {
            System.out.println("Error: root should be start object: quiting.");
            return;
        }

        token = advanceTokenToStartArray(jp);
        // walkObjectsInArray(jp);
    }

    private JsonToken advanceTokenToStartArray(JsonParser jp) throws JsonParseException, IOException {
        while (true) {
            JsonToken token = jp.nextToken();
            if (token == JsonToken.START_ARRAY) {
                return token;
            }
        }
    }

    private void walkObjectsInArray(JsonParser jp) throws JsonProcessingException, IOException {
        while (true) {
            if (jp.nextToken() == JsonToken.START_OBJECT) {
                JsonNode jn = jp.readValueAsTree();
                processObject(jn);
            } else {
                System.out.println("END of objects.");
                return;
            }
        }
    }

    private void processObject(JsonNode jn) {
        // String text = jn.toString();
        // String path = JsonPath.read(text, "$.Adreslijst.Adres.Woonplaats");
        // System.out.println("Woonplaats path = " + path);
        //
        // JsonNode node2 = jn.get("Adreslijst").get("Adres").get("Woonplaats");
        // System.out.println("Woonplaats = " + node2.asText());

        String json = jn.toString();
        Set<String> set = mapping.keySet();
        for (String key : set) {
            String value = mapping.get(key);
            String asText = JsonPath.read(json, "$." + value);
            System.out.println("Fieldname path: " + key + " value: " + asText);

            // key = (String) keys.next();
            // value = (String) mapping.get(key);
            // asText = jn.get(value).asText();
            // System.out.println("Fieldname: " + key + " value: " + asText);
        }

    }

}
