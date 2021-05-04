package br.com.provina.controller.dto;

import br.com.provina.model.Upvote;
import br.com.provina.model.VotingStatus;

public class UpvoteDto {

	private long id;
	private VotingStatus status = VotingStatus.STANDBY;
	private UserDto user;

	public UpvoteDto(Upvote upvote) {
		this.id = upvote.getId();
		this.status = upvote.getStatus();
		this.user = new UserDto(upvote.getUser());
	}

	public UserDto getUser() {
		return user;
	}

	public long getId() {
		return id;
	}

	public VotingStatus getStatus() {
		return status;
	}

}
