<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Endereço</title>
</head>
<body>
	<form action="EnderecoController" method="Post">
		<label>Rua</label><br>
		<input type="text" name="rua"><br>
		<label>Número</label><br>
		<input type="text" name="numero"><br>
		<label>Complemento</label><br>
		<input type="text" name="complemento"><br>
		<label>Referência</label><br>
		<input type="text" name="referencia"><br>
		<label>Cep</label><br>
		<input type="text" name="cep"><br>
		<label>Bairro</label><br>
		<input type="text" name="bairro"><br>
		<label>Cidade</label><br>
		<input type="text" name="cidade"><br>
		<label>Estado</label><br>
		<input type="text" name="estado"><br>
		<label>País</label><br>
		<input type="text" name="pais"><br>
		<br>
		<input type="submit" name="cadastrar" value="Cadastrar"><br>
	</form>
</body>
</html>