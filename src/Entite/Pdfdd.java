package Entite;

import Services.ServiceUtilisateur;
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
public class Pdfdd {

    public void GeneratePdf(String filename, List<demande_don> l) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException {
        int nb = 0;
        Document document = new Document() {
        };
        PdfWriter.getInstance(document, new FileOutputStream(filename + ".pdf"));
        document.open();
        document.add(new Paragraph("                              Les demandes de dons                    "));
        document.add(new Paragraph("                                                 "));
        document.add(new Paragraph("                                                 "));
        for (demande_don d : l) {
            ServiceUtilisateur se = new ServiceUtilisateur();
            nb++;
            document.add(new Paragraph("           :" + nb + ")"));
            document.add(new Paragraph("            Date  :" + d.getDate_demande()));
            document.add(new Paragraph("            le don :" + d.getType_produit_demande()));
            document.add(new Paragraph("            les remarques :" + d.getRemarques()));
            document.add(new Paragraph("            demandé par: " + se.rechercherparcid_prenom(d.getId_utilisateur()) + " " + se.rechercherparid_nom(d.getId_utilisateur())));
            document.add(new Paragraph("            l'etat:" + d.getEtat()));
            document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));

        }
        document.close();
        Process pro = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + filename + ".pdf");
    }

}
