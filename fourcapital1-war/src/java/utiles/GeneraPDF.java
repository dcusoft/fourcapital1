/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utiles;

import com.itextpdf.io.codec.Base64;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.BlockElement;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import dto.ClienteDTO;
import dto.DeudaDTO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;


/**
 *
 * @author Usuario
 */
public class GeneraPDF {

    public StreamedContent crear(ClienteDTO cliente, DeudaDTO deuda) {
        Date f=new Date();
        ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
        PdfWriter pw = new PdfWriter(baos);
        PdfDocument pd = new PdfDocument(pw);
        Document d = new Document(pd, PageSize.A4);
        //            PdfFont font = PdfFontFactory.createFont(FontConstants.COURIER_BOLD);
        System.out.println(getClass().getResource("../recursos/logo.png"));
        Image img = new Image(ImageDataFactory.create(getClass().getResource("../recursos/logo.png")));
        d.add(new Paragraph().add(new Image(ImageDataFactory.create(getClass().getResource("../recursos/logo.png")))));
        d.add(new Paragraph("\n"));
        d.add(new Paragraph("Lima, " + getFechaText(f) + "\n\n").setFontSize(12));
        d.add(new Paragraph("CONSTANCIA DE NO ADEUDO " + deuda.getNuemroCarta()).setFontSize(20).setBold().setTextAlignment(TextAlignment.CENTER));
        d.add(new Paragraph(new Text("\nFOUR CAPITAL S.A.C. ").setBold()).setFontSize(14).setTextAlignment(TextAlignment.JUSTIFIED)
                .add(" con ")
                .add(new Text("RUC 20601268427 ").setBold())
                .add("en calidad de cesionario del ")
                .add(new Text(deuda.getOrigen()==null ? "NULL":deuda.getOrigen()).setBold())
                .add(" expide la presente constancia, informando que a la fecha el titular, ")
                .add(new Text(cliente.getNombre().toUpperCase()).setBold())
                .add(" identificado con ")
                .add(new Text("D.N.I. Nº " + cliente.getIdcliente()).setBold())
                .add(", no mantine saldo deudor con nuestra Empresa por la(s) Obligación(es) Contrato(s) que a continuación se detalla(n):\n\n"));
        Table table = new Table(new float[]{1, 1, 1, 1});
        table.setWidth(UnitValue.createPercentValue(100));
        table.addHeaderCell(new Cell().add(new Paragraph("PRODUCTO").setBold().setFontSize(12).setTextAlignment(TextAlignment.CENTER)));
        table.addHeaderCell(new Cell().add(new Paragraph("#OPERACION").setBold().setFontSize(12).setTextAlignment(TextAlignment.CENTER)));
        table.addHeaderCell(new Cell().add(new Paragraph("DEUDA A LA FECHA").setBold().setFontSize(12).setTextAlignment(TextAlignment.CENTER)));
        table.addHeaderCell(new Cell().add(new Paragraph("SITUACIÓN").setBold().setFontSize(12).setTextAlignment(TextAlignment.CENTER)));
        table.addCell(new Cell().add(new Paragraph(deuda.getProducto()).setFontSize(12).setTextAlignment(TextAlignment.CENTER)));
        table.addCell(new Cell().add(new Paragraph(deuda.getDeudaPK().getIddeuda() + "").setFontSize(12).setTextAlignment(TextAlignment.CENTER)));
        table.addCell(new Cell().add(new Paragraph(deuda.getMonto()).setFontSize(12).setTextAlignment(TextAlignment.CENTER)));
        table.addCell(new Cell().add(new Paragraph(deuda.getEstado()).setFontSize(12).setTextAlignment(TextAlignment.CENTER)));
        d.add(table);
        d.add(new Paragraph("\nEsta(s) obligación(es) fue(ron) en cesiones sucesivas los derechos y acciones por el ").setFontSize(14).setTextAlignment(TextAlignment.JUSTIFIED)
                .add(new Text((deuda.getOrigen()==null ? "NULL":deuda.getOrigen()).toUpperCase()).setBold())
                .add(" el pasado " + getFechaText(deuda.getFecha()) + ".\n"));
        d.add(new Paragraph("Así mismo, certificamos que a la fecha esta obligación ha sido cancelada por el Importe de PAGO TOTAL "
                + "y en consecuencia ya no mantiene a la fecha saldo deudor por la(s) obligación(es) ante(s) citada(s).\n").setFontSize(14).setTextAlignment(TextAlignment.JUSTIFIED));
        d.add(new Paragraph("Se expide en la Ciudad de Lima el " + getFechaText(f) + "\n").setFontSize(14));
        d.add(new Paragraph("Atentamente,\n").setFontSize(14));
        d.add(new Paragraph().add(new Image(ImageDataFactory.create(getClass().getResource("../recursos/firma.png")))));
        d.add(new Paragraph("\nALFREDO VIDAL CALDERON").setBold().setFontSize(14));
        d.add(new Paragraph("GERENTE GENERAL").setBold().setFontSize(14));
        d.close();
        InputStream is=new ByteArrayInputStream(baos.toByteArray());
        return new DefaultStreamedContent(is, "application/pdf", "carta.pdf");
    }

    private String getFechaText(Date f) {
        String fecha,d,m;
//        
        SimpleDateFormat dia=new SimpleDateFormat("EEEE");
        d=dia.format(f);
        d=d.substring(0, 1).toUpperCase()+d.substring(1);
        SimpleDateFormat mes=new SimpleDateFormat("MMMM");
        m=mes.format(f);
        m=m.substring(0, 1).toUpperCase()+m.substring(1);
        SimpleDateFormat sdf=new SimpleDateFormat("'"+d+"' dd "+"'de "+m+" del'"+" YYYY");
        return sdf.format(f);
    }

}
