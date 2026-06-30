package com.utntp.utnmovieslibrarybackend.dto.response.savedMovie;

public class SavedMovieCountResponse {
    private Long totalSavedMovies;

    public SavedMovieCountResponse(Long totalSavedMovies) {
        this.totalSavedMovies = totalSavedMovies;
    }

    public Long getTotalSavedMovies() {
        return totalSavedMovies;
    }

    public void setTotalSavedMovies(Long totalSavedMovies) {
        this.totalSavedMovies = totalSavedMovies;
    }
}
