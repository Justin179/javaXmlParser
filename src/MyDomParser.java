import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

			// 裝資料用
			List<String[]> list = new ArrayList<String[]>();
			
			// loop each location
			for(int i = 0; i<locationList.getLength(); i++){
				handleOneLocation(locationList, i, list);
			}
			

			System.out.println(list); // 160筆資料拿到
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

	/*
	 *   <location>
   			<locationName>淡海</locationName>
   			<stationId>11006</stationId>
   			<time></time>
   			<time></time>
   		 </location>	
	 */
	private static void handleOneLocation(NodeList locationList, int i, List<String[]> list) {
		// 處理一個location 
		Node locationNode = locationList.item(i);
		
		// Node轉Element for method: getElementsByTagName("time");
		Element locationElement = (Element) locationNode;
		NodeList timeList = locationElement.getElementsByTagName("time");
		// System.out.println(timeList.getLength()); // 54個時間，就要寫54筆資料(一個時間+一個StationId)
		
		// 測站名稱 - 淡海
		String locationName = locationElement.getElementsByTagName("locationName").item(0).getTextContent(); 
		// 測站代號 - 11006
		String stationId = locationElement.getElementsByTagName("stationId").item(0).getTextContent(); 
		
		int counter = 0;
		for(int j = 0; j<timeList.getLength(); j++){ // loop 54 time node
			counter++;
			
			// 一個string array裝一筆資料(含)
			String[] strArray = new String[4];
			
			// 每一個time Element
			Element timeElement = (Element) timeList.item(j);
			
			// 資料日期
			String obsTime = timeElement.getElementsByTagName("obsTime").item(0).getTextContent();
			// 水位高度
			String value = timeElement.getElementsByTagName("value").item(0).getTextContent();
			
			System.out.print(counter+"  ");
			System.out.println(locationName+"  "+stationId+"  "+obsTime+"  "+value); // 判斷 測站代號+資料日期，若已有資料則不寫入，無資料則寫入
			
			strArray[0] = locationName; // 測站名稱
			strArray[1] = stationId; 	// 測站代號
			strArray[2] = obsTime;		// 資料日期
			strArray[3] = value;		// 水位高度
			
			list.add(strArray); // 把資料一筆一筆放到list
		}

	}
	
	
	
	
	

}