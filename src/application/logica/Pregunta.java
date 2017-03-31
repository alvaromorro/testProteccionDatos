package application.logica;

public class Pregunta {
	private int numeroPregunta;
	private String textoPregunta;
	private int respuesta = 0;
	private String recomendacion = "";
	private String grupo = "";
	private int respuetaOk = 1;
	
	public int getRespuetaOk() {
		return respuetaOk;
	}
	public void setRespuetaOk(int respuetaOk) {
		this.respuetaOk = respuetaOk;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
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
	public void setRespuesta(int respuesta) {
		this.respuesta = respuesta;
	}
	public int getRespuesta(){
		return this.respuesta;
	}
	public void setRecomendacion(String recomendacion) {
		this.recomendacion = recomendacion;
	}
	public String getRecomendacion(){
		return this.recomendacion;
	}	
	
	public boolean isCorrect(){
		return respuesta == respuetaOk;
	}
}
