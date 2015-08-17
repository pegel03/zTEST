package zTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;

import org.codehaus.jettison.mapped.MappedXMLOutputFactory;

public class XmlToJsonSreamingExample {

    public static void main(String[] args) throws Exception {
        XmlToJsonSreamingExample deze = new XmlToJsonSreamingExample();

        InputStream inputStream = deze.openStream();

        // String xml = "<root><foo arg=\"me\">foo string</foo><bar><x>1</x><y>5</y></bar></root>";
        // XMLEventReader reader = XMLInputFactory.newInstance().createXMLEventReader(new StringReader(xml));

        XMLEventReader reader = XMLInputFactory.newInstance().createXMLEventReader(inputStream);

        OutputStream outputStream = new FileOutputStream(new File("campingsOut.json"));
        XMLEventWriter writer = new MappedXMLOutputFactory(new HashMap()).createXMLEventWriter(outputStream);
        writer.add(reader);
        writer.close();
        reader.close();
        inputStream.close();
        outputStream.close();
    }

    private InputStream openStream() {
        return XmlToJsonSreamingExample.class.getResourceAsStream("campingsTest.xml");
    }
}