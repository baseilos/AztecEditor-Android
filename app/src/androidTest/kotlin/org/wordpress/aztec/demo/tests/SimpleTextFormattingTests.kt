package org.wordpress.aztec.demo.tests

import android.support.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.wordpress.aztec.demo.BaseTest
import org.wordpress.aztec.demo.MainActivity
import org.wordpress.aztec.demo.pages.EditLinkPage
import org.wordpress.aztec.demo.pages.EditorPage

/**
 * Created by matisseh on 9/7/17.
 */

class SimpleTextFormattingTests : BaseTest() {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testSimpleBoldFormatting() {
        val text1 = "some"
        val text2 = "text"
        val html = "$text1<b>$text2</b>"

        EditorPage()
                .insertText(text1)
                .toggleBold()
                .insertText(text2)
                .toggleHtml()
                .verifyHTML(html)
    }

    @Test
    fun testSimpleItalicFormatting() {
        val text1 = "some"
        val text2 = "text"
        val html = "$text1<i>$text2</i>"

        EditorPage()
                .insertText(text1)
                .toggleItalics()
                .insertText(text2)
                .toggleHtml()
                .verifyHTML(html)
    }

    @Test
    fun testSimpleStrikethroughFormatting() {
        val text1 = "some"
        val text2 = "text"
        val html = "$text1<del>$text2</del>"

        EditorPage()
                .insertText(text1)
                .toggleStrikethrough()
                .insertText(text2)
                .toggleHtml()
                .verifyHTML(html)
    }

    @Test
    fun testSimpleUnderlineFormatting() {
        val text1 = "some"
        val text2 = "text"
        val html = "$text1<u>$text2</u>"

        EditorPage()
                .insertText(text1)
                .toggleUnderline()
                .insertText(text2)
                .toggleHtml()
                .verifyHTML(html)
    }

    @Test
    fun testSimpleQuoteFormatting() {
        val text1 = "some\n"
        val text2 = "text"
        val html = "$text1<blockquote>$text2</blockquote>"

        EditorPage()
                .insertText(text1)
                .toggleQuote()
                .insertText(text2)
                .toggleHtml()
                .verifyHTML(html)
    }

    @Test
    fun testSimpleUnorderedListFormatting() {
        val text1 = "some\n"
        val text2 = "text"
        val html = "$text1<ul>\n\t<li>$text2</li>\n</ul>"

        EditorPage()
                .insertText(text1)
                .makeList(EditorPage.ListStyle.UNORDERED)
                .insertText(text2)
                .toggleHtml()
                .verifyHTML(html)
    }

    @Test
    fun testSimpleOrderedListFormatting() {
        val text1 = "some\n"
        val text2 = "text"
        val html = "$text1<ol>\n\t<li>$text2</li>\n</ol>"

        EditorPage()
                .insertText(text1)
                .makeList(EditorPage.ListStyle.ORDERED)
                .insertText(text2)
                .toggleHtml()
                .verifyHTML(html)
    }

    /*
    * This test enters link HTML and then checks the link dialog values.
    * This is opposite of the steps in the other tests (which enter rich text and check the HTML),
    * because Espresso does not click in the correct position for the link dialog's OK button.
     */
    @Test
    fun testLinkFormattingWithHTML() {
        val text = "some text"
        val link = "https://github.com/wordpress-mobile/AztecEditor-Android"
        val html = "<a href='$link'>$text</a>"

        EditorPage()
                .tapTop()
                .toggleHtml()
                .insertHTML(html)
                .toggleHtml()
                .makeLink()

        EditLinkPage()
                .verifyURL(link)
                .verifyName(text)
    }

    @Test
    fun testSimpleLinkFormatting() {
        val text = "some text"
        val link = "https://github.com/wordpress-mobile/AztecEditor-Android"
        val html = "<a href=\"$link\">$text</a>"

        EditorPage()
                .insertText(text)
                .selectAllText()
                .makeLink()

        EditLinkPage()
                .updateURL(link)
                .ok()

        EditorPage()
                .toggleHtml()
                .verifyHTML(html)
    }

