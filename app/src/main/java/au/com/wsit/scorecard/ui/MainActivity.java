package au.com.wsit.scorecard.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import au.com.wsit.scorecard.R;
import au.com.wsit.scorecard.adapters.HoleAdapter;
import au.com.wsit.scorecard.utils.Hole;
import au.com.wsit.scorecard.utils.ScoreItem;
import au.com.wsit.scorecard.utils.ScorecardConstants;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = MainActivity.class.getSimpleName();
    private SharedPreferences mSharedPreferences;
    private RecyclerView mScorecardRecycler;
    private LinearLayoutManager mLayoutManager;
    private HoleAdapter mHoleAdapter;
    private int maxHoles = 18;
    private int holeStartCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSharedPreferences = getSharedPreferences(ScorecardConstants.SHARED_PREFERENCES, Context.MODE_PRIVATE);

        mScorecardRecycler = (RecyclerView) findViewById(R.id.scoresRecyclerView);
        mLayoutManager = new LinearLayoutManager(MainActivity.this);
        mScorecardRecycler.setLayoutManager(mLayoutManager);

        // Load up the holes
        ArrayList<ScoreItem> scoreItems = new ArrayList<>();
        for (int i = 0; i < maxHoles; i++)
        {
            Hole hole = new Hole(MainActivity.this, holeStartCount);
            ScoreItem item = new ScoreItem();

            item.setHoleNumber(holeStartCount + "");
            item.setHoleShotCount(hole.getShotCount() + "");

            scoreItems.add(item);

            holeStartCount++;
        }

        mHoleAdapter = new HoleAdapter(MainActivity.this, scoreItems);
        mScorecardRecycler.setAdapter(mHoleAdapter);


    }

    // Setup the menu options
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // On menu item selected method
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        switch (id)
        {
            case R.id.action_clear_strokes:
                // Clear strokes from shared preferences
                mSharedPreferences.edit().clear().apply();

                // Reload adapter
                mHoleAdapter.notifyDataSetChanged();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
