package com.xiaobitipao.sample.spring4.web.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import com.xiaobitipao.sample.spring4.model.User;
import com.xiaobitipao.sample.spring4.repository.UserRepository;
import com.xiaobitipao.sample.spring4.service.UserService;
import com.xiaobitipao.sample.spring4.service.UserServiceImpl;
import com.xiaobitipao.sample.spring4.web.controller.UserController;

public class UserControllerTest {

    /**
     * <pre>
     * 注解 @Mock 用于创建一个 Mock，在测试方法中可以通过 when 为 Interface 提供一个虚拟的实现
     * </pre>
     */
    @Mock
    private UserRepository mockUserRepository;

    /**
     * <pre>
     * 注解 @InjectMocks 用于创建一个实例，其余用到 @Mock 注解创建的对象将被注入到该实例中。
     * 也就是说标注了 @InjectMocks 的对象需要使用标注了 @mock 注解的对象。
     * </pre>
     */
    @InjectMocks
    private UserService userService = new UserServiceImpl();

    /**
     * <pre>
     * 注解 @InjectMocks 用于创建一个实例，
     * 使得 UserController 中用到的 UserRepository 实例不是真实的，而是在测试方法中通过 when 虚拟出来的。
     * </pre>
     */
    @InjectMocks
    private UserController controller = new UserController();

    private MockMvc mockMvc;

    /**
     * <pre>
     * 必须使用 @RunWith(MockitoJUnitRunner.class) 标注测试类，或者使用 Mockito.initMocks(this) 进行 mocks 的初始化和注入。
     * 这里是使用第二种。
     * </pre>
     */
    @Before
    public void setUp() {

        // 对标注 @InjectMocks 注解的实例进行初始化和注入
        // 必须使用 @RunWith(MockitoJUnitRunner.class) 标注测试类，
        // 或者使用 Mockito.initMocks(this) 进行 mocks 的初始化和注入。
        // 这里是使用第二种。
        initMocks(this);

        // 创建 MockMvc
        // 与 HomeControllerTest 不同，这里不能直接 build，而是需要调用一下 setSingleView，
        // 原因是 HomeController 中的视图名（home）和控制器的 RequestMapping（/ or /homepage）不同，
        // UserController 中的视图名（user）和控制器的 RequestMapping（/user）相同，
        // 当你没有声明 ViewResolver 时，spring 会给你注册一个默认的 ViewResolver，就是 JstlView 的实例。
        // 它通过 RequestDispatcher 寻找资源（视图），不过这个资源也可能是 Servlet。
        // 也就是说，Controller 中方法返回字符串（视图名），也可能会解析成 Servlet，
        // 当你的请求路径与视图名相同时，就会发生死循环。
        // 实际上，这里设置的 InternalResourceView 参数与实际路径是无关紧要的，设置任意值都可以，
        // 但是这里为了清晰，设置成和实际路径相同。
        // http://blog.csdn.net/wthfeng/article/details/52742830
        // http://stackoverflow.com/questions/18813615/how-to-avoid-the-circular-view-path-exception-with-spring-mvc-test
        // mockMvc = standaloneSetup(controller).build();
        mockMvc = standaloneSetup(controller).setSingleView(new InternalResourceView("/WEB-INF/views/user.jsp")).build();
    }

    @Test
    public void testMockAndInjectMocks() throws Exception {

        // 创建 expected User
        User expectedUser = createUser();

        // 对 UserRepository 进行 Mock
        // 当调用 UserRepository.findUser 方法时返回 expected User
        when(mockUserRepository.findUser(anyString())).thenReturn(expectedUser);

        // 由于标注了 @Mock 注解的 mockUserRepository 会被注入到标注了 @InjectMocks 注解的
        // userService 中，
        // 当调用 userService.findUser 方法时，
        // userService.findUser 方法内部使用的 UserRepository 实例将是本 test 方法内被 Mock 的
        // mockUserRepository.findUser
        User result = userService.findUser(anyString());

        // 断言两个实例是同一个
        assertThat("result", result, is(sameInstance(expectedUser)));

        // 确认方法被调用了（参数也相同）
        verify(mockUserRepository).findUser(anyString());
    }

    @Test
    public void testUser() throws Exception {

        // 创建 expected User
        User expectedUser = createUser();

        // 创建 UserRepository 接口的 Mock 实现，
        // 这个实现会从他的 findUser 方法中返回 User 对象，
        // 即当调用 UserRepository.findUser 方法时返回 expected User
        when(mockUserRepository.findUser(anyString())).thenReturn(expectedUser);

        // 对 /user/00001 执行 get 请求
        // 预期视图的名称为 user
        // 预期 model 中包含名为 user 的属性
        // 预期 model 中名为 user 的属性值为 expectedUser
        mockMvc.perform(get("/user/00001")).andExpect(view().name("user")).andExpect(model().attributeExists("user"))
                .andExpect(model().attribute("user", expectedUser));
    }

    @Test
    public void testUsers() throws Exception {

        // 创建 expected Users
        List<User> expectedUsers = createUsers(3);

        // 创建 UserRepository 接口的 Mock 实现，
        // 这个实现会从他的 findUsers 方法中返回复数个 User 对象，
        // 即当调用 UserRepository.findUsers 方法时返回 expected Users
        when(mockUserRepository.findUsers()).thenReturn(expectedUsers);

        // 对 /user 执行 get 请求
        // 预期视图的名称为 user
        // 预期 model 中包含名为 userList 的属性
        // 预期 model 中名为 userList 的属性值包含 expectedUsers 的所有内容
        mockMvc.perform(get("/user")).andExpect(view().name("user")).andExpect(model().attributeExists("userList"))
                .andExpect(model().attribute("userList", hasItems(expectedUsers.toArray())));
    }

    private User createUser() {

        Date birthday = Date.from(LocalDateTime.of(1982, 4, 1, 0, 0).toInstant(ZoneOffset.UTC));
        return new User("00000", "紀伊太郎", "test@test.com", 25, birthday);
    }

    private List<User> createUsers(int count) {
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < count; i++) {
            String userid = String.format("%05d", i);
            int age = (int) (Math.random() * 100);
            String username = "紀伊太郎-" + age;
            String email = "test" + i + "@test.com";
            Date birthday = Date.from(LocalDateTime.of(1982, 4, 1, 0, 0).toInstant(ZoneOffset.UTC));
            User user = new User(userid, username, email, age, birthday);
            users.add(user);
        }
        return users;
    }
}
