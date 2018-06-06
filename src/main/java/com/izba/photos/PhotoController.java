package com.izba.photos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhotoController {
	
	@Autowired
	PhotoService photoService;
	
	@RequestMapping("/photos")
	public List<Photo> getAllPhotos(@RequestParam(value="id", defaultValue = "0", required = false) Long id,
			@RequestParam(value="albumId", defaultValue = "0", required = false) Long albumId) {
		return photoService.getAllPhotos(id, albumId);
	}
	
	@RequestMapping("/photos/{id}")
	public Photo getPhoto(@PathVariable Long id) {
		return photoService.getPhotoById(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/photos")
	public Photo addPhoto(@RequestBody Photo photo) {
		return photoService.addPhoto(photo);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/photos/{id}")
	public Photo updatePhoto(@PathVariable Long id, @RequestBody Photo photo) {
		return photoService.updatePhoto(photo);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/photos/{id}")
	public void deletePhoto(@RequestBody Photo photo, @PathVariable Long id) {
		photoService.deletePhotoById(id);
	}

}
