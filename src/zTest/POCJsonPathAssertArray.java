package zTest;

import static org.hamcrest.Matchers.hasSize;

import com.jayway.jsonassert.JsonAssert;

public class POCJsonPathAssertArray {

    public static void main(String[] args) {
        POCJsonPathAssertArray poc = new POCJsonPathAssertArray();
        poc.evaluate();
    }

    private void evaluate() {

        String json = "{\"arr1\": [1,2,3], \"arr2\": [] }";

        JsonAssert.with(json).assertThat("arr1", hasSize(3));
        JsonAssert.with(json).assertThat("arr2", hasSize(0));
        // JsonAssert.with(json).assertThat( "arr2", emptyCollection() );

        String json2 = "[{\"gem_prijsniveau\": \"$$\",\"betaling\": [],},{\"bron\": \"IENS\",\"poi_type\": \"restaurant\",\"telefoonnummer\": \"020 6200862\",}]";

        JsonAssert.with(json2).assertThat("$.[0].betaling", hasSize(0));

    }
}
