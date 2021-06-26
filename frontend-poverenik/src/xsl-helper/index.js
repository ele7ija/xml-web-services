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