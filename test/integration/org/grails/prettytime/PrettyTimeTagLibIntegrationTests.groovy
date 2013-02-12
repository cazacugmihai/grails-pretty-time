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

        assertOutputEquals '', template, [date: null]

        assertOutputEquals MOMENTS_AGO, template, [date: new Date()]
        
        template = '<prettytime:display date="${date}"/>'        
        assertOutputEquals ONE_DAY_AGO, template, [date: new Date() - 1]
    }
    
    void testCapitalization() {
        def date = new DateTime()
    
        def template = '<prettytime:display date="${date}" capitalize="true"/>'
        assertOutputEquals MOMENTS_AGO_CAPITALIZED, template, [date: date]

        template = '<prettytime:display date="${date}" capitalize="false"/>'
        assertOutputEquals MOMENTS_AGO, template, [date: date]

        template = '<prettytime:display date="${date}" capitalize="${true}"/>'
        assertOutputEquals MOMENTS_AGO_CAPITALIZED, template, [date: date]

        template = '<prettytime:display date="${date}" capitalize="${false}"/>'
        assertOutputEquals MOMENTS_AGO, template, [date: date]
    }

    void testJodaTime() {
        def date = new DateTime()
        def template = '<prettytime:display date="${date}"/>'
        assertOutputEquals MOMENTS_AGO, template, [date: date]
    }
    
    void testShowTime() {        
        def date = new Date()
        def expectedOutput = "$MOMENTS_AGO, " + date.format('hh:mm:ss a')

        def template = '<prettytime:display date="${date}" showTime="true"/>'
        assertOutputEquals expectedOutput, template, [date: date]    
        
        template = '<prettytime:display date="${date}" showTime="${true}"/>'
        assertOutputEquals expectedOutput, template, [date: date]     
        
        template = '<prettytime:display date="${date}" showTime="false"/>'
        assertOutputEquals MOMENTS_AGO, template, [date: date]     
        
        template = '<prettytime:display date="${date}" showTime="${false}"/>'
        assertOutputEquals MOMENTS_AGO, template, [date: date]     
    }
    
    void testShowTimeWithFormat() {            
        def template = '<prettytime:display date="${date}" showTime="true" format="HH:mm:ss"/>'
        def date = new Date()
        def expectedOutput = "$MOMENTS_AGO, " + date.format('HH:mm:ss')
        assertOutputEquals expectedOutput, template, [date: date]    
    }
    
}
