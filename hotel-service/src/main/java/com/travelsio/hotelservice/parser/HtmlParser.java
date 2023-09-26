package com.travelsio.hotelservice.parser;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Parser;
import org.springframework.stereotype.Component;

@Component
public class HtmlParser {

    private final Parser parser;

    public HtmlParser() {
        ParseSettings parseSettings = new ParseSettings(true, true);
        parser = Parser.htmlParser().settings(parseSettings);
    }

    public Document parse(String url) {
        Document document = null;
        try {
            document = Jsoup.connect(url).parser(parser).get();
        } catch (IOException e) {
            // TODO add custom exception
            e.printStackTrace();
        }
        return document;
    }

}
