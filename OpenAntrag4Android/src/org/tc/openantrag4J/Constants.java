package org.tc.openantrag4J;

/*
Copyright (c) 2013 René Röderstein

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

The Software shall be used for Good, not Evil.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

public abstract class Constants {

	public static final String VERSION = "0.9751";
	
	public static final String BASE_URL = "www.openantrag.de";
	public static final String PROTOCOL = "http";
	public static final String API_URL = "api";
	public static final String PROPOSAL_URL = "proposal";
	public static final String REPRESENTATION_URL = "representation";
	public static final String RESULTFORMAT_SUFFIX = "?format=json";
	public static final String COMMAND_GET_KEY_VALUE_LIST = "representation/GetKeyValueList";
	public static final String COMMAND_GET_REPRESENTATIVES = "representation/GetRepresentatives";
	public static final String COMMAND_GET_PAGE = "GetPage";
	public static final String COMMAND_KEY_ALL_REPRESENTATION = "all";
	public static final String COMMAND_GET_TAGS = "proposal/GetTags";
	public static final String COMMAND_GET_COMMENTS = "proposal/GetComments";
	public static final String COMMAND_GET_COMMITTEES = "representation/GetCommittees";
	public static final String COMMAND_GET_TOP = "GetTop";
	public static final String COMMAND_GET_BY_ID = "proposal/GetById";
	public static final String COMMAND_GET_BY_TAG = "GetByTag";
	public static final String COMMAND_GET_COUNT = "GetCount";
	public static final String FIELD_EXTERNAL_SHORT_URL = "ExternalShortUrl";
	public static final String FIELD_KEY = "Key";
	public static final String FIELD_NAME = "Name";
	public static final String FIELD_TITLE = "Title";
	public static final String FIELD_VALUE = "Value";
	public static final String FIELD_TEXT_HTML = "TextHtml";
	public static final String FIELD_ID_CURRENT_PROPOSAL_STEP = "ID_CurrentProposalStep";
	public static final String FIELD_URL = "Url";
	public static final String FIELD_SHORT_URL = "ShortUrl";
	public static final String FIELD_EXTERNAL_URL ="ExternalUrl";
	public static final String FIELD_CREATED_AT = "CreatedAt";
	public static final String FIELD_TITLE_URL = "TitleUrl";
	public static final String FIELD_ABUSE_MESSAGE_TEXT = "AbuseMessageText";
	public static final String FIELD_ABUSE_MESSAGE = "AbuseMessage";
	public static final String FIELD_ABUSE_MESSAGE_HTML = "AbuseMessageHtml";
	public static final String FIELD_IS_ABUSE = "IsAbuse";
	public static final String FIELD_TEXT_MARKDOWN = "TextMarkdown";
	public static final String FIELD_ID = "Id";
	public static final String FIELD_TAGS_LIST = "TagsList";
	public static final String FIELD_KEY_REPRESENTATIVE = "Key_Representative";
	public static final String FIELD_KEY_REPRESENTATION = "Key_Representation";
	public static final String FIELD_TIMESTAMP = "Timestamp";
	public static final String FIELD_TEXTMARKDOWN = "TextMarkdown";
	public static final String FIELD_KEY_COMMITTEE = "Key_Committee";
	public static final String FIELD_TEXT_RAW = "TextRaw";
	public static final String FIELD_FULL_URL = "FullUrl";
	public static final String FIELD_PROCESS_STEP = "ProcessStep";
	public static final String FIELD_ID_PROCESS_STEP = "ID_ProcessStep";
	public static final String FIELD_PROPOSAL_STEPS = "ProposalSteps";
	public static final String FIELD_CAPTION = "Caption";
	public static final String FIELD_ID_NEXT_STEPS = "ID_NextSteps";
	public static final String FIELD_SHORT_CAPTION = "ShortCaption";
	public static final String FIELD_COLOR = "Color";
	public static final String FIELD_FEEDITEM = "FeedItem";
	public static final String FIELD_FEED_TITLE = "Title";
	public static final String FIELD_FEED_TEXT = "Text";
	
	public static final String FIELD_INFO_TEXT = "InfoText";
	public static final String FIELD_INFO_HTML = "InfoHtml";
	
	public static final String FIELD_COMMENTED_AT_TIMESTAMP = "CommentedAtTimestamp";
	public static final String FIELD_COMMENTED_AT = "CommentedAt";
	public static final String FIELD_COMMENTED_BY = "CommentedBy";
	public static final String FIELD_COMMENT_HTML = "CommentHtml";
	public static final String FIELD_COMMENT_RAW = "CommentRaw";
	
	public static final String FIELD_REPRESENTATIVE_KEY = "Key";
	public static final String FIELD_REPRESENTATIVE_NAME = "Name";
	public static final String FIELD_REPRESENTATIVE_PARTY = "Party";
	public static final String FIELD_REPRESENTATIVE_MAIL = "Mail";
	public static final String FIELD_REPRESENTATIVE_TWITTER = "Twitter";
	public static final String FIELD_REPRESENTATIVE_INFO_HTML = "InfoHtml";
	public static final String FIELD_REPRESENTATIVE_PORTRAIT_IMAGE = "PortraitImage";
		
	public static final String DATE_FORMAT = "dd.MM.yyyy HH:mm:ss";
	
	private Constants() {
	}
	
	//"ProposalSteps":[{"ProcessStep":{"ID_NextSteps":"2","Caption":"Eingang des Antrags","ID":1,"ShortCaption":"Antragseingang","Color":"#AAAAAA"},"InfoText":"","Id":"1","Timestamp":1376469178,"CreatedAt":"14.08.2013 08:32:58","InfoHtml":"","ID_ProcessStep":1},{"ProcessStep":{"ID_NextSteps":"3,4","Caption":"Behandlung in der Fraktionssitzung","ID":2,"ShortCaption":"Behandlung Fraktionssitzung","Color":"#FF8800"},"InfoText":"Es bietet sich an, die vermeintliche oder tatsächliche Gefahrensituation an der genannten Kreuzung durch eine entsprechende Anfrage an das Bezirksamt überprüfen, also ob eine Häufung von Problemsituationen dort bekannt ist. Wir werden das in der kommenden Fraktionssitzung besprechen und entsprechend handeln. ","Id":"2","Timestamp":1376470214,"CreatedAt":"14.08.2013 08:50:14","InfoHtml":"<p>Es bietet sich an, die vermeintliche oder tatsächliche Gefahrensituation an der genannten Kreuzung durch eine entsprechende Anfrage an das Bezirksamt überprüfen, also ob eine Häufung von Problemsituationen dort bekannt ist. Wir werden das in der kommenden Fraktionssitzung besprechen und entsprechend handeln.<\/p>\n","ID_ProcessStep":2},{"ProcessStep":{"ID_NextSteps":"5","Caption":"Übernahme durch %REPRESENTATIVE%","ID":4,"ShortCaption":"Übernahme durch Bezirksverordneten","Color":"#2D9D24"},"InfoText":"In der Fraktions-Sitzung wurde beschlossen, den genannten Sachverhalt zunächst durch eine kleine Anfrage an das Bezirksamt genauer zu beleuchten. Die Anfrage wird derzeit in einem Pad gemeinsam mit der Basis und sonstigen Interessierten erarbeitet: https://bvv-spandau.piratenpad.de/ANFR-Ruhlebener-Teltower MFG Emilio Paolini ","Id":"3","Timestamp":1376834228,"CreatedAt":"18.08.2013 13:57:08","InfoHtml":"<p>In der Fraktions-Sitzung wurde beschlossen, den genannten Sachverhalt zunächst durch eine kleine Anfrage an das Bezirksamt genauer zu beleuchten.<\/p>\n<p>Die Anfrage wird derzeit in einem Pad gemeinsam mit der Basis und sonstigen Interessierten erarbeitet:<\/p>\n<p><a href=\"https://bvv-spandau.piratenpad.de/ANFR-Ruhlebener-Teltower\" target=\"_blank\">https://bvv-spandau.piratenpad.de/ANFR-Ruhlebener-Teltower<\/a><\/p>\n<p>MFG Emilio Paolini<\/p>\n","ID_ProcessStep":4}],"FeedItem":{"SourceFeed":null,"Authors":[],"Title":{"Text":"Änderung Ampelschaltung für Radfahrer Ruhlebener Str / Teltower Str (Bezirksverordnetenversammlung Spandau von Berlin)","Type":"text","AttributeExtensions":{}},"Categories":[],"Summary":null,"Links":[{"MediaType":null,"ElementExtensions":[],"Uri":"http://www.openantrag.de/berlin-spandau/aenderung-ampelschaltung-fuer-radfahrer-ruhlebener-str-teltower-str","Length":0,"RelationshipType":"alternate","AttributeExtensions":{},"BaseUri":null,"Title":null}],"ElementExtensions":[],"AttributeExtensions":{},"Copyright":null,"BaseUri":null,"Id":"1154","Content":{"Text":"An der Kreuzung Ruhlebener Str / Teltower Str Richtung Osten kommt es immer wieder zu gefährlichen Situationen. Grund hierfür die im gegensatz zur Auto-Ampel sehr kurze Grünphase für Radfahrer. Immer wieder kann beobachtet werden, dass Autofahrer für einen Radfahrer halten, dieser dann jedoch zwischenzeitlich schon \"rot\" hat und der Verkehr dann ins Stocken gerät oder nachfolgende Autofahrer mit dieser Reaktion nicht rechnen und es daher zu brenzligen Situationen kommt.\nDie Abzweigung ist so übersichtlich, dass nicht davon ausgegangen werden kann, dass durch die kurze Grünphase für die Radfahrer ein Sicherheitsgewinn erreicht wird- Im Gegenteil. Auch biegen nicht sonderlich viel LKW in den Tiefwerder Weg ein, so dass die Radfahrer vor Abbiegeunfällen geschützt werden müssten.\nEs wird beantragt, die Grünphase für die Radfahrer erheblich auszudehnen bzw. die extra Rad-Ampel zu entfernen. ","Type":"text","AttributeExtensions":{}},"Contributors":[],"PublishDate":"2013-08-14T08:32:58+02:00","LastUpdatedTime":"2013-08-14T08:32:58+02:00"},"ShortUrl":null,"ExternalUrl":null,"ID_CurrentProposalStep":"3","Timestamp":1376469178,"Key_Committee":null,"Title":"Änderung Ampelschaltung für Radfahrer Ruhlebener Str / Teltower Str","Key_Representative":"emilio-paolini","Key_Representation":"berlin-spandau","TextRaw":"An der Kreuzung Ruhlebener Str / Teltower Str Richtung Osten kommt es immer wieder zu gefährlichen Situationen. Grund hierfür die im gegensatz zur Auto-Ampel sehr kurze Grünphase für Radfahrer. Immer wieder kann beobachtet werden, dass Autofahrer für einen Radfahrer halten, dieser dann jedoch zwischenzeitlich schon \"rot\" hat und der Verkehr dann ins Stocken gerät oder nachfolgende Autofahrer mit dieser Reaktion nicht rechnen und es daher zu brenzligen Situationen kommt.\nDie Abzweigung ist so übersichtlich, dass nicht davon ausgegangen werden kann, dass durch die kurze Grünphase für die Radfahrer ein Sicherheitsgewinn erreicht wird- Im Gegenteil. Auch biegen nicht sonderlich viel LKW in den Tiefwerder Weg ein, so dass die Radfahrer vor Abbiegeunfällen geschützt werden müssten.\nEs wird beantragt, die Grünphase für die Radfahrer erheblich auszudehnen bzw. die extra Rad-Ampel zu entfernen. ","TagsList":"Verkehr,Stadtentwicklung","Id":"proposals-1154","TitleUrl":"aenderung-ampelschaltung-fuer-radfahrer-ruhlebener-str-teltower-str","FullUrl":"http://www.openantrag.de/berlin-spandau/aenderung-ampelschaltung-fuer-radfahrer-ruhlebener-str-teltower-str","CreatedAt":"14.08.2013 08:32:58"}

}
