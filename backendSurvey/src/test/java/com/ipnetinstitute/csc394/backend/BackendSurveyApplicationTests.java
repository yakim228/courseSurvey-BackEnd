package com.ipnetinstitute.csc394.backend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.ipnetinstitute.csc394.backend.BackendSurveyApplication;
<<<<<<< HEAD
import com.ipnetinstitute.csc394.backend.dao.BaseEntityRepository;
import com.ipnetinstitute.csc394.backend.entity.CatSurvey;
import com.ipnetinstitute.csc394.backend.entity.Student;
import com.ipnetinstitute.csc394.backend.entity.Term;
import com.ipnetinstitute.csc394.backend.entity.User;
import com.ipnetinstitute.csc394.backend.payload.request.LoginRequest;
=======
>>>>>>> fdc0442769b4f53d05958c45920a10bf7d43a88f
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.BeforeEach;
<<<<<<< HEAD
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
=======
import org.junit.jupiter.api.Order;
>>>>>>> fdc0442769b4f53d05958c45920a10bf7d43a88f
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith({ RestDocumentationExtension.class, SpringExtension.class })
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
<<<<<<< HEAD
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
=======
>>>>>>> fdc0442769b4f53d05958c45920a10bf7d43a88f
class BackendSurveyApplicationTests extends SpringBootServletInitializer {

	private ObjectMapper mapper = new ObjectMapper();
	@Autowired
	private WebApplicationContext context;

<<<<<<< HEAD
        @Autowired
	private BaseEntityRepository<User> userRepo;
        
        @Autowired
	private BaseEntityRepository<CatSurvey> catSurveyRepo;
        
=======
>>>>>>> fdc0442769b4f53d05958c45920a10bf7d43a88f
	private MockMvc mockMvc;

	@BeforeEach
	public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {

		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.apply(documentationConfiguration(restDocumentation)).build();
	}
<<<<<<< HEAD

