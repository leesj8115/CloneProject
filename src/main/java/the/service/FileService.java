package the.service;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import the.domain.entity.file.FileEntity;

public interface FileService {

	void getVisual(Model model);

	void saveFile(MultipartFile files, String division, Model model);

	List<FileEntity> saveMultiFiles(List<MultipartFile> files, String division);

}
