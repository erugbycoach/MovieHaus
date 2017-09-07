package com.example.android.moviemadness;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private MoviesAdapter moviesAdapter;

    private int selectedOption = R.id.action_popular;
    private static final String FILTER_TYPE_1 = "popular";
    private static final String FILTER_TYPE_2 = "top_rated";



    @BindView(R.id.recycler)
    RecyclerView recyclerView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (isNetworkAvailable()) {
            GridLayoutManager layoutManager = new GridLayoutManager(this, getSpan());
            ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(this, R.dimen.item_offset);
            recyclerView.addItemDecoration(itemDecoration);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(true);
            if (savedInstanceState == null) {
                setMovieAdapterPopular();
            } else {
                loadAdapterPerOptionSelected(
                        savedInstanceState.getInt("optionSelected", R.id.action_popular));
            }
        } else {
            Toast.makeText(this, getString(R.string.network_unavailable), Toast.LENGTH_LONG).show();
        }




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("optionsSelected", selectedOption);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        loadAdapterPerOptionSelected(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    private void loadAdapterPerOptionSelected(int selectedOption) {
        this.selectedOption = selectedOption;
        if (selectedOption == R.id.action_popular) {
            setMovieAdapterPopular();
        }

        if (selectedOption == R.id.action_top_rated) {
            setMovieAdapterTopRated();
         }
    }

    private int getSpan () {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            return 4;
        }
        return 2;
    }


    private void setMovieAdapterPopular () {
        moviesAdapter = new MoviesAdapter();
        FetchMoviesTask moviesTask = new FetchMoviesTask(moviesAdapter);
        recyclerView.setAdapter(moviesAdapter);
        moviesTask.execute(FILTER_TYPE_1);
    }

   private void setMovieAdapterTopRated () {
       moviesAdapter = new MoviesAdapter();
       FetchMoviesTask moviesTask = new FetchMoviesTask(moviesAdapter);
       recyclerView.setAdapter(moviesAdapter);
       moviesTask.execute(FILTER_TYPE_2);
   }

    public class ItemOffsetDecoration extends RecyclerView.ItemDecoration {

        private int mItemOffset;

        public ItemOffsetDecoration(int itemOffset) {
            mItemOffset = itemOffset;
        }

        public ItemOffsetDecoration(@NonNull Context context, @DimenRes int itemOffsetId) {
            this(context.getResources().getDimensionPixelSize(itemOffsetId));
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(mItemOffset, mItemOffset, mItemOffset, mItemOffset);
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }
}
