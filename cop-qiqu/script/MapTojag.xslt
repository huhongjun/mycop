<?xml version="1.0" encoding="UTF-8"?>
<!--
This file was generated by Altova MapForce 2006

YOU SHOULD NOT MODIFY THIS FILE, BECAUSE IT WILL BE
OVERWRITTEN WHEN YOU RE-RUN CODE GENERATION.

Refer to the Altova MapForce 2006 Documentation for further details.
http://www.altova.com/mapforce
-->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:a="attribute" xmlns:c="collection" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:o="object" exclude-result-prefixes="a c xs o">
	<xsl:output method="xml" encoding="UTF-8" indent="yes"/>
	<xsl:template match="/Model">
		<jag>
			<xsl:attribute name="xsi:noNamespaceSchemaLocation">D:/MyEclipseWork/workspace/QiQu-COP/model/input/jag.xsd</xsl:attribute>
			<entitylist>
				<xsl:for-each select="o:RootObject">
					<xsl:for-each select="c:Children">
						<xsl:for-each select="o:Model">
							<xsl:for-each select="c:Packages">
								<xsl:for-each select="o:Package">
									<xsl:for-each select="c:Tables">
										<xsl:for-each select="o:Table">
											<entity>
												<xsl:for-each select="a:Code">
													<xsl:attribute name="name">
														<xsl:value-of select="."/>
													</xsl:attribute>
												</xsl:for-each>
												<xsl:for-each select="a:Name">
													<xsl:attribute name="display-name">
														<xsl:value-of select="."/>
													</xsl:attribute>
												</xsl:for-each>
												<xsl:for-each select="a:Code">
													<xsl:attribute name="table-name">
														<xsl:value-of select="."/>
													</xsl:attribute>
												</xsl:for-each>
												<xsl:for-each select="c:Columns">
													<fieldlist>
														<xsl:for-each select="o:Column">
															<field>
																<xsl:for-each select="a:Code">
																	<xsl:attribute name="name">
																		<xsl:value-of select="."/>
																	</xsl:attribute>
																</xsl:for-each>
																<xsl:for-each select="a:Name">
																	<xsl:attribute name="label">
																		<xsl:value-of select="."/>
																	</xsl:attribute>
																</xsl:for-each>
																<xsl:for-each select="a:Mandatory">
																	<xsl:attribute name="required">
																		<xsl:value-of select="."/>
																	</xsl:attribute>
																</xsl:for-each>
																<xsl:for-each select="a:DataType">
																	<xsl:attribute name="sql-type">
																		<xsl:value-of select="."/>
																	</xsl:attribute>
																</xsl:for-each>
															</field>
														</xsl:for-each>
													</fieldlist>
												</xsl:for-each>
											</entity>
										</xsl:for-each>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:for-each>
						</xsl:for-each>
					</xsl:for-each>
				</xsl:for-each>
			</entitylist>
		</jag>
	</xsl:template>
</xsl:stylesheet>
