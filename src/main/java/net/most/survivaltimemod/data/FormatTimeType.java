package net.most.survivaltimemod.data;

public enum FormatTimeType {
    DEFAULT,
    NAMED,
    COMMAS,
    DEPENDS,
    COMMAS_NAMED,
    DEPENDS_NAMED,
    DEPENDS_COMMAS,
    DEPENDS_COMMAS_NAMED,
    HH_MM,
    HH_MM_NAMED,
    HH_MM_COMMAS,
    HH_MM_COMMAS_NAMED,
    MM_SS,
    MM_SS_NAMED,
    MM_SS_COMMAS,
    MM_SS_COMMAS_NAMED,
    ONLY_MINUTES,
    ONLY_MINUTES_NAMED,
    ONLY_HOURS,
    ONLY_HOURS_NAMED,
    ONLY_SECONDS,
    ONLY_SECONDS_NAMED;

    private static int parseHours(float time) {
        return (int) (time / 3600);
    }

    private static int parseMinutes(float time) {
        return (int) ((time % 3600) / 60);
    }

    private static int parseSeconds(float time) {
        return (int) (time % 60);
    }

    private static String getDefaultFormat(float time) {
        int hours = parseHours(time);
        int minutes = parseMinutes(time);
        int seconds = parseSeconds(time);
        if(hours == 0) return String.format("%02d:%02d", minutes, seconds);
        if(minutes == 0) return String.format("%02d:%02d", hours, seconds);
        if(seconds == 0) return String.format("%02d:%02d", hours, minutes);


        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    private static String getNamedFormat(float time) {
        int hours = parseHours(time);
        int minutes = parseMinutes(time);
        int seconds = parseSeconds(time);
        if(hours == 0) return String.format("%02dm %02ds", minutes, seconds);
        if(minutes == 0) return String.format("%02dh %02ds", hours, seconds);
        if(seconds == 0) return String.format("%02dh %02dm", hours, minutes);

        return String.format("%02dh %02dm %02ds", hours, minutes, seconds);
    }

    private static String getCommasFormat(float time) {
        int hours = parseHours(time);
        int minutes = parseMinutes(time);
        int seconds = parseSeconds(time);
        if(hours == 0) return String.format("%02d, %02d", minutes, seconds);
        if(minutes == 0) return String.format("%02d, %02d", hours, seconds);
        if(seconds == 0) return String.format("%02d, %02d", hours, minutes);

        return String.format("%02d, %02d, %02d", hours, minutes, seconds);
    }

    private static String getCommasNamedFormat(float time) {
        int hours = parseHours(time);
        int minutes = parseMinutes(time);
        int seconds = parseSeconds(time);
        if(hours == 0) return String.format("%02dm, %02ds", minutes, seconds);
        if(minutes == 0) return String.format("%02dh, %02ds", hours, seconds);
        if(seconds == 0) return String.format("%02dh, %02dm", hours, minutes);

        return String.format("%02dh, %02dm, %02ds", hours, minutes, seconds);
    }

    private static String getHH_SSFormat(float time) {
        int hours = parseHours(time);
        int seconds = parseSeconds(time);
        if(hours == 0) return String.format("%02d", seconds);
        if(seconds == 0) return String.format("%02d", hours);

        return String.format("%02d:%02d", hours, seconds);
    }

    private static String getHH_SSNamedFormat(float time) {
        int hours = parseHours(time);
        int seconds = parseSeconds(time);
        if(hours == 0) return String.format("%02ds", seconds);
        if(seconds == 0) return String.format("%02dh", hours);

        return String.format("%02dh %02ds", hours, seconds);
    }

    private static String getHH_SSCommasFormat(float time) {
        int hours = parseHours(time);
        int seconds = parseSeconds(time);
        if(hours == 0) return String.format("%02d", seconds);
        if(seconds == 0) return String.format("%02d", hours);

        return String.format("%02d, %02d", hours, seconds);
    }

    private static String getOnlyMinutesFormat(float time) {
        int minutes = parseMinutes(time);
        return String.format("%02d", minutes);
    }

    private static String getOnlyMinutesNamedFormat(float time) {
        int minutes = parseMinutes(time);
        return String.format("%02dm", minutes);
    }

    private static String getOnlyHoursFormat(float time) {
        int hours = parseHours(time);
        return String.format("%02d", hours);
    }

    private static String getOnlyHoursNamedFormat(float time) {
        int hours = parseHours(time);
        return String.format("%02dh", hours);
    }

    private static String getOnlySecondsFormat(float time) {
        int seconds = parseSeconds(time);
        return String.format("%02d", seconds);
    }

    private static String getOnlySecondsNamedFormat(float time) {
        int seconds = parseSeconds(time);
        return String.format("%02ds", seconds);
    }

    private static String getHH_SSCommasNamedFormat(float time) {
        int hours = parseHours(time);
        int seconds = parseSeconds(time);

        return String.format("%02dh, %02ds", hours, seconds);
    }

    private static String getMM_SSFormat(float time) {
        int minutes = parseMinutes(time);
        int seconds = parseSeconds(time);
        if(minutes == 0) return String.format("%02d", seconds);
        if(seconds == 0) return String.format("%02d", minutes);
        return String.format("%02d:%02d", minutes, seconds);
    }

    private static String getMM_SSNamedFormat(float time) {
        int minutes = parseMinutes(time);
        int seconds = parseSeconds(time);
        if(minutes == 0) return String.format("%02ds", seconds);
        if(seconds == 0) return String.format("%02dm", minutes);

        return String.format("%02dm %02ds", minutes, seconds);
    }

    private static String getMM_SSCommasFormat(float time) {
        int minutes = parseMinutes(time);
        int seconds = parseSeconds(time);
        if(minutes == 0) return String.format("%02d", seconds);
        if(seconds == 0) return String.format("%02d", minutes);

        return String.format("%02d, %02d", minutes, seconds);
    }

    private static String getMM_SSCommasNamedFormat(float time) {
        int minutes = parseMinutes(time);
        int seconds = parseSeconds(time);
        if(minutes == 0) return String.format("%02d", seconds);
        if(seconds == 0) return String.format("%02d", minutes);

        return String.format("%02dm, %02ds", minutes, seconds);
    }

    private static String getDependsFormat(float time) {

        if (time >= 3600) {
            return getDefaultFormat(time);
        } else if (time >= 60) {
            return getMM_SSFormat(time);
        } else {
            return getOnlySecondsFormat(time);
        }
    }

    private static String getDependsNamedFormat(float time) {

        if (time >= 3600) {
            return getNamedFormat(time);
        } else if (time >= 60) {
            return getMM_SSNamedFormat(time);
        } else {
            return getOnlySecondsNamedFormat(time);
        }
    }

    private static String getDependsCommasFormat(float time) {

        if (time >= 3600) {
            return getCommasFormat(time);
        } else if (time >= 60) {
            return getMM_SSCommasFormat(time);
        } else {
            return getOnlySecondsFormat(time);
        }
    }

    private static String getDependsCommasNamedFormat(float time) {

        if (time >= 3600) {
            return getCommasNamedFormat(time);
        } else if (time >= 60) {
            return getMM_SSCommasNamedFormat(time);
        } else {
            return getOnlySecondsNamedFormat(time);
        }
    }


    /**
     * Returns a formatted string based on the type.
     * types:
     * | DEFAULT: 00:00:00 |
     * NAMED: 00h 00m 00s |
     * COMMAS: 00, 00, 00 |
     * COMMAS_NAMED: 00h, 00m, 00s |
     * HH_SS: 00h 00s |
     * ONLY_MINUTES: 00m |
     * ONLY_HOURS: 00h |
     * ONLY_SECONDS: 00s |
     *
     * @return String with the selected format.
     */
    public static String getFormattedStringByType(FormatTimeType type, float time) {

        return switch (type) {
            case DEFAULT -> getDefaultFormat(time);
            case NAMED -> getNamedFormat(time);
            case COMMAS -> getCommasFormat(time);
            case COMMAS_NAMED -> getCommasNamedFormat(time);
            case HH_MM -> getHH_SSFormat(time);
            case HH_MM_NAMED -> getHH_SSNamedFormat(time);
            case HH_MM_COMMAS -> getHH_SSCommasFormat(time);
            case HH_MM_COMMAS_NAMED -> getHH_SSCommasNamedFormat(time);
            case MM_SS -> getMM_SSFormat(time);
            case MM_SS_NAMED -> getMM_SSNamedFormat(time);
            case MM_SS_COMMAS -> getMM_SSCommasFormat(time);
            case MM_SS_COMMAS_NAMED -> getMM_SSCommasNamedFormat(time);
            case ONLY_MINUTES -> getOnlyMinutesFormat(time);
            case ONLY_MINUTES_NAMED -> getOnlyMinutesNamedFormat(time);
            case ONLY_HOURS -> getOnlyHoursFormat(time);
            case ONLY_HOURS_NAMED -> getOnlyHoursNamedFormat(time);
            case ONLY_SECONDS -> getOnlySecondsFormat(time);
            case ONLY_SECONDS_NAMED -> getOnlySecondsNamedFormat(time);
            case DEPENDS -> getDependsFormat(time);
            case DEPENDS_NAMED -> getDependsNamedFormat(time);
            case DEPENDS_COMMAS -> getDependsCommasFormat(time);
            case DEPENDS_COMMAS_NAMED -> getDependsCommasNamedFormat(time);
        };


    }

}
