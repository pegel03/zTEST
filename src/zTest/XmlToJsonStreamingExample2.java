//package zTest;
//
//import java.io.InputStream;
//import java.io.StringReader;
//import java.io.StringWriter;
//
//import com.thoughtworks.xstream.XStream;
//import com.thoughtworks.xstream.io.HierarchicalStreamReader;
//import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
//import com.thoughtworks.xstream.io.copy.HierarchicalStreamCopier;
//import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
//import com.thoughtworks.xstream.io.xml.XppReader;
//
////<dependency>
////<groupId>com.thoughtworks.xstream</groupId>
////<artifactId>xstream</artifactId>
////<version>1.4.7</version>
////</dependency>
//
//public class XmlToJsonStreamingExample2 {
//
//    public static void main(String[] args) throws Exception {
//
//        String xml = "ToveJaniReminder" + "Don't forget me this weekend!";
//
////        HierarchicalStreamReader sourceReader = new XppReader(new StringReader(xml));
////        HierarchicalStreamReader sourceReader = XppReader.
//        new XStream(new hier)
//
//        StringWriter buffer = new StringWriter();
//        JettisonMappedXmlDriver jettisonDriver = new JettisonMappedXmlDriver();
//        jettisonDriver.createWriter(buffer);
//        HierarchicalStreamWriter destinationWriter = jettisonDriver.createWriter(buffer);
//
//        HierarchicalStreamCopier copier = new HierarchicalStreamCopier();
//        copier.copy(sourceReader, destinationWriter);
//
//        System.out.println(buffer.toString());
//    }
//
//    private InputStream openStream() {
//        return XmlToJsonSreamingExample.class.getResourceAsStream("campingsTest.xml");
//    }
//
// }
