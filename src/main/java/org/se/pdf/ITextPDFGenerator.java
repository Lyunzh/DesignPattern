package org.se.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.se.food.Food;
import org.se.memento.Order;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ITextPDFGenerator implements PDFGenerator {
    @Override
    public void generateOrderReport(Order order, String outputPath) throws IOException {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(outputPath));
            document.open();
            
            // 添加标题
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph title = new Paragraph("Order Report", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph("\n"));
            
            // 添加订单详情
            List<Food> foods = order.getFoods();
            float totalCalories = 0;
            
            document.add(new Paragraph("Ordered Items:", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
            for(Food food : foods) {
                document.add(new Paragraph(String.format(
                    "- %s\n  Price: $%.2f\n  Calories: %d\n  Weight: %dg\n  %s\n",
                    food.getName(),
                    food.getPrice(),
                    food.getCalories(),
                    food.getWeight(),
                    food.getFeatures()
                )));
                totalCalories += food.getCalories();
            }
            
            // 添加折扣信息
            document.add(new Paragraph("\nDiscount Applied: " + order.getDiscountState().getDiscountName()));
            
            // 添加总计信息
            document.add(new Paragraph(String.format(
                "\nOrder Summary:\nTotal Price: $%.2f\nTotal Calories: %.0f",
                order.getTotal(),
                totalCalories
            )));
            
        } catch (DocumentException e) {
            throw new IOException("Error generating PDF", e);
        } finally {
            document.close();
        }
    }
} 