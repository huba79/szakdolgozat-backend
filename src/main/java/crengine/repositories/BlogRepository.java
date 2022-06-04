/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crengine.repositories;


import crengine.domain.BlogEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
/**
 *
 * @author huba.tanczos
 */
@Repository
public interface  BlogRepository extends JpaRepository<BlogEntry, Long> {

    Optional<BlogEntry> findBlogEntryById(Long id);
        @Query(
            value = "SELECT * FROM BLOG_ENTRIES B WHERE B.COMPANY_ID = ?1", 
            nativeQuery = true)
    ArrayList<BlogEntry> findBlogEntryByCompanyId(Long id);   
}