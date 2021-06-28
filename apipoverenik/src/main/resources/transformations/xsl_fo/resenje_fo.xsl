<xsl:stylesheet
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        xmlns:fo="http://www.w3.org/1999/XSL/Format"
        xmlns:re="http://ftn.uns.ac.rs/tim5/model/resenje"
        xmlns:util="http://ftn.uns.ac.rs/tim5/model/util"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" version="2.0">

    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master
                        master-name="resenje-page">
                    <fo:region-body margin-top="0.75in"
                                    margin-bottom="0.75in" margin-left="90pt" margin-right="90pt" />
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="resenje-page">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="12pt" text-align="left"
                              padding-top="25pt" margin-top="0pt" margin-bottom="0pt" padding-bottom="0pt"
                    >
                        <xsl:if test="re:Resenje/re:odluka/re:prihvaceno='true'">
                            Решење када је жалба основана – налаже се:
                        </xsl:if>
                        <xsl:if test="re:Resenje/re:odluka/re:odbijena_zalba='true'">
                            Решење – одбија се као неоснована:
                        </xsl:if>
                        <xsl:if test="re:Resenje/re:odluka/re:odbijen_zahtev='true'">
                            Решење – одбија се захтев:
                        </xsl:if>
                    </fo:block>
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="12pt" text-align="left"
                              padding-top="0pt" margin-top="0pt" margin-bottom="0pt" padding-bottom="0pt"
                    >
                        Бр. <xsl:value-of select="re:Resenje/re:broj_resenja" />
                    </fo:block>
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="12pt" text-align="right"
                              padding-top="0pt" margin-top="0pt" margin-bottom="0pt" padding-bottom="0pt"
                    >
                        <xsl:value-of select="substring(/re:Resenje/re:datum_resenja/util:dan,4)" />.<xsl:value-of select="substring(/re:Resenje/re:datum_resenja/util:mesec,3)" />.<xsl:value-of select="/re:Resenje/re:datum_resenja/util:godina" />. године
                    </fo:block>

                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="12pt"
                              text-align="justify" padding-top="15pt" padding-bottom="0pt" margin-top="0pt" margin-bottom="0pt"
                    >
                        Повереник за информације од јавног значаја и заштиту података о личности, у поступку по жалби
                        коју је изјавило физичко лице
                        <xsl:value-of select="re:Resenje/re:zalba/re:Podnosilac" />
                        , због непоступања органа власти
                        <xsl:value-of select="re:Resenje/re:zalba/re:Organ_vlasti/util:Naziv"/>
                        у месту
                        <xsl:value-of select="re:Resenje/re:zalba/re:Organ_vlasti/util:Adresa/util:Mesto"/>,
                        ул.
                        <xsl:value-of select="re:Resenje/re:zalba/re:Organ_vlasti/util:Adresa/util:Ulica"/>
                        број
                        <xsl:value-of select="re:Resenje/re:zalba/re:Organ_vlasti/util:Adresa/util:Broj"/>
                        , по његовом захтеву од
                        <xsl:value-of select="substring(/re:Resenje/re:zalba/re:Datum_podnosenja_zahteva/util:dan,4)" />.<xsl:value-of select="substring(/re:Resenje/re:zalba/re:Datum_podnosenja_zahteva/util:mesec,3)" />.<xsl:value-of select="/re:Resenje/re:zalba/re:Datum_podnosenja_zahteva/util:godina" />. године
                        за приступ информацијама од јавног значаја, на основу члана 35. став 1. тачка 5. Закона о слободном приступу
                        информацијама од јавног значаја („Сл. гласник РС“, бр. 120/04, 54/07, 104/09 и 36/10), а у вези са
                        чланом 4. тачка 22. Закона о заштити података о личности („Сл. гласник РС“, број 87/18), као и члана
                        23. и члана 24. став 4. Закона о слободном приступу информацијама од јавног значаја и члана 173.
                        став 2. Закона о општем управном поступку („Сл. гласник РС“, бр. 18/2016 и 95/2018-аутентично
                        тумачење), доноси
                    </fo:block>

                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="14pt"
                              text-align="center" margin-top="25pt"
                    >
                        Р Е Ш Е Њ Е
                    </fo:block>
                    <xsl:if test="re:Resenje/re:odluka/re:prihvaceno='true'">
                        <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="12pt"
                                  text-align="justify" text-indent="30pt" margin-top="5pt" margin-bottom="0pt" padding-bottom="0pt"
                        >
                            I Налаже се органу власти
                            <xsl:value-of select="re:Resenje/re:zalba/re:Organ_vlasti/util:Naziv"/>
                            у месту
                            <xsl:value-of select="re:Resenje/re:zalba/re:Organ_vlasti/util:Adresa/util:Mesto"/>,
                            да без одлагања, а најкасније у року од пет дана од дана пријема овог решења, обавести физичко лице
                            <xsl:value-of select="re:Resenje/re:zalba/re:Podnosilac" />
                            да ли поседује тражене информације односно документ у коме су исте садржане, те да му, уколико такав документ поседује
                            достави копију истог електронском поштом на адресу … или поштом, с тим што ће пре достављања
                            заштитити и учинити недоступним податке о личности којима би се повредило право на приватност
                            лица на које се информације односе, као што су: адреса становања, лични матични број грађана, име
                            оца, радни стаж, просечна оцена студирања и сл. уколико су такви подаци садржани у траженом
                            документу.
                        </fo:block>
                        <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="12pt"
                                  text-align="justify" text-indent="30pt" margin-top="0pt" margin-bottom="0pt" padding-bottom="0pt"
                        >
                            II О извршењу решења орган власти <xsl:value-of select="re:Resenje/re:zalba/re:Organ_vlasti/util:Naziv"/> у месту <xsl:value-of select="re:Resenje/re:zalba/re:Organ_vlasti/util:Adresa/util:Mesto"/>,
                            ће обавестити Повереника у року од седам дана од пријема овог решења.
                        </fo:block>
                    </xsl:if>
                    <xsl:if test="re:Resenje/re:odluka/re:odbijena_zalba='true'">
                        <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="12pt"
                                  text-align="justify" text-indent="30pt" margin-top="5pt" margin-bottom="0pt" padding-bottom="0pt"
                        >
                            Одбија се, као неоснована, жалба физичког лица
                            <xsl:value-of select="re:Resenje/re:zalba/re:Podnosilac" />
                            , изјављена против обавештења органа власти
                            <xsl:value-of select="re:Resenje/re:zalba/re:Organ_vlasti/util:Naziv"/>
                            у месту
                            <xsl:value-of select="re:Resenje/re:zalba/re:Organ_vlasti/util:Adresa/util:Mesto"/>,
                            због недобијања тражених информација по његовом захтеву за приступ информацијама од јавног значаја поднетом
                            <xsl:value-of select="substring(/re:Resenje/re:zalba/re:Datum_podnosenja_zahteva/util:dan,4)" />.<xsl:value-of select="substring(/re:Resenje/re:zalba/re:Datum_podnosenja_zahteva/util:mesec,3)" />.<xsl:value-of select="/re:Resenje/re:zalba/re:Datum_podnosenja_zahteva/util:godina" />. године.
                        </fo:block>
                    </xsl:if>
                    <xsl:if test="re:Resenje/re:odluka/re:odbijen_zahtev='true'">
                        <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="12pt"
                                  text-align="justify" text-indent="30pt" margin-top="5pt" margin-bottom="0pt" padding-bottom="0pt"
                        >
                            Одбија се, као неоснован, захтев физичког лица
                            <xsl:value-of select="re:Resenje/re:zalba/re:Podnosilac" />
                            , за приступ информацијама од јавног значаја, поднет
                            <xsl:value-of select="substring(/re:Resenje/re:zalba/re:Datum_podnosenja_zahteva/util:dan,4)" />.<xsl:value-of select="substring(/re:Resenje/re:zalba/re:Datum_podnosenja_zahteva/util:mesec,3)" />.<xsl:value-of select="/re:Resenje/re:zalba/re:Datum_podnosenja_zahteva/util:godina" />. године.
                            органу власти
                            <xsl:value-of select="re:Resenje/re:zalba/re:Organ_vlasti/util:Naziv"/>
                            у месту
                            <xsl:value-of select="re:Resenje/re:zalba/re:Organ_vlasti/util:Adresa/util:Mesto"/>.
                        </fo:block>
                    </xsl:if>

                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="14pt"
                              text-align="center" margin-top="25pt"
                    >
                        О б р а з л о ж е њ е
                    </fo:block>
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="12pt"
                              text-align="justify" margin-top="0pt" margin-bottom="0pt" padding-bottom="0pt" text-indent="30pt"
                    >
                        <xsl:value-of select="re:Resenje/re:obrazlozenje"/>
                    </fo:block>

                    <fo:block font-family="Times New Roman" font-size="12pt" margin-top="15pt" padding-top="0pt" margin-bottom="0pt" padding-bottom="0pt"
                              text-align="right"
                    >
                        ПОВЕРЕНИК
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="12pt" margin-top="0pt" text-align="right"
                              margin-bottom="0pt" padding-bottom="0pt"
                    >
                        <xsl:value-of select="concat(/re:Resenje/re:poverenik/util:Ime,' ',/re:Resenje/re:poverenik/util:Prezime)" />
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
