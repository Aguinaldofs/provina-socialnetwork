package br.com.provina.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Upvote {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	private User user;

	@ManyToOne
	private Item item;

	@Enumerated(EnumType.STRING)
	private VotingStatus status = VotingStatus.STANDBY;

	public Upvote() {
	}

	public Upvote(User user, Item item, VotingStatus status) {
		this.user = user;
		this.item = item;
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public VotingStatus getStatus() {
		return status;
	}

	public void setStatus(VotingStatus status) {
		this.status = status;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}
