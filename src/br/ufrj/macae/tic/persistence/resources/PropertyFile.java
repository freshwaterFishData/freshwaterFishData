package br.ufrj.macae.tic.persistence.resources;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Abstracao das rotinas comuns de acesso a properties
 *
 */
public abstract class PropertyFile {
	/**
	 * erro caso o arquivo properties nao tenha sido encontrado 
	 */
	private static final String ARQUIVO_NAO_ENCONTRADO = "Arquivo properties n�o encontrado.";
	/**
	 * erro caso o valor da chave nao seja encontrado no arquivo properties
	 */
	private static final String CHAVE_NAO_ENCONTRADA = "Chave n�o encontrada no arquivo properties.";

	/**
	 * Retorna valor de entrada relativo ao arquivo properties e chave informados
	 * @param resourceFileName caminho do arquivo properties (relativo ao contexto da aplicacao)
	 * @param key chave
	 * @return valor da chave
	 * @throws MissingResourceException
	 */
	public static String getValue(String resourceFileName, String key) throws MissingResourceException {
		ResourceBundle messageBundle;
		
		try {
			messageBundle = ResourceBundle.getBundle(resourceFileName);
		} catch (MissingResourceException e) {
			throw new MissingResourceException(ARQUIVO_NAO_ENCONTRADO, resourceFileName, key);
		}
		try {
			return messageBundle.getString(key);
		} catch (MissingResourceException e) {
			throw new MissingResourceException(CHAVE_NAO_ENCONTRADA, resourceFileName, key);
		}
	}

}

