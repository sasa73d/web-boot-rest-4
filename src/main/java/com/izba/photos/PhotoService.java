package com.izba.photos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoService {
	
	@Autowired
	PhotoRepository photoRepository;
	
	public List<Photo> getAllPhotos(Long id, Long albumId) {
		if (id > 0 || albumId > 0) {
			return (List<Photo>) photoRepository.findByIdOrAlbumId(id, albumId);
		} else {
			return (List<Photo>) photoRepository.findAll();
		}
	}
	
	public Photo getPhotoById(Long id) {
		return photoRepository.findById(id).get();
	}
	
	public Photo addPhoto(Photo photo) {
		return photoRepository.save(photo);
	}
	
	public Photo updatePhoto(Photo photo) {
		return photoRepository.save(photo);
	}
	
	public void deletePhotoById(Long id) {
		photoRepository.deleteById(id);
	}

}
