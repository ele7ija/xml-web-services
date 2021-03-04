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


    //XQUERY I XUPDATE ZA OBAVESTENJE

    public static final String X_QUERY_FIND_ALL_OBAVESTENJA_EXPRESSION = "xquery version \"3.1\";\n" +
            "declare default element namespace \"http://ftn.uns.ac.rs/tim5/model/obavestenje\";\n" +
            "for $x in collection(\"/db/sample/obavestenje\")\n" +
            "return $x";

    public static final String X_UPDATE_REMOVE_OBAVESTENJE_BY_ID_EXPRESSION =
            "xquery version \"3.1\";\n" +
                    "xmldb:remove('/db/sample/obavestenje', '%s')";


    //XQUERY I XUPDATE ZA RESENJE

    public static final String X_QUERY_FIND_ALL_RESENJA_EXPRESSION = "xquery version \"3.1\";\n" +
            "declare default element namespace \"http://ftn.uns.ac.rs/tim5/model/resenje\";\n" +
            "for $x in collection(\"/db/sample/resenja\")\n" +
            "return $x";

    public static final String X_UPDATE_REMOVE_RESENJE_BY_ID_EXPRESSION =
            "xquery version \"3.1\";\n" +
                    "xmldb:remove('/db/sample/resenja', '%s')";


    //XQUERY I XUPDATE ZA IZVESTAJ

    public static final String X_QUERY_FIND_ALL_IZVESTAJI_EXPRESSION = "xquery version \"3.1\";\n" +
            "declare default element namespace \"http://ftn.uns.ac.rs/tim5/model/izvestaj\";\n" +
            "for $x in collection(\"/db/sample/izvestaj\")\n" +
            "return $x";

    public static final String X_UPDATE_REMOVE_IZVESTAJ_BY_ID_EXPRESSION =
            "xquery version \"3.1\";\n" +
                    "xmldb:remove('/db/sample/izvestaj', '%s')";


    //XQUERY I XUPDATE ZA GRADJANINA

    public static final String X_QUERY_FIND_ALL_GRADJANI_EXPRESSION = "xquery version \"3.1\";\n" +
            "declare default element namespace \"http://ftn.uns.ac.rs/tim5/model/gradjanin\";\n" +
            "for $x in collection(\"/db/sample/gradjanin\")\n" +
            "return $x";

    public static final String X_UPDATE_REMOVE_GRADJANIN_BY_ID_EXPRESSION =
            "xquery version \"3.1\";\n" +
                    "xmldb:remove('/db/sample/gradjanin', '%s')";


    //XQUERY I XUPDATE ZA SLUZBENIKA

    public static final String X_QUERY_FIND_ALL_SLUZBENICI_EXPRESSION = "xquery version \"3.1\";\n" +
            "declare default element namespace \"http://ftn.uns.ac.rs/tim5/model/sluzbenik\";\n" +
            "for $x in collection(\"/db/sample/sluzbenik\")\n" +
            "return $x";

    public static final String X_UPDATE_REMOVE_SLUZBENIK_BY_ID_EXPRESSION =
            "xquery version \"3.1\";\n" +
                    "xmldb:remove('/db/sample/sluzbenik', '%s')";


}
