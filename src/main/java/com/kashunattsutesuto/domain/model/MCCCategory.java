package com.kashunattsutesuto.domain.model;

public enum MCCCategory {
    FOOD(new String[]{"5411", "5412"}),
    MEAL(new String[]{"5811", "5812"}),
    OTHERS(new String[]{});
    private final String[] codes;

    MCCCategory(String[] codes) {
        this.codes = codes;
    }

    public String[] getCodes() {
        return codes;
    }

    public static MCCCategory fromCode(String code) {
        for (MCCCategory category : MCCCategory.values()) {
            for (String c : category.getCodes()) {
                if (c.equals(code)) {
                    return category;
                }
            }
        }
        return OTHERS;
    }
}
