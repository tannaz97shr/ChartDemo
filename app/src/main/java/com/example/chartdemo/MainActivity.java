package com.example.chartdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LineChart lineChart=(LineChart)findViewById(R.id.lineChart);

        final ChartData[] chartData=new ChartData[]{

            new ChartData (0,50,"farvardin"),
            new ChartData (1,25,"ordibehesht"),
            new ChartData (2,50,"khordad"),
            new ChartData (3,50,"tir")
        };

        List<Entry> entries=new ArrayList<>();
        for(ChartData chartDataItem:chartData){
            Entry entry= new Entry(chartDataItem.x,chartDataItem.y);
            entries.add(entry);
        }
        LineDataSet lineDataSet=new LineDataSet(entries,"Price");
        lineDataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float v, Entry entry, int i, ViewPortHandler viewPortHandler) {
                for(ChartData chartDataItem:chartData){
                    if(chartDataItem.x==entry.getX()){
                        return chartDataItem.title;
                    }
                }
                return"";
            }
        });
        LineData lineData=new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineChart.invalidate();
    }
}
