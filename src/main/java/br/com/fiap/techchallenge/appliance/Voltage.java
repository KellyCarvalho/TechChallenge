package br.com.fiap.techchallenge.appliance;

import java.util.Arrays;

public enum Voltage {
    ONE_HUNDRED_TWENTY_SEVEN("127V"),
    TWO_HUNDRED_AND_TWENTY("220V"),
    BIVOLT("bivolt");

    private final String displayName;

    Voltage(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Voltage get(String voltage){
        return Arrays.stream(Voltage.values())
                .filter(v -> v.getDisplayName().equals(voltage))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Status não suportado"));
    }
}
