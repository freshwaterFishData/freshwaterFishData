package br.ufrj.macae.tic.modelo.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import br.ufrj.macae.tic.persistence.dao.hibernate.AbstractGenericDAOHibernate;
import br.ufrj.macae.tic.persistence.entity.WaterBody;

public class WaterBodyDAO extends AbstractGenericDAOHibernate<WaterBody, Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9002737509937165668L;
	
	public List<WaterBody> getWaterBodyOrder() {
		
		Criteria crit = getSession().createCriteria(WaterBody.class);
		crit.addOrder(Order.asc("waterBody"));
		
		return crit.list();
		
	}

}
