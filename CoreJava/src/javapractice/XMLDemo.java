/*
 * Program to demonstrate XML Reading, writing and modification using Java
 */
package javapractice;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLDemo {

	// Logging setup

	private static Logger fileLogger = LogManager.getLogger(XMLDemo.class.getName());
	
	private static final String FILE_PATH = "./outputs/carparts.xml";

	public static void main(String[] args) {

		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.newDocument();

			// Create XML Document
			Element rootElement = doc.createElement("carParts");
			doc.appendChild(rootElement);

			Element part = doc.createElement("part");
			rootElement.appendChild(part);

			Element partName = doc.createElement("name");
			partName.appendChild(doc.createTextNode("Brake Pad"));
			part.appendChild(partName);

			Element partNumber = doc.createElement("number");
			partNumber.appendChild(doc.createTextNode("BP1234"));
			part.appendChild(partNumber);

			Element partManufacturer = doc.createElement("manufacturer");
			partManufacturer.appendChild(doc.createTextNode("XYZ Industries"));
			part.appendChild(partManufacturer);

			// Save XML document to XML file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			
			// Set properties to output XML in readable format
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(FILE_PATH));
			transformer.transform(source, result);

			// Read XML file
			File inputFile = new File(FILE_PATH);
			Document docRead = builder.parse(inputFile);
			docRead.getDocumentElement().normalize();

			fileLogger.info("Originally created XML:");
			fileLogger.info("Root element: " + docRead.getDocumentElement().getNodeName());
			NodeList nodeList = docRead.getElementsByTagName("part");
			for (int curIdx = 0; curIdx < nodeList.getLength(); curIdx++) {
				Node node = nodeList.item(curIdx);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					fileLogger.info("Part Name: " + element.getElementsByTagName("name").item(0).getTextContent());
					fileLogger.info("Part Number: " + element.getElementsByTagName("number").item(0).getTextContent());
					fileLogger.info("Manufacturer: " + element.getElementsByTagName("manufacturer").item(0).getTextContent());
				}
			}

			// Modify XML File
			for (int curIdx = 0; curIdx < nodeList.getLength(); curIdx++) {
				Node node = nodeList.item(curIdx);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					if (element.getElementsByTagName("name").item(0).getTextContent().equals("Brake Pad")) {
						element.getElementsByTagName("manufacturer").item(0).setTextContent("Malhar Industries");
					}
				}
			}

			fileLogger.info("Modified XML:");

			for (int curIdx = 0; curIdx < nodeList.getLength(); curIdx++) {
				Node node = nodeList.item(curIdx);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					fileLogger.info("Part Name: " + element.getElementsByTagName("name").item(0).getTextContent());
					fileLogger.info("Part Number: " + element.getElementsByTagName("number").item(0).getTextContent());
					fileLogger.info("Manufacturer: " + element.getElementsByTagName("manufacturer").item(0).getTextContent());
				}
			}

			DOMSource updatedSource = new DOMSource(docRead);
			StreamResult updatedResult = new StreamResult(new File(FILE_PATH));
			transformer.transform(updatedSource, updatedResult);

		} catch (ParserConfigurationException | TransformerException | IOException | SAXException e) {
			fileLogger.info(e + " occured");
		}
	}
}
