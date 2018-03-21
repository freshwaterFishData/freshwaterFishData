package br.ufrj.macae.tic.persistence.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import br.ufrj.macae.tic.persistence.dao.GenericDAO;

/**
 * 
 * DAO Generico para hibernate
 *
 * @param <T>  classe final 
 * @param <ID> identificador do objeto
 * 
 */
public interface GenericDAOHibernate<T, ID extends Serializable> extends GenericDAO<T, ID>  {

    /**
     * Retorna lista de objetos baseada nos criterios informados
     * @param criterion lista de Hibernate Criterion
     * @return lista de objetos baseada nos criterios informados
     */
//    List<T> pesquisa(Criterion... criterion);

    /**
     * M�todo que "seta" a sess�o do Hibernate
     * @param session
     */
    void setSession(Session session);
    
    /**
     * @return Se��o do Hibernate
     */
    Session getSession();

    /**
     * Retorna lista de objetos, baseado nos atributos preenchidos e
     * numa lista de atributos que devem ser excluidas dos crit�rios
     * @param instanciaExemplo instancia com atributos preenchidos a pesquisar
     * @param maxResults numero maximo de resultados retornados
     * @return lista de objetos
     */
    List<T> pesquisa(T instanciaExemplo, int maxResults) throws Exception;

    /**
     * Retorna lista de objetos, baseado nos atributos preenchidos e
     * numa lista de atributos que devem ser excluidas dos crit�rios
     * @param instanciaExemplo instancia com atributos preenchidos a pesquisar
     * @param pageNumber 
     * @param pageSize 
     * @param maxResults numero maximo de resultados retornados
     * @return lista de objetos
     */
    List<T> pesquisa(T instanciaExemplo, int pageNumber, int pageSize) throws Exception;
    
    /**
     * Retorna lista de objetos, baseado nos atributos preenchidos e
     * numa lista de atributos que devem ser excluidas dos crit�rios.
     * @param instanciaExemplo instancia com atributos preenchidos a pesquisar
     * @param maxResults numero maximo de resultados retornados
     * @param propriedadesExcluidas quais atributos nao incluir na busca
     * @return lista de objetos
     */
    List<T> pesquisa(T instanciaExemplo, String[] propriedadesExcluidas, int maxResults) throws Exception;

    /**
     * Retorna lista de objetos, baseado nos atributos preenchidos e
     * numa lista de atributos que devem ser excluidas dos crit�rios.
     * @param instanciaExemplo instancia com atributos preenchidos a pesquisar
     * @param maxResults numero maximo de resultados retornados
     * @param propriedadesExcluidas quais atributos nao incluir na busca
     * @param pageNumber 
     * @param pageSize 
     * @return lista de objetos
     */
    List<T> pesquisa(T instanciaExemplo, String[] propriedadesExcluidas, int pageNumber, int pageSize) throws Exception;
    
}
