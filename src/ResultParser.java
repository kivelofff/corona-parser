import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;



public class ResultParser {
    private DbHandler dbHandler;

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
        Set<Record> dayStatistics = new HashSet<>();
        for(Element row : rows) {
            if (row.hasAttr("style") && row.attr("style").equals("")) {
                //System.out.println(row.select("a").text());
                //System.out.println(row);
                //System.out.println("__________________________________________________________________");
                Elements cells = row.select("td");
                int currentSize = cells.size();
                if (currentSize == 19) {
                    String countryName = cells.get(1).text();
                    int totalCases = prepareValue(cells.get(2).text());

                    int newCases = prepareValue(cells.get(3).text());
                    int totDeaths = prepareValue(cells.get(4).text());
                    int newDeaths = prepareValue(cells.get(5).text());
                    int totRecovered = prepareValue(cells.get(6).text());
                    int newRecovered = prepareValue(cells.get(7).text());
                    int activeCases = prepareValue(cells.get(8).text());
                    int serious = prepareValue(cells.get(9).text());
                    int totCasesPer1M = prepareValue(cells.get(10).text());
                    int totDeathsPer1M = prepareValue(cells.get(11).text());
                    int totTest = prepareValue(cells.get(12).text());
                    int totTestPer1M = prepareValue(cells.get(13).text());
                    int population = prepareValue(cells.get(14).text());
                    Record currentRecord = new Record(countryName,totalCases,newCases,totDeaths,newDeaths,totRecovered,newRecovered,activeCases,serious,totCasesPer1M,totDeathsPer1M,totTest,totTestPer1M,population);
                    dayStatistics.add(currentRecord);
                    System.out.println(currentRecord);
                }
            }
        }
        dbHandler = new DbHandler(dayStatistics);
        dbHandler.uploadResults();

    }

    public int prepareValue(String s) {
        int num;
        try {
            num = s.equals("")? 0: Integer.parseInt(s.replaceAll(",",""));
        } catch (NumberFormatException e) {
            num=-1;
        }

        return num;
    }


}
