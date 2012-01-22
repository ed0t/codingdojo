package net.devonlinux.datamunging.football;

import net.devonlinux.datamunging.core.FileExtractor;
import net.devonlinux.datamunging.core.SpreadFinder;
import net.devonlinux.datamunging.core.SpreadInformation;

import java.io.IOException;
import java.util.List;

import static net.devonlinux.datamunging.core.FileExtractor.aFileExtractor;
import static net.devonlinux.datamunging.core.SpreadFinder.aSpreadFinder;

public class FootballMunge {

    private final FileExtractor extractor;

    private final String regex = "^\\s+[0-9]+\\. ([a-zA-Z_]+)\\s+[0-9]{1,2}\\s+[0-9]{1,2}\\s+[0-9]{1,2}\\s+[0-9]{1,2}\\s+([0-9]{1,2})\\s+-\\s+([0-9]{1,2}).*";
    private final SpreadFinder finder;

    public FootballMunge() {
        extractor = aFileExtractor(regex);
        finder = aSpreadFinder();
    }

    SpreadInformation findTeamWithMinimumSpread(List<SpreadInformation> data) {
        return finder.find(data);
    }

    public SpreadInformation findTeam(String filename) throws IOException {
        List<SpreadInformation> information = extractor.parseFile(filename);
        return findTeamWithMinimumSpread(information);
    }


    public static  void main (String [] args) throws IOException {
        String filename = "football.dat";

        FootballMunge munge = new FootballMunge();
        SpreadInformation footballSpread = munge.findTeam(filename);
        System.out.println("The team with the minimum spread is: " + footballSpread.name()+ " and the minimum spread is "+footballSpread.spread());

    }

}
