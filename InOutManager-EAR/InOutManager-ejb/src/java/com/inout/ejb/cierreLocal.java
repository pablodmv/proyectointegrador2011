/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inout.ejb;

import com.inout.dto.cierreDTO;
import com.inout.entities.Cierre;
import javax.ejb.Local;

/**
 *
 * @author pablo
 */
@Local
public interface cierreLocal {

    public Cierre convertirDTOCierre(cierreDTO CierreDTO);

    public cierreDTO convertirCierreDTO(Cierre cierre);
    
}
