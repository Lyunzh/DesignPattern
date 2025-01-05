package org.se.pdf;

import org.se.memento.Order;
import java.io.IOException;

public interface PDFGenerator {
    void generateOrderReport(Order order, String outputPath) throws IOException;
} 