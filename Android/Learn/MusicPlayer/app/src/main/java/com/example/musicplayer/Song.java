package com.example.musicplayer;

public class Song {

    private String songName;
    private int songFile;

    public Song(String songName, int songFile) {
        this.songName = songName;
        this.songFile = songFile;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public int getSongFile() {
        return songFile;
    }

    public void setSongFile(int songFile) {
        this.songFile = songFile;
    }
}
