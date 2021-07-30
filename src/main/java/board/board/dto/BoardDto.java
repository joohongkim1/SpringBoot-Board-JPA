package board.board.dto;

import java.time.LocalDateTime;

import lombok.Data;

// ��� �ʵ忡 ���� getter, setter �� �����ϰ�, toString, hashCode, equals �޼ҵ带 �����Ѵ�.
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
