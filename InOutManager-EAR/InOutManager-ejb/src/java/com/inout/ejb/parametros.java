/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inout.ejb;

import com.inout.dto.parametroDTO;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author pablo
 */
@Stateless
public class parametros implements parametrosLocal {

    @Override
    public List<parametroDTO> obtenerParametro(String parametro) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<parametroDTO> obtenerParametros(String parametro) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean insertarParametro(parametroDTO parametro) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean modificarParametro(parametroDTO parametro) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
 
}
