export const zalbaNaOdlukuXsl = `<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        xmlns:zo="http://ftn.uns.ac.rs/tim5/model/zalba_na_odluku"
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
                </style>
            </head>
            <body>
                <div>
                    <p class="big-text centered-text" style="padding-top: 35pt; margin-top: 15pt;">
                        <strong>
                            ЖАЛБА ПРОТИВ ОДЛУКЕ ОРГАНА ВЛАСТИ КОЈОМ ЈЕ
                            <u>ОДБИЈЕН ИЛИ ОДБАЧЕН ЗАХТЕВ</u> ЗА ПРИСТУП ИНФОРМАЦИЈИ
                        </strong>
                    </p>
                </div>
                <div>
                    <p class="normal-text" style="padding-top: 15pt; margin-top: 0pt; padding-bottom: 0pt; margin-bottom: 0pt; justify: left;">
                        <strong>Поверенику за информације од јавног значаја и заштиту података о личности</strong>
                    </p>
                    <p class="normal-text" style="padding-top: 0pt; margin-top: 0pt; padding-bottom: 0pt; margin-bottom: 0pt; justify: left;">
                        Адреса за пошту:
                        <xsl:value-of select="zo:Zalba_na_odluku/zo:poverenik/util:Mesto"/>,
                        <xsl:value-of select="zo:Zalba_na_odluku/zo:poverenik/util:Ulica"/> бр.
                        <xsl:value-of select="zo:Zalba_na_odluku/zo:poverenik/util:Broj"/>
                    </p>
                </div>
                <div>
                    <p class="big-text centered-text" style="margin-top: 35pt;">
                        <strong>Ж А Л Б А</strong>
                    </p>
                    <p class="normal-text centered-text" style="margin-top: -10pt;">
                        <xsl:value-of select="zo:Zalba_na_odluku/zo:zalilac/zo:osnovni_podaci/util:Ime"/>&#160;
                        <xsl:value-of select="zo:Zalba_na_odluku/zo:zalilac/zo:osnovni_podaci/util:Prezime"/>,
                        <xsl:value-of select="zo:Zalba_na_odluku/zo:zalilac/zo:osnovni_podaci/util:Adresa/util:Ulica"/> бр.
                        <xsl:value-of select="zo:Zalba_na_odluku/zo:zalilac/zo:osnovni_podaci/util:Adresa/util:Broj"/>,
                        <xsl:value-of select="zo:Zalba_na_odluku/zo:zalilac/zo:osnovni_podaci/util:Adresa/util:Mesto"/>
                    </p>
                    <p class="small-text centered-text" style="margin-top: -10pt;">Име, презиме, односно назив, адреса и седиште жалиоца</p>
                    <p class="normal-text centered-text" style="margin-top: 10pt;">
                        против решења-закључка:
                        <xsl:value-of select="zo:Zalba_na_odluku/zo:organ_vlasti/util:Naziv"/>
                    </p>
                    <p class="small-text centered-text" style="margin-top: -10pt;">назив органа који је донео одлуку</p>
                </div>
                <div>
                    <p class="normal-text" style="text-align: justify; margin-top: 15pt; margin-bottom: 0pt; padding-bottom: 0pt;">
                        Број одлуке:
                        <strong><xsl:value-of select="zo:Zalba_na_odluku/zo:odluka/zo:broj_odluke"/></strong>,
                        од&#160;<xsl:value-of select="substring(/zo:Zalba_na_odluku/zo:odluka/zo:datum_odluke/util:dan,4)" />.<xsl:value-of select="substring(/zo:Zalba_na_odluku/zo:odluka/zo:datum_odluke/util:mesec,3)" />.<xsl:value-of select="/zo:Zalba_na_odluku/zo:odluka/zo:datum_odluke/util:godina" />. године
                    </p>
                    <p class="normal-text indented" style="margin-top: 15pt; text-align: justify;">
                        Наведеном одлуком органа власти (решењем, закључком, обавештењем у писаној форми са елементима одлуке) , супротно закону, одбијен-одбачен је мој захтев који сам поднео/ла-упутио/ла дана
                        &#160;<xsl:value-of select="substring(/zo:Zalba_na_odluku/zo:datum_zahteva/util:dan,4)" />.<xsl:value-of select="substring(/zo:Zalba_na_odluku/zo:datum_zahteva/util:mesec,3)" />.<xsl:value-of select="/zo:Zalba_na_odluku/zo:datum_zahteva/util:godina" />. године
                        и тако ми ускраћено-онемогућено остваривање уставног и законског права на слободан приступ информацијама од јавног значаја. Oдлуку побијам у целости, односно у делу којим
                    </p>
                    <p class="normal-text" style="margin-top: 7pt; text-align: justify;">
                        <strong>
                            <xsl:value-of select="zo:Zalba_na_odluku/zo:osnova_za_zaljenje"/>
                        </strong>
                    </p>
                    <p class="normal-text" style="margin-top: 7pt; text-align: justify;">
                        јер није заснована на Закону о слободном приступу информацијама од јавног значаја.
                    </p>
                    <p class="normal-text indented" style="text-align: justify; margin-bottom: 0pt; padding-bottom: 0pt; margin-top: 0pt; margin-bottom: 0pt;">
                        На основу изнетих разлога, предлажем да Повереник уважи моју жалбу, поништи одлука првостепеног органа и омогући ми приступ траженој/им  информацији/ма.
                    </p>
                    <p class="normal-text indented" style="text-align: justify; margin-bottom: 0pt; padding-bottom: 0pt; margin-top: 0pt; margin-bottom: 0pt;">
                        Жалбу подносим благовремено, у законском року утврђеном у члану 22. ст. 1. Закона о слободном приступу информацијама од јавног значаја.
                    </p>
                </div>

                <p class="normal-text" style="margin-top: 30pt; margin-bottom: 0pt; text-align: right;">
                    <xsl:value-of select="concat(/zo:Zalba_na_odluku/zo:zalilac/zo:osnovni_podaci/util:Ime,' ',/zo:Zalba_na_odluku/zo:zalilac/zo:osnovni_podaci/util:Prezime)" />
                </p>
                <p class="small-text" style="margin-top: 0pt; margin-bottom: 0pt; text-align: right;">
                    Подносилац жалбе / Име и презиме/Име и презиме
                </p>

                <p class="normal-text " style="margin-top: 15pt; margin-bottom: 0pt; text-align: right;">
                    <xsl:value-of select="concat(/zo:Zalba_na_odluku/zo:zalilac/zo:osnovni_podaci/util:Adresa/util:Ulica,' ',/zo:Zalba_na_odluku/zo:zalilac/zo:osnovni_podaci/util:Adresa/util:Broj)" />
                </p>
                <p class="small-text" style="margin-top: 0pt; margin-bottom: 0pt; text-align: right;">
                    адреса
                </p>

                <p class="normal-text" style="margin-top: 15pt; margin-bottom: 0pt; text-align: right;">
                    <xsl:value-of select="zo:Zalba_na_odluku/zo:zalilac/zo:drugi_podaci_za_kontakt"/>
                </p>
                <p class="small-text" style="margin-top: 0pt; margin-bottom: 0pt; text-align: right;">
                    други подаци за контакт
                </p>

                <p class="normal-text" style="margin-top: -102pt; margin-bottom: 0pt; text-align: left;">
                    <xsl:value-of select="concat(' ', /zo:Zalba_na_odluku/zo:mesto_zalbe)" />
                </p>
                <p class="small-text" style="margin-bottom: 0pt; text-align: left;">
                    место
                </p>
                <p class="normal-text" style="margin-top: 15pt; margin-bottom: 0pt; text-align: left;">
                    дана&#160;<xsl:value-of select="substring(/zo:Zalba_na_odluku/zo:datum_zalbe/util:dan,4)" />.<xsl:value-of select="substring(/zo:Zalba_na_odluku/zo:datum_zalbe/util:mesec,3)" />.<xsl:value-of select="/zo:Zalba_na_odluku/zo:datum_zalbe/util:godina" />. године
                </p>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>`;

