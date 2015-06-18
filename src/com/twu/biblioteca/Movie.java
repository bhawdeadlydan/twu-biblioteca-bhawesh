package com.twu.biblioteca;

public class Movie {
    private String name;
    private int year;
    private String director;
    private int rating;

    public Movie(String name, int year, String director, int rating) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = rating;
    }

    public String toString() {
        return "\n"+name + "\t" + director + "\t" + year + "\t" + "rating:" + rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Movie movie = (Movie) o;

        if (year != movie.year)
            return false;
        if (rating != movie.rating)
            return false;
        if (!name.equals(movie.name))
            return false;
        return director.equals(movie.director);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + year;
        result = 31 * result + director.hashCode();
        result = 31 * result + rating;
        return result;
    }

    public boolean isMovieSame(String movieName) {
        return this.name.equals(movieName);
    }
}
