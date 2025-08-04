package in.trois.stockmanagement.logging;


import in.trois.stockmanagement.constants.logging.LogConstant;
import in.trois.stockmanagement.constants.logging.LogState;
import in.trois.stockmanagement.utils.ValidationUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogStringBuilder {
    /**
     * @param logState
     * @param classType
     * @param functionName
     * @param logMessage
     * @param logKeyValues
     * @return
     */
    public static String getLoggerString(LogState logState, Class<?> classType, String functionName, String logMessage,
                                         LogKeyValues... logKeyValues) {
        String appenderString = null;
        try {
            appenderString = logState.getPrefix() + classType.getSimpleName() + "." + functionName + "()"
                    + LogConstant.STRING_SEPERATOR;
            if (ValidationUtils.isValid(logMessage)) {
                appenderString += logMessage + LogConstant.STRING_SEPERATOR;
            }
            if (logKeyValues != null && logKeyValues.length > 0) {
                for (int i = 0; i < logKeyValues.length; i++) {
                    if (i != 0) {
                        appenderString += ",";
                    }
                    appenderString += logKeyValues[i].getParamName() + ":" + logKeyValues[i].getParamValue();
                }
            }
        } catch (Exception ex) {
            log.error(LogState.EXCEPTION.getPrefix() + "LogStringBuilder.getLoggerString()"
                    + LogConstant.STRING_SEPERATOR, ex);
        }
        return appenderString;
    }
}