import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.rolling.FixedWindowRollingPolicy;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy;

appender("CONSOLE", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
      pattern = "[%date] %level %thread %logger - %msg%n"
    }
}

println "*** Application host: $hostname ***"
if (!(hostname =~ /cloudbees/)) {
appender("FILE", RollingFileAppender) {
    file = "${System.getProperty('user.home')}/.figurate/logs/figurate-base.log"
    append = true
    encoder(PatternLayoutEncoder) {
      pattern = "[%date] %level %logger - %msg%n"
    }
    rollingPolicy(FixedWindowRollingPolicy) {
        fileNamePattern = "${System.getProperty('user.home')}/.figurate/logs/figurate-base.log.%i"
        minIndex = 1
        maxIndex = 4
    }
    triggeringPolicy(SizeBasedTriggeringPolicy) {
        maxFileSize = '100KB'
    }
}
}

root(Level.INFO, ["CONSOLE", "FILE"])
