/* This class was taken from com.google.gwt.safehtml.shared.SimpleHtmlSanitizerTest and includes those tests as a
 * starting point to ensure that our ExpandedHtmlSanitizer passes all the same tests.  It was then supplemented with
 * additional tests to cover our expanded valid markup and the OWASP XSS examples: https://www.owasp.org/index.php/XSS
 *
 * Originally Copyright 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.sencha.gxt.core.shared;

import com.google.gwt.safehtml.shared.SafeHtml;

public class GwtTestExpandedHtmlSanitizer extends CoreBaseTestCase {

  public void testFromGwtSimple() {
    // simple case
    SafeHtml html = ExpandedHtmlSanitizer.sanitizeHtml("foobar");
    assertEquals("foobar", html.asString());
  }

  public void testFromGwtDontChangeWhiteSpace() {
    // shouldn't change whitespace or newlines
    SafeHtml html = ExpandedHtmlSanitizer.sanitizeHtml("things are breezy\nand jolly\tgood");
    assertEquals("things are breezy\nand jolly\tgood", html.asString());
  }

  public void testFromGwtEscapeHtmlMetaCharacters() {
    // need to escape HTML metacharacters appearing on their own
    SafeHtml html = ExpandedHtmlSanitizer.sanitizeHtml("foo < bar & that's good");
    assertEquals("foo &lt; bar &amp; that&#39;s good", html.asString());
  }

  public void testFromGwtDontDoubleEscape() {
    // but don't double-escape HTML entities
    SafeHtml html = ExpandedHtmlSanitizer.sanitizeHtml("foo < bar &amp; that's good");
    assertEquals("foo &lt; bar &amp; that&#39;s good", html.asString());
  }

  public void testFromGwtEscapeLoneMetacharacters() {
    // need to escape HTML metacharacters appearing on their own
    SafeHtml html = ExpandedHtmlSanitizer.sanitizeHtml("\"foo < bar & that's good\"");
    assertEquals("&quot;foo &lt; bar &amp; that&#39;s good&quot;", html.asString());
  }

  public void testFromGwtDontEscapeValidTags() {
    // leave simple tags alone
    SafeHtml html = ExpandedHtmlSanitizer.sanitizeHtml("foo <em>bar</em>");
    assertEquals("foo <em>bar</em>", html.asString());
  }

  public void testFromGwtTagAtBeginning() {
    // correctly deal with a tag at the beginnign
    SafeHtml html = ExpandedHtmlSanitizer.sanitizeHtml("<em>bar</em>");
    assertEquals("<em>bar</em>", html.asString());
  }

  public void testFromGwtNonTagAtBeginning() {
    // correctly deal with a non-tag at the beginnig
    SafeHtml html = ExpandedHtmlSanitizer.sanitizeHtml("<yow <em>bar</em>");
    assertEquals("&lt;yow <em>bar</em>", html.asString());
  }

  public void testFromGwtNonTagAtEnd() {
    // correctly deal with a non-tag at the end
    SafeHtml html = ExpandedHtmlSanitizer.sanitizeHtml("<em>bar</em> foo <");
    assertEquals("<em>bar</em> foo &lt;", html.asString());
  }

  public void testFromGwtNullTag() {
    // correctly deal with bogus empty tag
    SafeHtml html = ExpandedHtmlSanitizer.sanitizeHtml("<>bar</em> foo<>");
    assertEquals("&lt;&gt;bar</em> foo&lt;&gt;", html.asString());
  }

  public void testFromGwtNullEndTag() {
    // correctly deal with bogus empty end tag
    SafeHtml html = ExpandedHtmlSanitizer.sanitizeHtml("</>bar</em> foo</>");
    assertEquals("&lt;/&gt;bar</em> foo&lt;/&gt;", html.asString());
  }

  public void testFromGwtSimpleTagsAndHtmlMetaChars() {
    // mix of simple tags and HTML metacharacters appearing on their own
    SafeHtml html = ExpandedHtmlSanitizer.sanitizeHtml("foo < bar & that's <b>good</b>");
    assertEquals("foo &lt; bar &amp; that&#39;s <b>good</b>", html.asString());
  }

  public void testFromGwtEvilTags() {
    // escape tags we don't know
    SafeHtml html = ExpandedHtmlSanitizer.sanitizeHtml("<script>evil()</script>");
    assertEquals("&lt;script&gt;evil()&lt;/script&gt;", html.asString());
  }

  public void testExpandedMarkupValid() {
    // test expanded valid markup with no attributes
    SafeHtml html = ExpandedHtmlSanitizer.sanitizeHtml("foo <u>bar</u>");
    assertEquals("foo <u>bar</u>", html.asString());

    html = ExpandedHtmlSanitizer.sanitizeHtml("<div>foobar</div>");
    assertEquals("<div>foobar</div>", html.asString());

    html = ExpandedHtmlSanitizer.sanitizeHtml("<li><em><span>foo</span><span>bar</span></em></li>");
    assertEquals("<li><em><span>foo</span><span>bar</span></em></li>", html.asString());

    html = ExpandedHtmlSanitizer.sanitizeHtml("<table><tr><td>foo</td><td>bar</td></tr></table>");
    assertEquals("<table><tr><td>foo</td><td>bar</td></tr></table>", html.asString());
  }

  public void testExpandedMarkupInvalid() {
    // test expanded valid markup made invalid by adding attributes
    SafeHtml html = ExpandedHtmlSanitizer.sanitizeHtml("foo <u class='reject'>bar</u>");
    assertEquals("foo &lt;u class=&#39;reject&#39;&gt;bar</u>", html.asString());

    html = ExpandedHtmlSanitizer.sanitizeHtml("<div onmouseover=\"alert('malicious xss')\">foobar</div>");
    assertEquals("&lt;div onmouseover=&quot;alert(&#39;malicious xss&#39;)&quot;&gt;foobar</div>", html.asString());

    html = ExpandedHtmlSanitizer.sanitizeHtml("<li><em><span style='color: red;'>foo</span>" +
        "<span style='color: blue;'>bar</span></em></li>");
    assertEquals("<li><em>&lt;span style=&#39;color: red;&#39;&gt;foo</span>" +
        "&lt;span style=&#39;color: blue;&#39;&gt;bar</span></em></li>", html.asString());

    html = ExpandedHtmlSanitizer.sanitizeHtml("<table width='100%' border='2'><tr><td>foo</td>" +
        "<td>bar</td></tr></table>");
    assertEquals("&lt;table width=&#39;100%&#39; border=&#39;2&#39;&gt;<tr><td>foo</td>" +
        "<td>bar</td></tr></table>", html.asString());
  }

  public void testOWASPXSSUsingScriptInAttributes() {
    // XSS using Script in Attributes
    SafeHtml html = ExpandedHtmlSanitizer.sanitizeHtml("<body onload=alert('test1')>");
    assertEquals("&lt;body onload=alert(&#39;test1&#39;)&gt;", html.asString());

    html = ExpandedHtmlSanitizer.sanitizeHtml("<b onmouseover=alert('Wufff!')>click me!</b>");
    assertEquals("&lt;b onmouseover=alert(&#39;Wufff!&#39;)&gt;click me!</b>", html.asString());

    html = ExpandedHtmlSanitizer.sanitizeHtml(
        "<img src=\"http://url.to.file.which/not.exist\" onerror=alert(document.cookie);>");
    assertEquals("&lt;img src=&quot;http://url.to.file.which/not.exist&quot; onerror=alert(document.cookie);&gt;",
        html.asString());
  }

  public void testOWASPXSSUsingScriptViaEncodedURISchemes() {
    // XSS using Script Via Encoded URI Schemes
    SafeHtml html = ExpandedHtmlSanitizer.sanitizeHtml("<IMG SRC=j&#X41vascript:alert('test2')>");
    assertEquals("&lt;IMG SRC=j&amp;#X41vascript:alert(&#39;test2&#39;)&gt;", html.asString());
  }

  public void testOWASPXSSUsingCodeEncoding() {
    // XSS using code encoding
    SafeHtml html = ExpandedHtmlSanitizer.sanitizeHtml("<META HTTP-EQUIV=\"refresh\" " +
        "CONTENT=\"0;url=data:text/html;base64,PHNjcmlwdD5hbGVydCgndGVzdDMnKTwvc2NyaXB0Pg\">");
    assertEquals("&lt;META HTTP-EQUIV=&quot;refresh&quot; " +
        "CONTENT=&quot;0;url=data:text/html;base64,PHNjcmlwdD5hbGVydCgndGVzdDMnKTwvc2NyaXB0Pg&quot;&gt;",
        html.asString());
  }

  public void testOWASPCookieGrabberExample() {
    // with unvalidated user input data, an attacker can easily steal a cookie from an authenticated user
    String unvalidatedInput = "<SCRIPT type=\"text/javascript\">" +
        "var adr = '../evil.php?cakemonster=' + escape(document.cookie);" +
        "</SCRIPT>";
    SafeHtml html = ExpandedHtmlSanitizer.sanitizeHtml("You typed: " + unvalidatedInput);
    assertEquals("You typed: &lt;SCRIPT type=&quot;text/javascript&quot;&gt;" +
        "var adr = &#39;../evil.php?cakemonster=&#39; + escape(document.cookie);" +
        "&lt;/SCRIPT&gt;", html.asString());
  }

  public void testOWASPErrorPageExample() {
    // we will try to force the error page to include our code
    String targetUrl = "http://testsite.test/<script>alert(\"TEST\");</script>";
    SafeHtml html = ExpandedHtmlSanitizer.sanitizeHtml("404 Not Found: " + targetUrl);
    assertEquals("404 Not Found: http://testsite.test/&lt;script&gt;alert(&quot;TEST&quot;);&lt;/script&gt;",
        html.asString());
  }

  public void testOWASPSpecialCharacters() {
    // the key HTML entities to identify are:
    SafeHtml html = ExpandedHtmlSanitizer.sanitizeHtml("> (greater than) " +
        "< (less than) " +
        "& (ampersand) " +
        "' (apostrophe or single quote) " +
        "\" (double quote) " +
        "\u20B5 (unicode values)");
    assertEquals("&gt; (greater than) " +
        "&lt; (less than) " +
        "&amp; (ampersand) " +
        "&#39; (apostrophe or single quote) " +
        "&quot; (double quote) " +
        "₵ (unicode values)", html.asString());
  }

  public void testOWASPXSSTagAttributeValue() {
    // an XSS exploit can be carried out without the use of <script> tags and even without the use of characters such
    String unvalidatedInput = "\" onfocus=\"alert(document.cookie)";
    SafeHtml html = ExpandedHtmlSanitizer.sanitizeHtml("<input value=\"" + unvalidatedInput + "\">");
    assertEquals("&lt;input value=&quot;&quot; onfocus=&quot;alert(document.cookie)&quot;&gt;", html.asString());
  }

  public void testOWASPDifferentSyntaxOrEncoding() {
    // signature-based filters can be simply defeated by obfuscating the attack
    SafeHtml html = ExpandedHtmlSanitizer.sanitizeHtml("<script >alert(document.cookie)</script >");
    assertEquals("&lt;script &gt;alert(document.cookie)&lt;/script &gt;", html.asString());

    html = ExpandedHtmlSanitizer.sanitizeHtml("<ScRiPt>alert(document.cookie)</ScRiPt>");
    assertEquals("&lt;ScRiPt&gt;alert(document.cookie)&lt;/ScRiPt&gt;", html.asString());

    // this is a URI based attack and isn't relevant to this sanitizer
    // html = ExpandedHtmlSanitizer.sanitizeHtml("%3cscript%3ealert(document.cookie)%3c/script%3e");
    // assertEquals("%3cscript%3ealert(document.cookie)%3c/script%3e", html.asString());
  }

  public void testOWASPBypassingNonRecursiveFiltering() {
    // the attacker can beat the filter by sending a string containing multiple attempts, like this one:
    SafeHtml html = ExpandedHtmlSanitizer.sanitizeHtml("<scr<script>ipt>alert(document.cookie)</script>");
    assertEquals("&lt;scr&lt;script&gt;ipt&gt;alert(document.cookie)&lt;/script&gt;", html.asString());
  }

  public void testOWASPIncludingExternalScript() {
    // it is possible to bypass the sanitization by using the ">" character in an attribute between script and src
    String targetUrl = "http://example/?var=<SCRIPT%20a=\">\"%20SRC=\"http://attacker/xss.js\"></SCRIPT>";
    SafeHtml html = ExpandedHtmlSanitizer.sanitizeHtml("404 Not Found: " + targetUrl);
    assertEquals("404 Not Found: " +
            "http://example/?var=&lt;SCRIPT%20a=&quot;&gt;&quot;%20SRC=" +
            "&quot;http://attacker/xss.js&quot;&gt;&lt;/SCRIPT&gt;", html.asString());
  }

  public void testOWASPHTTPParameterPollution() {
    // this evasion technique consists of splitting an attack vector between multiple parameters that have the same name
    String targetUrl = "http://example/page.php?param=<script&param=>...</&param=script>";
    SafeHtml html = ExpandedHtmlSanitizer.sanitizeHtml("404 Not Found: " + targetUrl);
    assertEquals("404 Not Found: http://example/page.php?param=&lt;script&amp;param=&gt;...&lt;/&amp;param=script&gt;",
        html.asString());
  }

}
