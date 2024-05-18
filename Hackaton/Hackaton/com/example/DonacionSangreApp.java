package com.example;

    import java.net.Authenticator;
    import java.net.PasswordAuthentication;
    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.sql.Statement;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Properties;

        public class DonacionSangreApp {

    private static final String URL = "https://redvital.000webhostapp.com/";
    private static final String USER = "id22181966_root";
    private static final String PASSWORD = "HackatonM135*";
    private static final Object SERVIDOR_SMTP = null;
    private static final Object PUERTO_SMTP = null;
    protected static final String CORREO_EMISOR = "tuSangreImmporta@gmai.com";
    protected static final String CONTRASEÑA_EMISOR = "HackMor24*";
    private static final Donante donante = null;

    private Connection connect() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar a la base de datos", e);
        }
    }

    public void agregarNuevoDonante(String nombre, int edad, String tipoSangre, double peso, double nivelHierro, String alergias) {
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Donantes (nombre, edad, tipo_sangre, peso, nivel_hierro, alergias) VALUES (?, ?, ?, ?, ?, ?)")) {
            pstmt.setString(1, nombre);
            pstmt.setInt(2, edad);
            pstmt.setString(3, tipoSangre);
            pstmt.setDouble(4, peso);
            pstmt.setDouble(5, nivelHierro);
            pstmt.setString(6, alergias);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Nuevo donante agregado exitosamente!");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al agregar nuevo donante", e);
        }
    }

    public List<Donante> buscarDonantesPorTipoSangre(String tipoSangre) {
        List<Donante> donantes = new ArrayList<>();
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement("SELECT nombre, edad, peso, nivel_hierro, alergias FROM Donantes WHERE tipo_sangre = ?")) {
            pstmt.setString(1, tipoSangre);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");
                double peso = rs.getDouble("peso");
                double nivelHierro = rs.getDouble("nivel_hierro");
                String alergias = rs.getString("alergias");

                Donante donante = new Donante(nombre, edad, tipoSangre, peso, nivelHierro, alergias);
                donantes.add(donante);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar donantes por tipo de sangre", e);
        }
        return donantes;
    }

    public List<Donante> identificarDonantesIdoneos(Paciente paciente, BaseDatosDonantes db) throws SQLException {
        List<Donante> candidatos = db.obtenerDonantesCandidatos(paciente);
        List<Donante> idoneos = new ArrayList<>();

        for (Donante candidato : candidatos) {
            if (algoritmo.esCompatible(paciente, candidato)) {
                idoneos.add(candidato);
            }
        }

        return idoneos;
    }
    
    public static void notificarDonantes(List<Donante> donantes) throws Exception {
        Properties props = configurarPropiedadesCorreo();
        Session session = crearSession(props);
    
        for (String correoElectronico : obtenerCorreosElectronicos(donantes)) {
            try {
                notificarDonante(correoElectronico, session);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    

    private static void notificarDonante(String correoElectronico, Session session) throws Exception {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(CORREO_EMISOR));
        message.addRecipient(Message.RecipientType, new InternetAddress(correoElectronico));
        message.setSubject("Solicitud de donación de sangre");
    
        String body = "Estimado(a) " + Donante.getNombre() + ",\n\n" +
        "Se le contacta porque su perfil de donante coincide con las necesidades de un paciente que requiere una transfusión de sangre de tipo " + donante.getTipoSangre() + ".\n\n" +
        "Su disposición para donar sangre puede salvar una vida. Si está dispuesto(a) a donar, por favor contacte al centro de donación de sangre más cercano o responda a este correo electrónico.\n\n" +
        "Agradecemos su tiempo y su compromiso con la donación de sangre.\n\n" +
        "Atentamente,\n" +
        "Centro de Donación de Sangre";

        message.setText(body);

        Transport.send(message);

        System.out.println("Correo electrónico de notificación enviado a " + Donante.getNombre());
    }
    

    private static List<String> obtenerCorreosElectronicos(List<Donante> donantes) {
        List<String> correosElectronicos = new ArrayList<>();
        for (Donante donante : donantes) {
            correosElectronicos.add(donante.getCorreoElectronico());
        }
        return correosElectronicos;
    }
    

    private static Session crearSession(Properties props) {
        return Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return  PasswordAuthentication(CORREO_EMISOR, CONTRASEÑA_EMISOR);
            }

            private PasswordAuthentication PasswordAuthentication(String correoEmisor, String contraseñaEmisor) {
                
                throw new UnsupportedOperationException("Unimplemented method 'PasswordAuthentication'");
            }
        });
    }
    

    private static Properties configurarPropiedadesCorreo() {
        Properties props = new Properties();
        props.put("mail.smtp.host", SERVIDOR_SMTP);
        props.put("mail.smtp.port", PUERTO_SMTP);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        return props;
    }
    

    public class NotificadorDonante {

        private static final String SERVIDOR_SMTP = "smtp.example.com";
        private static final int PUERTO_SMTP = 587;
        private static final String CORREO_EMISOR = "tuSangreImporta@gmail.com";
        private static final String CONTRASEÑA_EMISOR = "HackaMor23*";
    
        public static void notificarDonante(Donante donante) throws Exception {
    
            Properties props = new Properties();
            props.put("mail.smtp.host", SERVIDOR_SMTP);
            props.put("mail.smtp.port", PUERTO_SMTP);
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");
     
        }

        protected static PasswordAuthentication PasswordAuthentication(String correoEmisor, String contraseñaEmisor) {
        
            throw new UnsupportedOperationException("Unimplemented method 'PasswordAuthentication'");
        }

        public static String getServidorSmtp() {
            return SERVIDOR_SMTP;
        }

        public static int getPuertoSmtp() {
            return PUERTO_SMTP;
        }

        public static String getCorreoEmisor() {
            return CORREO_EMISOR;
        }

        public static String getContraseñaEmisor() {
            return CONTRASEÑA_EMISOR;
        }
    }
    

    @SuppressWarnings("unused")
    public static void main(String[] args) throws Exception {
        BaseDatosDonantes db = new BaseDatosDonantes();
        try (Connection conn = db.connect()) {
            if (conn != null) {
                System.out.println("Conexión a la base de datos establecida correctamente.");
            } else {
                System.err.println("Error al conectar a la base de datos.");
            }
        }
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DonaSangre", "id22181966_root", "HackatonM135*")) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM mytable");

        }
    }
}