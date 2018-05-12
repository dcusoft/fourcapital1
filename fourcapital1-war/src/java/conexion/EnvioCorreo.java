/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Usuario
 */
public class EnvioCorreo {

    private String origen;
    private String clave;
    private String servidor;
    private String destino;
    private String asunto;
    private String cc;
    private String cco;
    private String mensaje;
    private String rutaAdjunto;
    private String nombreAdjunto;
    private Properties p;
    private String port;
    private Session s;

    public EnvioCorreo() {
    }

    public EnvioCorreo(Session s) {
        this.s = s;
    }

    public EnvioCorreo(String origen, String clave, String servidor) {
        this.origen = origen;
        this.clave = clave;
        this.servidor = servidor;
        this.p = new Properties();
    }

    public void envio() {
        BodyPart texto = new MimeBodyPart();
        BodyPart adjunto = new MimeBodyPart();
        MimeMultipart m = new MimeMultipart();

        try {
            texto.setContent(mensaje, "text/html");
            m.addBodyPart(texto);
            if (rutaAdjunto != null && !rutaAdjunto.equals("")) {
                adjunto.setDataHandler(new DataHandler(new FileDataSource(rutaAdjunto)));
                adjunto.setFileName(nombreAdjunto);
                m.addBodyPart(adjunto);
            }
            MimeMessage mm = new MimeMessage(s);
//            mm.setFrom(origen);
            mm.addRecipients(Message.RecipientType.TO, destino);
            mm.setSubject(asunto);
//            mm.setSentDate(new Date());
            mm.setContent(m);
            Transport.send(mm);

        } catch (MessagingException ex) {
            Logger.getLogger(EnvioCorreo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Properties getProperties(String servidor) {

        switch (servidor) {
            case "gmail":
                p.put("mail.smtp.host", "smtp.gmail.com");
                p.setProperty("mail.smtp.starttls.enable", "true");
                p.setProperty("mail.smtp.port", (port == null) ? "587" : port);
                p.setProperty("mail.smtp.user", origen);
                p.setProperty("mail.smtp.auth", clave);
        }
        return p;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getCco() {
        return cco;
    }

    public void setCco(String cco) {
        this.cco = cco;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getRutaAdjunto() {
        return rutaAdjunto;
    }

    public void setRutaAdjunto(String rutaAdjunto) {
        this.rutaAdjunto = rutaAdjunto;
    }

    public String getNombreAdjunto() {
        return nombreAdjunto;
    }

    public void setNombreAdjunto(String nombreAdjunto) {
        this.nombreAdjunto = nombreAdjunto;
    }

    public Properties getP() {
        return p;
    }

    public void setP(Properties p) {
        this.p = p;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

}
