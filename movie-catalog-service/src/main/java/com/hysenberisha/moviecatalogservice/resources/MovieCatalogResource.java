package com.hysenberisha.moviecatalogservice.resources;

import com.hysenberisha.moviecatalogservice.models.CatalogItem;
import com.hysenberisha.moviecatalogservice.models.Movie;
import com.hysenberisha.moviecatalogservice.models.Rating;
import com.hysenberisha.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){

        Movie movie = restTemplate.getForObject("http://localhost:8082/movies/foo", Movie.class);
//            WebClient.Builder builder = WebClient.builder();

//        get all rated movie IDs

        UserRating ratings = restTemplate.getForObject("http://localhost:8083/ratingsdata/users/" + userId, UserRating.class);

//        List<Rating> ratings = Arrays.asList(
//                new Rating("1234", 4),
//                new Rating("5678", 3)
//        );

        return ratings.getUserRating().stream()
                // For each movie ID, call movie info service and get details
                .map(rating -> {

                    /*
                    Movie movie = webClientBuilder.build()
                            .get()
                            .uri("http://localhost:8082/movies/" + rating.getMovieId())
                            .retrieve()
                            .bodyToMono(Movie.class)
                            .block();
                     */

                //Put them all together
                    return new CatalogItem(movie.getName(), "Test", rating.getRating());
                })
                .collect(Collectors.toList());








    }
}
