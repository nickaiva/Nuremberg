package nickaiva.sightseeing.nuremberg;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static View.OnClickListener mainOnClickListener;
    private RecyclerView recyclerView;
    public static int currentItem;

    private class MainOnClickListener implements View.OnClickListener {
        private final Context context;
        private MainOnClickListener(Context c) {
            this.context = c;
        }

        @Override
        public void onClick(View v) {
            currentItem = recyclerView.getChildLayoutPosition(v);//  getChildPosition deprecated
            startActivity(new Intent(getApplicationContext(), nickaiva.sightseeing.nuremberg.DetailActivity.class));
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainOnClickListener = new MainOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);
        recyclerView.setHasFixedSize(true);//for better performance
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<nickaiva.sightseeing.nuremberg.MainDataDef> mainData = new ArrayList<nickaiva.sightseeing.nuremberg.MainDataDef>();
        for (int i = 0; i < nickaiva.sightseeing.nuremberg.MainData.nameArray.length; i++) {
            mainData.add(new nickaiva.sightseeing.nuremberg.MainDataDef(
                    nickaiva.sightseeing.nuremberg.MainData.imageArray[i],
                    getString(nickaiva.sightseeing.nuremberg.MainData.nameArray[i]),
                    getString(nickaiva.sightseeing.nuremberg.MainData.infoArray[i])
            ));

        }
        RecyclerView.Adapter adapter = new nickaiva.sightseeing.nuremberg.MainAdapter(mainData);
        recyclerView.setAdapter(adapter);

        //display popup for back button
        Snackbar.make(findViewById(R.id.myCoordinatorLayout), R.string.popup_close,
                Snackbar.LENGTH_SHORT)
                .show();
    }
}
