/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inout.ejb;

import com.inout.entities.Marca;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;

/**
 *
 * @author pablo
 */
@Remote
public interface marcaLocal {

    public String alta(String fecha, String hora, Integer id_dispositivo,String dispositivo, Integer id_pareja, String id_persona);
    public List<Marca> obtenerTodasMarcas(Date fecha);

    
}
