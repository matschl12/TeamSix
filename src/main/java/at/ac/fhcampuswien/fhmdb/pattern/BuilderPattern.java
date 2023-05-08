package at.ac.fhcampuswien.fhmdb.pattern;


public class BuilderPattern {
    public static class MovieAPIRequestBuilder {
        private final String url;
        private String query;
        private String genre;
        private String releaseYear;
        private String rating;

        public MovieAPIRequestBuilder(String url) {
            this.url = url;
        }

        public MovieAPIRequestBuilder query (String query) {
            this.query = query;
            return this;
        }
        public MovieAPIRequestBuilder genre (String genre) {
            this.genre = genre;
            return this;
        }
        public MovieAPIRequestBuilder releaseYear (String releaseYear) {
            this.releaseYear = releaseYear;
            return this;
        }
        public MovieAPIRequestBuilder rating (String rating) {
            this.rating = rating;
            return this;
        }
        public String build() {
            MovieAPIRequestBuilder movieAPI = new MovieAPIRequestBuilder(url);
            movieAPI.query = this.query;
            movieAPI.genre = this.genre;
            movieAPI.releaseYear = this.releaseYear;
            movieAPI.rating = this.rating;

            return url + "/movies?query=" +query+ "&genre=" +genre+ "&releaseYear=" +releaseYear+ "&ratingFrom=" +rating;
    }
}
}
