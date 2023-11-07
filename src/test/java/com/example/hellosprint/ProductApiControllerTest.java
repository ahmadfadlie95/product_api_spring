package com.example.hellosprint;
import com.example.hellosprint.controllers.ProductController;
import com.example.hellosprint.data.ProductRequest;
import com.example.hellosprint.models.Product;
import com.example.hellosprint.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductApiControllerTest {
    @Autowired private MockMvc mockMvc; //virtual postman yg akan digunakan untuk test api kita
    @Autowired private ObjectMapper objectMapper; //object to json transformer(json yang diletakkan dibody)
    @MockBean private ProductService productService;
    final static String END_POINT_PATH = "/api/products";
    @Test
    public void testAddShouldReturn400BadRequest() throws Exception {
        Product product = new Product(); //bila set name,desc,price kosong, expect ada error 400 (test case 1)
        product.setName("");
        product.setDescription("");
        product.setPrice(null);

        String requestBody = objectMapper.writeValueAsString(product);

        mockMvc.perform(post(END_POINT_PATH).contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }
    @Test
    public void testAddShouldReturn201Created() throws Exception {
        Product product = new Product();
        product.setName("Kopi O");
        product.setDescription("Gula Lebih");
        product.setPrice(2.50);
        product.setImageUrl("https://www.google.com");

        Mockito.when(productService.createProduct(product.getDto())).thenReturn(product.getDto()); //expect ada product dalam body

        String requestBody = objectMapper.writeValueAsString(product);

        mockMvc.perform(post(END_POINT_PATH).contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andDo(print());

    }
    @Test
    public void testGetShouldReturn404NotFound() throws Exception {
        Long productId = 123L;
        String requestURI = END_POINT_PATH + "/" + productId;

        //Mockito.when(productService.findById(productId)).(ProductNotFoundException.class);

        mockMvc.perform(get(requestURI))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
    @Test
    public void testGetShouldReturn200OK() throws Exception {
        Long productId = 123L;
        String requestURI = END_POINT_PATH + "/" + productId;
        String name = "Kopi O";

        Product product = new Product();
        product.setName(name);
        product.setDescription("Air Panas");
        product.setPrice(3.5);
        product.setImageUrl("www.google.com");
        product.setId(productId);

        Mockito.when(productService.findById(productId)).thenReturn(Optional.of(product));

        mockMvc.perform(get(requestURI))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name", is(name)))
                .andDo(print());
    }
    @Test
    public void testListShouldReturn204NoContent() throws Exception {
        Mockito.when(productService.getAllProducts()).thenReturn(new ArrayList<>());

        mockMvc.perform(get(END_POINT_PATH))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
    @Test
    public void testListShouldReturn200OK() throws Exception {
        Product product = new Product();
        product.setName("Kopi O");
        product.setDescription("Air Panas");
        product.setPrice(3.5);
        product.setImageUrl("www.google.com");
        product.setId(1L);

        Product product2 = new Product();
        product2.setName("Teh O");
        product2.setDescription("Air Panas");
        product2.setPrice(2.5);
        product2.setImageUrl("www.google.com");
        product2.setId(2L);

        List<ProductRequest> listProduct = List.of(product.getDto(), product2.getDto());

        Mockito.when(productService.getAllProducts()).thenReturn(listProduct);

        mockMvc.perform(get(END_POINT_PATH))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].name", is("Kopi O")))
                .andExpect(jsonPath("$[1].name", is("Teh O")))
                .andDo(print());
    }
    @Test
    public void testUpdateShouldReturn404NotFound() throws Exception {
        Long productId = 123L;
        String requestURI = END_POINT_PATH + "/" + productId;

        Product product = new Product();
        product.setName("Kopi O");
        product.setDescription("Air Panas");
        product.setPrice(3.5);
        product.setImageUrl("www.google.com");
        product.setId(1L);

        //Mockito.when(productService.updateProduct(product)).thenThrow(UserNotFoundException.class);

        String requestBody = objectMapper.writeValueAsString(product);

        mockMvc.perform(put(requestURI).contentType("application/json").content(requestBody))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
    @Test
    public void testUpdateShouldReturn400BadRequest() throws Exception {
        Long productId = 123L;
        String requestURI = END_POINT_PATH + "/" + productId;

        Product product = new Product();
        product.setName("Kopi O");
        product.setDescription("Air Panas");
        product.setPrice(3.5);
        product.setImageUrl("www.google.com");
        product.setId(1L);

        String requestBody = objectMapper.writeValueAsString(product);

        mockMvc.perform(put(requestURI).contentType("application/json").content(requestBody))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }
    @Test
    public void testUpdateShouldReturn200OK() throws Exception {
        Long productId = 123L;
        String requestURI = END_POINT_PATH + "/" + productId;

        Product product = new Product();
        product.setName("Kopi O");
        product.setDescription("Air Panas");
        product.setPrice(3.5);
        product.setImageUrl("www.google.com");
        product.setId(1L);

        //Mockito.when(productService.updateProduct(product)).thenReturn(product);

        String requestBody = objectMapper.writeValueAsString(product);

        mockMvc.perform(put(requestURI).contentType("application/json").content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Kopi O")))
                .andDo(print());
    }
    @Test
    public void testDeleteShouldReturn404NotFound() throws Exception {
        Long productId = 123L;
        String requestURI = END_POINT_PATH + "/" + productId;

        Mockito.doThrow(ProductNotFoundException.class).when(productService).deleteProduct(productId);;

        mockMvc.perform(delete(requestURI))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
    @Test
    public void testDeleteShouldReturn200OK() throws Exception {
        Long productId = 123L;
        String requestURI = END_POINT_PATH + "/" + productId;

        Mockito.doNothing().when(productService).deleteProduct(productId);;

        mockMvc.perform(delete(requestURI))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}
