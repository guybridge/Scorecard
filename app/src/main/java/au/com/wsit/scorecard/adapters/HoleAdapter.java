package au.com.wsit.scorecard.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import au.com.wsit.scorecard.R;
import au.com.wsit.scorecard.utils.Hole;
import au.com.wsit.scorecard.utils.ScoreItem;

/**
 * Created by guyb on 20/09/16.
 */
public class HoleAdapter extends RecyclerView.Adapter<HoleAdapter.HoleViewHolder>
{
    private Context mContext;
    private ArrayList<ScoreItem> mItems;
    public static final String TAG = HoleAdapter.class.getSimpleName();

    public HoleAdapter(Context context, ArrayList<ScoreItem> items)
    {
        mContext = context;
        mItems = items;
    }

    @Override
    public HoleViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.score_item, parent, false);
        return new HoleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HoleViewHolder holder, int position)
    {
        holder.bindViewHolder(mItems.get(position));
    }

    @Override
    public int getItemCount()
    {
        return mItems.size();
    }

    public class HoleViewHolder extends RecyclerView.ViewHolder
    {

        private TextView mHoleNumber;
        private TextView mShotCount;
        private Button mDecrement;
        private Button mIncrease;
        private Hole mHole;

        public HoleViewHolder(View itemView)
        {
            super(itemView);
            mHoleNumber = (TextView) itemView.findViewById(R.id.holeNumberTextView);
            mShotCount = (TextView) itemView.findViewById(R.id.shotCountTextView);
            mDecrement = (Button) itemView.findViewById(R.id.decreaseCountButton);
            mIncrease = (Button) itemView.findViewById(R.id.increaseCountButton);
        }

        public void bindViewHolder(ScoreItem item)
        {
            mHole = new Hole(mContext, Integer.valueOf(item.getHoleNumber()));

            mHoleNumber.setText("Hole " + item.getHoleNumber()+ ":");
            mShotCount.setText(mHole.getShotCount() + "");


            mDecrement.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    mHole.decrementShot();
                    mShotCount.setText(mHole.getShotCount() + "");

                }
            });

            mIncrease.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    mHole.incrementShot();
                    mShotCount.setText(mHole.getShotCount() + "");
                }
            });


        }
    }
}
