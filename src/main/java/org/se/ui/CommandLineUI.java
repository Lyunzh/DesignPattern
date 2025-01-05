package org.se.ui;

import org.se.food.*;
import org.se.memento.Order;
import org.se.memento.OrderHistory;
import org.se.memento.OrderMemento;
import org.se.state.*;
import org.se.food.interpreter.*;
import org.se.pdf.PDFGeneratorFacade;

import java.util.*;
import java.io.IOException;

public class CommandLineUI {
    private List<Food> allFoods;
    private Order currentOrder;
    private OrderHistory history;
    private PDFGeneratorFacade pdfFacade;
    
    public CommandLineUI(List<Food> foods) {
        this.allFoods = foods;
        this.currentOrder = new Order();
        this.history = new OrderHistory();
        this.pdfFacade = new PDFGeneratorFacade();
    }
    
    public void start() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("\n1. Display Food List");
            System.out.println("2. Order Food");
            System.out.println("3. Undo Last Order");
            System.out.println("4. Apply Discount");
            System.out.println("5. Show Current Order");
            System.out.println("6. Generate PDF Report");
            System.out.println("7. Exit");
            
            int choice = scanner.nextInt();
            
            switch(choice) {
                case 1:
                    displayFoodList();
                    break;
                case 2:
                    orderFood();
                    break;
                case 3:
                    undoLastOrder();
                    break;
                case 4:
                    applyDiscount();
                    break;
                case 5:
                    showCurrentOrder();
                    break;
                case 6:
                    generatePDFReport();
                    break;
                case 7:
                    return;
            }
        }
    }
    
    private void displayFoodList() {
        System.out.println("\nSort by:");
        System.out.println("1. Price");
        System.out.println("2. Calories");
        System.out.println("3. Food Type");
        System.out.println("4. Pizza Radius");
        System.out.println("5. Spiciness");
        System.out.println("6. Thickness");
        
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        
        List<Food> sortedFoods = new ArrayList<>(allFoods);
        
        switch(choice) {
            case 1:
                sortedFoods.sort((f1, f2) -> Float.compare(f1.getPrice(), f2.getPrice()));
                break;
            case 2:
                sortedFoods.sort((f1, f2) -> Integer.compare(f1.getCalories(), f2.getCalories()));
                break;
            case 3:
                sortedFoods.sort((f1, f2) -> f1.getClass().getSimpleName().compareTo(f2.getClass().getSimpleName()));
                break;
            case 4:
                sortedFoods.sort((f1, f2) -> {
                    if(f1 instanceof Pizza && f2 instanceof Pizza) {
                        return Integer.compare(((Pizza)f1).getRadius(), ((Pizza)f2).getRadius());
                    }
                    return 0;
                });
                break;
            case 5:
                // 使用解释器模式进行辣度排序
                sortedFoods.sort((f1, f2) -> {
                    if(f1 instanceof Fried_chicken && f2 instanceof Fried_chicken) {
                        SpicinessExpression expr = new SpicinessNonterminalExpression(
                            new SpicinessTerminalExpression("Mild", 1),
                            new SpicinessTerminalExpression("Medium", 2),
                            new SpicinessTerminalExpression("Hot", 3)
                        );
                        Spiciness s1 = new Spiciness(((Fried_chicken)f1).getSpiciness());
                        Spiciness s2 = new Spiciness(((Fried_chicken)f2).getSpiciness());
                        return expr.greatThan(s1, s2);
                    }
                    return 0;
                });
                break;
            case 6:
                // 使用解释器模式进行厚度排序
                sortedFoods.sort((f1, f2) -> {
                    if(f1 instanceof French_fries && f2 instanceof French_fries) {
                        ThicknessExpression expr = new ThicknessNonterminalExpression(
                            new ThicknessTerminalExpression("Thin", 1),
                            new ThicknessTerminalExpression("Regular", 2),
                            new ThicknessTerminalExpression("Thick", 3)
                        );
                        Thickness t1 = new Thickness(((French_fries)f1).getThickness());
                        Thickness t2 = new Thickness(((French_fries)f2).getThickness());
                        return expr.greatThan(t1, t2);
                    }
                    return 0;
                });
                break;
        }
        
        for(int i = 0; i < sortedFoods.size(); i++) {
            System.out.println((i+1) + ". " + sortedFoods.get(i).toString());
        }
    }
    
    private void orderFood() {
        displayFoodList();
        System.out.println("Enter food number to order (0 to finish):");
        Scanner scanner = new Scanner(System.in);
        
        while(true) {
            int choice = scanner.nextInt();
            if(choice == 0) break;
            if(choice > 0 && choice <= allFoods.size()) {
                history.push(currentOrder.save());
                currentOrder.addFood(allFoods.get(choice-1));
                System.out.println("Added to order!");
            }
        }
    }
    
    private void undoLastOrder() {
        OrderMemento memento = history.pop();
        if(memento != null) {
            currentOrder.restore(memento);
            System.out.println("Last order undone!");
        } else {
            System.out.println("No more actions to undo!");
        }
    }
    
    private void applyDiscount() {
        System.out.println("\nAvailable Discounts:");
        System.out.println("1. No Discount");
        System.out.println("2. $50 off when over $100");
        System.out.println("3. 50% off Margherita Pizza");
        
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        
        switch(choice) {
            case 1:
                currentOrder.setDiscountState(new NoDiscountState());
                break;
            case 2:
                currentOrder.setDiscountState(new Over100DiscountState());
                break;
            case 3:
                currentOrder.setDiscountState(new PizzaHalfPriceState());
                break;
        }
        
        System.out.println("Discount applied! New total: $" + currentOrder.getTotal());
    }
    
    private void showCurrentOrder() {
        List<Food> foods = currentOrder.getFoods();
        if(foods.isEmpty()) {
            System.out.println("Order is empty!");
            return;
        }
        
        System.out.println("\nCurrent Order:");
        for(Food food : foods) {
            System.out.println("- " + food);
        }
        System.out.println("Total (with " + currentOrder.getDiscountState().getDiscountName() + 
                          "): $" + currentOrder.getTotal());
    }
    
    private void generatePDFReport() {
        if(currentOrder.getFoods().isEmpty()) {
            System.out.println("Cannot generate report for empty order!");
            return;
        }

        System.out.println("\nChoose PDF Generator:");
        System.out.println("1. iText");
        System.out.println("2. PDFBox");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine(); // 消费换行符

        System.out.println("Enter output path for PDF file:");
        String outputPath = scanner.nextLine();

        try {
            switch(choice) {
                case 1:
                    pdfFacade.generateReport("itext", currentOrder, outputPath);
                    break;
                case 2:
                    pdfFacade.generateReport("pdfbox", currentOrder, outputPath);
                    break;
                default:
                    System.out.println("Invalid choice!");
                    return;
            }
            System.out.println("PDF report generated successfully at: " + outputPath);
        } catch (IOException e) {
            System.out.println("Error generating PDF report: " + e.getMessage());
        }
    }
} 