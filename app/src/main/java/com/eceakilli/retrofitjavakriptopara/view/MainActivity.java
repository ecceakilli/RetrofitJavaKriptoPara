package com.eceakilli.retrofitjavakriptopara.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.eceakilli.retrofitjavakriptopara.R;
import com.eceakilli.retrofitjavakriptopara.adapter.RecyclerViewAdapter;
import com.eceakilli.retrofitjavakriptopara.databinding.ActivityMainBinding;
import com.eceakilli.retrofitjavakriptopara.model.CryptoModel;
import com.eceakilli.retrofitjavakriptopara.service.CryptoAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    ArrayList<CryptoModel> cryptoModels;
    private Call<List<CryptoModel>> call;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;


    CompositeDisposable compositeDisposable; //RX java için kullanırsın call methodunın hafizadan temizlenmesini sürekli olarak tutmamasını saglar
                                             //Rx java ile işlem yapacaksan call değil 'observable' kullanırsın


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        cryptoModels = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);

        //https://api.nomics.com/v1/prices?key=e847fcd9285ccce1f0e3580ead6d9e23d6ea4d95     ------veri linki

        loadData();

    }

    private  void loadData(){


     //  call = AppClassApplication.getInstance().getCryptoAPI().getData();


       compositeDisposable = new CompositeDisposable();
       compositeDisposable.add(AppClassApplication.getInstance().getCryptoAPI().getData()
               .subscribeOn(Schedulers.io()) //hangi threadde kayıt olma işlemi yapılacagı
               .observeOn(AndroidSchedulers.mainThread())    //hangid threadde görüntülenecek
               .subscribe(this::handleResponce)); // nerde ele alınacak


       /* call.enqueue(new Callback<List<CryptoModel>>() {
            @Override
            public void onResponse(Call<List<CryptoModel>> call, Response<List<CryptoModel>> response) {
                if (response.isSuccessful()){
                    List<CryptoModel> responceList = response.body();
                    cryptoModels = new ArrayList<>(responceList);

                    //RecyclerView
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerViewAdapter = new RecyclerViewAdapter(cryptoModels);
                    recyclerView.setAdapter(recyclerViewAdapter);

                   for (CryptoModel cryptoModel : cryptoModels) {
                        System.out.println(cryptoModel.currency);


                }

            }

            @Override
            public void onFailure(Call<List<CryptoModel>> call, Throwable t) {
                t.printStackTrace();
                System.out.println("BASARISIZ");

            }
        });*/
    }

    private void handleResponce(List<CryptoModel> cryptoModelList){

        cryptoModels = new ArrayList<>(cryptoModelList);

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerViewAdapter = new RecyclerViewAdapter(cryptoModels);
        recyclerView.setAdapter(recyclerViewAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        compositeDisposable.clear(); //tüm api kodlarını temizledik
    }
}