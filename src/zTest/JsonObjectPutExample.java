package zTest;

import org.json.JSONObject;

public class JsonObjectPutExample {

    public static void main(String... args) {
        JsonObjectPutExample example = new JsonObjectPutExample();
        example.putter();
    }

    private void putter() {
        JSONObject startObject = new JSONObject();
        startObject.put("adres", new JSONObject());
        JSONObject startAdres = (JSONObject) startObject.get("adres");
        startAdres.put("provincie", "albion");
        String key = "adres.landcode";
        String[] split = key.split("\\.");
        if (split.length > 1) {
            JSONObject adres = (JSONObject) startObject.get(split[0]);
            adres.put(split[1], "NLD");
        }
        // TODO _ pgl remove sysout.
        System.out.println("object: " + startObject);
    }
}
