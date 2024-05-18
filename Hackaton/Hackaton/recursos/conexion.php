<?php
//conexión a la base de datos
$servername = "localhost"; // Nombre/IP del servidor
$database ="id22181966_dbredvital";
$username = "id22181966_root";
$password = "HackatonM135*";
// Creamos la conexión

$con = mysqli_connect($servername, $username, $password, $database);
// Comprobamos la conexión

if (!$con) {
    die("La conexión ha fallado: ".mysqli_connect_error());
}
echo "se realizo exitosamente la conexion a la base de datos" .$database;

