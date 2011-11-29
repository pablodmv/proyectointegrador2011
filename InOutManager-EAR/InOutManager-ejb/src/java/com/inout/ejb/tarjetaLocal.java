/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inout.ejb;

import com.inout.dto.tarjetaDTO;
import com.inout.entities.Tarjeta;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;

/**
 *
 * @author pablo
 */
@Remote
public interface tarjetaLocal {

    public Boolean altaTarjeta(tarjetaDTO tarjeta, String userLogin);
    public tarjetaDTO ObtenerTarjetaDTOID(String id, String userLogin);
    public Boolean modificarTarjeta(tarjetaDTO TarjetaDTO, String userLogin);
    public Boolean eliminarTarjeta(tarjetaDTO TarjetaDTO, String userLogin);
    public List<tarjetaDTO> ObtenerTarjetasActivasDTO();
    public Tarjeta convertirDTOTarjeta(tarjetaDTO TarjetaDTO);
    public tarjetaDTO convertirTarjetaDTO(Tarjeta tarjeta);


    
}
