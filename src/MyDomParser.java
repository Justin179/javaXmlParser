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

			
			// 拿第一個location
			Node node = locationList.item(0);
			Element location = (Element) node; 
			

			
			
			NodeList details = location.getChildNodes();
//			for(int j = 0; j<details.getLength(); j++){ // locationName, stationId, time, time, time ...
//				Node n = details.item(j);
//				if(n.getNodeType() == Node.ELEMENT_NODE){
//					Element subTags = (Element) n;
//					System.out.println(subTags.getTagName()); 
//					
//				}
//			}
			

			//System.out.println(details.getLength()); // 113 // locationName, stationId, time, time, time ...
			
			/*
			 *  #text
				locationName
				#text
				stationId
				#text
				time
				#text
				time
				#text
				time
			for(int i = 0; i<10; i++){
				Node thirdNode = details.item(i);
				System.out.println(thirdNode.getNodeName());
			}
			*/

			Node timeNode = details.item(5);
			// System.out.println(timeNode.getNodeName()); time
			// <time> Element
			Element timeElement = (Element) timeNode;
			
			Node obsTime = timeElement.getElementsByTagName("obsTime").item(0);
			System.out.println(obsTime.getTextContent()); // 2017-03-06T04:54:00+08:00
			
			Element weatherElement = (Element)timeElement.getElementsByTagName("weatherElement").item(0);
			Node valueNode = weatherElement.getElementsByTagName("value").item(0);
			System.out.println(valueNode.getTextContent()); // 303
			
//			Element timeElement = (Element) timeNode;
//			Node first = timeElement.getFirstChild();
//			System.out.println(first.getNodeName());
//			Node last = timeElement.getLastChild();
//			System.out.println(last.getNodeName());
			
			
			
			
			//System.out.println(time.getNodeName());
			

			
			
			
			

//			for(int i = 0; i<locationList.getLength(); i++){
//				
//				// 取  <locationName>淡海</locationName>
//				// 	 <stationId>11006</stationId>
//	
//				Node node = locationList.item(i);
//				if(node.getNodeType()==Node.ELEMENT_NODE){ // element node, text node...
//					Element location = (Element) node; 
//					NodeList details = location.getChildNodes();
//					for(int j = 0; j<details.getLength(); j++){ // locationName, stationId, time, time, time ...
//						Node n = details.item(j);
//						if(n.getNodeType() == Node.ELEMENT_NODE){
//							Element subTags = (Element) n;
//							System.out.println(subTags.getTagName()); // locationName, stationId, time, time, time ...
//							
//						}
//					}
//					
//				}
//				
//				System.out.println("=============================");
//			}
			

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		

	}

}