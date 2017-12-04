package pt.ismai.a029187.xmlparser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.util.ArrayList;

public class SaxCustomerXmlHandler extends MyXmlListHandler<Integer> {
    private String tempValue;

    public SaxCustomerXmlHandler() {
        osElementos = new ArrayList<Integer>();
    }

    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) throws SAXException {
        tempValue = "";
        if (qName.equalsIgnoreCase("customer"))
            ;
    }

    @Override
    public void characters(char[] ch, int start, int end) {
        tempValue = new String(ch, start, end);
    }

    @Override
    public void endElement(String uri, String localName,
                           String qName) throws SAXException {
        if (qName.equalsIgnoreCase("customer"))
            osElementos.add(new Integer(tempValue));
    }
}