package songlistApp;

import java.util.Collections;
import java.util.Comparator;

public class Song {
    private String name;
    private String artist;
    private String album;
    private String year;
    
    public Song(String name, String artist, String album, String year){
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.year = year;
    }
    
    public String getName (){return name;}
    public void setName(String name){this.name = name;}

    public String getAlbum (){return album;}
    public void setAlbum(String album){this.album = album;}

    public String getArtist (){return artist;}
    public void setArtist(String artist){this.artist = artist;}

    public String getYear (){return year;}
    public void setYear(String year){this.year = year;}

    @Override
    public String toString(){
        return "Song [name=" + name + " artist=" + artist + " album=" + album + " year=" + year + "]";
    }
    
	public static Comparator<Song> songComparator = new Comparator<Song>() {
	    @Override
	    public int compare(Song song1, Song song2) {
	        String songOneName = song1.getName().toLowerCase() + song1.getArtist().toLowerCase();
	        String songTwoName = song2.getName().toLowerCase() + song2.getArtist().toLowerCase();
	        return songOneName.compareTo(songTwoName);
	    }
	};
}