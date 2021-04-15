import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.util.*;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class ReadXml
{

    static String stroka, stroka2;
    final static String ADDRESS = "I:\\InfoMaximum\\Projects\\address.xml";
    static List<String> item = new ArrayList<>();
    static List<String> city_floor = new ArrayList<>();

    public static void main(String[] args)
    {

        try
        {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLHandler handler = new XMLHandler();
            parser.parse(new File(ADDRESS), handler);

            HashMap<String, Integer> hm = new HashMap<String, Integer>();
            Integer count;
            for (String it : item) {
                count = hm.get(it);
                if (count == null) hm.put(it, 1);
                else hm.put(it, count + 1);
            }
            System.out.println("Count of items: " + item.size());
            System.out.println("Count of unique items: " + hm.size());
            System.out.println("Double items: ");

            for (Object key : hm.keySet()) {
                if (hm.get(key) > 1) {
                    System.out.println(key + "   ---> " + hm.get(key) + " duplicates");
                }
            }
            System.out.println("\n*******************\n");
            HashMap<String, Integer> cf = new HashMap<String, Integer>();
            Integer count2;
            for (String it : city_floor) {
                count2 = cf.get(it);
                if (count2 == null) cf.put(it, 1);
                else cf.put(it, count2 + 1);
            }
            for (Object key : cf.keySet()) {
                System.out.println(key + "   ---> " + cf.get(key) + " items");
            }
        }
        catch(Exception ei){}
    }
    static class XMLHandler extends DefaultHandler {
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            if (qName.equals("item")) {
                String city = attributes.getValue("city");
                String street = attributes.getValue("street");
                int house = Integer.parseInt(attributes.getValue("house"));
                int floor = Integer.parseInt(attributes.getValue("floor"));
                stroka = "[City " + city + ", street " + street + ", house " + house + ", floors: " + floor + "]";
                item.add(stroka);
                stroka2 = "In the city of " + city + " buildings with " + floor + " floors";
                city_floor.add(stroka2);
            }
        }
    }
}