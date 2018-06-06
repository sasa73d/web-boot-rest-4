package com.izba.albums;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AlbumRepository extends CrudRepository<Album, Long> {
	public List<Album> findByIdOrUserId(Long id, Long userId);
}
