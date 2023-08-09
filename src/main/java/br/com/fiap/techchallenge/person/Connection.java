package br.com.fiap.techchallenge.person;

public enum Connection {
    PARENT, SON, BROTHER, GRANDFATHER, GRANDSON, UNCLE, NEPHEW, COUSIN, OTHER;

    public static Connection inverseConnection(Connection connection) {
        return switch (connection) {
            case PARENT -> SON;
            case SON -> PARENT;
            case BROTHER -> BROTHER;
            case GRANDFATHER -> GRANDSON;
            case GRANDSON -> GRANDFATHER;
            case UNCLE -> NEPHEW;
            case NEPHEW -> UNCLE;
            case COUSIN -> COUSIN;
            default -> OTHER;
        };
    }
}
