package the.service;

import java.io.File;
import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import the.domain.dto.FileDto;
import the.domain.entity.file.FileEntity;
import the.domain.entity.file.FileRepository;

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


    
}
