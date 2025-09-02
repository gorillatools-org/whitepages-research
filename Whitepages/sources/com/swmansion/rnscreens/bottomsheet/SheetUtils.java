package com.swmansion.rnscreens.bottomsheet;

public final class SheetUtils {
    public static final SheetUtils INSTANCE = new SheetUtils();

    public final boolean isStateStable(int i) {
        return i == 3 || i == 4 || i == 5 || i == 6;
    }

    private SheetUtils() {
    }

    public final int sheetStateFromDetentIndex(int i, int i2) {
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    throw new IllegalArgumentException("[RNScreens] Invalid detentCount/index combination " + i2 + " / " + i);
                } else if (i == -1) {
                    return 5;
                } else {
                    if (i != 0) {
                        if (i == 1) {
                            return 6;
                        }
                        if (i != 2) {
                            throw new IllegalArgumentException("[RNScreens] Invalid detentCount/index combination " + i2 + " / " + i);
                        }
                    }
                }
            } else if (i == -1) {
                return 5;
            } else {
                if (i != 0) {
                    if (i != 1) {
                        throw new IllegalArgumentException("[RNScreens] Invalid detentCount/index combination " + i2 + " / " + i);
                    }
                }
            }
            return 4;
        } else if (i == -1) {
            return 5;
        } else {
            if (i != 0) {
                throw new IllegalArgumentException("[RNScreens] Invalid detentCount/index combination " + i2 + " / " + i);
            }
        }
        return 3;
    }

    public final int detentIndexFromSheetState(int i, int i2) {
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    throw new IllegalArgumentException("[RNScreens] Invalid state " + i + " for detentCount " + i2);
                } else if (i == 3) {
                    return 2;
                } else {
                    if (i == 4) {
                        return 0;
                    }
                    if (i != 5) {
                        if (i != 6) {
                            throw new IllegalArgumentException("[RNScreens] Invalid state " + i + " for detentCount " + i2);
                        }
                    }
                }
            } else if (i != 3) {
                if (i == 4) {
                    return 0;
                }
                if (i != 5) {
                    throw new IllegalArgumentException("[RNScreens] Invalid state " + i + " for detentCount " + i2);
                }
            }
            return 1;
        } else if (i == 3) {
            return 0;
        } else {
            if (i != 5) {
                throw new IllegalArgumentException("[RNScreens] Invalid state " + i + " for detentCount " + i2);
            }
        }
        return -1;
    }
}
