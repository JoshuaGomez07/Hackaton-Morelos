<?php

include(conex.php);

// Obtener los datos del formulario
$id_donador=1;
$nombre = $_POST["nombre"];
$edad = $_POST["edad"];
$genero = $_POST["genero"];
$email = $_POST["email"];
$telefono = isset($_POST["telefono"]) ? $_POST["telefono"] : ""; // Si no se proporciona, será una cadena vacía
$tipo_sangre = 1;
$id_cp=1;
$comentarios = isset($_POST["comentarios"]) ? $_POST["comentarios"] : ""; // Si no se proporciona, será una cadena vacía

// Preparar la consulta SQL
$sql = "INSERT INTO donador (id_donador, nombre, id_cp, edad, genero, correo_electronico, telefono, tipo_sangre, comentarios) VALUES ($id_dinador, $nombre,$id_cp,  $edad, $genero, $email, $telefono, $tipo_sangre, $comentarios);";
$registrar = mysqli_query($con,$sql);
if ($registrar) {
        ?>
        <h3>Registro Exitoso</h3>
        <?php
?>