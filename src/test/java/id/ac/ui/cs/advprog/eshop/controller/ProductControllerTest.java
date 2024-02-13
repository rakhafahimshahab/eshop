package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Captor
    private ArgumentCaptor<Product> productArgumentCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void createProductPost_ShouldCreateProductAndRedirect() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/product/create")
                        .param("name", "Test Product")
                        .param("price", "100.0"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("list"));

        Mockito.verify(productService).create(productArgumentCaptor.capture());
        Product capturedProduct = productArgumentCaptor.getValue();
        // You can add assertions here to verify the properties of the 'capturedProduct'
    }

    @Test
    void editProductPage_ShouldDisplayEditProductPage() throws Exception {
        Product product = new Product(); // Assume this is your product object
        BDDMockito.given(productService.get("1")).willReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.get("/product/edit/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("EditProduct"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("product"));
    }

    @Test
    void editProductPost_ShouldEditProductAndRedirect() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/product/edit")
                        .param("id", "1")
                        .param("name", "Updated Product")
                        .param("price", "200.0"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("list"));

        Mockito.verify(productService).edit(productArgumentCaptor.capture());
        // Add assertions here if needed
    }

    @Test
    void deleteProduct_ShouldDeleteProductAndRedirect() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product/delete/1"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/product/list"));

        Mockito.verify(productService).delete(productArgumentCaptor.capture());
        // Add assertions here if needed
    }

}
