package br.ufrj.macae.tic.modelo.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.ufrj.macae.tic.persistence.dao.hibernate.AbstractGenericDAOHibernate;
import br.ufrj.macae.tic.persistence.entity.Occurrence;

public class OccurrenceDAO extends AbstractGenericDAOHibernate<Occurrence, Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4978926247760773838L;


	
	/*
	 * Obtém as ocorrências  ordenadas por data
	 */
	public List<Occurrence> getOccurrenceByDate(Occurrence occurrence ,Date finalDate) {

		Criteria crit = getSession().createCriteria(Occurrence.class);
		Criteria eventCrit = crit.createCriteria("event");
		Criteria subProjectCrit = crit.createCriteria("event.subproject");

		subProjectCrit.add(Restrictions.eq("id", occurrence.getEvent().getSubproject().getId()));

		eventCrit.add(Restrictions.between("eventDate", occurrence.getEvent().getEventDate(), finalDate));	
		eventCrit.addOrder(Order.desc("eventDate"));

		return crit.list();

	}

}

