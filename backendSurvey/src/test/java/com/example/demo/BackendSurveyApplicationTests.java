package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.ipnetinstitute.csc394.backend.BackendSurveyApplication;
import com.ipnetinstitute.csc394.backend.dao.BaseEntityRepository;
import com.ipnetinstitute.csc394.backend.entity.CatSurvey;
import com.ipnetinstitute.csc394.backend.entity.Classe;
import com.ipnetinstitute.csc394.backend.entity.Course;
import com.ipnetinstitute.csc394.backend.entity.Student;
import com.ipnetinstitute.csc394.backend.entity.Survey;
import com.ipnetinstitute.csc394.backend.entity.User;
import com.ipnetinstitute.csc394.backend.payload.request.LoginRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
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
import org.springframework.restdocs.payload.PayloadDocumentation;
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
@SpringBootTest(classes = {BackendSurveyApplication.class})
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BackendSurveyApplicationTests extends SpringBootServletInitializer{

        private ObjectMapper mapper = new ObjectMapper();
	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;
        
        @Autowired
	private BaseEntityRepository<User> userRepo;
        
        @Autowired
	private BaseEntityRepository<CatSurvey> catSurveyRepo;
        
        @Autowired
	private BaseEntityRepository<Course> courseRepo;
        
        @Autowired
	private BaseEntityRepository<Survey> surveyRepo;
        @Autowired
	private BaseEntityRepository<Classe> classeRepo;
        
	@BeforeEach
	public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {

		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(documentationConfiguration(restDocumentation)).build();
	}
        
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
//									fieldWithPath("student").description("La liste d'eleves"),
									fieldWithPath("role").description("La liste de roles"),
//									fieldWithPath("teacher").description("La liste d'enseignants"),
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
//									fieldWithPath("student").description("La liste d'eleves"),
									fieldWithPath("role").description("La liste de roles"),
//									fieldWithPath("teacher").description("La liste d'enseignants"),
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
//                                                                        fieldWithPath("student").description("La liste d'eleves"),
									fieldWithPath("role").description("La liste de roles"),
//									fieldWithPath("teacher").description("La liste d'enseignants"),
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
         public void countUsersTest() throws JsonProcessingException, Exception {
            System.out.println("CountTest is called"); try {
                mockMvc.perform(get("/count/{entity}","user").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(document("countUsers",
                preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()))); 
             } catch (Exception e) { e.printStackTrace(); throw (e); }
         }
         @Test 
	 @Order(6)
         public void getAllUsersTest() throws JsonProcessingException, Exception {
            System.out.println("getAllUsersTest is called"); try {
                mockMvc.perform(get("/getAll/{entity}","user").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(document("getAllUsers",
                preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()))); 
             } catch (Exception e) { e.printStackTrace(); throw (e); }
         }
//         ======================================================================
         @Test
         @Order(7)
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
//									fieldWithPath("catSurveyQuestions").description("La liste de categorie lie a cet survey"),
//									fieldWithPath("surveys").description("Liste des surveys de cette d'une catgorie"),
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
	@Order(8)
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
//									fieldWithPath("catSurveyQuestions").description("La liste de categorie lie a cet survey"),
//									fieldWithPath("surveys").description("Liste des surveys de cette d'une catgorie"),
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
	@Order(9)
	public void deleteCatSurveyByIdTest() throws JsonProcessingException, Exception {
		System.out.println("deleteCatSurveyById is called");
                
                CatSurvey catSurvey1 = new CatSurvey();
                catSurvey1.setName("Survey du 2eme semestre");
                catSurvey1.setDescription("Ce survey compte evaluer les professeurs");
                catSurvey1.setModBy(1);
                catSurvey1.setCreateDateTime(new Date());
                catSurvey1.setModDateTime(new Date());
                catSurveyRepo.save(catSurvey1);
                
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
        
        @Test
         @Order(10)
         public void saveSurveyTest() throws JsonProcessingException, Exception {
            System.out.println("SaveSurveyTest is called");
            Survey survey = new Survey();
            survey.setTitle("survey standard 1");
            survey.setDescription("survey d'evaluation");
            survey.setModBy(1);
            survey.setCreateDateTime(new Date());
            survey.setModDateTime(new Date());
            survey.setBeginDate(new Date());
            survey.setBeginMessage("ouverture du survey 1");
            survey.setBeginDate(new Date());
            survey.setEndMessage("fermeture du survey 1");
            survey.setStatus(0);
            
            CatSurvey catSurvey1 = new CatSurvey();
            catSurvey1.setName("Survey du 2eme semestre");
            catSurvey1.setDescription("Ce survey compte evaluer les professeurs");
            catSurvey1.setModBy(1);
            catSurvey1.setCreateDateTime(new Date());
            catSurvey1.setModDateTime(new Date());
            catSurveyRepo.save(catSurvey1);
            
            List<CatSurvey> catSurveys = catSurveyRepo.findAll();
            CatSurvey catSurvey = catSurveys.get(catSurveys.size()-1);
            survey.setCatSurvey(catSurvey);
            
            List<Course> courses = courseRepo.findAll();
            Course course = courses.get(courses.size()-1);
            survey.setCourse(course);
            
            String surveyJson = mapper.writeValueAsString(survey);
            System.out.println(survey);
		try {
			mockMvc.perform(post("/save/{entity}", "survey").contentType(MediaType.APPLICATION_JSON).content(surveyJson))
					.andExpect(status().isOk())
					.andDo(document("saveSurvey", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()),
							requestFields(fieldWithPath("id").description("Survey id").ignored(),
									fieldWithPath("title").description("Title du survey"),
									fieldWithPath("description").description("la description du survey"),
									fieldWithPath("beginMessage").description("Message de debut du survey"),
                                                                        fieldWithPath("beginDate").description("Date de debut du survey"),
									fieldWithPath("endMessage").description("Message de fin du survey"),
									fieldWithPath("endDate").description("Date de fin du survey"),
                                                                        PayloadDocumentation.subsectionWithPath("catSurvey").description("categprie du survey"),
                                                                        PayloadDocumentation.subsectionWithPath("course").description("cours concerne"),
                                                                        fieldWithPath("status").description("status du survey"),
                                                                        fieldWithPath("createDateTime").description("Date de creation").ignored(),
									fieldWithPath("modDateTime").description("Date de modification").ignored(),
									fieldWithPath("error").description("Utiliser pour le message d'erreur").ignored(),
									fieldWithPath("modBy").description("Id de la personne connetee"),
                                                                        fieldWithPath("type").description("type du Survey")
                                                                        
                                                        )));

		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
         }
         
        @Test
	@Order(11)
	public void deleteSurveyTest() throws JsonProcessingException, Exception {
		System.out.println("deleteSurvey is called");
                List<Survey> surveys = surveyRepo.findAll();
                Survey survey = surveys.get(surveys.size()-1);
                Survey survey1 = new Survey();
                survey1.setId(survey.getId());
                survey1.setTitle("survey standard 1");
                survey1.setDescription("survey d'evaluation");
                survey1.setModBy(1);
                survey1.setCreateDateTime(new Date());
                survey1.setModDateTime(new Date());
                survey1.setBeginDate(new Date());
                survey1.setBeginMessage("ouverture du survey 1");
                survey1.setBeginDate(new Date());
                survey1.setEndMessage("fermeture du survey 1");
                survey1.setStatus(0);
		System.out.println(survey1);
		String catSurveyJson = mapper.writeValueAsString(survey1);
		try {
			mockMvc.perform(post("/delete/{entity}","survey").contentType(MediaType.APPLICATION_JSON)
					.content(catSurveyJson)).andExpect(status().isOk())
					.andExpect(MockMvcResultMatchers.content().string("Success"))
					.andDo(document("deleteSurvey",
							preprocessRequest(prettyPrint()), 
							preprocessResponse(prettyPrint()),
                                                        requestFields(fieldWithPath("id").description("Survey id").ignored(),
									fieldWithPath("title").description("Title du survey"),
									fieldWithPath("description").description("la description du survey"),
                                                                        fieldWithPath("beginMessage").description("Message de debut du survey"),
                                                                        fieldWithPath("beginDate").description("Date de debut du survey"),
									fieldWithPath("endMessage").description("Message de fin du survey"),
									fieldWithPath("endDate").description("Date de fin du survey"),
                                                                        fieldWithPath("status").description("status du survey"),
                                                                        fieldWithPath("course").description("cours concerne"),
                                                                        fieldWithPath("catSurvey").description("categprie du survey"),
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
	@Order(14)
	public void saveClasseTest() throws JsonProcessingException, Exception {
            System.out.println("SaveCourseTest is called");
            Classe classe = new Classe();
            
            classe.setName("Classe Colombia");
            classe.setModBy(1);
            classe.setCreateDateTime(new Date());
            classe.setModDateTime(new Date());
            
            String classeJson = mapper.writeValueAsString(classe);
            System.out.println(classe);
		try {
			mockMvc.perform(post("/save/{entity}", "classe").contentType(MediaType.APPLICATION_JSON).content(classeJson))
					.andExpect(status().isOk())
					.andDo(document("saveClasse", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()),
							requestFields(fieldWithPath("id").description("Survey id").ignored(),
									fieldWithPath("name").description("Nom de la classe"),
                                                                        fieldWithPath("createDateTime").description("Date de creation").ignored(),
									fieldWithPath("modDateTime").description("Date de modification").ignored(),
									fieldWithPath("error").description("Utiliser pour le message d'erreur").ignored(),
									fieldWithPath("modBy").description("Id de la personne connetee"),
                                                                        fieldWithPath("type").description("type du Survey")
                                                                        
                                                        )));

		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
         }
         
        @Test
	@Order(15)
	public void deleteClasseTest() throws JsonProcessingException, Exception {
		System.out.println("deleteClasse is called");
                List<Classe> classes = classeRepo.findAll();
                Classe classe = classes.get(classes.size()-1);
                Classe classe1 = new Classe();
                classe1.setId(classe.getId());
                classe1.setName("classe columbia");
                classe1.setModBy(1);
                classe1.setCreateDateTime(new Date());
                classe1.setModDateTime(new Date());
		System.out.println(classe1);
		String catSurveyJson = mapper.writeValueAsString(classe1);
		try {
			mockMvc.perform(post("/delete/{entity}","classe").contentType(MediaType.APPLICATION_JSON)
					.content(catSurveyJson)).andExpect(status().isOk())
					.andExpect(MockMvcResultMatchers.content().string("Success"))
					.andDo(document("deleteClasse",
							preprocessRequest(prettyPrint()), 
							preprocessResponse(prettyPrint()),
                                                        requestFields(fieldWithPath("id").description("Survey id").ignored(),
									fieldWithPath("name").description("Nom de la classe"),
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
}
