package com.hysenberisha.moviecatalogservice.resources;

import com.hysenberisha.moviecatalogservice.models.CatalogItem;
import com.hysenberisha.moviecatalogservice.models.Movie;
import com.hysenberisha.moviecatalogservice.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){

//        Movie movie = restTemplate.getForObject("http://localhost:8081/movies/foo", Movie.class);


//        get all rated movie IDs
        List<Rating> ratings = Arrays.asList(
                new Rating("1234", 4),
                new Rating("5678", 3)
        );

        return ratings.stream()
                .map(rating -> {
                    Movie movie =
                            restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
                    return new CatalogItem(movie.getName(), "Test", rating.getRating());
                })
                .collect(Collectors.toList());


//        For each movie ID, call movie info service and get details


//        Put them all together


    }
}
