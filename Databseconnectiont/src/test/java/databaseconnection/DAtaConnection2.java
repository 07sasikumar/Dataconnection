package databaseconnection;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.kernel.geom.PageSize;
//import com.itextpdf.layout.property.UnitValue; // Add this import for UnitValue

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAtaConnection2 {
    public static void main(String[] args) {
        String connectionUrl = "jdbc:sqlserver://DESKTOP-7IA5N30;databaseName=BPS;encrypt=true;trustServerCertificate=true;";
        String user = "sa";
        String password = "1234";
        Connection con = null;

        // Output PDF file path
        String pdfFilePath = "AadharData.pdf";

        try {
            System.out.println("Creating Connection.");
            con = DriverManager.getConnection(connectionUrl, user, password);
            System.out.println("Connection created.");

            // Define the SQL query to retrieve the data
            String query = "SELECT AadharId, FirstName, LastName, MobileNumber, BioMetric, " +
                           "Gender, Status,Address,Pincode, CreatedDate, LastModifiedDate, LastModifiedBy " +
                           "FROM BPS.dbo.Aadhar";
// Resut fileter 
//            String query = "SELECT AadharId, FirstName, LastName, MobileNumber, BioMetric, " +
//                    "Gender, Status,Address,Pincode, CreatedDate, LastModifiedDate, LastModifiedBy " +
//                    "FROM BPS.dbo.Aadhar where [AadharId] = 123456789101";

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Initialize PDF writer and document with A3 page size
            PdfWriter writer = new PdfWriter(pdfFilePath);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc, PageSize.A3);

            // Add a title to the PDF
            document.add(new Paragraph("Aadhar Data Report "));

            // Define a table with 12 columns and specify column widths
            float[] columnWidths = {1, 3, 2, 2, 2, 1, 1, 3, 1, 2, 2, 2}; // Adjust these widths as needed
            Table table = new Table(columnWidths);

            // Set table width to fill the page or use a specific width
         //   table.setWidth(UnitValue.createPercentValue(100)); // Table fills 100% of the available width

            // Add header cells to the table
            table.addHeaderCell(new Cell().add(new Paragraph("AadharId")));
            table.addHeaderCell(new Cell().add(new Paragraph("First_Name")));
            table.addHeaderCell(new Cell().add(new Paragraph("Last_Name")));
            table.addHeaderCell(new Cell().add(new Paragraph("Mobile_Number")));
            table.addHeaderCell(new Cell().add(new Paragraph("Bio_Metric")));
            table.addHeaderCell(new Cell().add(new Paragraph("Gender")));
            table.addHeaderCell(new Cell().add(new Paragraph("Status")));
            table.addHeaderCell(new Cell().add(new Paragraph("Address")));
            table.addHeaderCell(new Cell().add(new Paragraph("Pincode")));
            table.addHeaderCell(new Cell().add(new Paragraph("Created_Date")));
            table.addHeaderCell(new Cell().add(new Paragraph("Last_Modified_Date")));
            table.addHeaderCell(new Cell().add(new Paragraph("Last_Modified_By")));

            // Process the result set and add data to the table
            while (rs.next()) {
                table.addCell(createCell(rs.getObject("AadharId")));
                table.addCell(createCell(rs.getString("FirstName")));
                table.addCell(createCell(rs.getString("LastName")));
                table.addCell(createCell(rs.getString("MobileNumber")));
                table.addCell(createCell(rs.getString("BioMetric")));
                table.addCell(createCell(rs.getString("Gender")));
                table.addCell(createCell(rs.getString("Status")));
                table.addCell(createCell(rs.getString("Address")));
                table.addCell(createCell(rs.getString("Pincode")));
                table.addCell(createCell(rs.getString("CreatedDate")));
                table.addCell(createCell(rs.getString("LastModifiedDate")));
                table.addCell(createCell(rs.getString("LastModifiedBy")));
            }

            // Add the table to the document
            document.add(table);

            // Close document and resources
            document.close();
            rs.close();
            stmt.close();

            System.out.println("PDF created successfully at: " + pdfFilePath);

        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                    System.out.println("Connection closed.");
                } catch (SQLException e) {
                    System.err.println("Failed to close connection: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }

    private static Cell createCell(String value) {
        return new Cell().add(new Paragraph(value != null ? value : "N/A"));
    }

    private static Cell createCell(Object value) {
        return new Cell().add(new Paragraph(value != null ? String.valueOf(value) : "N/A"));
    }
}
