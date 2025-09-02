package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.R$dimen;
import com.google.android.material.R$id;
import com.google.android.material.R$integer;
import com.google.android.material.R$layout;
import com.google.android.material.R$string;
import com.google.android.material.button.MaterialButton;
import java.util.Calendar;

public final class MaterialCalendar<S> extends PickerFragment {
    static final Object MONTHS_VIEW_GROUP_TAG = "MONTHS_VIEW_GROUP_TAG";
    static final Object NAVIGATION_NEXT_TAG = "NAVIGATION_NEXT_TAG";
    static final Object NAVIGATION_PREV_TAG = "NAVIGATION_PREV_TAG";
    static final Object SELECTOR_TOGGLE_TAG = "SELECTOR_TOGGLE_TAG";
    /* access modifiers changed from: private */
    public CalendarConstraints calendarConstraints;
    private CalendarSelector calendarSelector;
    private CalendarStyle calendarStyle;
    /* access modifiers changed from: private */
    public Month current;
    /* access modifiers changed from: private */
    public View dayFrame;
    /* access modifiers changed from: private */
    public RecyclerView recyclerView;
    private int themeResId;
    private View yearFrame;
    private RecyclerView yearSelector;

    enum CalendarSelector {
        DAY,
        YEAR
    }

    interface OnDayClickListener {
        void onDayClick(long j);
    }

    public DateSelector getDateSelector() {
        return null;
    }

