package application.logica;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadXML {
	private String url = "src/res/preguntas.xml" ;
	
	
	public void readXML(ArrayList<Pregunta> preguntas){
		try{

			File fXmlFile = new File(url);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
	
			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();
	
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	
			NodeList nList = doc.getElementsByTagName("pregunta");
	
			System.out.println(nList.getLength());
			System.out.println("----------------------------");
	
			for(int i = 0; i <nList.getLength(); i++){
	
					Pregunta pregunta = new Pregunta();
					
					Node nNode = nList.item(i);
		
					System.out.println("\nCurrent Element :" + nNode.getNodeName());
					
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {

						Element eElement = (Element) nNode;
						System.out.println(eElement.getElementsByTagName("numero").item(0).getTextContent());
						pregunta.setNumeroPregunta(Integer.parseInt(eElement.getElementsByTagName("numero").item(0).getTextContent()));
						pregunta.setTextoPregunta(eElement.getElementsByTagName("texto").item(0).getTextContent());
						
						preguntas.add(pregunta);
			
				}
			}
			
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
		}
}
