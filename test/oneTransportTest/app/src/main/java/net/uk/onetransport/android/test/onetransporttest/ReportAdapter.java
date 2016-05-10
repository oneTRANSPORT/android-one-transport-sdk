package net.uk.onetransport.android.test.onetransporttest;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ReportAdapter extends BaseAdapter {

    public static final int TEXT = 0;
    public static final int COLOUR = 1;

    // Chunks of text, one per item.  Arrays always have two elements:
    // 0 = String, text
    // 1 = Integer, colour
    private ArrayList<Object[]> chunks = new ArrayList<>();
    private Context context;
    private DisplayMetrics displayMetrics;
    private int numTests = 0;
    private int numCompleted = 0;

    public ReportAdapter(Context context, Resources resources) {
        this.context = context;
        displayMetrics = resources.getDisplayMetrics();
    }

    @Override
    public int getCount() {
        return chunks.size();
    }

    @Override
    public Object getItem(int position) {
        return chunks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = new TextView(context);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            convertView.setLayoutParams(params);
            convertView.setPadding(16, 8, 16, 8);
        }
        ((TextView) convertView).setTextColor((Integer) chunks.get(position)[COLOUR]);
        ((TextView) convertView).setText((String) chunks.get(position)[TEXT]);
        return convertView;
    }

    public void addChunk(String text, int colour) {
        Object[] chunk = new Object[2];
        chunk[TEXT] = text;
        chunk[COLOUR] = colour;
        chunks.add(chunk);
        showCompleted();
        notifyDataSetInvalidated();
    }

    public void setNumTests(int numTests) {
        this.numTests = numTests;
    }

    public void incNumCompleted() {
        numCompleted++;
    }

    private void showCompleted() {
        if (chunks.size() > 1) {
            Object[] chunk = chunks.get(1);
            chunk[TEXT] = "Starting tests... " + String.valueOf(numCompleted)
                    + "/" + String.valueOf(numTests);
        }
    }

    private float dipsToPixels(float dips) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dips, displayMetrics);
    }
}
