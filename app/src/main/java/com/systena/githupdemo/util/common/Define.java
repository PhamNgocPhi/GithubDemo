package com.systena.githupdemo.util.common;

public class Define {
    public class Preference {
        public static final String PREF_FILE_NAME = "app_preference";
        public static final String KEY_ACCESS_TOKEN = "access_token";
    }

    public class TimeFormat {
        public static final String FORMAT_1 = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    }

    public class ViewState {
        public static final int SHOW_LOADING = -1;
        public static final int HIDE_lOADING = -2;
        public static final int SHOW_ERROR = -3;

        public class Splash {
            public static final int GO_LOGIN = 1;
            public static final int GO_HOME = 2;
        }

        public class Login {
            public static final int GO_HOME = 1;
            public static final int ERROR_VALIDATE = 2;
            public static final int LOGIN_FAILED = 3;
        }

        public class Register {
            public static final int GO_HOME = 1;
            public static final int ERROR_VALIDATE = 2;
            public static final int REGISTER_FAILED = 3;
        }

        public class Github {
            public static final int SEARCH_ERROR = 1;
        }

    }

    public class ResponseStatus {
        public static final int LOADING = 1;
        public static final int SUCCESS = 2;
        public static final int ERROR = 0;
    }

    public class Database {
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "app_database";

        public class User {
            public static final String TABLE_NAME = "dtb_user";
            public static final String ID = "id";
            public static final String LOG_IN = "login";
            public static final String AVATAR_URL = "avatar_url";
            public static final String NAME = "name";
            public static final String COMPANY = "company";
            public static final String EMAIL = "email";
            public static final String LOCATION = "location";
        }

        public class Repo {
            public static final String TABLE_NAME = "dtb_repo";
            public static final String ID = "id";
            public static final String NAME = "name";
            public static final String FULL_NAME = "full_name";
            public static final String DESCRIPTION = "description";
            public static final String CONTRIBUTORS_URL = "contributors_url";
            public static final String STARGAZERS_COUNT = "stargazers_count";
            public static final String UPDATED_AT = "updated_at";
            public static final String LANGUAGE = "language";
        }
    }

    public class Network {
        public static final long DEFAULT_TIMEOUT = 60L;

        public class ErrorCode {
            public static final String LOST_INTERNET = "E1000";
            public static final String NO_RESPONSE = "E1001";
            public static final String UNKNOWN_ERROR = "E1002";
            public static final String TIME_OUT = "E1003";
            public static final String ACCESS_TOKEN_EXPIRED = "E1004";
        }

        public class BaseResponse {
            public static final String SUCCESS = "success";
            public static final String DATA = "data";
            public static final String PAGE = "page";
            public static final String ERROR = "error";
            public static final String ERROR_CODE = "error_code";
            public static final String ERROR_MESSAGE = "error_message";

        }
    }
}
