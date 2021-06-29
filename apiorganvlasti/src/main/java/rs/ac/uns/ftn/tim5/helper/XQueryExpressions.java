package rs.ac.uns.ftn.tim5.helper;

public class XQueryExpressions {


    //XQUERY I XUPDATE ZA ZAHTEV

    public static final String X_QUERY_FIND_ALL_ZAHTEVI_EXPRESSION = "xquery version \"3.1\";\n" +
            "declare default element namespace \"http://ftn.uns.ac.rs/tim5/model/zahtev\";\n" +
            "for $x in collection(\"/db/sample/zahtev\")\n" +
            "return $x";

    public static final String X_UPDATE_REMOVE_ZAHTEV_BY_ID_EXPRESSION =
            "xquery version \"3.1\";\n" +
                    "xmldb:remove('/db/sample/zahtev', '%s')";

    public static final String SEARCH_ZAHTEVI = "xquery version \"3.1\";\n"
            + "declare default element namespace \"http://ftn.uns.ac.rs/tim5/model/zahtev\";\n"
            + "for $x in collection(\"/db/sample/zahtev\")\n"
            + "where $x//*/text()[contains(lower-case(.) ,\"%s\")]\n" + "return $x";


    //XQUERY I XUPDATE ZA OBAVESTENJE

    public static final String X_QUERY_FIND_ALL_OBAVESTENJA_EXPRESSION = "xquery version \"3.1\";\n" +
            "declare default element namespace \"http://ftn.uns.ac.rs/tim5/model/obavestenje\";\n" +
            "for $x in collection(\"/db/sample/obavestenje\")\n" +
            "return $x";

    public static final String X_UPDATE_REMOVE_OBAVESTENJE_BY_ID_EXPRESSION =
            "xquery version \"3.1\";\n" +
                    "xmldb:remove('/db/sample/obavestenje', '%s')";

    public static final String X_QUERY_FIND_OBAVESTENJE_BY_ID_ZAHTEVA_EXPRESSION = "xquery version \"3.1\";\n" +
            "declare default element namespace \"http://ftn.uns.ac.rs/tim5/model/obavestenje\";\n" +
            "for $x in collection(\"/db/sample/obavestenje\")/Obavestenje[@id_zahteva=%d]\n" +
            "return $x";


    //XQUERY I XUPDATE ZA RESENJE

    public static final String X_QUERY_FIND_ALL_RESENJA_EXPRESSION = "xquery version \"3.1\";\n" +
            "declare default element namespace \"http://ftn.uns.ac.rs/tim5/model/resenje\";\n" +
            "for $x in collection(\"/db/sample/resenja\")\n" +
            "return $x";

    public static final String X_UPDATE_REMOVE_RESENJE_BY_ID_EXPRESSION =
            "xquery version \"3.1\";\n" +
                    "xmldb:remove('/db/sample/resenja', '%s')";

    public static final String X_QUERY_FIND_RESENJE_BY_ID_ZALBE_EXPRESSION = "xquery version \"3.1\";\n" +
            "declare default element namespace \"http://ftn.uns.ac.rs/tim5/model/resenje\";\n" +
            "for $x in collection(\"/db/sample/resenja\")/Resenje[@id_zalbe=%d]\n" +
            "return $x";

    public static final String SEARCH_RESENJE= "xquery version \"3.1\";\n"
            + "declare default element namespace \"http://ftn.uns.ac.rs/tim5/model/resenje\";\n"
            + "for $x in collection(\"/db/sample/resenja\")\n"
            + "where $x//*/text()[contains(lower-case(.) ,\"%s\")]\n" + "return $x";


    //XQUERY I XUPDATE ZA IZVESTAJ

    public static final String X_QUERY_FIND_ALL_IZVESTAJI_EXPRESSION = "xquery version \"3.1\";\n" +
            "declare default element namespace \"http://ftn.uns.ac.rs/tim5/model/izvestaj\";\n" +
            "for $x in collection(\"/db/sample/izvestaj\")\n" +
            "return $x";

    public static final String X_UPDATE_REMOVE_IZVESTAJ_BY_ID_EXPRESSION =
            "xquery version \"3.1\";\n" +
                    "xmldb:remove('/db/sample/izvestaj', '%s')";

    public static final String SEARCH_IZVESTAJI = "xquery version \"3.1\";\n"
            + "declare default element namespace \"http://ftn.uns.ac.rs/tim5/model/izvestaj\";\n"
            + "for $x in collection(\"/db/sample/izvestaj\")\n"
            + "where $x//*/text()[contains(lower-case(.) ,\"%s\")]\n" + "return $x";


