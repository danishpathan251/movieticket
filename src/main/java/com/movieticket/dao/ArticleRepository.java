package com.movieticket.dao;

import com.movieticket.entities.Articles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ArticleRepository extends JpaRepository<Articles,Long> {


}
