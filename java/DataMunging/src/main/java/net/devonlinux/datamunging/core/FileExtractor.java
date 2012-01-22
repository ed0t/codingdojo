package net.devonlinux.datamunging.core;

import com.google.common.collect.Lists;
import com.google.common.io.Resources;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileExtractor {

    private String regex;

    public static FileExtractor aFileExtractor(String regex){
        return new FileExtractor(regex);
    }
    
    
    public FileExtractor(String regex) {
        this.regex = regex;
    }

    public List<SpreadInformation> parseFile(String filename) throws IOException {
        List<SpreadInformation> data = Lists.newArrayList();
        URL resource = Resources.getResource(filename);
        List<String> lines = Resources.readLines(resource, Charset.defaultCharset());
        for (String line : lines) {
            SpreadInformation element = extractFrom(line);
            if(element != null){
                data.add(element);
            }
        }

        return data;
    }

    SpreadInformation extractFrom(String line) {
        SpreadInformation information = null;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        if(matcher.find()){

            String name = matcher.group(1);
            int maxValue = Integer.parseInt(matcher.group(2));
            int minValue = Integer.parseInt(matcher.group(3));
            information = new SpreadInformation(name, maxValue, minValue);

        }
        return information;
    }

}
