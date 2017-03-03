package ee.goodsandservices.movieservice.service;

import ee.goodsandservices.movieservice.domain.Movie;
import ee.goodsandservices.movieservice.repository.MovieRepository;
import ee.goodsandservices.movieservice.repository.search.MovieSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Movie.
 */
@Service
@Transactional
public class MovieService {

    private final Logger log = LoggerFactory.getLogger(MovieService.class);
    
    private final MovieRepository movieRepository;

    private final MovieSearchRepository movieSearchRepository;

    public MovieService(MovieRepository movieRepository, MovieSearchRepository movieSearchRepository) {
        this.movieRepository = movieRepository;
        this.movieSearchRepository = movieSearchRepository;
    }

    /**
     * Save a movie.
     *
     * @param movie the entity to save
     * @return the persisted entity
     */
    public Movie save(Movie movie) {
        log.debug("Request to save Movie : {}", movie);
        Movie result = movieRepository.save(movie);
        movieSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the movies.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Movie> findAll(Pageable pageable) {
        log.debug("Request to get all Movies");
        Page<Movie> result = movieRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one movie by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public Movie findOne(Long id) {
        log.debug("Request to get Movie : {}", id);
        Movie movie = movieRepository.findOne(id);
        return movie;
    }

    /**
     *  Delete the  movie by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Movie : {}", id);
        movieRepository.delete(id);
        movieSearchRepository.delete(id);
    }

    /**
     * Search for the movie corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Movie> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Movies for query {}", query);
        Page<Movie> result = movieSearchRepository.search(queryStringQuery(query), pageable);
        return result;
    }
}
