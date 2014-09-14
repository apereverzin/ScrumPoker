package com.creek.scrumpoker;

import com.creek.scrumpoker.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
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
public class VoteActivity extends Activity {
    static FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.FILL_PARENT);
    static TableLayout.LayoutParams rowLp = new TableLayout.LayoutParams(
                    ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.FILL_PARENT,
                    1.0f);
    static TableRow.LayoutParams cellLpVert = new TableRow.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    1.0f);
    static TableRow.LayoutParams cellLpHor = new TableRow.LayoutParams(
                    ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.FILL_PARENT,
                    1.0f);
    static {
        cellLpVert.gravity = Gravity.CENTER_VERTICAL;
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        int orientation = display.getOrientation();

        Bundle extras = getIntent().getExtras();

        final String vote = (String)extras.get(ScrumPokerActivity.VOTE);

        setContentView(R.layout.table);
        TableLayout table = (TableLayout)findViewById(R.id.tableLayout1);
        table.setLayoutParams(ScrumPokerActivity.lp);
        TableRow row1 = Util.createRow(VoteActivity.this);
        
        TextView tv = createCell(vote);
        if(orientation == LinearLayout.HORIZONTAL) {
            row1.addView(tv, cellLpHor);
        } else {
            row1.addView(tv, cellLpVert);
        }
        table.addView(row1, rowLp);
        
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
    
    private TextView createCell(final String text) {
        TextView textView = Util.createCell(VoteActivity.this, text);
        textView.setOnClickListener(new  View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        if(text.length() == 1) {
            textView.setTextSize(256);
        } else if(text.length() == 2) {
            textView.setTextSize(216);
        } else {
            textView.setTextSize(144);
        }
        return textView;
    }
}
