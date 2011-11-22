/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inout.ejb;

import com.inout.dto.tarjetaDTO;
import javax.ejb.Local;
import javax.ejb.Remote;

/**
 *
 * @author pablo
 */
@Remote
public interface tarjetaLocal {

    public Boolean altaTarjeta(tarjetaDTO tarjeta);
    public tarjetaDTO ObtenerTarjetaID(String id);

    
}
