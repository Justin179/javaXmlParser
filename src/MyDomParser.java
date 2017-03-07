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

			
			// 處理一個location 
			Node locationNode = locationList.item(0);
			// System.out.println(n.getNodeName()); location
			
			Element locationElement = (Element) locationNode;
			NodeList timeList = locationElement.getElementsByTagName("time");
			System.out.println(timeList.getLength()); // 54
			
			String locationName = locationElement.getElementsByTagName("locationName").item(0).getTextContent(); // 淡海
			String stationId = locationElement.getElementsByTagName("stationId").item(0).getTextContent(); // 11006
			
			int counter = 0;
			for(int i = 0; i<timeList.getLength(); i++){ // loop 54 time node
				counter++;
				Element timeElement = (Element) timeList.item(i);
				String obsTime = timeElement.getElementsByTagName("obsTime").item(0).getTextContent();
				String value = timeElement.getElementsByTagName("value").item(0).getTextContent();
				System.out.print(counter+"  ");
				System.out.println(locationName+"  "+stationId+"  "+obsTime+"  "+value); // 判斷 測站代號+資料日期，若已有資料則不寫入，無資料則寫入
				
			}
			
			
			
			
//			for(int i = 0; i<locationList.getLength(); i++){
//				
//				Node n = locationList.item(0);
//				System.out.println(n.getNodeName());
//				
//				
////				Node node = locationList.item(i);
////				if(node.getNodeType()==Node.ELEMENT_NODE){ // element node, text node...
////					Element location = (Element) node; 
////					NodeList details = location.getChildNodes();
////					for(int j = 0; j<details.getLength(); j++){ // locationName, stationId, time, time, time ...
////						Node n = details.item(j);
////						if(n.getNodeType() == Node.ELEMENT_NODE){
////							Element subTags = (Element) n;
////							System.out.println(subTags.getTagName()); // locationName, stationId, time, time, time ...
////						}
////					}
////				}
//				
//				System.out.println("=============== 一個測站，一組資料 ==============");
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