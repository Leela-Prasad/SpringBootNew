package com.springboot.controllers;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.hasProperty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.springboot.domain.Product;
import com.springboot.service.ProductService;

public class ProductControllerTest {

	@Mock
	private ProductService productService;
	
	@InjectMocks
	private ProductController productController;
	
	@Captor
	ArgumentCaptor<Product> productCaptor;
	
	private MockMvc mockMvc;
	
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc=MockMvcBuilders.standaloneSetup(productController).build();
	}
	
	@Test
	public void testListProducts() throws Exception{
		List<Product> products = new ArrayList<>();
		products.add(new Product());
		products.add(new Product());
		
		when(productService.listAll()).thenReturn((List) products);
		mockMvc.perform(get("/product/list"))
				.andExpect(status().isOk())
				.andExpect(view().name("product/products"))
				.andExpect(model().attribute("products", hasSize(2)));
		
	}
	
	@Test
	public void testGetProduct() throws Exception {
		//Integer id=1;
		when(productService.getById(anyInt())).thenReturn(new Product());
		
		mockMvc.perform(get("/product/show/1"))
				.andExpect(status().isOk())
				.andExpect(view().name("product/product"))
				.andExpect(model().attribute("product", instanceOf(Product.class)));
	}

	@Test
	public void testNewProduct() throws Exception{
		
		verifyZeroInteractions(productService);
		mockMvc.perform(get("/product/new"))
		       .andExpect(status().isOk())
		       .andExpect(view().name("product/productform"))
		       .andExpect(model().attribute("product", instanceOf(Product.class)));
		
	}
	
	@Test
	public void testEdit() throws Exception {
		
		when(productService.getById(anyInt())).thenReturn(new Product());
		mockMvc.perform(get("/product/edit/1"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("product/productform"))
			   .andExpect(model().attribute("product", instanceOf(Product.class)));
		
	}
	
	@Test
	public void testSaveOrUpdate() throws Exception {	
		
		Integer id=1;
		String description="test description";
		BigDecimal price=new BigDecimal("25.6");
		String imageUrl="test url";
		
		Product product = new Product();
		product.setId(id);
		product.setDescription(description);
		product.setPrice(price);
		product.setImageUrl(imageUrl);
		
		when(productService.saveOrUpdate(any(Product.class))).thenReturn(product);
		
		mockMvc.perform(post("/product")
			   .param("id","1")
			   .param("description", "test description")
			   .param("price","25.6")
			   .param("imageUrl", "test url"))
			   .andExpect(status().is3xxRedirection())
			   .andExpect(view().name("redirect:/product/show/1"))
			   .andExpect(model().attribute("product", instanceOf(Product.class)))
			   .andExpect(model().attribute("product", hasProperty("id",is(id))))
			   .andExpect(model().attribute("product", hasProperty("description",is(description))))
			   .andExpect(model().attribute("product", hasProperty("price",is(price))))
			   .andExpect(model().attribute("product", hasProperty("imageUrl",is(imageUrl))));
		
		verify(productService).saveOrUpdate(productCaptor.capture());
		assertThat(product.getId(),is(productCaptor.getValue().getId()));
		assertThat(product.getDescription(),is(productCaptor.getValue().getDescription()));
		assertThat(product.getPrice(),is(productCaptor.getValue().getPrice()));
		assertThat(product.getImageUrl(), is(productCaptor.getValue().getImageUrl()));
		
	}
	
	@Test
	public void testDelete() throws Exception {
		
		mockMvc.perform(get("/product/delete/1"))
			   .andExpect(status().is3xxRedirection())
			   .andExpect(view().name("redirect:/product/list"));
		
		verify(productService,times(1)).delete(anyInt());
	}
}
