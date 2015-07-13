package com.sample.model.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Configurable;

import java.math.BigDecimal;


/**
 * The persistent class for the track database table.
 * 
 */
@Configurable
@Entity
@Table(name="track")
@NamedQuery(name="TrackEntity.findAll", query="SELECT t FROM TrackEntity t")
public class TrackEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int trackId;

	private int albumId;

	private int bytes;

	private String composer;

	private int milliseconds;

	private String name;

	private BigDecimal unitPrice;

	//bi-directional many-to-one association to GenreEntity
	@ManyToOne
	@JoinColumn(name="GenreId")
	private GenreEntity genre;

	//bi-directional many-to-one association to MediaTypeEntity
	@ManyToOne
	@JoinColumn(name="MediaTypeId")
	private MediaTypeEntity mediaType;

	public TrackEntity() {
	}

	public int getTrackId() {
		return this.trackId;
	}

	public void setTrackId(int trackId) {
		this.trackId = trackId;
	}

	public int getAlbumId() {
		return this.albumId;
	}

	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}

	public int getBytes() {
		return this.bytes;
	}

	public void setBytes(int bytes) {
		this.bytes = bytes;
	}

	public String getComposer() {
		return this.composer;
	}

	public void setComposer(String composer) {
		this.composer = composer;
	}

	public int getMilliseconds() {
		return this.milliseconds;
	}

	public void setMilliseconds(int milliseconds) {
		this.milliseconds = milliseconds;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public GenreEntity getGenre() {
		return this.genre;
	}

	public void setGenre(GenreEntity genre) {
		this.genre = genre;
	}

	public MediaTypeEntity getMediaType() {
		return this.mediaType;
	}

	public void setMediaType(MediaTypeEntity mediaType) {
		this.mediaType = mediaType;
	}

}