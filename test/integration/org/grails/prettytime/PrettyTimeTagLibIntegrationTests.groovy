package org.grails.prettytime

import grails.test.GroovyPagesTestCase
import org.joda.time.DateTime

class PrettyTimeTagLibIntegrationTests extends GroovyPagesTestCase {

    static final String MOMENTS_AGO = 'moments ago'
    static final String ONE_DAY_AGO = '1 day ago'
    static final String MOMENTS_AGO_CAPITALIZED = 'Moments ago'
    static transactional = false

    void testDisplay() {
        def template = '<prettytime:display date="${date}"/>'

        assertOutputEquals '', template, [date: null], stripEnclosingTag

        assertOutputEquals MOMENTS_AGO, template, [date: new Date()], stripEnclosingTag

        template = '<prettytime:display date="${date}"/>'
        assertOutputEquals ONE_DAY_AGO, template, [date: new Date() - 1], stripEnclosingTag
    }

    void testCapitalization() {
        def date = new DateTime()

        def template = '<prettytime:display date="${date}" capitalize="true"/>'
        assertOutputEquals MOMENTS_AGO_CAPITALIZED, template, [date: date], stripEnclosingTag

        template = '<prettytime:display date="${date}" capitalize="false"/>'
        assertOutputEquals MOMENTS_AGO, template, [date: date], stripEnclosingTag

        template = '<prettytime:display date="${date}" capitalize="${true}"/>'
        assertOutputEquals MOMENTS_AGO_CAPITALIZED, template, [date: date], stripEnclosingTag

        template = '<prettytime:display date="${date}" capitalize="${false}"/>'
        assertOutputEquals MOMENTS_AGO, template, [date: date], stripEnclosingTag
    }

    void testJodaTime() {
        def date = new DateTime()
        def template = '<prettytime:display date="${date}"/>'
        assertOutputEquals MOMENTS_AGO, template, [date: date], stripEnclosingTag
    }

    void testShowTime() {
        def date = new Date()
        def expectedOutput = "$MOMENTS_AGO, " + date.format('hh:mm:ss a')

        def template = '<prettytime:display date="${date}" showTime="true"/>'
        assertOutputEquals expectedOutput, template, [date: date], stripEnclosingTag

        template = '<prettytime:display date="${date}" showTime="${true}"/>'
        assertOutputEquals expectedOutput, template, [date: date], stripEnclosingTag

        template = '<prettytime:display date="${date}" showTime="false"/>'
        assertOutputEquals MOMENTS_AGO, template, [date: date], stripEnclosingTag

        template = '<prettytime:display date="${date}" showTime="${false}"/>'
        assertOutputEquals MOMENTS_AGO, template, [date: date], stripEnclosingTag
    }

    void testShowTimeWithFormat() {
        def template = '<prettytime:display date="${date}" showTime="true" format="HH:mm:ss"/>'
        def date = new Date()
        def expectedOutput = "$MOMENTS_AGO, " + date.format('HH:mm:ss')
        assertOutputEquals expectedOutput, template, [date: date], stripEnclosingTag
    }

    void testWrapInTimeTagWithTitleAndDateTimeAttributes() {
        def date = new Date()
        def dateString = applyTemplate('<g:formatDate date="${date}"/>', [date: date])

        def template = '<prettytime:display date="${date}"/>'
        def expectedOutput = """<time datetime="$dateString" title="$dateString">$MOMENTS_AGO</time>"""

        assertOutputEquals expectedOutput, template, [date: date]
    }

    def stripEnclosingTag = { StringWriter output ->
        output.toString() ? new XmlSlurper().parseText(output.toString()).text() : ''
    }

}
