package com.sample.model.pojos;

public class Album {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Album.AlbumId
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    private Integer albumid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Album.Title
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Album.ArtistId
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    private Integer artistid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Album.AlbumId
     *
     * @return the value of Album.AlbumId
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    public Integer getAlbumid() {
        return albumid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Album.AlbumId
     *
     * @param albumid the value for Album.AlbumId
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    public void setAlbumid(Integer albumid) {
        this.albumid = albumid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Album.Title
     *
     * @return the value of Album.Title
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Album.Title
     *
     * @param title the value for Album.Title
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Album.ArtistId
     *
     * @return the value of Album.ArtistId
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    public Integer getArtistid() {
        return artistid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Album.ArtistId
     *
     * @param artistid the value for Album.ArtistId
     *
     * @mbggenerated Thu Jun 05 12:50:06 CEST 2014
     */
    public void setArtistid(Integer artistid) {
        this.artistid = artistid;
    }
}