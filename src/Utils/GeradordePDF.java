package Utils;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Classes.Conta;
import SQL.Conexao;
import SQL.SQLConsultaExtrato;

public class GeradordePDF {

    public static void GeraPDF(int NumConta, int numCont) {
        Document doc = null;
        OutputStream outPutStream = null;
        try {
            doc = new Document(PageSize.A4, 30, 20, 20, 30);

            outPutStream = new FileOutputStream("Extrato" + numCont + ".pdf");

            try {
                SQLConsultaExtrato.SQLConsultaExtrato(Conta.numConta);
                PdfWriter.getInstance(doc, outPutStream);
                doc.open();
                Paragraph paragrafo = new Paragraph("Extrato Bancário");
                paragrafo.setAlignment(Element.ALIGN_CENTER);
                Paragraph paragrafo2 = new Paragraph(" ");
                Paragraph nome = new Paragraph("Titular da Conta: " + Conta.usuario + "\n" + "CPF: " + Conta.CPF + "\n" + "Numero da Conta: " + Conta.numConta);
                Paragraph numero = new Paragraph(" ");
                doc.add(paragrafo);
                doc.add(paragrafo2);
                doc.add(nome);
                doc.add(numero);

                PdfPTable tabela = new PdfPTable(4);

                PdfPCell cabecalho = new PdfPCell(new Paragraph("Transações Realizadas"));
                cabecalho.setHorizontalAlignment(Element.ALIGN_CENTER);
                cabecalho.setBorder(PdfPCell.NO_BORDER);
                cabecalho.setBackgroundColor(new BaseColor(100, 150, 200));
                cabecalho.setColspan(4);
                tabela.addCell(cabecalho);
                tabela.addCell("Numero da Conta");
                tabela.addCell("Valor");
                tabela.addCell("Tipo de Transação");
                tabela.addCell("Data");

                // Conexao
                Conexao c = new Conexao();
                // Comandos SQL
                try {
                    PreparedStatement pstmt = c.conectar().prepareStatement("SELECT * FROM `log` where log_NumConta =?");
                    pstmt.setLong(1, NumConta);
                    pstmt.execute();
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        tabela.addCell(rs.getString("log_NumConta"));
                        tabela.addCell(rs.getString("log_valor"));
                        tabela.addCell(rs.getString("log_transacao"));
                        tabela.addCell(rs.getString("log_data"));
                    }
                    doc.add(tabela);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }

            }

            catch (DocumentException e) {
                JOptionPane.showMessageDialog(null, "Erro ao gerar o extrato!" + e);
            } finally {
                doc.close();
            }
            try {
                Desktop.getDesktop().open(new File("Extrato" + numCont + ".pdf"));
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Erro ao gerar o extrato!" + e);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar o extrato!" + e);
        }
    }

}