    //XQUERY I XUPDATE ZA GRADJANINA

    public static final String X_QUERY_FIND_ALL_GRADJANI_EXPRESSION = "xquery version \"3.1\";\n" +
            "declare default element namespace \"http://ftn.uns.ac.rs/tim5/model/gradjanin\";\n" +
            "for $x in collection(\"/db/sample/gradjanin\")\n" +
            "return $x";

    public static final String X_UPDATE_REMOVE_GRADJANIN_BY_ID_EXPRESSION =
            "xquery version \"3.1\";\n" +
                    "xmldb:remove('/db/sample/gradjanin', '%s')";

    public static final String X_QUERY_FIND_GRADJANIN_BY_KORISNICKO_IME = "xquery version \"3.1\";\n"
            + "declare default element namespace \"http://ftn.uns.ac.rs/tim5/model/gradjanin\";\n"
            + "for $x in collection(\"/db/sample/gradjanin\")/Gradjanin\n" + "where $x/korisnicko_ime/text()=\"%s\"\n"
            + "return $x";


    //XQUERY I XUPDATE ZA SLUZBENIKA

    public static final String X_QUERY_FIND_ALL_SLUZBENICI_EXPRESSION = "xquery version \"3.1\";\n" +
            "declare default element namespace \"http://ftn.uns.ac.rs/tim5/model/sluzbenik\";\n" +
            "for $x in collection(\"/db/sample/sluzbenik\")\n" +
            "return $x";

    public static final String X_UPDATE_REMOVE_SLUZBENIK_BY_ID_EXPRESSION =
            "xquery version \"3.1\";\n" +
                    "xmldb:remove('/db/sample/sluzbenik', '%s')";

    public static final String X_QUERY_FIND_SLUZBENIK_BY_KORISNICKO_IME = "xquery version \"3.1\";\n"
            + "declare default element namespace \"http://ftn.uns.ac.rs/tim5/model/sluzbenik\";\n"
            + "for $x in collection(\"/db/sample/sluzbenik\")/Sluzbenik\n" + "where $x/korisnicko_ime/text()=\"%s\"\n"
            + "return $x";

    //XQUERY I XUPDATE ZA ZALBU NA ODLUKU
    public static final String X_QUERY_FIND_ALL_ZALBE_NA_ODLUKU_EXPRESSION = "xquery version \"3.1\";\n" +
            "declare default element namespace \"http://ftn.uns.ac.rs/tim5/model/zalba_na_odluku\";\n" +
            "for $x in collection(\"/db/sample/zalba_na_odluku\")\n" +
            "return $x";

    public static final String X_UPDATE_REMOVE_ZALBA_NA_ODLUKU_BY_ID_EXPRESSION =
            "xquery version \"3.1\";\n" +
                    "xmldb:remove('/db/sample/zalba_na_odluku', '%s')";

    public static final String X_QUERY_FIND_ZALBA_NA_ODLUKU_BY_ID_ZAHTEVA_EXPRESSION = "xquery version \"3.1\";\n" +
            "declare default element namespace \"http://ftn.uns.ac.rs/tim5/model/zalba_na_odluku\";\n" +
            "for $x in collection(\"/db/sample/zalba_na_odluku\")/Zalba_na_odluku[@id_zahteva=%d]\n" +
            "return $x";



    //XQUERY I XUPDATE ZA ZALBU NA CUTANJE

    public static final String X_QUERY_FIND_ALL_ZALBE_CUTANJA_EXPRESSION = "xquery version \"3.1\";\n" +
            "declare default element namespace \"http://ftn.uns.ac.rs/tim5/model/zalba_cutanja\";\n" +
            "for $x in collection(\"/db/sample/zalba_cutanja\")\n" +
            "return $x";

    public static final String X_UPDATE_REMOVE_ZALBA_CUTANJA_BY_ID_EXPRESSION =
            "xquery version \"3.1\";\n" +
                    "xmldb:remove('/db/sample/zalba_cutanja', '%s')";

    public static final String X_QUERY_FIND_ZALBA_CUTANJA_BY_ID_ZAHTEVA_EXPRESSION = "xquery version \"3.1\";\n" +
            "declare default element namespace \"http://ftn.uns.ac.rs/tim5/model/zalba_cutanja\";\n" +
            "for $x in collection(\"/db/sample/zalba_cutanja\")/Zalba_cutanja[@id_zahteva=%d]\n" +
            "return $x";

}
