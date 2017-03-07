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
			Document doc = docBuilder.parse("TIDE_P_02_0306010_cwbrain.xml");
			
			NodeList locationList = doc.getElementsByTagName("location");


			for(int i = 0; i<locationList.getLength(); i++){
				
				// 取  <locationName>淡海</locationName>
				// 	 <stationId>11006</stationId>
	
				Node node = locationList.item(i);
				if(node.getNodeType()==Node.ELEMENT_NODE){ // element node, text node...
					Element location = (Element) node; 
					NodeList details = location.getChildNodes();
					for(int j = 0; j<details.getLength(); j++){ // locationName, stationId, time, time, time ...
						Node n = details.item(j);
						if(n.getNodeType() == Node.ELEMENT_NODE){
							Element subTags = (Element) n;
							System.out.println(subTags.getTagName()); // locationName, stationId, time, time, time ...
							
						}
					}
					
				}
				
				System.out.println("=============================");
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