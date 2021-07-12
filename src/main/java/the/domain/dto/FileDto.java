package the.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import the.domain.entity.file.FileEntity;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileDto {
    private String fileName;
    private String filePath;
    private String division;
    private long fileSize;

    public FileEntity toEntity() {
        FileEntity entity = FileEntity.builder()
                            .fileName(fileName)
                            .filePath(filePath)
                            .division(division)
                            .fileSize(fileSize)
                            .build();

        return entity;
    }
}
