package bg.softuni.entity;

import bg.softuni.entity.base.BaseDomainObject;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "POST_TAG")
public class PostTagModel extends BaseDomainObject {

	private PostModel post;

	private TagModel tag;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="post_id")
	public PostModel getPost() {
		return this.post;
	}

	public void setPost(PostModel post){
		this.post = post;
	}

	@OneToOne
	@JoinColumn(name="tag_id")
	public TagModel getTag() {
		return tag;
	}

	public void setTag(TagModel tag) {
		this.tag = tag;
	}
}