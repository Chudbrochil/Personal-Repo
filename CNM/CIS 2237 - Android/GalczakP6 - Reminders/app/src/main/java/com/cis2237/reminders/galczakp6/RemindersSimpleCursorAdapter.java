// Anthony Galczak - agalczak@cnm.edu
// CIS 2237 - Program 6 - Reminders

package com.cis2237.reminders.galczakp6;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Build;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by anthony on 11/29/2016.
 */
public class RemindersSimpleCursorAdapter extends SimpleCursorAdapter {



    public RemindersSimpleCursorAdapter(Context context, int layout, Cursor cursor, String[] from, int[] to,
                                            int flag)
    {
        super(context, layout, cursor, from, to, flag);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor)
    {
        super.bindView(view, context, cursor);

        ViewHolder holder = (ViewHolder)view.getTag();
        if(holder == null)
        {
            holder = new ViewHolder();
            holder.colImp = cursor.getColumnIndexOrThrow(RemindersDbAdapter.COL_IMPORTANT);
            holder.listTab = view.findViewById(R.id.row_tab);
            view.setTag(holder);
        }

        if(cursor.getInt(holder.colImp) > 0)
        {
            holder.listTab.setBackgroundColor(getColorWrapper(context, R.color.colorPrimary));
        }
        else
        {
            holder.listTab.setBackgroundColor(getColorWrapper(context, R.color.colorAccent));
        }
    }

    public static int getColorWrapper(Context context, int id)
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            return context.getColor(id);
        }
        else
        {
            return context.getResources().getColor(id);
        }

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent)
    {
        return super.newView(context, cursor, parent);
    }

    static class ViewHolder{
        // store column index
        int colImp;
        // store View
        View listTab;
    }
}
