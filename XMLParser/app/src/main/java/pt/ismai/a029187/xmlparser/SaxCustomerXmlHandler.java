package pt.ismai.a029187.xmlparser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class SaxCustomerXmlHandler extends MyXmlListHandler<Integer> {
    private String tempValue;
    private int tempCodigoCliente;
    private boolean capturar;

    public SaxCustomerXmlHandler() {
        capturar = false;
    }

    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) throws SAXException {
        tempValue = "";
        if (qName.equalsIgnoreCase("customerlist"))
            capturar = true;
    }

    @Override
    public void characters(char[] ch, int start, int end) {
        tempValue = new String(ch, start, end);
    }

    @Override
    public void endElement(String uri, String localName,
                           String qName) throws SAXException {
        if (qName.equalsIgnoreCase("customerlist"))
            capturar = false;
        else if (capturar && qName.equalsIgnoreCase("customer"))
            osElementos.add(Integer.parseInt(tempValue));
    }
}