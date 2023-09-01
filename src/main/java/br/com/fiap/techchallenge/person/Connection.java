package br.com.fiap.techchallenge.person;

import java.util.List;

public enum Connection {
    PARENT, SON, BROTHER, GRANDFATHER, GRANDSON, UNCLE, NEPHEW, COUSIN, OTHER;

    public Connection inverseConnection() {
        return switch (this) {
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
