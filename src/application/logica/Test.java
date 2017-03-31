package application.logica;

import java.util.ArrayList;
import java.util.List;

public class Test {
	private ArrayList<Pregunta> preguntasTest = new ArrayList<Pregunta>();
	private int puntuación;
	private static Test reference = null;
	
	public void addPregunta(Pregunta p){
		preguntasTest.add(p);
	}
	
	public Pregunta getPregunta(int i){
		return preguntasTest.get(i);
	}
	
	public int getNumeroPreguntas(){
		return preguntasTest.size();
	}
	
	public static Test getReference(){
		if(reference == null){
			reference = new Test();
			return reference;
		}else{
			return reference;
		}
	}

	public void setListaPreguntas(ArrayList<Pregunta> preguntas) {
		this.preguntasTest = preguntas;
	}
	
	public ArrayList<Pregunta> getListaPreguntas(){
		return preguntasTest;
	}
}
