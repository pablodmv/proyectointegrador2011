/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inout.webservice;

import com.inout.dto.marcaDTO;
import com.inout.ejb.marca;
import com.inout.ejb.marcaLocal;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ejb.Stateless;

/**
 *
 * @author pablo
 */
@WebService()
@Stateless()
public class InOutWebservice {
    @EJB
    marcaLocal marca ;

    /**
     * Web service operation
     */
    @WebMethod(operationName = "AddMarca")
    public Boolean AddMarca(@WebParam(name = "fecha")
    String fecha, @WebParam(name = "hora")
    String hora, @WebParam(name = "id_Dispositivo")
    String id_Dispositivo, @WebParam(name = "descripcion_Dispositivo")
    String descripcion_Dispositivo,  @WebParam(name = "id_persona") String id_persona) {
        marcaDTO MarcaDTO = new marcaDTO(fecha, hora, id_Dispositivo, descripcion_Dispositivo, id_persona);
        return marca.altaMarca(MarcaDTO);

    }







}
