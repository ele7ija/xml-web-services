<xsl:stylesheet
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        xmlns:fo="http://www.w3.org/1999/XSL/Format"
        xmlns:iz="http://ftn.uns.ac.rs/tim5/model/izvestaj"
        xmlns:util="http://ftn.uns.ac.rs/tim5/model/util"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" version="2.0">
    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master
                    master-name="izvestaj-page">
                    <fo:region-body
                            margin-top="0.75in"
                            margin-bottom="0.75in"
                            margin-left="90pt"
                            margin-right="90pt"/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="izvestaj-page">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="14pt"
                              text-align="center" margin-top="55pt" font-weight="bold"
                    >
                        И З В Е Ш Т А Ј
                    </fo:block>
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="14pt"
                              text-align="center" font-weight="bold"
                    >
                        О ПРЕДУЗЕТИМ РАДЊАМА У ПОГЛЕДУ ОСТВАРИВАЊА ПРАВА НА
                        СЛОБОДАН ПРИСТУП ИНФОРМАЦИЈАМА ОД ЈАВНОГ ЗНАЧАЈА
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="10pt"
                              text-indent="30pt" margin-top="20pt">
                        Извештај поднет од стране органа власти:
                        <xsl:value-of select="/iz:Izvestaj/iz:organ_vlasti/util:Naziv" />,
                        <xsl:value-of select="concat(/iz:Izvestaj/iz:organ_vlasti/util:Adresa/util:Ulica,' ',/iz:Izvestaj/iz:organ_vlasti/util:Adresa/util:Broj)" />,
                        <xsl:value-of select="/iz:Izvestaj/iz:organ_vlasti/util:Adresa/util:Mesto" />
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="12pt"
                              text-indent="30pt" margin-top="20pt">
                        Статистика захтева за приступ информацијама од јавног значаја:
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="10pt"
                              text-indent="40pt" margin-top="10pt">
                        Број прихваћених:
                        <xsl:value-of select="iz:Izvestaj/iz:statistika_zahteva/iz:broj_prihvacenih"/>
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="10pt"
                              text-indent="40pt">
                        Број одбијених:
                        <xsl:value-of select="iz:Izvestaj/iz:statistika_zahteva/iz:broj_odbijenih"/>
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="10pt"
                              text-indent="40pt">
                        Број истеклих:
                        <xsl:value-of select="iz:Izvestaj/iz:statistika_zahteva/iz:broj_isteklih"/>
                    </fo:block>

                    <fo:block font-family="Times New Roman" font-size="12pt"
                              text-indent="30pt" margin-top="20pt">
                        Статистика жалби на захтеве за приступ информацијама од јавног значаја:
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="10pt"
                              text-indent="40pt" margin-top="10pt">
                        Број жалби на одлуку:
                        <xsl:value-of select="iz:Izvestaj/iz:statistika_zalbi/iz:broj_zalbi_na_odluku"/>
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="10pt"
                              text-indent="40pt">
                        Број жалби на ћутање (није поступио):
                        <xsl:value-of select="iz:Izvestaj/iz:statistika_zalbi/iz:zalbe_cutanja/iz:nije_postupio"/>
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="10pt"
                              text-indent="40pt">
                        Број жалби на ћутање (није поступио у целости):
                        <xsl:value-of select="iz:Izvestaj/iz:statistika_zalbi/iz:zalbe_cutanja/iz:nije_postupio_u_celosti"/>
                    </fo:block>

                    <fo:block font-family="Times New Roman" font-size="12pt" text-indent="40pt"
                              margin-top="50pt">
                        дана&#160;<xsl:value-of select="substring(/iz:Izvestaj/iz:datum_podnosenja/util:dan,4)" />.<xsl:value-of select="substring(/iz:Izvestaj/iz:datum_podnosenja/util:mesec,3)" />.<xsl:value-of select="/iz:Izvestaj/iz:datum_podnosenja/util:godina" />. године
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>