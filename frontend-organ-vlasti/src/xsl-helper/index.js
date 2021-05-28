export const zahtevXsl = `<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        xmlns:za="http://ftn.uns.ac.rs/tim5/model/zahtev"
        xmlns:util="http://ftn.uns.ac.rs/tim5/model/util"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" version="1.0">
    <xsl:template match="/">
        <html>
            <head>
                <style>
                    p {
                    padding-left: 80pt;
                    padding-right: 80pt;
                    font-family: "Times New Roman";
                    }
                    p.normal-text {
                    font-size: 12pt;
                    }
                    p.small-text {
                    font-size: 8pt;
                    }
                    p.big-text {
                    font-size: 14pt;
                    }
                    p.centered-text {
                    text-align: center;
                    }
                    p.indented {
                    text-indent: 30pt;
                    }
                    p.indented2 {
                    text-indent: 20pt;
                    }
                    p.indented3 {
                    text-indent: 90pt;
                    }
                    p.with-checkbox {
                    margin-top: 0pt;
                    margin-bottom: 0pt;
                    }
                </style>
            </head>
            <body>
                <div>
                    <p class="normal-text centered-text" style="margin-top: 35pt;">
                        <xsl:value-of select="/za:Zahtev/za:organ/util:Naziv" />,
                        <xsl:value-of select="concat(/za:Zahtev/za:organ/util:Adresa/util:Ulica,' ',/za:Zahtev/za:organ/util:Adresa/util:Broj)" />,
                        <xsl:value-of select="/za:Zahtev/za:organ/util:Adresa/util:Mesto" />
                    </p>
                    <p class="normal-text centered-text" style="margin-top: -15pt;">назив и седиште органа коме се захтев упућује</p>
                </div>
                <div>
                    <p class="big-text centered-text" style="margin-top: 55pt;">
                        <strong>З А Х Т Е В</strong>
                    </p>
                    <p class="big-text centered-text" style="margin-top: -10pt;">
                        <strong>за приступ информацији од јавног значаја</strong>
                    </p>
                </div>
                <div>
                    <p class="normal-text indented" style="margin: 0; margin-top: 35pt;">
                        На основу члана
                        <xsl:value-of select="za:Zahtev/za:pravni_osnov/util:Clan"/>.
                        ст.
                        <xsl:value-of select="za:Zahtev/za:pravni_osnov/util:Strana"/>.
                        <xsl:value-of select="za:Zahtev/za:pravni_osnov/util:Naziv"/>
                        <span class="normal-text indented2" style="margin: 0;">
                            („Службени гласник РС“, бр.
                            <xsl:variable name="DOPUNE_COUNT" select="count(za:Zahtev/za:pravni_osnov/util:Dopune/util:Dopuna)"/>
                            <xsl:for-each select="za:Zahtev/za:pravni_osnov/util:Dopune/util:Dopuna">
                                <xsl:value-of select="util:Broj_dopune"/>/<xsl:value-of select="substring(util:Godina,3)"/>
                                <xsl:choose>
                                    <xsl:when test="position() = $DOPUNE_COUNT - 1"> и </xsl:when>
                                    <xsl:when test="position() &lt; $DOPUNE_COUNT">, </xsl:when>
                                </xsl:choose>
                            </xsl:for-each>
                            ), од горе наведеног органа захтевам:*
                        </span>
                    </p>
                    <p class="normal-text indented with-checkbox" style="margin-top: 20pt;">
                        <xsl:element name="input">
                            <xsl:attribute name="type">checkbox</xsl:attribute>
                            <xsl:attribute name="name">posedovanje</xsl:attribute>
                            <xsl:if test="za:Zahtev/za:elementi_zahteva/za:Element_Zahteva/util:Tekst='Obavestenje o posedovanju'">
                                <xsl:attribute name="checked">true</xsl:attribute>
                            </xsl:if>
                        </xsl:element>
                        обавештење да ли поседује тражену информацију;
                    </p>
                    <p class="normal-text indented with-checkbox">
                        <xsl:element name="input">
                            <xsl:attribute name="type">checkbox</xsl:attribute>
                            <xsl:attribute name="name">uvid</xsl:attribute>
                            <xsl:if test="za:Zahtev/za:elementi_zahteva/za:Element_Zahteva/util:Tekst='Uvid u dokument'">
                                <xsl:attribute name="checked">true</xsl:attribute>
                            </xsl:if>
                        </xsl:element>
                        увид у документ који садржи тражену информацију;
                    </p>
                    <p class="normal-text indented with-checkbox">
                        <xsl:element name="input">
                            <xsl:attribute name="type">checkbox</xsl:attribute>
                            <xsl:attribute name="name">kopija</xsl:attribute>
                            <xsl:if test="za:Zahtev/za:elementi_zahteva/za:Element_Zahteva/util:Tekst='Kopija dokumenta'">
                                <xsl:attribute name="checked">true</xsl:attribute>
                            </xsl:if>
                        </xsl:element>
                        копију документа који садржи тражену информацију;
                    </p>
                    <p class="normal-text indented with-checkbox">
                        <xsl:element name="input">
                            <xsl:attribute name="type">checkbox</xsl:attribute>
                            <xsl:attribute name="name">kopija</xsl:attribute>
                            <xsl:if test="za:Zahtev/za:elementi_zahteva/za:Element_Zahteva/util:Tekst='Dostavljanje kopije'">
                                <xsl:attribute name="checked">true</xsl:attribute>
                            </xsl:if>
                        </xsl:element>
                        достављање копије документа који садржи тражену информацију:**
                    </p>
                    <p class="normal-text indented3 with-checkbox">
                        <xsl:element name="input">
                            <xsl:attribute name="type">checkbox</xsl:attribute>
                            <xsl:attribute name="name">posta</xsl:attribute>
                            <xsl:if test="za:Zahtev/za:elementi_zahteva/za:Element_Zahteva/util:Metod_Dostave='posta'">
                                <xsl:attribute name="checked">true</xsl:attribute>
                            </xsl:if>
                        </xsl:element>
                        поштом
                    </p>
                    <p class="normal-text indented3 with-checkbox">
                        <xsl:element name="input">
                            <xsl:attribute name="type">checkbox</xsl:attribute>
                            <xsl:attribute name="name">elektronska</xsl:attribute>
                            <xsl:if test="za:Zahtev/za:elementi_zahteva/za:Element_Zahteva/util:Metod_Dostave='elektronska posta'">
                                <xsl:attribute name="checked">true</xsl:attribute>
                            </xsl:if>
                        </xsl:element>
                        електронском поштом
                    </p>
                    <p class="normal-text indented3 with-checkbox">
                        <xsl:element name="input">
                            <xsl:attribute name="type">checkbox</xsl:attribute>
                            <xsl:attribute name="name">faks</xsl:attribute>
                            <xsl:if test="za:Zahtev/za:elementi_zahteva/za:Element_Zahteva/util:Metod_Dostave='faks'">
                                <xsl:attribute name="checked">true</xsl:attribute>
                            </xsl:if>
                        </xsl:element>
                        факсом
                    </p>
                    <p class="normal-text indented" style="margin-top: 20pt;">
                        Овај захтев се
                        односи на следеће информације:
                    </p>
                    <p class="normal-text indented">
                        <strong><xsl:value-of select="za:Zahtev/za:opis_zahteva" /></strong>
                    </p>
                </div>

                <div style="margin-top: 30pt;">
                    <div style="float: left;">
                        <p class="normal-text centered-text" style="margin-bottom: 0pt; ">
                            <xsl:value-of select="concat(' ', /za:Zahtev/za:trazilac/util:Adresa/util:Mesto)" /><br/>
                            <xsl:value-of select="concat(/za:Zahtev/za:trazilac/util:Adresa/util:Ulica,' ',/za:Zahtev/za:trazilac/util:Adresa/util:Broj)" />
                        </p>
                        <p class="small-text centered-text" style="margin-top: 0pt; margin-bottom: 0pt;">место и адреса</p>
                    </div>
                    <div style="float: right; ">
                        <p class="normal-text centered-text" style="margin-right: 25pt; margin-bottom: 0pt;">
                            <xsl:value-of select="concat(/za:Zahtev/za:trazilac/util:Ime,' ',/za:Zahtev/za:trazilac/util:Prezime)" />
                        </p>
                        <p class="small-text centered-text" style="margin-right: 25pt; margin-top: 0pt; margin-bottom: 0pt;">Тражилац информације/Име и презиме</p>
                    </div>

                    <p class="normal-text" style="overflow: auto; float: right; margin-right: 25pt; margin-top: 30pt;">
                        дана&#160;<xsl:value-of select="substring(/za:Zahtev/za:datum/util:dan,4)" />.<xsl:value-of select="substring(/za:Zahtev/za:datum/util:mesec,3)" />.<xsl:value-of select="/za:Zahtev/za:datum/util:godina" />. године
                    </p>

                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>`