	@Test
	@Order(1)
	public void signupTest() throws JsonProcessingException, Exception {
		System.out.println("SignupTest is called");
		User user = new User("John", "Doe", "+228 90 12 13 54", "john.doe@gmail.com");
		user.setModBy(1);
		user.setUserName("john.doe");
		user.setPassword("secret@Password");
		user.setModDateTime(new Date());
		user.setCreateDateTime(new Date());
		System.out.println(user);
		String userJson = mapper.writeValueAsString(user);
		System.out.println(userJson);
		try {
			mockMvc.perform(post("/api/auth/signup").contentType(MediaType.APPLICATION_JSON).content(userJson))
					.andExpect(status().isOk())
					.andExpect(jsonPath("userName", is(user.getUserName())))
					.andDo(document("signup", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()),
							requestFields(fieldWithPath("id").description("User id").ignored(),
									fieldWithPath("userName").description("Nom d'utilisateur"),
									fieldWithPath("password").description("Mot de passe"),
									fieldWithPath("firstName").description("Prenom"),
									fieldWithPath("lastName").description("Nom"),
									fieldWithPath("phone").description("Telephone"),
									fieldWithPath("email").description("E-mail"),
									fieldWithPath("student").description("La liste d'eleves"),
									fieldWithPath("role").description("La liste de roles"),
									fieldWithPath("teacher").description("La liste d'enseignants"),
									fieldWithPath("type").description("Set this to user"),
									fieldWithPath("createDateTime").description("Date de creation").ignored(),
									fieldWithPath("modDateTime").description("Date de modification").ignored(),
									fieldWithPath("error").description("Utiliser pour le message d'erreur").ignored(),
									fieldWithPath("modBy").description("Id de la personne connetee"))));

		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	
	 @Test
	 @Order(2)
         public void signinTest() throws JsonProcessingException, Exception
	 { System.out.println("signinTest is called");
         LoginRequest user1 = new LoginRequest("john.doe", "secret@Password");
         System.out.println(user1);
	 String userJson = mapper.writeValueAsString(user1);
	 System.out.println(userJson);
         try {
	 mockMvc.perform(post("/api/auth/signin").contentType(MediaType.APPLICATION_JSON)
         .content(userJson)).andExpect(status().isOk())
         .andExpect(jsonPath("username", is(user1.getUserName())))
	 .andDo(document("signin", preprocessRequest(prettyPrint()),
	 preprocessResponse(prettyPrint()),
	 requestFields(
            fieldWithPath("userName").description("Nom d'utilisateur"),
            fieldWithPath("password").description("Le mot de passe").ignored()
            ))); 
         }
	 catch (Exception e) { e.printStackTrace(); throw (e); } }
	
        @Test
	@Order(3)
	public void deleteUserTest() throws JsonProcessingException, Exception {
		System.out.println("deleteUser is called");
                List<User> users = userRepo.findAll();
                User user1 = users.get(users.size()-1);
                User user = new User(user1.getFirstName(), user1.getLastName(),user1.getPhone() ,user1.getEmail());
                user.setId(user1.getId());
		System.out.println(user);
		String userJson = mapper.writeValueAsString(user);
		try {
			mockMvc.perform(post("/delete/{entity}","user").contentType(MediaType.APPLICATION_JSON)
					.content(userJson)).andExpect(status().isOk())
					.andExpect(MockMvcResultMatchers.content().string("Success"))
					.andDo(document("deleteUser",
							preprocessRequest(prettyPrint()), 
							preprocessResponse(prettyPrint()),
                                                        requestFields(fieldWithPath("id").description("User id").ignored(),
									fieldWithPath("userName").description("Nom d'utilisateur"),
									fieldWithPath("password").description("Mot de passe"),
									fieldWithPath("firstName").description("Prenom"),
									fieldWithPath("lastName").description("Nom"),
									fieldWithPath("phone").description("Telephone"),
									fieldWithPath("email").description("E-mail"),
									fieldWithPath("student").description("La liste d'eleves"),
									fieldWithPath("role").description("La liste de roles"),
									fieldWithPath("teacher").description("La liste d'enseignants"),
									fieldWithPath("type").description("Set this to user"),
									fieldWithPath("createDateTime").description("Date de creation").ignored(),
									fieldWithPath("modDateTime").description("Date de modification").ignored(),
									fieldWithPath("error").description("Utiliser pour le message d'erreur").ignored(),
									fieldWithPath("modBy").description("Id de la personne connetee"))
							));

		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}
        
        @Test
	@Order(4)
	public void saveUserTest() throws JsonProcessingException, Exception {
		System.out.println("SaveUserTest is called");
		User user = new User("John", "Doe", "+228 90 12 13 14", "john.doe@gmail.com");
		user.setModBy(1);
		user.setUserName("john.doe");
		user.setPassword("secret@Password");
		user.setModDateTime(new Date());
		user.setCreateDateTime(new Date());
		System.out.println(user);
		String userJson = mapper.writeValueAsString(user);
		System.out.println(userJson);
		try {
			mockMvc.perform(post("/save/{entity}", "user").contentType(MediaType.APPLICATION_JSON).content(userJson))
					.andExpect(status().isOk()).andExpect(jsonPath("userName", is(user.getUserName())))
					.andDo(document("saveUser", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()),
							requestFields(fieldWithPath("id").description("User id").ignored(),
									fieldWithPath("userName").description("Nom d'utilisateur"),
									fieldWithPath("password").description("Mot de passe"),
									fieldWithPath("firstName").description("Prenom"),
									fieldWithPath("lastName").description("Nom"),
									fieldWithPath("phone").description("Telephone"),
									fieldWithPath("email").description("E-mail"),
									fieldWithPath("type").description("Set this to user"),
                                                                        fieldWithPath("student").description("La liste d'eleves"),
									fieldWithPath("role").description("La liste de roles"),
									fieldWithPath("teacher").description("La liste d'enseignants"),
									fieldWithPath("createDateTime").description("Date de creation").ignored(),
									fieldWithPath("modDateTime").description("Date de modification").ignored(),
									fieldWithPath("error").description("Utiliser pour le message d'erreur").ignored(),
									fieldWithPath("modBy").description("Id de la personne connetee"))));

		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}
	 
        @Test 
        @Order(5)
        public void deleteUserByIdTest() throws JsonProcessingException, Exception {
           System.out.println("DeleteUserByIdTest is called");
           List<User> users = userRepo.findAll();
           User user1 = users.get(users.size()-1);
           try {
               mockMvc.perform(get("/delete/{entity}/{id}","user",user1.getId()).contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk()).andDo(document("deleteUserById",
               preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()))); 
            } catch (Exception e) { e.printStackTrace(); throw (e); }
        }
        
	 @Test 
	 @Order(6)
         public void countTest() throws JsonProcessingException, Exception {
            System.out.println("CountTest is called"); try {
                mockMvc.perform(get("/count/{entity}","user").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(document("count",
                preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()))); 
             } catch (Exception e) { e.printStackTrace(); throw (e); }
         }
         
         @Test 
	 @Order(7)
         public void getAllUsersTest() throws JsonProcessingException, Exception {
            System.out.println("getAllUsersTest is called");
            try {
                mockMvc.perform(get("/getAll/{entity}","user").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(document("getAllUsers",
                preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()))); 
             } catch (Exception e) { e.printStackTrace(); throw (e); }
         }
         
         
         
//         ======================================================================
         @Test
         @Order(8)
         public void saveCategorieSurveyTest() throws JsonProcessingException, Exception {
            System.out.println("SaveCategorieSurveyTest is called");
            CatSurvey catSurvey = new CatSurvey();
            catSurvey.setName("Survey du 2eme semestre");
            catSurvey.setDescription("Ce survey compte evaluer les professeurs");
            catSurvey.setModBy(1);
            catSurvey.setCreateDateTime(new Date());
            catSurvey.setModDateTime(new Date());
            String catSurveyJson = mapper.writeValueAsString(catSurvey);
            System.out.println(catSurvey);
		try {
			mockMvc.perform(post("/save/{entity}", "cat_survey").contentType(MediaType.APPLICATION_JSON).content(catSurveyJson))
					.andExpect(status().isOk())
					.andDo(document("saveCatSurvey", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()),
							requestFields(fieldWithPath("id").description("catSurvey id").ignored(),
									fieldWithPath("name").description("Nom du survey"),
									fieldWithPath("description").description("la description du survey"),
									fieldWithPath("catSurveyQuestions").description("La liste de categorie lie a cet survey"),
									fieldWithPath("surveys").description("Liste des surveys de cette d'une catgorie"),
                                                                        fieldWithPath("createDateTime").description("Date de creation").ignored(),
									fieldWithPath("modDateTime").description("Date de modification").ignored(),
									fieldWithPath("error").description("Utiliser pour le message d'erreur").ignored(),
									fieldWithPath("modBy").description("Id de la personne connetee"),
                                                                        fieldWithPath("type").description("type du CatSurvey")
                                                                        
                                                        )));

		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
         }
         
        @Test
	@Order(9)
	public void deleteCatSurveyTest() throws JsonProcessingException, Exception {
		System.out.println("deleteCatSurvey is called");
                List<CatSurvey> catSurveys = catSurveyRepo.findAll();
                CatSurvey catSurvey = catSurveys.get(catSurveys.size()-1);
                CatSurvey catSurvey1 = new CatSurvey();
                catSurvey1.setId(catSurvey.getId());
                catSurvey1.setName("Survey du 2eme semestre");
                catSurvey1.setDescription("Ce survey compte evaluer les professeurs");
                catSurvey1.setCreateDateTime(new Date());
                catSurvey1.setModDateTime(new Date());
		System.out.println(catSurvey1);
		String catSurveyJson = mapper.writeValueAsString(catSurvey1);
		try {
			mockMvc.perform(post("/delete/{entity}","cat_survey").contentType(MediaType.APPLICATION_JSON)
					.content(catSurveyJson)).andExpect(status().isOk())
					.andExpect(MockMvcResultMatchers.content().string("Success"))
					.andDo(document("deleteCatSurvey",
							preprocessRequest(prettyPrint()), 
							preprocessResponse(prettyPrint()),
                                                        requestFields(fieldWithPath("id").description("User id").ignored(),
									fieldWithPath("name").description("Nom du survey"),
									fieldWithPath("description").description("la description du survey"),
									fieldWithPath("catSurveyQuestions").description("La liste de categorie lie a cet survey"),
									fieldWithPath("surveys").description("Liste des surveys de cette d'une catgorie"),
                                                                        fieldWithPath("createDateTime").description("Date de creation").ignored(),
									fieldWithPath("modDateTime").description("Date de modification").ignored(),
									fieldWithPath("error").description("Utiliser pour le message d'erreur").ignored(),
									fieldWithPath("modBy").description("Id de la personne connetee"),
                                                                        fieldWithPath("type").description("type du CatSurvey"))));

		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}
        
        @Test
	@Order(10)
	public void deleteCatSurveyByIdTest() throws JsonProcessingException, Exception {
		System.out.println("deleteCatSurveyById is called");
                List<CatSurvey> catSurveys = catSurveyRepo.findAll();
                CatSurvey catSurvey = catSurveys.get(catSurveys.size()-1);
		try {
			mockMvc.perform(get("/delete/{entity}/{id}","cat_survey",catSurvey.getId()).contentType(MediaType.APPLICATION_JSON)
					).andExpect(status().isOk())
					.andExpect(MockMvcResultMatchers.content().string("Success"))
					.andDo(document("deleteCatSurveyById",
							preprocessRequest(prettyPrint()), 
							preprocessResponse(prettyPrint())
                                                        ));

		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}
=======
	/*
	 * @Test
	 * 
	 * @Order(1) public void signupTest() throws JsonProcessingException, Exception
	 * { System.out.println("SignupTest is called"); User user = new User("John",
	 * "Doe", "+228 90 12 13 54", "john.doe@gmail.com"); user.setModBy(1);
	 * user.setUserName("john.doe"); user.setPassword("secret@Password");
	 * user.setModDateTime(new Date()); user.setCreateDateTime(new Date());
	 * System.out.println(user); String userJson = mapper.writeValueAsString(user);
	 * System.out.println(userJson); try {
	 * mockMvc.perform(post("/api/auth/signup").contentType(MediaType.
	 * APPLICATION_JSON).content(userJson)) .andExpect(status().isOk())
	 * .andExpect(jsonPath("userName", is(user.getUserName())))
	 * .andDo(document("signup", preprocessRequest(prettyPrint()),
	 * preprocessResponse(prettyPrint()),
	 * requestFields(fieldWithPath("id").description("User id").ignored(),
	 * fieldWithPath("userName").description("Nom d'utilisateur"),
	 * fieldWithPath("password").description("Mot de passe"),
	 * fieldWithPath("firstName").description("Prenom"),
	 * fieldWithPath("lastName").description("Nom"),
	 * fieldWithPath("phone").description("Telephone"),
	 * fieldWithPath("email").description("E-mail"),
	 * fieldWithPath("student").description("La liste d'eleves"),
	 * fieldWithPath("role").description("La liste de roles"),
	 * fieldWithPath("teacher").description("La liste d'enseignants"),
	 * fieldWithPath("type").description("Set this to user"),
	 * fieldWithPath("createDateTime").description("Date de creation").ignored(),
	 * fieldWithPath("modDateTime").description("Date de modification").ignored(),
	 * fieldWithPath("error").description("Utiliser pour le message d'erreur").
	 * ignored(),
	 * fieldWithPath("modBy").description("Id de la personne connetee"))));
	 * 
	 * } catch (Exception e) { e.printStackTrace(); throw (e); } }
	 */

	/*
	 * @Test
	 * 
	 * @Order(2) public void signinTest() throws JsonProcessingException, Exception
	 * { System.out.println("signinTest is called"); LoginRequest user1 = new
	 * LoginRequest("john.doe", "secret@Password"); System.out.println(user1);
	 * String userJson = mapper.writeValueAsString(user1);
	 * System.out.println(userJson); try {
	 * mockMvc.perform(post("/api/auth/signin").contentType(MediaType.
	 * APPLICATION_JSON).content(userJson)) .andExpect(status().isOk()) //
	 * .andExpect(jsonPath("userName", is(user1.getUserName())))
	 * .andDo(document("signin", preprocessRequest(prettyPrint()),
	 * preprocessResponse(prettyPrint()),
	 * requestFields(fieldWithPath("id").description("User id").ignored(),
	 * fieldWithPath("userName").description("Nom d'utilisateur"),
	 * fieldWithPath("email").description("E-mail").ignored(),
	 * fieldWithPath("roles").description("Set role to user").ignored(),
	 * fieldWithPath("accessToken").description("The Token").ignored(),
	 * fieldWithPath("tokenType").description("The token Type").ignored()))); }
	 * catch (Exception e) { e.printStackTrace(); throw (e); } }
	 */
	/*
	 * @Test
	 * 
	 * @Order(3) public void deleteUserTest() throws JsonProcessingException,
	 * Exception { System.out.println("delete is called"); User user = new
	 * User("John", "Doe", "+228 90 12 13 14", "john.doe@gmail.com");
	 * 
	 * user.setModBy(1); user.setUserName("john.doe");
	 * user.setPassword("secret@Password"); String userJson =
	 * mapper.writeValueAsString(user); try {
	 * mockMvc.perform(post("/delete/{entity}","user").contentType(MediaType.
	 * APPLICATION_JSON) .content(userJson)).andExpect(status().isOk())
	 * .andExpect(MockMvcResultMatchers.content().string("Success"))
	 * .andDo(document("deleteUser", preprocessRequest(prettyPrint()),
	 * preprocessResponse(prettyPrint())
	 * //requestFields(fieldWithPath("content").description("User name"))
	 * //responseFields(fieldWithPath("content").
	 * description("Success or Error: Error message")) ));
	 * 
	 * } catch (Exception e) { e.printStackTrace(); throw (e); } }
	 * 
	 * @Test
	 * 
	 * @Order(4) public void countTest() throws JsonProcessingException, Exception {
	 * System.out.println("Count is called"); try {
	 * mockMvc.perform(get("/count/{entity}",
	 * "user").contentType(MediaType.APPLICATION_JSON))
	 * .andExpect(status().isOk()).andDo(document("count",
	 * preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())));
	 * 
	 * } catch (Exception e) { e.printStackTrace(); throw (e); } }
	 * 
	 */
>>>>>>> fdc0442769b4f53d05958c45920a10bf7d43a88f
}
