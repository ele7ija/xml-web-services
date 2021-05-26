<xsl:stylesheet
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        xmlns:fo="http://www.w3.org/1999/XSL/Format"
        xmlns:za="http://ftn.uns.ac.rs/tim5/model/zahtev"
        xmlns:util="http://ftn.uns.ac.rs/tim5/model/util"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" version="2.0">

    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master
                        master-name="zahtev-page">
                    <fo:region-body margin-top="0.75in"
                                    margin-bottom="0.75in" margin-left="90pt" margin-right="90pt" />
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="zahtev-page">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="12pt" text-align="center"
                              padding-top="35pt" margin-top="15pt"
                    >
                        <xsl:value-of select="/za:Zahtev/za:organ/util:Naziv" />,
                        <xsl:value-of select="concat(/za:Zahtev/za:organ/util:Adresa/util:Ulica,' ',/za:Zahtev/za:organ/util:Adresa/util:Broj)" />,
                        <xsl:value-of select="/za:Zahtev/za:organ/util:Adresa/util:Mesto" />
                    </fo:block>
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="8pt"
                              text-align="center"
                    >
                        назив и седиште органа коме се захтев упућује
                    </fo:block>

                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="14pt"
                              text-align="center" margin-top="55pt" font-weight="bold"
                    >
                        З А Х Т Е В
                    </fo:block>
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="14pt"
                              text-align="center" font-weight="bold"
                    >
                        за приступ информацији од јавног значаја
                    </fo:block>

                    <fo:block font-family="Times New Roman" font-size="12pt"
                              margin="0pt" margin-top="35pt" margin-bottom="20pt" text-indent="30pt" text-align="justify"
                    >
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
                    </fo:block>

                    <fo:block font-size="12pt"
                              text-indent="30pt" margin-top="0pt" margin-bottom="0pt"
                    >
                        <xsl:choose>
                            <xsl:when
                                    test="za:Zahtev/za:elementi_zahteva/za:Element_Zahteva/util:Tekst='Obavestenje o posedovanju'">
                                <fo:inline font-size="12pt">
                                    ✓
                                </fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                                <fo:inline font-size="12pt">
                                    &#160;&#160;&#160;
                                </fo:inline>
                            </xsl:otherwise>
                        </xsl:choose>
                        <fo:inline font-family="Times New Roman" font-size="12pt">
                            обавештење да ли поседује тражену информацију;
                        </fo:inline>
                    </fo:block>

                    <fo:block font-family="Times New Roman" font-size="12pt"
                              text-indent="30pt" margin-top="0pt" margin-bottom="0pt"
                    >
                        <xsl:choose>
                            <xsl:when
                                    test="za:Zahtev/za:elementi_zahteva/za:Element_Zahteva/util:Tekst='Uvid u dokument'">
                                <fo:inline font-size="12pt">
                                    ✓
                                </fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                                <fo:inline font-size="12pt">
                                    &#160;&#160;&#160;
                                </fo:inline>
                            </xsl:otherwise>
                        </xsl:choose>
                        увид у документ који садржи тражену информацију;
                    </fo:block>

                    <fo:block font-family="Times New Roman" font-size="12pt"
                              text-indent="30pt" margin-top="0pt" margin-bottom="0pt"
                    >
                        <xsl:choose>
                            <xsl:when
                                    test="za:Zahtev/za:elementi_zahteva/za:Element_Zahteva/util:Tekst='Kopija dokumenta'">
                                <fo:inline font-size="12pt">
                                    ✓
                                </fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                                <fo:inline font-size="12pt">
                                    &#160;&#160;&#160;
                                </fo:inline>
                            </xsl:otherwise>
                        </xsl:choose>
                        увид у копију документа који садржи тражену информацију;
                    </fo:block>

                    <fo:block font-family="Times New Roman" font-size="12pt"
                              text-indent="30pt" margin-top="0pt" margin-bottom="0pt"
                    >
                        <xsl:choose>
                            <xsl:when
                                    test="za:Zahtev/za:elementi_zahteva/za:Element_Zahteva/util:Tekst='Dostavljanje kopije'">
                                <fo:inline font-size="12pt">
                                    ✓
                                </fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                                <fo:inline font-size="12pt">
                                    &#160;&#160;&#160;
                                </fo:inline>
                            </xsl:otherwise>
                        </xsl:choose>
                        достављање копије документа који садржи тражену информацију:**
                    </fo:block>

                    <fo:block font-family="Times New Roman" font-size="12pt"
                              text-indent="90pt" margin-top="0pt" margin-bottom="0pt"
                    >
                        <xsl:choose>
                            <xsl:when
                                    test="za:Zahtev/za:elementi_zahteva/za:Element_Zahteva/util:Metod_Dostave='posta'">
                                <fo:inline font-size="12pt">
                                    ✓
                                </fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                                <fo:inline font-size="12pt">
                                    &#160;&#160;&#160;
                                </fo:inline>
                            </xsl:otherwise>
                        </xsl:choose>
                        поштом
                    </fo:block>

                    <fo:block font-family="Times New Roman" font-size="12pt"
                              text-indent="90pt" margin-top="0pt" margin-bottom="0pt"
                    >
                        <xsl:choose>
                            <xsl:when
                                    test="za:Zahtev/za:elementi_zahteva/za:Element_Zahteva/util:Metod_Dostave='elektronska posta'">
                                <fo:inline font-size="12pt">
                                    ✓
                                </fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                                <fo:inline font-size="12pt">
                                    &#160;&#160;&#160;
                                </fo:inline>
                            </xsl:otherwise>
                        </xsl:choose>
                        електронском поштом
                    </fo:block>

                    <fo:block font-family="Times New Roman" font-size="12pt"
                              text-indent="90pt" margin-top="0pt" margin-bottom="0pt"
                    >
                        <xsl:choose>
                            <xsl:when
                                    test="za:Zahtev/za:elementi_zahteva/za:Element_Zahteva/util:Metod_Dostave='faks'">
                                <fo:inline font-size="12pt">
                                    ✓
                                </fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                                <fo:inline font-size="12pt">
                                    &#160;&#160;&#160;
                                </fo:inline>
                            </xsl:otherwise>
                        </xsl:choose>
                        факсом
                    </fo:block>

                    <fo:block font-family="Times New Roman" font-size="12pt"
                              text-indent="30pt" margin-top="20pt">
                        Овај захтев се односи на следеће информације:
                    </fo:block>

                    <fo:block font-family="Times New Roman" font-size="12pt"
                              text-indent="30pt" font-weight="bold" text-align="justify">
                        <xsl:value-of select="za:Zahtev/za:opis_zahteva" />
                    </fo:block>

                    <fo:block font-family="Times New Roman" font-size="8pt"
                              text-align="justify">
                        (навести што прецизнији опис информације која се тражи као и друге податке
                        који олакшавају проналажење тражене информације)
                    </fo:block>

                    <fo:block font-family="Times New Roman" font-size="12pt" margin-top="30pt" padding-top="0pt"
                              text-align="right" margin-bottom="0pt" padding-bottom="0pt"
                    >
                        <xsl:value-of select="concat(/za:Zahtev/za:trazilac/util:Ime,' ',/za:Zahtev/za:trazilac/util:Prezime)" />&#xA;
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="8pt" margin-top="0pt" padding-top="0pt"
                              text-align="right"
                    >
                        <fo:inline font-size="8pt">Тражилац информације/Име и презиме</fo:inline>
                    </fo:block>

                    <fo:block font-family="Times New Roman" font-size="12pt" margin-top="15pt" padding-top="0pt"
                              text-align="right" margin-bottom="0pt" padding-bottom="0pt"
                    >
                        <xsl:value-of select="concat(/za:Zahtev/za:trazilac/util:Adresa/util:Ulica,' ',/za:Zahtev/za:trazilac/util:Adresa/util:Broj)" />
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="8pt" margin-top="0pt" padding-top="0pt"
                              text-align="right"
                    >
                        <fo:inline font-size="8pt">адреса</fo:inline>
                    </fo:block>

                    <fo:block font-family="Times New Roman" font-size="12pt" margin-top="15pt" text-align="right">
                        дана&#160;<xsl:value-of select="substring(/za:Zahtev/za:datum/util:dan,4)" />.<xsl:value-of select="substring(/za:Zahtev/za:datum/util:mesec,3)" />.<xsl:value-of select="/za:Zahtev/za:datum/util:godina" />. године
                    </fo:block>

                    <fo:block font-family="Times New Roman" font-size="12pt" margin-top="-56pt" text-align="left"
                              margin-bottom="0pt" padding-bottom="0pt"
                    >
                        <xsl:value-of select="concat(' ', /za:Zahtev/za:trazilac/util:Adresa/util:Mesto)" />
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="8pt" margin-top="-2pt" padding-top="0pt"
                              text-align="left"
                    >
                        <fo:inline font-size="8pt">место</fo:inline>
                    </fo:block>

                    <fo:block font-family="Times New Roman" font-size="8pt"
                              text-align="justify" margin-top="80pt">
                        * У кућици означити која законска права на приступ
                        информацијама желите да остварите.
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="8pt"
                              text-align="justify">
                        ** У кућици означити начин достављања копије
                        докумената.
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
