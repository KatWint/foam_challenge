package com.katwdojo.experiments.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.katwdojo.experiments.models.Image;

@Repository
public interface ImageRepository extends CrudRepository<Image, Long>,  PagingAndSortingRepository<Image,Long>{

	List<Image> findAll();
	
	List<Image> findAll(Sort sort);
 }
