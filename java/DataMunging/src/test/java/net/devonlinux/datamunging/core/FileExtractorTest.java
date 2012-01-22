package net.devonlinux.datamunging.core;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class FileExtractorTest {

    private final String regex = "^\\s+([0-9]{1,2})\\s+([0-9]+)\\s+([0-9]+)\\s+.*";
    private FileExtractor extractor;

    @Before
    public void setUp() throws Exception {
        extractor = new FileExtractor(regex);
    }

    @Test
    public void shouldExtractDayAndCalculateSpread() throws Exception {
        String line = "   1  88    59    74          53.8       0.00 F       280  9.6 270  17  1.6  93 23 1004.5";
        SpreadInformation information = extractor.extractFrom(line);
        assertThat("The day of the month is ", information.name(), is(equalTo("1")));
        assertThat("The spread is ", information.spread(), is(equalTo(29)));
    }

    @Test
    public void shouldReturnAnEmptyArrayIfTheLineDoesNotHaveTheExpectedFormat() throws Exception {
        String line = "  Dy MxT   MnT   AvT   HDDay  AvDP 1HrP TPcpn WxType PDir AvSp Dir MxS SkyC MxR MnR AvSLP";
        SpreadInformation information = extractor.extractFrom(line);
        assertThat("There are no information ", information, is(equalTo(null)));
    }


    @Test
    public void shouldParseAFile() throws Exception {
        String filename = "weather.dat";
        FileExtractor extractor = new FileExtractor(regex);
        List<SpreadInformation> data = extractor.parseFile(filename);
        assertThat("The number of element is ", data.size(), is(equalTo(5)));

    }
}
