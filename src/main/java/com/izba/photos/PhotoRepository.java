package com.izba.photos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PhotoRepository extends CrudRepository<Photo, Long> { 

	List<Photo> findByIdOrAlbumId(Long id, Long albumId);

}
