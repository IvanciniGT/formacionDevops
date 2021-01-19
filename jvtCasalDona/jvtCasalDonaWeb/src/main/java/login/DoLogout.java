package login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/DoLogout")
public class DoLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
    	Boolean logout = Boolean.valueOf(request.getParameter("logout"));
    	if (logout) {
    		if(session != null){
        		session.invalidate();
        	}

        	response.sendRedirect("pages/publiques/login.xhtml");
    	} else {
    		response.sendRedirect("pages/protegides/principal.xhtml");
    	}
    	
		
	}
	
}