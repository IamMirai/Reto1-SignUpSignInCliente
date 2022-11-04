/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.concurrent.TimeoutException;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import main.ApplicationFXSignUpTest;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;

/**
 *
 * @author 2dam
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignUpVControllerTest extends ApplicationTest {

    private Text labelInvalidUser;
    private Text labelInvalidEmail;
    private Text labelInvalidName;
    private Text labelInvalidPassword;
    private TextField textFieldUsername;
    private TextField textFieldEmail;
    private PasswordField passwordField;
    
    @BeforeClass
    public static void setUpClass() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(ApplicationFXSignUpTest.class);
    }

    @Test
    public void test1_InitialStage() {
        verifyThat("#passwordField", isEnabled());
        verifyThat("#passwordFieldConfirm", isEnabled());
        verifyThat("#textFieldUsername", hasText(""));
        verifyThat("#textFieldEmail", hasText(""));
        verifyThat("#textFieldName", hasText(""));
        verifyThat("#passwordField", hasText(""));
        verifyThat("#passwordFieldConfirm", hasText(""));
        verifyThat("#textFieldPassword", hasText(""));
        verifyThat("#textFieldConfirmPassword", hasText(""));
        verifyThat("#buttonSignUp", isEnabled());
        verifyThat("#buttonSignIn", isEnabled());

    }

    @Test
    public void test2_FieldsEmpty() {
        labelInvalidUser = lookup("#labelInvalidUser").query();
        labelInvalidEmail = lookup("#labelInvalidEmail").query();
        labelInvalidName = lookup("#labelInvalidName").query();
        labelInvalidPassword = lookup("#labelInvalidPassword").query();
        clickOn("#buttonSignUp");
        assertEquals("Username can't be empty nor contain an empty space.", labelInvalidUser.getText());
        assertEquals("Invalid format of email (*@*.*)", labelInvalidEmail.getText());
        assertEquals("Name is empty", labelInvalidName.getText());
        assertEquals("Password can't be empty nor contain an empty space or his lenght is less than 8.", labelInvalidPassword.getText());
    }

    @Test
    public void test3_UsernameIsInvalid() {
        labelInvalidUser = lookup("#labelInvalidUser").query();
        textFieldUsername = lookup("#textFieldUsername").query();
        clickOn("#textFieldUsername");
        write("test test");
        clickOn("#textFieldEmail");
        assertEquals("Username can't be empty nor contain an empty space.", labelInvalidUser.getText());
        
        clickOn("#textFieldUsername");
         eraseText(9);
        write("01234567890123456789012345");
        clickOn("#textFieldEmail");
        assertTrue(textFieldUsername.getText().length() <=25);
        
        doubleClickOn("#textFieldUsername").push(KeyCode.DELETE);
        write("Test1");
        assertEquals("", labelInvalidUser.getText());
    }
    
    @Test
    public void test4_EmailIsInvalid() {
        labelInvalidEmail = lookup("#labelInvalidEmail").query();
        textFieldEmail = lookup("#textFieldEmail").query();
        clickOn("#textFieldEmail");
        write("test");
        clickOn("#textFieldName");
        assertEquals("Invalid format of email (*@*.*)", labelInvalidEmail.getText());
        
        clickOn("#textFieldEmail");
        eraseText(4);
        write("test@test");
        clickOn("#textFieldName");
        assertEquals("Invalid format of email (*@*.*)", labelInvalidEmail.getText());
        
        clickOn("#textFieldEmail");
        eraseText(9);
        write("test@test.test");
        clickOn("#textFieldName");
        assertEquals("", labelInvalidEmail.getText());
    }
    
    @Test
    public void test5_PasswordIsInvalid() {
        labelInvalidPassword = lookup("#labelInvalidPassword").query();
        passwordField = lookup("#passwordField").query();
        clickOn("#passwordField");
        write("test");
        clickOn("#passwordFieldConfirm");
        assertEquals("Password can't be empty nor contain an empty space or his lenght is less than 8.", labelInvalidPassword.getText());
        
        clickOn("#passwordField");
        eraseText(4);
        write("test test");
        clickOn("#passwordFieldConfirm");
        assertEquals("Password can't be empty nor contain an empty space or his lenght is less than 8.", labelInvalidPassword.getText());
        
        clickOn("#passwordField");
        eraseText(9);
        write("abcd*1234");
        clickOn("#passwordFieldConfirm");
        assertEquals("", labelInvalidPassword.getText());
    }

}
