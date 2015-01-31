package ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;


import com.cycle.single.library.R;

import java.util.Calendar;

public class TimePicker extends FrameLayout {

    private NumberPicker hourPicker;
    private NumberPicker minPicker;
    private Calendar mCalendar;

    public TimePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        mCalendar = Calendar.getInstance();
        ((LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(
                R.layout.time_picker, this, true);

        hourPicker = (NumberPicker) findViewById(R.id.time_hours);
        minPicker = (NumberPicker) findViewById(R.id.time_minutes);
        minPicker.setFormatter(NumberPicker.TWO_DIGIT_FORMATTER);
        hourPicker.setFormatter(NumberPicker.TWO_DIGIT_FORMATTER);

        hourPicker.setMinValue(0);
        hourPicker.setMaxValue(23);
        minPicker.setMinValue(0);
        minPicker.setMaxValue(59);

        minPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker picker, int oldVal,
                                      int newVal) {
                mCalendar.set(Calendar.MINUTE, newVal);

            }
        });

        hourPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker picker, int oldVal,
                                      int newVal) {
                mCalendar.set(Calendar.HOUR, newVal);
            }
        });

        updateTime();

    }


    public TimePicker(Context context) {
        this(context, null);
    }

    private void updateTime() {

        hourPicker.setValue(mCalendar.get(Calendar.HOUR_OF_DAY));
        minPicker.setValue(mCalendar.get(Calendar.MINUTE));
    }

    public int getHour() {
        return hourPicker.getValue();
    }

    public int getMinute() {
        return mCalendar.get(Calendar.MINUTE);
    }

    public void setCalendar(Calendar calendar) {
        this.mCalendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
        this.mCalendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
        updateTime();
    }


}
