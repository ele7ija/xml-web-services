export const izvestajXSL = `<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
                xmlns:iz="http://ftn.uns.ac.rs/tim5/model/izvestaj"
                xmlns:util="http://ftn.uns.ac.rs/tim5/model/util"
                version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
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
                    table.indented {
                        text-indent: 90pt
                    }
                </style>
            </head>
            <body>
                <div>
                    <p class="big-text centered-text" style="padding-top: 35pt; margin-top: 15pt;">
                        <strong>
                            ГОДИШЊИ ИЗВЕШТАЈ О ПРЕДУЗЕТИМ РАДЊАМА У ПОГЛЕДУ ОСТВАРИВАЊА ПРАВА НА
                            СЛОБОДАН ПРИСТУП ИНФОРМАЦИЈАМА ОД ЈАВНОГ ЗНАЧАЈА
                        </strong>
                    </p>
                </div>
                <div>
                    <p class="normal-text" style="padding-top: 15pt; margin-top: 0pt; padding-bottom: 0pt; margin-bottom: 0pt; justify: left;">
                        <strong>Поверенику за информације од јавног значаја и заштиту података о личности</strong>
                    </p>
                </div>
                <div>
                    <p class="big-text centered-text" style="margin-top: 35pt;">
                        <strong>И З В Е Ш Т А Ј</strong>
                    </p>
                    <p class="normal-text indented" style = "margin-top: -10pt">
                        Органа власти: <xsl:value-of select="iz:Izvestaj/iz:organ_vlasti/util:Naziv"/>.
                    </p>
                </div>
                <div>
                    <p class="normal-text" style="margin-top: 25pt;">
                        <strong>Статистика захтева за приступ информацијама од јавног значаја</strong>
                    </p>
                    <table class="small-text indented" style="margin-top: 10pt; font-size:10pt">
                        <thead>
                            <th>Број прихваћених</th>
                            <th>Број одбијених</th>
                            <th>Број истеклих</th>
                        </thead>
                        <tbody>
                            <td><xsl:value-of select="iz:Izvestaj/iz:statistika_zahteva/iz:broj_prihvacenih"/></td>
                            <td><xsl:value-of select="iz:Izvestaj/iz:statistika_zahteva/iz:broj_odbijenih"/></td>
                            <td><xsl:value-of select="iz:Izvestaj/iz:statistika_zahteva/iz:broj_isteklih"/></td>
                        </tbody>
                    </table>
                </div>
                <div>
                    <p class="normal-text" style="margin-top: 25pt;">
                        <strong>Статистика жалби везане за предузете радње везане за захтеве за приступ
                        информацијама од јавног значаја</strong>
                    </p>
                    <table class="indented" style="margin-top: 10pt; font-size:10pt">
                        <thead>
                            <th>Бр. ж. на одлуку</th>
                            <th>Бр. ж. на ћутање (није поступио)</th>
                            <th>Бр. ж. на ћутање (нп. у целости)</th>
                        </thead>
                        <tbody>
                            <td><xsl:value-of select="iz:Izvestaj/iz:statistika_zalbi/iz:broj_zalbi_na_odluku"/></td>
                            <td><xsl:value-of select="iz:Izvestaj/iz:statistika_zalbi/iz:zalbe_cutanja/iz:nije_postupio"/></td>
                            <td><xsl:value-of select="iz:Izvestaj/iz:statistika_zalbi/iz:zalbe_cutanja/iz:nije_postupio_u_celosti"/></td>
                        </tbody>
                    </table>
                </div>
                <div>
                    <p class="normal-text" style="margin-top: 15pt; margin-bottom: 0pt; text-align: left;">
                        дана&#160;<xsl:value-of select="substring(/iz:Izvestaj/iz:datum_podnosenja/util:dan,4)" />.<xsl:value-of select="substring(/iz:Izvestaj/iz:datum_podnosenja/util:mesec,3)" />.<xsl:value-of select="/iz:Izvestaj/iz:datum_podnosenja/util:godina" />. године
                    </p>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
`