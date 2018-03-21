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
public enum EnumClasseDocente {

    A, BI, BII, CI, CII, CIII, CIV, DI, DII, DIII, DIV, E;

    //reescrevendo do enum para String
    @Override
    public String toString() {
        switch (this) {
            case A: {
                return "A";
            }
            case BI: {
                return "B-I";
            }
            case BII: {
                return "B-II";
            }
            case CI: {
                return "C-I";
            }
            case CII: {
                return "C-II";
            }
            case CIII: {
                return "C-III";
            }
            case CIV: {
                return "C-IV";
            }
            case DI: {
                return "D-I";
            }
            case DII: {
                return "D-II";
            }
            case DIII: {
                return "D-III";
            }
            case DIV: {
                return "D-IV";
            }
            case E: {
                return "E";
            }
        }
        return null;
    }

    //reescrevendo da String para o enum
    public static EnumClasseDocente getTipoPeloTexto(String texto) {
        if (texto.equals("A")) {
            return A;
        }
        if (texto.equals("B-I")) {
            return BI;
        }
        if (texto.equals("B-II")) {
            return BII;
        }
        if (texto.equals("C-I")) {
            return CI;
        }
        if (texto.equals("C-II")) {
            return CII;
        }
        if (texto.equals("C-III")) {
            return CIII;
        }
        if (texto.equals("C-IV")) {
            return CIV;
        }
        if (texto.equals("D-I")) {
            return DI;
        }
        if (texto.equals("D-II")) {
            return DII;
        }
        if (texto.equals("D-III")) {
            return DIII;
        }
        if (texto.equals("D-IV")) {
            return DIV;
        }
        if (texto.equals("E")) {
            return E;
        }
        return null;

    }

    public static List<EnumClasseDocente> getCombo() {
        List<EnumClasseDocente> situacoes = new ArrayList<EnumClasseDocente>();
        for (EnumClasseDocente value : EnumClasseDocente.values()) {
            situacoes.add(value);
        }

        return situacoes;
    }
}
