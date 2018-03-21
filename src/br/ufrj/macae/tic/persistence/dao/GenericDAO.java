package br.ufrj.macae.tic.persistence.dao;

import java.io.Serializable;
import java.util.List;

import br.ufrj.macae.tic.persistence.ConstraintException;

/**
 * 
 * Interface generica para DAO.
 * Define como padrao minimo as assinaturas para CRUD - Create, Retrieve(1 ou +), Update, Delete 
 *
 * @param <T> Classe de dominio
 * @param <ID> ID da Classe de dominio
 * 
 */
public interface GenericDAO<T, ID extends Serializable> {

	/**
	 * Retorna classe de dominio relativa ao DAO
	 * @return classe de dominio relativa ao DAO
	 */
	public Class<T> getPersistentClass();
	
	/**
	 * Atualiza um objeto j� persistido
	 * @param objeto objeto
	 */
	void atualiza(T objeto);
	
	/**
	 * Persiste o objeto
	 * @param objeto objeto
	 */
    void insere(T objeto) throws Exception;
    
    
    /**
     * Persiste a lista de objetos
     * @param listaDeObjetos 
     */
    void insere(List<T> listaDeObjetos);
    
    /**
     * Exclui o objeto do banco de dados, mantendo somente o objeto na memoria, no chamado "transient state".
     * @param objeto 
     * @throws ConstraintException 
     */
    void apaga(T objeto) throws ConstraintException;
    
    /**
     * recupera um objeto baseado no id
     * @param id id do objeto
     * @return objeto
     * @throws DAOException 
     */
    T recupera(ID id) throws DAOException;
    
    /**
     * Retorna lista de objetos, baseado nos atributos preenchidos e
     * numa lista de atributos que devem ser excluidas dos crit�rios
     * @param instanciaExemplo
     * @param propriedadesExcluidas
     * @return lista de objetos
     * @throws DAOException 
     */
    List<T> pesquisa(T instanciaExemplo, String[] propriedadesExcluidas) throws DAOException;
    
    /**
     * Retorna lista de objetos baseada nos criterios informados
     * @param instanciaExemplo
     * @return List
     * @throws DAOException 
     */
    List<T> pesquisa(T instanciaExemplo) throws DAOException;

    
}
