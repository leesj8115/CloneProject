package the.service;

import org.springframework.ui.Model;

import the.domain.dto.FileDto;

public interface FileService {

    void saveFile(FileDto dto, Model model);

	void getVisual(Model model);

}
