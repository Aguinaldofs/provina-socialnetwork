package br.com.provina.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private LocalDateTime creationdate = LocalDateTime.now();
	private String url;
	@ManyToOne
	private User owner;
	@ManyToOne
	private Category category;
	@OneToMany(mappedBy = "item", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Comment> comments = new ArrayList<>();
	@OneToMany(mappedBy = "item", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Upvote> upvotes = new ArrayList<>();

	public Item() {
	}

	public Item(String name, String url, Category category) {
		super();
		this.name = name;
		this.url = url;
		this.category = category;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<Upvote> getUpvotes() {
		return upvotes;
	}

	public void setUpvotes(List<Upvote> upvotes) {
		this.upvotes = upvotes;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getCreationdate() {
		return creationdate;
	}

	public Long getId() {
		return id;
	}

	public void setCreationdate(LocalDateTime creationdate) {
		this.creationdate = creationdate;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
