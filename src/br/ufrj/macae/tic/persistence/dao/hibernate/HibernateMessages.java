package br.ufrj.macae.tic.persistence.dao.hibernate;

import java.text.MessageFormat;
import java.util.MissingResourceException;

import br.ufrj.macae.tic.persistence.resources.PropertyFile;

/**
 * Mensagens do pacote hibernate
 *
 */
public enum HibernateMessages {
	/**
	 * Envelope gerada quando ocorre erro ao instanciar DAO
	 */
	SESSION_FACTORY_LOOKUP_ERROR		("session.factory.lookup.error"),

	/**
	 * Session nao configurada no DAO antes de seu uso
	 */
	SESSION_NOT_AVAIABLE				("session.not.configured")
	;
	
	/**
	 * valor da chave no arquivo de properties
	 */
	private String messageKey;
	
	/**
	 *  valor do mensagem da chave no arquivo de properties
	 */
	private String messageValue;

	/**
	 * Construtor privado de uso do enum ao criar as "constantes"
	 * @param value
	 * @throws MissingResourceException
	 */
	private HibernateMessages(String value) throws MissingResourceException {
		String resourceFileName = HibernateConstants.RESOURCE_FOLDER + "/" + this.getClass().getSimpleName();
		this.messageKey   = value;
		this.messageValue = PropertyFile.getValue(resourceFileName, messageKey);
	}
	
	/**
	 * retorna o valor original da string nao formatada do arquivo de properties
	 * util quando a mensagem do arquivo properties ja esta completa, isto �,
	 * nao possui argumentos {0}, {1} etc.
	 * @return mensagem
	 * @throws MissingResourceException 
	 */
	public String toString() throws MissingResourceException {
		return messageValue;
	}
	
	/**
	 * Retorna o valor da Envelope com os argumentos passados como parametros.
	 * Ex:
	 * missing.resource.file = arquivo properties n�o encontrado: {0}
	 * {0} ser� substituido pelo args[0] e o resultado � retornado.
	 * @param args argumentos que ser�o formatados na string final
	 * @return string formatada
	 * @throws IllegalArgumentException falha por argumentos invalidos
	 */
	public String toString(Object... args) throws IllegalArgumentException{
		return MessageFormat.format(messageValue, args);
	}

}
