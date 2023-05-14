# Instagram  API Application


## Built With
* `Java 17`
* `Maven 4.0.0`
* `MySql Ver 8.0.32`
* `Spring Boot 3.0.5`
* `IntelliJ IDEA 2023.1 (Community Edition)`

## Data Flow

### 1. Model:
* It consists of **User**, **AuthenticationToken** and **Post** entity classes along with their data members and member functions
* Used **_@Table_** and **_@Entity_** annotations inside the entity class.
* Used Lombok to reduce boilerplate code for pojo class.By using Lombok annotations like _**@Data,**_ **@_NoArgsConstructor_**, **_@AllArgsConstructor_** getters and setters for those object generate automatically.
* Used **_@ManyToOne_** and **_@OneToMany_** annotation to perform one to one mapping between User and Post.
* Used **AuthenticationToken** entity to authenticate user for signup, signin and to create post.
* Used **_FetchType.LAZY_** to defer initialization of a User object as long as it's possible.

### 2. Controller:
* It consists of  **UserController** and **PostController** classes in which used the annotations like **@RestController** to annotate the class as Controller.
* Used annotation **_@GetMapping_** , **_@PostMapping_** , **_@PutMapping_** , **_@DeleteMapping_** to map the HTTP web requests to the specific handler methods.

<br>

### API Reference:
<br>

>User's API References
<br>

* User SignUp:
```*.sh-session
  http://localhost:8080/user/signup
```

* User SignIn:
```*.sh-session
  http://localhost:8080/user/signin
```

* Get User's Posts:
```*.sh-session
  http://localhost:8080/user/posts
```

* Update User's Details:
```*.sh-session
  http://localhost:8080/user/update
```


<br>

>Post's API References:
<br>

* Create Post via User's authentication:
```*.sh-session
  http://localhost:8080/post/email/{userEmail}/token/{token}
```

* Get all Posts:
```*.sh-session
  http://localhost:8080/post/
```

<br>

### 3. Service:
* It consists of **UserService**, **TokenService** and **PostService** classes in which provide some business functionalities of every handler methods.
* Used _**@Service**_ annotation to indicate that a class belongs to the service layer.
* Used **_@Transactional_** annotation to separate transaction management code from the code for business logic on the update and delete methods.

### 4. Repository:
* It consists of **UserDao** ,**TokenDao** and **PostDao** interface classes that extends CrudRepository which is interface for generic inbuilt CRUD operations on a repository for a specific type. Usually represent the database access layer in an application.
* Used **Iterable** for User and Post to manage the data of entity classes by performing CRUD operations.
* Used _**@Repository**_ annotation is used to indicate that the class provides the mechanism for storage, retrieval, search, update and delete operation on objects.
* Used _**@Modifying**_ annotation wrote named parameters query using @Query annotation to insert, update, or delete an entity.

## Data Structure Used
Used `Iterable<T>` to store objects for entity classes.

## Project Summary
* In this project I performed CRUD operation like add,get,delete and update.<br/>
* The aim of this project to create instagram-api application via performing _**many to one**_ mapping between entity classes.
* Used interface CrudRepository class for generic CRUD inbuilt operations like save,saveAll,updateById, etc.
* Used our own custom finder methods and wrote operations using custom queries.
* Implemented authentication for User SignUp, SignIn and to create Post.