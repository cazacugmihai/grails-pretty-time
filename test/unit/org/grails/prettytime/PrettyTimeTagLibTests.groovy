package org.grails.prettytime

import org.joda.time.DateTime
import org.joda.time.Period

class PrettyTimeTagLibTests {

    String getBodyFromTemplate(def template, def params = []) {
        def tag = params ? applyTemplate(template, params) : applyTemplate(template)
        tag ? new XmlSlurper().parseText(tag).text() : ''
    }

    void testFormatDate() {
        assert getBodyFromTemplate('<prettytime:display />') == ''

        assert getBodyFromTemplate('<prettytime:display date="${new Date()}" />') == 'moments ago'
        assert getBodyFromTemplate('<prettytime:display date="${new Date() - 7}" />') == '1 week ago'
        assert getBodyFromTemplate('<prettytime:display date="${new Date() - 14}" />') == '2 weeks ago'

        def cal = Calendar.instance
        cal.add(Calendar.HOUR, -1)
        assert getBodyFromTemplate('<prettytime:display date="${date}" />', [date: cal.time]) == '1 hour ago'

        cal.add(Calendar.HOUR, 2)
        assert getBodyFromTemplate('<prettytime:display date="${date}" />', [date: cal.time]) == '60 minutes from now'
    }

    void testFormatDateWIthJodaTime() {
        def dt = new DateTime()
        assert getBodyFromTemplate('<prettytime:display date="${date}" />', [date: dt]) == 'moments ago'

        dt = dt.minus(Period.days(7))
        assert getBodyFromTemplate('<prettytime:display date="${date}" />', [date: dt]) == '1 week ago'

        dt = dt.minus(Period.days(7))
        assert getBodyFromTemplate('<prettytime:display date="${date}" />', [date: dt]) == '2 weeks ago'
    }

    void testFormatDateWithLong() {
        [new Date(), new Date() - 7, new Date() - 14].each { dateToTest ->
            assert getBodyFromTemplate('<prettytime:display date="${date}" />', [date: dateToTest]) ==
                    getBodyFromTemplate('<prettytime:display date="${date}" />', [date: dateToTest.getTime()])
        }
    }

    void testWithDifferentLocale() {
        assert getBodyFromTemplate('<prettytime:display date="${new Date()}" />') == 'moments ago'

        request.addPreferredLocale(Locale.GERMAN)

        assert getBodyFromTemplate('<prettytime:display date="${new Date()}" />') == 'vor einem Augenblick'
        assert getBodyFromTemplate('<prettytime:display date="${new Date() - 7}" />') == 'vor 1 Woche'
        assert getBodyFromTemplate('<prettytime:display date="${new Date() - 14}" />') == 'vor 2 Wochen'

        request.addPreferredLocale(new Locale('pl'))

        assert getBodyFromTemplate('<prettytime:display date="${new Date()}" />') == 'przed chwilą'
        assert getBodyFromTemplate('<prettytime:display date="${new Date() - 7}" />') == '1 tydzień temu'
        assert getBodyFromTemplate('<prettytime:display date="${new Date() - 14}" />') == '2 tygodni(e) temu'
    }

}
