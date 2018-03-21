package br.ufrj.macae.tic.persistence.dao.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;

import br.ufrj.macae.tic.persistence.ConstraintException;
import br.ufrj.macae.tic.persistence.dao.AbstractGenericDAO;
import br.ufrj.macae.tic.persistence.dao.GenericDAO;

/**
 * 
 * DAO Generico para hibernate
 * 
 * @param <T>
 *            classe final
 * @param <ID>
 *            identificador do objeto
 * 
 */
public abstract class AbstractGenericDAOHibernate<T, ID extends Serializable>
		extends AbstractGenericDAO<T, ID> implements GenericDAOHibernate<T, ID> {

	/**
	 * numero maximo de objetos padr�o retornado pela busca. Zero traz todo
	 * mundo
	 */
	public static final int MAX_RESULTS_DEFAULT = 0;

	/** */
	protected static final String CONSTRAINT_MESSAGE = "N�o � poss�vel excluir {0} devido � uma restri��o de integridade.";

	/**
	 * Sessao do hibernate
	 */
	protected Session session;

	protected static SessionFactory sessionFactory;
	private static Configuration conf;

	/** numero maximo de resultados retornados numa busca */
	protected int maxResults = MAX_RESULTS_DEFAULT;

	/**
	 * Constroi GenericDAOHibernate baseado no tipo da classe final
	 */
	@SuppressWarnings("unchecked")
	public AbstractGenericDAOHibernate() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		
		session = getSession();
	}

	/**
	 * Atalho para persistir a sessao (idem a getSession().flush();)
	 */
	public void flushSession() {
		getSession().flush();
	}

	/**
	 * Retorna a sessao do hibernate
	 * 
	 * @return a sessao do hibernate
	 */
	@SuppressWarnings("deprecation")
	public Session getSession() {
		
		if (sessionFactory == null) {
			conf = new AnnotationConfiguration().configure("/br/ufrj/macae/tic/modelo/hibernate.cfg.xml");
			sessionFactory = conf.buildSessionFactory();	
		} 		

		return sessionFactory.openSession();
	}

	/**
	 * Configura a sessao
	 * 
	 * @param session
	 *            Hibernate
	 */
	public void setSession(Session session) {
		this.session = session;
	}

	/**
	 * Atalho para limpar a sessao (idem a getSession().clear();)
	 */
	public void clearSession() {
		session.clear();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see GenericDAO#recupera(java.io.Serializable)
	 */
	@SuppressWarnings("unchecked")
	public T recupera(ID id) {
		
		try {
			
			session = getSession();
			session.clear();
			T detached = (T) persistentClass.newInstance();
			T lazyCandidate = (T) session.get(persistentClass, id);
			Fetcher.fetchAndDetach(lazyCandidate, detached);
			return (T) detached;

		} catch (Exception e) {
			throw new HibernateException(e);
		} 
	}

	/**
	 * (non-Javadoc)
	 * @throws Exception 
	 * 
	 * @see GenericDAO#insere(java.lang.Object)
	 */
	public void insere(T entity) throws Exception {
		try {
			session = getSession();
			session.beginTransaction();
			//clearSession();
			session.save(entity);
			session.flush();
			session.getTransaction().commit();
			
			session.clear();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}

	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see GenericDAO#insere(java.lang.Object)
	 */
	public void insereOuAtualiza(T entity) {
		try {
			session = getSession();
			session.beginTransaction();
			session.saveOrUpdate(entity);
			session.flush();
			session.getTransaction().commit();
			session.clear();			
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}

	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see GenericDAO#insere(List)
	 * 
	 */
	public void insere(List<T> listaDeObjetos) {
		try {
			
			session = getSession();
			session.beginTransaction();
			for (T t : listaDeObjetos) {
				session.save(t);
			}
			session.flush();
			session.getTransaction().commit();
			session.clear();
		} catch (Exception e) {
			session.getTransaction().rollback();
		}
	}

	public void atualiza(T entity) {

		try {	
			
			session.beginTransaction();			
			session.merge(entity);
			session.flush();
			session.getTransaction().commit();
			session.clear();
			//session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			throw new RuntimeException(e);
		}
	}

	public void apaga(T entity) throws ConstraintException {
		try {
			
			
			session = getSession();
			
			session.beginTransaction();	
			
			session.delete(entity);
			session.flush();
			session.getTransaction().commit();
			session.clear();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			session.clear();
			e.printStackTrace();
			throw new ConstraintException(e);
		}
	}

	/**
	 * Rotina comum �s rotinas de busca. Considera maxResults apenas se o numero
	 * for inteiro positivo.
	 * 
	 * @param instanciaExemplo
	 * @param pageNumber
	 * @param pageSize
	 * @param excludeProperty
	 * @return lista de objetos
	 */
	@SuppressWarnings("unchecked")
	private List<T> _pesquisa(T instanciaExemplo, int pageNumber, int pageSize,
			String... excludeProperty) {
		
		session = getSession();
		Criteria criteria = session.createCriteria(persistentClass);

		if (pageSize > 0 && pageNumber > 0) {
			criteria.setMaxResults(pageSize);
			criteria.setFirstResult((pageNumber - 1) * pageSize);
		}

		Example example = Example.create(instanciaExemplo);
		example.enableLike(MatchMode.ANYWHERE);
		example.ignoreCase();

		if (excludeProperty.length > 0) {
			for (String exclude : excludeProperty) {
				example.excludeProperty(exclude);
			}
		}
		criteria.add(example);

		return criteria.list();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see GenericDAO#pesquisa(java.lang.Object, java.lang.String[])
	 */
	public List<T> pesquisa(T instanciaExemplo, String[] excludeProperty) {
		return _pesquisa(instanciaExemplo, 1, this.maxResults, excludeProperty);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see GenericDAOHibernate#pesquisa(java.lang.Object, java.lang.String[],
	 *      int)
	 */
	public List<T> pesquisa(T instanciaExemplo, String[] excludeProperty,
			int maxResults) {
		return _pesquisa(instanciaExemplo, 1, maxResults, excludeProperty);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see GenericDAOHibernate#pesquisa(java.lang.Object, java.lang.String[],
	 *      int, int)
	 */
	public List<T> pesquisa(T instanciaExemplo, String[] excludeProperty,
			int pageNumber, int pageSize) {
		return _pesquisa(instanciaExemplo, pageNumber, pageSize,
				excludeProperty);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see GenericDAO#pesquisa(java.lang.Object)
	 */
	public List<T> pesquisa(T instanciaExemplo) {
		return _pesquisa(instanciaExemplo, 1, this.maxResults);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see GenericDAOHibernate#pesquisa(java.lang.Object, int)
	 */
	public List<T> pesquisa(T instanciaExemplo, int maxResults) {
		return _pesquisa(instanciaExemplo, 1, maxResults);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see GenericDAOHibernate#pesquisa(java.lang.Object, int, int)
	 */
	public List<T> pesquisa(T instanciaExemplo, int pageNumber, int pageSize) {
		return _pesquisa(instanciaExemplo, pageNumber, pageSize);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see GenericDAOHibernate#pesquisa(org.hibernate.criterion.Criterion[])
	 */
	@SuppressWarnings("unchecked")
	public List<T> pesquisa(Criterion... criterion) {
		Criteria criteria = getSession().createCriteria(persistentClass);
		for (Criterion c : criterion) {
			criteria.add(c);
		}
		return criteria.list();
	}

	/**
	 */
	public int getMaxResults() {
		return maxResults;
	}

	/**
	 * @param maxResults
	 *            the maxResults to set
	 */
	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}

	/**
	 * @param persistentClass
	 *            the persistentClass to set
	 */
	public void setPersistentClass(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}
	
}
