package board.board.dto;

import java.time.LocalDateTime;

import lombok.Data;

// 모든 필드에 대해 getter, setter 를 생성하고, toString, hashCode, equals 메소드를 생성한다.
@Data
public class BoardDto {
	private int id;
	private String title;
	private String contents;
	private int hit;
	private String creatorId;
	private LocalDateTime createdAt;
	private String updatorId;
	private LocalDateTime updatedAt;
}
