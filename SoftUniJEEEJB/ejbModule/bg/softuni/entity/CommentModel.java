package bg.softuni.entity;

import bg.softuni.entity.base.BaseDomainObject;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "COMMENTS")
public class CommentModel extends BaseDomainObject {
	private static final long serialVersionUID = 1L;
	private String title;
	private String content;
	private Date date;
	private UserModel author;
	private PostModel post;

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

	@JoinColumn(name = "post_id", referencedColumnName = "id")
	@ManyToOne
	public PostModel getPost() {
		return post;
	}

	public void setPost(PostModel post) {
		this.post = post;
	}

}
