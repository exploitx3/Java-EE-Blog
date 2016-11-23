package bg.softuni.entity;

import java.util.*;
import java.util.List;

import javax.persistence.*;

import bg.softuni.entity.base.BaseDomainObject;

@Entity
@Table(name = "POSTS")
public class PostModel extends BaseDomainObject {
	private static final long serialVersionUID = 1L;
	private String title;
	private String content;
	private Date date;
	private UserModel author;
	private Long visits = 0L;
	private Set<TagModel> tags = new HashSet<TagModel>();

	@Column(name = "title", length = 100, nullable = false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content", length = 400, nullable = false)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "date")
	@Temporal(TemporalType.DATE)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@JoinColumn(name = "author_id", referencedColumnName = "id")
	@ManyToOne
	public UserModel getAuthor() {
		return author;
	}

	public void setAuthor(UserModel author) {
		this.author = author;
	}

	@Column(name = "visits")
	public Long getVisits() {
		return visits;
	}

	public void setVisits(Long visits) {
		this.visits = visits;
	}

	@ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(name = "POST_TAG", schema = "blog", joinColumns = {
		@JoinColumn(name = "post_id", nullable = false, updatable = false)},
			inverseJoinColumns = { @JoinColumn(name = "tag_id",
			nullable = false, updatable = false) })
	public Set<TagModel> getTags() {
		return this.tags;
	}

	public void setTags(Set<TagModel> tags) {
	    this.tags = tags;
    }

	public void incrementVisits(){
		this.visits++;
	}
}
