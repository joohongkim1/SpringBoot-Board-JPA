package board.dto;

import lombok.Data;

@Data
public class BoardFileDto {
	private int id;
	private int boardId;
	private String originalFileName;
	private String storedFilePath;
	private long fileSize;
}
