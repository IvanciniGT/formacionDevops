package login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import ldap.LDAPServices;

@WebServlet("/DoLogin")
public class DoLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final int CADUCIDAD=30*60; // 30 minutos
	private LDAPServices ldap_services;
	private BLDAP dadesLDAP;
	private boolean isInGroup;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String usuari = request.getParameter("usuari");
		String password = request.getParameter("password");
		dadesLDAP= obtenirDadesLDAP();

		ControlSessio controlSessio =validaUsuariLDAP(usuari,password);
		
		if(controlSessio!=null){
			// El login ha sido exitoso
			HttpSession session = request.getSession();
			
			if(isInGroup) {
				session.setAttribute("ControlSessioUsuari", controlSessio);
				session.setAttribute("loginError","");
				
				// Establecer caducidad de la sesión
				session.setMaxInactiveInterval(CADUCIDAD);
				response.sendRedirect("pages/protegides/principal.xhtml");
			} else {

				session.setAttribute("loginError",dadesLDAP.getInfoGrupLDAP());
				response.sendRedirect("pages/publiques/login.xhtml");
			}
			
		} else {
			// El login NO ha sido exitoso
			HttpSession session = request.getSession();
			
			Integer intentsFallits=1;
			
			if (session.getAttribute("loginErrorAttempts")!=null)
				intentsFallits=((Integer)session.getAttribute("loginErrorAttempts"))+1;
			
			session.setAttribute("loginErrorAttempts",intentsFallits);
			
			if (intentsFallits<dadesLDAP.getnIntents())
				session.setAttribute("loginError","El seu usuari o contrassenya s�n incorrectes. Intent: "+intentsFallits);
			else
				session.setAttribute("loginError",dadesLDAP.getInfoBloqueig());
			
			response.sendRedirect("pages/publiques/login.xhtml");
			
		}
		
	}
	
	private ControlSessio validaUsuariLDAP(String usuari, String password)  {
		ControlSessio cs=null;  
		
		//Creating instance of ActiveDirectory
		ldap_services=new LDAPServices(dadesLDAP.getUrl(), dadesLDAP.getDomini());
		
		try {
			//Comprovem si l'usuari i clau d'acces son correctes
			ldap_services.authentication(usuari, password);
			
			// Comprovem que estiguis dins el grup del LDAP
			isInGroup=ldap_services.isInGroup(usuari, "GRP_JVTCASALDONA",  dadesLDAP.getDomainBase());
			
	        
	        // Fiquem al registre d'accessos si el acces es valid o no per falta de permisos al grup de LDAP
			if(isInGroup==true)
				cs=obtenirDadesSessioUsuari(usuari, "S");
	        else  
	        	cs=obtenirDadesSessioUsuari(usuari, "G");
	        
		} catch (ldap.LDAPException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
			// Fiquem al control d'accessos que no es valid
			return obtenirDadesSessioUsuari(usuari, "C");
		} finally {
	        //Closing LDAP Connection
	        try {
	        	ldap_services.closeLdapConnection();
			} catch (ldap.LDAPException e) {
				// TODO Bloque catch generado automaticamente
				e.printStackTrace();
			}
			
		}

		return cs;
	}
	
	
	private ControlSessio obtenirDadesSessioUsuari(String usuari, String accesValid) {
		
		/*
		 * Variable accValid
		 * S: Acces valid
		 * C: Error en la clau d'acces o usuari
		 * G: Error en el grup de LDAP - Sense permisos 
		 */
		
		ControlSessio csu=null;
		
	    try {
	    	
	    	BLogin login=new BLogin();
			login.setUsuari(usuari);
			login.setEsAccessValid(accesValid);
			login.setAplicacio("CasalDona");
			login.setDatahora(new Date());
			
			
	    	Gson gson = new Gson();
			//URL url = new URL("http://localhost:9080/jvtUtilsWeb/javaTeam/login/login");
			URL url = new URL("http://was-srv2.paeria.loc/jvtUtilsWeb/javaTeam/login/login");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");			
			conn.setRequestProperty("Content-Type", MediaType.APPLICATION_JSON);	
			conn.setDoOutput(true); //per a poder enviar paràmetres.
			//conn.setRequestProperty("Accept", MediaType.APPLICATION_JSON);
			//conn.setDoInput(true);
			
		
			// Transformem el pojo a json
			String input = gson.toJson(login) ;

			// enviem el paràmetre d'entrada al servei web.
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode());
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream()), "UTF-8"));
			
			// Llegim la sortida del servei web. És un literal en format json
			String output, strJson;
			strJson=new String();
			
			while ((output = br.readLine()) != null) { System.out.println("\nClient json: "+output); strJson +=output; }

			conn.disconnect();
			
			if (accesValid.equals("S") || accesValid.equals("G")) {
				csu= new ControlSessio();
				// Transformem el literal al pojo
				csu.setSessioUsuari(gson.fromJson(strJson, SessioUsuari.class));
				csu.setUltimaConnexio(new Date(csu.getSessioUsuari().getlDataHora()));
			}
			
		} catch (IOException e) {
			// TODO Bloque catch generado automaticamente
			e.printStackTrace();
		}
	    
		return csu;
	}
	
	
	private BLDAP obtenirDadesLDAP() {
		BLDAP dadesLDAP = new BLDAP();
		try {

	    	Gson gson = new Gson();
			URL url = new URL("http://localhost:9080/jvtUtilsWeb/javaTeam/login/LDAP/jvtCasalDona");
			//URL url = new URL("http://was-srv2.paeria.loc/jvtUtilsWeb/javaTeam/login/LDAP/jvtCasalDona");
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");			
			conn.setRequestProperty("Content-Type", MediaType.APPLICATION_JSON);	
			conn.setDoOutput(true); 
			//per a poder enviar paràmetres.
			//conn.setRequestProperty("Accept", MediaType.APPLICATION_JSON);
			//conn.setDoInput(true);
			
			//String nomAplicacio="jvtCasalDona";
			//String input = gson.toJson(nomAplicacio) ;
			
			// enviem el paràmetre d'entrada al servei web.
			OutputStream os = conn.getOutputStream();	
			//os.write(input.getBytes());
			os.flush();
			

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode());
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream()), "UTF-8"));
			
			// Llegim la sortida del servei web. És un literal en format json
			String output, strJson;
			strJson=new String();
			
			while ((output = br.readLine()) != null) { System.out.println("\nClient json: "+output); strJson +=output; }

			conn.disconnect();
			
			
			// Transformem el literal al pojo
			dadesLDAP=gson.fromJson(strJson, BLDAP.class);
				
			
		} catch (IOException e) {
			// TODO Bloque catch generado automaticamente
			e.printStackTrace();
		};
		return dadesLDAP;
	}

}