package br.gov.mapa.abcom.infra.exceptions;

/**
 * <p> Enumeração de todas as mensagens de exceções </p>
 */
public enum MessageCode {

	MSG_ERRO_REGISTRO_NAO_ENCONTRADO("Nenhum registro encontrado"),
	MSG_ERRO_REPRESENTANTE_NAO_ENCONTRADO("Nenhum representante encontrado"),
	;

	/**
	 * Error code.
	 */
	private final String descricao;

	/**
	 * <p>Default construtor.</p>
	 *
	 * @param descricao
	 */
	private MessageCode(final String descricao) {
		this.descricao = descricao;
	}

	/*
	 * @see tech.diegohordi.urlshortener.controllers.exceptions.CodigoErro#getCode()
	 */
	public String getDescricao() {
		return descricao;
	}

	/*
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return descricao;
	}
}
