package org.se.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.se.food.Food;
import org.se.memento.Order;

import java.io.IOException;
import java.util.List;
//Refactored with Facade pattern
public class PDFBoxGenerator implements PDFGenerator {
    @Override
    public void generateOrderReport(Order order, String outputPath) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                // 设置标题
                contentStream.beginText();
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 18);
                contentStream.newLineAtOffset(220, 750);
                contentStream.showText("Order Report");
                contentStream.endText();

                // 添加订单详情
                List<Food> foods = order.getFoods();
                float totalCalories = 0;
                float yPosition = 700;

                contentStream.beginText();
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 14);
                contentStream.newLineAtOffset(50, yPosition);
                contentStream.showText("Ordered Items:");
                contentStream.endText();

                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
                for(Food food : foods) {
                    yPosition -= 20;
                    contentStream.beginText();
                    contentStream.newLineAtOffset(50, yPosition);
                    contentStream.showText(food.getName());
                    contentStream.endText();

                    yPosition -= 15;
                    contentStream.beginText();
                    contentStream.newLineAtOffset(70, yPosition);
                    contentStream.showText(String.format("Price: $%.2f, Calories: %d, Weight: %dg",
                            food.getPrice(), food.getCalories(), food.getWeight()));
                    contentStream.endText();

                    totalCalories += food.getCalories();
                }

                // 添加折扣信息
                yPosition -= 30;
                contentStream.beginText();
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
                contentStream.newLineAtOffset(50, yPosition);
                contentStream.showText("Discount Applied: " + order.getDiscountState().getDiscountName());
                contentStream.endText();

                // 添加总计信息
                yPosition -= 30;
                contentStream.beginText();
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);
                contentStream.newLineAtOffset(50, yPosition);
                contentStream.showText(String.format("Total Price: $%.2f", order.getTotal()));
                contentStream.endText();

                yPosition -= 20;
                contentStream.beginText();
                contentStream.newLineAtOffset(50, yPosition);
                contentStream.showText(String.format("Total Calories: %.0f", totalCalories));
                contentStream.endText();
            }

            document.save(outputPath);
        }
    }
} 