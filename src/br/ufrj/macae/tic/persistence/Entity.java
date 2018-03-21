package br.ufrj.macae.tic.persistence;

import java.io.Serializable;


/**
 * Iterface para classe de dominio persistivel
 * @param <PK> tipo de dado do identificador primario
 */
public interface Entity<PK extends Serializable> extends Serializable {

	/**
	 * Retorna identificador primario do objeto
	 * @return identificador primario do objeto
	 */
	PK getId();

	/**
	 * Retorna identificador primario do objeto
	 * @param pk
	 */
	void setId(PK pk);

	/**
     * Retorna se o objeto � novo (ainda n�o foi persistido)
     * @return true o objeto � novo
     */
    boolean isNew();
    
}
