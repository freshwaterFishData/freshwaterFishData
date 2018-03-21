/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrj.macae.tic.enumeration;

import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;


public enum EnumCategoriaDocente {

    Auxiliar, Assistente, Adjunto, Associado, Titular;

    //reescrevendo do enum para String
    @Override
    public String toString() {
        switch (this) {
            case Auxiliar: {
                return "Auxiliar";
            }
            case Assistente: {
                return "Assistente";
            }
            case Adjunto: {
                return "Adjunto";
            }
            case Associado: {
                return "Associado";
            }
            case Titular: {
                return "Titular";
            }
        }
        return null;
    }

    //reescrevendo da String para o enum
    public static EnumCategoriaDocente getTipoPeloTexto(String texto) {
        if (texto.equals("Auxiliar")) {
            return Auxiliar;
        }
        if (texto.equals("Assistente")) {
            return Assistente;
        }
        if (texto.equals("Adjunto")) {
            return Adjunto;
        }
        if (texto.equals("Associado")) {
            return Associado;
        }
        if (texto.equals("Titular")) {
            return Titular;
        }

        return null;

    }

    public static List<EnumCategoriaDocente> getCombo() {
        List<EnumCategoriaDocente> situacoes = new ArrayList<EnumCategoriaDocente>();
        
   
        for (EnumCategoriaDocente value : EnumCategoriaDocente.values()) {
            situacoes.add(value);
        }

        return situacoes;
    }
}
