
package br.ufrj.macae.tic.filter;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ufrj.macae.tic.persistence.entity.Person;

public class LoginFilter implements Serializable, Filter {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 3020109704555748529L;
	
	/*
	 * Verifica se o usuário tem permissão para ver uma tela no sistema 
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        Person usuario = (Person) session.getAttribute("usuario");
        
        String url = req.getRequestURI(); 
         
        //obterAcessoDosUsuarios();
                
        if (usuario != null) {
        	chain.doFilter(request, response);
        } else {
        	        	
            HttpServletResponse res = (HttpServletResponse) response;
            res.sendRedirect(req.getContextPath() + "/login/login.jsf");
        }
    }
	

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
    
}

/**
 * outro jeito de fazer
 * public class LoginFilter implements Serializable, Filter{
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response; 
        UserManager userManager = (UserManager) req.getSession().getAttribute("userManager");
        if (userManager == null || !userManager.isLoggedIn()) {
        	// Aqui retorno se não existir...
        	res.sendRedirect(req.getContextPath() + "/index.xhtml");
        } else {
        	// Aqui continua se existir
        	chain.doFilter(request, response); 
        }
}
 */

