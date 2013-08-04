grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'

    if (grailsVersion >= '2.1.3')
        legacyResolve true // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility

    repositories {
        grailsCentral()
        mavenCentral()
        mavenLocal()
    }
    dependencies {

        compile 'org.ocpsoft.prettytime:prettytime:3.0.2.Final'

        compile('joda-time:joda-time:2.2') {
            export = false
        }
    }

    plugins {
        build(":release:2.2.0") {
            export = false
        }

        compile(':code-coverage:1.2.6') {
            export = false
        }
    }
}
