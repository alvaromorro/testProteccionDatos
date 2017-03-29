package application.logica;

public class Pregunta {
	private int numeroPregunta;
	private String textoPregunta;
	private String respuesta = "";
	

	public int getNumeroPregunta() {
		return numeroPregunta;
	}
	public void setNumeroPregunta(int numeroPregunta) {
		this.numeroPregunta = numeroPregunta;
	}
	public String getTextoPregunta() {
		return textoPregunta;
	}
	public void setTextoPregunta(String textoPregunta) {
		this.textoPregunta = textoPregunta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	public String getRespuesta(){
		return this.respuesta;
	}

	
	
}
