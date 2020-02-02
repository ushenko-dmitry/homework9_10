package ru.mail.dimaushenko.constants;

public class PropertyConstants {

    public static final String SQL_REQUEST_CREATE_TABLE_USER = "sql.request.table.create.user";
    public static final String SQL_REQUEST_CREATE_TABLE_USER_INFORMATION = "sql.request.table.create.user_information";

    public static final String SQL_REQUEST_DROP_TABLE_USER = "sql.request.table.drop.user";
    public static final String SQL_REQUEST_DROP_TABLE_USER_INFORMATION = "sql.request.table.drop.user_information";

    public static final String SQL_REQUEST_INSERT_USER = "sql.request.insert.user";
    public static final String SQL_REQUEST_INSERT_USER_INFORMATION = "sql.request.insert.user_information";

    public static final String SQL_REQUEST_SELECT_ALL_USERS = "sql.request.select.all.user";
    public static final String SQL_REQUEST_SELECT_USER_INFORMATION_BY_ID = "sql.request.select.user_information.by_id";

    public static final String SQL_REQUEST_REMOVE_USER_BY_ID = "sql.request.remove.user.by.id";

    public static final String SQL_REQUEST_UPDATE_USER = "sql.request.update.user";
    public static final String SQL_REQUEST_UPDATE_USER_INFORMATION = "sql.request.update.user_information";

    public static final String SQL_REQUEST_IS_USER_INFORMATION_FOUND_BY_ID = "sql.request.user_information.is_found.by_id";
    public static final String SQL_REQUEST_IS_USER_FOUND_BY_ID = "sql.request.user.is_found.by_id";

    public static final String SQL_COLUMN_USER_ID = "sql.column.user.id";
    public static final String SQL_COLUMN_USER_USERNAME = "sql.column.user.username";
    public static final String SQL_COLUMN_USER_PASSWORD = "sql.column.user.password";
    public static final String SQL_COLUMN_USER_IS_ACTIVE = "sql.column.user.is_active";
    public static final String SQL_COLUMN_USER_AGE = "sql.column.user.age";

    public static final String SQL_COLUMN_USER_INFORMATION_ID = "sql.column.user_information.user_id";
    public static final String SQL_COLUMN_USER_INFORMATION_ADDRESS = "sql.column.user_information.address";
    public static final String SQL_COLUMN_USER_INFORMATION_TELEPHONE = "sql.column.user_information.telephone";

    public static final String FORM_PARAMETER_ID = "form.name.id";
    public static final String FORM_PARAMETER_USERNAME = "form.name.username";
    public static final String FORM_PARAMETER_PASSWORD = "form.name.password";
    public static final String FORM_PARAMETER_IS_ACTIVE = "form.name.is_active";
    public static final String FORM_PARAMETER_AGE = "form.name.age";

    public static final String FORM_PARAMETER_ADDRESS = "form.name.address";

    private PropertyConstants() {
    }

}
