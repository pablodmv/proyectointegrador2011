/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ati.manager.inout.beans;

import ati.manager.inout.facade.Facade;
import com.inout.dto.tarjetaDTO;


/**
 *
 * @author gustavo
 */
public class TarjetaBean {

    private String idTarjeta;
    private String tipoTarjeta;
    private String descTarjeta;

    private String msgSuccess;

    private tarjetaDTO selectedCard = new tarjetaDTO();

    /** Creates a new instance of TarjetaBean */
    public TarjetaBean() {
    }

    public String getDescTarjeta() {
        return descTarjeta;
    }

    public void setDescTarjeta(String descTarjeta) {
        this.descTarjeta = descTarjeta;
    }

    public String getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(String idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public String getMsgSuccess() {
        return msgSuccess;
    }

    public void setMsgSuccess(String msgSuccess) {
        this.msgSuccess = msgSuccess;
    }

    public tarjetaDTO getSelectedCard() {
        return selectedCard;
    }

    public void setSelectedCard(tarjetaDTO selectedCard) {
        this.selectedCard = selectedCard;
    }

    

    public String save(){
        this.selectedCard.setId(this.getIdTarjeta());
        this.selectedCard.setTipo(Short.parseShort(this.getTipoTarjeta()));
        this.selectedCard.setDescripcion(this.getDescTarjeta());
        Facade f = Facade.getInstance();
        
        String result = "";

        if(f.saveCard(selectedCard)){
            result = "Ingreso exitoso.";
        }else{
            result = "Error en el ingreso de la tarjeta.";
        }
        this.msgSuccess = result;

        return "";
    }


}
