package org.grails.prettytime

import grails.test.GroovyPagesTestCase
import org.joda.time.DateTime

class PrettyTimeTagLibTests extends GroovyPagesTestCase {
    
    static final String MOMENTS_AGO = 'moments ago'
    static final String MOMENTS_AGO_CAPITALIZED = 'Moments ago' 
    static transactional = false

    void testDisplay() {
        def template = '<prettytime:display date="${date}"/>'

        shouldFailWithCause(PrettyTimeException) {
			assertOutputEquals "", template, [date: null]
		}

        assertOutputEquals "moments ago", template, [date: new Date()]
    }

    void testCapitalization() {
        def template = '<prettytime:display date="${date}" capitalize="true"/>'
        assertOutputEquals MOMENTS_AGO_CAPITALIZED, template, [date: new Date()]

        template = '<prettytime:display date="${date}" capitalize="false"/>'
        assertOutputEquals MOMENTS_AGO, template, [date: new Date()]

        template = '<prettytime:display date="${date}" capitalize="${true}"/>'
        assertOutputEquals MOMENTS_AGO_CAPITALIZED, template, [date: new Date()]

        template = '<prettytime:display date="${date}" capitalize="${false}"/>'
        assertOutputEquals MOMENTS_AGO, template, [date: new Date()]
    }

    void testJodaTime() {
        def date = new DateTime()
        def template = '<prettytime:display date="${date}"/>'
        assertOutputEquals MOMENTS_AGO, template, [date: date]
    }
    
}