package com.example.uts_pam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.ContactsContract;

import com.example.uts_pam.Adapter.AdapterListPhoto;
import com.example.uts_pam.Model.Photos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListPhotoActivity extends AppCompatActivity {

    private final List<Photos> viewItems = new ArrayList<>();

    @BindView(R.id.lst_photos)
    RecyclerView lstphotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_photo);
        ButterKnife.bind(this);

        lstphotos.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        lstphotos.setHasFixedSize(true);
        lstphotos.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

        AdapterListPhoto adapterListPhoto = new AdapterListPhoto(this, viewItems);
        lstphotos.setAdapter(adapterListPhoto);

        addItemFromJSON();
    }

    private void addItemFromJSON() {
        try {

            String jsonDataString = readJSONDataFromFile();
            JSONArray jsonArray = new JSONArray(jsonDataString);

            for(int i = 0; i < jsonArray.length(); i++){

                JSONObject itemObj = jsonArray.getJSONObject(i);

                Photos photos = new Photos();
                photos.setAlbumId(itemObj.getInt("albumId"));
                photos.setId(itemObj.getInt("id"));
                photos.setTitle(itemObj.getString("title"));
                photos.setUrl(itemObj.getString("url"));
                photos.setThumbnailUrl(itemObj.getString("thumbnailUrl"));

                viewItems.add(photos);
            }


        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    private String readJSONDataFromFile() throws IOException {

        InputStream inputStream = null;
        StringBuilder stringBuilder = new StringBuilder();

        try {

            String jsonString;
            inputStream = getResources().openRawResource(R.raw.list_photo);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8)
            );

            while ((jsonString = bufferedReader.readLine()) != null) {
                stringBuilder.append(jsonString);
            }
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(inputStream != null) {
                inputStream.close();
            }
        }

        return new String(stringBuilder);
    }
}
