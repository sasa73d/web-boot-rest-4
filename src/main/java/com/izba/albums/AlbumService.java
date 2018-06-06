package com.izba.albums;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumService {
	
	@Autowired
	private AlbumRepository albumRepository;
	
	public List<Album> getAllAlbums(Long id, Long userId) {
		if (id > 0 || userId > 0) {
			return (List<Album>) albumRepository.findByIdOrUserId(id, userId);
		} else {
			return (List<Album>) albumRepository.findAll();
		}
	}
	
	public Album getAlbumById(Long id) {
		return albumRepository.findById(id).get();
	}
	
	public Album addAlbum(Album album) {
		return albumRepository.save(album);
	}
	
	public Album updateAlbum(Album album) {
		return albumRepository.save(album);
	}
	
	public void deleteAlbumById(Long id) {
		albumRepository.deleteById(id);
	}

}
