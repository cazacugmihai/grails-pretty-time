package org.grails.prettytime

import org.joda.time.DateTime
import org.joda.time.Period

class PrettyTimeTagLibTests {

    void testFormatDate() {

        assert applyTemplate('<prettytime:display />') == ''

        assert applyTemplate('<prettytime:display date="${new Date()}" />') == 'moments ago'
        assert applyTemplate('<prettytime:display date="${new Date() - 7}" />') == '1 week ago'
        assert applyTemplate('<prettytime:display date="${new Date() - 14}" />') == '2 weeks ago'

        def cal = Calendar.instance
        cal.add(Calendar.HOUR, -1)
        assert applyTemplate('<prettytime:display date="${date}" />', [date: cal.time]) == '1 hour ago'

        cal.add(Calendar.HOUR, 2)
        assert applyTemplate('<prettytime:display date="${date}" />', [date: cal.time]) == '60 minutes from now'
    }

    void testFormatDateWIthJodaTime() {

        def dt = new DateTime()

        assert applyTemplate('<prettytime:display date="${date}" />', [date: dt]) == 'moments ago'

        dt = dt.minus(Period.days(7))

        assert applyTemplate('<prettytime:display date="${date}" />', [date: dt]) == '1 week ago'

        dt = dt.minus(Period.days(7))

        assert applyTemplate('<prettytime:display date="${date}" />', [date: dt]) == '2 weeks ago'

    }

    void testWithDifferentLocale() {

        assert applyTemplate('<prettytime:display date="${new Date()}" />') == 'moments ago'

        request.addPreferredLocale(Locale.GERMAN)

        assert applyTemplate('<prettytime:display date="${new Date()}" />') == 'vor einem Augenblick'
        assert applyTemplate('<prettytime:display date="${new Date() - 7}" />') == 'vor 1 Woche'
        assert applyTemplate('<prettytime:display date="${new Date() - 14}" />') == 'vor 2 Wochen'

        request.addPreferredLocale(new Locale('pl'))

        assert applyTemplate('<prettytime:display date="${new Date()}" />') == 'przed chwilą'
        assert applyTemplate('<prettytime:display date="${new Date() - 7}" />') == '1 tydzień temu'
        assert applyTemplate('<prettytime:display date="${new Date() - 14}" />') == '2 tygodni(e) temu'
    }
}
