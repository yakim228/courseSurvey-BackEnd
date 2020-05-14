package com.ipnetinstitute.csc394.backend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.ipnetinstitute.csc394.backend.BackendSurveyApplication;
import com.ipnetinstitute.csc394.backend.entity.Student;
import com.ipnetinstitute.csc394.backend.entity.User;
import com.ipnetinstitute.csc394.backend.payload.request.LoginRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
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
class BackendSurveyApplicationTests extends SpringBootServletInitializer {

	private ObjectMapper mapper = new ObjectMapper();
	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@BeforeEach
	public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {

		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.apply(documentationConfiguration(restDocumentation)).build();
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
}
