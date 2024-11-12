package databaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.kernel.geom.PageSize;

public class DataConnection2 {
    public static void main(String[] args) {
        // Database connection details
        String connectionUrl = "jdbc:sqlserver://DESKTOP-7IA5N30;databaseName=Superstore;encrypt=true;trustServerCertificate=true;";
        String user = "sa";
        String password = "1234";
        Connection con = null;

        // Output PDF file path
        String pdfFilePath = "SuperstoreOrdersReport.pdf";

        try {
            System.out.println("Creating Connection.");
            con = DriverManager.getConnection(connectionUrl, user, password);
            System.out.println("Connection created.");

            // SQL query to retrieve Superstore Orders data
            String query = "SELECT top 100 [Row ID], [Order ID], [Order Date], [Ship Date], [Ship Mode], [Customer ID], " +
                           "[Customer Name], [Segment], [Country], [City], [State], [Postal Code], [Region], " +
                           "[Product ID], [Category], [Sub-Category], [Product Name], [Sales], [Quantity], " +
                           "[Discount], [Profit] FROM [Superstore].[dbo].[Orders] ORDER BY [Row ID]";

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Initialize PDF writer and document with A2 page size
            PdfWriter writer = new PdfWriter(pdfFilePath);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc, PageSize.A1);

            // Add a title to the PDF
            document.add(new Paragraph("Superstore Orders Report"));

            // Define a table with 21 columns for all selected columns
            float[] columnWidths = {1, 1, 2, 2, 2, 1, 2, 1, 1, 2, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1};
            Table table = new Table(columnWidths);

            // Add table header cells
            String[] headers = {"Row ID", "Order ID", "Order Date", "Ship Date", "Ship Mode", "Customer ID",
                                "Customer Name", "Segment", "Country", "City", "State", "Postal Code", 
                                "Region", "Product ID", "Category", "Sub-Category", "Product Name", 
                                "Sales", "Quantity", "Discount", "Profit"};
            for (String header : headers) {
                table.addHeaderCell(new Cell().add(new Paragraph(header)));
            }

            // Process the result set and add data to the table
            while (rs.next()) {
                table.addCell(createCell(rs.getString("Row ID")));
                table.addCell(createCell(rs.getString("Order ID")));
                table.addCell(createCell(rs.getString("Order Date")));
                table.addCell(createCell(rs.getString("Ship Date")));
                table.addCell(createCell(rs.getString("Ship Mode")));
                table.addCell(createCell(rs.getString("Customer ID")));
                table.addCell(createCell(rs.getString("Customer Name")));
                table.addCell(createCell(rs.getString("Segment")));
                table.addCell(createCell(rs.getString("Country")));
                table.addCell(createCell(rs.getString("City")));
                table.addCell(createCell(rs.getString("State")));
                table.addCell(createCell(rs.getString("Postal Code")));
                table.addCell(createCell(rs.getString("Region")));
                table.addCell(createCell(rs.getString("Product ID")));
                table.addCell(createCell(rs.getString("Category")));
                table.addCell(createCell(rs.getString("Sub-Category")));
                table.addCell(createCell(rs.getString("Product Name")));
                table.addCell(createCell(rs.getString("Sales")));
                table.addCell(createCell(rs.getString("Quantity")));
                table.addCell(createCell(rs.getString("Discount")));
                table.addCell(createCell(rs.getString("Profit")));
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

    private static Cell createCell(Object value) {
        return new Cell().add(new Paragraph(value != null ? String.valueOf(value) : "N/A"));
    }
}
