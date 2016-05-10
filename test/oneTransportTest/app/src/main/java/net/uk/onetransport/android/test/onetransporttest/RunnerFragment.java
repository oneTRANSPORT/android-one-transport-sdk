package net.uk.onetransport.android.test.onetransporttest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class RunnerFragment extends Fragment{


    private ReportAdapter reportAdapter;

    public RunnerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        reportAdapter = new ReportAdapter(getContext(), getResources());
        new RunnerTask(reportAdapter).execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_runner, container, false);
        ListView listView = (ListView) view.findViewById(R.id.list);
        listView.setAdapter(reportAdapter);
        return view;
    }

}
