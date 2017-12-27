package com.android.mytdvbapp.mytdvbapplication.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.android.mytdvbapp.mytdvbapplication.R;
import com.android.mytdvbapp.mytdvbapplication.adapters.FavoriteAdapter;
import com.android.mytdvbapp.mytdvbapplication.adapters.OnDeleteFavorite;
import com.android.mytdvbapp.mytdvbapplication.base.AbstractActivity;
import com.android.mytdvbapp.mytdvbapplication.models.Session;
import com.android.mytdvbapp.mytdvbapplication.models.response.FavoritesResponse;
import com.android.mytdvbapp.mytdvbapplication.network.ServiceException;
import com.android.mytdvbapp.mytdvbapplication.network.ServiceManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;
import rx.Subscriber;

public class FavoriteActivity extends AbstractActivity implements OnDeleteFavorite {

    @BindView(R.id.rv_favorites)
    RecyclerView rv_favorites;

    private ServiceManager serviceManager;
    private List<String> favorites = new ArrayList<>();
    private FavoriteAdapter favoriteAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        //
        ButterKnife.bind(this);
        //
        serviceManager = new ServiceManager();
        initFakeDatas();
    }

    private void initFakeDatas() {
        favorites.add("1234578");
        favorites.add("1234578");
        favorites.add("1234578");
        favorites.add("1234578");
        favorites.add("1234578");
        favorites.add("1234578");
        initFavoritesRV();
    }

    /**
     * Method to initialize datas to the current activity
     */
    private void initDatas() {
        try {
            serviceManager.getFavorites(Session.get().getSessionToken().getToken(), new Subscriber<Response<FavoritesResponse>>() {
                @Override
                public void onCompleted() {
                    //todo : loadder
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(Response<FavoritesResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getData().getFavorites() != null && response.body().getData().getFavorites().size() > 0) {
                            favorites = response.body().getData().getFavorites();
                            initFavoritesRV();
                        } else {
                            // todo : handle error case
                        }
                    }
                }
            });
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to initialize the recycler view with the adapter and fill datas
     */
    private void initFavoritesRV() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        rv_favorites.setLayoutManager(mLayoutManager);
        favoriteAdapter = new FavoriteAdapter(favorites, this);
        rv_favorites.setAdapter(favoriteAdapter);
    }

    @Override
    public String getTitleBarTitle() {
        return getResources().getString(R.string.my_favorites);
    }

    @Override
    public void OnDeleteFavoriteClicked(final int position, String id) {
        try {
            serviceManager.deleteFavorite(id, Session.get().getSessionToken().getToken(), new Subscriber<Response<FavoritesResponse>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(Response<FavoritesResponse> response) {
                    if (response.isSuccessful()) {
                        // todo: show alert dialog ok
                        favoriteAdapter.remove(position);
                    } else {
                        // todo: error case
                    }
                }
            });
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}


