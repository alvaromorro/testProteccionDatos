package application.logica;

import java.util.ArrayList;
import java.util.List;

public class Test {
	private ArrayList<TestPage> paginasTest = new ArrayList<TestPage>();
	private int puntuación;
	
	public void addPagina(TestPage p){
		paginasTest.add(p);
	}
	
	public TestPage getPagina(int i){
		return paginasTest.get(i);
	}
}
