package org.se.utils;

import org.jsoup.nodes.Document;

import java.io.IOException;

public interface JsoupFactory {

    Document getDocument() throws IOException;

}
