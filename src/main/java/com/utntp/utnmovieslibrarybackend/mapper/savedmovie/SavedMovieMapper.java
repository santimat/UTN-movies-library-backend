package com.utntp.utnmovieslibrarybackend.mapper.savedmovie;

import com.utntp.utnmovieslibrarybackend.model.movie.Movie;
import com.utntp.utnmovieslibrarybackend.model.savedMovie.SavedMovie;
import com.utntp.utnmovieslibrarybackend.model.user.User;

public class SavedMovieMapper {
    public SavedMovieMapper(){}

    public SavedMovie toEntity(Movie movie, User user){
        SavedMovie savedMovie = new SavedMovie();
        savedMovie.setMovie(movie);
        savedMovie.setUser(user);
        return savedMovie;
    }
}
