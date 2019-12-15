package com.csfu.cpsc41101.personapplication.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.csfu.cpsc41101.personapplication.PersonDetailsActivity;
import com.csfu.cpsc41101.personapplication.R;
import com.csfu.cpsc41101.personapplication.model.Person;
import com.csfu.cpsc41101.personapplication.model.PersonDB;

public class SummaryListAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return PersonDB.getInstance().getPersonList().size();
    }

    @Override
    public Object getItem(int i) {
        return PersonDB.getInstance().getPersonList().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row_view;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            row_view = inflater.inflate(R.layout.person_row, viewGroup, false);
        } else row_view = view;

        //
        Person p = PersonDB.getInstance().getPersonList().get(i);
        //
        ((TextView) row_view.findViewById(R.id.first_name)).setText(p.getFirstName() + " ");
        ((TextView) row_view.findViewById(R.id.last_name)).setText(p.getLastName());
        row_view.setTag(new Integer(i));

        row_view.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//
                        Toast.makeText(view.getContext(), "View Object was touched by user.", Toast.LENGTH_SHORT).show();

                        // Page Navigation
                        Intent intent = new Intent(view.getContext(), PersonDetailsActivity.class);
                        intent.putExtra("PersonIndex", ((Integer)view.getTag()).intValue());
                        view.getContext().startActivity(intent);
                    }
                }
        );

        return row_view;
    }


}
