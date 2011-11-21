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

    public String readData(String data) {
        this.data = data;
        String[] datosEnvio = data.split(",");
        System.out.println("Ohon: " + data);
        InOutWebserviceService ws = new InOutWebserviceService();
        InOutWebservice webservice = ws.getInOutWebservicePort();
        String prueba = datosEnvio[3];
        String[] ip = prueba.split(".");
        String armoIdDispositivo ="" ;
        for (int i= 0; i < ip.length; i++) {
            armoIdDispositivo += ip[i];
        }





        Boolean resultado = webservice.addMarca(datosEnvio[1], datosEnvio[2], 10, datosEnvio[4], datosEnvio[0]);
        if (resultado) {
            return "Sucess!";

        }
        return "Fail";
    }
}
