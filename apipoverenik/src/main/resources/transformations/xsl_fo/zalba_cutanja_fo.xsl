<xsl:stylesheet
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        xmlns:fo="http://www.w3.org/1999/XSL/Format"
        xmlns:zc="http://ftn.uns.ac.rs/tim5/model/zalba_cutanja"
        xmlns:util="http://ftn.uns.ac.rs/tim5/model/util"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" version="2.0">

    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master
                        master-name="zalba-na-cutanje-page">
                    <fo:region-body margin-top="0.75in"
                                    margin-bottom="0.75in" margin-left="90pt" margin-right="90pt" />
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="zalba-na-cutanje-page">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="14pt" text-align="center"
                              padding-top="15pt" margin-top="0pt" font-weight="bold"
                    >
                        ЖАЛБА КАДА ОРГАН ВЛАСТИ
                        <fo:inline text-decoration="underline">НИЈЕ ПОСТУПИО/НИЈЕ ПОСТУПИО У ЦЕЛОСТИ ПО ЗАХТЕВУ</fo:inline>
                        ТРАЖИОЦА У ЗАКОНСКОМ  РОКУ  (ЋУТАЊЕ УПРАВЕ)
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
                        <xsl:value-of select="zc:Zalba_cutanja/zc:poverenik/util:Mesto"/>,
                        <xsl:value-of select="zc:Zalba_cutanja/zc:poverenik/util:Ulica"/> бр.
                        <xsl:value-of select="zc:Zalba_cutanja/zc:poverenik/util:Broj"/>
                    </fo:block>
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="12pt"
                              text-align="left" margin-top="15pt"
                    >
                        У складу са чланом 22. Закона о слободном приступу информацијама од јавног значаја подносим:
                    </fo:block>

                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="14pt"
                              text-align="center" margin-top="35pt" font-weight="bold"
                    >
                        Ж А Л Б А
                    </fo:block>
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="12pt"
                              text-align="center" margin-top="10pt"
                    >
                        против
                    </fo:block>
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="12pt"
                              text-align="center" margin-top="10pt"
                    >
                        <xsl:value-of select="zc:Zalba_cutanja/zc:organ_vlasti/util:Naziv"/>
                    </fo:block>
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="8pt"
                              text-align="center" margin-top="0pt"
                    >
                        (навести назив органа)
                    </fo:block>
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="12pt"
                              text-align="center" margin-top="10pt"
                    >
                        због тога што орган власти:
                    </fo:block>
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="12pt"
                              text-align="center" margin-top="0pt" font-weight="bold"
                    >
                        <xsl:if test="zc:Zalba_cutanja/zc:razlog_zalbe='није поступио'">
                            <fo:inline text-decoration="underline">није поступио</fo:inline>
                            <fo:inline>/ није поступио у целости у законском року</fo:inline>
                        </xsl:if>
                        <xsl:if test="zc:Zalba_cutanja/zc:razlog_zalbe='није поступио у целости'">
                            <fo:inline>није поступио /</fo:inline>
                            <fo:inline text-decoration="underline">није поступио у целости</fo:inline>
                            <fo:inline>у законском року</fo:inline>
                        </xsl:if>
                    </fo:block>
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="8pt"
                              text-align="center" margin-top="0pt"
                    >
                        (подвући  због чега се изјављује жалба)
                    </fo:block>

                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="12pt"
                              text-align="justify" margin-top="15pt" margin-bottom="0pt" padding-bottom="0pt"
                    >
                        по мом захтеву  за слободан приступ информацијама од јавног значаја који сам поднео  том органу  дана
                        <xsl:value-of select="substring(/zc:Zalba_cutanja/zc:datum_zahteva/util:dan,4)" />.<xsl:value-of select="substring(/zc:Zalba_cutanja/zc:datum_zahteva/util:mesec,3)" />.<xsl:value-of select="/zc:Zalba_cutanja/zc:datum_zahteva/util:godina" />. године
                        а којим сам тражио/ла да ми се у складу са Законом о слободном приступу информацијама од јавног значаја омогући увид- копија документа који садржи информације  о /у вези са :
                    </fo:block>
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="12pt"
                              text-align="justify" margin-top="15pt" font-weight="bold"
                    >
                        <xsl:value-of select="zc:Zalba_cutanja/zc:zahtevana_informacije" />
                    </fo:block>
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="8pt"
                              text-align="center" margin-top="0pt"
                    >
                        (навести податке о захтеву и информацији/ама)
                    </fo:block>
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="12pt"
                              text-align="justify" margin-top="15pt" margin-bottom="0pt" padding-bottom="0pt"
                    >
                        На основу изнетог, предлажем да Повереник уважи моју жалбу и омогући ми приступ траженој/им  информацији/ма.
                    </fo:block>
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="12pt"
                              text-align="justify" margin-top="0pt" margin-bottom="0pt" padding-bottom="0pt"
                    >
                        Као доказ, уз жалбу достављам копију захтева са доказом о предаји органу власти.
                    </fo:block>
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="12pt"
                              text-align="justify" margin-top="0pt" margin-bottom="0pt" padding-bottom="0pt"
                    >
                        <fo:inline font-weight="bold">Напомена:</fo:inline>
                        Код жалбе  због непоступању по захтеву у целости, треба приложити и добијени одговор органа власти.
                    </fo:block>

                    <fo:block font-family="Times New Roman" font-size="12pt"
                              margin-top="30pt" text-align="right" margin-bottom="0pt" padding-bottom="0pt" padding-top="0pt">
                        <xsl:value-of select="concat(/zc:Zalba_cutanja/zc:zalilac/zc:osnovni_podaci/util:Ime,' ',/zc:Zalba_cutanja/zc:zalilac/zc:osnovni_podaci/util:Prezime)" />
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="8pt" margin-top="0pt" padding-top="0pt"
                              text-align="right"
                    >
                        Подносилац жалбе / Име и презиме
                    </fo:block>

                    <fo:block font-family="Times New Roman" font-size="12pt"
                              margin-top="15pt" text-align="right" margin-bottom="0pt" padding-bottom="0pt" padding-top="0pt">
                        <xsl:value-of select="concat(/zc:Zalba_cutanja/zc:zalilac/zc:osnovni_podaci/util:Adresa/util:Ulica,' ',/zc:Zalba_cutanja/zc:zalilac/zc:osnovni_podaci/util:Adresa/util:Broj)" />
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="8pt" margin-top="0pt" padding-top="0pt"
                              text-align="right"
                    >
                        адреса
                    </fo:block>

                    <fo:block font-family="Times New Roman" font-size="12pt"
                              margin-top="15pt" text-align="right" margin-bottom="0pt" padding-bottom="0pt" padding-top="0pt">
                        <xsl:value-of select="zc:Zalba_cutanja/zc:zalilac/zc:drugi_podaci_za_kontakt"/>
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="8pt" margin-top="0pt" padding-top="0pt"
                              text-align="right"
                    >
                        други подаци за контакт
                    </fo:block>

                    <fo:block font-family="Times New Roman" font-size="12pt" margin-top="15pt" text-align="left"
                              margin-bottom="0pt" padding-bottom="0pt"
                    >
                        У
                        <xsl:value-of select="/zc:Zalba_cutanja/zc:mesto_zalbe" />
                        , dана
                        <xsl:value-of select="substring(/zc:Zalba_cutanja/zc:datum_zalbe/util:dan,4)" />.<xsl:value-of select="substring(/zc:Zalba_cutanja/zc:datum_zalbe/util:mesec,3)" />.<xsl:value-of select="/zc:Zalba_cutanja/zc:datum_zalbe/util:godina" />. године
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
