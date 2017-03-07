import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class MyDomParser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder docBuilder = factory.newDocumentBuilder();
			Document doc = docBuilder.parse("people.xml");
			
			NodeList nodes = doc.getElementsByTagName("person");

			
			for(int i = 0; i<nodes.getLength(); i++){
				Node node = nodes.item(i);
				if(node.getNodeType()==Node.ELEMENT_NODE){ // element node, text node...
					Element person = (Element) node; 
					
					NodeList names = person.getChildNodes();
					for(int j = 0; j<names.getLength(); j++){
						Node n = names.item(j);
						if(n.getNodeType() == Node.ELEMENT_NODE){
							Element name = (Element) n;
							System.out.println(name.getTagName() + ":" +name.getTextContent());
						}
					}
					
					
				}
				
				
			}
			

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		

	}

}