package com.babak.gerimedicaassignment;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@ActiveProfiles("gerimedica-assignment-profile")
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GerimedicaAssignmentApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    void on_empty_database_when_upload_file_with_no_duplicate_should_store_all() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file",
                getClass().getResourceAsStream("/exercise.csv"));
        MockMultipartHttpServletRequestBuilder multipartRequest =
                MockMvcRequestBuilders.multipart("/upload");
        MvcResult result = mockMvc.perform(multipartRequest.file(file))
                .andExpect(status().isOk())
                .andReturn();
        JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
        Assertions.assertTrue(jsonObject.getBoolean("success"));
        Assertions.assertEquals(18, jsonObject.getInt("rowsUploaded"));
        Assertions.assertEquals(0, jsonObject.getInt("rowsFailed"));
    }

    @Test
    void on_uploading_csv_file_when_upload_file_with_duplicate_values_should_store_unique_values() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file",
                getClass().getResourceAsStream("/exercise.csv"));
        MockMultipartHttpServletRequestBuilder multipartRequest =
                MockMvcRequestBuilders.multipart("/upload");
        mockMvc.perform(multipartRequest.file(file))
                .andExpect(status().isOk());

        MockMultipartHttpServletRequestBuilder multipartRequest2 =
                MockMvcRequestBuilders.multipart("/upload");
        MockMultipartFile file2 = new MockMultipartFile("file",
                getClass().getResourceAsStream("/exercise_with_duplicate.csv"));

        MvcResult result = mockMvc.perform(multipartRequest2.file(file2))
                .andExpect(status().isOk())
                .andReturn();
        JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
        Assertions.assertTrue(jsonObject.getBoolean("success"));
        Assertions.assertEquals(1, jsonObject.getInt("rowsUploaded"));
        Assertions.assertEquals(2, jsonObject.getInt("rowsFailed"));
        Assertions.assertEquals("276885007", jsonObject.getJSONArray("duplicatedCodes").getString(0));
        Assertions.assertEquals("61086009", jsonObject.getJSONArray("duplicatedCodes").getString(1));
    }

    @Test
    void on_empty_database_when_upload_file_with_invalid_data_should_fail() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file",
                getClass().getResourceAsStream("/exercise_wrong_formatting.csv"));
        MockMultipartHttpServletRequestBuilder multipartRequest =
                MockMvcRequestBuilders.multipart("/upload");
        MvcResult result = mockMvc.perform(multipartRequest.file(file))
                .andExpect(status().isOk())
                .andReturn();
        JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
        Assertions.assertFalse(jsonObject.getBoolean("success"));
    }

    @Test
    void on_database_with_18_rows_when_fetch_all_should_return_all() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file",
                getClass().getResourceAsStream("/exercise.csv"));
        MockMultipartHttpServletRequestBuilder multipartRequest =
                MockMvcRequestBuilders.multipart("/upload");
        mockMvc.perform(multipartRequest.file(file))
                .andExpect(status().isOk());
        MvcResult result = mockMvc.perform(get("/fetchAll")).andReturn();
        JSONArray jsonArray = new JSONArray(result.getResponse().getContentAsString());
        Assertions.assertEquals(18, jsonArray.length());
    }

    @Test
    void on_database_with_18_rows_when_fetch_one_row_should_expected_result() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file",
                getClass().getResourceAsStream("/exercise.csv"));
        MockMultipartHttpServletRequestBuilder multipartRequest =
                MockMvcRequestBuilders.multipart("/upload");
        mockMvc.perform(multipartRequest.file(file))
                .andExpect(status().isOk());
        MvcResult result = mockMvc.perform(get("/fetchByCode").param("code", "61086009")).andReturn();
        JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
        Assertions.assertEquals("ZIB", jsonObject.getString("source"));
        Assertions.assertEquals("ZIB001", jsonObject.getString("codeListCode"));
        Assertions.assertEquals("61086009", jsonObject.getString("code"));
        Assertions.assertEquals("Polsslag onregelmatig", jsonObject.getString("displayValue"));
        Assertions.assertEquals("01-01-2019", ((Date)jsonObject.get("fromDate")));
        Assertions.assertEquals("2", jsonObject.getString("sortingPriority"));
    }

}
