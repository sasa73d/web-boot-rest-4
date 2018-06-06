package com.izba.albums;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AlbumController {
	
	@Autowired
	private AlbumService albumService;
	
	@RequestMapping("/albums")
	public List<Album> getAllAlbums(@RequestParam(value="id", defaultValue = "0", required = false) Long id,
			@RequestParam(value="userId", defaultValue = "0", required = false) Long userId) {
		return albumService.getAllAlbums(id, userId);
		
	}
	
	@RequestMapping("/albums/{id}")
	public Album getAlbum(@PathVariable Long id) {
		return albumService.getAlbumById(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/albums")
	public Album addAlbum(@RequestBody Album album) {
		return albumService.addAlbum(album);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/albums/{id}")
	public Album updateAlbum(@PathVariable Long id, @RequestBody Album album) {
		return albumService.updateAlbum(album);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/albums{id}")
	public void deleteAlbum(@RequestBody Album album, @PathVariable Long id) {
		albumService.deleteAlbumById(id);
	}

}
