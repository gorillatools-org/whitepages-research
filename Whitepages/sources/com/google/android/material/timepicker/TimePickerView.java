package com.google.android.material.timepicker;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Checkable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.view.ViewCompat;
import com.google.android.material.R$id;
import com.google.android.material.R$layout;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.chip.Chip;

class TimePickerView extends ConstraintLayout {
    private final ClockFaceView clockFace;
    private final ClockHandView clockHandView;
    private final Chip hourView;
    private final Chip minuteView;
    private final View.OnClickListener selectionListener;
    private final MaterialButtonToggleGroup toggle;

    interface OnDoubleTapListener {
    }

    interface OnPeriodChangeListener {
    }

    interface OnSelectionChange {
    }

    public TimePickerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TimePickerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.selectionListener = new View.OnClickListener() {
            public void onClick(View view) {
                OnSelectionChange unused = TimePickerView.this.getClass();
            }
        };
        LayoutInflater.from(context).inflate(R$layout.material_timepicker, this);
        this.clockFace = (ClockFaceView) findViewById(R$id.material_clock_face);
        MaterialButtonToggleGroup materialButtonToggleGroup = (MaterialButtonToggleGroup) findViewById(R$id.material_clock_period_toggle);
        this.toggle = materialButtonToggleGroup;
        materialButtonToggleGroup.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            public void onButtonChecked(MaterialButtonToggleGroup materialButtonToggleGroup, int i, boolean z) {
                OnPeriodChangeListener unused = TimePickerView.this.getClass();
            }
        });
        this.minuteView = (Chip) findViewById(R$id.material_minute_tv);
        this.hourView = (Chip) findViewById(R$id.material_hour_tv);
        this.clockHandView = (ClockHandView) findViewById(R$id.material_clock_hand);
        setupDoubleTap();
        setUpDisplay();
    }

    private void setupDoubleTap() {
        final GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
            public boolean onDoubleTap(MotionEvent motionEvent) {
                OnDoubleTapListener unused = TimePickerView.this.getClass();
                return false;
            }
        });
        AnonymousClass4 r1 = new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (((Checkable) view).isChecked()) {
                    return gestureDetector.onTouchEvent(motionEvent);
                }
                return false;
            }
        };
        this.minuteView.setOnTouchListener(r1);
        this.hourView.setOnTouchListener(r1);
    }

    private void setUpDisplay() {
        this.minuteView.setTag(R$id.selection_type, 12);
        this.hourView.setTag(R$id.selection_type, 10);
        this.minuteView.setOnClickListener(this.selectionListener);
        this.hourView.setOnClickListener(this.selectionListener);
        this.minuteView.setAccessibilityClassName("android.view.View");
        this.hourView.setAccessibilityClassName("android.view.View");
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (view == this && i == 0) {
            updateToggleConstraints();
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        updateToggleConstraints();
    }

    private void updateToggleConstraints() {
        if (this.toggle.getVisibility() == 0) {
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(this);
            constraintSet.clear(R$id.material_clock_display, ViewCompat.getLayoutDirection(this) == 0 ? 2 : 1);
            constraintSet.applyTo(this);
        }
    }
}
