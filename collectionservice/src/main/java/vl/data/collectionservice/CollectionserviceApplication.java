package vl.data.collectionservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import vl.data.collectionservice.init.album.AlbumLoader;
import vl.data.collectionservice.init.comment.CommentLoader;
import vl.data.collectionservice.init.photo.PhotoLoader;
import vl.data.collectionservice.init.post.PostLoader;
import vl.data.collectionservice.init.todo.TodoLoader;
import vl.data.collectionservice.init.user.UserLoader;
import vl.data.collectionservice.init.test.TestCaseLoader;

@SpringBootApplication
@EnableDiscoveryClient
public class CollectionserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollectionserviceApplication.class, args);
	}

	@Bean
	@Order(1)
	CommandLineRunner users(UserLoader userLoader){
		return args -> {
			userLoader.loadUsers();

		};
	}

	@Bean
	@Order(2)
	CommandLineRunner todo(TodoLoader todoLoader){
		return args -> {
			todoLoader.loadTodos();

		};
	}

	@Bean
	@Order(3)
	CommandLineRunner post(PostLoader postLoader){
		return args -> {
			postLoader.loadPosts();

		};
	}

	@Bean
	@Order(4)
	CommandLineRunner comment(CommentLoader commentLoader){
		return args -> {
			commentLoader.loadComments();

		};
	}

	@Bean
	@Order(5)
	CommandLineRunner album(AlbumLoader albumLoader){
		return args -> {
			albumLoader.loadAlbums();

		};
	}

	@Bean
	@Order(6)
	CommandLineRunner photo(PhotoLoader photoLoader){
		return args -> {
			photoLoader.loadPhotos();

		};
	}

	@Bean
	@Order(7)
	CommandLineRunner testCase(TestCaseLoader testCasesLoader){
		return args -> {
			testCasesLoader.saveNewUser(1L);
			testCasesLoader.saveNewUser(3L);
			testCasesLoader.saveNewUser(7L);

		};
	}



}
