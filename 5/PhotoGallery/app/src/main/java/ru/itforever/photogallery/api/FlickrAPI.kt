package ru.itforever.photogallery.api

import androidx.room.Query

public interface FlickrAPI {
    @GET("services/rest/?method=flickr.photos.getRecent&api_key=Your API
            &extras=url_s&format=json&nojsoncallback=1")
    Call<PhotosResponse> getRecent();
    @GET("services/rest/?method=flickr.photos.search&api_key=d Your API
            &extras=url_s&format=json&nojsoncallback=1")
    Call<PhotosResponse> getSearchPhotos(@Query("text") String keyWord);
}