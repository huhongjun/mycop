<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">

<xsl:output method="html" />

<xsl:template match="/">
  <html><head><title>XDoclet output</title>
  <xsl:call-template name="css" />

  </head>
  <body>
    <xsl:for-each select="/jel/class">

      <h1>
        <xsl:choose>
          <xsl:when test="@abstract='true'">Interface:
          <xsl:value-of select="@name" /></xsl:when>
          <xsl:otherwise>Class
          : <xsl:value-of select="@name" />
           ( <xsl:value-of select="@superclass" /> )
          </xsl:otherwise>
        </xsl:choose>
      </h1>

      <h2>Instance Variables</h2>
      <xsl:apply-templates select="fields/field" />

        <h2>Constructors</h2>
        <xsl:apply-templates select="methods/constructor" />

      <h2>Methods</h2>
      <xsl:apply-templates select="methods/method" />

      <hr/>
    </xsl:for-each>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>
