package br.ufrj.macae.tic.persistence.dao.factory;

import java.text.MessageFormat;
import java.util.MissingResourceException;

import br.ufrj.macae.tic.persistence.resources.PropertyFile;

/**
 * Mensagens do componente DAOFactory
 *
 */
public enum DAOFactoryMessages implements DAOFactoryConstants {
	/**
	 * Envelope gerada quando ocorre erro ao instanciar DAO
	 */
	INSTANTIATE_ERROR		("daofactory.instantiate.error")
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
	private DAOFactoryMessages(String value) throws MissingResourceException{
		String resourceFileName = RESOURCE_FOLDER + "/" + this.getClass().getSimpleName();
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