export const zalbaCutanjaXsl = `<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        xmlns:zc="http://ftn.uns.ac.rs/tim5/model/zalba_cutanja"
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
                </style>
            </head>
            <body>
                <div>
                    <p class="big-text centered-text" style="padding-top: 35pt; margin-top: 15pt;">
                        <strong>
                            ЖАЛБА КАДА ОРГАН ВЛАСТИ
                            <u>НИЈЕ ПОСТУПИО/НИЈЕ ПОСТУПИО У ЦЕЛОСТИ ПО ЗАХТЕВУ</u>
                            ТРАЖИОЦА У ЗАКОНСКОМ  РОКУ  (ЋУТАЊЕ УПРАВЕ)
                        </strong>
                    </p>
                </div>
                <div>
                    <p class="normal-text" style="padding-top: 15pt; margin-top: 0pt; padding-bottom: 0pt; margin-bottom: 0pt; justify: left;">
                        <strong>Поверенику за информације од јавног значаја и заштиту података о личности</strong>
                    </p>
                    <p class="normal-text" style="padding-top: 0pt; margin-top: 0pt; padding-bottom: 0pt; margin-bottom: 0pt; justify: left;">
                        Адреса за пошту:
                        <xsl:value-of select="zc:Zalba_cutanja/zc:poverenik/util:Mesto"/>,
                        <xsl:value-of select="zc:Zalba_cutanja/zc:poverenik/util:Ulica"/> бр.
                        <xsl:value-of select="zc:Zalba_cutanja/zc:poverenik/util:Broj"/>
                    </p>
                    <p class="normal-text" style="padding-top: 15pt; margin-top: 0pt; padding-bottom: 0pt; margin-bottom: 0pt; justify: left;">
                        У складу са чланом 22. Закона о слободном приступу информацијама од јавног значаја подносим:
                    </p>
                </div>
                <div>
                    <p class="big-text centered-text" style="margin-top: 35pt;">
                        <strong>Ж А Л Б У</strong>
                    </p>
                    <p class="normal-text centered-text" style="margin-top: 0pt;">
                        против
                    </p>
                    <p class="normal-text centered-text" style="margin-top: 5pt;">
                        <xsl:value-of select="zc:Zalba_cutanja/zc:organ_vlasti/util:Naziv"/>
                    </p>
                    <p class="small-text centered-text" style="margin-top: -10pt;">(навести назив органа)</p>

                    <p class="normal-text centered-text" style="margin-top: 10pt;">због тога што орган власти:</p>
                    <p class="normal-text centered-text" style="margin-top: 0pt; font-weight: bold;">
                        <xsl:if test="zc:Zalba_cutanja/zc:razlog_zalbe='није поступио'">
                            <span><u>није поступио</u> / није поступио у целости у законском року</span>
                        </xsl:if>
                        <xsl:if test="zc:Zalba_cutanja/zc:razlog_zalbe='није поступио у целости'">
                            <span>није поступио / <u>није поступио у целости</u> у законском року</span>
                        </xsl:if>
                    </p>
                    <p class="small-text centered-text" style="margin-top: -10pt;">(подвући  због чега се изјављује жалба)</p>
                </div>
                <div>
                    <p class="normal-text" style="text-align: justify; margin-top: 15pt; margin-bottom: 0pt; padding-bottom: 0pt;">
                        по мом захтеву  за слободан приступ информацијама од јавног значаја који сам поднео  том органу  дана
                        &#160;<xsl:value-of select="substring(/zc:Zalba_cutanja/zc:datum_zahteva/util:dan,4)" />.<xsl:value-of select="substring(/zc:Zalba_cutanja/zc:datum_zahteva/util:mesec,3)" />.<xsl:value-of select="/zc:Zalba_cutanja/zc:datum_zahteva/util:godina" />. године
                        а којим сам тражио/ла да ми се у складу са Законом о слободном приступу информацијама од јавног значаја омогући увид- копија документа који садржи информације  о /у вези са :
                    </p>
                    <p class="normal-text centered-text" style="margin-top: 15pt; text-align: justify; font-weight: bold;">
                        <xsl:value-of select="zc:Zalba_cutanja/zc:zahtevana_informacije" />
                    </p>
                    <p class="small-text centered-text" style="margin-top: 0pt;">(навести податке о захтеву и информацији/ама)</p>
                    <p class="normal-text" style="margin-top: 15pt; margin-bottom: 0pt; padding-bottom: 0pt; text-align: justify;">
                        На основу изнетог, предлажем да Повереник уважи моју жалбу и омогући ми приступ траженој/им  информацији/ма.
                    </p>
                    <p class="normal-text" style="margin-top: 0pt; margin-bottom: 0pt; padding-bottom: 0pt; text-align: justify;">
                        Као доказ, уз жалбу достављам копију захтева са доказом о предаји органу власти.
                    </p>
                    <p class="normal-text" style="text-align: justify; margin-bottom: 0pt; padding-bottom: 0pt; margin-top: 0pt; margin-bottom: 0pt;">
                        <strong>Напомена:</strong>
                        Код жалбе  због непоступању по захтеву у целости, треба приложити и добијени одговор органа власти.
                    </p>
                </div>

                <p class="normal-text" style="margin-top: 30pt; margin-bottom: 0pt; text-align: right;">
                    <xsl:value-of select="concat(/zc:Zalba_cutanja/zc:zalilac/zc:osnovni_podaci/util:Ime,' ',/zc:Zalba_cutanja/zc:zalilac/zc:osnovni_podaci/util:Prezime)" />
                </p>
                <p class="small-text" style="margin-top: 0pt; margin-bottom: 0pt; text-align: right;">
                    Подносилац жалбе / Име и презиме
                </p>

                <p class="normal-text " style="margin-top: 15pt; margin-bottom: 0pt; text-align: right;">
                    <xsl:value-of select="concat(/zc:Zalba_cutanja/zc:zalilac/zc:osnovni_podaci/util:Adresa/util:Ulica,' ',/zc:Zalba_cutanja/zc:zalilac/zc:osnovni_podaci/util:Adresa/util:Broj)" />
                </p>
                <p class="small-text" style="margin-top: 0pt; margin-bottom: 0pt; text-align: right;">
                    адреса
                </p>

                <p class="normal-text" style="margin-top: 15pt; margin-bottom: 0pt; text-align: right;">
                    <xsl:value-of select="zc:Zalba_cutanja/zc:zalilac/zc:drugi_podaci_za_kontakt"/>
                </p>
                <p class="small-text" style="margin-top: 0pt; margin-bottom: 0pt; text-align: right;">
                    други подаци за контакт
                </p>

                <p class="normal-text" style="margin-top: 15pt; margin-bottom: 0pt padding-bottom: 0pt; text-align: left;">
                    У
                    <xsl:value-of select="concat(' ', /zc:Zalba_cutanja/zc:mesto_zalbe)" />
                    , dана
                    <xsl:value-of select="substring(/zc:Zalba_cutanja/zc:datum_zalbe/util:dan,4)" />.<xsl:value-of select="substring(/zc:Zalba_cutanja/zc:datum_zalbe/util:mesec,3)" />.<xsl:value-of select="/zc:Zalba_cutanja/zc:datum_zalbe/util:godina" />. године

                </p>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>`;

