package org.grails.prettytime

import grails.test.mixin.TestFor
import org.joda.time.DateTime
import spock.lang.Specification
import spock.lang.Unroll

@TestFor(PrettyTimeTagLib)
class PrettyTimeTagLibIntegrationTests extends Specification {

    static final String MOMENTS_AGO = 'moments ago'
    static final String ONE_DAY_AGO = '1 day ago'
    static final String MOMENTS_AGO_CAPITALIZED = 'Moments ago'
    static transactional = false


    @Unroll("test display for #sno")
    def "test display"() {
        setup:
        String actualResult
        when:
        actualResult = applyTemplate('<prettytime:display date="${date}"/>', [date: inputDate])

        then:
        actualResult == expectedResult

        where:
        sno         | inputDate      | expectedResult
        ""          | null           | ""
        MOMENTS_AGO | new Date()     | MOMENTS_AGO
        ONE_DAY_AGO | new Date() - 1 | ONE_DAY_AGO

    }

    @Unroll("testing capitalization for #scenario")
    void "test Capitalization"() {
        setup:
        String actualResult

        when:
        actualResult = applyTemplate(inputTemplate, [date: inputDateTime])

        then:
        actualResult == expectedResult

        where:
        scenario                              | inputDateTime  | inputTemplate                                                | expectedResult
        'capitalize true with normal string'  | new DateTime() | '<prettytime:display date="${date}" capitalize="true"/>'     | MOMENTS_AGO_CAPITALIZED
        'capitalize true with gstring'        | new DateTime() | '<prettytime:display date="${date}" capitalize="${true}"/>'  | MOMENTS_AGO_CAPITALIZED
        'capitalize false with normal string' | new DateTime() | '<prettytime:display date="${date}" capitalize="false"/>'    | MOMENTS_AGO
        'capitalize false with gstring'       | new DateTime() | '<prettytime:display date="${date}" capitalize="${false}"/>' | MOMENTS_AGO

    }

    void "testing with JodaTime"() {
        setup:
        DateTime dateTime = new DateTime()
        String template = '<prettytime:display date="${date}"/>'

        expect:
        MOMENTS_AGO == applyTemplate(template, [date: dateTime])
    }



    void "testing with showtime true as normal string"(){
        Date date = new Date()
        String expectedOutput = "$MOMENTS_AGO, " + date.format('hh:mm:ss a')

        expect:
        applyTemplate('<prettytime:display date="${date}" showTime="true"/>',[date:date]) == expectedOutput
    }

    void "testing with showtime true as gstring "(){
        Date date = new Date()
        String expectedOutput = "$MOMENTS_AGO, " + date.format('hh:mm:ss a')

        expect:
        applyTemplate('<prettytime:display date="${date}" showTime="${true}"/>',[date:date]) == expectedOutput
    }

    void "testing with showtime false as normal string "(){
        Date date = new Date()
        String expectedOutput = MOMENTS_AGO

        expect:
        applyTemplate('<prettytime:display date="${date}" showTime="${false}"/>',[date:date]) == expectedOutput
    }

    void "testing with showtime false as gstring "(){
        Date date = new Date()
        String expectedOutput = MOMENTS_AGO

        expect:
        applyTemplate('<prettytime:display date="${date}" showTime="${false}"/>',[date:date]) == expectedOutput
    }



    void "testing showTime With format"() {
        setup:
        String template = '<prettytime:display date="${date}" showTime="true" format="HH:mm:ss"/>'
        Date date = new Date()
        String expectedOutput = "$MOMENTS_AGO, " + date.format('HH:mm:ss')
        expect:
        applyTemplate(template, [date:date]) == expectedOutput
    }

    void "test showTime In Html5 TimeTag"() {
        setup:
        Date date = new Date()
        String dateString = applyTemplate('<g:formatDate date="${date}"/>', [date: date])
        String template = '<prettytime:display date="${date}" html5wrapper="true"/>'
        String expectedOutput = """<time datetime="$dateString" title="$dateString">$MOMENTS_AGO</time>"""
        String actualOutput

        when:
        actualOutput = applyTemplate(template, [date:date])

        then:
        expectedOutput == actualOutput
    }

}
