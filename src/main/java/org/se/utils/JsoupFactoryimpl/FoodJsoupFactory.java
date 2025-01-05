package org.se.utils.JsoupFactoryimpl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.se.utils.JsoupFactory;

import java.io.File;
import java.io.IOException;
//Refactored with Factory pattern
public class FoodJsoupFactory implements JsoupFactory
{
    private Document document;

    private final String file= "C:\\Users\\23563\\Desktop\\软件设计模式\\food.html";

    @Override
    public Document getDocument() throws IOException {
        return Jsoup.parse(new File(file),"UTF-8");
    }
}
