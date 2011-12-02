/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inout.ejb;

import com.inout.dto.cierreDTO;
import com.inout.entities.Cierre;
import javax.ejb.Stateless;

/**
 *
 * @author pablo
 */
@Stateless
public class cierre implements cierreLocal {
    

    @Override
    public Cierre convertirDTOCierre(cierreDTO CierreDTO){
        Cierre cierre = new Cierre();
        cierre.setAno(CierreDTO.getAno());
        cierre.setId(CierreDTO.getID());
        cierre.setMes(CierreDTO.getMes());
        return cierre;
    }

    @Override
    public cierreDTO convertirCierreDTO(Cierre cierre){
        cierreDTO CierreDTO = new cierreDTO();
        CierreDTO.setAno(cierre.getAno());
        CierreDTO.setID(cierre.getId());
        CierreDTO.setMes(cierre.getMes());
        return CierreDTO;


    }


 
}
