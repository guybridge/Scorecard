package au.com.wsit.scorecard.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by guyb on 20/09/16.
 */
public class Hole
{
    private int mHoleNumber;
    private int mShotCount;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private Context mContext;
    public static final String TAG = Hole.class.getSimpleName();

    // Create a constructor which initialises which hole we are changing
    public Hole(Context context, int holeNumber)
    {
        // Store in the member variable
        mHoleNumber = holeNumber;

        // Setup shared prefs
        mSharedPreferences = mContext.getSharedPreferences(ScorecardConstants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        // Get the last shotcount for the given hole
        mShotCount = mSharedPreferences.getInt(ScorecardConstants.KEY_SHOT_COUNT + mHoleNumber, 0);

    }

    // Increments the hole's shot count by 1
    public void incrementShot()
    {
        // Put a new int into the shotcount key
        // Increments the mShotcount by 1
        mEditor.putInt(ScorecardConstants.KEY_SHOT_COUNT + mHoleNumber, mShotCount++);
        mEditor.apply();
    }

    public void decrementShot()
    {
        // Put a new int into the shotcount key
        // Increments the mShotcount by 1
        mEditor.putInt(ScorecardConstants.KEY_SHOT_COUNT + mHoleNumber, mShotCount--);
        mEditor.apply();
    }

    // returns the shot count
    public int getShotCount()
    {
        return mShotCount;
    }
}
