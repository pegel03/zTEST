package zTest;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;

public class CreateThumbnailTransparent {

    public static void main(String... args) throws IOException {
        System.out.println("binnen");
        File img = new File("TrekTM500Nyondames.png");

        BufferedImage in = ImageIO.read(img);
        // BufferedImage newImage = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_ARGB);
        // Graphics2D g = newImage.createGraphics();
        // g.drawImage(in, 0, 0, null);
        // g.dispose();

        if (in.getColorModel().getTransparency() != Transparency.OPAQUE) {
            System.out.println("opaque");
            int w = in.getWidth();
            int h = in.getHeight();
            BufferedImage image2 = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image2.createGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, w, h);
            g.drawRenderedImage(in, null);
            g.dispose();
            ImageIO.write(in, "png", img);
        }

        Thumbnails.of(img).size(140, 79).outputQuality(0.6).toFile(new File("TrekTM500NyondamesOUT.png"));
        System.out.println("klaar");
    }
}
