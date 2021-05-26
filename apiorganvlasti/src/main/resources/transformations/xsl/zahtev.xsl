<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        xmlns:za="http://ftn.uns.ac.rs/tim5/model/zahtev"
        xmlns:util="http://ftn.uns.ac.rs/tim5/model/util"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" version="2.0">
    <xsl:template match="/">
        <html>
            <head>
                <style>
                    html {
                    background-color: gray;
                    }
                    body {
                    margin-left: 400pt;
                    margin-right: 400pt;
                    background-color: white;
                    }
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
                    <p class="normal-text centered-text" style="padding-top: 35pt; margin-top: 15pt;">
                        <xsl:value-of select="/za:Zahtev/za:organ/util:Naziv" />,
                        <xsl:value-of select="concat(/za:Zahtev/za:organ/util:Adresa/util:Ulica,' ',/za:Zahtev/za:organ/util:Adresa/util:Broj)" />,
                        <xsl:value-of select="/za:Zahtev/za:organ/util:Adresa/util:Mesto" />
                    </p>
                    <p class="small-text centered-text" style="margin-top: -10pt;">назив и седиште органа коме се захтев упућује</p>
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
                    <p class="normal-text indented" style="margin: 0; margin-top: 35pt; text-align: justify;">
                        На основу члана
                        <xsl:value-of select="za:Zahtev/za:pravni_osnov/util:Clan"/>.
                        ст.
                        <xsl:value-of select="za:Zahtev/za:pravni_osnov/util:Strana"/>.
                        <xsl:value-of select="za:Zahtev/za:pravni_osnov/util:Naziv"/>
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
                    </p>
                    <p class="normal-text indented with-checkbox" style="margin-top: 20pt; text-align: justify;">
                        <xsl:element name="input">
                            <xsl:attribute name="type">checkbox</xsl:attribute>
                            <xsl:attribute name="name">posedovanje</xsl:attribute>
                            <xsl:attribute name="disabled">true</xsl:attribute>
                            <xsl:if test="za:Zahtev/za:elementi_zahteva/za:Element_Zahteva/util:Tekst='Obavestenje o posedovanju'">
                                <xsl:attribute name="checked">true</xsl:attribute>
                            </xsl:if>
                        </xsl:element>
                        обавештење да ли поседује тражену информацију;
                    </p>
                    <p class="normal-text indented with-checkbox" style="text-align: justify;">
                        <xsl:element name="input">
                            <xsl:attribute name="type">checkbox</xsl:attribute>
                            <xsl:attribute name="name">uvid</xsl:attribute>
                            <xsl:attribute name="disabled">true</xsl:attribute>
                            <xsl:if test="za:Zahtev/za:elementi_zahteva/za:Element_Zahteva/util:Tekst='Uvid u dokument'">
                                <xsl:attribute name="checked">true</xsl:attribute>
                            </xsl:if>
                        </xsl:element>
                        увид у документ који садржи тражену информацију;
                    </p>
                    <p class="normal-text indented with-checkbox" style="text-align: justify;">
                        <xsl:element name="input">
                            <xsl:attribute name="type">checkbox</xsl:attribute>
                            <xsl:attribute name="name">kopija</xsl:attribute>
                            <xsl:attribute name="disabled">true</xsl:attribute>
                            <xsl:if test="za:Zahtev/za:elementi_zahteva/za:Element_Zahteva/util:Tekst='Kopija dokumenta'">
                                <xsl:attribute name="checked">true</xsl:attribute>
                            </xsl:if>
                        </xsl:element>
                        увид у копију документа који садржи тражену информацију;
                    </p>
                    <p class="normal-text indented with-checkbox" style="text-align: justify;">
                        <xsl:element name="input">
                            <xsl:attribute name="type">checkbox</xsl:attribute>
                            <xsl:attribute name="name">kopija</xsl:attribute>
                            <xsl:attribute name="disabled">true</xsl:attribute>
                            <xsl:if test="za:Zahtev/za:elementi_zahteva/za:Element_Zahteva/util:Tekst='Dostavljanje kopije'">
                                <xsl:attribute name="checked">true</xsl:attribute>
                            </xsl:if>
                        </xsl:element>
                        достављање копије документа који садржи тражену информацију:**
                    </p>
                    <p class="normal-text indented3 with-checkbox" style="text-align: justify;">
                        <xsl:element name="input">
                            <xsl:attribute name="type">checkbox</xsl:attribute>
                            <xsl:attribute name="name">posta</xsl:attribute>
                            <xsl:attribute name="disabled">true</xsl:attribute>
                            <xsl:if test="za:Zahtev/za:elementi_zahteva/za:Element_Zahteva/util:Metod_Dostave='posta'">
                                <xsl:attribute name="checked">true</xsl:attribute>
                            </xsl:if>
                        </xsl:element>
                        поштом
                    </p>
                    <p class="normal-text indented3 with-checkbox" style="text-align: justify;">
                        <xsl:element name="input">
                            <xsl:attribute name="type">checkbox</xsl:attribute>
                            <xsl:attribute name="name">elektronska</xsl:attribute>
                            <xsl:attribute name="disabled">true</xsl:attribute>
                            <xsl:if test="za:Zahtev/za:elementi_zahteva/za:Element_Zahteva/util:Metod_Dostave='elektronska posta'">
                                <xsl:attribute name="checked">true</xsl:attribute>
                            </xsl:if>
                        </xsl:element>
                        електронском поштом
                    </p>
                    <p class="normal-text indented3 with-checkbox" style="text-align: justify;">
                        <xsl:element name="input">
                            <xsl:attribute name="type">checkbox</xsl:attribute>
                            <xsl:attribute name="name">faks</xsl:attribute>
                            <xsl:attribute name="disabled">true</xsl:attribute>
                            <xsl:if test="za:Zahtev/za:elementi_zahteva/za:Element_Zahteva/util:Metod_Dostave='faks'">
                                <xsl:attribute name="checked">true</xsl:attribute>
                            </xsl:if>
                        </xsl:element>
                        факсом
                    </p>
                    <p class="normal-text indented" style="margin-top: 20pt; text-align: justify;">
                        Овај захтев се
                        односи на следеће информације:
                    </p>
                    <p class="normal-text indented" style="text-align: justify;">
                        <strong><xsl:value-of select="za:Zahtev/za:opis_zahteva" /></strong>
                    </p>
                </div>

                <p class="normal-text" style="margin-top: 30pt; margin-bottom: 0pt; text-align: right;">
                    <xsl:value-of select="concat(/za:Zahtev/za:trazilac/util:Ime,' ',/za:Zahtev/za:trazilac/util:Prezime)" />
                </p>
                <p class="small-text" style="margin-top: 0pt; margin-bottom: 0pt; text-align: right;">
                    Тражилац информације/Име и презиме
                </p>

                <p class="normal-text " style="margin-top: 30pt; margin-bottom: 0pt; text-align: right;">
                    <xsl:value-of select="concat(/za:Zahtev/za:trazilac/util:Adresa/util:Ulica,' ',/za:Zahtev/za:trazilac/util:Adresa/util:Broj)" />
                </p>
                <p class="small-text" style="margin-top: 0pt; margin-bottom: 0pt; text-align: right;">
                    адреса
                </p>

                <p class="normal-text" style="margin-top: 30pt; margin-bottom: 0pt; text-align: right;">
                    дана&#160;<xsl:value-of select="substring(/za:Zahtev/za:datum/util:dan,4)" />.<xsl:value-of select="substring(/za:Zahtev/za:datum/util:mesec,3)" />.<xsl:value-of select="/za:Zahtev/za:datum/util:godina" />. године
                </p>

                <p class="normal-text" style="margin-top: -65pt; margin-bottom: 0pt; text-align: left;">
                    <xsl:value-of select="concat(' ', /za:Zahtev/za:trazilac/util:Adresa/util:Mesto)" />
                </p>
                <p class="small-text" style="margin-top: -2pt; margin-bottom: 0pt; text-align: left;">
                    место
                </p>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
