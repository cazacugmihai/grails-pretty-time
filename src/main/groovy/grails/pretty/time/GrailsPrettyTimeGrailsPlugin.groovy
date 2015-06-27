package grails.pretty.time

import grails.plugins.*

class GrailsPrettyTimeGrailsPlugin extends Plugin {

    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "3.0.1 > *"
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp"
    ]

    // TODO Fill in these fields
    def title = "Pretty-time plugin" // Headline display name of the plugin
    def author = "Cazacu Mihai"
    def authorEmail = "cazacugmihai@gmail.com"
    def description = 'A plugin that allows you to display human readable, relative timestamps.'

    // URL to the plugin's documentation
    def documentation = "https://github.com/cazacugmihai/grails-pretty-time/blob/master/README.md"

    // Extra (optional) plugin metadata

    // License: one of 'APACHE', 'GPL2', 'GPL3'
    def license = "APACHE"

    // Any additional developers beyond the author specified above.
    def developers = [
            [ name: "Janusz Slota", email: "janusz.slota@nixilla.com" ],
            [ name: "Diego Toharia", email: "diego@toharia.com" ],
            [ name: "Stefan Glase", email: "stefan.glase@googlemail.com" ],
            [name: "Manish Kapoor", email: "manishkapoor_1989@yahoo.co.in"]
    ]

    // Location of the plugin's issue tracker.
    def issueManagement = [ system: "Github", url: "https://github.com/cazacugmihai/grails-pretty-time/issues" ]

    // Online location of the plugin's browseable source code.
    def scm = [ url: "https://github.com/cazacugmihai/grails-pretty-time" ]

    Closure doWithSpring() { {->
        // TODO Implement runtime spring config (optional)
    }
    }

    void doWithDynamicMethods() {
        // TODO Implement registering dynamic methods to classes (optional)
    }

    void doWithApplicationContext() {
        // TODO Implement post initialization spring config (optional)
    }

    void onChange(Map<String, Object> event) {
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    void onConfigChange(Map<String, Object> event) {
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }

    void onShutdown(Map<String, Object> event) {
        // TODO Implement code that is executed when the application shuts down (optional)
    }
}
