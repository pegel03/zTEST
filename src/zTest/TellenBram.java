package zTest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TellenBram {
    public static void main(String... args) {
        System.out.println("Hallo Bram");

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));

        TellenBram bram = new TellenBram();
        System.out.println("tijd start" + new Date());
        bram.tellen(1000000000l);
        System.out.println("tijd klaar" + new Date());
    }

    private void tellen(long beginTel) {
        int teller = 0;
        while (teller < beginTel) {
            teller = teller + 1;
            // System.out.println("teller = " + start);
        }
        System.out.println("Klaar met tellen tot " + teller);
    }
}
