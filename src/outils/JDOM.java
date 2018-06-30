package outils;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.File;

public class JDOM {

    private Element racine;
    private static JDOM ourInstance = new JDOM();

    public static JDOM getInstance() {
        if (ourInstance == null) {
            ourInstance = new JDOM();
        }
        return ourInstance;
    }

    private JDOM() {
        SAXBuilder saxBuilder = new SAXBuilder();
        Document document;
        try {
            document = saxBuilder.build(new File("parametres.xml"));
            racine = document.getRootElement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getValue (String elementName) {
        return racine.getChild(elementName).getValue();
    }

    public void setValue (String elementName, String value) {
        racine.getChild(elementName).setText(value);
    }
}
