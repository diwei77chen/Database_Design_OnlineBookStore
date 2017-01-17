package database;


import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import sun.net.www.content.audio.wav;

public class SAXHandler extends DefaultHandler{
    private ArrayList<Entry> entryList;
    private boolean signal = false;
    private String tagName = null;
    
	public SAXHandler() {
		entryList = new ArrayList<Entry>();
	}
	
	public void setEntryList(ArrayList<Entry> enL) {
		entryList = enL;
	}
	
	public ArrayList<Entry> getEntryList() {
		return entryList;
	}
	
	public void startDocument() throws SAXException {
//        System.out.println("start document   : ");
        
    }

    public void endDocument() throws SAXException {
//        System.out.println("end document     : ");
    }

    public void startElement(String uri, String localName,
        String qName, Attributes attributes)
    throws SAXException {
    	if (qName.equals("article") || qName.equals("inproceedings") || qName.equals("proceedings") 
    			|| qName.equals("book") || qName.equals("incollection") || qName.equals("phdthesis") 
    			|| qName.equals("mastersthesis") || qName.equals("www")) {
    		entryList.add(new Entry());
    		entryList.get(entryList.size() - 1).setPubType(qName);
    	}
    	
    	if (!qName.equals("dblp")) {
    		entryList.get(entryList.size() - 1).getInfoList().add(qName);
    	}
    	tagName = qName;
//        System.out.println("start element    : " + qName);
    }

    public void endElement(String uri, String localName, String qName)
    throws SAXException {
//        System.out.println("end element      : " + qName);
    }

    public void characters(char ch[], int start, int length)
    throws SAXException {
    	if (tagName.equals("author")) {
    		entryList.get(entryList.size() - 1).getAuthor().add(new String(ch, start, length));
    	}
    	if (tagName.equals("title")) {
    		entryList.get(entryList.size() - 1).setPublication(new String(ch, start, length));
    	}
    	if (tagName.equals("year")) {
    		entryList.get(entryList.size() - 1).setPubDate(new String(ch, start, length));
    	}
    	if (tagName.equals("journal") || tagName.equals("booktitle")) {
    		entryList.get(entryList.size() - 1).setVenue(new String(ch, start, length));
    	}
    	entryList.get(entryList.size() - 1).getInfoList().add(new String(ch, start, length));
  
//        System.out.println("start characters : " + new String(ch, start, length));
    }
}
