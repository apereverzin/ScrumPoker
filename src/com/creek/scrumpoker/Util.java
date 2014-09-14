package com.creek.scrumpoker;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * 
 * @author Andrey Pereverzin
 *
 */
public class Util {
    static public TableRow createRow(Activity activity) {
        TableRow row = new TableRow(activity);
        row.setClickable(true);
        return row;
    }
    
    static public TextView createCell(final Activity activity, final String text) {
        TextView textView = new TextView(activity);
        textView.setText(text);
        textView.setBackgroundColor(Color.WHITE);
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.CENTER);
        textView.setClickable(true);
        return textView;
    }
    
    static public TextView createRotatedCell(Activity activity, String text) {
        RotatedTextView textView = new RotatedTextView(activity);
        textView.setText(text);
        textView.setBackgroundColor(Color.WHITE);
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.CENTER);
        textView.setTypeface(null, Typeface.BOLD);
        return textView;
   }
    
   static class RotatedTextView extends TextView {
        public RotatedTextView(Context context) {
            super(context);
        }
       
        @Override
        protected void onDraw(Canvas canvas) {
            canvas.save();
            canvas.rotate(90);
            super.onDraw(canvas);
            canvas.restore();
        }   
    }
}
