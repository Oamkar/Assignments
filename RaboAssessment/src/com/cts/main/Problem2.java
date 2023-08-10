package com.cts.main;

import org.apache.log4j.Logger;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class Problem2 {

	private Logger log = Logger.getLogger(Problem2.class.getName());
	
	public void removeEmptyNodesFromXmlFile() {
		String inputFilePath = "sampleXml.xml";
		String outputFilePath = "outputXml.xml";

		try {
			// Parsing the input XML file
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new File(inputFilePath));

			// Removing empty nodes from the document
			Utilities.removeEmptyNodes(doc);

			// Creating a new XML document for the remaining node names
			Document resultDoc = builder.newDocument();
			Element rootElement = resultDoc.createElement("NodeNames");
			resultDoc.appendChild(rootElement);

			// Extracting node names from the modified document
			Utilities.extractNodeNames(doc.getDocumentElement(), rootElement);

			// Writing the new XML document to a file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(new DOMSource(resultDoc), new StreamResult(new File(outputFilePath)));

			log.info("New XML file created successfully! Output file: "+ outputFilePath);

		} catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
			log.error("Error occured while processing XML file : ", e);
		}
	}
}
