package zTest;

import com.jayway.jsonpath.JsonPath;

public class POCJsonPathExpression {

    private final static String json = "{\"@phone_nr\": \"0118571382\",\"@id\": \"417\",\"@station_name\": \"Faasse\",\"@address\": \"Zuidstraat 27\",\"@postal_code\": \"4361AA\",\"@brand\": \"Total\",\"@longitude\": \"3.444580\",\"@latitude\": \"51.528590\",\"@city\": \"Westkapelle\",\"services\": {\"@shop\": \"1\",\"@trailer\": \"0\",\"@wifi\": \"0\",\"@bakery\": \"0\",\"@highway\": \"0\",\"@gas247\": \"0\",\"@unmanned\": \"0\",\"@carwash\": \"0\"},\"fuels\": {\"fuel\": [{\"@price\": \"1789\",\"@name\": \"euro95\",\"@sold\": \"1\"},{\"@price\": \"1439\",\"@name\": \"diesel\",\"@sold\": \"1\"},{\"@name\": \"autogas\",\"@sold\": \"0\"},{\"@name\": \"euro98\",\"@sold\": \"0\"},{\"@name\": \"leaded\",\"@sold\": \"0\"},{\"@price\": \"1849\",\"@name\": \"euro_special\",\"@sold\": \"1\"},{\"@name\": \"diesel_special\",\"@sold\": \"0\"},{\"@name\": \"cng\",\"@sold\": \"0\"}]},\"openingHours\": {\"period\": [{\"@open\": \"730\",\"@day\": \"1\",\"@close\": \"2000\"},{\"@open\": \"730\",\"@day\": \"2\",\"@close\": \"2000\"},{\"@open\": \"730\",\"@day\": \"3\",\"@close\": \"2000\"},{\"@open\": \"730\",\"@day\": \"4\",\"@close\": \"2000\"},{\"@open\": \"730\",\"@day\": \"5\",\"@close\": \"2000\"},{\"@open\": \"800\",\"@day\": \"6\",\"@close\": \"2000\"}]}}";

    public static void main(String[] args) {
        POCJsonPathExpression poc = new POCJsonPathExpression();
        poc.evaluate();
    }

    private void evaluate() {
        Object read1 = JsonPath.read(json, "$.@phone_nr");
        System.out.println("read = " + read1);
        Object read2 = JsonPath.read(json, "$.services");
        System.out.println("read = " + read2);
        Object read3 = JsonPath.read(json, "$.services.?(@shop == '1')");
        System.out.println("read = " + read3);
        Object read4 = JsonPath.read(json, "$.fuels.fuel");
        System.out.println("read = " + read4);
        Object read5 = JsonPath.read(json, "fuels.fuel[?(@.@name == 'euro95')].@price");
        System.out.println("read = " + read5);
        Object read6 = JsonPath.read(json, "fuels.fuel[?(@.@sold == '1')]");
        System.out.println("read = " + read6);

    }

}
