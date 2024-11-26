package com.company;

import com.company.unitTesting.dto.EmailDto;
import com.company.unitTesting.dto.EmailTemplateDto;
import com.company.unitTesting.dto.UserDto;
import com.company.unitTesting.service.EmailMessenger;
import com.company.unitTesting.service.EmailTemplateEngine;
import com.company.unitTesting.service.MailServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class EmilMessengerTest {
    private static final String RANDOM_MESSAGE = "Message";
    private static final String RANDOM_EMAIL = "email@email.com";


    @Mock
    private EmailTemplateEngine templateEngineMock;

    /*
 * Other possible answers:
      -	CALLS_REAL_METHODS
    -	RETURNS_DEFAULTS
    -	RETURNS_MOCKS
    -	RETURNS_SELF
    -	RETURNS_SMART_NULLS

 */
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private MailServer mailServerMock;

    @InjectMocks
    private EmailMessenger messenger;

    @Captor
    private ArgumentCaptor<EmailDto> captor;

    private AutoCloseable mocks;


    @BeforeEach
    void setUp() {
        // We can create mock based on the Interface or a Class
//    	templateEngine = mock(TemplateEngine.class);

        // init mock with annotations
//    	MockitoAnnotations.initMocks(this);

        // just another version of initilization of mocks with annotation
        // pay attention to tear down method - we have to call close method
        mocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    @Test
    void sendMessageTest() {
        // given
        InOrder inOrder = inOrder(templateEngineMock,mailServerMock);
        UserDto userDto = new UserDto(RANDOM_EMAIL);
        EmailTemplateDto templateDto = new EmailTemplateDto();
        when(templateEngineMock.prepareMessage(userDto, templateDto)).thenReturn(RANDOM_MESSAGE);

        /*OR use :::*/
/*        when(templateEngineMock.prepareMessage(any(UserDto.class), any(EmailTemplateDto.class))).thenReturn(RANDOM_EMAIL);  */
        /*
         * Other possible matchers:
         * - anyString()
         * - anyInt()
         * - etc
         */

        // write this statement instead with eq() argument matcher
//    	when(templateEngineMock.prepareMessage(eq(client), any(EmailTemplateDto.class)))
//			.thenReturn(RANDOM_EMAIL);

        /*
         * Also otehr matchers available:
         * - eq()
         * - isA(Class type)
         * - isNull()
         * - isNotNull()
         * - matches(regex)
         * - etc
         */

        // this statements will throw an exception : case if we use matcher in one arg  we should use it for all args
//    	when(templateEngineMock.prepareMessage(client, any(EmailTemplateDto.class)))
//    		.thenReturn(RANDOM_EMAIL);

        // when
        messenger.sendMessage(userDto, templateDto);

        // then
        verify(templateEngineMock).prepareMessage(userDto, templateDto);
        verify(mailServerMock).send(any(EmailDto.class));

//        check if this method call once
        verify(templateEngineMock, times(1)).prepareMessage(userDto, templateDto);
        verify(templateEngineMock, atLeast(1)).prepareMessage(userDto, templateDto);
        verify(templateEngineMock, atLeastOnce()).prepareMessage(userDto, templateDto);
        verify(templateEngineMock, atMost(1)).prepareMessage(any(UserDto.class), any(EmailTemplateDto.class));
//        check if this method never call => test fail
//        verify(templateEngineMock, never()).prepareMessage(userDto, templateDto);

        inOrder.verify(templateEngineMock).prepareMessage(userDto, templateDto);
        inOrder.verify(mailServerMock).send(any(EmailDto.class));

        verifyNoMoreInteractions(templateEngineMock, mailServerMock);

//        In this case verifyNoMoreInteractions will throw an exception : because we don't verify on previous code
//        templateEngineMock.evaluateTemplate(null);
//        verifyNoMoreInteractions(templateEngineMock);
    }

    @Test
    public void sendMessageTestV2() {
        // given
        UserDto client = new UserDto(RANDOM_EMAIL);
        EmailTemplateDto template = new EmailTemplateDto();
        when(templateEngineMock.prepareMessage(client, template)).thenReturn(RANDOM_MESSAGE);

        messenger.sendMessage(client, template);

        verify(templateEngineMock).prepareMessage(client, template);
        verify(mailServerMock).send(captor.capture());
        Assertions.assertEquals(client.getEmail(), captor.getValue().getAddress());
    }

    @Test
    void callRealMethodOnMockExample() {
        UserDto client = new UserDto("");
        EmailTemplateDto template = new EmailTemplateDto();

        // null is returned
        System.out.println(templateEngineMock.prepareMessage(client, template));

        when(templateEngineMock.prepareMessage(client, template)).thenCallRealMethod();
        // 'Some template' will be returned - because that is the result of prepare message 
        System.out.println(templateEngineMock.prepareMessage(client, template));
    }


    @Test
    void spyExample() {
        List<Integer> listInts = new ArrayList<>();
        List<Integer> spy = spy(listInts);

        // OutOfBoundsException because get method is called on real list that is empty
//    	when(spy.get(0)).thenReturn(0);

        doReturn(0).when(spy).get(0);
        System.out.println(spy.get(0));

        verify(spy).get(0);
    }


}
