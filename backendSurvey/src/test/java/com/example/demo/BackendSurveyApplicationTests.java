package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.ipnetinstitute.csc394.backend.BackendSurveyApplication;
import com.ipnetinstitute.csc394.backend.dao.BaseEntityRepository;
import com.ipnetinstitute.csc394.backend.dao.CategorieSurveyQuestionEntityRepository;
import com.ipnetinstitute.csc394.backend.entity.CatSurvey;
import com.ipnetinstitute.csc394.backend.entity.CatSurveyQuestion;
import com.ipnetinstitute.csc394.backend.entity.Classe;
import com.ipnetinstitute.csc394.backend.entity.Course;
import com.ipnetinstitute.csc394.backend.entity.Question;
import com.ipnetinstitute.csc394.backend.entity.Student;
import com.ipnetinstitute.csc394.backend.entity.StudentSurvey;
import com.ipnetinstitute.csc394.backend.entity.Subject;
import com.ipnetinstitute.csc394.backend.entity.Survey;
import com.ipnetinstitute.csc394.backend.entity.Term;
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
        
        @Autowired
        CategorieSurveyQuestionEntityRepository csqRepo;
        
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
	 @Order(7)
         public void getAllUsersTest() throws JsonProcessingException, Exception {
            System.out.println("getAllUsersTest is called"); try {
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
	 @Order(9)
         public void getAllCatSurveyTest() throws JsonProcessingException, Exception {
            System.out.println("getCatSurveyTest is called"); try {
                mockMvc.perform(get("/getAll/{entity}","cat_survey").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(document("getAllCatSurvey",
                preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()))); 
             } catch (Exception e) { e.printStackTrace(); throw (e); }
         }
         
         @Test 
	 @Order(10)
         public void countCatSurveyTest() throws JsonProcessingException, Exception {
            System.out.println("countCatSurvey is called"); try {
                mockMvc.perform(get("/count/{entity}","cat_survey").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(document("countCatSurvey",
                preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()))); 
             } catch (Exception e) { e.printStackTrace(); throw (e); }
         }
        @Test
	@Order(11)
	public void deleteCatSurveyTest() throws JsonProcessingException, Exception {
		System.out.println("deleteCatSurvey is called");
                
                List<CatSurvey> catSurveys = catSurveyRepo.findAll();
                CatSurvey catSurvey = catSurveys.get(catSurveys.size()-1);
                CatSurvey catSurvey1 = new CatSurvey();
                catSurvey1.setModBy(1);
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
	@Order(12)
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
        
//        @Test
//         @Order(13)
//         public void saveSurveyTest() throws JsonProcessingException, Exception {
//            System.out.println("SaveSurveyTest is called");
//            Survey survey = new Survey();
//            survey.setTitle("survey standard 1");
//            survey.setDescription("survey d'evaluation");
//            survey.setModBy(1);
//            survey.setCreateDateTime(new Date());
//            survey.setModDateTime(new Date());
//            survey.setBeginDate(new Date());
//            survey.setBeginMessage("ouverture du survey 1");
//            survey.setBeginDate(new Date());
//            survey.setEndMessage("fermeture du survey 1");
//            survey.setStatus(0);
//            
//            CatSurvey catSurvey1 = new CatSurvey();
//            catSurvey1.setName("Survey du 2eme semestre");
//            catSurvey1.setDescription("Ce survey compte evaluer les professeurs");
//            catSurvey1.setModBy(1);
//            catSurvey1.setCreateDateTime(new Date());
//            catSurvey1.setModDateTime(new Date());
//            catSurveyRepo.save(catSurvey1);
//            
//            CatSurvey c = new CatSurvey();
//            c.setName("Cat 1");
//            c.setDescription("categorie 1A");
//            c.setCreateDateTime(new Date());
//            c.setModDateTime(new Date());
//            c.setModBy(1);
//            
//            survey.setCatSurvey(c);
//            
//            List<Course> courses = courseRepo.findAll();
//            Course course = courses.get(courses.size()-1);
//            survey.setCourse(course);
//            
//            String surveyJson = mapper.writeValueAsString(survey);
//            System.out.println(survey);
//		try {
//			mockMvc.perform(post("/save/{entity}", "survey").contentType(MediaType.APPLICATION_JSON).content(surveyJson))
//					.andExpect(status().isOk())
//					.andDo(document("saveSurvey", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()),
//							requestFields(fieldWithPath("id").description("Survey id").ignored(),
//									fieldWithPath("title").description("Title du survey"),
//									fieldWithPath("description").description("la description du survey"),
//									fieldWithPath("beginMessage").description("Message de debut du survey"),
//                                                                        fieldWithPath("beginDate").description("Date de debut du survey"),
//									fieldWithPath("endMessage").description("Message de fin du survey"),
//									fieldWithPath("endDate").description("Date de fin du survey"),
//                                                                        fieldWithPath("status").description("status du survey"),
//                                                                        fieldWithPath("createDateTime").description("Date de creation").ignored(),
//									fieldWithPath("modDateTime").description("Date de modification").ignored(),
//									fieldWithPath("error").description("Utiliser pour le message d'erreur").ignored(),
//									fieldWithPath("modBy").description("Id de la personne connetee"),
//                                                                        fieldWithPath("type").description("type du Survey")
//                                                                        
//                                                        )));
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw (e);
//		}
//         }
         
//        @Test
//	@Order(14)
//	public void deleteSurveyTest() throws JsonProcessingException, Exception {
//		System.out.println("deleteSurvey is called");
//                List<Survey> surveys = surveyRepo.findAll();
//                Survey survey = surveys.get(surveys.size()-1);
//                Survey survey1 = new Survey();
//                survey1.setId(survey.getId());
//                survey1.setTitle("survey standard 1");
//                survey1.setDescription("survey d'evaluation");
//                survey1.setModBy(1);
//                survey1.setCreateDateTime(new Date());
//                survey1.setModDateTime(new Date());
//                survey1.setBeginDate(new Date());
//                survey1.setBeginMessage("ouverture du survey 1");
//                survey1.setBeginDate(new Date());
//                survey1.setEndMessage("fermeture du survey 1");
//                survey1.setStatus(0);
//		System.out.println(survey1);
//		String catSurveyJson = mapper.writeValueAsString(survey1);
//		try {
//			mockMvc.perform(post("/delete/{entity}","survey").contentType(MediaType.APPLICATION_JSON)
//					.content(catSurveyJson)).andExpect(status().isOk())
//					.andExpect(MockMvcResultMatchers.content().string("Success"))
//					.andDo(document("deleteSurvey",
//							preprocessRequest(prettyPrint()), 
//							preprocessResponse(prettyPrint()),
//                                                        requestFields(fieldWithPath("id").description("Survey id").ignored(),
//									fieldWithPath("title").description("Title du survey"),
//									fieldWithPath("description").description("la description du survey"),
//                                                                        fieldWithPath("beginMessage").description("Message de debut du survey"),
//                                                                        fieldWithPath("beginDate").description("Date de debut du survey"),
//									fieldWithPath("endMessage").description("Message de fin du survey"),
//									fieldWithPath("endDate").description("Date de fin du survey"),
//                                                                        fieldWithPath("status").description("status du survey"),
//                                                                        fieldWithPath("createDateTime").description("Date de creation").ignored(),
//									fieldWithPath("modDateTime").description("Date de modification").ignored(),
//									fieldWithPath("error").description("Utiliser pour le message d'erreur").ignored(),
//									fieldWithPath("modBy").description("Id de la personne connetee"),
//                                                                        fieldWithPath("type").description("type du CatSurvey"))));
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw (e);
//		}
//	}
//        @Test
//        @Order(15)
//         public void getAllSurveyTest() throws JsonProcessingException, Exception {
//            System.out.println("getAllSurveyTest is called"); try {
//                mockMvc.perform(get("/getAll/{entity}","survey").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk()).andDo(document("getAllSurvey",
//                preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()))); 
//             } catch (Exception e) { e.printStackTrace(); throw (e); }
//         }
         
//         @Test 
//	 @Order(16)
//         public void countSurveyTest() throws JsonProcessingException, Exception {
//            System.out.println("countSurvey is called"); try {
//                mockMvc.perform(get("/count/{entity}","survey").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk()).andDo(document("countSurvey",
//                preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()))); 
//             } catch (Exception e) { e.printStackTrace(); throw (e); }
//         }
         
//         @Test 
//	 @Order(17)
//         public void getAllSurveyByStudentTest() throws JsonProcessingException, Exception {
//            System.out.println("getAllSurveyByStudent is called"); try {
//                mockMvc.perform(get("/getSurveybyCategoryAndCourse/{CategoryID}/{CourseID}",2,1).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk()).andDo(document("getAllSurveyByStudent",
//                preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()))); 
//             } catch (Exception e) { e.printStackTrace(); throw (e); }
//         }
         
        @Test
	@Order(18)
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
	@Order(19)
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
        
     @Test 
	 @Order(20)
         public void getClasseTest() throws JsonProcessingException, Exception {
            System.out.println("getClasseTest is called"); try {
                mockMvc.perform(get("/getAll/{entity}","classe").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(document("getAllClasse",
                preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()))); 
             } catch (Exception e) { e.printStackTrace(); throw (e); }
         }
         
         @Test 
	 @Order(21)
         public void countClasseTest() throws JsonProcessingException, Exception {
            System.out.println("countCatSurvey is called"); try {
                mockMvc.perform(get("/count/{entity}","classe").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(document("countClasse",
                preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()))); 
             } catch (Exception e) { e.printStackTrace(); throw (e); }
         }
//         ==================Question=====================
         @Test
	@Order(22)
	public void saveQuestionTest() throws JsonProcessingException, Exception {
            System.out.println("saveQuestionTest is called");
            Question q = new Question();
            q.setTitle("Tenue vestimentaire de professeur");
            q.setId(2);
            q.setCreateDateTime(new Date());
            q.setModDateTime(new Date());
            q.setModBy(1);
            String qJson = mapper.writeValueAsString(q);
            System.out.println(qJson);
		try {
			mockMvc.perform(post("/save/{entity}", "question").contentType(MediaType.APPLICATION_JSON).content(qJson))
					.andExpect(status().isOk())
					.andDo(document("saveQuestion", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()),
							requestFields(fieldWithPath("id").description("Question id").ignored(),
                                                                        fieldWithPath("title").description("Titre de la question").ignored(),
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
        
        @Autowired
        private BaseEntityRepository<Question> quesRepo;
        @Test
	@Order(23)
	public void deleteQuestionTest() throws JsonProcessingException, Exception {
		System.out.println("deleteQuestion is called");
                List<Question> qs = quesRepo.findAll();
                Question q = qs.get(qs.size()-1);
//                CatSurveyQuestion csq1 = new CatSurveyQuestion();
//                csq1.setId(csq.getId());
//                csq1.setModBy(1);
//                csq1.setQuestion(csq.getQuestion());
//                csq1.setCreateDateTime(new Date());
//                csq1.setModDateTime(new Date());
//		
//		String csqJson = mapper.writeValueAsString(csq1);
		try {
			mockMvc.perform(get("/delete/{entity}/{id}","question",q.getId()).contentType(MediaType.APPLICATION_JSON)
					).andExpect(status().isOk())
					.andExpect(MockMvcResultMatchers.content().string("Success"))
					.andDo(document("deleteQuestion",
							preprocessRequest(prettyPrint()), 
							preprocessResponse(prettyPrint())
                                                        ));

		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}
        
     @Test 
	 @Order(24)
         public void getAllQuestionTest() throws JsonProcessingException, Exception {
            System.out.println("getAllQuestionTest is called"); try {
                mockMvc.perform(get("/getAll/{entity}","question").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(document("getAllQuestion",
                preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()))); 
             } catch (Exception e) { e.printStackTrace(); throw (e); }
         }
         
         @Test 
	 @Order(25)
         public void countQuestionTest() throws JsonProcessingException, Exception {
            System.out.println("countQuestion is called"); try {
                mockMvc.perform(get("/count/{entity}","question").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(document("countQuestion",
                preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()))); 
             } catch (Exception e) { e.printStackTrace(); throw (e); }
         }
         
//         =======================Subject========================
         
         
          @Test
	@Order(22)
	public void saveSubjectTest() throws JsonProcessingException, Exception {
            System.out.println("saveSubjectTest is called");
            Subject s = new Subject();
            s.setName("CSC394");
            s.setCode("AGT40");
            s.setId(2);
            s.setCreateDateTime(new Date());
            s.setModDateTime(new Date());
            s.setModBy(1);
            String sJson = mapper.writeValueAsString(s);
            System.out.println(sJson);
		try {
			mockMvc.perform(post("/save/{entity}", "subject").contentType(MediaType.APPLICATION_JSON).content(sJson))
					.andExpect(status().isOk())
					.andDo(document("saveSubject", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()),
							requestFields(fieldWithPath("id").description("Subject id").ignored(),
                                                                        fieldWithPath("code").description("Code du sujet").ignored(),
                                                                        fieldWithPath("name").description("Nom du sujet").ignored(),
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
        
        @Autowired
        private BaseEntityRepository<Subject> subRepo;
        @Test
	@Order(23)
	public void deleteSubjectTest() throws JsonProcessingException, Exception {
		System.out.println("deleteSubject is called");
                List<Subject> ss = subRepo.findAll();
                Subject s = ss.get(ss.size()-1);
		try {
			mockMvc.perform(get("/delete/{entity}/{id}","subject",s.getId()).contentType(MediaType.APPLICATION_JSON)
					).andExpect(status().isOk())
					.andExpect(MockMvcResultMatchers.content().string("Success"))
					.andDo(document("deleteSubject",
							preprocessRequest(prettyPrint()), 
							preprocessResponse(prettyPrint())
                                                        ));

		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}
        
     @Test 
	 @Order(24)
         public void getAllSubjectTest() throws JsonProcessingException, Exception {
            System.out.println("getAllSubjectTest is called"); try {
                mockMvc.perform(get("/getAll/{entity}","subject").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(document("getAllSubject",
                preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()))); 
             } catch (Exception e) { e.printStackTrace(); throw (e); }
         }
         
         @Test 
	 @Order(25)
         public void countSubjectTest() throws JsonProcessingException, Exception {
            System.out.println("countSubject is called"); try {
                mockMvc.perform(get("/count/{entity}","subject").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(document("countSubject",
                preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()))); 
             } catch (Exception e) { e.printStackTrace(); throw (e); }
         }
         
         
         //         =======================Term========================
         
         
          @Test
	@Order(22)
	public void saveTermTest() throws JsonProcessingException, Exception {
            System.out.println("saveTermTest is called");
            Term s = new Term();
            s.setName("Semestre 1");
            s.setCode("SHI40");
            s.setId(2);
            s.setCreateDateTime(new Date());
            s.setModDateTime(new Date());
            s.setModBy(1);
            String sJson = mapper.writeValueAsString(s);
            System.out.println(sJson);
		try {
			mockMvc.perform(post("/save/{entity}", "term").contentType(MediaType.APPLICATION_JSON).content(sJson))
					.andExpect(status().isOk())
					.andDo(document("saveTerm", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()),
							requestFields(fieldWithPath("id").description("Term id").ignored(),
                                                                        fieldWithPath("code").description("Code du semestre").ignored(),
                                                                        fieldWithPath("name").description("Nom du semestre").ignored(),
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
        
        @Autowired
        private BaseEntityRepository<Term> termRepo;
        @Test
	@Order(23)
	public void deleteTermTest() throws JsonProcessingException, Exception {
		System.out.println("deleteTerm is called");
                List<Term> ss = termRepo.findAll();
                Term s = ss.get(ss.size()-1);
		try {
			mockMvc.perform(get("/delete/{entity}/{id}","term",s.getId()).contentType(MediaType.APPLICATION_JSON)
					).andExpect(status().isOk())
					.andExpect(MockMvcResultMatchers.content().string("Success"))
					.andDo(document("deleteTerm",
							preprocessRequest(prettyPrint()), 
							preprocessResponse(prettyPrint())
                                                        ));

		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}
        
     @Test 
	 @Order(24)
         public void getAllTermTest() throws JsonProcessingException, Exception {
            System.out.println("getAllTermTest is called"); try {
                mockMvc.perform(get("/getAll/{entity}","term").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(document("getAllTerm",
                preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()))); 
             } catch (Exception e) { e.printStackTrace(); throw (e); }
         }
         
         @Test 
	 @Order(25)
         public void countTermTest() throws JsonProcessingException, Exception {
            System.out.println("countTerm is called"); try {
                mockMvc.perform(get("/count/{entity}","term").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(document("countTerm",
                preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()))); 
             } catch (Exception e) { e.printStackTrace(); throw (e); }
         }
         
         //         =======================Course========================
         
         
//          @Test
//	@Order(22)
//	public void saveCourseTest() throws JsonProcessingException, Exception {
//            System.out.println("saveCourseTest is called");
//            Course s = new Course();
//            s.setId(2);
//            s.setCreateDateTime(new Date());
//            s.setModDateTime(new Date());
//            s.setModBy(1);
//            
//            List<Classe> qs = classeRepo.findAll();
//            Classe q = qs.get(qs.size()-1);
//            s.setClasse(q);
//            String sJson = mapper.writeValueAsString(s);
//            System.out.println(sJson);
//		try {
//			mockMvc.perform(post("/save/{entity}", "course").contentType(MediaType.APPLICATION_JSON).content(sJson))
//					.andExpect(status().isOk())
//					.andDo(document("saveCourse", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()),
//							requestFields(fieldWithPath("id").description("Course id").ignored(),
//                                                                        fieldWithPath("createDateTime").description("Date de creation").ignored(),
//									fieldWithPath("modDateTime").description("Date de modification").ignored(),
//									fieldWithPath("error").description("Utiliser pour le message d'erreur").ignored(),
//									fieldWithPath("modBy").description("Id de la personne connetee"),
//                                                                        fieldWithPath("type").description("type du Survey") 
//                                                        )));
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw (e);
//		}
//         }
        
        @Autowired
        private BaseEntityRepository<Course> courseRep;
        @Test
	@Order(23)
	public void deleteCourseTest() throws JsonProcessingException, Exception {
		System.out.println("deleteCourse is called");
                List<Course> ss = courseRepo.findAll();
                Course s = ss.get(ss.size()-1);
		try {
			mockMvc.perform(get("/delete/{entity}/{id}","course",s.getId()).contentType(MediaType.APPLICATION_JSON)
					).andExpect(status().isOk())
					.andExpect(MockMvcResultMatchers.content().string("Success"))
					.andDo(document("deleteCourse",
							preprocessRequest(prettyPrint()), 
							preprocessResponse(prettyPrint())
                                                        ));

		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}
        
     @Test 
	 @Order(24)
         public void getAllCourseTest() throws JsonProcessingException, Exception {
            System.out.println("getAllCourseTest is called"); try {
                mockMvc.perform(get("/getAll/{entity}","course").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(document("getAllCourse",
                preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()))); 
             } catch (Exception e) { e.printStackTrace(); throw (e); }
         }
         
         @Test 
	 @Order(25)
         public void countCourseTest() throws JsonProcessingException, Exception {
            System.out.println("countCourse is called"); try {
                mockMvc.perform(get("/count/{entity}","course").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(document("countCourse",
                preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()))); 
             } catch (Exception e) { e.printStackTrace(); throw (e); }
         }
         
         
           @Test
	@Order(30)
	public void saveStudentSurveyTest() throws JsonProcessingException, Exception {
            System.out.println("saveStudentSurveyTest is called");
               StudentSurvey s = new StudentSurvey();
            s.setIs_na((short)5);
            s.setComments("reponse");
            s.setId_question(1);
            s.setId_student(1);
            s.setId_survey(1);
            s.setRating(3);
            s.setId(2);
            s.setCreateDateTime(new Date());
            s.setModDateTime(new Date());
            s.setModBy(1);
            String sJson = mapper.writeValueAsString(s);
            System.out.println(sJson);
		try {
			mockMvc.perform(post("/save/{entity}", "student_survey").contentType(MediaType.APPLICATION_JSON).content(sJson))
					.andExpect(status().isOk())
					.andDo(document("saveStudentSurvey", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()),
							requestFields(fieldWithPath("id").description("StudentSurvey id").ignored(),
                                                                        fieldWithPath("id_question").description("id de la question").ignored(),
                                                                        fieldWithPath("id_student").description("id de l'eleve").ignored(),
                                                                        fieldWithPath("id_survey").description("id du survey").ignored(),
                                                                        fieldWithPath("comments").description("commentaire").ignored(),
                                                                        fieldWithPath("is_na").description("na").ignored(),
                                                                        fieldWithPath("rating").description("id du rating").ignored(),
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
        
        @Autowired
        private BaseEntityRepository<StudentSurvey> stdsRepo;
        @Test
	@Order(31)
	public void deleteStudentSurveyTest() throws JsonProcessingException, Exception {
		System.out.println("deleteStudentSurvey is called");
                List<StudentSurvey> ss = stdsRepo.findAll();
                StudentSurvey s = ss.get(ss.size()-1);
		try {
			mockMvc.perform(get("/delete/{entity}/{id}","student_survey",s.getId()).contentType(MediaType.APPLICATION_JSON)
					).andExpect(status().isOk())
					.andExpect(MockMvcResultMatchers.content().string("Success"))
					.andDo(document("deleteStudentSurvey",
							preprocessRequest(prettyPrint()), 
							preprocessResponse(prettyPrint())
                                                        ));

		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}
        
     @Test 
	 @Order(32)
         public void getAllStudentSurveysTest() throws JsonProcessingException, Exception {
            System.out.println("getAllStudentSurveyTest is called"); try {
                mockMvc.perform(get("/getAll/{entity}","student_survey").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(document("getAllStudentSurveys",
                preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()))); 
             } catch (Exception e) { e.printStackTrace(); throw (e); }
         }
         
         @Test 
	 @Order(25)
         public void countStudentSurveyTest() throws JsonProcessingException, Exception {
            System.out.println("countStudentSurvey is called"); try {
                mockMvc.perform(get("/count/{entity}","student_survey").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(document("countStudentSurvey",
                preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()))); 
             } catch (Exception e) { e.printStackTrace(); throw (e); }
         }
}
