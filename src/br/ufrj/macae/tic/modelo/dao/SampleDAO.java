package br.ufrj.macae.tic.modelo.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.ufrj.macae.tic.persistence.dao.hibernate.AbstractGenericDAOHibernate;
import br.ufrj.macae.tic.persistence.entity.Event;

public class SampleDAO extends AbstractGenericDAOHibernate<Event, Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9097583046367571785L;

	/*
	 * Obter ocorrẽncia ordenado por data e filtrado por projeto
	 */
	public List<Event> getEventOrderByDate(Event event) {

		Criteria crit = getSession().createCriteria(Event.class);
		Criteria subProjectCrit = crit.createCriteria("subproject");
		subProjectCrit.add(Restrictions.eq("id", event.getSubproject().getId()));
		crit.addOrder(Order.desc("eventDate"));

		return crit.list();

	}

	/*
	 * obter evento de coleta ordenado por data
	 */
	public List<Event> getEventOrderByDate() {

		Criteria crit = getSession().createCriteria(Event.class);
		crit.addOrder(Order.desc("eventDate"));

		return crit.list();

	}

	/*
	 * Filtra coleta por data
	 */
	public List<Event> getEventByDate(Date initialDate, Date finalDate, long idSubproject) {


		Criteria crit = getSession().createCriteria(Event.class);
		crit.add(Restrictions.between("eventDate", initialDate, finalDate));	
		crit.addOrder(Order.desc("eventDate"));

		Criteria subProjectCrit = crit.createCriteria("subproject");
		subProjectCrit.add(Restrictions.eq("id", idSubproject));

		return crit.list();

	}

	/*
	 * obtem o último id da coleta cadastrado no BD e incrementa  mais 1 
	 */
	public long getMaxEvent() {

		Criteria crit = getSession().createCriteria(Event.class);
		crit.addOrder(Order.desc("id"));
		Event e = (Event) crit.list().get(0);
		return e.getId() + 1;	

	}

	/*
	 * Obtém coleta por id
	 */
	public List<Event> getEventByEventId(String eventId) {


		Criteria crit = getSession().createCriteria(Event.class);	
		crit.add(Restrictions.eq("eventId", eventId));

		return crit.list();

	}



}
