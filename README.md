# PrettyTime


PrettyTime is an OpenSource time formatting library. PrettyTime creates human readable, relative timestamps like those seen on Digg, Twitter, and Facebook. It’s simple, get started “right now!” and in over 25 languages!

This plugin allows you to display human readable, relative timestamps. It is based on [PrettyTime](http://ocpsoft.org/prettytime/) OpenSource time formatting library.

## Installation

```
compile ":pretty-time:3.0.2.Final-1.0.0"
```

## Installation for 2.x
```
grails install-plugin pretty-time
plugins {
    compile ":pretty-time:2.1.3.Final-1.0.1"
}
```

## Requirements

* Grails 2.0 or above
* for Grails 1.x use version 0.3

## Usage

```
<prettytime:display date="${someDate}" />
```

outputs:

```
"right now", "2 days ago", or "3 months from now"
```

## Internationalization (I18n)

Build in - uses prettytime library translations. TagLib included in this plugin respects current locale.

## Tag attributes

<table>
    <tr>
        <th>Attribute</th>
        <th>Description</th>
    </tr>
    <tr class="table-odd">
        <td><strong class="bold">date</strong></td>
        <td>The date object to format.</td>
    </tr>
    <tr class="table-even">
        <td><strong class="bold">capitalize</strong></td>
        <td>Capitalize the output text (default: false). Ex: "<strong class="bold">m</strong>oments ago" will be capitalized to "<strong class="bold">M</strong>oments ago".</td>
    </tr>
    <tr class="table-odd">
        <td><strong class="bold">showTime</strong></td>
        <td>Show the time (default: false). Ex: "2 days ago<strong class="bold">, 12:00:25 AM</strong>".</td>
    </tr>
    <tr class="table-even">
        <td><strong class="bold">html5wrapper</strong></td>
        <td>Wrap the output text (default: false). Ex: "moments ago" will be wrapped with "&lt;time datetime=&quot;some date&quot; title=&quot;some date&quot;&gt;moments ago&lt;/time&gt;".</td>
    </tr>
    <tr class="table-odd">
        <td><strong class="bold">format</strong></td>
        <td>The format to use for the date (default: "hh:mm:ss a"). The default value is set by "default.date.format" in I18n.</td>
    </tr>
</table>
