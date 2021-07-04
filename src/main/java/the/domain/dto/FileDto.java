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
    private String fileOriName;
    private String filePath;
    private String division;

    public FileEntity toEntity() {
        FileEntity entity = FileEntity.builder()
                            .fileName(fileName)
                            .fileOriName(fileOriName)
                            .filePath(filePath)
                            .division(division)
                            .build();

        return entity;
    }
}
