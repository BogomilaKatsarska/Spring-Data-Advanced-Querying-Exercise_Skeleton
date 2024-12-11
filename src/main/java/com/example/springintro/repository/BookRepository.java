package com.example.springintro.repository;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.BookInfo;
import com.example.springintro.model.entity.EditionType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);

    List<Book> findByPriceLessThanOrPriceGreaterThan(BigDecimal lowestPrice, BigDecimal highestPrice);
    // findByPriceNotBetween() does not work here

    List<Book> findAllByReleaseDateBeforeOrReleaseDateAfter(LocalDate before, LocalDate after);

    List<Book> findByReleaseDateBefore(LocalDate parsed);

    List<Book> findByTitleContainingIgnoreCase(String search);

    List<Book> findByAuthorLastNameStartingWith(String ending);

    @Query("SELECT COUNT(b) FROM Book b " +
    "WHERE LENGTH(b.title) > :length")
    int countByTitleSizeGreaterThan(int length);

    BookInfo findByTitle(String title);

    @Query("UPDATE Book as b SET b.copies = b.copies + :count WHERE b.releaseDate > :parsed")
    @Modifying
    @Transactional
    int updateBookCopiesReleasedAfter(LocalDate parsed, int count);

    @Transactional
    int deleteByCopiesLessThan(int count);
}