    public static MaterialCalendar newInstance(DateSelector dateSelector, int i, CalendarConstraints calendarConstraints2) {
        MaterialCalendar materialCalendar = new MaterialCalendar();
        Bundle bundle = new Bundle();
        bundle.putInt("THEME_RES_ID_KEY", i);
        bundle.putParcelable("GRID_SELECTOR_KEY", dateSelector);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", calendarConstraints2);
        bundle.putParcelable("CURRENT_MONTH_KEY", calendarConstraints2.getOpenAt());
        materialCalendar.setArguments(bundle);
        return materialCalendar;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("THEME_RES_ID_KEY", this.themeResId);
        bundle.putParcelable("GRID_SELECTOR_KEY", (Parcelable) null);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", this.calendarConstraints);
        bundle.putParcelable("CURRENT_MONTH_KEY", this.current);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            bundle = getArguments();
        }
        this.themeResId = bundle.getInt("THEME_RES_ID_KEY");
        MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0.m(bundle.getParcelable("GRID_SELECTOR_KEY"));
        this.calendarConstraints = (CalendarConstraints) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        this.current = (Month) bundle.getParcelable("CURRENT_MONTH_KEY");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        final int i;
        int i2;
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getContext(), this.themeResId);
        this.calendarStyle = new CalendarStyle(contextThemeWrapper);
        LayoutInflater cloneInContext = layoutInflater.cloneInContext(contextThemeWrapper);
        Month start = this.calendarConstraints.getStart();
        if (MaterialDatePicker.isFullscreen(contextThemeWrapper)) {
            i2 = R$layout.mtrl_calendar_vertical;
            i = 1;
        } else {
            i2 = R$layout.mtrl_calendar_horizontal;
            i = 0;
        }
        View inflate = cloneInContext.inflate(i2, viewGroup, false);
        inflate.setMinimumHeight(getDialogPickerHeight(requireContext()));
        GridView gridView = (GridView) inflate.findViewById(R$id.mtrl_calendar_days_of_week);
        ViewCompat.setAccessibilityDelegate(gridView, new AccessibilityDelegateCompat() {
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                accessibilityNodeInfoCompat.setCollectionInfo((Object) null);
            }
        });
        gridView.setAdapter(new DaysOfWeekAdapter());
        gridView.setNumColumns(start.daysInWeek);
        gridView.setEnabled(false);
        this.recyclerView = (RecyclerView) inflate.findViewById(R$id.mtrl_calendar_months);
        this.recyclerView.setLayoutManager(new SmoothCalendarLayoutManager(getContext(), i, false) {
            /* access modifiers changed from: protected */
            public void calculateExtraLayoutSpace(RecyclerView.State state, int[] iArr) {
                if (i == 0) {
                    iArr[0] = MaterialCalendar.this.recyclerView.getWidth();
                    iArr[1] = MaterialCalendar.this.recyclerView.getWidth();
                    return;
                }
                iArr[0] = MaterialCalendar.this.recyclerView.getHeight();
                iArr[1] = MaterialCalendar.this.recyclerView.getHeight();
            }
        });
        this.recyclerView.setTag(MONTHS_VIEW_GROUP_TAG);
        MonthsPagerAdapter monthsPagerAdapter = new MonthsPagerAdapter(contextThemeWrapper, (DateSelector) null, this.calendarConstraints, new OnDayClickListener() {
            public void onDayClick(long j) {
                if (MaterialCalendar.this.calendarConstraints.getDateValidator().isValid(j)) {
                    DateSelector unused = MaterialCalendar.this.getClass();
                    throw null;
                }
            }
        });
        this.recyclerView.setAdapter(monthsPagerAdapter);
        int integer = contextThemeWrapper.getResources().getInteger(R$integer.mtrl_calendar_year_selector_span);
        RecyclerView recyclerView2 = (RecyclerView) inflate.findViewById(R$id.mtrl_calendar_year_selector_frame);
        this.yearSelector = recyclerView2;
        if (recyclerView2 != null) {
            recyclerView2.setHasFixedSize(true);
            this.yearSelector.setLayoutManager(new GridLayoutManager((Context) contextThemeWrapper, integer, 1, false));
            this.yearSelector.setAdapter(new YearGridAdapter(this));
            this.yearSelector.addItemDecoration(createItemDecoration());
        }
        if (inflate.findViewById(R$id.month_navigation_fragment_toggle) != null) {
            addActionsToMonthNavigation(inflate, monthsPagerAdapter);
        }
        if (!MaterialDatePicker.isFullscreen(contextThemeWrapper)) {
            new PagerSnapHelper().attachToRecyclerView(this.recyclerView);
        }
        this.recyclerView.scrollToPosition(monthsPagerAdapter.getPosition(this.current));
        return inflate;
    }

    private RecyclerView.ItemDecoration createItemDecoration() {
        return new RecyclerView.ItemDecoration() {
            private final Calendar endItem = UtcDates.getUtcCalendar();
            private final Calendar startItem = UtcDates.getUtcCalendar();

            public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
                if ((recyclerView.getAdapter() instanceof YearGridAdapter) && (recyclerView.getLayoutManager() instanceof GridLayoutManager)) {
                    YearGridAdapter yearGridAdapter = (YearGridAdapter) recyclerView.getAdapter();
                    GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                    DateSelector unused = MaterialCalendar.this.getClass();
                    throw null;
                }
            }
        };
    }

    /* access modifiers changed from: package-private */
    public Month getCurrentMonth() {
        return this.current;
    }

    /* access modifiers changed from: package-private */
    public CalendarConstraints getCalendarConstraints() {
        return this.calendarConstraints;
    }

    /* access modifiers changed from: package-private */
    public void setCurrentMonth(Month month) {
        MonthsPagerAdapter monthsPagerAdapter = (MonthsPagerAdapter) this.recyclerView.getAdapter();
        int position = monthsPagerAdapter.getPosition(month);
        int position2 = position - monthsPagerAdapter.getPosition(this.current);
        boolean z = false;
        boolean z2 = Math.abs(position2) > 3;
        if (position2 > 0) {
            z = true;
        }
        this.current = month;
        if (z2 && z) {
            this.recyclerView.scrollToPosition(position - 3);
            postSmoothRecyclerViewScroll(position);
        } else if (z2) {
            this.recyclerView.scrollToPosition(position + 3);
            postSmoothRecyclerViewScroll(position);
        } else {
            postSmoothRecyclerViewScroll(position);
        }
    }

    /* access modifiers changed from: package-private */
    public CalendarStyle getCalendarStyle() {
        return this.calendarStyle;
    }

    static int getDayHeight(Context context) {
        return context.getResources().getDimensionPixelSize(R$dimen.mtrl_calendar_day_height);
    }

    /* access modifiers changed from: package-private */
    public void setSelector(CalendarSelector calendarSelector2) {
        this.calendarSelector = calendarSelector2;
        if (calendarSelector2 == CalendarSelector.YEAR) {
            this.yearSelector.getLayoutManager().scrollToPosition(((YearGridAdapter) this.yearSelector.getAdapter()).getPositionForYear(this.current.year));
            this.yearFrame.setVisibility(0);
            this.dayFrame.setVisibility(8);
        } else if (calendarSelector2 == CalendarSelector.DAY) {
            this.yearFrame.setVisibility(8);
            this.dayFrame.setVisibility(0);
            setCurrentMonth(this.current);
        }
    }

    /* access modifiers changed from: package-private */
    public void toggleVisibleSelector() {
        CalendarSelector calendarSelector2 = this.calendarSelector;
        CalendarSelector calendarSelector3 = CalendarSelector.YEAR;
        if (calendarSelector2 == calendarSelector3) {
            setSelector(CalendarSelector.DAY);
        } else if (calendarSelector2 == CalendarSelector.DAY) {
            setSelector(calendarSelector3);
        }
    }

    private void addActionsToMonthNavigation(View view, final MonthsPagerAdapter monthsPagerAdapter) {
        final MaterialButton materialButton = (MaterialButton) view.findViewById(R$id.month_navigation_fragment_toggle);
        materialButton.setTag(SELECTOR_TOGGLE_TAG);
        ViewCompat.setAccessibilityDelegate(materialButton, new AccessibilityDelegateCompat() {
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                String str;
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                if (MaterialCalendar.this.dayFrame.getVisibility() == 0) {
                    str = MaterialCalendar.this.getString(R$string.mtrl_picker_toggle_to_year_selection);
                } else {
                    str = MaterialCalendar.this.getString(R$string.mtrl_picker_toggle_to_day_selection);
                }
                accessibilityNodeInfoCompat.setHintText(str);
            }
        });
        MaterialButton materialButton2 = (MaterialButton) view.findViewById(R$id.month_navigation_previous);
        materialButton2.setTag(NAVIGATION_PREV_TAG);
        MaterialButton materialButton3 = (MaterialButton) view.findViewById(R$id.month_navigation_next);
        materialButton3.setTag(NAVIGATION_NEXT_TAG);
        this.yearFrame = view.findViewById(R$id.mtrl_calendar_year_selector_frame);
        this.dayFrame = view.findViewById(R$id.mtrl_calendar_day_selector_frame);
        setSelector(CalendarSelector.DAY);
        materialButton.setText(this.current.getLongName());
        this.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                int i3;
                if (i < 0) {
                    i3 = MaterialCalendar.this.getLayoutManager().findFirstVisibleItemPosition();
                } else {
                    i3 = MaterialCalendar.this.getLayoutManager().findLastVisibleItemPosition();
                }
                Month unused = MaterialCalendar.this.current = monthsPagerAdapter.getPageMonth(i3);
                materialButton.setText(monthsPagerAdapter.getPageTitle(i3));
            }

            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                if (i == 0) {
                    recyclerView.announceForAccessibility(materialButton.getText());
                }
            }
        });
        materialButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MaterialCalendar.this.toggleVisibleSelector();
            }
        });
        materialButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int findFirstVisibleItemPosition = MaterialCalendar.this.getLayoutManager().findFirstVisibleItemPosition() + 1;
                if (findFirstVisibleItemPosition < MaterialCalendar.this.recyclerView.getAdapter().getItemCount()) {
                    MaterialCalendar.this.setCurrentMonth(monthsPagerAdapter.getPageMonth(findFirstVisibleItemPosition));
                }
            }
        });
        materialButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int findLastVisibleItemPosition = MaterialCalendar.this.getLayoutManager().findLastVisibleItemPosition() - 1;
                if (findLastVisibleItemPosition >= 0) {
                    MaterialCalendar.this.setCurrentMonth(monthsPagerAdapter.getPageMonth(findLastVisibleItemPosition));
                }
            }
        });
    }

    private void postSmoothRecyclerViewScroll(final int i) {
        this.recyclerView.post(new Runnable() {
            public void run() {
                MaterialCalendar.this.recyclerView.smoothScrollToPosition(i);
            }
        });
    }

    private static int getDialogPickerHeight(Context context) {
        Resources resources = context.getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(R$dimen.mtrl_calendar_navigation_height) + resources.getDimensionPixelOffset(R$dimen.mtrl_calendar_navigation_top_padding) + resources.getDimensionPixelOffset(R$dimen.mtrl_calendar_navigation_bottom_padding);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R$dimen.mtrl_calendar_days_of_week_height);
        int i = MonthAdapter.MAXIMUM_WEEKS;
        return dimensionPixelSize + dimensionPixelSize2 + (resources.getDimensionPixelSize(R$dimen.mtrl_calendar_day_height) * i) + ((i - 1) * resources.getDimensionPixelOffset(R$dimen.mtrl_calendar_month_vertical_padding)) + resources.getDimensionPixelOffset(R$dimen.mtrl_calendar_bottom_padding);
    }

    /* access modifiers changed from: package-private */
    public LinearLayoutManager getLayoutManager() {
        return (LinearLayoutManager) this.recyclerView.getLayoutManager();
    }

    public boolean addOnSelectionChangedListener(OnSelectionChangedListener onSelectionChangedListener) {
        return super.addOnSelectionChangedListener(onSelectionChangedListener);
    }
}
