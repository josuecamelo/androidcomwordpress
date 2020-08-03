package com.josuecamelo.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.CompletableFuture;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<Model> list;
    private RecyclerViewAdapter adapter;

    //private String baseURL = "http://192.168.0.2/mobilewp/";
    private String baseURL = "https://sargentopereira.com.br/";

    //codigo josue - 01/08/2020
    /*public static List<WPPostVideos> mListPost;
    public static List<WPPost> mListPostNormal;*/

    //código original
    public static List<WPPost> mListPost;

    private String teste;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        mLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);

        list = new ArrayList<Model>();
        /// call retrofill
        getRetrofit();

        adapter = new RecyclerViewAdapter( list, MainActivity.this);

        recyclerView.setAdapter(adapter);

    }
    public void getRetrofit(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitArrayApi service = retrofit.create(RetrofitArrayApi.class);


        //codigo josue - 01/08/2020
        /*Call<List<WPPostVideos>> call = service.getPostVideos();
        Call<List<WPPost>> callPosts = service.getPostInfo();*/
        //final codigo josue


        //codigo orignal
        Call<List<WPPost>>  call = service.getPostInfo();

        // to make call to dynamic URL

        // String yourURL = yourURL.replace(BaseURL,"");
        // Call<List<WPPost>>  call = service.getPostInfo( yourURL);

        /// to get only 6 post from your blog
        // http://your-blog-url/wp-json/wp/v2/posts?per_page=2

        // to get any specific blog post, use id of post
        //  http://www.blueappsoftware.in/wp-json/wp/v2/posts/1179

        // to get only title and id of specific
        // http://www.blueappsoftware.in/android/wp-json/wp/v2/posts/1179?fields=id,title


        /*
        call.enqueue(new Callback<List<WPPostVideos>>() {
            @Override
            public void onResponse(Call<List<WPPostVideos>> call, Response<List<WPPostVideos>> response) {
                Log.e("mainactivyt", " response "+ response.body());
                mListPost = response.body();
                progressBar.setVisibility(View.GONE);


                for (int i=0; i<response.body().size();i++){

                    System.out.println(response.body().get(i).getContent().getRendered());
                    System.out.println( response.body().get(i).getTitle().getRendered() );
                    System.out.println( response.body().get(i).getAcf().getUrlDoVideo() );


                    String tempdetails =  response.body().get(i).getExcerpt().getRendered().toString();
                    tempdetails = tempdetails.replace("<p>","");
                    tempdetails = tempdetails.replace("</p>","");
                    tempdetails = tempdetails.replace("[&hellip;]","");

                    list.add( new Model( Model.IMAGE_TYPE,  response.body().get(i).getTitle().getRendered(),
                            tempdetails,
                            response.body().get(i).getLinks().getWpFeaturedmedia().get(0).getHref())  );
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<WPPostVideos>> call, Throwable t) {
                System.out.println("ERROR ->>>>>>>" + t.getMessage());
            }
        });

        //OUtros Posts
        callPosts.enqueue(new Callback<List<WPPost>>() {
            @Override
            public void onResponse(Call<List<WPPost>> call, Response<List<WPPost>> response) {
                Log.e("mainactivyt", " response "+ response.body());
                mListPostNormal = response.body();
                for (int i=0; i<response.body().size();i++){
                    System.out.println(response.body().get(i).getContent().getRendered());
                    System.out.println( response.body().get(i).getTitle().getRendered() );
                }
            }
            @Override
            public void onFailure(Call<List<WPPost>> call, Throwable t) {
                System.out.println("ERROR ->>>>>>>" + t.getMessage());
            }
        });*/





        //original

        call.enqueue(new Callback<List<WPPost>>() {
            @Override
            public void onResponse(Call<List<WPPost>> call, Response<List<WPPost>> response) {
                Log.e("mainactivyt", " response "+ response.body());
                mListPost = response.body();
                progressBar.setVisibility(View.GONE);


                System.out.println("Total Items: " + response.headers().get("x-wp-total"));
                System.out.println("Total Pages: " + response.headers().get("x-wp-totalpages"));
                //System.out.println("Link Próxima Página: " + response.headers().get("link"));

                for (int i=0; i<response.body().size();i++){
                    Log.e("main ", " title "+ response.body().get(i).getTitle().getRendered() + " "+
                            response.body().get(i).getId());

                    String tempdetails =  response.body().get(i).getExcerpt().getRendered().toString();
                    tempdetails = tempdetails.replace("<p>","");
                    tempdetails = tempdetails.replace("</p>","");
                    tempdetails = tempdetails.replace("[&hellip;]","");

                   /* list.add( new Model( Model.IMAGE_TYPE,  response.body().get(i).getTitle().getRendered(),
                            tempdetails,
                            response.body().get(i).getLinks().getWpFeaturedmedia().get(0).getHref())  );*/

                    //Pegar a Imagem em Si
                    /*System.out.println("Média Imagem " + response.body().get(i).getLinks().getWpFeaturedmedia().get(0).getHref());
                    System.out.println("");*/

                    /*list.add( new Model( Model.IMAGE_TYPE,  response.body().get(i).getTitle().getRendered(),
                            tempdetails,
                            response.body().get(i).getLinks().getWpFeaturedmedia().get(0).getHref())  );*/


                    list.add( new Model( Model.IMAGE_TYPE,  response.body().get(i).getTitle().getRendered(),
                            tempdetails, null  ));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<WPPost>> call, Throwable t) {

            }
        });
    }

    //codigo josue - 01/08/2020
    /*public static List<WPPostVideos> getList(){
        return  mListPost;
    }

    public static List<WPPost> getListNormal(){
        return  mListPostNormal;
    }*/

    //final

    public static List<WPPost> getList(){
        return  mListPost;
    }
}
