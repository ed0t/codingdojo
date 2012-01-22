package net.devonlinux.datamunging.weather;


import net.devonlinux.datamunging.core.FileExtractor;
import net.devonlinux.datamunging.core.SpreadFinder;
import net.devonlinux.datamunging.core.SpreadInformation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static net.devonlinux.datamunging.core.FileExtractor.aFileExtractor;
import static net.devonlinux.datamunging.core.SpreadFinder.aSpreadFinder;

public class WeatherMunge {
    private FileExtractor extractor;
    private SpreadFinder finder;
    private String regex = "^\\s+([0-9]{1,2})\\s+([0-9]+)\\s+([0-9]+)\\s+.*";

    public WeatherMunge() {
        extractor = aFileExtractor(regex);
        finder = aSpreadFinder();
    }

    SpreadInformation findDayWithMinimumSpread(List<SpreadInformation> data) {
        return finder.find(data);
    }

    public SpreadInformation findDay(String filename) throws IOException, URISyntaxException {
        List<SpreadInformation> information = extractor.parseFile(filename);
        return findDayWithMinimumSpread(information);
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        String filename = "weather.dat";

        WeatherMunge munge = new WeatherMunge();
        SpreadInformation day = munge.findDay(filename);
        System.out.println("The day with the minimum spread is: " + day.name());
    }


}
