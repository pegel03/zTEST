package zTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

import de.odysseus.staxon.json.JsonXMLConfig;
import de.odysseus.staxon.json.JsonXMLConfigBuilder;
import de.odysseus.staxon.json.JsonXMLOutputFactory;

//<dependency>
//<groupId>de.odysseus.staxon</groupId>
//<artifactId>staxon</artifactId>
//<version>1.3</version>
//</dependency>

public class XmlToJsonStreamingExample3 {

    /**
     * Copy/format JSON using {@link XMLEventWriter#add(XMLEventReader)}.
     * 
     * @param args
     *            ignored
     * @throws XMLStreamException
     * @throws IOException
     */
    public static void main(String[] args) throws XMLStreamException, IOException {
        String inFile = "campingsTestAlles.xml";
        // String inFile = "campingsTest.xml";
        System.out.println("Converting: " + inFile);

        InputStream input = XmlToJsonStreamingExample3.class.getResourceAsStream(inFile);
        // OutputStream output = System.out;
        OutputStream output = new FileOutputStream(new File("campingsOut.json"));
        /*
         * If we want to insert JSON array boundaries for multiple elements, we need to set the <code>autoArray</code> property. If our XML
         * source was decorated with <code>&lt;?xml-multiple?&gt;</code> processing instructions, we'd set the <code>multiplePI</code>
         * property instead. With the <code>autoPrimitive</code> property set, element text gets automatically converted to JSON primitives
         * (number, boolean, null).
         */
        // JsonXMLConfig config = new JsonXMLConfigBuilder().autoArray(true).autoPrimitive(true).multiplePI(true).prettyPrint(true).build();
        JsonXMLConfig config = new JsonXMLConfigBuilder().autoArray(false).autoPrimitive(true).prettyPrint(true).build();
        try {
            /*
             * Create reader (XML).
             */
            XMLEventReader reader = XMLInputFactory.newInstance().createXMLEventReader(input);

            /*
             * Create writer (JSON).
             */
            XMLEventWriter writer = new JsonXMLOutputFactory(config).createXMLEventWriter(output);

            /*
             * To insert JSON array boundaries, we wrap our writer into an XMLMultipleStreamWriter.
             */
            // writer = new XMLMultipleStreamWriter(writer, false, "/results");
            // new XMLMultipleEventWriter(writer, true, "/Product/Language");

            /*
             * Copy events from reader to writer.
             */
            writer.add(reader);

            /*
             * Close reader/writer.
             */
            reader.close();
            writer.close();
        } finally {
            /*
             * As per StAX specification, XMLEventReader/Writer.close() doesn't close the underlying stream.
             */
            output.close();
            input.close();
            System.out.println("Ready.");
        }
    }
}