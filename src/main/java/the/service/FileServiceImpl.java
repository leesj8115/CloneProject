package the.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import lombok.extern.slf4j.Slf4j;
import the.domain.dto.FileDto;
import the.domain.entity.file.FileEntity;
import the.domain.entity.file.FileRepository;

@Slf4j
@Service
public class FileServiceImpl implements FileService {
    
    @Autowired
    FileRepository repository;

    @Override
    public void saveFile(FileDto dto, Model model) {
        
        FileEntity result = repository.save(dto.toEntity());

        String msg = (result != null) ? "파일 저장을 완료했습니다." : "파일 저장을 실패했습니다.";

        model.addAttribute("msg", msg);
    }

	@Override
	public void getVisual(Model model) {
		
		
		// visual 폴더 안에 있는 이미지(파일 엔티티)를 가져옴
		List<FileEntity> result = repository.findAllByDivision("visual");
		
		log.debug("result 갯수 = " + result.size());
		
		
		// model을 통해 데이터 전달, visual.html에서 뿌려줄 예정
		model.addAttribute("visual", result);
		
	}



    
}
