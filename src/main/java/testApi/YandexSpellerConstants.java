package testApi;

public class YandexSpellerConstants {
    static final String PARAM_TEXT = "text";
    static final String PARAM_OPTIONS = "options";
    static final String PARAM_LANG = "lang";
    static final String PARAM_FORMAT = "format";

    public enum Language {
        RU("ru"),
        UK("uk"),
        EN("en"),
        WRONG_LANGUAGE("ruuk");

        private String languageCode;

        String langCode() {
            return languageCode;
        }

        Language(String lang) {
            this.languageCode = lang;
        }
    }

    public enum ErrorCodes {
        ERROR_UNKNOWN_WORD("\"code\":1"),
        ERROR_REPEAT_WORD("\"code\":2"),
        ERROR_CAPITALIZATION("\"code\":3"),
        ERROR_TOO_MANY_ERRORS("\"code\":4");

        private String value;

        ErrorCodes(String value) {
            this.value = value;
        }


        @Override
        public String toString() {
            return value;
        }
    }

    public enum Format {
        PLAIN("plain"),
        HTML("html"),
        WRONG_FORMAT("123");

        private String value;

        Format(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public enum StatusLine {
        SL_BAD_REQUEST("HTTP/1.1 400 Bad request"),
        SL_METHOD_NOT_ALLOWED("HTTP/1.1 405 Method not allowed");

        private String value;

        StatusLine(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }

    }

    public enum InvalidParameter {
        LANG("Invalid parameter 'lang'"),
        FORMAT("Invalid parameter 'format'"),
        METHOD("Method not allowed");

        private String value;

        InvalidParameter(String s) {
        }

        @Override
        public String toString() {
            return value;
        }

    }

    public enum Options {
        IGNORE_DIGITS("2"),
        IGNORE_URLS("4"),
        FIND_REPEAT_WORDS("8"),
        IGNORE_CAPITALIZATION("512");

        String value;

        Options(String i) {
            value = i;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public enum Examples {
        MOTHER("moter", "mother"),
        REQUISITE("requisitee", "requisite"),
        REPEAT("repeat repeat word word", ""),
        DIFFERENT_LETTERS("SuNShinE", "Sunshine"),
        WITH_DIGITS("m0thers", "mothers"),
        SPLIT_PHRASE("sheismymother", "she is my mother"),
        WORD_WITH_LEADING_DIGITS("1letter", "letter"),
        CORRECT_WORD("correct", "correct");

        String word;
        String s;

        Examples(String word, String s) {
            this.word = word;
            this.s = s;
        }
    }
}