export const resenjeXsl = `<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        xmlns:re="http://ftn.uns.ac.rs/tim5/model/resenje"
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
                </style>
            </head>
            <body>
                <div>
                    <p class="normal-text" style="text-align: left; margin-top: 25pt; margin-bottom: 0pt; padding-bottom: 0pt;">
                        <xsl:if test="re:Resenje/re:odluka/re:prihvaceno='true'">
                            Решење када је жалба основана – налаже се:
                        </xsl:if>
                        <xsl:if test="re:Resenje/re:odluka/re:odbijena_zalba='true'">
                            Решење – одбија се као неоснована:
                        </xsl:if>
                        <xsl:if test="re:Resenje/re:odluka/re:odbijen_zahtev='true'">
                            Решење – одбија се захтев:
                        </xsl:if>
                    </p>
                    <p class="normal-text" style="text-align: left; margin-top: 0pt; margin-bottom: 0pt; padding-top: 0pt; padding-bottom: 0pt;">
                        Бр. <xsl:value-of select="re:Resenje/re:broj_resenja" />
                    </p>
                    <p class="normal-text" style="text-align: right; margin-top: 0pt; margin-bottom: 0pt; padding-top: 0pt; padding-bottom: 0pt;">
                        <xsl:value-of select="substring(/re:Resenje/re:datum_resenja/util:dan,4)" />.<xsl:value-of select="substring(/re:Resenje/re:datum_resenja/util:mesec,3)" />.<xsl:value-of select="/re:Resenje/re:datum_resenja/util:godina" />. године
                    </p>
                </div>

                <div>
                    <p class="normal-text" style="padding-top: 15pt; margin-top: 0pt; padding-bottom: 0pt; margin-bottom: 0pt; text-align: justify;">
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
                    </p>
                </div>
                <div>
                    <p class="big-text centered-text" style="margin-top: 25pt;">
                        Р Е Ш Е Њ Е
                    </p>
                    <xsl:if test="re:Resenje/re:odluka/re:prihvaceno='true'">
                        <p class="normal-text indented" style="text-align: justify; margin-top: 5pt; margin-bottom: 0pt; padding-bottom: 0pt;">
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
                        </p>
                        <p class="normal-text indented" style="text-align: justify; margin-top: 0pt; margin-bottom: 0pt; padding-bottom: 0pt;">
                            II О извршењу решења орган власти <xsl:value-of select="re:Resenje/re:zalba/re:Organ_vlasti/util:Naziv"/> у месту <xsl:value-of select="re:Resenje/re:zalba/re:Organ_vlasti/util:Adresa/util:Mesto"/>,
                            ће обавестити Повереника у року од седам дана од пријема овог решења.
                        </p>
                    </xsl:if>
                    <xsl:if test="re:Resenje/re:odluka/re:odbijena_zalba='true'">
                        <p class="normal-text indented" style="text-align: justify; margin-top: 5pt; margin-bottom: 0pt; padding-bottom: 0pt;">
                            Одбија се, као неоснована, жалба физичког лица
                            <xsl:value-of select="re:Resenje/re:zalba/re:Podnosilac" />
                            , изјављена против обавештења органа власти
                            <xsl:value-of select="re:Resenje/re:zalba/re:Organ_vlasti/util:Naziv"/>
                            у месту
                            <xsl:value-of select="re:Resenje/re:zalba/re:Organ_vlasti/util:Adresa/util:Mesto"/>,
                            због недобијања тражених информација по његовом захтеву за приступ информацијама од јавног значаја поднетом
                            <xsl:value-of select="substring(/re:Resenje/re:zalba/re:Datum_podnosenja_zahteva/util:dan,4)" />.<xsl:value-of select="substring(/re:Resenje/re:zalba/re:Datum_podnosenja_zahteva/util:mesec,3)" />.<xsl:value-of select="/re:Resenje/re:zalba/re:Datum_podnosenja_zahteva/util:godina" />. године.
                        </p>
                    </xsl:if>
                    <xsl:if test="re:Resenje/re:odluka/re:odbijen_zahtev='true'">
                        <p class="normal-text indented" style="text-align: justify; margin-top: 5pt; margin-bottom: 0pt; padding-bottom: 0pt;">
                            Одбија се, као неоснован, захтев физичког лица
                            <xsl:value-of select="re:Resenje/re:zalba/re:Podnosilac" />
                            , за приступ информацијама од јавног значаја, поднет
                            <xsl:value-of select="substring(/re:Resenje/re:zalba/re:Datum_podnosenja_zahteva/util:dan,4)" />.<xsl:value-of select="substring(/re:Resenje/re:zalba/re:Datum_podnosenja_zahteva/util:mesec,3)" />.<xsl:value-of select="/re:Resenje/re:zalba/re:Datum_podnosenja_zahteva/util:godina" />. године.
                            органу власти
                            <xsl:value-of select="re:Resenje/re:zalba/re:Organ_vlasti/util:Naziv"/>
                            у месту
                            <xsl:value-of select="re:Resenje/re:zalba/re:Organ_vlasti/util:Adresa/util:Mesto"/>,
                        </p>
                    </xsl:if>
                </div>
                <div>
                    <p class="big-text centered-text" style="margin-top: 25pt;">
                        О б р а з л о ж е њ е
                    </p>
                    <p class="normal-text indented" style="text-align: justify; margin-top: 0pt; margin-bottom: 0pt; padding-bottom: 0pt;">
                        <xsl:value-of select="re:Resenje/re:obrazlozenje"/>
                    </p>
                </div>

                <p class="normal-text" style="margin-top: 15pt; margin-bottom: 0pt; text-align: right;">
                    ПОВЕРЕНИК
                </p>
                <p class="normal-text" style="margin-top: 0pt; margin-bottom: 0pt; text-align: right;">
                    <xsl:value-of select="concat(/re:Resenje/re:poverenik/util:Ime,' ',/re:Resenje/re:poverenik/util:Prezime)" />
                </p>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>`;