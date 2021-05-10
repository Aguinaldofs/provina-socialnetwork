package br.com.provina.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.provina.model.Comment;
import br.com.provina.model.Item;
import br.com.provina.model.User;

public class CommentForm {

	@NotNull
	@NotEmpty
	private String text;

	public String getText() {
		return text;
	}

	public Comment convert(User user, Item item, String media) {
		Comment comment = new Comment(text, media, item, user);
		return comment;
	}

}
