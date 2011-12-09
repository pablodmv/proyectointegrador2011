/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inout.ejb;

import com.inout.dto.parametroDTO;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author pablo
 */
@Local
public interface parametrosLocal {

    public List<parametroDTO> obtenerParametro(String parametro);
    public List<parametroDTO> obtenerParametros(String parametro);
    public Boolean insertarParametro(parametroDTO parametro);
    public Boolean modificarParametro(parametroDTO parametro);
}
