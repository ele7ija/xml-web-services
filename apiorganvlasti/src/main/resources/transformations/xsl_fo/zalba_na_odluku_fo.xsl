<xsl:stylesheet
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        xmlns:fo="http://www.w3.org/1999/XSL/Format"
        xmlns:zo="http://ftn.uns.ac.rs/tim5/model/zalba_na_odluku"
        xmlns:util="http://ftn.uns.ac.rs/tim5/model/util"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" version="2.0">

    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master
                        master-name="zalba-na-odluku-page">
                    <fo:region-body margin-top="0.75in"
                                    margin-bottom="0.75in" margin-left="90pt" margin-right="90pt" />
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="zalba-na-odluku-page">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="14pt" text-align="center"
                              padding-top="15pt" margin-top="0pt" font-weight="bold"
                    >
                        ЖАЛБА ПРОТИВ ОДЛУКЕ ОРГАНА ВЛАСТИ КОЈОМ ЈЕ ОДБИЈЕН ИЛИ ОДБАЧЕН ЗАХТЕВ ЗА ПРИСТУП ИНФОРМАЦИЈИ
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="12pt" padding-left="80pt" padding-right="80pt"
                              text-align="left" padding-top="15pt" margin-top="0pt" margin-bottom="0pt" padding-bottom="0pt" font-weight="bold"
                    >
                        Поверенику за информације од јавног значаја и заштиту података о личности
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="12pt" padding-left="80pt" padding-right="80pt"
                              text-align="left" padding-top="0pt" margin-top="0pt" margin-bottom="0pt" padding-bottom="0pt"
                    >
                        Адреса за пошту:
                        <xsl:value-of select="zo:Zalba_na_odluku/zo:poverenik/util:Mesto"/>,
                        <xsl:value-of select="zo:Zalba_na_odluku/zo:poverenik/util:Ulica"/> бр.
                        <xsl:value-of select="zo:Zalba_na_odluku/zo:poverenik/util:Broj"/>
                    </fo:block>

                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="14pt"
                              text-align="center" margin-top="35pt" font-weight="bold"
                    >
                        Ж А Л Б А
                    </fo:block>
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="12pt"
                              text-align="center" margin-top="10pt"
                    >
                        <xsl:value-of select="zo:Zalba_na_odluku/zo:zalilac/zo:osnovni_podaci/util:Ime"/>&#160;
                        <xsl:value-of select="zo:Zalba_na_odluku/zo:zalilac/zo:osnovni_podaci/util:Prezime"/>,
                        <xsl:value-of select="zo:Zalba_na_odluku/zo:zalilac/zo:osnovni_podaci/util:Adresa/util:Ulica"/> бр.
                        <xsl:value-of select="zo:Zalba_na_odluku/zo:zalilac/zo:osnovni_podaci/util:Adresa/util:Broj"/>,
                        <xsl:value-of select="zo:Zalba_na_odluku/zo:zalilac/zo:osnovni_podaci/util:Adresa/util:Mesto"/>
                    </fo:block>
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="8pt"
                              text-align="center" margin-top="0pt"
                    >
                        Име, презиме, односно назив, адреса и седиште жалиоца
                    </fo:block>
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="12pt"
                              text-align="center" margin-top="10pt"
                    >
                        против решења-закључка:
                        <xsl:value-of select="zo:Zalba_na_odluku/zo:organ_vlasti/util:Naziv"/>
                    </fo:block>
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="8pt"
                              text-align="center" margin-top="0pt"
                    >
                        назив органа који је донео одлуку
                    </fo:block>

                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="12pt"
                              margin-top="20pt" margin-bottom="0pt" padding-bottom="0pt" text-align="left">
                        Број одлуке:
                        <fo:inline font-weight="bold"><xsl:value-of select="zo:Zalba_na_odluku/zo:odluka/zo:broj_odluke"/></fo:inline>,
                        од&#160;<xsl:value-of select="substring(/zo:Zalba_na_odluku/zo:odluka/zo:datum_odluke/util:dan,4)" />.<xsl:value-of select="substring(/zo:Zalba_na_odluku/zo:odluka/zo:datum_odluke/util:mesec,3)" />.<xsl:value-of select="/zo:Zalba_na_odluku/zo:odluka/zo:datum_odluke/util:godina" />. године
                    </fo:block>

                    <fo:block font-family="Times New Roman" font-size="12pt"
                              text-indent="30pt" margin-top="15pt" text-align="justify">
                        Наведеном одлуком органа власти (решењем, закључком, обавештењем у писаној форми са елементима одлуке) , супротно закону, одбијен-одбачен је мој захтев који сам поднео/ла-упутио/ла дана
                        &#160;<xsl:value-of select="substring(/zo:Zalba_na_odluku/zo:datum_zahteva/util:dan,4)" />.<xsl:value-of select="substring(/zo:Zalba_na_odluku/zo:datum_zahteva/util:mesec,3)" />.<xsl:value-of select="/zo:Zalba_na_odluku/zo:datum_zahteva/util:godina" />. године
                        и тако ми ускраћено-онемогућено остваривање уставног и законског права на слободан приступ информацијама од јавног значаја. Oдлуку побијам у целости, односно у делу којим
                    </fo:block>

                    <fo:block font-family="Times New Roman" font-size="12pt"
                              margin-top="7pt" text-align="justify" font-weight="bold">
                        <xsl:value-of select="zo:Zalba_na_odluku/zo:osnova_za_zaljenje"/>
                    </fo:block>

                    <fo:block font-family="Times New Roman" font-size="12pt"
                              margin-top="7pt" text-align="justify">
                        јер није заснована на Закону о слободном приступу информацијама од јавног значаја.
                    </fo:block>

                    <fo:block font-family="Times New Roman" font-size="12pt"
                              text-indent="30pt" margin-top="0pt" text-align="justify" margin-bottom="0pt" padding-bottom="0pt" padding-top="0pt">
                        На основу изнетих разлога, предлажем да Повереник уважи моју жалбу, поништи одлука првостепеног органа и омогући ми приступ траженој/им  информацији/ма.
                    </fo:block>

                    <fo:block font-family="Times New Roman" font-size="12pt"
                              text-indent="30pt" margin-top="0pt" text-align="justify" margin-bottom="0pt" padding-bottom="0pt" padding-top="0pt">
                        Жалбу подносим благовремено, у законском року утврђеном у члану 22. ст. 1. Закона о слободном приступу информацијама од јавног значаја.
                    </fo:block>

                    <fo:block font-family="Times New Roman" font-size="12pt"
                              margin-top="30pt" text-align="right" margin-bottom="0pt" padding-bottom="0pt" padding-top="0pt">
                        <xsl:value-of select="concat(/zo:Zalba_na_odluku/zo:zalilac/zo:osnovni_podaci/util:Ime,' ',/zo:Zalba_na_odluku/zo:zalilac/zo:osnovni_podaci/util:Prezime)" />
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="8pt" margin-top="0pt" padding-top="0pt"
                              text-align="right"
                    >
                        Подносилац жалбе / Име и презиме
                    </fo:block>

                    <fo:block font-family="Times New Roman" font-size="12pt"
                              margin-top="15pt" text-align="right" margin-bottom="0pt" padding-bottom="0pt" padding-top="0pt">
                        <xsl:value-of select="concat(/zo:Zalba_na_odluku/zo:zalilac/zo:osnovni_podaci/util:Adresa/util:Ulica,' ',/zo:Zalba_na_odluku/zo:zalilac/zo:osnovni_podaci/util:Adresa/util:Broj)" />
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="8pt" margin-top="0pt" padding-top="0pt"
                              text-align="right"
                    >
                        адреса
                    </fo:block>

                    <fo:block font-family="Times New Roman" font-size="12pt"
                              margin-top="15pt" text-align="right" margin-bottom="0pt" padding-bottom="0pt" padding-top="0pt">
                        <xsl:value-of select="zo:Zalba_na_odluku/zo:zalilac/zo:drugi_podaci_za_kontakt"/>
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="8pt" margin-top="0pt" padding-top="0pt"
                              text-align="right"
                    >
                        други подаци за контакт
                    </fo:block>

                    <fo:block font-family="Times New Roman" font-size="12pt" margin-top="-102pt" text-align="left"
                              margin-bottom="0pt" padding-bottom="0pt"
                    >
                        <xsl:value-of select="concat(' ', /zo:Zalba_na_odluku/zo:mesto_zalbe)" />
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="8pt" padding-top="0pt"
                              text-align="left"
                    >
                        место
                    </fo:block>

                    <fo:block font-family="Times New Roman" font-size="12pt" margin-top="15pt" text-align="left">
                        дана&#160;<xsl:value-of select="substring(/zo:Zalba_na_odluku/zo:datum_zalbe/util:dan,4)" />.<xsl:value-of select="substring(/zo:Zalba_na_odluku/zo:datum_zalbe/util:mesec,3)" />.<xsl:value-of select="/zo:Zalba_na_odluku/zo:datum_zalbe/util:godina" />. године
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
