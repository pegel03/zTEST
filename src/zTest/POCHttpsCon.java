package zTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.codec.binary.Base64;

public class POCHttpsCon {

    public static void main(String[] args) throws Exception {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @Override
            public void checkClientTrusted(X509Certificate[] certs, String authType) {
                if (certs != null) {
                    for (int i = 0; i < certs.length; i++) {
                        System.out.println("cert: " + certs[i]);
                    }
                }

            }

            @Override
            public void checkServerTrusted(X509Certificate[] certs, String authType) {
                if (certs != null) {
                    for (int i = 0; i < certs.length; i++) {
                        System.out.println("cert: " + certs[i]);
                    }
                }
            }
        } };
        // Install the all-trusting trust manager
        final SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

        // String url = "https://distribution-xml.booking.com/json/bookings.getHotels?rows=3";
        String userpass = "XMLSPECS" + ":" + "SPECSXML";
        String basicAuth = "Basic " + new String(new Base64().encode(userpass.getBytes()));
        System.out.println("basicAuth: " + basicAuth);
        String outFile = "booking.xml";

        // InputStream is = uc.getInputStream();

        URL url = new URL("https://distribution-xml.booking.com/json/bookings.getHotels?rows=3");
        URLConnection uc = url.openConnection();
        uc.setRequestProperty("Authorization", basicAuth);
        final Reader reader = new InputStreamReader(uc.getInputStream());
        final BufferedReader br = new BufferedReader(reader);
        String line = "";
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    } // End of main
}
