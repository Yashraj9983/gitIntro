package bootstrap;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.EnhancedPatternLayout;
import org.apache.log4j.Level;
import org.apache.log4j.Priority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Driver {
    static Logger logger = LoggerFactory.getLogger(Driver.class);

    public static void main(String[] args) throws Exception {
//        configureLogging(Driver.getArgument(args, "log.file.path"),
//                Driver.getArgument(args, "log.level"));
        configureLogging("var/log/gitIgnore/","INFO");
        logger.info("Hello..");

    }

    public static String configureLogging(String logFile, String logLevel) {
        DailyRollingFileAppender dailyRollingFileAppender = new DailyRollingFileAppender();

        String logFilename = logFile + "gitIntroLogs.log";
        switch (logLevel) {
            case "DEBUG": {
                dailyRollingFileAppender.setThreshold(Level.toLevel(Priority.DEBUG_INT));
            }
            case "WARN": {
                dailyRollingFileAppender.setThreshold(Level.toLevel(Priority.WARN_INT));
            }
            case "ERROR": {
                dailyRollingFileAppender.setThreshold(Level.toLevel(Priority.ERROR_INT));
            }
            default: {
                dailyRollingFileAppender.setThreshold(Level.toLevel(Priority.INFO_INT));
            }
            break;
        }

        System.out.println("Log files written out at " + logFilename);
        dailyRollingFileAppender.setFile(logFilename);
        dailyRollingFileAppender.setLayout(new EnhancedPatternLayout("%d [%t] %-5p %c - %m%n"));

        dailyRollingFileAppender.activateOptions();
        org.apache.log4j.Logger.getRootLogger().addAppender(dailyRollingFileAppender);
        return dailyRollingFileAppender.getFile();
    }

    public static String getArgument(String[] args, String argumentName) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].contains("--")) {
                String arg = args[i].replaceAll("--", "");
                if (arg.equals(argumentName)) {
                    return args[i + 1];
                }
            }
        }
        return null;
    }

}
