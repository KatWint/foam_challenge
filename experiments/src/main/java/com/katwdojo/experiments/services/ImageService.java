package com.katwdojo.experiments.services;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.katwdojo.experiments.models.Image;
import com.katwdojo.experiments.repositories.ImageRepository;

@Service
public class ImageService {
	
	@Autowired 
	private ImageRepository imageRepo;
	
	public List<Image> getAllImages(){
		return imageRepo.findAll();
	}
	
	public void saveImage(MultipartFile file) {
		Image i = new Image();
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains("..")) {
			System.out.println("not a valid file");
		}
		try {
		i.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		imageRepo.save(i);
	}
	
	public Image updateImage(Image image) {
		return imageRepo.save(image);
	}
	
	public List<Image> findGroup(Sort classification){
		return imageRepo.findAll(classification);
	}
	
	public Image findOne(Long id) {
		return imageRepo.findById(id).orElse(null);
	}
}
