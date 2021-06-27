<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        xmlns:zc="http://ftn.uns.ac.rs/tim5/model/zalba_cutanja"
        xmlns:util="http://ftn.uns.ac.rs/tim5/model/util"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" version="1.0">
    <xsl:template match="/">
        <html>
            <head>
                <style>
                    html {
                    background-color: gray;
                    }
                    body {
                    margin-left: 200pt;
                    margin-right: 200pt;
                    background-color: white;
                    min-height: 800px;
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
                </style>
            </head>
            <body>
                <div>
                    <p class="big-text centered-text" style="padding-top: 35pt; margin-top: 15pt;">
                        <strong>
                            ЖАЛБА КАДА ОРГАН ВЛАСТИ
                            <u>НИЈЕ ПОСТУПИО/НИЈЕ ПОСТУПИО У ЦЕЛОСТИ ПО ЗАХТЕВУ</u> ТРАЖИОЦА У ЗАКОНСКОМ  РОКУ  (ЋУТАЊЕ УПРАВЕ)
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
                        <xsl:value-of select="substring(/zc:Zalba_cutanja/zc:datum_zahteva/util:dan,4)" />.<xsl:value-of select="substring(/zc:Zalba_cutanja/zc:datum_zahteva/util:mesec,3)" />.<xsl:value-of select="/zc:Zalba_cutanja/zc:datum_zahteva/util:godina" />. године
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
                    <xsl:value-of select="/zc:Zalba_cutanja/zc:mesto_zalbe" />
                    , dана
                    <xsl:value-of select="substring(/zc:Zalba_cutanja/zc:datum_zalbe/util:dan,4)" />.<xsl:value-of select="substring(/zc:Zalba_cutanja/zc:datum_zalbe/util:mesec,3)" />.<xsl:value-of select="/zc:Zalba_cutanja/zc:datum_zalbe/util:godina" />. године

                </p>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
