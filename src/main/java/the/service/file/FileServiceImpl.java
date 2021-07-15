package the.service.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import the.domain.dto.FileDto;
import the.domain.entity.file.FileEntity;
import the.domain.entity.file.FileRepository;

@Slf4j
@Service
public class FileServiceImpl implements FileService {
    
    @Autowired
    FileRepository repository;
    
    // rootPath은 작업 환경 (집 로컬, 외부 로컬, GCP Server)에 맞게 변경 필요
    // private String rootPath = "D:/workspace/cloneProj/src/main/resources/static/images";	// 집
    private String rootPath = "E:/spring/workspace/cloneProj/src/main/resources/static/images";	// 외부
    // private String rootPath = "/home/leesj8115/src/root/WEB-INF/classes/static/images";		// 리눅스 서버 (GCP)

	@Override
	public void getVisual(Model model) {
		
		// visual 폴더 안에 있는 이미지(파일 엔티티)를 가져옴
		List<FileEntity> result = repository.findAllByDivision("visual");
		
		//log.debug("result 갯수 = " + result.size());
		
		// model을 통해 데이터 전달, visual.html에서 뿌려줄 예정
		model.addAttribute("visual", result);
	}

	@Override
	public void saveFile(MultipartFile files, String division, Model model) {

		// rootPath은 controller 공용 변수로 설정해둠
        
        String basePath = rootPath + "/" + division;

        // divison에 해당하는 폴더 없을 경우 폴더 생성
        // 참고 : https://coding-factory.tistory.com/283
        File folder = new File(basePath);

        if (!folder.exists()) {
            folder.mkdir();
            log.debug(basePath + " : 폴더 생성");
        }

        String filePath = basePath + "/" + files.getOriginalFilename();

        File dest = new File(filePath);

        try {
            files.transferTo(dest);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        
        /*
         * 위에서 실제 파일은 전송한 후
         * DB에는 root 아래 / 부터 저장되도록 수정함
         * 지금은 무조건 images 폴더 아래로 가게 되어있으니 해당 에 맞게 filePath 수정
         * */
        
        FileDto dto = new FileDto();
        dto.setFileName(files.getOriginalFilename());
        dto.setFilePath("/images" + "/" + division + "/" + files.getOriginalFilename());
        dto.setFileSize(files.getSize());
        dto.setDivision(division);

        FileEntity result = repository.save(dto.toEntity());

        String msg = (result != null) ? "파일 저장을 완료했습니다." : "파일 저장을 실패했습니다.";

        model.addAttribute("msg", msg);
	}

	@Override
	public List<FileEntity> saveMultiFiles(List<MultipartFile> files, String division) {
		// rootPath은 controller 공용 변수로 설정해둠
        
        String basePath = rootPath + "/" + division;
        
        List<FileEntity> entityList = new ArrayList<>();
        
        for (MultipartFile file : files) {
        	// divison에 해당하는 폴더 없을 경우 폴더 생성
            // 참고 : https://coding-factory.tistory.com/283
            File folder = new File(basePath);

            if (!folder.exists()) {
                folder.mkdir();
                log.debug(basePath + " : 폴더 생성");
            }

            String filePath = basePath + "/" + file.getOriginalFilename();

            File dest = new File(filePath);

            try {
                file.transferTo(dest);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
            
            /*
             * 위에서 실제 파일은 전송한 후
             * DB에는 root 아래 / 부터 저장되도록 수정함
             * 지금은 무조건 images 폴더 아래로 가게 되어있으니 해당 에 맞게 filePath 수정
             * */
            
            FileDto dto = new FileDto();
            dto.setFileName(file.getOriginalFilename());
            dto.setFileSize(file.getSize());
            dto.setFilePath("/images" + "/" + division + "/" + file.getOriginalFilename());
            dto.setDivision(division);
            
            FileEntity resultEntity = repository.save(dto.toEntity());
            
            if (resultEntity != null) {
                log.debug(dto.getFileName() + " 파일 업로드 완료");
                entityList.add(resultEntity);
            }
        }
        
        return entityList;
	}



    
}
