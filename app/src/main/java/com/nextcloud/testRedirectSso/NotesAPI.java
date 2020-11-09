package com.nextcloud.testRedirectSso;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NotesAPI {
    String API_ENDPOINT = "/index.php/apps/notestutorial/api/0.1";

    @GET("/notes")
    Call<List<Note>> getNotes();
}
