package the.domain.entity.file;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class FileEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long fno;

    @Column (nullable = false)
    private String fileName;        // db에 들어가는 파일 이름

    @Column (nullable = false)
    private String fileOriName;     // 실제 파일 이름

    @Column (nullable = false)
    private String filePath;         // 파일 경로

    @Column (nullable = false)
    private String division;        // 분류 (목적)

}

