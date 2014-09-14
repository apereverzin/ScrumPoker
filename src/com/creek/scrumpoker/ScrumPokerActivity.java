package com.creek.scrumpoker;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormatSymbols;

import com.creek.scrumpoker.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * 
 * @author Andrey Pereverzin
 *
 */
public class ScrumPokerActivity extends Activity {
    static final String VOTE = "VOTE";
    
    static FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.FILL_PARENT);
    static TableLayout.LayoutParams rowLp = new TableLayout.LayoutParams(
                    ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.FILL_PARENT,
                    1.0f);
    static TableRow.LayoutParams cellLp = new TableRow.LayoutParams(
                    ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.FILL_PARENT,
                    1.0f);

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
            int orientation = display.getOrientation();
            
            setContentView(R.layout.table);
            TableLayout table = (TableLayout)findViewById(R.id.tableLayout1);
            table.setLayoutParams(lp);
            table.setStretchAllColumns(true);


            TableRow row1 = Util.createRow(ScrumPokerActivity.this);
            TableRow row2 = Util.createRow(ScrumPokerActivity.this);
            TableRow row3 = Util.createRow(ScrumPokerActivity.this);
            TableRow row4 = Util.createRow(ScrumPokerActivity.this);
            table.addView(row1, rowLp);
            table.addView(row2, rowLp);
            table.addView(row3, rowLp);
            
            TextView question = createCell("?");
            TextView half = createCell(URLDecoder.decode("%c2%bd", "UTF-8"));
            TextView one = createCell("1");
            TextView two = createCell("2");
            TextView three = createCell("3");
            TextView five = createCell("5");
            TextView eight = createCell("8");
            TextView thirteen = createCell("13");
            TextView twenty = createCell("20");
            TextView fourty = createCell("40");
            TextView hundred = createCell("100");
            TextView infinity = createCell(new DecimalFormatSymbols().getInfinity());
            TextView pi = createCell("\u03c0");
            
            if(orientation == LinearLayout.HORIZONTAL) {
                row1.addView(question, cellLp);
                row1.addView(half, cellLp);
                row1.addView(one, cellLp);
                row1.addView(two, cellLp);
                row2.addView(three, cellLp);
                row2.addView(five, cellLp);
                row2.addView(eight, cellLp);
                row2.addView(thirteen, cellLp);
                row3.addView(twenty, cellLp);
                row3.addView(fourty, cellLp);
                row3.addView(hundred, cellLp);
                row4.addView(pi, cellLp);
                row4.addView(infinity, cellLp);
                table.addView(row4, rowLp);
            } else {
                row1.addView(question, cellLp);
                row1.addView(half, cellLp);
                row1.addView(one, cellLp);
                row1.addView(two, cellLp);
                row1.addView(three, cellLp);
                row2.addView(five, cellLp);
                row2.addView(eight, cellLp);
                row2.addView(thirteen, cellLp);
                row2.addView(twenty, cellLp);
                row2.addView(fourty, cellLp);
                row3.addView(hundred, cellLp);
                row3.addView(pi, cellLp);
                row3.addView(infinity, cellLp);
            }
        } catch (UnsupportedEncodingException ex) {
            //
        }
    }
    
    private TextView createCell(final String text) {
        TextView textView = Util.createCell(ScrumPokerActivity.this, text);
        textView.setOnClickListener(new  View.OnClickListener() {
            public void onClick(View v) {
                Intent voteIntent = new Intent(ScrumPokerActivity.this, VoteActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(ScrumPokerActivity.VOTE, text);
                voteIntent.putExtras(bundle);
                startActivity(voteIntent);
            }
        });
        textView.setTextSize(48);
        return textView;
    }
}
