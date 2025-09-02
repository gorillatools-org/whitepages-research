package com.google.android.gms.common.sqlite;

import android.database.AbstractWindowedCursor;
import android.database.CrossProcessCursor;
import android.database.Cursor;
import android.database.CursorWindow;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class CursorWrapper extends android.database.CursorWrapper implements CrossProcessCursor {
    private AbstractWindowedCursor zza;

    @KeepForSdk
    public CursorWrapper(Cursor cursor) {
        super(cursor);
        for (int i = 0; i < 10 && (cursor instanceof android.database.CursorWrapper); i++) {
            cursor = ((android.database.CursorWrapper) cursor).getWrappedCursor();
        }
        if (cursor instanceof AbstractWindowedCursor) {
            this.zza = (AbstractWindowedCursor) cursor;
            return;
        }
        throw new IllegalArgumentException("Unknown type: ".concat(cursor.getClass().getName()));
    }

    @KeepForSdk
    public void fillWindow(int i, CursorWindow cursorWindow) {
        this.zza.fillWindow(i, cursorWindow);
    }

    @KeepForSdk
    public CursorWindow getWindow() {
        return this.zza.getWindow();
    }

    public final /* synthetic */ Cursor getWrappedCursor() {
        return this.zza;
    }

    public final boolean onMove(int i, int i2) {
        return this.zza.onMove(i, i2);
    }

    @KeepForSdk
    public void setWindow(CursorWindow cursorWindow) {
        this.zza.setWindow(cursorWindow);
    }
}
