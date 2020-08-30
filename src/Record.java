public class Record {
    private String countryName;
    private int totalCases;
    private int newCases;
    private int totDeaths;
    private int totRecovered;
    private int newRecovered;
    private int activeCases;
    private int serious;
    private int totCasesPer1M;
    private int totTest;
    private int totTestper1M;
    private int population;

    public Record(String countryName, int totalCases, int newCases, int totDeaths, int totRecovered, int newRecovered, int activeCases, int serious, int totCasesPer1M, int totTest, int totTestper1M, int population) {
        this.countryName = countryName;
        this.totalCases = totalCases;
        this.newCases = newCases;
        this.totDeaths = totDeaths;
        this.totRecovered = totRecovered;
        this.newRecovered = newRecovered;
        this.activeCases = activeCases;
        this.serious = serious;
        this.totCasesPer1M = totCasesPer1M;
        this.totTest = totTest;
        this.totTestper1M = totTestper1M;
        this.population = population;
    }

    @Override
    public String toString() {
        return countryName + ", " + totalCases + ", " + newCases + ", " + totDeaths + ", " + totRecovered + ", " + newRecovered + ", " + activeCases + ", " + serious + ", " + totCasesPer1M  + ", " + totTest + ", " + totTestper1M + ", " + population;
    }
}
