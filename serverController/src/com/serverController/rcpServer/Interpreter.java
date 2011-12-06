package com.serverController.rcpServer;

public class Interpreter {

    private String data;

    public Interpreter() {
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Boolean readData(String data) {
        this.data = data;
        String[] datosEnvio = data.split(",");
        System.out.println("Recibe: " + data);
        InOutWebserviceService ws = new InOutWebserviceService();
        InOutWebservice webservice = ws.getInOutWebservicePort();
  
        try {
            Boolean resultado = webservice.addMarca(datosEnvio[1], datosEnvio[2], datosEnvio[3], datosEnvio[4], datosEnvio[0]);
            if (resultado) {
                return true;

            }else{
                return false;
            }

        } catch (Exception e) {
            System.out.println("No se pudo conecta al webservice " + e.getLocalizedMessage());
            return false;
        }


    }
}
