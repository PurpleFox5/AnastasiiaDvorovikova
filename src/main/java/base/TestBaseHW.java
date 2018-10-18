package base;

import org.testng.annotations.BeforeSuite;

import java.util.ArrayList;
import java.util.List;

public abstract class TestBaseHW {

    protected static final int COUNT = 4;
    protected static final String MAIN_TEXT = "LOREM IPSUM DOLOR SIT AMET, CONSECTETUR ADIPISICING ELIT, SED DO EIUSMOD" +
            " TEMPOR INCIDIDUNT UT LABORE ET DOLORE MAGNA ALIQUA. UT ENIM AD MINIM VENIAM, QUIS NOSTRUD" +
            " EXERCITATION ULLAMCO LABORIS NISI UT ALIQUIP EX EA COMMODO CONSEQUAT DUIS AUTE IRURE DOLOR IN " +
            "REPREHENDERIT IN VOLUPTATE VELIT ESSE CILLUM DOLORE EU FUGIAT NULLA PARIATUR.";

    protected List<String> headerSectionsList = new ArrayList<>();
    protected List<String> texts = new ArrayList<>();

    {

        headerSectionsList.add("HOME");
        headerSectionsList.add("CONTACT FORM");
        headerSectionsList.add("SERVICE");
        headerSectionsList.add("METALS & COLORS");

        texts.add("To include good practices\nand ideas from successful\nEPAM project");
        texts.add("To be flexible and\ncustomizable");
        texts.add("To be multiplatform");
        texts.add("Already have good base\n(about 20 internal and\nsome external projects),\nwish to get more…");

    }
}
