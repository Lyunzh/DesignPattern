package org.se.pdf;

import org.se.memento.Order;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
//Refactored with facade pattern
public class PDFGeneratorFacade {
    private Map<String, PDFGenerator> generators;
    
    public PDFGeneratorFacade() {
        generators = new HashMap<>();
        generators.put("itext", new ITextPDFGenerator());
        generators.put("pdfbox", new PDFBoxGenerator());
    }
    
    public void generateReport(String generatorType, Order order, String outputPath) throws IOException {
        PDFGenerator generator = generators.get(generatorType.toLowerCase());
        if (generator == null) {
            throw new IllegalArgumentException("Unsupported PDF generator type: " + generatorType);
        }
        generator.generateOrderReport(order, outputPath);
    }
    
    public void addGenerator(String type, PDFGenerator generator) {
        generators.put(type.toLowerCase(), generator);
    }
} 