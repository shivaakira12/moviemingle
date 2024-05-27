package com.moviemingle.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
public class Movie {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long movieId;
	private String movieTitle;
	private String movieReleaseYear;
	private String movieGenre;
	private String movieDirector;
	private String movieCast;
	private String movieSynopsis;
	private String image;
	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Movie(Long movieId, String movieTitle, String movieReleaseYear, String movieGenre, String movieDirector,
			String movieCast, String movieSynopsis, String image) {
		super();
		this.movieId = movieId;
		this.movieTitle = movieTitle;
		this.movieReleaseYear = movieReleaseYear;
		this.movieGenre = movieGenre;
		this.movieDirector = movieDirector;
		this.movieCast = movieCast;
		this.movieSynopsis = movieSynopsis;
		this.image = image;
	}
	public Long getMovieId() {
		return movieId;
	}
	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}
	public String getMovieTitle() {
		return movieTitle;
	}
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	public String getMovieReleaseYear() {
		return movieReleaseYear;
	}
	public void setMovieReleaseYear(String movieReleaseYear) {
		this.movieReleaseYear = movieReleaseYear;
	}
	public String getMovieGenre() {
		return movieGenre;
	}
	public void setMovieGenre(String movieGenre) {
		this.movieGenre = movieGenre;
	}
	public String getMovieDirector() {
		return movieDirector;
	}
	public void setMovieDirector(String movieDirector) {
		this.movieDirector = movieDirector;
	}
	public String getMovieCast() {
		return movieCast;
	}
	public void setMovieCast(String movieCast) {
		this.movieCast = movieCast;
	}
	public String getMovieSynopsis() {
		return movieSynopsis;
	}
	public void setMovieSynopsis(String movieSynopsis) {
		this.movieSynopsis = movieSynopsis;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "Movie [movieId=" + movieId + ", movieTitle=" + movieTitle + ", movieReleaseYear=" + movieReleaseYear
				+ ", movieGenre=" + movieGenre + ", movieDirector=" + movieDirector + ", movieCast=" + movieCast
				+ ", movieSynopsis=" + movieSynopsis + ", image=" + image + "]";
	}
	
}
