package mtapitest.clients;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import mtapitest.enums.MTErrorCode;
import mtapitest.exceptions.MTException;

public class XMLDocumentParser {

	private Document document;

	public XMLDocumentParser(String xmlFilePath) throws MTException {
		try {
			File xmlFile = new File(xmlFilePath);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			this.document = builder.parse(xmlFile);
		} catch (Exception e) {
			throw new MTException(MTErrorCode.CONFIG_PARSER_FAILED.getValue() + "\n" + e.getMessage());
		}
	}

	public Node getNode(String path) throws Exception {
		return this.getNode(null, path);
	}

	public Node getNode(Node node, String path) throws Exception {
		try {
			if (node == null)
				node = this.document.getDocumentElement();
			NodeList nodeList = node.getChildNodes();
			String[] nodes = path.split("\\.");
			for (int i = 0; i < nodes.length; i++) {
				for (int j = 0; j < nodeList.getLength(); j++) {
					if (nodeList.item(j).getNodeType() == Node.ELEMENT_NODE) {
						if (nodeList.item(j).getNodeName().equals(nodes[i])) {
							node = nodeList.item(j);
							nodeList = node.getChildNodes();
							break;
						} else {
							throw new MTException(String.format(MTErrorCode.CONFIG_NOT_FOUND.getValue(), nodes[i]));
						}
					}
				}
			}
		} catch (Exception e) {
			throw new MTException(MTErrorCode.CONFIG_PARSER_FAILED.getValue() + "\n" + e.getMessage());
		}
		return node;
	}

	public Node getSpecificChildNode(Node node, String childNodeName) {
		NodeList nodeList = node.getChildNodes();
		for (int j = 0; j < nodeList.getLength(); j++) {
			if (nodeList.item(j).getNodeType() == Node.ELEMENT_NODE) {
				if (nodeList.item(j).getNodeName().equals(childNodeName)) {
					return nodeList.item(j);
				}
			}
		}
		return null;
	}
}
