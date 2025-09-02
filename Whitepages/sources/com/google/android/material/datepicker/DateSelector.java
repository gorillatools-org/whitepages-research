package com.google.android.material.datepicker;

import android.os.Parcelable;
import java.util.Collection;

public interface DateSelector extends Parcelable {
    Collection getSelectedDays();
}
