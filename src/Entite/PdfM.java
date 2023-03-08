package Entite;

import Entite.Chauffeur;
import com.itextpdf.text.Document;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author AZAYEZ BINSA
 */
public class PdfM {

    public void GeneratePdf(String filename, List<Chauffeur> l) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException {
        int nb = 0;
        Document document = new Document() {
        };
        PdfWriter.getInstance(document, new FileOutputStream(filename + ".pdf"));
        document.open();
        document.add(new Paragraph("                              Les chauffeur                    "));
        document.add(new Paragraph("                                                 "));
        document.add(new Paragraph("                                                 "));
        for (Chauffeur d : l) {
            nb++;
            document.add(new Paragraph("           :" + nb + ")"));
            document.add(new Paragraph("            CIN  :" + d.getCIN()));
            document.add(new Paragraph("            Adresse :" + d.getAdresse()));
            document.add(new Paragraph("            la Statut disponibilite :" + d.getStatut_disponibilite()));
            document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));

        }
        document.close();
        Process pro = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + filename + ".pdf");
    }

}
