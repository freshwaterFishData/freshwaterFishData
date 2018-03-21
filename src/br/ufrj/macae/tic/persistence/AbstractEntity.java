package br.ufrj.macae.tic.persistence;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Abstracao de classes de dominio persistiveis
 *
 */
@MappedSuperclass
public abstract class AbstractEntity implements Entity<Long> {
    
	/** necessario para serializable */
	private static final long serialVersionUID = 1L;
	
	/** identificador */
	@Id
	@Column(name="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    
    /**
     * Retorna PK
     * @return PK
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Atribui PK
     * @param pk PK
     */
    public void setId(Long pk) {
    	this.id = pk;
	}

	/**
     * Informa se o objeto � novo
     * @return true se o objeto � novo (n�o possui ID)
     */
    public boolean isNew() {
        return (this.id == null);
    }

    /**
     * Informa se dois objetos s�o iguais.
     * @return true se dois objetos s�o iguais. 
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public abstract boolean equals(Object object);

    /** 
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    public abstract int hashCode();

}
