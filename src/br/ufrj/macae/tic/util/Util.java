package br.ufrj.macae.tic.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Properties;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import br.ufrj.macae.tic.exception.EmailException;

public class Util {

	public static final String ERRO_ATUALIZAR = "Ocorreu um erro ao atualizar os dados.";	
	public static final String ERRO_EXCLUIR = "Ocorreu um erro ao excluir os dados.";
	public static final String ERRO_INSERIR = "Ocorreu um erro ao cadastrar os dados.";
	public static final String ERRO_GENERICO = "Ocorreu um erro ao executar o procedimento, por favor contacte nosso suporte através do email devtic@macae.ufrj.br e informe o problema.";
	
	
	public static String criptografaSenha(String senha) throws NoSuchAlgorithmException{
		 
        MessageDigest md = MessageDigest.getInstance( "SHA" );  

        md.update( senha.getBytes() );  
        BigInteger hash = new BigInteger( 1, md.digest() );  
        String retornaSenha = hash.toString( 16 );  
        
        return retornaSenha;
	}
	
	public static void enviarEmail(String emailDest, String nomeDest, String assunto,
			String corpo) throws EmailException {

		try {
			String emailRemet = "sistemas@macae.ufrj.br";
			String nomeRemet = "Sistema de Monitoria";

			Properties props = System.getProperties();
			props.put("mail.smtp.host", "vmail.tic.ufrj.br");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");

			Authenticator auth = new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("sistemas@macae.ufrj.br",
							"ufrjMACAE#2014");
				}
			};

			Session session = Session.getInstance(props, auth);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emailRemet, nomeRemet));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					emailDest, nomeDest));
			message.setSubject(assunto);
			//message.setContent(corpo, "text/plain");
			
			message.setContent( corpo, "text/html; charset=iso-8859-1" );  

			Transport.send(message);
			
		} catch (Exception e) {
			throw new EmailException("Erro ao enviar o email.");
		}
	}
	
	public static String gerarSenha() {
		
		double d = Math.random();
		String senha = "SuX" + String.valueOf(d);
		
		return senha;
	}
	
	public static void mostrarMensagem(String mensagem) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage();
        message.setDetail(mensagem);
        message.setSummary("");
        context.addMessage(null, message);
    }
	
	public static String formatarData(Calendar data) {
		
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");	
		return formatador.format( data );
	}
	
	public static Calendar obterDataAtual() {		
		
		Date data = new Date();
		Calendar dataAtual = new GregorianCalendar();
		dataAtual.setTime(data);
		
		return dataAtual;
	}
	
	public static String obterDataHoraFormatada() {
		
		LocalDateTime agora = LocalDateTime.now();
		DateTimeFormatter formatador = DateTimeFormatter
		  .ofLocalizedDateTime(FormatStyle.SHORT)
		  .withLocale(new Locale("pt", "br"));
		
		return agora.format(formatador); //08/04/14 10:02
	}
	
	
    public static Object obterObjetoSesao(String nomeObjeto) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
		
        return session.getAttribute(nomeObjeto);

	}
	
	public static void guardarObjetoSessao(String nameObjeto, Object objetoSessao) {
		
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
     	session.setAttribute("usuario", objetoSessao);
		
	}
	
	
	
	
}
