package com.nextcloud.testRedirectSso;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.nextcloud.android.sso.api.NextcloudAPI;
import com.nextcloud.android.sso.exceptions.NextcloudFilesAppAccountNotFoundException;
import com.nextcloud.android.sso.exceptions.NoCurrentAccountSelectedException;
import com.nextcloud.android.sso.helper.SingleAccountHelper;
import com.nextcloud.android.sso.model.SingleSignOnAccount;

import retrofit2.NextcloudRetrofitApiBuilder;

public class ApiProvider {
    protected static NotesAPI notesAPI;

    public ApiProvider(Context context) {
        try {
            SingleSignOnAccount ssoAccount = SingleAccountHelper.getCurrentSingleSignOnAccount(context);
            NextcloudAPI nextcloudAPI = new NextcloudAPI(context, ssoAccount, new GsonBuilder().create(), new NextcloudAPI.ApiConnectedListener() {
                @Override
                public void onConnected() {
                    // Ignore..
                }

                @Override
                public void onError(Exception ex) {
                    // Ignore...
                }
            });
            notesAPI = new NextcloudRetrofitApiBuilder(nextcloudAPI, NotesAPI.API_ENDPOINT).create(NotesAPI.class);

        } catch (NextcloudFilesAppAccountNotFoundException | NoCurrentAccountSelectedException e) {
        }
    }

    public static NotesAPI getNotesAPI() {
        return notesAPI;
    }
}