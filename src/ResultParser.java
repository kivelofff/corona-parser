import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;



public class ResultParser {
    public static void main(String[] args) {
        ResultParser parser = new ResultParser();
        try {
            parser.getValues();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getValues() throws IOException {
        Document doc = Jsoup.connect("https://www.worldometers.info/coronavirus/")
                            .userAgent("Chrome/4.0.249.0 Safari/532.5")
                            .referrer("http://www.google.com")
                            .get();
        Element tableEntries = doc.select("table#main_table_countries_today").first();
        Elements rows = tableEntries.select("tr");
        for(Element row : rows) {
            if (row.attr("class").equals("odd") || row.attr("class").equals("even")) {
                Elements cells = row.select("td");
                for (Element cell: cells) {
                    String currentCountry = cell.select("a").text();
                    if (currentCountry != null) {
                        System.out.println(currentCountry);
                    }
                }
            }
        }
    }
}
