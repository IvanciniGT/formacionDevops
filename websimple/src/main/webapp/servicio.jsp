
<%@ page import="com.curso.Calculadora" %>


<%
    int numero1=Integer.parseInt(request.getParameter("a"));
    int numero2=Integer.parseInt(request.getParameter("b"));
    int suma=Calculadora.suma(numero1,numero2);
%>
{"resultado": <%=suma%>}

