package br.com.provina.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String text;
	private String media;
	private LocalDateTime creationdate = LocalDateTime.now();
	@ManyToOne
	private Item item;
	@ManyToOne
	private User user;

	public Comment() {

	}

	public Comment(String text, String media, Item item, User user) {
		super();
		this.text = text;
		this.item = item;
		this.user = user;
		this.setMedia(media);
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(LocalDateTime creationdate) {
		this.creationdate = creationdate;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long itemId) {
		this.id = itemId;
	}

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}
}
