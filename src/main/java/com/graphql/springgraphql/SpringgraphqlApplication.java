package com.graphql.springgraphql;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.graphql.springgraphql.demo.Author;
import com.graphql.springgraphql.demo.AuthorRepository;
import com.graphql.springgraphql.demo.Book;
import com.graphql.springgraphql.demo.BookRepository;
import com.graphql.springgraphql.demo.BookResolver;
import com.graphql.springgraphql.demo.Mutation;
import com.graphql.springgraphql.demo.Query;

@SpringBootApplication
public class SpringgraphqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringgraphqlApplication.class, args);
	}

	@Bean
	public BookResolver authorResolver(AuthorRepository authorRepository) {
		return new BookResolver(authorRepository);
	}

	@Bean
	public Query query(AuthorRepository authorRepository, BookRepository bookRepository) {
		return new Query(authorRepository, bookRepository);
	}

	@Bean
	public Mutation mutation(AuthorRepository authorRepository, BookRepository bookRepository) {
		return new Mutation(authorRepository, bookRepository);
	}
	
	@Bean
	public CommandLineRunner demo(AuthorRepository authorRepository, BookRepository bookRepository) {
		return (args) -> {
			Author author = new Author("Herbert", "Schildt");
			authorRepository.save(author);

			bookRepository.save(new Book("Java: A Beginner's Guide, Sixth Edition", "0071809252", 728, author));
		};
	}
}
