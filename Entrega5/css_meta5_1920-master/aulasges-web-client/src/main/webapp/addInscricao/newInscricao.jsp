<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="model" class="presentation.web.model.InscreveUtenteNaAulaModel" scope="request"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>We need help TT^TT</title>
</head>
<body>



<h2>Inscrever numa Aula</h2>
<form action="inscreverAula" method="post">
    
   <div class="mandatory_field">
		<label for="modalidade">Modalidades:</label>
		<select name="modalidade">  
			<c:forEach var="modalidade" items="${model.modalidades}">
				<c:choose>
    				<c:when test="${model.modalidade == modalidade.id}">
						<option selected = "selected" value="${modalidade.id}">${modalidade.nome}</option>
				    </c:when>
    				<c:otherwise>
						<option value="${modalidade.id}">${modalidade.nome}</option>
				    </c:otherwise>
					</c:choose>
			</c:forEach> 
		</select>
   </div>
   
   
   <div class="mandatory_field">
		<label for="aula">Aulas:</label>
		<select name="aula">  
			<c:forEach var="aula" items="${model.aulas}">
				<c:choose>
    				<c:when test="${model.aula == aula.id}">
						<option selected = "selected" value="${aula.id}">${aula.nome}</option>
				    </c:when>
    				<c:otherwise>
						<option value="${aula.id}">${aula.nome}</option>
				    </c:otherwise>
					</c:choose>
			</c:forEach> 
		</select>
   </div>
   
   <div class="mandatory_field">
    	<label for="tipo">Tipo de Inscricao:</label> 
    	<input type="text" name="tipo" size="50" value="${model.tipo}"/> 
    </div>
   
   <div class="mandatory_field">
    	<label for="numUtente">Numero de Utente:</label> 
    	<input type="text" name="numUtente" size="50" value="${model.numUtente}"/> 
    </div>
   
   
   
   
   <div class="button" align="right">
   		<input type="submit" value="Inscrever Aula">
   </div>
</form>
<c:if test="${model.hasMessages}">
	<p>Mensagens</p>
	<ul>
	<c:forEach var="mensagem" items="${model.messages}">
		<li>${mensagem} 
	</c:forEach>
	</ul>
</c:if>






</body>
</html>