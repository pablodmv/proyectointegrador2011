package proy.integrador.jserver;

public class Interpreter {
	
	private String data;
	
	public Interpreter(){
		
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	public String readData(String data){
		this.data = data;
		System.out.println("Obtengo el mensaje desde python: " + data);
		return data;
	}
	

}
