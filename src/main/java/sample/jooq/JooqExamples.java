/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.jooq;

import org.jooq.Configuration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sample.jooq.domain.tables.daos.BookDao;
import sample.jooq.domain.tables.pojos.Book;

@Component
public class JooqExamples implements CommandLineRunner {
    private final Configuration configuration;

    private JooqExamples(Configuration configuration) {

        this.configuration = configuration;
    }

    @Override
    public void run(String... args) throws Exception {

        Book book = new Book();
        book.setId(3);
        book.setAuthorId(2);
        book.setTitle("A Song of Ice and Fire");
        book.setPublishedIn(2001);
        book.setLanguageId(1);

        BookDao bookDao = new BookDao(configuration);
        bookDao.insert(book);

        Iterable<Book> allBooks = bookDao.findAll();

        System.out.println("all books : " + allBooks);

    }

 //   private void create(Book book) {


//        Query query = this.dsl.insertInto(BOOK)
//            .set(BOOK.AUTHOR_ID, myBook.getAuthorId())
//            .set(BOOK.TITLE, myBook.getTitle())
//            .set(BOOK.PUBLISHED_IN, myBook.getPublishedIn())
//            .set(BOOK.LANGUAGE_ID, myBook.getLanguageId())
//            .returning(BOOK.ID);

//        query.execute();
//    }

//    private Iterable<MyBook> fetchAllBooks() {
//
//        return this.dsl.selectFrom(BOOK)
//            .orderBy(BOOK.TITLE)
//            .fetch(record -> aMyBook()
//                .id(record.getId())
//                .authorId(record.getAuthorId())
//                .title(record.getTitle())
//                .publishedIn(record.getPublishedIn())
//                .languageId(record.getLanguageId())
//                .build());
//    }
//
//    private void fetchAllBooks(String bookStoreName) {
//
//        Query query = this.dsl.select(BOOK.TITLE, AUTHOR.FIRST_NAME, AUTHOR.LAST_NAME)
//            .from(BOOK).join(AUTHOR).on(BOOK.AUTHOR_ID.eq(AUTHOR.ID))
//            .join(BOOK_TO_BOOK_STORE).on(BOOK_TO_BOOK_STORE.BOOK_ID.eq(BOOK.ID))
//            .where(BOOK_TO_BOOK_STORE.NAME.eq(bookStoreName));
//
//        Object[] bind = query.getBindValues().toArray(new Object[]{});
//        List<String> list = this.jdbc.query(query.getSQL(), bind,
//            (rs, rowNum) -> rs.getString(1) + " : " + rs.getString(2) + " "
//                + rs.getString(3));
//
//
//        System.out.println("List of books supplied by " + bookStoreName + " :" + list);
//    }
//
//    private void jooqFetch() {
//
//        Result<Record> results = this.dsl.select().from(AUTHOR).fetch();
//        for (Record result : results) {
//            Integer id = result.getValue(AUTHOR.ID);
//            String firstName = result.getValue(AUTHOR.FIRST_NAME);
//            String lastName = result.getValue(AUTHOR.LAST_NAME);
//            System.out.println("jOOQ Fetch " + id + " " + firstName + " " + lastName);
//        }
//    }
//
//    private void jooqSql() {
//
//        Query query = this.dsl.select(BOOK.TITLE, AUTHOR.FIRST_NAME, AUTHOR.LAST_NAME)
//            .from(BOOK).join(AUTHOR).on(BOOK.AUTHOR_ID.equal(AUTHOR.ID))
//            .where(BOOK.PUBLISHED_IN.equal(2015));
//        Object[] bind = query.getBindValues().toArray(new Object[]{});
//        List<String> list = this.jdbc.query(query.getSQL(), bind,
//            (rs, rowNum) -> rs.getString(1) + " : " + rs.getString(2) + " "
//                + rs.getString(3));
//        System.out.println("jOOQ SQL " + list);
//    }
}
