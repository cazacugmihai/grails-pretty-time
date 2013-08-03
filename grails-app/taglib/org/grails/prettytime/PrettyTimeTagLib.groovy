package org.grails.prettytime

import org.apache.commons.lang.StringUtils
import org.ocpsoft.prettytime.PrettyTime
import org.springframework.web.servlet.support.RequestContextUtils

class PrettyTimeTagLib {

    static namespace = "prettytime"

    def display = { attrs, body ->
        def date = attrs.remove('date')
        def showTime = Boolean.valueOf(attrs.remove('showTime'))
        def capitalize = Boolean.valueOf(attrs.remove('capitalize'))
        def html5wrapper = Boolean.valueOf(attrs.remove('html5wrapper'))

        if ('org.joda.time.DateTime'.equals(date?.class?.name)) {
            date = date.toDate()
        }

        if ('java.lang.Long'.equals(date?.class?.name)) {
            date = new Date(date)
        }

        if (!date) return

        def prettyTime = new PrettyTime(RequestContextUtils.getLocale(request))
        String result = prettyTime.format(date).trim()

        if (capitalize) result = StringUtils.capitalize(result)

        if (showTime) {
            def format = attrs.remove('format') ?: message(code: 'default.date.format', default: 'hh:mm:ss a')
            result += ', ' + date.format(format)
        }

        if (html5wrapper)
            out << """<time datetime="${g.formatDate(date: date)}" title="${g.formatDate(date: date)}">${result}</time>"""
        else
            out << result
    }

}
