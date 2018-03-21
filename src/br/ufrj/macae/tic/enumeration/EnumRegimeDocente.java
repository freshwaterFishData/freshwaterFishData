/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrj.macae.tic.enumeration;

import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;

/**
 *
 * @author yuegr
 */
public enum EnumRegimeDocente {

    Vinte, Quarenta, DE;

    //reescrevendo do enum para String
    @Override
    public String toString() {
        switch (this) {
            case Vinte: {
                return "20";
            }
            case Quarenta: {
                return "40";
            }
            case DE: {
                return "DE";
            }
        }
        return null;
    }

    //reescrevendo da String para o enum
    public static EnumRegimeDocente getTipoPeloTexto(String texto) {
        if (texto.equals("20")) {
            return Vinte;
        }
        if (texto.equals("40")) {
            return Quarenta;
        }
        if (texto.equals("40DE")) {
            return DE;
        }
        return null;
    }

    public static List<EnumRegimeDocente> getCombo() {
        List<EnumRegimeDocente> situacoes = new ArrayList<EnumRegimeDocente>();
        for (EnumRegimeDocente value : EnumRegimeDocente.values()) {
            situacoes.add(value);
        }

        return situacoes;
    }
}
