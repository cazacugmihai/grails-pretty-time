package org.grails.prettytime

import grails.test.mixin.TestFor
import org.joda.time.DateTime
import org.joda.time.Period
import spock.lang.Specification

@TestFor(PrettyTimeTagLib)
class PrettyTimeTagLibTests extends Specification {



    def "testFormatDate "() {
        expect:
        applyTemplate('<prettytime:display />') == ''
        applyTemplate('<prettytime:display date="${new Date()}" />') == 'moments ago'
        applyTemplate('<prettytime:display date="${new Date() - 7}" />') == '1 week ago'
        applyTemplate('<prettytime:display date="${new Date() - 14}" />') == '2 weeks ago'
     }

    def "another testFormatDate"(){
        when:
        def cal = Calendar.instance
        cal.add(Calendar.HOUR, -1)
        then:
        applyTemplate('<prettytime:display date="${date}" />', [date: cal.time]) == '1 hour ago'

        when:
        cal.add(Calendar.HOUR, 2)
        then:
        applyTemplate('<prettytime:display date="${date}" />', [date: cal.time]) == '60 minutes from now'

    }

    def "testFormatDateWIthJodaTime"() {
        when:
        def dt = new DateTime()
        then:
        applyTemplate('<prettytime:display date="${date}" />', [date: dt]) == 'moments ago'

        when:
        dt = dt.minus(Period.days(7))
        then:
        applyTemplate('<prettytime:display date="${date}" />', [date: dt]) == '1 week ago'

        when:
        dt = dt.minus(Period.days(7))
        then:
        applyTemplate('<prettytime:display date="${date}" />', [date: dt]) == '2 weeks ago'
    }

    def "testFormatDateWithLong"() {
        setup:
        [new Date(), new Date() - 7, new Date() - 14].each { dateToTest ->
            assert applyTemplate('<prettytime:display date="${date}" />', [date: dateToTest]) ==
                    applyTemplate('<prettytime:display date="${date}" />', [date: dateToTest.getTime()])
        }
    }

    def "testWithDifferentLocale"() {
        expect:
        applyTemplate('<prettytime:display date="${new Date()}" />') == 'moments ago'

        when:
        request.addPreferredLocale(Locale.GERMAN)
        then:
        applyTemplate('<prettytime:display date="${new Date()}" />') == 'vor einem Augenblick'
        applyTemplate('<prettytime:display date="${new Date() - 7}" />') == 'vor 1 Woche'
        applyTemplate('<prettytime:display date="${new Date() - 14}" />') == 'vor 2 Wochen'

        when:
        request.addPreferredLocale(new Locale('pl'))
        then:
        applyTemplate('<prettytime:display date="${new Date()}" />') == 'przed chwilą'
        applyTemplate('<prettytime:display date="${new Date() - 7}" />') == '1 tydzień temu'
        applyTemplate('<prettytime:display date="${new Date() - 14}" />') == '2 tygodni(e) temu'
    }

}
