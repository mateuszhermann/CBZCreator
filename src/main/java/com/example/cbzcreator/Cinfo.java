package com.example.cbzcreator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Cinfo {
    private String Writter;
    private String Volume;
    private String Series;
    private String XmlCode;
    private String Location;

    public Cinfo(String writter, String volume, String series) {
        Writter = writter;
        Volume = volume;
        Series = series;
        XmlCode=generateXmlCode(writter, volume, series);
    }

    public String generateXmlCode(String writter, String volume, String series){
        String xmlcode=String.format("""
            <?xml version="1.0" encoding="UTF-8"?>
            <ComicInfo xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
                <Series>%s</Series>
                <Writer>%s</Writer>
                <Volume>%s</Volume>
            </ComicInfo>
                """,series,writter,volume);
        System.out.println(xmlcode);
        return  xmlcode;
    }
    public void generateComicInfo() throws IOException {
        String tmpLocation=System.getProperty("java.io.tmpdir");
        File cinfo=new File(tmpLocation+"ComicInfo.xml");
        try {
            FileWriter writer=new FileWriter(cinfo);
            writer.write("""
            <?xml version="1.0" encoding="UTF-8"?>
            <ComicInfo xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
                <Series>%s</Series>
                <Writer>%s</Writer>
                <Volume>%s</Volume>
            </ComicInfo>""");
            writer.close();
            this.Location=cinfo.getPath();

        } catch (IOException e) {
            cinfo.createNewFile();
            generateComicInfo();
        }
        System.out.println(cinfo);
    }

    public static void main(String[] args) {

    }
}
