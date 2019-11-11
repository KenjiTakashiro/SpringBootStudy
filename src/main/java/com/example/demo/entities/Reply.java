package com.example.demo.entities;

import javax.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "reply")
public class Reply {
	@Id
	@GeneratedValue
	@Column
	private Long id;

	@Column
	private Long rate_id;

	@Column
	private String reply;

	@Column
	private String comment_user;

}
