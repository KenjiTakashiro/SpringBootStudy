package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entities.Reply;
import com.example.demo.repositories.ReplyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyService {
	private final ReplyRepository replyRepository;

	public void create(Long rate_id, String reply, String comment_user) {
		Reply rep = new Reply();
		rep.setRate_id(rate_id);
		rep.setReply(reply);
		rep.setComment_user(comment_user);
		replyRepository.save(rep);
	}
}