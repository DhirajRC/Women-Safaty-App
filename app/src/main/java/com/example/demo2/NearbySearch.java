package com.example.demo2;

import com.google.android.gms.common.api.ApiException;
import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.RankBy;


import java.io.IOException;

public class NearbySearch {

    public PlacesSearchResponse run(){
        PlacesSearchResponse request = new PlacesSearchResponse();
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyB01co5XCprsmuSzBmKzxQFCvJGlTDk-sE")
                .build();
        LatLng location1 = new LatLng(-33.8670522, 151.1957362);
        try {
            request = PlacesApi.nearbySearchQuery(context,location1).radius(5000)
                    .rankby(RankBy.PROMINENCE)
                    .keyword("cruise")
                    .language("en")
                    .type(PlaceType.RESTAURANT)
                    .await();
        } finally {
            return request;
        }
    }
}