/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrj.macae.tic.modelo.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.JDBCConnectionException;

import br.ufrj.macae.tic.exception.LoginException;
import br.ufrj.macae.tic.persistence.dao.hibernate.AbstractGenericDAOHibernate;
import br.ufrj.macae.tic.persistence.entity.Person;


/**
 * @author Equipe de desenvolvimento TIC/Macaé 
 * 
 * AbstractGenericDAOHibernate ela tem métodos de inclusão, alteração, exclusão e consulta. Consulta-la antes de criar métodos desse tipo
 * 
 * Use criteria para fazer os métodos necessários: como fazer está em https://docs.jboss.org/hibernate/orm/3.6/reference/pt-BR/html/querycriteria.html
 */
public class PersonDAO extends AbstractGenericDAOHibernate<Person, Long> implements Serializable {  

		
	/**
	 * 
	 */
	private static final long serialVersionUID = -2926807961840085275L;

	/*
	 * Verifica se o login e a senha do usuário estão corretas 
	 */
	public Person autenticarUsuario(Person person) throws LoginException {
		
		try {
			
			Criteria crit = getSession().createCriteria(Person.class);		 
			crit.add(Restrictions.eq("cpf",person.getCpf()));
			crit.add(Restrictions.eq("password",person.getPassword()));
			crit.setMaxResults(1);			
			
			person = (Person) crit.uniqueResult();
			
			if(person == null) {
				throw new LoginException("Login ou senha não conferem.");
			}		
			
		
		} catch (LoginException e) {
			throw e;
		} catch (JDBCConnectionException e) {
			sessionFactory = null;
			autenticarUsuario(person);
		}
		
		return person;

	}
	
	public Person obterProfessorPorCodigo(Person professor) {
		Criteria crit = getSession().createCriteria(Person.class);
		crit.add(Restrictions.eq("professor.codigoProfessor", professor.getId()));
		crit.setMaxResults(1);
		
		return (Person) crit.uniqueResult();
	}

	public List<Person> getPersonOrderbyName() {
		
		Criteria crit = getSession().createCriteria(Person.class);
		crit.addOrder(Order.asc("id"));
		
		return crit.list();
		
	}


}
