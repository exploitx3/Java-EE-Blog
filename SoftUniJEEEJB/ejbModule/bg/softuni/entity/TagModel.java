package bg.softuni.entity;

import bg.softuni.entity.base.BaseDomainObject;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "TAGS")
public class TagModel extends BaseDomainObject {

	private String name;
	private Set<PostModel> posts = new HashSet<PostModel>();

	@Column(name = "name", length = 10)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "tags")
	public Set<PostModel> getPosts() {
		return posts;
	}

	public void setPosts(Set<PostModel> posts) {
		this.posts = posts;
	}
}