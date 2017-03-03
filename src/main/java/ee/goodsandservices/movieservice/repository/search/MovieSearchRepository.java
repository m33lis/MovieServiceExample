package ee.goodsandservices.movieservice.repository.search;

import ee.goodsandservices.movieservice.domain.Movie;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Movie entity.
 */
public interface MovieSearchRepository extends ElasticsearchRepository<Movie, Long> {
}
