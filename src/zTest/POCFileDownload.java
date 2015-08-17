package zTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

public class POCFileDownload {

    // <dependency>
    // <groupId>commons-codec</groupId>
    // <artifactId>commons-codec</artifactId>
    // <version>1.3</version>
    // <scope>provided</scope>
    // </dependency>
    //

    // NS API.
    // Op www.ns.nl/api vindt u meer informatie.
    // Gebruikersnaam = bstroobants@anwb.nl
    // Wachtwoord = b1es6nvTImSf8fZ3eY7L7-q8n-467bdQFM47tvc5r1qVL1U63mbp_w
    // http://webservices.ns.nl/ns-api-stations-v2

    // "poi": "booking",
    // "username": "XMLSPECS",
    // "password": "SPECSXML",
    // "url": "https://distribution-xml.booking.com/json/bookings.getHotels?rows=3",

    public static void main(String... args) {
        System.out.println("we zijn binnen...");
        // System.setProperty("http.proxyHost", "astaro.anwb.local");
        // System.setProperty("http.proxyPort", "8080");
        POCFileDownload us = new POCFileDownload();
        // us.downloadFile();
        us.downloadSSLFile();
        System.out.println("En klaar.");
    }

    private void downloadFile() {
        OutputStream fos = null;
        try {

            // NS
            // String url = "http://webservices.ns.nl/ns-api-stations-v2";
            // String userpass = "bstroobants@anwb.nl" + ":" + "b1es6nvTImSf8fZ3eY7L7-q8n-467bdQFM47tvc5r1qVL1U63mbp_w";
            // String basicAuth = "Basic " + new String(new Base64().encode(userpass.getBytes()));
            // System.out.println("basicAuth: " + basicAuth);
            // String outFile = "nsStations.xml";

            String url = "https://distribution-xml.booking.com/json/bookings.getHotels?rows=3";
            String userpass = "XMLSPECS" + ":" + "SPECSXML";
            String basicAuth = "Basic " + new String(new Base64().encode(userpass.getBytes()));
            System.out.println("basicAuth: " + basicAuth);
            String outFile = "booking.xml";

            // C:\Users\pglas.iprofslaptop120\.eclipse\org.eclipse.equinox.security

            URLConnection uc = new URL(url).openConnection();
            uc.setRequestProperty("Authorization", basicAuth);
            InputStream is = uc.getInputStream();
            byte[] buffer = new byte[4096];
            int n = -1;
            fos = new FileOutputStream(new File(outFile));
            while ((n = is.read(buffer)) != -1) {
                fos.write(buffer, 0, n);
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(fos);
        }
    }

    private void downloadSSLFile() {
        // import java.net.URL;
        // import java.io.*;
        // import javax.net.ssl.HttpsURLConnection;

        System.setProperty("javax.net.ssl.trustStore", "cacerts.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "changeit");

        String httpsURL = "https://distribution-xml.booking.com/json/bookings.getHotels?rows=3";
        String userpass = "XMLSPECS" + ":" + "SPECSXML";
        String basicAuth = "Basic " + new String(new Base64().encode(userpass.getBytes()));

        URL myurl = null;
        try {
            myurl = new URL(httpsURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        URLConnection con = null;
        // HttpsURLConnection con = null;
        try {
            con = myurl.openConnection();
            con.setRequestProperty("Authorization", basicAuth);
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream ins = null;
        try {
            ins = con.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStreamReader isr = new InputStreamReader(ins);
        BufferedReader in = new BufferedReader(isr);

        String inputLine;

        try {
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
