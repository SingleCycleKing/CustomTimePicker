package ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.cycle.single.library.R;

import java.util.Calendar;

public class DatePicker extends FrameLayout {
    private NumberPicker yearPicker;
    private NumberPicker mouthPicker;
    private NumberPicker dayPicker;
    private Calendar mCalendar;

    public DatePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        mCalendar = Calendar.getInstance();
        ((LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(
                R.layout.date_picker, this, true);

        yearPicker = (NumberPicker) findViewById(R.id.time_years);
        mouthPicker = (NumberPicker) findViewById(R.id.time_mouths);
        dayPicker = (NumberPicker) findViewById(R.id.time_days);

        dayPicker.setFormatter(NumberPicker.TWO_DIGIT_FORMATTER);
        mouthPicker.setFormatter(NumberPicker.TWO_DIGIT_FORMATTER);

        yearPicker.setMinValue(1900);
        yearPicker.setMaxValue(2100);
        mouthPicker.setMinValue(1);
        mouthPicker.setMaxValue(12);
        dayPicker.setMinValue(1);

        yearPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                calculateDay(mouthPicker.getValue(), yearPicker.getValue());
                mCalendar.set(Calendar.YEAR, newVal);
            }
        });

        mouthPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                calculateDay(mouthPicker.getValue(), yearPicker.getValue());
                mCalendar.set(Calendar.MONTH, newVal - 1);
            }
        });

        dayPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mCalendar.set(Calendar.DAY_OF_MONTH, newVal);
            }
        });
        updateTime();

    }

    public void updateTime() {
        yearPicker.setValue(mCalendar.get(Calendar.YEAR));
        mouthPicker.setValue(mCalendar.get(Calendar.MONTH) + 1);
        calculateDay(mouthPicker.getValue(), yearPicker.getValue());
        dayPicker.setValue(mCalendar.get(Calendar.DAY_OF_MONTH));
    }


    private void calculateDay(int mouthValue, int yearValue) {
        if (1 == mouthValue || 3 == mouthValue || 5 == mouthValue ||
                7 == mouthValue || 8 == mouthValue || 10 == mouthValue || 12 == mouthValue)
            dayPicker.setMaxValue(31);
        else if (2 == mouthValue) {
            if (0 == yearValue % 4 && (0 != yearValue % 100 || 0 == yearValue % 400))
                dayPicker.setMaxValue(28);
            else dayPicker.setMaxValue(29);
        } else dayPicker.setMaxValue(30);
    }


    public void setCalendar(Calendar calendar) {
        this.mCalendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        this.mCalendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
        this.mCalendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH));
        updateTime();
    }

    public Calendar getCalendar() {
        return mCalendar;
    }
}
