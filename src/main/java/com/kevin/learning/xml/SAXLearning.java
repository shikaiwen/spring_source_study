package com.kevin.learning.xml;

import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

/**
 * SAX is simple api for xml 
 * @author root
 *
 */
public class SAXLearning {

	DefaultHandler   dh;
	
	void test1() throws ParserConfigurationException, SAXException, IOException{
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser saxParser = spf.newSAXParser();
		XMLReader reader = saxParser.getXMLReader();
		reader.setContentHandler(new MyContentHandler());
		reader.setErrorHandler(new MyErrorHandler());
		reader.setEntityResolver(new MyEntityResolver());
		InputSource inputSource = new InputSource();
		reader.parse(inputSource);
	}
}

class MyEntityResolver implements EntityResolver{

	public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
		return null;
	}
	
}

class MyErrorHandler implements ErrorHandler{

	public void warning(SAXParseException exception) throws SAXException {
		// TODO Auto-generated method stub
	}

	public void error(SAXParseException exception) throws SAXException {
		// TODO Auto-generated method stub
	}

	public void fatalError(SAXParseException exception) throws SAXException {
		// TODO Auto-generated method stub
	}
}

class MyContentHandler implements ContentHandler{

	public void setDocumentLocator(Locator locator) {
		// TODO Auto-generated method stub
		
	}

	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		
	}

	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		
	}

	public void startPrefixMapping(String prefix, String uri) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	public void endPrefixMapping(String prefix) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	public void processingInstruction(String target, String data) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	public void skippedEntity(String name) throws SAXException {
		// TODO Auto-generated method stub
		
	}
	
}
