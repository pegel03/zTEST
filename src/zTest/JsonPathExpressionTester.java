package zTest;

import net.minidev.json.JSONArray;

import com.jayway.jsonpath.JsonPath;

public class JsonPathExpressionTester {

    public static void main(String... args) {
        JsonPathExpressionTester tester = new JsonPathExpressionTester();
        tester.test();
    }

    private void test() {
        System.out.println("testing..");
        String read3 = null;
        String tree = "[{\"Name\":\"Ab1\",\"Price\":\"153\"},{\"Name\":\"Cd2\",\"Price\":\"179\"}]";
        Object read = JsonPath.read(tree, "$[?(@.Name == 'Ab1')].Price");

        String json = "{\"@ExternalKey\": \"campsite-44462\",\"@TreeIndex\": \"14\",\"@ID\": \"44462\",\"@ParentID\": \"19966\",\"Language\": [{\"@LanguageID\": \"0\",\"Field\": [{\"@refID\": \"1020\",\"$\": \"44462\"},{\"@refID\": \"1027\",\"$\": \"Camping\"},{\"@refID\": \"1028\",\"$\": \"Pusztasee\"}]},{\"@name\": \"nl\",\"@LanguageID\": \"3\",\"Field\": [{\"@refID\": \"1951\",\"$\": \"De camping ligt ca. 1 km ten westen van het centrum van Andau. Vanuit Andau via de B206 richting Tadten. Even buiten de bebouwde kom van Andau ligt de camping rechts aan deze weg.\"},{\"@refID\": \"1950\",\"$\": \"Het grind/grasstrand aan de Pusztasee meet 5 bij 200 m. Daggasten zijn welkom, honden niet toegestaan. Het water in het meertje loopt vrij snel af tot een diepte van 15 m. In het water drijven drie opblaasbare eilanden. Via een trapje kan men het water in.\"}]}]}";
        // List<JSONObject> read2 = JsonPath.read(json, "$.Language[0].Field[?(@.@refID == '1020')]");
        Object read2 = JsonPath.read(json, "$.Language[0].Field[?(@.@refID == '1020')].$");
        if (read2 instanceof JSONArray) {
            read3 = (String) ((JSONArray) read).get(0);
        }

        System.out.println("json: " + read2);
        // System.out.println("json single: " + read2.get(0));
        System.out.println("read3; " + read3);
        System.out.println("ready.");
    }

}
