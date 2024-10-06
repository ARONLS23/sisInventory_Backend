package Controller;

import com.company.inventory.controllers.CategoryController;
import com.company.inventory.models.Category;
import com.company.inventory.response.CategoryResponseRest;
import com.company.inventory.services.ICategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CategoryControllerTest {
    @InjectMocks
    CategoryController categoryController;

    @Mock
    private ICategoryService categoryService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveTest(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Category category = new Category();
        category.setId(Long.valueOf(1));
        category.setName("Lacteos");
        category.setDescription("productos derivados de la leche");

        when(categoryService.save(any(Category.class))).thenReturn(
                new ResponseEntity<CategoryResponseRest>(HttpStatus.OK)
        );

        ResponseEntity<CategoryResponseRest> response = categoryController.saveCategory(category);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

}
