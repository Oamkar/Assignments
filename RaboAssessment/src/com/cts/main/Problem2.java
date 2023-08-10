package com.cts.main;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class Problem2 {

	public static void main(String[] args) {
		String inputFile = "sampleXml.xml";
		String outputFile = "outputXml.xml";

		try {
			// Parsing the input XML file
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new File(inputFile));

			// Removing empty nodes from the document
			removeEmptyNodes(doc);

			// Creating a new XML document for the remaining node names
			Document resultDoc = builder.newDocument();
			Element rootElement = resultDoc.createElement("NodeNames");
			resultDoc.appendChild(rootElement);

			// Extracting node names from the modified document
			extractNodeNames(doc.getDocumentElement(), rootElement);

			// Writing the new XML document to a file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(new DOMSource(resultDoc), new StreamResult(new File(outputFile)));

			System.out.println("New XML file created successfully!");

		} catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
			e.printStackTrace();
		}
	}

	private static void removeEmptyNodes(Node node) {
		NodeList childNodes = node.getChildNodes();
		for (int i = childNodes.getLength() - 1; i >= 0; i--) {
			Node child = childNodes.item(i);

			if (child.getNodeType() == Node.ELEMENT_NODE) {
				removeEmptyNodes(child);

				if (child.getChildNodes().getLength() == 0) {
					node.removeChild(child);
				}
			}
		}
	}

	private static void extractNodeNames(Node node, Element rootElement) {
		NodeList childNodes = node.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node child = childNodes.item(i);

			if (child.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) child;
				Element nodeNameElement = rootElement.getOwnerDocument().createElement("Node");
				nodeNameElement.setTextContent(element.getNodeName());
				rootElement.appendChild(nodeNameElement);

				extractNodeNames(child, rootElement);
			}
		}
	}
}
