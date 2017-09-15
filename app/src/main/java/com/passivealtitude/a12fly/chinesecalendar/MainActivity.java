package com.passivealtitude.a12fly.chinesecalendar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.coolerfall.widget.lunar.LunarView;
import com.coolerfall.widget.lunar.MonthDay;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements LunarView.OnDatePickListener {

    private TextView gDayOfMonth;
    private TextView gDay;
    private TextView gMonth;
    private TextView gYear;
    private TextView lDay;
    private TextView lMonth;
    private TextView hr;
    private TextView min;
    private TextView ampm;
    private LunarView mLunarView;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLunarView = (LunarView) findViewById(R.id.lunar);
        mLunarView.setOnDatePickListener(this);

        gDayOfMonth = (TextView) findViewById(R.id.tvtoday);
        gDay = (TextView) findViewById(R.id.yDay);
        gMonth = (TextView) findViewById(R.id.Ymonth);
        gYear = (TextView) findViewById(R.id.Yyear);
        lDay = (TextView) findViewById(R.id.lDay);
        lMonth = (TextView) findViewById(R.id.lmonth);
        hr = (TextView) findViewById(R.id.hr);
        min = (TextView) findViewById(R.id.lmin);
        ampm = (TextView) findViewById(R.id.ampm);

        setDate(Calendar.getInstance());
        setTimeCal(Calendar.getInstance());

        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());
            }
        });
    }

    public void setDate(Calendar cal ) {
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        gDayOfMonth.setText("星期" + rDayOfMonth(dayOfWeek));
        gDay.setText(String.format("%02d",cal.get(Calendar.DATE)));
        if((cal.get(Calendar.MONTH)+1)==0)
            gMonth.setText(String.format("%02d",12));
        else
            gMonth.setText(String.format("%02d",cal.get(Calendar.MONTH)+1));

        gYear.setText(String.format("%04d",cal.get(Calendar.YEAR)));
        setTimeCal(cal);

    }

    private void setTimeCal(Calendar cal) {
        hr.setText(String.format("%02d",cal.get(Calendar.HOUR)));
        min.setText(String.format("%02d",cal.get(Calendar.MINUTE)));
        if (cal.get(Calendar.AM_PM) == Calendar.AM)
            ampm.setText("AM");
        else
            ampm.setText("PM");
    }

    public String rDayOfMonth(int dayOfWeek) {
        if (Calendar.MONDAY == dayOfWeek) {
            return "一";
        } else if (Calendar.TUESDAY == dayOfWeek) {
            return "二";
        } else if (Calendar.WEDNESDAY == dayOfWeek) {
            return "三";
        } else if (Calendar.THURSDAY == dayOfWeek) {
            return "四";
        } else if (Calendar.FRIDAY == dayOfWeek) {
            return "五";
        } else if (Calendar.SATURDAY == dayOfWeek) {
            return "六";
        } else if (Calendar.SUNDAY == dayOfWeek) {
            return "天";
        } else {
            return "X";
        }
    }

    @Override
    public void onDatePick(LunarView view, MonthDay monthDay) {
        String lunarMonth = monthDay.getLunar().getLunarMonth();
        String lunarDay = monthDay.getLunar().getLunarDay();
        lDay.setText(lunarDay);
        lMonth.setText(lunarMonth);
        setDate(monthDay.getCalendar());
        setTimeCal(Calendar.getInstance());
    }
}
