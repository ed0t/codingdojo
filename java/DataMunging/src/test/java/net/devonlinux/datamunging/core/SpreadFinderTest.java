package net.devonlinux.datamunging.core;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class SpreadFinderTest {

    private SpreadFinder finder;

    @Before
    public void setUp() throws Exception {
        finder = SpreadFinder.aSpreadFinder();
    }

    @Test
    public void shouldReturnTheElementWithTheMinimumSpread() throws Exception {
        List<SpreadInformation> data = Arrays.asList(new SpreadInformation("1", 88, 59), new SpreadInformation("2", 79, 63));
        SpreadInformation element = finder.find(data);
        assertThat("The element with the minimum spread is", element.name(), is(equalTo("2")));
        assertThat("The  minimum spread is", element.spread(), is(equalTo(16)));
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowAnExceptionIfTheListIsEmpty() throws Exception {
       finder.find(Collections.EMPTY_LIST);

       fail("A RuntimeException expected");


    }
}
