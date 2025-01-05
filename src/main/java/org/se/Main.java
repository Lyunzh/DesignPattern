package org.se;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.se.food.Food;
import org.se.food.French_fries;
import org.se.food.Fried_chicken;
import org.se.food.Pizza;
import org.se.food.adapter.French_friesAdapter;
import org.se.food.adapter.Fried_chickenAdapter;
import org.se.food.adapter.PizzaAdapter;
import org.se.food.interpreter.*;
import org.se.utils.JsoupFactory;
import org.se.utils.JsoupFactoryimpl.FoodJsoupFactory;
import org.se.ui.CommandLineUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
public class Main {
    public static void main(String[] args) throws IOException {
        JsoupFactory foodFactory = new FoodJsoupFactory();
        Document doc = foodFactory.getDocument();
        Elements pizzaElements = doc.getElementsByClass("Pizza");
        ArrayList<Pizza> pizzas = new ArrayList<>();
        for (Element element : pizzaElements) {
            PizzaAdapter pizzaAdapter = new PizzaAdapter();
            pizzaAdapter.TemplateMethod(element);
            pizzas.add(pizzaAdapter.pizza);
        }

        Elements friesElements = doc.getElementsByClass("french-fries");
        ArrayList<French_fries> fries = new ArrayList<>();
        for (Element element : friesElements) {
            French_friesAdapter friesAdapter = new French_friesAdapter();
            friesAdapter.TemplateMethod(element);
            fries.add(friesAdapter.fries);
        }

        Elements chickenElements = doc.getElementsByClass("fried-chicken");
        ArrayList<Fried_chicken> chickens = new ArrayList<>();
        for ( Element element : chickenElements)
        {
            Fried_chickenAdapter Adapter = new Fried_chickenAdapter();
            Adapter.TemplateMethod(element);
            chickens.add(Adapter.fried_chicken);
        }


        // 创建所有食物列表
        List<Food> allFoods = new ArrayList<>();
        allFoods.addAll(pizzas);
        allFoods.addAll(fries);
        allFoods.addAll(chickens);
        
        // 启动命令行界面
        CommandLineUI ui = new CommandLineUI(allFoods);
        ui.start();
    }
}