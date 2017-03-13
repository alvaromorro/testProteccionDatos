package application.logica;

import java.util.List;

public class TestPage {

	private int resultado;
	private int numeroPreguntas;
	private List<Pregunta> listaPreguntas;
	
	
	public int getResultado() {
		return resultado;
	}
	public void setResultado(int resultado) {
		this.resultado = resultado;
	}
	public int getNumeroPreguntas() {
		return numeroPreguntas;
	}
	public void setNumeroPreguntas(int numeroPreguntas) {
		this.numeroPreguntas = numeroPreguntas;
	}
	public List<Pregunta> getListaPreguntas() {
		return listaPreguntas;
	}
	public void setListaPreguntas(List<Pregunta> listaPreguntas) {
		this.listaPreguntas = listaPreguntas;
	}
}
