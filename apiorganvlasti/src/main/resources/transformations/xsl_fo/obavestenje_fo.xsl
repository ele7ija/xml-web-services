<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        xmlns:ob="http://ftn.uns.ac.rs/tim5/model/obavestenje"
        xmlns:util="http://ftn.uns.ac.rs/tim5/model/util"
        xmlns:fo="http://www.w3.org/1999/XSL/Format"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" version="2.0">
    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master
                        master-name="obavestenje-page">
                    <fo:region-body margin-top="0.75in"
                                    margin-bottom="0.75in" margin-left="90pt" margin-right="90pt" />
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="obavestenje-page">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="12pt" text-align="left"
                              padding-top="15pt" margin-top="15pt" margin-bottom="0pt" padding-bottom="0pt"
                    >
                        <xsl:value-of select="/ob:Obavestenje/ob:Organ/util:Naziv" />
                    </fo:block>
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="12pt" text-align="left"
                              padding-top="0pt" margin-top="0pt" margin-bottom="0pt" padding-bottom="0pt"
                    >
                        <xsl:value-of select="concat(/ob:Obavestenje/ob:Organ/util:Adresa/util:Ulica,' ',/ob:Obavestenje/ob:Organ/util:Adresa/util:Broj,', ',/ob:Obavestenje/ob:Organ/util:Adresa/util:Mesto)" />
                    </fo:block>
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="8pt" text-align="left"
                              padding-top="0pt" margin-top="0pt"
                    >
                        назив и седиште органа
                    </fo:block>
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="12pt" text-align="left"
                              padding-top="0pt" margin-top="15pt" padding-bottom="0pt" margin-bottom="0pt"
                    >
                        Број предмета: <xsl:value-of select="/ob:Obavestenje/ob:Predmet/ob:Broj_Predmeta" />
                    </fo:block>
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="12pt" text-align="left"
                              padding-top="0pt" margin-top="0pt"
                    >
                        Датум: <xsl:value-of select="substring(/ob:Obavestenje/ob:Predmet/ob:Datum/util:dan,4)" />.<xsl:value-of select="substring(/ob:Obavestenje/ob:Predmet/ob:Datum/util:mesec,3)" />.<xsl:value-of select="/ob:Obavestenje/ob:Predmet/ob:Datum/util:godina" />.
                    </fo:block>
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="12pt" text-align="left"
                              padding-top="0pt" margin-top="15pt" padding-bottom="0pt" margin-bottom="0pt"
                    >
                        <xsl:value-of select="concat(/ob:Obavestenje/ob:Trazilac/util:Ime,' ',/ob:Obavestenje/ob:Trazilac/util:Prezime)"/>.
                    </fo:block>
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="12pt" text-align="left"
                              padding-top="0pt" margin-top="0pt" padding-bottom="0pt" margin-bottom="0pt"
                    >
                        <xsl:value-of select="concat(/ob:Obavestenje/ob:Trazilac/util:Adresa/util:Ulica,' ',/ob:Obavestenje/ob:Trazilac/util:Adresa/util:Broj,', ',/ob:Obavestenje/ob:Trazilac/util:Adresa/util:Mesto)" />
                    </fo:block>
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="8pt" text-align="left"
                              padding-top="0pt" margin-top="0pt" padding-bottom="0pt" margin-bottom="0pt"
                    >
                        Име и презиме / назив / и адреса подносиоца захтева
                    </fo:block>
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="14pt"
                              text-align="center" margin-top="35pt" font-weight="bold"
                    >
                        O Б А В Е Ш Т Е Њ Е
                    </fo:block>
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="14pt"
                              text-align="center" margin-top="0pt" font-weight="bold"
                    >
                        о стављању на увид документа који садржи
                    </fo:block>
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="14pt"
                              text-align="center" margin-top="0pt" font-weight="bold"
                    >
                        тражену информацију и о изради копије
                    </fo:block>
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="12pt" text-align="justify"
                              padding-top="0pt" margin-top="25pt" padding-bottom="0pt" margin-bottom="0pt" text-indent="30pt"
                    >
                        На основу члана
                        <xsl:value-of select="ob:Obavestenje/ob:Predmet/ob:Pravni_Osnov/util:Clan"/>.
                        ст.
                        <xsl:value-of select="ob:Obavestenje/ob:Predmet/ob:Pravni_Osnov/util:Strana"/>.
                        <xsl:value-of select="ob:Obavestenje/ob:Predmet/ob:Pravni_Osnov/util:Naziv"/>
                        , поступајући по вашем захтеву за слободан приступ информацијамa, којим сте тражили увид у документ/е са информацијама о / у вези са:
                    </fo:block>
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="12pt" text-align="justify"
                              padding-top="0pt" margin-top="25pt" padding-bottom="0pt" margin-bottom="0pt" font-weight="bold"
                    >
                        <xsl:value-of select="ob:Obavestenje/ob:Predmet/ob:Opis_Trazene_Informacije"/>
                    </fo:block>
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="8pt" text-align="center"
                              padding-top="0pt" margin-top="5pt" padding-bottom="0pt" margin-bottom="0pt" font-weight="bold"
                    >
                        (опис тражене информације)
                    </fo:block>
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="12pt" text-align="justify"
                              padding-top="0pt" margin-top="15pt" padding-bottom="0pt" margin-bottom="0pt"
                    >
                        обавештавамо вас да дана
                        <xsl:value-of select="
                                substring(ob:Obavestenje/ob:Predmet/ob:Odgovor/util:Uvid/util:Datum_Uvida/util:dan,4)"/>.
                        <xsl:value-of select="
                                substring(ob:Obavestenje/ob:Predmet/ob:Odgovor/util:Uvid/util:Datum_Uvida/util:mesec,3)"/>.
                        <xsl:value-of
                                select="ob:Obavestenje/ob:Predmet/ob:Odgovor/util:Uvid/util:Datum_Uvida/util:godina"/>,
                        у
                        <xsl:value-of
                                select="concat(ob:Obavestenje/ob:Predmet/ob:Odgovor/util:Uvid/util:Vreme_Uvida/util:Sat,':',ob:Obavestenje/ob:Predmet/ob:Odgovor/util:Uvid/util:Vreme_Uvida/util:Minut)"/>,
                        у просторијама органа у
                        <xsl:value-of select="/ob:Obavestenje/ob:Organ/util:Adresa/util:Mesto"/>
                        ул.
                        <xsl:value-of select="/ob:Obavestenje/ob:Organ/util:Adresa/util:Ulica"/>
                        бр.
                        <xsl:value-of select="/ob:Obavestenje/ob:Organ/util:Adresa/util:Broj"/>
                        можете извршити увид у документ/е у коме је садржана тражена информација.
                    </fo:block>
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="12pt" text-align="justify"
                              padding-top="0pt" margin-top="15pt" padding-bottom="0pt" margin-bottom="0pt"
                    >
                        Том приликом, на ваш захтев, може вам се издати и копија документа са траженом информацијом.
                    </fo:block>
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="12pt" text-align="justify"
                              padding-top="0pt" margin-top="20pt" padding-bottom="0pt" margin-bottom="0pt"
                    >
                        Трошкови су утврђени Уредбом Владе Републике Србије („Сл. гласник РС“, бр. 8/06), и то: копија стране А4 формата износи 3 динара, А3 формата 6 динара, CD 35 динара, дискете 20 динара, DVD 40 динара, аудио-касета – 150 динара, видео-касета 300 динара, претварање једне стране документа из физичког у електронски облик – 30 динара.
                    </fo:block>
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="12pt" text-align="justify"
                              padding-top="0pt" margin-top="20pt" padding-bottom="0pt" margin-bottom="0pt" text-indent="30pt"
                    >
                        Износ укупних трошкова израде копије документа по вашем захтеву износи
                        <xsl:value-of select="/ob:Obavestenje/ob:Predmet/ob:Uplata/util:Iznos"/>
                        динара и уплаћује се на жиро-рачун Буџета Републике Србије бр.
                        <xsl:value-of select="/ob:Obavestenje/ob:Predmet/ob:Uplata/util:Racun/util:Broj_Racuna"/>,
                        с позивом на број
                        <xsl:value-of select="/ob:Obavestenje/ob:Predmet/ob:Uplata/util:Poziv_Na_Broj"/>,
                        – ознака шифре општине/града где се налази орган власти ( из
                        <xsl:value-of select="/ob:Obavestenje/ob:Predmet/ob:Uplata/util:Pravni_Osnov/util:Naziv"/>
                        – „Сл. гласник РС“,
                        <xsl:variable name="DOPUNE_COUNT" select="count(/ob:Obavestenje/ob:Predmet/ob:Uplata/util:Pravni_Osnov/util:Dopune/util:Dopuna)"/>
                        <xsl:for-each select="/ob:Obavestenje/ob:Predmet/ob:Uplata/util:Pravni_Osnov/util:Dopune/util:Dopuna">
                            <xsl:value-of select="util:Broj_dopune"/>/<xsl:value-of select="substring(util:Godina,3)"/>
                            <xsl:choose>
                                <xsl:when test="position() = $DOPUNE_COUNT - 1"> и </xsl:when>
                                <xsl:when test="position() &lt; $DOPUNE_COUNT">, </xsl:when>
                            </xsl:choose>
                        </xsl:for-each>
                        ).
                    </fo:block>
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="12pt" text-align="left"
                              padding-top="0pt" margin-top="20pt" padding-bottom="0pt" margin-bottom="0pt"
                    >
                        Достављено:
                    </fo:block>
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="12pt" text-align="left"
                              padding-top="0pt" margin-top="0pt" padding-bottom="0pt" margin-bottom="0pt"
                    >
                        1.&#160;&#160;&#160;Именованом
                    </fo:block>
                    <fo:block font-family="Times New Roman" padding-left="80pt" padding-right="80pt" font-size="12pt" text-align="left"
                              padding-top="0pt" margin-top="0pt" padding-bottom="0pt" margin-bottom="0pt"
                    >
                        2.&#160;&#160;&#160;Архиви
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