    @Test
    fun testSimpleMoreTagFormatting() {
        val text1 = "some"
        val text2 = "text"
        val html = "$text1\n\n<!--more-->\n\n$text2"

        EditorPage()
                .insertText(text1)
                .addMoreRule()
                .focusedInsertText(text2)
                .toggleHtml()
                .verifyHTML(html)
    }

    // @Test TODO: uncomment when Preformatted will be added back as a feature
    fun testSimplePageBreakFormatting() {
        val text1 = "some"
        val text2 = "text"
        val html = "$text1\n\n<!--nextpage-->\n\n$text2"

        EditorPage()
                .insertText(text1)
                .addPage()
                .focusedInsertText(text2)
                .toggleHtml()
                .verifyHTML(html)
    }

    @Test
    fun testSimpleHeadingOneFormatting() {
        val text1 = "some\n"
        val text2 = "text"
        val html = "$text1<h1>$text2</h1>"

        EditorPage()
                .insertText(text1)
                .makeHeader(EditorPage.HeadingStyle.ONE)
                .insertText(text2)
                .toggleHtml()
                .verifyHTML(html)
    }

    @Test
    fun testSimpleHeadingTwoFormatting() {
        val text1 = "some\n"
        val text2 = "text"
        val html = "$text1<h2>$text2</h2>"

        EditorPage()
                .insertText(text1)
                .makeHeader(EditorPage.HeadingStyle.TWO)
                .insertText(text2)
                .toggleHtml()
                .verifyHTML(html)
    }

    @Test
    fun testSimpleHeadingThreeFormatting() {
        val text1 = "some\n"
        val text2 = "text"
        val html = "$text1<h3>$text2</h3>"

        EditorPage()
                .insertText(text1)
                .makeHeader(EditorPage.HeadingStyle.THREE)
                .insertText(text2)
                .toggleHtml()
                .verifyHTML(html)
    }

    @Test
    fun testSimpleHeadingFourFormatting() {
        val text1 = "some\n"
        val text2 = "text"
        val html = "$text1<h4>$text2</h4>"

        EditorPage()
                .insertText(text1)
                .makeHeader(EditorPage.HeadingStyle.FOUR)
                .insertText(text2)
                .toggleHtml()
                .verifyHTML(html)
    }

    @Test
    fun testSimpleHeadingFiveFormatting() {
        val text1 = "some\n"
        val text2 = "text"
        val html = "$text1<h5>$text2</h5>"

        EditorPage()
                .insertText(text1)
                .makeHeader(EditorPage.HeadingStyle.FIVE)
                .insertText(text2)
                .toggleHtml()
                .verifyHTML(html)
    }

    @Test
    fun testSimpleHeadingSixFormatting() {
        val text1 = "some\n"
        val text2 = "text"
        val html = "$text1<h6>$text2</h6>"

        EditorPage()
                .insertText(text1)
                .makeHeader(EditorPage.HeadingStyle.SIX)
                .insertText(text2)
                .toggleHtml()
                .verifyHTML(html)
    }

    // @Test TODO: uncomment when Preformatted will be added back as a feature
    fun testSimplePreformattedTextFormatting() {
        val text1 = "some\n"
        val text2 = "text"
        val html = "$text1\n<pre>$text2</pre>"

        EditorPage()
                .insertText(text1)
                .makeHeader(EditorPage.HeadingStyle.PRESELECTOR)
                .focusedInsertText(text2)
                .toggleHtml()
                .verifyHTML(html)
    }

    @Test
    fun testMixedList() {

        var text = "sample text\n"
        val expected1 = Regex("<ul>[\\S\\s]+</ul>")
        val expected2 = Regex("<ol>[\\S\\s]+</ol>")

        for (i in 1..4) {
            text += text
        }

        EditorPage()
                .tapTop()
                .insertText(text)
                .selectAllText()
                .makeList(EditorPage.ListStyle.UNORDERED)
                .toggleHtml()
                .verifyHTML(expected1)
                .toggleHtml()
                .tapTop()
                .makeList(EditorPage.ListStyle.ORDERED)
                .toggleHtml()
                .verifyHTML(expected2)
    }

}