export const obavestenjeXsl = `<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        xmlns:ob="http://ftn.uns.ac.rs/tim5/model/obavestenje"
        xmlns:util="http://ftn.uns.ac.rs/tim5/model/util"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" version="1.0">
    <xsl:template match="/">
        <html>
            <head>
                <style>
                    p {
                    padding-left: 80pt;
                    padding-right: 80pt;
                    font-family: "Times New Roman";
                    }
                    p.normal-text {
                    font-size: 12pt;
                    }
                    p.small-text {
                    font-size: 8pt;
                    }
                    p.big-text {
                    font-size: 14pt;
                    }
                    p.left-text {
                    text-align: left;
                    }
                    p.indented {
                    text-indent: 30pt;
                    }
                    p.centered-text {
                    text-align: center;
                    }
                </style>
            </head>
            <body>
                <div>
                    <p class="normal-text left-text" style="margin-top: 15pt; margin-bottom: 0pt; padding-bottom: 0pt;">
                        <xsl:value-of select="/ob:Obavestenje/ob:Organ/util:Naziv" />
                    </p>
                    <p class="normal-text left-text" style="margin-top: 0pt; padding-top: 0pt; margin-bottom: 0pt; padding-bottom: 0pt;">
                        <xsl:value-of select="concat(/ob:Obavestenje/ob:Organ/util:Adresa/util:Ulica,' ',/ob:Obavestenje/ob:Organ/util:Adresa/util:Broj,', ',/ob:Obavestenje/ob:Organ/util:Adresa/util:Mesto)" />
                    </p>
                    <p class="small-text left-text" style="margin-top: 0pt; padding-top: 0pt;">назив и седиште органа</p>
                    <p class="normal-text left-text" style="margin-top: 0pt; padding-top: 0pt; margin-bottom: 0pt; padding-bottom: 0pt;">
                        Број предмета: <xsl:value-of select="/ob:Obavestenje/ob:Predmet/ob:Broj_Predmeta" />
                    </p>
                    <p class="normal-text left-text" style="margin-top: 0pt; padding-top: 0pt;">
                        Датум: <xsl:value-of select="substring(/ob:Obavestenje/ob:Predmet/ob:Datum/util:dan,4)" />.<xsl:value-of select="substring(/ob:Obavestenje/ob:Predmet/ob:Datum/util:mesec,3)" />.<xsl:value-of select="/ob:Obavestenje/ob:Predmet/ob:Datum/util:godina" />.
                    </p>
                    <p class="normal-text left-text" style="margin-top: 15pt; padding-top: 0pt; margin-bottom: 0pt; padding-bottom: 0pt;">
                        <xsl:value-of select="concat(/ob:Obavestenje/ob:Trazilac/util:Ime,' ',/ob:Obavestenje/ob:Trazilac/util:Prezime)"/>.
                    </p>
                    <p class="normal-text left-text" style="margin-top: 0pt; padding-top: 0pt; margin-bottom: 0pt; padding-bottom: 0pt;">
                        <xsl:value-of select="concat(/ob:Obavestenje/ob:Trazilac/util:Adresa/util:Ulica,' ',/ob:Obavestenje/ob:Trazilac/util:Adresa/util:Broj,', ',/ob:Obavestenje/ob:Trazilac/util:Adresa/util:Mesto)" />
                    </p>
                    <p class="small-text left-text" style="margin-top: 0pt; padding-top: 0pt;">Име и презиме / назив / и адреса подносиоца захтева</p>
                </div>
                <div>
                    <p class="big-text centered-text" style="margin-top: 35pt;">
                        <strong>O Б А В Е Ш Т Е Њ Е</strong>
                    </p>
                    <p class="normal-text centered-text" style="margin-top: -10pt;">
                        <strong>о стављању на увид документа који садржи</strong>
                    </p>
                    <p class="normal-text centered-text" style="margin-top: -10pt;">
                        <strong>тражену информацију и о изради копије</strong>
                    </p>
                </div>
                <div>
                    <p class="normal-text indented" style="margin: 0; margin-top: 25pt; text-align: justify;">
                        На основу члана
                        <xsl:value-of select="ob:Obavestenje/ob:Predmet/ob:Pravni_Osnov/util:Clan"/>.
                        ст.
                        <xsl:value-of select="ob:Obavestenje/ob:Predmet/ob:Pravni_Osnov/util:Strana"/>.
                        <xsl:value-of select="ob:Obavestenje/ob:Predmet/ob:Pravni_Osnov/util:Naziv"/>
                        , поступајући по вашем захтеву за слободан приступ информацијамa, којим сте тражили увид у документ/е са информацијама о / у вези са:
                    </p>
                    <p class="normal-text" style="margin: 0; margin-top: 25pt; text-align: justify;">
                        <strong>
                            <xsl:value-of select="ob:Obavestenje/ob:Predmet/ob:Opis_Trazene_Informacije"/>
                        </strong>
                    </p>
                    <p class="small-text centered-text" style="margin-top: 5pt;">
                        <strong>(опис тражене информације)</strong>
                    </p>
                    <p class="normal-text" style="margin: 0; margin-top: 15pt; text-align: justify;">
                        обавештавамо вас да дана
                        <xsl:value-of select="
                            substring(ob:Obavestenje/ob:Predmet/ob:Odgovor/util:Uvid/util:Datum_Uvida/util:dan,4)"/>.
                        <xsl:value-of select="
                            substring(ob:Obavestenje/ob:Predmet/ob:Odgovor/util:Uvid/util:Datum_Uvida/util:mesec,3)"/>.
                        <xsl:value-of
                            select="ob:Obavestenje/ob:Predmet/ob:Odgovor/util:Uvid/util:Datum_Uvida/util:godina"/>,
                        у
                        <xsl:value-of
                            select="concat(ob:Obavestenje/ob:Predmet/ob:Odgovor/util:Uvid/util:Vreme_Uvida/util:Sat,':',ob:Obavestenje/ob:Predmet/ob:Odgovor/util:Uvid/util:Vreme_Uvida/util:Minut)"/>,
                        у просторијама органа у
                        <xsl:value-of select="/ob:Obavestenje/ob:Organ/util:Adresa/util:Mesto"/>
                        ул.
                        <xsl:value-of select="/ob:Obavestenje/ob:Organ/util:Adresa/util:Ulica"/>
                        бр.
                        <xsl:value-of select="/ob:Obavestenje/ob:Organ/util:Adresa/util:Broj"/>
                        можете извршити увид у документ/е у коме је садржана тражена информација.
                    </p>
                    <p class="normal-text indented" style="margin: 0; margin-top: 15pt; text-align: justify;">
                        Том приликом, на ваш захтев, може вам се издати и копија документа са траженом информацијом.
                    </p>
                </div>

                <p class="normal-text" style="margin-top: 20pt; margin-bottom: 0pt; text-align: justify;">
                    Трошкови су утврђени Уредбом Владе Републике Србије („Сл. гласник РС“, бр. 8/06), и то: копија стране А4 формата износи 3 динара, А3 формата 6 динара, CD 35 динара, дискете 20 динара, DVD 40 динара, аудио-касета – 150 динара, видео-касета 300 динара, претварање једне стране документа из физичког у електронски облик – 30 динара.
                </p>
                <p class="normal-text indented" style="margin-top: 20pt; margin-bottom: 0pt; text-align: justify;">
                    Износ укупних трошкова израде копије документа по вашем захтеву износи
                    <xsl:value-of select="/ob:Obavestenje/ob:Predmet/ob:Uplata/util:Iznos"/>
                    динара и уплаћује се на жиро-рачун Буџета Републике Србије бр.
                    <xsl:value-of select="/ob:Obavestenje/ob:Predmet/ob:Uplata/util:Racun/util:Broj_Racuna"/>,
                    с позивом на број
                    <xsl:value-of select="/ob:Obavestenje/ob:Predmet/ob:Uplata/util:Poziv_Na_Broj"/>,
                    – ознака шифре општине/града где се налази орган власти ( из
                    <xsl:value-of select="/ob:Obavestenje/ob:Predmet/ob:Uplata/util:Pravni_Osnov/util:Naziv"/>
                    – „Сл. гласник РС“,
                    <xsl:variable name="DOPUNE_COUNT" select="count(/ob:Obavestenje/ob:Predmet/ob:Uplata/util:Pravni_Osnov/util:Dopune/util:Dopuna)"/>
                    <xsl:for-each select="/ob:Obavestenje/ob:Predmet/ob:Uplata/util:Pravni_Osnov/util:Dopune/util:Dopuna">
                        <xsl:value-of select="util:Broj_dopune"/>/<xsl:value-of select="substring(util:Godina,3)"/>
                        <xsl:choose>
                            <xsl:when test="position() = $DOPUNE_COUNT - 1"> и </xsl:when>
                            <xsl:when test="position() &lt; $DOPUNE_COUNT">, </xsl:when>
                        </xsl:choose>
                    </xsl:for-each>
                    ).
                </p>
                <p class="normal-text" style="margin-top: 20pt; margin-bottom: 0pt; text-align: left;">
                    Достављено:
                </p>
                <p class="normal-text" style="margin-top: 0pt; margin-bottom: 0pt; text-align: left;">
                    1.&#160;&#160;&#160;Именованом
                </p>
                <p class="normal-text" style="margin-top: 0pt; margin-bottom: 0pt; text-align: left;">
                    2.&#160;&#160;&#160;Архиви
                </p>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>`;